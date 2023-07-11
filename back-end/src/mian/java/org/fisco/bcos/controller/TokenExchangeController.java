package org.fisco.bcos.controller;

import org.fisco.bcos.contracts.DigitalCashCall;
import org.fisco.bcos.entity.Account;
import org.fisco.bcos.entity.TokenExchange;
import org.fisco.bcos.service.AccountService;
import org.fisco.bcos.service.TokenExchangeService;
import org.fisco.bcos.utils.Paillier;
import org.fisco.bcos.utils.PaillierTest;
import org.fisco.bcos.utils.paillier.PaillierCipher;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tokenExchange")
public class TokenExchangeController {
    @Autowired
    private TokenExchangeService tokenExchangeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;


    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:32
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/addTokenExchange")
    public int addTokenExchange(HttpServletRequest httpServletRequest) throws Exception {
        String bank = httpServletRequest.getParameter("bank");
        String initiator = httpServletRequest.getParameter("initiator");
        String token =  httpServletRequest.getParameter("token");
        String updateTime  = httpServletRequest.getParameter("updateTime");
        Timestamp ts = Timestamp.valueOf(updateTime);
        System.out.println(initiator);
        int i = tokenExchangeService.addTokenExchange(new TokenExchange(bank,initiator,token,"等待银行发放",ts));
        return  i;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:33
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/agreeTokenExchange")
    public int updateTokenExchange(HttpServletRequest httpServletRequest) throws Exception {
        String id= httpServletRequest.getParameter("id");
        String status= "已发放";
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        TokenExchange tokenExchange = tokenExchangeService.queryTokenExchangeByID(Integer.parseInt(id));

        tokenExchange.setStatus(status);
        tokenExchange.setUpdateTime(t);
        int i = tokenExchangeService.updateTokenExchange(tokenExchange);
        //Token


        //银行同意兑换的话，则申请兑换的企业的账户的数字余额数量减少。
//        if(Integer.parseInt(status)==1){
            DigitalCashCall digitalCashCall = DigitalCashCall.getInstance(web3j,credentials);
            //拿到想要兑付的数字货币金额
            String token=tokenExchange.getToken();
            Account bank = accountService.findByName(tokenExchange.getinitiator());
            Account initiator =accountService.findByName(tokenExchange.getBank());
            //修改数据库中的兑付企业的账户余额
            initiator.setBal(String.valueOf(Integer.valueOf(initiator.getBal())-Integer.valueOf(token)));
            accountService.updateAccount(initiator);
            //

            String initiatorAddr=initiator.getAddr();
            String bankAddr=bank.getAddr();


            //从链上拿到申请兑付企业对应的账户地址的数字货币余额密文。
            String bal = digitalCashCall.checkBal(initiatorAddr);


            int m= Integer.valueOf(token);
            BigInteger n = BigInteger.valueOf((m*(-1)));
            //读取公钥
            RSAPublicKey pub = PaillierTest.read_paillier_Pub();
            String tokenCipher =  PaillierCipher.encrypt(n,pub);
            String amount = PaillierCipher.ciphertextAdd(bal,tokenCipher);
            RSAPrivateKey pri = PaillierTest.read_paillier_Pri();
            System.out.println(PaillierCipher.decrypt(digitalCashCall.checkBal(initiatorAddr),pri)+"===剩余数字货币");
            digitalCashCall.TokenExchange(amount,initiatorAddr,bankAddr,token);
//        }
        return  i;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:33
     * @param
     * @return
     */
    @PostMapping("/queryAllTokenExchange")
    public List<TokenExchange> queryAllTokenExchange(){
        List<TokenExchange> tokenExchanges = tokenExchangeService.queryAllTTokenExchange();
        return  tokenExchanges;

    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:33
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryTokenExchangeByBank")
    public List<TokenExchange> queryTokenExchangeByBank(HttpServletRequest httpServletRequest){
        String bank = httpServletRequest.getParameter("bank");
        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByBank(bank);
        return  tokenExchanges;
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:33
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryTokenExchangeByInitiator")
    public List<TokenExchange>  queryTokenExchangeByInitiator(HttpServletRequest httpServletRequest){
        String initiator = httpServletRequest.getParameter("initiator");
        System.out.println(initiator);
        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByInitiator(initiator);
        return  tokenExchanges;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:33
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryTokenExchangeByBankAndInitiator")
    public List<TokenExchange> queryTokenExchangeByBankAndInitiator(HttpServletRequest httpServletRequest){
        String bank = httpServletRequest.getParameter("bank");
        String initiator = httpServletRequest.getParameter("initiator");
        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByInitiatorAndBank(initiator,bank);
        return  tokenExchanges;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/28 19:33
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryTokenExchangeByID")
    public TokenExchange queryTokenExchangeByID(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
       TokenExchange tokenExchange=tokenExchangeService.queryTokenExchangeByID(Integer.parseInt(id));
        return  tokenExchange;
    }

    @GetMapping("/queryTokenExchangeByBankAndStatus/{bank}/{status}")
    public List<TokenExchange> queryTokenExchangeByBankAndStatus(@PathVariable("bank") String bank,@PathVariable("status") String status){
        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByBankAndStatus(bank,status);
        return  tokenExchanges;
    }
    @GetMapping("/queryTokenExchangeByInitiatorAndStatus/{initiator}/{status}")
    public List<TokenExchange> queryTokenExchangeByInitiatorAndStatus(@PathVariable("initiator") String initiator,@PathVariable("initiator") String status){
        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByInitiatorAndStatus(initiator, status);
        return  tokenExchanges;
    }

}
