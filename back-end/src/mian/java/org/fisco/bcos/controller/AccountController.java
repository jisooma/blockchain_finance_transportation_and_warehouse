package org.fisco.bcos.controller;

import org.fisco.bcos.contracts.DigitalCashCall;
import org.fisco.bcos.contracts.DigitalCurrencyCall;
import org.fisco.bcos.entity.Account;
import org.fisco.bcos.entity.User;
import org.fisco.bcos.service.AccountService;
import org.fisco.bcos.utils.PaillierTest;
import org.fisco.bcos.utils.paillier.PaillierCipher;
//import org.fisco.bcos.utils.seal.SealUtils;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/2/29 5:19
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

//    private SealUtils sealUtils;


    @PostMapping("/register")
    public boolean register(HttpServletRequest request) throws Exception {
        String name =request.getParameter("name");
        String bal = request.getParameter("bal");
        String password = request.getParameter("password");
        String type  = request.getParameter("type");
        int type1;
        if(type.equals("金融机构")){
            type1=0;
        }else if(type.equals("物流仓储企业")){
            type1=1;
        }else if(type.equals("供应链企业")){
            type1=2;
        }else{
            type1=3;
        }

        Credentials credentials1 = GenCredential.create();
        //账户地址
        String address = credentials1.getAddress();
        //账户私钥
        String privateKey = credentials1.getEcKeyPair().getPrivateKey().toString(16);
        //账户公钥
        String publicKey = credentials1.getEcKeyPair().getPublicKey().toString(16);
        //账户已注册
        if(accountService.findByName(name)!=null){
            return false;
        }
        System.out.println(type1+"-"+name+"-"+bal);
        int i = accountService.addAccount(new Account(type1,name,password,bal,0,address,privateKey));

        //读取同态加密公钥
        RSAPublicKey pub=PaillierTest.read_paillier_Pub();
        //对bal加密
        String amount = PaillierCipher.encrypt(new BigInteger(bal),pub);
        DigitalCashCall digitalCashCall = DigitalCashCall.getInstance(web3j,credentials);
        digitalCashCall.MintCoin(credentials.getAddress(),address,amount);


        DigitalCurrencyCall digitalCurrencyCall = DigitalCurrencyCall.getInstance(web3j,credentials);
//        digitalCurrencyCall.MintCoin(credentials.getAddress(),address,amount);
        digitalCurrencyCall.createAccount(type,name,amount,address);

        //Test读取同态加密私钥，对其进行解密
        RSAPrivateKey pri = PaillierTest.read_paillier_Pri();
        System.out.println(digitalCurrencyCall.checkBal(address));
//        BigInteger payeeDe=PaillierCipher.decrypt(digitalCashCall.checkBal(address),pri);
        BigInteger payeeDe=PaillierCipher.decrypt(digitalCurrencyCall.checkBal(address),pri);
        System.out.println(address+"---Miwen------"+amount);
        System.out.println(address+"----mingwen-----"+payeeDe);

        //生成电子印章图片
//        SealUtils.OfficialSeal_1("F:\\mazhixiu\\sealTest\\"+name+".png", name);
        //生成数字证书
        //
        return  true;
    }

    @PostMapping("/agreeRegister")
    public boolean agreeRegister(HttpServletRequest request) throws Exception {
        String id =request.getParameter("id");
        String status = request.getParameter("status");

        if(Integer.parseInt(status)==1){
            Account account = accountService.queryById(Integer.parseInt(id));
            account.setStatus(Integer.parseInt(status));

            accountService.updateAccount(account);

            String address = account.getAddr();
            String bal = account.getBal();

            BigInteger a = new BigInteger(bal);
            //读取同态加密公钥
            RSAPublicKey pub=PaillierTest.read_paillier_Pub();
            //对bal加密
            String amount = PaillierCipher.encrypt(new BigInteger(bal),pub);
            DigitalCashCall digitalCashCall = DigitalCashCall.getInstance(web3j,credentials);
            digitalCashCall.MintCoin(credentials.getAddress(),address,amount);

//            //Test读取同态加密私钥，对其进行解密
//            RSAPrivateKey pri = PaillierTest.read_paillier_Pri();
//            BigInteger payeeDe=PaillierCipher.decrypt(digitalCashCall.checkBal(address),pri);
//            System.out.println(address+"---Miwen------"+amount);
//            System.out.println(address+"----mingwen-----"+payeeDe);
            return  true;
        }
        return false;
    }
    @PostMapping("/login")
    public Account login(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        String type  = httpServletRequest.getParameter("type");
        int type1;
        if(type.equals("金融机构")){
            type1=0;
        }else if(type.equals("物流仓储企业")){
            type1=1;
        }else if (type.equals("供应链企业")){
            type1=2;
        }else{
            type1=3;
        }
        Account account=accountService.login(type1,name,password);
        return account;
    }
    @PostMapping("/queryUserByName")
    public Account findByName(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        Account user =accountService.findByName(name);
        System.out.println(credentials.getAddress());
        return user;
    }

    @PostMapping("/queryUserListbyType")
    public List<Account> queryUserListByType(HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        List<Account> userlist= accountService.queryByType(Integer.valueOf(type));
        return userlist;
    }
    @PostMapping("/queryUserList")
    public List<Account>  queryUserList() throws Exception {
        List<Account> userlist= accountService.queryAllAccount();
        return userlist;
    }

    @PostMapping("/queryUserList1")
    public List<User> queryUserList1() throws Exception {
        List<Account> userlist= accountService.queryAllAccount();

        List<User> users = new LinkedList<>();
        for(Account account:userlist){
            User user = new User();
            System.out.println(account.getId());
            user.setName(account.getName());
            user.setAddr(account.getAddr());
            if(account.getType()==0){
                user.setType("金融机构");
            }else if(account.getType()==1){
                user.setType("物流仓储企业");

            }else if(account.getType()==2){
                user.setType("供应链企业");
            }else{
                user.setType("监管机构");
                continue;
            }
            if(account.getStatus()==0){
                user.setStatus("正常经营");
            }
            users.add(user);
        }
        return users;
    }
    @PostMapping("/queryUserListbyStatus")
    public List<Account> queryUserListByStatus(HttpServletRequest request) throws Exception {
        String status = request.getParameter("status");
        String type  = request.getParameter("type");
        List<Account> userlist= accountService.queryByStatusAndType(Integer.valueOf(type),Integer.valueOf(status));
        return userlist;
    }
}
