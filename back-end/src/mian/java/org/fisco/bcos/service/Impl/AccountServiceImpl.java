package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.Account;
import org.fisco.bcos.mapper.AccountMapper;
import org.fisco.bcos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int addAccount(Account account) {
        return accountMapper.addAccount(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    @Override
    public int deleteAccount(int id) {
        return accountMapper.deleteAccount(id);
    }

    @Override
    public Account login(int type, String name, String password) {
        return accountMapper.login(type,name,password);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/2/17 10:35
     * @param name
     * @return
     */
    @Override
    public Account findByName(String name) {

        return accountMapper.findByName(name);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/2/17 10:35
     * @param type
     * @param status
     * @return
     */
    @Override
    public List<Account> queryByStatusAndType(int type, int status) {
        return accountMapper.queryByStatusAndType(type,status);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/2/17 10:35
     * @param type
     * @return
     */
    @Override
    public List<Account> queryByType(int type) {
            return accountMapper.queryByType(type);
    }

    @Override
    public List<Account> queryAllAccount() {
        return accountMapper. queryAllAccount();
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/2/17 10:36
     * @param id
     * @return
     */
    @Override
    public Account queryById(int id) {
        return accountMapper.findById(id);
    }
}
