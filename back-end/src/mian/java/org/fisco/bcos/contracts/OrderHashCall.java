package org.fisco.bcos.contracts;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/4/14 19:46
 * @Email:861359297@qq.com
 */
public class OrderHashCall {
    private static Web3j web3j;
    private static Credentials credentials;
    private static  OrderHashCall  OrderHashCall;
    private String contractAddress;
    // OrderHash合约
    private  OrderHash  OrderHash;

    public OrderHashCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.OrderHash==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.OrderHash= OrderHash.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-------------OrderHash合约地址----------------address is: " + this.OrderHash.getContractAddress());
            if(this.OrderHash!=null){
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
        if (this.OrderHash==null){
            this.OrderHash =  OrderHash.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("--------------------OrderHash--------address is: " +  OrderHash.getContractAddress());
            this.contractAddress = this.OrderHash.getContractAddress();
            return  true;
        }
        return false;
    }
    public  static  OrderHashCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        OrderHashCall.web3j = web3j;
        OrderHashCall.credentials=  credentials;
        //
        if(OrderHashCall == null){
            OrderHashCall = new OrderHashCall();
        }
        return OrderHashCall;
    }
    public void setOrderHash(BigInteger id, String hash, String updateTime) throws Exception {
        TransactionReceipt transactionReceipt= OrderHash.setOrderHash(id,hash,updateTime).send();
        System.out.println(transactionReceipt.getOutput());
        System.out.println("##################");
        System.out.println(transactionReceipt.getInput());
        BigInteger bn=transactionReceipt.getBlockNumber();
        String th =transactionReceipt.getTransactionHash();
        String from=transactionReceipt.getFrom();
        //updateTime
        System.out.println();
        //可以将这些数据放在数据库里，便于查询。
    }

    public Map<String, String> getOrderHash(BigInteger id) throws Exception {
        System.out.println(id);
        Tuple4<BigInteger, BigInteger, String, String> res=OrderHash.getOrderHash(id).send();
        Map<String, String> result = new HashMap<String, String>();
        result.put("no",String.valueOf(res.getValue1()));
        result.put("id",String.valueOf(res.getValue2()));
        result.put("hash",res.getValue3());
        result.put("updateTime",res.getValue4());
        System.out.println(result);
        return result;
    }

    public String CompareOrderHash(BigInteger id) throws Exception {
        System.out.println(id);
        Tuple4<BigInteger, BigInteger, String, String> res=OrderHash.getOrderHash(id).send();
        System.out.println(res.getValue3());
        return res.getValue3();
    }
}
