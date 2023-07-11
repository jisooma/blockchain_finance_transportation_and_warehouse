package org.fisco.bcos;

import org.fisco.bcos.entity.User1;
import org.fisco.bcos.mapper.User1Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class TestDB {
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private User1Mapper user1Mapper;
//    @Test
//    public void t() throws SQLException {
//        System.out.println(dataSource.getClass());
//        System.out.println(dataSource.getConnection());
//    }
//
//    @Test
//    public void t1(){
//        int type = 0;
//        String name = "银行";
//        String address = "dsdsdd";
//        String privateKey="1213123";
//        String password = "dsds";
//        String bal = "12121";
//        User1 user1 = new User1(0,name,password,bal,0,address,privateKey);
//        System.out.println(user1);
//        int i = user1Mapper.addUser(user1);
//        System.out.println(i);
//    }
//    @Test
//    public void t2(){
//        List<User1> users=user1Mapper.queryByType(0);
//        System.out.println(users);
//    }
}
