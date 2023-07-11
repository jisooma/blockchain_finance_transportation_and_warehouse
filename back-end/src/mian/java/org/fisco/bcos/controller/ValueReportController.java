package org.fisco.bcos.controller;

import org.fisco.bcos.entity.Inventory;
import org.fisco.bcos.entity.ValueReport;
import org.fisco.bcos.service.ValueReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/valueReport")
public class ValueReportController {
    @Autowired
    private ValueReportService valueReportService;
    @PostMapping("/addvalueReport")
    public int addValueReport(HttpServletRequest httpServletRequest){
        String bank = httpServletRequest.getParameter("bank");
        String logistics = httpServletRequest.getParameter("logistics");
        String updateTime  = httpServletRequest.getParameter("updateTime");
        Timestamp ts = Timestamp.valueOf(updateTime);
        int i = valueReportService.addValueReport(new ValueReport());

        //填写合同模板
//        System.out.println(collateral);
//        Map<String, String> data = new HashMap<String, String>();
//        //key为pdf模板的form表单的名字，value为需要填充的值
//        data.put("id", String.valueOf(collateral.getId()));
//        data.put("enterprise", collateral.getEnterprise());
//        data.put("consigner", collateral.getConsigner());
//        data.put("updateTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collateral.getUpdateTime()));
//        data.put("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collateral.getUpdateTime()) );
//        data.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collateral.getEndTime()));
//        String src= "F:\\gl-file\\upload\\seal\\担保合同fill.pdf";
//        String des="F:\\gl-file\\upload\\seal\\担保合同"+enterprise+"-"+consigner+"Filled.pdf";
//
//        PDFUtils.generatePDF(src,des,data);
//
//        //银行盖章
//        String pkPath ="F:\\gl-file\\upload\\seal\\tomatocc.p12";
//        String image = "F:\\gl-file\\upload\\seal\\"+enterprise+".png";
//        String filed = "text";
//        //470//250
//        SignatureInfo signatureInfo = ItextUtil.setSignInfo1(pkPath,image,filed,800);
//        ItextUtil.sign(des,des,signatureInfo);
        return  i;
    }
    @GetMapping("/queryValueReportByReno/{reNo}")
    public ValueReport queryValueReportByReno(@PathVariable("reNo") int reNo){
        return valueReportService.queryValueReportByReno(reNo);
    }
    @GetMapping("/queryAllValueReport")
    public List<ValueReport> queryAllValueReport(){
        return  valueReportService.queryAllValueReport();
    }

    @GetMapping("/queryAllValueReport/{enterprise}")
    public List<ValueReport> queryAllValueReportByEnterPrise(@PathVariable("enterprise") String enterprise){
        return  valueReportService.queryAllValueReportByEnterPrise(enterprise);
    }
    @GetMapping("/queryValueReportByLogistics/{logistics}")
    public List<ValueReport> queryValueReportByLogistics(@PathVariable("logistics") String logistics){
        return  valueReportService.queryValueReportByLogistics(logistics);
    }

}
