package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.fisco.bcos.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
//    List<User> queryUserList();
//
//    List<User> queryUserListByType(String type);
//
//    User queryById(int id);
//
//    User login(String name,String password,String type);

    int addUser(User user);
//    int updateUser(User user);
//
//    int deleteUser(int id);
}
