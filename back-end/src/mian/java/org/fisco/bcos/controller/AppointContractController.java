package org.fisco.bcos.controller;


import org.fisco.bcos.chaindata.ContractHashDetail;
import org.fisco.bcos.contracts.ContractHashCall;
import org.fisco.bcos.entity.AppointContract;

import org.fisco.bcos.service.AppointContractService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author mazhixiu
 * @date 2021/3/1 15:11
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/AppointContract")
public class AppointContractController {
    @Autowired
    private AppointContractService appointContractService;
    @Autowired
    private ContractHashDetailService contractHashDetailService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;


    @Value("${mzx.file.root.path}")
    private String filePath;

    @PostMapping("/queryAll")
    public List<AppointContract> queryAllAppointContract(){
       return appointContractService.queryAllAppointContract();
    }

    //银行的业务
    @PostMapping("/addAppointContract")
    public int addAppointContract(HttpServletRequest httpServletRequest) throws Exception {
        String bank = httpServletRequest.getParameter("bank");
        String logistics = httpServletRequest.getParameter("logistics");
        String money =  httpServletRequest.getParameter("money");
        String updateTime  = httpServletRequest.getParameter("updateTime");
        String endTime  = httpServletRequest.getParameter("endTime");
        Timestamp ts = Timestamp.valueOf(updateTime);
        Timestamp ts2 = Timestamp.valueOf(endTime);
        int i = appointContractService.addAppointContract(new AppointContract(logistics,bank,money,"等待物流企业签署",ts,ts2));
        System.out.println(logistics);
        System.out.println(bank);

        //填充委托合同的模板
        AppointContract AppointContract = appointContractService.queryAppointContractByLogisticsAndBankAndTime(logistics,bank,ts);
        System.out.println(AppointContract+"----");
        Map<String, String> data = new HashMap<String, String>();
        //key为pdf模板的form表单的名字，value为需要填充的值
        data.put("id", String.valueOf(AppointContract.getId()));
        data.put("bank", AppointContract.getBank());
        data.put("logistics", AppointContract.getLogistics());
        data.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(AppointContract.getUpdateTime()));
        data.put("money", AppointContract.getMoney());
        data.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(AppointContract.getUpdateTime()) );
        data.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(AppointContract.getEndTime()));
        String src= "F:\\mazhixiu\\contracts\\Appoint\\AppointContractTemplate.pdf";
        String des="F:\\mazhixiu\\contracts\\Appoint\\appoint-"+bank+"-"+logistics+"Filled.pdf";

        PDFUtils.generatePDF(src,des,data);

        //银行盖章
        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
        String image = "F:\\mazhixiu\\seals\\"+AppointContract.getBank()+".png";
        String filed = "text";
        //470//250
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,800);
        ItextUtil.sign(des,des,signatureInfo);

        return  i;
    }

    @PostMapping("/queryAppointContract")
    public List<AppointContract> queryAppointContract(){
        return  appointContractService.queryAllAppointContract();
    }

    @PostMapping("/disagreeAppointContract")
    public int disagreeAppointContract(HttpServletRequest httpServletRequest) throws Exception {

        String id = httpServletRequest.getParameter("id");
//        String status= httpServletRequest.getParameter("status");
        AppointContract appointContract  = appointContractService.queryAppointContractByID(Integer.parseInt(id));
        appointContract.setStatus("拒绝签署");
        int i= appointContractService.updateAppointContract(appointContract);
        return i;
    }
    //物流业的业务
    @PostMapping("/agreeAppointContract")
    public int agreeAppointContract(HttpServletRequest httpServletRequest) throws Exception {

        String id = httpServletRequest.getParameter("id");
//        String status= httpServletRequest.getParameter("status");
        AppointContract AppointContract  = appointContractService.queryAppointContractByID(Integer.parseInt(id));
        Date updateTime = new Date();
        Timestamp ts = new Timestamp(updateTime.getTime());
        AppointContract.setStatus("同意签署");
        AppointContract.setUpdateTime(ts);
        int i = appointContractService.updateAppointContract(AppointContract);
        String bank = AppointContract.getBank();
        String logistics = AppointContract.getLogistics();

        //物流企业同意委托，签订合同
        //String path="F:\\gl-file\\upload\\seal\\质押贷款合同fill.pdf";
        String des ="F:\\mazhixiu\\contracts\\Appoint\\appoint-"+AppointContract.getBank()+"-"+AppointContract.getLogistics()+"Filled.pdf";

        String pkPath ="F:\\mazhixiu\\key\\tomatocc.p12";
//        String pkPath ="F:\\gl-file\\upload\\seal\\"+user1Service.findByName(AppointContract.getLogistics())+".pem";

        String image = "F:\\mazhixiu\\seals\\"+AppointContract.getLogistics()+".png";
        String filed = "text1";
        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,600);
        ItextUtil.sign(des,des,signatureInfo);

        //对文件求hash
        String s = MD5Util.md5HashCode32(des);
        //上链的数据:合同的hash
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        Map<String,String> result=contractHashCall.setContractHash(new BigInteger(id),AppointContract.getBank(),AppointContract.getLogistics(),"委托合同",s,String.valueOf(updateTime));
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
        fillData.put("contract","委托合同");
        String src1= "F:\\mazhixiu\\certificate\\blockchainCertificate.pdf";
        String des1="F:\\mazhixiu\\certificate\\blockchainCertificate-"+bank+"-"+logistics+"Filled.pdf";
        //填充pdf
        PDFUtils.generatePDF(src1,des1,fillData);
        //盖章
        String image1 = "F:\\mazhixiu\\seals\\blockchain.png";
        String filled ="image1";

//        SignatureInfo signatureInfo1 = ItextUtil.setSignInfo1(pkPath,image1,filled,500);
//        ItextUtil.sign(src1,des1,signatureInfo1);
        System.out.println(s);
        return  i;
    }

    //银行与物流企业均可以查询自己的appoint
    @PostMapping("/queryAppointContractByLogistics")
    public List<AppointContract> queryAppointContractByLogistics(HttpServletRequest httpServletRequest){
        String logistics = httpServletRequest.getParameter("logistics");
        return  appointContractService.queryAppointContractByLogistics(logistics);
    }

    @PostMapping("/queryAppointContractByLogisticsAndStatus")
    public List<AppointContract> queryAppointContractByLogisticsAndStatus(HttpServletRequest httpServletRequest){
        String logistics = httpServletRequest.getParameter("logistics");
        String status= httpServletRequest.getParameter("status");
        return  appointContractService.queryAppointContractByLogisticsAndStatus(logistics,status);
    }

    @PostMapping("/queryAppointContractByBank")
    public List<AppointContract> queryAppointContractByBank (HttpServletRequest httpServletRequest){
        String bank = httpServletRequest.getParameter("bank");
        List<AppointContract> AppointContracts = appointContractService.queryAppointContractByBank(bank);
        return  AppointContracts;
    }
    @PostMapping("/queryAppointContractByBankAndStatus")
    public List<AppointContract> queryAppointContractByBankAndStatus (HttpServletRequest httpServletRequest){
        String bank = httpServletRequest.getParameter("bank");
        String status= httpServletRequest.getParameter("status");
        System.out.println(bank);
        System.out.println(status);
        return  appointContractService.queryAppointContractByBankAndStatus(bank,status);
    }

    @PostMapping("/traceAppointContractRecord")
    public void TraceAppointContractRecord(){

    }

    /**
     * @author: mazhixiu
     * @description: 查询链上hash
     * @date: 2021/4/7 16:17
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/queryAppointContractHash")
    public Map<String,String> queryAppointContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        credentials = GenCredential.create(privateKey);
        String type = "委托合同";
        System.out.println(id);
        //这里需要加上根据调用者，修改调用者的账户地址

//        AgreementCall agreementCall  = AgreementCall.getInstance(web3j,credentials);
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
//        Map<String,String> result =agreementCall.getContractHash(new BigInteger(id));
        Map<String,String> result =contractHashCall.getContractHash(new BigInteger(id),type);

        ContractHashDetail contractHashDetail = contractHashDetailService.queryContractHashDetailByNo(Integer.parseInt(result.get("no")));
        result.put("bn",contractHashDetail.getBlockNumber());
        result.put("th",contractHashDetail.getThHash());
        result.put("from",contractHashDetail.getFromAddr());
        result.put("to",contractHashDetail.getToAddr());
        System.out.println(result);
        return  result;
    }
    @PostMapping("/CompareAppointContractHash")
    public boolean CompareInventoryContractHash(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String type = "委托合同";
        ContractHashCall contractHashCall = ContractHashCall.getInstance(web3j,credentials);
        System.out.println(credentials.getAddress()+"----------------");
        String hash=contractHashCall.CompareContractHash(new BigInteger(id),type);
        System.out.println(hash);
        //计算贷款企业提交的文件数据的hash

        String type1=httpServletRequest.getParameter("type");
        String initiator=httpServletRequest.getParameter("initiator");
        String signer=httpServletRequest.getParameter("signer");
        //计算用户提交的合同文件的hash
        String s = MD5Util.md5HashCode32(filePath+"\\"+type1+"\\"+"appoint-"+initiator+'-'+signer+"Filled.pdf");
        System.out.println("用户提交的合同文件hash："+s);
        return  hash.equals(s);
    }
}
