package org.fisco.bcos.controller;


import org.fisco.bcos.contracts.DigitalCurrencyCall;
import org.fisco.bcos.contracts.LoanReciptCall;
import org.fisco.bcos.contracts.WareHouseReciptCall;
import org.fisco.bcos.entity.*;
import org.fisco.bcos.entity.LoanRecipt;
import org.fisco.bcos.service.*;
import org.fisco.bcos.utils.PaillierTest;
import org.fisco.bcos.utils.paillier.PaillierCipher;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/loanRecipt")
public class LoanReciptController {
    @Autowired
    private LoanReciptService loanReciptService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private WarehouseReceiptService warehouseReceiptService;
    @Autowired
    private RespositoryService respositoryService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private LoanApplyService loanApplyService;
    @Autowired
    private LoanContractService loanContractService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;


    //银行的业务
    @PostMapping("/addLoanRecipt")
    public int addLoanRecipt(HttpServletRequest httpServletRequest) throws Exception {
        String enterprise = httpServletRequest.getParameter("enterprise");
        String bank = httpServletRequest.getParameter("bank");
        String loID =  httpServletRequest.getParameter("loID");
        String allMoney =  httpServletRequest.getParameter("allMoney");
        String dueTime = httpServletRequest.getParameter("dueTime");
        Date date =new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Timestamp ts2 = Timestamp.valueOf(dueTime);

        //拿到银行和融资企业的地址
        String enterpriseAddr=accountService.findByName(enterprise).getAddr();
        String bankAddr=accountService.findByName(bank).getAddr();


        int i  = loanReciptService.addLoanRecipt(new LoanRecipt(enterprise,bank,Integer.parseInt(loID),allMoney,"0","贷款账单生效中",ts,ts2));

        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        //读取同态加密的公钥
        RSAPublicKey pub = PaillierTest.read_paillier_Pub();
        //将贷款金额加密
        String amount =  PaillierCipher.encrypt(new BigInteger(allMoney),pub);
        //贷款账单上链
        LoanReciptCall loanReciptCall = LoanReciptCall.getInstance(web3j,credentials);
        boolean bool=loanReciptCall.addLoanRecipt(new BigInteger(loID),enterpriseAddr,bankAddr,amount,ts.toString(),dueTime);
        //更新链上对应融资企业的数字货币余额。
        DigitalCurrencyCall digitalCurrencyCall = DigitalCurrencyCall.getInstance(web3j,credentials);
//        digitalCurrencyCall.MintCoin(credentials.getAddress(),enterpriseAddr,amount);
        Map<String,String> data = loanReciptCall.getLoanReciptByLoanId(new BigInteger(loID));
        System.out.println(data);
        return i;
    }

    @PostMapping("/repayLoanRecipt")
    public int updateLoanRecipt(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String reMoney = httpServletRequest.getParameter("reMoney");
        Date date =new Date();
        Timestamp ts = new Timestamp(date.getTime());

        LoanRecipt loanRecipt = loanReciptService.queryLoanReciptByID(Integer.parseInt(id));
        loanRecipt.setReMoney(reMoney);
        loanRecipt.setStatus("已偿还贷款");
        loanRecipt.setUpdateTime(ts);

        int i  = loanReciptService.updateLoanRecipt(loanRecipt);

        RSAPublicKey pub =  PaillierTest.read_paillier_Pub();
        String m = PaillierCipher.encrypt(new BigInteger(reMoney),pub);
        //修改链上贷款账单的状态
//        LoanReciptCall loanReciptCall = LoanReciptCall.getInstance(web3j,credentials);
//        boolean bool = loanReciptCall.RepayLoanRecipt(new BigInteger(id),m,ts.toString());


        //修改数据库账户余额状态
        Account bank= accountService.findByName(loanRecipt.getBank());
        Account enterprise = accountService.findByName(loanRecipt.getEnterprise());
        int bankBal=Integer.parseInt(bank.getBal());
        int enterpriseBal = Integer.parseInt(enterprise.getBal());
//        new Double(v).intValue()
        int Money=Integer.parseInt(String.valueOf(new Double((loanRecipt.getAllMoney())).intValue()));
        bankBal=bankBal+Money;
        enterpriseBal = enterpriseBal-Money;
        bank.setBal(String.valueOf(bankBal));
        enterprise.setBal(String.valueOf(enterpriseBal));
        System.out.println("还款金额："+Money);
        System.out.println("数据库银行余额："+bankBal);
        System.out.println("数据库企业余额："+enterpriseBal);
        enterprise.setBal(String.valueOf(enterpriseBal));
        accountService.updateAccount(bank);
        accountService.updateAccount(enterprise);

        transferInfoService.addTransferInfo(new TransferInfo(enterprise.getName(),bank.getName(),loanRecipt.getAllMoney(),"借贷转账",ts,"还款"));


        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        //修改链上对应的账户余额状态。
        String bankAddr = bank.getAddr();
        String enterpriseAddr = enterprise.getAddr();
        //读取同态加密公钥
//        RSAPublicKey pub = PaillierTest.read_paillier_Pub();
        //读取同态加密私钥
        RSAPrivateKey pri= PaillierTest.read_paillier_Pri();
        //实例化智能合约
        DigitalCurrencyCall digitalCurrencyCall = DigitalCurrencyCall.getInstance(web3j,credentials);

        //解密链上银行账户的余额
        BigInteger payer1=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(bankAddr),pri);
        //解密链上贷款企业账户的余额
        BigInteger payee1=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(enterpriseAddr),pri);
        System.out.println(enterprise+"---链上余额------"+payee1);
        System.out.println(bank+"----链上余额-----"+payer1);
        //需要对贷款金额求相反数，方便后续实现加密。
        int Money1 = -Money;
        //对贷款账单的余额进行同态加密
        String amount = PaillierCipher.encrypt(new BigInteger(String.valueOf(Money)),pub);
        String amount1 = PaillierCipher.encrypt(new BigInteger(String.valueOf(Money1)),pub);
        System.out.println("解密贷款金额："+PaillierCipher.decrypt(amount,pri));
        System.out.println("解密贷款金额相反数："+PaillierCipher.decrypt(amount1,pri));
//        //贷款企业账户余额加上贷款金额
//        String enterpriseNewBal = PaillierCipher.ciphertextAdd(digitalCurrencyCall.checkBal(enterpriseAddr),amount);
//        //银行账户余额加上贷款金额的相反数。
//        String bankNewBal = PaillierCipher.ciphertextAdd(digitalCurrencyCall.checkBal(bankAddr),amount1);
        //amount为还款企业要减去的余额
        //amount1为银行要加上的余额
        digitalCurrencyCall.TokenSend(enterpriseAddr,bankAddr,amount,amount1,amount,"借贷转账",String.valueOf(ts));

        //解密链上银行账户的余额
        BigInteger payer=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(bankAddr),pri);
        //解密链上贷款企业账户的余额
        BigInteger payee=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(enterpriseAddr),pri);
        System.out.println(enterprise+"---链上余额------"+payee);
        System.out.println(bank+"----链上余额-----"+payer);


        //拿回仓单的所有权
        LoanApply loanApply =loanApplyService.queryLoanApplyByID(loanRecipt.getLoanID());
        Inventory inventory = inventoryService.queryInventoryByID(loanApply.getInID());
        WarehouseReceipt warehouseReceipt=warehouseReceiptService.queryWarehouseReceiptByID(inventory.getWaNo());
        warehouseReceipt.setHolder(enterprise.getName());
        warehouseReceipt.setStatus("合法");
        warehouseReceipt.setUpdateTime(ts);
        Respository respository = respositoryService.queryRespositoryByID(warehouseReceipt.getReno());
        respository.setStatus("同意入库");
        respository.setUpdateTime(ts);
        respositoryService.updateRespository(respository);
        warehouseReceiptService.updateWarehouseReceipt(warehouseReceipt);

        //拿回链上对应的仓单所有权
        WareHouseReciptCall warehouseReceiptCall = WareHouseReciptCall.getInstance(web3j,credentials);
        warehouseReceiptCall.updateWareHouseReciptStatusAndHolder(new BigInteger(String.valueOf(warehouseReceipt.getWaNo())),"合法",enterprise.getName(),String.valueOf(ts));

        //更新贷款账单
        LoanContract loanContract = loanContractService.queryLoanContractByReciptID(loanRecipt.getID());
        loanContract.setStatus("贷款已偿还,合同失效");
        loanContractService.updateLoanContract(loanContract);
        //更新贷款申请
        loanApply.setStatus("贷款申请失效");
        loanApply.setUpdateTime(ts);
        loanApplyService.updateLoanApply(loanApply);
        return i;
    }

    @GetMapping("/queryLoanReciptbyId/{id}")
    public List<LoanRecipt> queryLoanReciptbyId(@PathVariable("id") int id){
        List<LoanRecipt> lists = new LinkedList<>();
        lists.add(loanReciptService.queryLoanReciptByID(id));
        return  lists;
    }
    @GetMapping("/queryLoanReciptById/{id}")
    public LoanRecipt queryLoanReciptById(@PathVariable("id") int id){
        return  loanReciptService.queryLoanReciptByID(id);
    }
    @GetMapping("/queryLoanReciptByEnterpirse/{enterprise}")
    public List<LoanRecipt> queryLoanReciptByEnterpirse(@PathVariable("enterprise") String enterprise){
        return  loanReciptService.queryLoanReciptByEnterpirse(enterprise);
    }
    @GetMapping("/queryLoanReciptByBank/{bank}")
    public List<LoanRecipt> queryLoanReciptByBank(@PathVariable("bank") String bank){
        return  loanReciptService.queryLoanReciptByBank(bank);
    }
    @GetMapping("/queryLoanReciptByenterpriseAndBank/{enterprise}/{bank}")
    public List<LoanRecipt> queryLoanReciptByenterpriseAndBank(@PathVariable("enterprise") String enterprise,@PathVariable("bank") String bank){
        return  loanReciptService.queryLoanReciptByenterpriseAndBank(enterprise,bank);
    }
    @GetMapping("/queryLoanReciptByEnterpriseAndStatus/{enterprise}/{status}")
    public List<LoanRecipt> queryLoanReciptByEnterpirseAndStatus(@PathVariable("enterprise") String enterprise,@PathVariable("status") String status){
        return  loanReciptService.queryLoanReciptByEnterpirseAndStatus(enterprise, status);
    }
    @GetMapping("/queryLoanReciptByBankAndStatus/{bank}/{status}")
    public List<LoanRecipt> queryLoanReciptByBankAndStatus(@PathVariable("bank") String bank,@PathVariable("status") String status){
        return  loanReciptService.queryLoanReciptByBankAndStatus(bank,status);
    }
    @GetMapping("/queryLoanReciptByStatus/{status}")
    public List<LoanRecipt> queryLoanReciptByStatus(@PathVariable("status")String status){
        return  loanReciptService.queryLoanReciptByStatus(status);
    }
}
