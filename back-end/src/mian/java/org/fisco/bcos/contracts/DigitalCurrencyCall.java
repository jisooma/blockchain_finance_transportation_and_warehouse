package org.fisco.bcos.contracts;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/4/5 21:41
 * @Email:861359297@qq.com
 */
public class DigitalCurrencyCall {
    private static Web3j web3j;
    private static Credentials credentials;
    private static  DigitalCurrencyCall  DigitalCurrencyCall;
    private String contractAddress;
//    private String contractAddress= "0x4452cedad897f713714db2b672771edf18d50f6f";
    //DigitalCurrency合约
    private DigitalCurrency DigitalCurrency;

    public DigitalCurrencyCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.DigitalCurrency==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }



    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.DigitalCurrency=DigitalCurrency.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                    GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("--------DigitalCurrencyCall------合约地址----------------address is: " + this.DigitalCurrency.getContractAddress());
            if(this.DigitalCurrency!=null){
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
        if (this.DigitalCurrency==null){
            this.DigitalCurrency = DigitalCurrency.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("-------------------DigitalCurrencyCall----------address is: " + DigitalCurrency.getContractAddress());
            this.contractAddress = this.DigitalCurrency.getContractAddress();
            return  true;
        }
        return false;
    }
    //
    public  static   DigitalCurrencyCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        DigitalCurrencyCall.web3j = web3j;
        DigitalCurrencyCall.credentials=  credentials;
        //
        if(DigitalCurrencyCall == null){
            DigitalCurrencyCall = new DigitalCurrencyCall();
        }
        return DigitalCurrencyCall;
    }

//    public void MintCoin(String bank,String receiver,String amount) throws Exception {
//        // credentials.getAddress();部署合约的账户地址，作为银行的账户地址。
//        TransactionReceipt transactionReceipt= DigitalCurrency.tokenMint(bank,receiver,amount).send();
//        List<DigitalCurrency.MintEventResponse> list=DigitalCurrency.getMintEvents(transactionReceipt);
//        System.out.println(list);
//        //遍历list；
//        //做逻辑判断
//        //返回一个值
//        System.out.println();
//    }
    public void TokenSend(String payer,String payee,String amount1,String amount2,String amount,String type,String time) throws Exception {
        TransactionReceipt transactionReceipt=DigitalCurrency.tokenSend(payer,payee,amount1,amount2,type,time).send();
        List<DigitalCurrency.SentEventResponse> sentEventResponseList = DigitalCurrency.getSentEvents(transactionReceipt);
        System.out.println(sentEventResponseList);
    }
    public String checkBal(String addr) throws Exception {
        TransactionReceipt transactionReceipt=DigitalCurrency.checkBalance(addr).send();
        Tuple1<String> amount=DigitalCurrency.getCheckBalanceOutput(transactionReceipt);
//        TransactionReceipt transactionReceipt1=DigitalCurrency.findByAddr(addr).send();
//        System.out.println(transactionReceipt1);
//        Tuple7<BigInteger, String, String, String, String, BigInteger, String> res=DigitalCurrency.getFindByAddrOutput(transactionReceipt1);
//        System.out.println("tuple7"+res);
//        String bal = res.getValue4();
//        System.out.println(bal);
        return amount.getValue1();
    }

    public void TokenExchange(String bank,String payer,String amount1,String amount2,String type,String time) throws Exception {
        TransactionReceipt transactionReceipt=DigitalCurrency.tokenSend(bank,payer,amount1,amount2,type,time).send();
        List<DigitalCurrency.SentEventResponse> sentEventResponseList = DigitalCurrency.getSentEvents(transactionReceipt);
        System.out.println(sentEventResponseList);
    }

    public void createAccount(String type,String name,String bal,String addr) throws Exception {
        TransactionReceipt transactionReceipt=DigitalCurrency.createAccount(credentials.getAddress(),type,name,bal,addr).send();
//        System.out.println(transactionReceipt);
        System.out.println(name+"账户创建成功,账户地址："+addr);
    }

    public  List<BigInteger> getReciptByAddr(String addr) throws Exception {
       List<BigInteger> lists= DigitalCurrency.getMyTransferInfoIdList(addr).send();
       System.out.println(lists+"lsit--------------+++++++++++++++++");
       return lists;
    }

    public Map<String,String> getTranferInfoFrom(BigInteger no) throws Exception {
        Tuple6<BigInteger, String, String, String, String, String> res= DigitalCurrency.displayTransferInfo(no).send();
        Map<String,String> results= new HashMap<String, String>();
        results.put("traNo",String.valueOf(res.getValue1()));
        results.put("payer",res.getValue2());
        results.put("payee",res.getValue3());
        results.put("money",res.getValue4());
        results.put("type",res.getValue5());
        results.put("time",res.getValue6());
        return  results;
    }


}
