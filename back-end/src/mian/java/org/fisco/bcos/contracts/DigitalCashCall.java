package org.fisco.bcos.contracts;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.utils.paillier.PaillierCipher;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.request.Transaction;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/16 15:50
 * @Email:861359297@qq.com
 */
public class DigitalCashCall {

    private static Web3j web3j;
    private static Credentials credentials;
    private static  DigitalCashCall  digitalCashCall;
    private String contractAddress;
//    private String contractAddress= "0xc2318f3b16bba0864e0256f5266cef7a4aa47c38";
    //DigitalCash合约
    private DigitalCash digitalCash;

    public DigitalCashCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.digitalCash==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.digitalCash=DigitalCash.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("--------DigitalCashCall------合约地址----------------address is: " + this.digitalCash.getContractAddress());
            if(this.digitalCash!=null){
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
        if (this.digitalCash==null){
            this.digitalCash = DigitalCash.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("-------------------DigitalCashCall----------address is: " + digitalCash.getContractAddress());
            this.contractAddress = this.digitalCash.getContractAddress();
            return  true;
        }
        return false;
    }
    //
    public  static   DigitalCashCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        digitalCashCall.web3j = web3j;
        digitalCashCall.credentials=  credentials;
        //
        if(digitalCashCall == null){
            digitalCashCall = new DigitalCashCall();
        }
        return digitalCashCall;
    }

    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/3/16 10:37
     * @param bank
     * @param receiver
     * @param amount
     * @return
     */
    public void MintCoin(String bank,String receiver,String amount) throws Exception {
       // credentials.getAddress();部署合约的账户地址，作为银行的账户地址。
       TransactionReceipt transactionReceipt= digitalCash.tokenMint(bank,receiver,amount).send();
       List<DigitalCash.MintEventResponse> list=digitalCash.getMintEvents(transactionReceipt);
       System.out.println(list);
       //遍历list；
        //做逻辑判断
        //返回一个值
       System.out.println();
    }
    public void TokenSend(String payer,String payee,String amount1,String amount2,String amount) throws Exception {
        TransactionReceipt transactionReceipt=digitalCash.tokenSend(payer,payee,amount1,amount2,amount).send();
        List<DigitalCash.SentEventResponse> sentEventResponseList = digitalCash.getSentEvents(transactionReceipt);
        System.out.println(sentEventResponseList);
    }
    public String checkBal(String addr) throws Exception {
        TransactionReceipt transactionReceipt=digitalCash.checkBalance(addr).send();
        Tuple1<String> amount=digitalCash.getCheckBalanceOutput(transactionReceipt);
        String bal = amount.getValue1();
        return bal;
    }

    //注意把amount加上
    public void TokenExchange(String bank,String payer,String amount,String token) throws Exception {
        TransactionReceipt transactionReceipt=digitalCash.tokenExchange(bank,payer,token).send();
        List<DigitalCash.SentEventResponse> sentEventResponseList = digitalCash.getSentEvents(transactionReceipt);
        System.out.println(sentEventResponseList);
    }

}
