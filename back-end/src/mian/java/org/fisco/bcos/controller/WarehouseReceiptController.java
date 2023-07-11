package org.fisco.bcos.controller;

import org.fisco.bcos.contracts.WareHouseReciptCall;
import org.fisco.bcos.contracts.WarehouseReciptHashCall;
import org.fisco.bcos.entity.AppointContract;
import org.fisco.bcos.entity.Respository;
import org.fisco.bcos.entity.WarehouseReceipt;
import org.fisco.bcos.service.AppointContractService;
import org.fisco.bcos.service.RespositoryService;
import org.fisco.bcos.service.WarehouseReceiptService;
import org.fisco.bcos.utils.StringUtil;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/3/21 17:12
 * @Email:861359297@qq.com
 */

@CrossOrigin
@RestController
@RequestMapping("/warehouseReceipt")
public class WarehouseReceiptController {
    @Autowired
    private WarehouseReceiptService WarehouseReceiptService;
    @Autowired
    private RespositoryService respositoryService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:50
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/addWarehouseReceipt")
    public int addWarehouseReceipt(HttpServletRequest httpServletRequest) throws Exception {

        String holder = httpServletRequest.getParameter("holder");
        String logistics = httpServletRequest.getParameter("logistics");
        String reno= httpServletRequest.getParameter("reno");
        String valMoney = httpServletRequest.getParameter("valMoney");
        String status = httpServletRequest.getParameter("status");
        String address = httpServletRequest.getParameter("address");
        String remark = httpServletRequest.getParameter("remark");
        String updateTime = httpServletRequest.getParameter("updateTime");
        System.out.println(reno);
        Timestamp ts =Timestamp.valueOf(updateTime);

        WarehouseReceipt WarehouseReceipt = new WarehouseReceipt(holder,logistics,Integer.parseInt(reno),address,valMoney,"合法",ts,remark);
        System.out.println(WarehouseReceipt);
        int i = WarehouseReceiptService.addWarehouseReceipt(WarehouseReceipt);
        WarehouseReceipt warehouseReceipt=WarehouseReceiptService.queryWarehouseReceiptByLogisticsAndHolderAndTime(logistics,holder,ts);
        System.out.println(warehouseReceipt+"------");
        int id = warehouseReceipt.getWaNo();

        //仓单信息上链
        String privateKey = httpServletRequest.getParameter("privateKey");
        credentials = GenCredential.create(privateKey);
        WareHouseReciptCall warehouseReceiptCall = WareHouseReciptCall.getInstance(web3j,credentials);
        warehouseReceiptCall.addWareHouseRecipt(new BigInteger(String.valueOf(id)),holder,logistics,new BigInteger(String.valueOf(reno)),valMoney,address,status,String.valueOf(ts));

        //仓单hash值上链
        String s= StringUtil.encode(warehouseReceipt.toString());
        WarehouseReciptHashCall warehouseReceiptHashCall = WarehouseReciptHashCall.getInstance(web3j,credentials);
        warehouseReceiptHashCall.setWarehouseReciptHash(new BigInteger(String.valueOf(id)),s,String.valueOf(ts));
        return i;
    }

    @PostMapping("/updateWarehouseReceipt")
    public int updateWarehouseReceipt(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        String holder= httpServletRequest.getParameter("holder");
        Date updateTime = new Date();
        Timestamp ts = new Timestamp(updateTime.getTime());
        WarehouseReceipt warehouseReceipt  = WarehouseReceiptService.queryWarehouseReceiptByID(Integer.parseInt(id));
        if(warehouseReceipt.getHolder().equals(holder)){
            warehouseReceipt.setStatus("货物出库,仓单失效");
            warehouseReceipt.setUpdateTime(ts);
        }
        int j = WarehouseReceiptService.updateWarehouseReceipt(warehouseReceipt);
        Respository respository = respositoryService.queryRespositoryByID(warehouseReceipt.getReno());
        respository.setUpdateTime(ts);
        respository.setStatus("货物解除抵押,已出库");
        int i=respositoryService.updateRespository(respository);
        if(i==1&&j==1){
            return  1;
        }
        return  -1;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:50
     * @param holder
     * @return
     */
    @GetMapping("/queryWarehouseReceiptByHolder/{holder}")
    public List<WarehouseReceipt> queryWarehouseReceiptByHolder(@PathVariable("holder") String holder){
        return  WarehouseReceiptService.queryWarehouseReceiptByHolder(holder);
    }
    @GetMapping("/queryWarehouseReceiptByHolder/{holder}/{status}")
    public List<WarehouseReceipt> queryWarehouseReceiptByHolder(@PathVariable("holder") String holder,@PathVariable("status") String status){
        return  WarehouseReceiptService.queryWarehouseReceiptByHolderAndStatus(holder, status);
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:51
     * @param logistics
     * @return
     */
    @GetMapping("/queryWarehouseReceiptByLogistics/{logistics}")
    public List<WarehouseReceipt> queryRespositoryByLogistics(@PathVariable("logistics") String logistics){

        return  WarehouseReceiptService.queryWarehouseReceiptByLogistics(logistics);
    }
    @GetMapping("/queryWarehouseReceiptByLogisticsAndStatus/{logistics}/{status}")
    public List<WarehouseReceipt> queryRespositoryByLogistics(@PathVariable("logistics") String logistics,@PathVariable("status") String status){
        return  WarehouseReceiptService.queryWarehouseReceiptByLogisticsAndStatus(status,logistics);
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:51
     * @param logistics
     * @param holder
     * @return
     */
    @GetMapping("/queryWarehouseReceiptByLogisticsAndHolder/{logistics}/{holder}")
    public List<WarehouseReceipt> queryRespositoryByLogisticsAndHolder(@PathVariable("logistics") String logistics,@PathVariable("holder") String holder){
        return  WarehouseReceiptService.queryWarehouseReceiptByLogisticsAndHolder(logistics, holder);
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:51
     * @param status
     * @return
     */
    @GetMapping("/queryWarehouseReceiptByStatus/{status}")
    public List<WarehouseReceipt> queryWarehouseReceiptByStatus(@PathVariable("status")String status){

        return  WarehouseReceiptService.queryWarehouseReceiptByStatus(status);
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:51
     * @param id
     * @return
     */
    @GetMapping("/queryWarehouseReceiptById/{id}")
    public WarehouseReceipt queryWarehouseReceiptByID(@PathVariable("id")int id){

        return  WarehouseReceiptService.queryWarehouseReceiptByID(id);
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/24 16:51
     * @param id
     * @return
     */
    @GetMapping("/queryWarehouseReceiptbyId/{id}")
    public WarehouseReceipt queryWarehouseReceiptbyID(@PathVariable("id")int id){
        WarehouseReceipt warehouseReceipt = WarehouseReceiptService.queryWarehouseReceiptByID(id);
        return warehouseReceipt;
    }

    @PostMapping("/queryWarehouseReceiptByIDFromChain")
    public  Map<String, String> queryWarehouseReceiptByIDFromChain(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        WareHouseReciptCall warehouseReceiptCall = WareHouseReciptCall.getInstance(web3j,credentials);
        Map<String, String> results = warehouseReceiptCall.getWareHouseReciptByID(new BigInteger(String.valueOf(id)));
        return results;
    }

    @PostMapping("/CompareWarehouseReceiptByHashFromChain")
    public Boolean CompareWarehouseReceiptByHashFromChain(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        WarehouseReciptHashCall warehouseReceiptHashCall = WarehouseReciptHashCall.getInstance(web3j,credentials);
        String hash = warehouseReceiptHashCall.CompareWarehouseReciptHash(new BigInteger(String.valueOf(id)));
        WarehouseReceipt warehouseReceipt = WarehouseReceiptService.queryWarehouseReceiptByID(Integer.parseInt(id));
        String s= StringUtil.encode(warehouseReceipt.toString());
        return hash.equals(s);
    }
}
