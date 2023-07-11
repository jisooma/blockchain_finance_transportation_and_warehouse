package org.fisco.bcos.controller;

import org.fisco.bcos.chaindata.ContractHashDetail;
import org.fisco.bcos.contracts.ContractHashCall;
import org.fisco.bcos.contracts.DigitalCurrencyCall;
import org.fisco.bcos.contracts.WareHouseReciptCall;
import org.fisco.bcos.entity.*;
import org.fisco.bcos.service.*;
import org.fisco.bcos.utils.MD5Util;
import org.fisco.bcos.utils.PaillierTest;
import org.fisco.bcos.utils.fillpdf.PDFUtils;
import org.fisco.bcos.utils.paillier.PaillierCipher;
import org.fisco.bcos.utils.stamp.ItextUtil;
import org.fisco.bcos.utils.stamp.SignatureInfo;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/3/26 10:51
 * @Email:861359297@qq.com
 */

@CrossOrigin
@RestController
@RequestMapping("/LoanContract")
public class LoanContractController {
    @Autowired
    private LoanContractService LoanContractService;
    @Autowired
    private LoanApplyService loanApplyService;
    @Autowired
    private LoanReciptService loanReciptService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private WarehouseReceiptService warehouseReceiptService;
    @Autowired
    private ContractHashDetailService contractHashDetailService;
    @Autowired
    private RespositoryService respositoryService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    @Value("${mzx.file.root.path}")
    private String filePath;

    /**
     * @author: mazhixiu
     * @description: 生成Loan-，银行已经签署，等待贷款企业同意签署
     * @date: 2021/3/28 20:25
     * @param httpServletRequest
     * @return
     */

    @PostMapping("/addLoanContract")
    public int addLoanApply(HttpServletRequest httpServletRequest) throws Exception {
        String interests = httpServletRequest.getParameter("interests");
        String id= httpServletRequest.getParameter("id");
        //贷款申请信息
        LoanApply loanApply = loanApplyService.queryLoanApplyByID(Integer.parseInt(id));
        //贷款账单
        LoanRecipt loanRecipt=loanReciptService.queryLoanReciptByloanID(Integer.parseInt(id));

        //拿到质押贷款对应的信息
        int inID = loanApply.getInID();
        String enterprise = loanApply.getEnterprise();
        String bank = loanApply.getBank();
        int waNo = inventoryService.queryInventoryByID(inID).getWaNo();
        String  loanPurpose = loanApply.getLoanPurpose();
        String Money = loanApply.getLoMoney();
        Timestamp startTime = loanApply.getUpdateTime();
        Timestamp dueTime = loanApply.getDueTime();

        //生成质押贷款合同
        System.out.println(Double.parseDouble(interests));
        int i = LoanContractService.addLoanContract(new LoanContract(enterprise,bank,loanPurpose,Money,waNo,loanRecipt.getID(),Double.parseDouble(interests),Money,startTime,dueTime,"等待签署"));

        LoanContract LoanContract = LoanContractService.queryLoanContractByBankAndEnterpriseAndTime(bank,enterprise,startTime);
        System.out.println(LoanContract);
        Map<String, String> data = new HashMap<String, String>();
        //key为pdf模板的form表单的名字，value为需要填充的值
        data.put("id", String.valueOf(LoanContract.getId()));
        data.put("bank", LoanContract.getBank());
        data.put("enterprise",LoanContract.getEnterprise());
        data.put("loanPurpose",LoanContract.getLoanPurpose());
        data.put("money", LoanContract.getMoney());
        data.put("interests",String.valueOf(LoanContract.getInterests()));
        data.put("waNo",String.valueOf(LoanContract.getWaNo()));
        data.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(LoanContract.getStartTime()));
        data.put("dueTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(LoanContract.getDueTime()));

        String src="F:\\mazhixiu\\contracts\\Loan\\LoanContractTemplate.pdf";
        String des="F:\\mazhixiu\\contracts\\Loan\\Loan-"+bank+"-"+enterprise+"Filled.pdf";

        PDFUtils.generatePDF(src,des,data);

        //银行盖章
        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
        String image = "F:\\mazhixiu\\seals\\"+LoanContract.getBank()+".png";
        String filed = "text";
        //470//250
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,800);
        ItextUtil.sign(des,des,signatureInfo);
        return  i;
    }

    @PostMapping("/disagreeLoanContract")
    public int disagreeLoanContract(HttpServletRequest httpServletRequest) throws Exception {
        //根据贷款质押合同的id取出Loan-
        String id = httpServletRequest.getParameter("id");
        LoanContract LoanContract = LoanContractService.queryLoanContractByID(Integer.parseInt(id));
        LoanContract.setStatus("拒绝签署");
        int i = LoanContractService.updateLoanContract(LoanContract);
        LoanRecipt loanRecipt = loanReciptService.queryLoanReciptByID(LoanContract.getReciptId());
        loanRecipt.setStatus("账单无效");
        loanReciptService.updateLoanRecipt(loanRecipt);
        return i;
    }
    /**
     * @author: mazhixiu
     * @description: 同意签署Loan，激活贷款账单
     * @date: 2021/3/28 20:25
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/agreeLoanContract")
    public int agreeLoanContract(HttpServletRequest httpServletRequest) throws Exception {
        //根据贷款质押合同的id取出Loan-
        String id = httpServletRequest.getParameter("id");
        LoanContract LoanContract = LoanContractService.queryLoanContractByID(Integer.parseInt(id));
        System.out.println(LoanContract);
        Date updateTime = new Date();
        Timestamp ts = new Timestamp(updateTime.getTime());
        LoanContract.setStatus("同意签署");
//        LoanContract.setUpdateTime(ts);
        int i = LoanContractService.updateLoanContract(LoanContract);
//        loanApplyService.queryLoanApplyByID(LoanContract.)

        //String path="F:\\gl-file\\upload\\seal\\Loan-fill.pdf";
        String des ="F:\\mazhixiu\\contracts\\Loan\\Loan-"+LoanContract.getBank()+"-"+LoanContract.getEnterprise()+"Filled.pdf";

        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
//        String pkPath ="F:\\gl-file\\upload\\seal\\"+user1Service.findByName(AppointContract.getLogistics())+".pem";

        String image = "F:\\mazhixiu\\seals\\"+LoanContract.getEnterprise()+".png";
        String filed = "text1";
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,1000);
        ItextUtil.sign(des,des,signatureInfo);

        //对文件求hash
        String s = MD5Util.md5HashCode32(des);

        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);

        //上链的数据:合同的hash
        ContractHashCall contractHashCall  = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result=contractHashCall.setContractHash(new BigInteger(id),LoanContract.getBank(),LoanContract.getEnterprise(),"质押贷款合同",s,String.valueOf(updateTime));
        String bn=result.get("bn");
        String th =result.get("th");
        String from=result.get("from");
        String to = result.get("to");
        String no = result.get("no");
        int j = contractHashDetailService.addContractHashDetail(new ContractHashDetail(String.valueOf(bn),th,from,to,ts,Integer.parseInt(no)));


        //区块链存证证书的生成
        Map<String,String> fillData = new HashMap<>();
        fillData.put("addr",from);
        fillData.put("time",String.valueOf(ts));
        fillData.put("type","hash值上链");
        fillData.put("number",no);
        fillData.put("bn",bn);
        fillData.put("th",th);
        fillData.put("to",to);
        fillData.put("hash",s);
        fillData.put("contarct","质押贷款合同");
        String src1= "F:\\mazhixiu\\certificate\\blockchainCertificate.pdf";
        String des1="F:\\mazhixiu\\certificate\\blockchainCertificate-"+LoanContract.getBank()+"-"+LoanContract.getEnterprise()+"Filled.pdf";
        //填充pdf
        PDFUtils.generatePDF(src1,des1,fillData);
        //盖章
//        String image1 = "F:\\mazhixiu\\seals\\blockchain.png";
//        String filled ="image1";
//
//        SignatureInfo signatureInfo1 = ItextUtil.setSignInfo1(pkPath,image1,filled,500);
//        ItextUtil.sign(src1,des1,signatureInfo1);



        LoanRecipt loanRecipt=loanReciptService.queryLoanReciptByID(LoanContract.getReciptId());
        loanRecipt.setStatus("贷款账单生效中，未偿还状态");
        loanReciptService.updateLoanRecipt(loanRecipt);

        //数据库仓单所有权转移
        WarehouseReceipt warehouseReceipt = warehouseReceiptService.queryWarehouseReceiptByID(LoanContract.getWaNo());
        warehouseReceipt.setHolder(LoanContract.getBank());
        warehouseReceipt.setStatus("仓单已抵押");
        Respository respository = respositoryService.queryRespositoryByID(warehouseReceipt.getReno());
        respository.setStatus("货物已抵押");
        respository.setUpdateTime(ts);
        warehouseReceipt.setUpdateTime(ts);
        warehouseReceiptService.updateWarehouseReceipt(warehouseReceipt);
        respositoryService.updateRespository(respository);

        //链上对应的仓单所有权转移
        WareHouseReciptCall warehouseReceiptCall = WareHouseReciptCall.getInstance(web3j,credentials);
        warehouseReceiptCall.updateWareHouseReciptStatusAndHolder(new BigInteger(String.valueOf(LoanContract.getWaNo())),"已抵押",LoanContract.getBank(),String.valueOf(ts));

        //修改数据库账户余额状态
        Account bank= accountService.findByName(LoanContract.getBank());
        Account enterprise = accountService.findByName(LoanContract.getEnterprise());
        int bankBal=Integer.parseInt(bank.getBal());
        int enterpriseBal = Integer.parseInt(enterprise.getBal());
//        new Double(v).intValue()
        int Money=Integer.parseInt(String.valueOf(new Double((loanRecipt.getAllMoney())).intValue()));
        bankBal=bankBal-Money;
        enterpriseBal = enterpriseBal+Money;
        bank.setBal(String.valueOf(bankBal));
        System.out.println("贷款金额："+Money);
        System.out.println("数据库银行余额："+bankBal);
        System.out.println("数据库企业余额："+enterpriseBal);
        enterprise.setBal(String.valueOf(enterpriseBal));
        accountService.updateAccount(bank);
        accountService.updateAccount(enterprise);

        //添加账单
        transferInfoService.addTransferInfo(new TransferInfo(bank.getName(),enterprise.getName(),loanRecipt.getAllMoney(),"借贷转账",ts,"借款"));

        //修改链上对应的账户余额状态。
        String bankAddr = bank.getAddr();
        String enterpriseAddr = enterprise.getAddr();
        //读取同态加密公钥
        RSAPublicKey pub = PaillierTest.read_paillier_Pub();
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
//        System.out.println("解密贷款金额："+PaillierCipher.decrypt(amount,pri));
//        System.out.println("解密贷款金额相反数："+PaillierCipher.decrypt(amount1,pri));
        //贷款企业账户余额加上贷款金额
//       String enterpriseNewBal = PaillierCipher.ciphertextAdd(digitalCurrencyCall.checkBal(enterpriseAddr),amount);
//        //银行账户余额加上贷款金额的相反数。
//        String bankNewBal = PaillierCipher.ciphertextAdd(digitalCurrencyCall.checkBal(bankAddr),amount1);
        digitalCurrencyCall.TokenSend(bankAddr,enterpriseAddr,amount,amount1,amount,"借贷转账",String.valueOf(ts));

        //解密链上银行账户的余额
        BigInteger payer=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(bankAddr),pri);
        //解密链上贷款企业账户的余额
        BigInteger payee=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(enterpriseAddr),pri);
        System.out.println(enterprise+"---链上余额------"+payee);
        System.out.println(bank+"----链上余额-----"+payer);
        return  i;
    }

/*    *//**
     * @author: mazhixiu
     * @description: 查询链上hash
     * @date: 2021/3/28 20:29
     * @param httpServletRequest
     * @return
     *//*
    @PostMapping("/queryLoanContractHash")
    public Map<String,String> queryAppointContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        //这里需要加上根据调用者，修改调用者的账户地址
        ContractHashCall ContractHashCall  = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result =ContractHashCall.getContractHash(new BigInteger(id));
        System.out.println(result);
        return  result;
    }*/

    @GetMapping("/queryAllLoanContract")
    public List<LoanContract> queryAllLoanContract() {
        return LoanContractService.queryAllLoanContract();

    }
    @GetMapping("/queryLoanContractByID/{id}")
    public LoanContract queryLoanContractByID(@PathVariable("id") int id){
        return LoanContractService.queryLoanContractByID(id);

    }
    @GetMapping("/queryLoanContractByStatus/{status}")
    public List<LoanContract> queryLoanContractByStatus(@PathVariable("status") String status){
        return LoanContractService.queryLoanContractByStatus(status);
    }
    @GetMapping("/queryLoanContractByEnterprise/{enterprise}")
    public List<LoanContract> queryLoanContractByEnterprise(@PathVariable("enterprise") String enterprise){
        return LoanContractService.queryLoanContractByEnterprise(enterprise);

    }
    @GetMapping("/queryLoanContractByBank/{bank}")
    public List<LoanContract> queryLoanContractByBank(@PathVariable("bank")  String bank){
        return LoanContractService.queryLoanContractByBank(bank);

    }
    @GetMapping("/queryLoanContractByBankAndEnterprise/{bank}/{enterprise}")
    public List<LoanContract> queryLoanContractByBankAndEnterprise(@PathVariable("bank")  String bank,@PathVariable("enterprise")String enterprise){
        return  LoanContractService.queryLoanContractByBankAndEnterprise(bank,enterprise);

    }
    @GetMapping("/queryLoanContractByBankAndStatus/{bank}/{status}")
    public List<LoanContract> queryLoanContractByBankAndStatus(@PathVariable("bank")  String bank, @PathVariable("status")String status){
        return  LoanContractService.queryLoanContractByBankAndStatus(bank,status);

    }
    @GetMapping("/queryLoanContractByEnterpriseAndStatus/{enterprise}/{status}")
    public List<LoanContract> queryLoanContractByEnterpriseAndStatus(@PathVariable("enterprise") String enterprise,@PathVariable("status") String status){
        return LoanContractService.queryLoanContractByEnterpriseAndStatus(enterprise, status);
    }
    @GetMapping("/queryLoanContractByBankAndEnterpriseAndStatus/{bank}/{enterprise}/{status}")
    public List<LoanContract> queryLoanContractByBankAndEnterpriseAndStatus(@PathVariable("bank")  String bank,@PathVariable("enterprise")String enterprise,@PathVariable("status")String status){
        return LoanContractService.queryLoanContractByBankAndEnterpriseAndStatus(bank, enterprise, status);
    }

    @PostMapping("/queryLoanContractHash")
    public  Map<String,String>  queryInventoryHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "质押贷款合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result =contractHashCall.getContractHash(new BigInteger(id),type);
        ContractHashDetail contractHashDetail = contractHashDetailService.queryContractHashDetailByNo(Integer.parseInt(result.get("no")));
        result.put("bn",contractHashDetail.getBlockNumber());
        result.put("th",contractHashDetail.getThHash());
        result.put("from",contractHashDetail.getFromAddr());
        result.put("to",contractHashDetail.getToAddr());
        System.out.println(result);
        return  result;
    }

    @PostMapping("/CompareLoanContractHash")
    public boolean CompareCollateralContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
//        String privateKey = httpServletRequest.getParameter("privateKey");
//        System.out.println(privateKey);
//        credentials = GenCredential.create(privateKey);
        String type = "质押贷款合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        String hash=contractHashCall.CompareContractHash(new BigInteger(id),type);
        System.out.println("链上合同文件hash："+hash);
        //计算贷款企业提交的文件数据的hash
        String type1=httpServletRequest.getParameter("type");
        String initiator=httpServletRequest.getParameter("initiator");
        String signer=httpServletRequest.getParameter("signer");
        //计算用户提交的合同文件的hash
        String s = MD5Util.md5HashCode32(filePath+"\\"+type1+"\\"+"loan-"+initiator+'-'+signer+"Filled.pdf");
        System.out.println("用户提交的合同文件hash："+s);
        return  hash.equals(s);
    }

}