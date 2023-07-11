package org.fisco.bcos.service;


import org.fisco.bcos.entity.Account;
import org.fisco.bcos.entity.User1;

import java.util.List;

public interface AccountService {
    public int addAccount(Account account);
    public int updateAccount(Account account);
    public int deleteAccount( int id);
    public Account login( int type, String name, String password);
    public Account findByName(String name);
    public List<Account> queryByStatusAndType(int type, int status);
    public List<Account> queryByType(int type);
    public List<Account> queryAllAccount();
    public Account queryById(int id);
}
