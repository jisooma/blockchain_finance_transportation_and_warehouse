//package org.fisco.bcos.configure;
//
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.MultipartConfigElement;
//
///**
// * @author mazhixiu
// * @date 2021/4/1 15:10
// * @Email:861359297@qq.com
// */
//@Configuration
//public class MultipartConfig {
//        @Bean
//        public MultipartConfigElement multipartConfigElement() {
//            MultipartConfigFactory factory = new MultipartConfigFactory();
//            // 置文件大小限制 ,超出此大小页面会抛出异常信息
//            factory.setMaxFileSize("2MB"); //KB,MB
//            // 设置总上传数据总大小
//            factory.setMaxRequestSize("20MB");
//            // 设置文件临时文件夹路径
//            // factory.setLocation("E://test//");
//            // 如果文件大于这个值，将以文件的形式存储，如果小于这个值文件将存储在内存中，默认为0
//            // factory.setMaxRequestSize(0);
//            return factory.createMultipartConfig();
//    }
//}
