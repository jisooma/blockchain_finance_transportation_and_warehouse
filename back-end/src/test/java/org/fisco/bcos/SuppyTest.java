package org.fisco.bcos;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.temp.HelloWorld;
import org.fisco.bcos.temp.Test3;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.Transaction;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class SuppyTest extends BaseTest {

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
//    @Autowired
    private Test3 test3;
//    int bal = 12345;
    BigInteger  bal = new BigInteger("12345");
//    String addr =



    @Test
    public void deployAndCallHelloWorld() throws Exception {
        // deploy contract
        test3 = Test3.deploy(web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();

        System.out.println("-----------------------------address is: " + test3.getContractAddress());
        recordAssetAddr(test3.getContractAddress());

    }

    public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
        System.out.println("address："+address);
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        //文件输出流是用于将数据写入到文件中
        // 创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
        prop.load(contractResource.getInputStream());
        String aa = prop.getProperty("address");
        System.out.println(aa);
    }
    public String loadAssetAddr() throws Exception {
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        System.out.println(contractAddress);
        return contractAddress;
    }
    @Test
    public  void createAccount() throws Exception {
        String contractAddress = loadAssetAddr();
        test3 = Test3.load(contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            System.out.println("-----------------------------address is: " + test3.getContractAddress());

            TransactionReceipt transactionReceipt = test3.createAccount("金融机构", "工商银行", bal).send();

            System.out.println("金融机构");
            System.out.println(transactionReceipt);
        }

    @Test
    public  void createAppoint() throws Exception {
        String contractAddress = loadAssetAddr();
        test3 = Test3.load(contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        System.out.println("-----------------------------address is: " + test3.getContractAddress());

        TransactionReceipt transactionReceipt = test3.createAppoint("0x46ba8e72fed8c03947baded964737116218517de","2020-2-2").send();
        List<Test3.CreateAppointEventEventResponse> responses=test3.getCreateAppointEventEvents(transactionReceipt);

        System.out.println("金融机构");
        System.out.println(transactionReceipt);
        System.out.println(responses);


        //TransactionReceipt transactionReceipt1= test3.agreeAppoint(new BigInteger("1"),"2020-2-3",true).send();
    }
    @Test
    public  void agreeAppoint() throws Exception {
        String contractAddress = loadAssetAddr();
        test3 = Test3.load(contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        System.out.println("-----------------------------address is: " + test3.getContractAddress());

//        TransactionReceipt transactionReceipt = test3.createAppoint("0x46ba8e72fed8c03947baded964737116218517de","2020-2-2").send();
        TransactionReceipt transactionReceipt1= test3.agreeAppoint(new BigInteger("1"),"2020-2-3",true).send();
        List<Test3.AgreeEventEventResponse> responses=test3.getAgreeEventEvents(transactionReceipt1);
        System.out.println(transactionReceipt1);
        System.out.println(responses);
    }

    @Test
    public  void displayAppoint() throws Exception {
        String contractAddress = loadAssetAddr();
        test3 = Test3.load(contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        System.out.println("-----------------------------address is: " + test3.getContractAddress());
        Tuple5<BigInteger, String, String, String, String> res ;
//        test3.displayAppoint()
//        test3.getAgreeAppointInput()

//        res  = test3.displayAppoint(new BigInteger("1"));
//        System.out.println(res);
//        System.out.println(responses);
    }



    @Test
    public  void createValueReport() throws Exception {
        String contractAddress = loadAssetAddr();
        test3 = Test3.load(contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        System.out.println("-----------------------------address is: " + test3.getContractAddress());

        TransactionReceipt transactionReceipt = test3.createValueReport("0x46ba8e72fed8c03947baded964737116218517de",new BigInteger("1"),new BigInteger("123"),"2021-2-2").send();
//        =test3.getCreateValueReportOutput(transactionReceipt);

        System.out.println("金融机构");
        System.out.println(transactionReceipt);
//        System.out.println(responses);


        //TransactionReceipt transactionReceipt1= test3.agreeAppoint(new BigInteger("1"),"2020-2-3",true).send();
    }


    @Test
    public  void getValueReport() throws Exception {
        String contractAddress = loadAssetAddr();
        test3 = Test3.load(contractAddress,web3j,credentials,new StaticGasProvider(
                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        System.out.println("-----------------------------address is: " + test3.getContractAddress());

//        TransactionReceipt transactionReceipt = test3.getMyValueReport();


        System.out.println("金融机构");
//        System.out.println(transactionReceipt);
//        System.out.println(responses);


        //TransactionReceipt transactionReceipt1= test3.agreeAppoint(new BigInteger("1"),"2020-2-3",true).send();
    }




}


