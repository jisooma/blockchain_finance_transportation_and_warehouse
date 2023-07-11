package org.fisco.bcos.controller;


import org.fisco.bcos.contracts.RespositoryCall;
import org.fisco.bcos.contracts.RespositoryHashCall;
import org.fisco.bcos.entity.Respository;
import org.fisco.bcos.entity.Respository;
import org.fisco.bcos.service.RespositoryService;
import org.fisco.bcos.utils.PaillierTest;
import org.fisco.bcos.utils.StringUtil;
import org.fisco.bcos.utils.paillier.PaillierCipher;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/10 11:12
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/respository")
public class RespositoryController {
    @Autowired
    private RespositoryService respositoryService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    @PostMapping("/addRespository")
    public int addRespository(HttpServletRequest httpServletRequest) throws Exception {
        String name = httpServletRequest.getParameter("name");
        String specification = httpServletRequest.getParameter("specification");
        String number = httpServletRequest.getParameter("number");
        String addr = httpServletRequest.getParameter("addr");
        String holder = httpServletRequest.getParameter("holder");
        String logistics = httpServletRequest.getParameter("logistics");
        String remark = httpServletRequest.getParameter("remark");

        Date updateTime = new Date();
        Timestamp ts = new Timestamp(updateTime.getTime());

//        (String name, String specification, int number, String addr, String holder, Timestamp updateTime, String status, String remark)
        Respository respository = new Respository(name,specification,Integer.parseInt(number),addr,holder,logistics,ts,"等待物流企业评估审核",remark);
        int i = respositoryService.addRespository(respository);

        return i;
    }

    @PostMapping("/agreeResopsitory")
    public int agreeRespository(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        Respository respository = respositoryService.queryRespositoryByID(Integer.parseInt(id));
        Date updateTime = new Date();
        Timestamp ts = new Timestamp(updateTime.getTime());
        respository.setUpdateTime(ts);
        respository.setStatus("同意入库");
        int i=respositoryService.updateRespository(respository);

        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);

        Respository respository1 = respositoryService.queryRespositoryByID(Integer.parseInt(id));
        RespositoryHashCall respositoryHashCall = RespositoryHashCall.getInstance(web3j,credentials);
        String hash= StringUtil.encode(respository1.toString());
        respositoryHashCall.setRespositoryHash(new BigInteger(id),hash,String.valueOf(ts));
        return  i;
    }

    @PostMapping("/updateRespository")
    public int updateRespositoryholder(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        String holder = httpServletRequest.getParameter("holder");
        Respository respository=respositoryService.queryRespositoryByID(Integer.parseInt(id));
        respository.setHolder(holder);
        Date updateTime = new Date();
        Timestamp ts = Timestamp.valueOf(String.valueOf(updateTime.getTime()));
        respository.setUpdateTime(ts);
        return respositoryService.updateRespository(respository);
    }

    @PostMapping("/updateRespository/{id}/{status}")
    public int updateRespositoryStatus(@PathVariable("id") String id,@PathVariable("status")String status){
        Respository respository=respositoryService.queryRespositoryByID(Integer.parseInt(id));
        respository.setStatus(status);
        Date updateTime = new Date();
        Timestamp ts = Timestamp.valueOf(String.valueOf(updateTime.getTime()));
        respository.setUpdateTime(ts);
        return respositoryService.updateRespository(respository);
    }
    @GetMapping("/queryRespositorybyId/{id}")
    public List<Respository> queryRespositorybyId(@PathVariable("id") int id){
        List<Respository> lists = new LinkedList<>();
        lists.add(respositoryService.queryRespositoryByID(id));
        return  lists;
    }
    @GetMapping("/queryRespositoryById/{id}")
    public Respository queryRespositoryById(@PathVariable("id") int id){
        return  respositoryService.queryRespositoryByID(id);
    }
   @GetMapping("/queryRespositoryByholder/{holder}")
    public List<Respository> queryRespositoryByholder(@PathVariable("holder") String holder){
        return  respositoryService.queryRespositoryByHolder(holder);
    }
    @GetMapping("/queryRespositoryByholder/{holder}/{status}")
    public List<Respository> queryRespositoryByholderAndStatus(@PathVariable("holder") String holder,@PathVariable("status") String status){
        return  respositoryService.queryRespositoryByHolderAndStatus(holder,status);
    }
    @GetMapping("/queryRespositoryByLogistics/{logistics}")
    public List<Respository> queryRespositoryByLogistics(@PathVariable("logistics") String logistics){
        return  respositoryService.queryRespositoryByLogistics(logistics);
    }
    @GetMapping("/queryRespositoryByLogistics/{logistics}/{status}")
    public List<Respository> queryRespositoryByLogisticsAndStatus(@PathVariable("logistics") String logistics,@PathVariable("status") String status){
        System.out.println(status);
        return  respositoryService.queryRespositoryByLogisticsAndStatus(logistics,status);
    }
    @GetMapping("/queryRespositoryBystatus/{status}")
    public List<Respository> queryRespositoryBystatus(@PathVariable("status")String status){
        return  respositoryService.queryRespositoryByStatus(status);
    }


    @PostMapping("/CompareRespositoryByHashFromChain")
    public Boolean CompareRespositoryByHashFromChain(HttpServletRequest httpServletRequest) throws Exception {
        String privateKey = httpServletRequest.getParameter("privateKey");
        System.out.println(privateKey);
        credentials = GenCredential.create(privateKey);
        String id = httpServletRequest.getParameter("id");
        RespositoryHashCall respositoryHashCall = RespositoryHashCall.getInstance(web3j,credentials);
        String hash = respositoryHashCall.CompareRespositoryHash(new BigInteger(String.valueOf(id)));
        Respository Respository = respositoryService.queryRespositoryByID(Integer.parseInt(id));
        String s= StringUtil.encode(Respository.toString());
        return hash.equals(s);
    }
}
