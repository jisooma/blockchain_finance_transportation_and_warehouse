package org.fisco.bcos.controller;

import org.fisco.bcos.chaindata.ContractHashDetail;
import org.fisco.bcos.contracts.ContractHashCall;
import org.fisco.bcos.contracts.DigitalCurrencyCall;
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
import java.io.FileInputStream;
import java.io.InputStream;
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
 * @date 2021/3/30 11:20
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/ordercontract")
public class OrderContractController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderContractService orderContractService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private ContractHashDetailService contractHashDetailService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    @Value("${mzx.file.root.path}")
    private String filePath;


    /**
     * @author: mazhixiu
     * @description: order-
     * @date: 2021/3/30 14:17
     * @param request
     * @return
     */
    @PostMapping("/addOrderContract")
    public int addOrder(HttpServletRequest request) throws Exception {
        //订单编号
        String  id = request.getParameter("id");
        String  demands = request.getParameter("demands");
        String  addr= request.getParameter("addr");
        String  time = request.getParameter("time");
        String  expiration = request.getParameter("expiration");
        String  startTime= request.getParameter("startTime");
        String  endTime = request.getParameter("endTime");
        String  remark = request.getParameter("remark");
        Order order = orderService.queryOrderByUUId(id);
        String supplier = order.getSupplier();
        String buyer = order.getBuyer();
        String uuid = order.getUuid();
        OrderItem orderItem = orderItemService.queryOrderItemByUUID(uuid);

        OrderContract orderContract = new OrderContract(supplier,buyer,id,
                demands,addr, Timestamp.valueOf(time),expiration,
                Timestamp.valueOf(startTime),Timestamp.valueOf(endTime),"等待签署",remark);
        int i = orderContractService.addOrderContract(orderContract);

        //填充order-的模板
        OrderContract orderContract1 = orderContractService.queryOrderContractByUUId(id);
        Order order1 = orderService.queryOrderByUUId(id);
        OrderItem orderItem1 = orderItemService.queryOrderItemByUUID(id);

        System.out.println(orderContract1+"----");
        Map<String, String> data = new HashMap<String, String>();
        //key为pdf模板的form表单的名字，value为需要填充的值
        data.put("id", String.valueOf(orderContract1.getId()));
        data.put("supplier", orderContract1.getSupplier());
        data.put("buyer", orderContract1.getBuyer());
        data.put("uuid", orderContract1.getuuid());
        data.put("name", orderItem1.getName());
        data.put("specification", orderItem1.getSpecification());
        data.put("number", orderItem1.getNumber());
        data.put("price", orderItem1.getRemark());
        data.put("totalMoney", order1.getTotalMoney());
        data.put("demands",orderContract1.getDemands());
        data.put("addr", orderContract1.getAddr());
        data.put("expiration", orderContract1.getExpiration());
        data.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderContract1.getTime()) );
        data.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderContract1.getStartTime()) );
        data.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderContract1.getEndTime()));
        String src= "F:\\mazhixiu\\contracts\\Order\\OrderContractTemplate.pdf";
        String des="F:\\mazhixiu\\contracts\\Order\\Order-"+buyer+"-"+supplier+"Filled.pdf";

        PDFUtils.generatePDF(src,des,data);

        //银行盖章
        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
        String image = "F:\\mazhixiu\\seals\\"+orderContract1.getBuyer()+".png";
        String filed = "text";
        //470//250
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,800);
        ItextUtil.sign(des,des,signatureInfo);

        return  i;
    }

    @PostMapping("/disAgreeOrderContract")
    public boolean disAgreeOrderContract(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        OrderContract orderContract = orderContractService.queryOrderContractById(Integer.parseInt(id));
        orderContract.setStatus("拒绝签署");
        int i = orderContractService.updateOrderContract(orderContract);
        if(i==1){
            return true;
        }
        return  false;
    }
    /**
     * @author: mazhixiu
     * @description: order-
     * @date: 2021/3/30 14:38
     * @param request
     * @return
     */
    @PostMapping("/agreeOrderContract")
    public int agreeOrderContract(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        Date date = new Date();
        Timestamp updateTime = new Timestamp(date.getTime());
        System.out.println("123-----"+id);
        OrderContract orderContract = orderContractService.queryOrderContractById(Integer.parseInt(id));
        orderContract.setStatus("同意签署");
        int i = orderContractService.updateOrderContract(orderContract);
        Order order = orderService.queryOrderByUUId(orderContract.getuuid());
        String remark  = order.getRemark();
        Account supplier = accountService.findByName(orderContract.getSupplier());
        Account buyer = accountService.findByName(orderContract.getBuyer());


        //签订合同
        String des ="F:\\mazhixiu\\contracts\\Order\\Order-"+orderContract.getBuyer()+"-"+orderContract.getSupplier()+"filled.pdf";

        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";

        String image = "F:\\mazhixiu\\seals\\"+orderContract.getSupplier()+".png";
        String filed = "text1";
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,600);
        ItextUtil.sign(des,des,signatureInfo);

        //对文件求hash
        InputStream inputStream = new FileInputStream(des);
        String s = MD5Util.md5HashCode32(inputStream);

        String privateKey = request.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        //上链的数据:合同的hash
        ContractHashCall agreementCall  = ContractHashCall.getInstance(web3j,credentials);
        Map<String,String> result=agreementCall.setContractHash(new BigInteger(id),orderContract.getBuyer(),orderContract.getSupplier(),"采购合同",s,String.valueOf(updateTime));
//        System.out.println(s);
        String bn=result.get("bn");
        String th =result.get("th");
        String from=result.get("from");
        String to = result.get("to");
        String no = result.get("no");
        int j = contractHashDetailService.addContractHashDetail(new ContractHashDetail(String.valueOf(bn),th,from,to,updateTime,Integer.parseInt(no)));

        //区块链存证证书的生成
        Map<String,String> fillData = new HashMap<>();
        fillData.put("addr",from);
        fillData.put("time",String.valueOf(updateTime));
        fillData.put("type","hash值上链");
        fillData.put("number",no);
        fillData.put("bn",bn);
        fillData.put("th",th);
        fillData.put("to",to);
        fillData.put("hash",s);
        fillData.put("contract","采购合同");
        String src1= "F:\\mazhixiu\\certificate\\blockchainCertificate.pdf";
        String des1="F:\\mazhixiu\\certificate\\blockchainCertificate-"+orderContract.getBuyer()+"-"+orderContract.getSupplier()+"Filled.pdf";
        //填充pdf
        PDFUtils.generatePDF(src1,des1,fillData);
        //盖章
//        String image1 = "F:\\mazhixiu\\seals\\blockchain.png";
//        String filled ="image1";
//
//        SignatureInfo signatureInfo1 = ItextUtil.setSignInfo1(pkPath,image1,filled,500);
//        ItextUtil.sign(src1,des1,signatureInfo1);
//        System.out.println(s);

        //转账
        int supplierBal=Integer.parseInt(supplier.getBal());
        int buyerBal = Integer.parseInt(buyer.getBal());
//        new Double(v).intValue()
        int Money=Integer.parseInt(String.valueOf(new Double((order.getTotalMoney())).intValue()));
        supplierBal=supplierBal+Money;
        buyerBal = buyerBal-Money;
        supplier.setBal(String.valueOf(supplierBal));
        System.out.println("转账金额："+Money);
        System.out.println("数据库供应商余额："+supplierBal);
        System.out.println("数据库订购商余额："+buyerBal);
        buyer.setBal(String.valueOf(buyerBal));
        accountService.updateAccount(supplier);
        accountService.updateAccount(buyer);

        transferInfoService.addTransferInfo(new TransferInfo(buyer.getName(),supplier.getName(),order.getTotalMoney(),"普通转账",updateTime,remark));

        //修改链上对应的账户余额状态。
        String supplierAddr = supplier.getAddr();
        String buyerAddr = buyer.getAddr();
        //读取同态加密公钥
        RSAPublicKey pub = PaillierTest.read_paillier_Pub();
        //读取同态加密私钥
        RSAPrivateKey pri= PaillierTest.read_paillier_Pri();
        //实例化智能合约
        DigitalCurrencyCall digitalCurrencyCall = DigitalCurrencyCall.getInstance(web3j,credentials);

        //解密链上银行账户的余额
        BigInteger payer1= PaillierCipher.decrypt(digitalCurrencyCall.checkBal(supplierAddr),pri);
        //解密链上贷款企业账户的余额
        BigInteger payee1=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(buyerAddr),pri);
        System.out.println(buyer+"---链上余额------"+payee1);
        System.out.println(supplier+"----链上余额-----"+payer1);
        //需要对贷款金额求相反数，方便后续实现加密。
        int Money1 = -Money;
        //对贷款账单的余额进行同态加密
        String amount = PaillierCipher.encrypt(new BigInteger(String.valueOf(Money)),pub);
        String amount1 = PaillierCipher.encrypt(new BigInteger(String.valueOf(Money1)),pub);
//        System.out.println("解密贷款金额："+PaillierCipher.decrypt(amount,pri));
//        System.out.println("解密贷款金额相反数："+PaillierCipher.decrypt(amount1,pri));
//        //贷款企业账户余额加上贷款金额
//        String buyerNewBal = PaillierCipher.ciphertextAdd(digitalCurrencyCall.checkBal(buyerAddr),amount1);
//        //银行账户余额加上贷款金额的相反数。
//        String supplierNewBal = PaillierCipher.ciphertextAdd(digitalCurrencyCall.checkBal(supplierAddr),amount);
        digitalCurrencyCall.TokenSend(buyerAddr,supplierAddr,amount,amount1,amount,"普通转账",String.valueOf(updateTime));

        //解密链上银行账户的余额
        BigInteger payer=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(supplierAddr),pri);
        //解密链上贷款企业账户的余额
        BigInteger payee=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(buyerAddr),pri);
        System.out.println(buyer+"---链上余额------"+payee);
        System.out.println(supplier+"----链上余额-----"+payer);
        return i;
    }
    @GetMapping("/queryMyOrderContract/{name}")
    public List<OrderContract> queryOrderMyContract(@PathVariable("name") String name){
        List<OrderContract> orderContracts = orderContractService.queryOrderContractByBuyer(name);
        orderContracts.addAll(orderContractService.queryOrderContractBySupplier(name));
        return orderContracts;
    }
    @GetMapping("/queryOrderContractBySupplier/{supplier}")
    public List<OrderContract> queryOrderContractBySupplier(@PathVariable("supplier") String supplier){
        return orderContractService.queryOrderContractBySupplier(supplier);
    }
    @GetMapping("/queryOrderContractBySupplierAndStatus/{supplier}/{status}")
    public List<OrderContract> queryOrderContractBySupplierAndStatus(@PathVariable("supplier")String supplier, @PathVariable("status")String status){
        return orderContractService.queryOrderContractBySupplierAndStatus(supplier, status);
    }
    @GetMapping("/queryOrderContractByBuyer/{buyer}")
    public List<OrderContract> queryOrderContractByBuyer(@PathVariable("buyer") String buyer){
        return orderContractService.queryOrderContractByBuyer(buyer);
    }
    @GetMapping("/queryOrderContractByBuyerAndStatus/{buyer}/{status}")
    public List<OrderContract> queryOrderContractByBuyerAndStatus(@PathVariable("buyer")String buyer,@PathVariable("status") String status){
        return orderContractService.queryOrderContractByBuyerAndStatus(buyer, status);
    }
    @GetMapping("/queryAllOrderContract")
    public List<OrderContract> queryAllOrderContract(){
        return orderContractService.queryAllOrderContract();
    }
    @GetMapping("/queryOrderContractById/{id}")
    public OrderContract queryOrderContractById(@PathVariable("id") int id){
        return orderContractService.queryOrderContractById(id);
    }
    @GetMapping("/queryOrderContractByStatus/{status}")
    public OrderContract queryOrderContractByStatus(@PathVariable("status") String status){
        return orderContractService.queryOrderContractByStatus(status);
    }
    @GetMapping("/deleteOrderContract/{id}")
    public int deleteOrderContract(@PathVariable("id") int id){
        return orderContractService.deleteOrderContract(id);
    }

    @PostMapping("/queryLoanContractHash")
    public  Map<String,String>  queryInventoryHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "采购合同";
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
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "采购合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        String hash=contractHashCall.CompareContractHash(new BigInteger(id),type);
        System.out.println("链上合同文件hash："+hash);
        //计算贷款企业提交的文件数据的hash
        String type1=httpServletRequest.getParameter("type");
        String initiator=httpServletRequest.getParameter("initiator");
        String signer=httpServletRequest.getParameter("signer");
        //计算用户提交的合同文件的hash
        String s = MD5Util.md5HashCode32(filePath+"\\"+type1+"\\"+"order-"+initiator+'-'+signer+"Filled.pdf");
        System.out.println("用户提交的合同文件hash："+s);
        return  hash.equals(s);
    }
}
