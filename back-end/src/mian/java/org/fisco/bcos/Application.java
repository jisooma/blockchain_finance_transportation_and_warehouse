package org.fisco.bcos;

import org.fisco.bcos.utils.PaillierTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) throws IOException {
        //生成同态密钥对。
        PaillierTest.generate_paillier_keypair();
        SpringApplication.run(Application.class, args);
    }
}
