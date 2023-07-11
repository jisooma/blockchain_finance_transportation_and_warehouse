package org.fisco.bcos.controller;

import org.fisco.bcos.contracts.DigitalCurrencyCall;
import org.fisco.bcos.entity.Account;
import org.fisco.bcos.entity.TransferInfo;
import org.fisco.bcos.service.AccountService;
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
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/4/15 19:12
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/supervise")
public class SuperviseController {
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private AccountService accountService;

    @PostMapping("/getTraNoFromAddr")
    public List<TransferInfo>  recipt(HttpServletRequest httpServletRequest) throws Exception {
        String name = httpServletRequest.getParameter("name");
        Account account =  accountService.findByName(name);
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        DigitalCurrencyCall digitalCurrencyCall = DigitalCurrencyCall.getInstance(web3j,credentials);
        List<BigInteger> list = digitalCurrencyCall.getReciptByAddr(account.getAddr());
        System.out.println(list);
        List<TransferInfo> transferInfos = new LinkedList<>();
        for(BigInteger s:list){
            TransferInfo transferInfo = new TransferInfo();
            Map<String,String> result= digitalCurrencyCall.getTranferInfoFrom(s);
            transferInfo.setId(Integer.parseInt(result.get("traNo")));
            transferInfo.setPayee(result.get("payee"));
            transferInfo.setPayer(result.get("payer"));
            transferInfo.setMoney(result.get("money"));
            transferInfo.setType(result.get("type"));
            transferInfo.setUpdateTime(Timestamp.valueOf(result.get("time")));
            transferInfos.add(transferInfo);
        }
        return transferInfos;
    }




}
