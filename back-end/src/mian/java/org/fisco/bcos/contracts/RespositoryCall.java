package org.fisco.bcos.contracts;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/3/17 9:47
 * @Email:861359297@qq.com
 */
public class RespositoryCall {
    private static Web3j web3j;
    private static Credentials credentials;
    private static  RespositoryCall  respositoryCall;
    private String contractAddress=null;
    //Respository合约
    private Respository respository;

    
    public RespositoryCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.respository==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.respository=Respository.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-------------合约地址----------------address is: " + this.respository.getContractAddress());
            if(this.respository!=null){
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
        if (this.respository==null){
            this.respository = Respository.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("-----------------------------address is: " + respository.getContractAddress());
            this.contractAddress = this.respository.getContractAddress();
            return  true;
        }
        return false;
    }
    //
    public  static   RespositoryCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        respositoryCall.web3j = web3j;
        respositoryCall.credentials=  credentials;
        //
        if(respositoryCall == null){
            respositoryCall = new RespositoryCall();
        }
        return respositoryCall;
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/17 10:12
     * @param enterprise
     * @param transport
     * @param text
     * @param addr
     * @param valMoney
     * @param updateTime
     * @return
     */
    public void addRespository(String enterprise, String transport, String text, String addr, String valMoney, String updateTime) throws Exception {
        TransactionReceipt transactionReceipt=respository.createRepository(enterprise, transport, text, addr, valMoney, updateTime).send();
        System.out.println(transactionReceipt);
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/17 10:10
     * @param no
     * @param holder
     * @param updateTime
     * @return
     */
    public  boolean updateRespositoryHolder(BigInteger no, String holder, String updateTime) throws Exception {
        Boolean res = respository.updateRepositoryHolder(no,holder,updateTime).send();
        return  res;
    }
    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/17 10:11
     * @param no
     * @param holder
     * @param updateTime
     * @return
     */
    public  boolean updateRespositoryStatus(BigInteger no, String holder, String updateTime) throws Exception {
        Boolean res = respository.updateRepositoryHolder(no,holder,updateTime).send();
        return  res;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/17 10:11
     * @param no
     * @return
     */
    public void dispalyRespository(BigInteger no) throws Exception {
        Tuple8<BigInteger, String, String, String, String, String, String, String> res = respository.displayRepository(no).send();
        Map<String, String> result = new HashMap<String, String>();
        result.put("ID",String.valueOf(res.getValue1()));
        result.put("eneterprise",res.getValue2());
        result.put("logistics",res.getValue3());
        result.put("text",res.getValue4());
        result.put("addr",res.getValue5());
        result.put("valMoney",res.getValue6());
        result.put("status",res.getValue7());
        result.put("updateTime",res.getValue8());
    }


}
