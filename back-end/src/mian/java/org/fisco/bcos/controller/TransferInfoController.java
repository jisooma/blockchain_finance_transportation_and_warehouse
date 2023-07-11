package org.fisco.bcos.controller;


import org.fisco.bcos.contracts.DigitalCurrencyCall;
import org.fisco.bcos.entity.Account;
import org.fisco.bcos.entity.TransferInfo;
import org.fisco.bcos.service.AccountService;
import org.fisco.bcos.service.TransferInfoService;
import org.fisco.bcos.utils.PaillierTest;
import org.fisco.bcos.utils.paillier.PaillierCipher;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transferInfo")
public class TransferInfoController {
    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    @PostMapping("/addTransferInfo")
    public int addAppoint(HttpServletRequest httpServletRequest) throws Exception {
        String payer = httpServletRequest.getParameter("payer");
        String payee = httpServletRequest.getParameter("payee");
        String money =  httpServletRequest.getParameter("money");
        String remark =  httpServletRequest.getParameter("remark");
        Date date =new Date();
        Timestamp ts = new Timestamp(date.getTime());


        //需要对账户的余额进行判断
//        boolean b1 = Integer.parseInt(accountService.findByName(payer).getBal())>Integer.parseInt(money);
        //转账的信息需要上链，并且使用同态加密对金额进行加密。
        ;
        int i=-1;
        if(Integer.parseInt(accountService.findByName(payer).getBal())>=Integer.parseInt(money)){

           i = transferInfoService.addTransferInfo(new TransferInfo(payer,payee,money,"普通转账",ts,remark));

            Account accountPayer = accountService.findByName(payer);
            Account accountPayee = accountService.findByName(payee);
            //拿到融资企业的地址
            String payerAddr=accountPayer.getAddr();
            String payeeAddr=accountPayee.getAddr();

            //查看payer和payee的余额
            DigitalCurrencyCall digitalCurrencyCall = DigitalCurrencyCall.getInstance(web3j,credentials);
            String payerBal=digitalCurrencyCall.checkBal(payerAddr);
            String payeeBal=digitalCurrencyCall.checkBal(payeeAddr);

            //读取公钥
            RSAPublicKey pub = PaillierTest.read_paillier_Pub();
            RSAPrivateKey pri = PaillierTest.read_paillier_Pri();

            //将金额转为整型
            int m = Integer.parseInt(money);
            accountPayer.setBal(String.valueOf(Integer.parseInt(accountPayer.getBal())-m));
            accountPayee.setBal(String.valueOf(Integer.parseInt(accountPayee.getBal())+m));
            accountService.updateAccount(accountPayee);
            accountService.updateAccount(accountPayer);
            System.out.println("payee:"+accountPayee.getBal());
            //数据库中帐户余额加减
            //对转账的金额的进行加密，接收方加上转账的金额，发送方减去发送的金额。
            String transferMoney1 = PaillierCipher.encrypt(BigInteger.valueOf(m),pub);//600
            String transferMoney2 = PaillierCipher.encrypt(BigInteger.valueOf(m*(-1)),pub);//-600
            digitalCurrencyCall.TokenSend(payerAddr,payeeAddr,transferMoney1,transferMoney2,money,"普通转账",String.valueOf(ts));


//            DigitalCurrencyCall11.TokenSend(payerAddr,payeeAddr,transferMoney1,transferMoney2,money);
            System.out.println("digitalCurrencyCall----payer--:"+PaillierCipher.decrypt(digitalCurrencyCall.checkBal(payerAddr),pri));
            System.out.println("digitalCurrencyCall----payee--:"+PaillierCipher.decrypt(digitalCurrencyCall.checkBal(payeeAddr),pri));
//            System.out.println("digitalCurrencyCall11--payee----:"+digitalCurrencyCall.checkBal(payeeAddr));

//            //接收企业余额加上对应金额的密文
//            String payeeNowBal=PaillierCipher.ciphertextAdd(payeeBal,transferMoney1);//400
//            //支付企业余额减去对应金额的密文
//            String payerNowBal=PaillierCipher.ciphertextAdd(payerBal,transferMoney2);
            //更新企业对应账户的余额
//            digitalCurrencyCall.TokenSend(payerAddr,payeeAddr,payerNowBal,payeeNowBal,transferMoney1);
        }
       return  i;

//        //读取私钥
//        //解密账户对应的余额
//        RSAPrivateKey pri = PaillierTest.read_paillier_Pri();
//        BigInteger payerDe=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(payerAddr),pri);
//        BigInteger payeeDe=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(payeeAddr),pri);
//        System.out.println(payerDe);
//        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
//        System.out.println(payeeDe);

    }

    @PostMapping("/queryMyTransferInfo")
    public List<TransferInfo> queryMyTransferInfo(HttpServletRequest httpServletRequest) throws Exception {
        String name = httpServletRequest.getParameter("name");
        List<TransferInfo> transferInfos= transferInfoService.queryMyTransferInfo(name);
        //查询到的金额需要解密。
        return transferInfos;
    }

    @PostMapping("/queryMyTransferInfoByType")
    public List<TransferInfo> queryMyTransferInfoByType(HttpServletRequest httpServletRequest) throws Exception {
        String name = httpServletRequest.getParameter("name");
        List<TransferInfo> transferInfos= transferInfoService.queryMyTransferInfo(name);
        //查询到的金额需要解密。
        return transferInfos;
    }
    @PostMapping("/queryTransferInfoByPayee")
    public List<TransferInfo> queryTransferInfoByPayee(HttpServletRequest httpServletRequest) throws Exception {
        String payee = httpServletRequest.getParameter("payee");
        //查询到的金额需要解密。
       List<TransferInfo> transferInfos= transferInfoService.queryTransferInfoByPayeeAndType(payee,"普通类型");
        return transferInfos;
    }
    @PostMapping("/queryTransferInfoByPayer")
    public List<TransferInfo> queryTransferInfoByPayer(HttpServletRequest httpServletRequest) throws Exception {
        String payer = httpServletRequest.getParameter("payer");
        List<TransferInfo> transferInfos = transferInfoService.queryTransferInfoByPayerAndType(payer,"普通类型");
        return transferInfos;
    }

    @PostMapping("/queryAllTransferInfo")
    public List<TransferInfo> queryAllTransferInfo(HttpServletRequest httpServletRequest) throws Exception {
        List<TransferInfo> transferInfos= transferInfoService.queryAllTransferInfo();
        return transferInfos;
    }

}
