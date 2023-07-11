package org.fisco.bcos.controller;

import org.fisco.bcos.chaindata.ContractHashDetail;

import org.fisco.bcos.contracts.ContractHashCall;
import org.fisco.bcos.entity.Collateral;
import org.fisco.bcos.service.CollateralService;
import org.fisco.bcos.service.ContractHashDetailService;
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
/**
 * @author mazhixiu
 * @date 2021/3/22 15:11
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/collateral")
public class CollateralController {
    @Autowired
    private CollateralService collateralService;
    @Autowired
    private ContractHashDetailService contractHashDetailService;

    @Value("${mzx.file.root.path}")
    private String filePath;
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;


    /**
     * @author: mazhixiu
     * @description: 融资企业申请担保
     * @date: 2021/3/23 9:46
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/addCollateral")
    public int addCollateral(HttpServletRequest httpServletRequest) throws Exception {
        String enterprise = httpServletRequest.getParameter("enterprise");
        String consigner = httpServletRequest.getParameter("consigner");
        String updateTime = httpServletRequest.getParameter("date1");
//        Date updateTime  =new Date();
        String date2= httpServletRequest.getParameter("date2");
        Timestamp ts1 = Timestamp.valueOf(updateTime);
        Timestamp ts2 = Timestamp.valueOf(date2);
        int i = collateralService.addCollateral(new Collateral(enterprise,consigner,"等待签署",ts1,ts2));

        //填写合同模板
        System.out.println(enterprise);
        System.out.println(consigner);
        System.out.println(ts1);
//        Collateral collateral = collateralService.queryCollateralByEnterpriseAndConsigner(enterprise,consigner);
        Collateral collateral = collateralService.queryCollateralByEnterpriseAndConsignerAndTime(enterprise,consigner,ts1);
        System.out.println(collateral);
        Map<String, String> data = new HashMap<String, String>();
        //key为pdf模板的form表单的名字，value为需要填充的值
        data.put("id", String.valueOf(collateral.getId()));
        data.put("enterprise", collateral.getEnterprise());
        data.put("consigner", collateral.getConsigner());
        data.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collateral.getUpdateTime()));
        data.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collateral.getUpdateTime()) );
        data.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collateral.getEndTime()));
        String src= "F:\\mazhixiu\\contracts\\Collateral\\CallteralContractTemplate.pdf";
        String des="F:\\mazhixiu\\contracts\\Collateral\\collateral-"+enterprise+"-"+consigner+"Filled.pdf";

        PDFUtils.generatePDF(src,des,data);

        //盖章
        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
        String image = "F:\\mazhixiu\\seals\\"+enterprise+".png";
        String filed = "text";
        //470//250
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,800);
        ItextUtil.sign(des,des,signatureInfo);

        return i;
    }
    @PostMapping("/disagreeCollateral")
    public int disagreeCollateral(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        Collateral collateral = collateralService.queryCollateralByID(Integer.parseInt(id));
        collateral.setStatus("同意签署");
        int i = collateralService.updateCollateral(collateral);
        return i;
    }

    /**
     * @author: mazhixiu
     * @description: 核心企业同意担保
     * @date: 2021/3/23 9:46
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/agreeCollateral")
    public int agreeCollateral(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        Collateral collateral = collateralService.queryCollateralByID(Integer.parseInt(id));
        String status= httpServletRequest.getParameter("status");
        collateral.setStatus("同意签署");
        Date updateTime  =new Date();
        Timestamp ts1 = new Timestamp(updateTime.getTime());
        collateral.setUpdateTime(ts1);

        int i = collateralService.updateCollateral(collateral);

        String des ="F:\\mazhixiu\\contracts\\Collateral\\collateral-"+collateral.getEnterprise()+"-"+collateral.getConsigner()+"Filled.pdf";

        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
//        String pkPath ="F:\\gl-file\\upload\\seal\\"+user1Service.findByName(appoint.getLogistics())+".pem";

        String image = "F:\\mazhixiu\\seals\\"+collateral.getConsigner()+".png";
        String filed = "text1";
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,600);
        ItextUtil.sign(des,des,signatureInfo);

        //对collateral-文件求hash值
        String s = MD5Util.md5HashCode32(des);
        System.out.println("CollateralHash------"+s);
        //上链的数据
        String privateKey = httpServletRequest.getParameter("privateKey");
        credentials = GenCredential.create(privateKey);
        ContractHashCall contractHashtCall  = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result=contractHashtCall.setContractHash(new BigInteger(id),collateral.getEnterprise(),collateral.getConsigner(),"担保合同",s,String.valueOf(updateTime));
        String bn=result.get("bn");
        String th =result.get("th");
        String from=result.get("from");
        String to = result.get("to");
        String no = result.get("no");
        System.out.println("from:"+from);
        System.out.println("to:"+to);
        System.out.println("no:"+no);
        int j = contractHashDetailService.addContractHashDetail(new ContractHashDetail(String.valueOf(bn),th,from,to,ts1,Integer.parseInt(no)));


        //区块链存证证书的生成
        Map<String,String> fillData = new HashMap<>();
        fillData.put("addr",from);
        fillData.put("time",String.valueOf(ts1));
        fillData.put("type","hash值上链");
        fillData.put("number",no);
        fillData.put("bn",bn);
        fillData.put("th",th);
        fillData.put("to",to);
        fillData.put("hash",s);
        fillData.put("contract","担保合同");
        String src1= "F:\\mazhixiu\\certificate\\blockchainCertificate.pdf";
        String des1="F:\\mazhixiu\\certificate\\blockchainCertificate-"+collateral.getEnterprise()+"-"+collateral.getConsigner()+"Filled.pdf";
        //填充pdf
        PDFUtils.generatePDF(src1,des1,fillData);
//        //盖章
//        String image1 = "F:\\mazhixiu\\seals\\blockchain.png";
//        String filled ="image";
//
//        SignatureInfo signatureInfo1 = ItextUtil.setSignInfo1(pkPath,image1,filled,500);
//        ItextUtil.sign(src1,des1,signatureInfo1);
        System.out.println(s);
        return  i;
    }
    /**
     * @author: mazhixiu
     * @description: 根据id查找collateral-
     * @date: 2021/3/23 9:45
     * @param id
     * @return
     */
    @GetMapping("/queryCollateralById/{id}")
    public List<Collateral> queryCollateralById(@PathVariable("id") int id){
        System.out.println("coll---"+id);
        List<Collateral> lists = new LinkedList<>();
        lists.add(collateralService.queryCollateralByID(id));
        return lists;
    }
    @GetMapping("/queryCollateralbyId/{id}")
    public Collateral queryCollateralbyId(@PathVariable("id") int id){
        return collateralService.queryCollateralByID(id);
    }
    /**
     * @author: mazhixiu
     * @description: 查找collateral-
     * @date: 2021/3/23 9:45
     * @param
     * @return
     */

    @PostMapping("/queryAllCollateral")
    public List<Collateral> queryAllCollateral(){
        return  collateralService.queryAllCollateral();
    }

    /**
     * @author: mazhixiu
     * @description: 查询我的collateral-
     * @date: 2021/3/23 9:48
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryMyCollateral")
    public List<Collateral> queryMyCollateral(HttpServletRequest httpServletRequest) {
        String  enterprise= httpServletRequest.getParameter("enterprise");
       List<Collateral> lists  =  collateralService.queryCollateralByEnterprise(enterprise);
       lists.addAll(collateralService.queryCollateralByConsigner(enterprise));
        return lists;
    }

    @PostMapping("/queryMyCollateralAndStatus")
    public List<Collateral> queryMyCollateralAndStatus(HttpServletRequest httpServletRequest) {
        String  enterprise= httpServletRequest.getParameter("enterprise");
        String  status= httpServletRequest.getParameter("status");
        List<Collateral> lists  =  collateralService.queryCollateralByEnterpriseAndStatus(enterprise,status);
        lists.addAll(collateralService.queryCollateralByConsignerAndStatus(enterprise,status));
        return lists;
    }
    /**
     * @author: mazhixiu
     * @description: 根据申请担保企业名查找collateral-
     * @date: 2021/3/23 9:45
     * @param enterprise
     * @return
     */
    @PostMapping("/queryCollateralbyEnterprise/{enterprise}")
    public List<Collateral> queryCollateralbyEnterprise(@PathVariable("enterprise") String enterprise){

        return  collateralService.queryCollateralByEnterprise(enterprise);
    }
    /**
     * @author: mazhixiu
     * @description: 根据担保企业名查找collateral-
     * @date: 2021/3/23 9:44
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryCollateralByConsigner")
    public List<Collateral> queryCollateralByConsigner(HttpServletRequest httpServletRequest) {
        String consigner = httpServletRequest.getParameter("consigner");
        return collateralService.queryCollateralByConsigner(consigner);
    }
    @PostMapping("/queryCollateralByEnterprise")
    public List<Collateral> queryCollateralbyEnterprise(HttpServletRequest httpServletRequest) {
        String  enterprise= httpServletRequest.getParameter("enterprise");
        return collateralService.queryCollateralByEnterprise(enterprise);
    }

    /**
     * @author: mazhixiu
     * @description:  根据企业名和合同状态查找collateral-
     * @date: 2021/3/23 9:43
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryCollateralByConsignerAndStatus/")
    public List<Collateral> queryCollateralByConsignerAndStatus(HttpServletRequest httpServletRequest) {
        String consigner = httpServletRequest.getParameter("consigner");
        String status = httpServletRequest.getParameter("status");
        return collateralService.queryCollateralByConsignerAndStatus(consigner,status);
    }


    /**
     * @author: mazhixiu
     * @description: 根据企业名和合同状态查找collateral-
     * @date: 2021/3/23 9:38
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryCollateralByEnterpriseAndStatus")
    public List<Collateral> queryCollateralbyEnterpriseAndStatus(HttpServletRequest httpServletRequest) {
        String  enterprise= httpServletRequest.getParameter("enterprise");
        String status = httpServletRequest.getParameter("status");
        return collateralService.queryCollateralByEnterpriseAndStatus(enterprise,status);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/29 9:53
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryCollateralContractHash")
    public Map<String,String> queryCollateralContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "担保合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result =contractHashCall.getContractHash(new BigInteger(id),type);
        ContractHashDetail contractHashDetail = contractHashDetailService.queryContractHashDetailByNo(Integer.parseInt(result.get("no")));
        result.put("bn",contractHashDetail.getBlockNumber());
        result.put("th",contractHashDetail.getThHash());
        result.put("from",contractHashDetail.getFromAddr());
        result.put("to",contractHashDetail.getToAddr());
//        result.put("no",String.valueOf(contractHashDetail.getNo()));
        System.out.println(result);
        return  result;
    }


    @PostMapping("/CompareCollateralContractHash")
    public boolean CompareCollateralContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "担保合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        String hash=contractHashCall.CompareContractHash(new BigInteger(id),type);
        System.out.println("链上合同文件hash："+hash);
        //计算贷款企业提交的文件数据的hash
        String type1=httpServletRequest.getParameter("type");
        String initiator=httpServletRequest.getParameter("initiator");
        String signer=httpServletRequest.getParameter("signer");
        //计算用户提交的合同文件的hash
        String s = MD5Util.md5HashCode32(filePath+"\\"+type1+"\\"+"collateral-"+initiator+'-'+signer+"Filled.pdf");
        System.out.println("用户提交的合同文件hash："+s);
        return  hash.equals(s);
    }
}
