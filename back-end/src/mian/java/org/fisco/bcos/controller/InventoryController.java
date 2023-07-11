package org.fisco.bcos.controller;

import org.fisco.bcos.chaindata.ContractHashDetail;
import org.fisco.bcos.contracts.ContractHashCall;
import org.fisco.bcos.entity.*;
import org.fisco.bcos.service.ContractHashDetailService;
import org.fisco.bcos.service.InventoryService;
import org.fisco.bcos.service.RespositoryService;
import org.fisco.bcos.service.WarehouseReceiptService;
import org.fisco.bcos.utils.MD5Util;
import org.fisco.bcos.utils.fillpdf.PDFUtils;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/inventory")
public class InventoryController{
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private WarehouseReceiptService WarehouseReceiptService;
    @Autowired
    private RespositoryService respositoryService;
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
     * @description:  物流企业的业务：增加库存
     * @date: 2021/3/23 15:46
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/addInventory")
    public int addInventory(HttpServletRequest httpServletRequest) throws Exception {
        String enterprise = httpServletRequest.getParameter("enterprise");
        String logistics = httpServletRequest.getParameter("logistics");
        String no = httpServletRequest.getParameter("no");
        String money = httpServletRequest.getParameter("money");
        String updateTime  = httpServletRequest.getParameter("updateTime");
        String endTime  = httpServletRequest.getParameter("endTime");
        Timestamp ts = Timestamp.valueOf(updateTime);
        Timestamp ts2 = Timestamp.valueOf(endTime);
        int i = inventoryService.addInventory(new Inventory(enterprise,logistics,Integer.parseInt(no),money,"等待签署",ts,ts2));

        //填写合同模板
        Inventory inventory = inventoryService.queryInventoryByLogisticsAndEnterpriseAndTime(logistics,enterprise,ts);
        System.out.println(inventory);

        Map<String, String> data = new HashMap<String, String>();
        //key为pdf模板的form表单的名字，value为需要填充的值
        data.put("id", String.valueOf(inventory.getId()));
        data.put("enterprise", inventory.getEnterprise());
        data.put("logistics", inventory.getLogistics());
        data.put("reNo", String.valueOf(inventory.getWaNo()));
        data.put("Money", inventory.getReMoney());
        data.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inventory.getUpdateTime()));
        data.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inventory.getUpdateTime()) );
        data.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inventory.getEndTime()));

        //填写仓单内容
        //根据库存合同里面的仓单的reNo拿到仓单
        System.out.println(inventory.getWaNo());//仓单的reno
        WarehouseReceipt WarehouseReceipt = WarehouseReceiptService.queryWarehouseReceiptByID(inventory.getWaNo());
        //仓单
        System.out.println(WarehouseReceipt);
        System.out.println("库存合同id===="+inventory.getId());
        System.out.println("仓单id===="+inventory.getWaNo());
        //根据仓单拿到货物id
        System.out.println("====="+WarehouseReceipt.getReno());
        System.out.println(WarehouseReceipt);
        data.put("no", String.valueOf(WarehouseReceipt.getWaNo()));
        data.put("holder1", WarehouseReceipt.getHolder());
        data.put("logistics1", WarehouseReceipt.getLogistics());
        data.put("reno", String.valueOf(WarehouseReceipt.getReno()));
        data.put("address", WarehouseReceipt.getAddress());
        data.put("valMoney", WarehouseReceipt.getValMoney());
        data.put("status1", WarehouseReceipt.getStatus());
        data.put("updateTime1",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(WarehouseReceipt.getUpdateTime()));

        //填写货物信息

        //货物的id
        System.out.println("=====货物"+WarehouseReceipt.getReno());
        Respository respository = respositoryService.queryRespositoryByID(WarehouseReceipt.getReno());
        data.put("reNo2", String.valueOf(respository.getReNo()));
        data.put("holder2", respository.getHolder());
        data.put("logistics2", respository.getLogistics());
        data.put("name", respository.getName());
        data.put("specification", respository.getSpecification());
        data.put("number",String.valueOf( respository.getNumber()));
        data.put("addr", respository.getAddr());
        data.put("status2", respository.getStatus());
        data.put("remark", respository.getRemark());
        data.put("updateTime2",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(WarehouseReceipt.getUpdateTime()));

        String src= "F:\\mazhixiu\\contracts\\Inventory\\InventoryContractTemplate.pdf";
        String des="F:\\mazhixiu\\contracts\\Inventory\\Inventory-"+logistics+"-"+enterprise+"Filled.pdf";

        PDFUtils.generatePDF(src,des,data);

        //物流企业盖章
        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
        String image = "F:\\mazhixiu\\seals\\"+logistics+".png";
        String filed = "text";
        //470//250
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,800);
        ItextUtil.sign(des,des,signatureInfo);
        return  i;
    }

    @PostMapping("/disagreeInventory")
    public int disagreeInventory(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        Inventory inventory = inventoryService.queryInventoryByID(Integer.parseInt(id));
        inventory.setStatus("拒绝签署");
        int i = inventoryService.updateInventory(inventory);
        return i;
    }

    /**
     * @author: mazhixiu
     * @description: 存货企业签订Inventory
     * @date: 2021/3/23 15:46
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/agreeInventory")
    public int agreeInventory(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        Inventory inventory = inventoryService.queryInventoryByID(Integer.parseInt(id));
        Date updateTime = new Date();
        Timestamp ts = new Timestamp(updateTime.getTime());
        inventory.setStatus("同意签署");
        inventory.setUpdateTime(ts);
        String enterprise = inventory.getEnterprise();
        String logistics = inventory.getLogistics();
        int i = inventoryService.updateInventory(inventory);


        String des ="F:\\mazhixiu\\contracts\\Inventory\\Inventory-"+logistics+"-"+enterprise+"Filled.pdf";

        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";

        String image = "F:\\mazhixiu\\seals\\"+enterprise+".png";
        String filed = "text1";
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,600);
        ItextUtil.sign(des,des,signatureInfo);

        String s = MD5Util.md5HashCode32(des);
        //        System.out.println(s);
        System.out.println("InventoryHash---"+s);

        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        //上链的数据
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result=contractHashCall.setContractHash(new BigInteger(id),inventory.getLogistics(),inventory.getEnterprise(),"仓储合同",s,String.valueOf(updateTime));
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
        fillData.put("contract","仓储合同");
        String src1= "F:\\mazhixiu\\certificate\\blockchainCertificate.pdf";
        String des1="F:\\mazhixiu\\certificate\\blockchainCertificate-"+logistics+"-"+enterprise+"Filled.pdf";
        //填充pdf
        PDFUtils.generatePDF(src1,des1,fillData);
        //盖章
//        String image1 = "F:\\mazhixiu\\seals\\blockchain.png";
//        String filled ="image1";
//
//        SignatureInfo signatureInfo1 = ItextUtil.setSignInfo1(pkPath,image1,filled,500);
//        ItextUtil.sign(src1,des1,signatureInfo1);

        return  i;
    }
    /**
     * @author: mazhixiu
     * @description:
     * @date: 2021/3/23 15:46
     * @param id
     * @return
     */
    @GetMapping("/queryInventorybyId/{id}")
    public List<Inventory> queryInventorybyId(@PathVariable("id") int id){
        System.out.println("invnetoyr"+id);
        List<Inventory> lists = new LinkedList<>();
        lists.add(inventoryService.queryInventoryByID(id));
        return lists;
    }

    @GetMapping("/queryInventoryById/{id}")
    public Inventory queryInventoryById(@PathVariable("id") int id){
        return inventoryService.queryInventoryByID(id);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/23 15:46
     * @param enterprise
     * @return
     */
    @GetMapping("/queryInventoryByEnterprise/{enterprise}")
    public List<Inventory> queryInventoryByEnterprise(@PathVariable("enterprise") String enterprise){
        return  inventoryService.queryInventoryByEnterprise(enterprise);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/23 15:45
     * @param logistics
     * @return
     */
    @GetMapping("/queryInventoryByLogistics/{logistics}")
    public List<Inventory> queryInventoryByLogistics(@PathVariable("logistics") String logistics){
        return  inventoryService.queryInventoryByLogistics(logistics);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/23 15:45
     * @param logistics
     * @param status
     * @return
     */
    @GetMapping("/queryInventoryByLogisticsAndStatus/{logistics}/{status}")
    public List<Inventory> queryInventoryByLogisticsAndStatus(@PathVariable("logistics") String logistics,@PathVariable("status") String status){
        return  inventoryService.queryInventoryByLogisticsAndStatus(logistics, status);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/23 15:45
     * @param enterprise
     * @param status
     * @return
     */
    @GetMapping("/queryInventoryByEnterpriseAndStatus/{enterprise}/{status}")
    public List<Inventory> queryInventoryByEnterpriseAndStatus(@PathVariable("enterprise") String enterprise,@PathVariable("status") String status){
        return  inventoryService.queryInventoryByEnterpriseAndStatus(enterprise, status);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/23 15:45
     * @param status
     * @return
     */
    @GetMapping("/queryInventoryByStatus/{status}")
    public List<Inventory> queryInventoryByStatus(@PathVariable("status") String status){
        return  inventoryService.queryInventoryByLogistics(status);
    }



    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/23 15:45
     * @param enterprise
     * @param logistics
     * @param status
     * @return
     */
    @GetMapping("/queryInventoryByEnterpriseAndLogisticsAndStatus/{enterprise}/{logistics}/{status}")
    public List<Inventory> queryInventoryByEnterpriseAndLogisticsAndStatus(@PathVariable("enterprise") String enterprise,@PathVariable("logistics") String logistics,@PathVariable("status") String status){
        return  inventoryService.queryInventoryByLogisticsAndEnterpriseAndStatus(logistics, enterprise,status);
    }

    @PostMapping("/queryInventoryHash")
    public  Map<String,String>  queryInventoryHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String pri = httpServletRequest.getParameter("privateKey");
        String type="仓储合同" ;
        System.out.println(id);
        //这里需要加上根据调用者，修改调用者的账户地址
        credentials = GenCredential.create(pri);
        ContractHashCall contractHashCall= ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result =contractHashCall.getContractHash(new BigInteger(id),type);
        System.out.println("############:"+result);
        ContractHashDetail contractHashDetail = contractHashDetailService.queryContractHashDetailByNo(Integer.parseInt(result.get("no")));
        System.out.println();
        result.put("bn",contractHashDetail.getBlockNumber());
        result.put("th",contractHashDetail.getThHash());
        result.put("from",contractHashDetail.getFromAddr());
        result.put("to",contractHashDetail.getToAddr());
        System.out.println("&&&&&&&&&:"+result);
        return result;
    }
    @PostMapping("/CompareInventoryContractHash")
    public boolean CompareInventoryContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "仓储合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        String hash=contractHashCall.CompareContractHash(new BigInteger(id),type);
        System.out.println(hash);
        //计算贷款企业提交的文件数据的hash

        String type1=httpServletRequest.getParameter("type");
        String initiator=httpServletRequest.getParameter("initiator");
        String signer=httpServletRequest.getParameter("signer");
        //计算用户提交的合同文件的hash
        String s = MD5Util.md5HashCode32(filePath+"\\"+type1+"\\"+"inventory-"+initiator+'-'+signer+"Filled.pdf");
        System.out.println("用户提交的合同文件hash："+s);
        return  hash.equals(s);
    }
}
