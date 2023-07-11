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
 * @date 2021/4/14 16:43
 * @Email:861359297@qq.com
 */
public class RespositoryHashCall {
    private static Web3j web3j;
    private static Credentials credentials;
    private static  RespositoryHashCall  RespositoryHashCall;
    private String contractAddress;
    // RespositoryHash合约
    private  RespositoryHash  RespositoryHash;

    public RespositoryHashCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.RespositoryHash==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.RespositoryHash= RespositoryHash.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-------------RespositoryHash合约地址----------------address is: " + this.RespositoryHash.getContractAddress());
            if(this.RespositoryHash!=null){
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
        if (this.RespositoryHash==null){
            this.RespositoryHash =  RespositoryHash.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("--------------------RespositoryHash--------address is: " +  RespositoryHash.getContractAddress());
            this.contractAddress = this.RespositoryHash.getContractAddress();
            return  true;
        }
        return false;
    }
    public  static  RespositoryHashCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        RespositoryHashCall.web3j = web3j;
        RespositoryHashCall.credentials=  credentials;
        //
        if(RespositoryHashCall == null){
            RespositoryHashCall = new RespositoryHashCall();
        }
        return RespositoryHashCall;
    }
    public void setRespositoryHash(BigInteger id,String hash,String updateTime) throws Exception {
        TransactionReceipt transactionReceipt= RespositoryHash.setRespositoryHash(id,hash,updateTime).send();
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

    public Map<String, String> getRespositoryHash(BigInteger id) throws Exception {
        System.out.println(id);
        Tuple4<BigInteger, BigInteger, String, String> res=RespositoryHash.getRespositoryHash(id).send();
        Map<String, String> result = new HashMap<String, String>();
        result.put("no",String.valueOf(res.getValue1()));
        result.put("id",String.valueOf(res.getValue2()));
        result.put("hash",res.getValue3());
        result.put("updateTime",res.getValue4());
        System.out.println(result);
        return result;
    }

    public String CompareRespositoryHash(BigInteger id) throws Exception {
        System.out.println(id);
        Tuple4<BigInteger, BigInteger, String, String> res=RespositoryHash.getRespositoryHash(id).send();
        System.out.println(res.getValue3());
        return res.getValue3();
    }
}
