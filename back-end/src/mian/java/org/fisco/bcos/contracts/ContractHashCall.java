package org.fisco.bcos.contracts;

import org.fisco.bcos.chaindata.ContractHashDetail;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.service.ContractHashDetailService;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/3/19 9:41
 * @Email:861359297@qq.com
 */
public class ContractHashCall {
    private ContractHashDetailService contractHashDetailService;
    private static Web3j web3j;
    private static Credentials credentials;
    private static   ContractHashCall   ContractHashCall;
    private String contractAddress="0xab8ff93194249a7801732e63b478a9973cef43b8";
    // ContractHash合约
    private  ContractHash  contractHash;

    public  ContractHashCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.contractHash==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.contractHash= ContractHash.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-------------ContractHash合约地址----------------address is: " + this.contractHash.getContractAddress());
            if(this.contractHash!=null){
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
        if (this.contractHash==null){
            this.contractHash =  ContractHash.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("---------------------------ContractHash-address is: " +  contractHash.getContractAddress());
            this.contractAddress = this.contractHash.getContractAddress();
            return  true;
        }
        return false;
    }
    public  static   ContractHashCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        ContractHashCall.web3j = web3j;
        ContractHashCall.credentials=  credentials;
        //
        if(ContractHashCall == null){
            ContractHashCall = new ContractHashCall();
        }
        return ContractHashCall;
    }
    public Map<String,String> setContractHash(BigInteger id, String partyA, String partyB, String type, String hash, String updateTime) throws Exception {
        TransactionReceipt transactionReceipt= contractHash.setContarctHash(id,partyA,partyB,type,hash,updateTime).send();
        System.out.println("##################333333");
        Tuple7<BigInteger, BigInteger, String, String, String,String,String> res =contractHash.getContarctHash(id,type).send();
        BigInteger bn=transactionReceipt.getBlockNumber();
        String th =transactionReceipt.getTransactionHash();
        String from=transactionReceipt.getFrom();
        String to = transactionReceipt.getTo();
        System.out.println("链上存证编号："+res.getValue1());

        Map<String, String> result = new HashMap<String, String>();
        result.put("bn",String.valueOf(bn));
        result.put("th",th);
        result.put("from",from);
        result.put("to",to);
        result.put("no",String.valueOf(res.getValue1()));
        result.put("updateTime",res.getValue7());
        return  result;
    }

    public Map<String, String> getContractHash(BigInteger id,String type) throws Exception {
        System.out.println(id);
        Tuple7<BigInteger, BigInteger, String, String, String,String,String> res =contractHash.getContarctHash(id,type).send();
        Map<String, String> result = new HashMap<String, String>();
        result.put("no",String.valueOf(res.getValue1()));
        result.put("id",String.valueOf(res.getValue2()));
        result.put("partyA",res.getValue3());
        result.put("partyB",res.getValue4());
        result.put("type",res.getValue5());
        result.put("hash",res.getValue6());
        result.put("updateTime",res.getValue7());
        System.out.println(result);
        return result;
    }

    //返回合同对应的hash
    public String CompareContractHash(BigInteger id,String type) throws Exception {
        System.out.println(id);
        Tuple7<BigInteger, BigInteger,String, String, String, String,String> res =contractHash.getContarctHash(id,type).send();
        System.out.println(res.getValue6());
        return res.getValue6();
    }
}
