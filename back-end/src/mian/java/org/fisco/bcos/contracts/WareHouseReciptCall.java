package org.fisco.bcos.contracts;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tuples.generated.Tuple9;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/4/10 17:27
 * @Email:861359297@qq.com
 */
public class WareHouseReciptCall {
    private static Web3j web3j;
    private static Credentials credentials;
    private static  WareHouseReciptCall  WareHouseReciptcall;
    private String contractAddress;
    //WareHouseRecipt合约
    private WareHouseRecipt WareHouseRecipt;

    public WareHouseReciptCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.WareHouseRecipt==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.WareHouseRecipt=WareHouseRecipt.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-------WareHouseRecipt------合约地址----------------address is: " + this.WareHouseRecipt.getContractAddress());
            if(this.WareHouseRecipt!=null){
                return true;
            }else{
                return false;
            }
        }
        return  false;
    }
    //部署合约
    public boolean deployContract() throws Exception {
        // deploy contract
        //合约为空
        if (this.WareHouseRecipt==null){
            this.WareHouseRecipt = WareHouseRecipt.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("---------------WareHouseRecipt------address is: " + WareHouseRecipt.getContractAddress());
            this.contractAddress = this.WareHouseRecipt.getContractAddress();
            return  true;
        }
        return false;
    }
    public  static   WareHouseReciptCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        WareHouseReciptCall.web3j = web3j;
        WareHouseReciptCall.credentials=  credentials;
        if(WareHouseReciptcall == null){
            WareHouseReciptcall = new WareHouseReciptCall();
        }
        return WareHouseReciptcall;
    }
    public boolean addWareHouseRecipt(BigInteger id, String holder, String logistics, BigInteger reno,String valMoney, String addr,String status,String updateTime) throws Exception {
        TransactionReceipt transactionReceipt=WareHouseRecipt.setWarehouseRecipt(id,holder,logistics,reno,valMoney,addr,status,updateTime).send();
        System.out.println(transactionReceipt);
        return true;
    }
    public Map<String, String> getWareHouseReciptByID(BigInteger id) throws Exception {
        Tuple9<BigInteger, BigInteger, String, String, BigInteger, String, String, String, String> res= WareHouseRecipt.getWarehouseRecipt(id).send();
        Map<String, String> recipt = new HashMap<String, String>();
        recipt.put("no",String.valueOf(res.getValue1()));
        recipt.put("id",String.valueOf(res.getValue2()));
        recipt.put("enterprise",res.getValue3());
        recipt.put("logistics",res.getValue4());
        recipt.put("reno",String.valueOf(res.getValue5()));
        recipt.put("valMoney",String.valueOf(res.getValue6()));
        recipt.put("addr",String.valueOf(res.getValue7()));
        recipt.put("status",res.getValue8());
        recipt.put("updateTime",res.getValue9());
        return recipt;
    }

    public boolean updateWareHouseReciptStatusAndHolder(BigInteger no,String status,String holder,String time) throws Exception {
        TransactionReceipt transactionReceipt=WareHouseRecipt.updateWarehouseReciptStatusAndHolder(no,holder,status,time).send();
        System.out.println(transactionReceipt);
        Tuple1<Boolean> output = WareHouseRecipt.getUpdateWarehouseReciptStatusAndHolderOutput(transactionReceipt);
        Boolean res = output.getValue1();
        System.out.println("--------"+res);
        String  res1= transactionReceipt.getOutput();
        System.out.println(res1);
        return res;
    }
}
