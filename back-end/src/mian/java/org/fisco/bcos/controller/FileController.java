package org.fisco.bcos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.fisco.bcos.File.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mazhixiu
 * @date 2021/4/7 10:14
 * @Email:861359297@qq.com
 */
@CrossOrigin
@Api(value = "文件上传，下载相关功能")
@RestController
@RequestMapping("/api/v1")
public class FileController {
    // 设置固定的日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // 将 yml 中的自定义配置注入到这里
    @Value("${mzx.file.root.path}")
    private String filePath;
    // 日志打印
    private Logger log = LoggerFactory.getLogger("FileController");

    // 文件上传 （可以多文件上传）
    @PostMapping("/upload")
    public Result fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        // 得到格式化后的日期
        String format = sdf.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();

        // 时间 和 日期拼接
//        String newFileName = format + "_" + fileName;
        String[] strs = fileName.split("-");
        String type=strs[0];
        System.out.println("类型："+type);
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath+'\\'+type+'\\' + fileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            log.info("上传成功，当前上传的文件保存在 {}",filePath+"\\"+type+"\\" + fileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            return Result.succ("上传成功");
        } catch (IOException e) {
            log.error(e.toString());
        }
        // 待完成 —— 文件类型校验工作
        return Result.fail("上传错误");
    }

    //    文件下载功能
    @RequestMapping("/download")
    public  void fileDownLoads(HttpServletResponse res,HttpServletRequest request){
        String id = request.getParameter("id");
        String initiator=request.getParameter("initiator");
        String signer=request.getParameter("signer");
        String type = request.getParameter("type");
        System.out.println(initiator);
        System.out.println(signer);

        String fileName = type+'-'+initiator+'-'+signer+"Filled.pdf";
        System.out.println("要下载的文件："+fileName);
        try {
            //获取要下载的模板名称
//            String fileName = "仓储合同enteprise_2-logistics_1Filled1.pdf";
            //设置要下载的文件的名称
            res.setHeader("Content-disposition", "attachment;fileName=" + fileName);
            //通知客服文件的MIME类型
            res.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //获取文件的路径
            //String filePath = getClass().getResource("/template/" + fileName).getPath();
            //excel模板路径
            File cfgFile = ResourceUtils.getFile("F:\\mazhixiu\\contracts\\"+type+"\\"+fileName);
//            File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/仓储合同enteprise_2-logistics_1Filled1.pdf");
            FileInputStream input = new FileInputStream(cfgFile);
            //            FileInputStream input = new FileInputStream(new File("d://"+fileName));
            OutputStream out = res.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            res.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
            System.out.println(fileName+"下载完成");
        } catch (Exception ex) {
            System.out.println(fileName+"下载失败！");
        }
    }

    @RequestMapping("/preview")
    @ResponseBody
    public void devDoc(HttpServletRequest request, HttpServletResponse response, String storeName) throws Exception {
        String id = request.getParameter("id");
        String initiator=request.getParameter("initiator");
        String signer=request.getParameter("signer");
        String type = request.getParameter("type");
        System.out.println(initiator);
        System.out.println(signer);

        String fileName = type+'-'+initiator+'-'+signer+"Filled.pdf";


        request.setCharacterEncoding("UTF-8");
        String ctxPath = request.getSession().getServletContext().getRealPath("");
//        String downLoadPath = ctxPath + "/file/" + storeName;
        response.setContentType("application/pdf");
        FileInputStream in = new FileInputStream(new File("F:\\mazhixiu\\contracts\\"+type+"\\"+fileName));
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[512];
        while ((in.read(b))!=-1) {
            out.write(b);
        }
        out.flush();
        in.close();
        out.close();
    }

    @RequestMapping("/previewCertificate")
    @ResponseBody
    public void dev(HttpServletRequest request, HttpServletResponse response, String storeName) throws Exception {
//        String id = request.getParameter("id");
        String initiator=request.getParameter("initiator");
        String signer=request.getParameter("signer");
//        String type = request.getParameter("type");
        System.out.println(initiator);
        System.out.println(signer);

        String fileName ="blockchainCertificate"+'-'+initiator+'-'+signer+"Filled.pdf";


        request.setCharacterEncoding("UTF-8");
        String ctxPath = request.getSession().getServletContext().getRealPath("");
//        String downLoadPath = ctxPath + "/file/" + storeName;
        response.setContentType("application/pdf");
        FileInputStream in = new FileInputStream(new File("F:\\mazhixiu\\certificate\\"+fileName));
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[512];
        while ((in.read(b))!=-1) {
            out.write(b);
        }
        out.flush();
        in.close();
        out.close();
    }
}