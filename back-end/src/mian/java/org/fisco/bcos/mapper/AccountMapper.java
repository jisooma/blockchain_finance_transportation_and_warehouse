package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AccountMapper {
    public int addAccount(Account account);
    public int updateAccount(Account account);
    public int deleteAccount(@Param("id") int id);
    public Account login(@Param("type") int type, @Param("name") String name, @Param("password") String password);
    public Account findByName(@Param("name") String name);
    public List<Account> queryByStatusAndType(@Param("type") int type, @Param("status") int status);
    public List<Account> queryByType(@Param("type") int type);
    public List<Account> queryAllAccount();
    public Account findById(@Param("id") int id);
}
