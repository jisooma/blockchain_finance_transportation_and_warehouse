package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.User1;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface User1Mapper {
    public int addUser(User1 user);
    public int updateUser(User1 user);
    public int deleteUser(@Param("id") int id);
    public User1 login(@Param("type") int type, @Param("name") String name, @Param("password") String password);
    public User1 findByName(@Param("name") String name);
    public List<User1> queryByStatusAndType(@Param("type") int type,@Param("status") int status);
    public List<User1> queryByType(@Param("type") int type);
}
