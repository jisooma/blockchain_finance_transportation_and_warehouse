package org.fisco.bcos.contracts;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.Tuple;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoderFactory;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhixiu
 * @date 2021/3/12 9:16
 * @Email:861359297@qq.com
 */
public class LoanReciptCall {

    private static Web3j web3j;
    private static Credentials credentials;
    private static  LoanReciptCall  loanReciptcall;
    private String contractAddress=null;
    //loanRecipt合约
    private LoanRecipt loanRecipt;

    public LoanReciptCall() throws Exception {
        if(loadContract()){
            System.out.println("alreadly deployed, loaded successfully");
        }else {
            System.out.println("deploying");
        }
        if(this.loanRecipt==null){
            if (!this.deployContract())
                System.out.println("deploying error");
        }
    }
    //加载合约实例
    public boolean loadContract(){
        if(this.contractAddress!=null&&!this.contractAddress.isEmpty()){
            //加载合约
            this.loanRecipt=LoanRecipt.load(this.contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-------loanRecipt------合约地址----------------address is: " + this.loanRecipt.getContractAddress());
            if(this.loanRecipt!=null){
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
        if (this.loanRecipt==null){
            this.loanRecipt = LoanRecipt.deploy(web3j,credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            System.out.println("---------------loanRecipt------address is: " + loanRecipt.getContractAddress());
            this.contractAddress = this.loanRecipt.getContractAddress();
            return  true;
        }
        return false;
    }
    //
    public  static   LoanReciptCall  getInstance(Web3j web3j,Credentials credentials) throws Exception {
        LoanReciptCall.web3j = web3j;
        LoanReciptCall.credentials=  credentials;
        //
        if(loanReciptcall == null){
            loanReciptcall = new LoanReciptCall();
        }
        return loanReciptcall;
    }
    public boolean addLoanRecipt(BigInteger id,String enterprise,String bank,String allMoney,String updateTime,String dueTime) throws Exception {
        TransactionReceipt transactionReceipt=loanRecipt.createLoanRecipt(id,enterprise,bank,allMoney,updateTime,dueTime).send();
        System.out.println(transactionReceipt);
//        String input = transactionReceipt.getInput();
//        TransactionDecoder txDecodeSampleDecoder =  TransactionDecoderFactory.buildTransactionDecoder(AccountTest.ABI_ARRAY[0],AccountTest.BINARY_ARRAY[0]);
//
//        String jsonResult = txDecodeSampleDecoder.decodeInputReturnJson(input);
//        System.out.println(jsonResult);
        return true;
    }
    public Map<String, String> getLoanReciptByLoanId(BigInteger id) throws Exception {
        BigInteger no = loanRecipt.getLoanReciptByLoanId(id).send();
        System.out.println(no);
        Tuple8<BigInteger, String, String, String, String, String, String, String> res= loanRecipt.getLoanReciptByID(no).send();
        Map<String, String> recipt = new HashMap<String, String>();
        recipt.put("ID",String.valueOf(res.getValue1()));
        recipt.put("eneterprise",res.getValue2());
        recipt.put("bank",res.getValue3());
        recipt.put("allMoney",res.getValue4());
        recipt.put("reMoney",res.getValue5());
        recipt.put("status",res.getValue6());
        recipt.put("updateTime",res.getValue7());
        recipt.put("dueTime",res.getValue8());
        return recipt;
    }

    public boolean RepayLoanRecipt(BigInteger no,String money,String time) throws Exception {
        TransactionReceipt transactionReceipt=loanRecipt.RepayLoanRecipt(no,money,time).send();
        System.out.println(transactionReceipt);
        Tuple1<Boolean> output = loanRecipt.getRepayLoanReciptOutput(transactionReceipt);
        Boolean res = output.getValue1();
        System.out.println("--------"+res);
        String  res1= transactionReceipt.getOutput();
        System.out.println(res1);
        return res;
    }
}
