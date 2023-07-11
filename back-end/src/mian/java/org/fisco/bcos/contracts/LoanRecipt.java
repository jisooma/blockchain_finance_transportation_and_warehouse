package org.fisco.bcos.contracts;

import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.*;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class LoanRecipt extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506114e7806100206000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630764ab3e1461007d5780634e6210fc146101235780638d8130c814610164578063a85238b5146102bb578063ce1dc7311461057e578063f7b1177314610616575b600080fd5b34801561008957600080fd5b506100a8600480360381019080803590602001909291905050506106e7565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100e85780820151818401526020810190506100cd565b50505050905090810190601f1680156101155780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561012f57600080fd5b5061014e6004803603810190808035906020019092919050505061079e565b6040518082815260200191505060405180910390f35b34801561017057600080fd5b506102a160048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610802565b604051808215151515815260200191505060405180910390f35b3480156102c757600080fd5b506102e660048036038101908080359060200190929190505050610d9d565b604051808981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b838110156103a0578082015181840152602081019050610385565b50505050905090810190601f1680156103cd5780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b838110156104065780820151818401526020810190506103eb565b50505050905090810190601f1680156104335780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b8381101561046c578082015181840152602081019050610451565b50505050905090810190601f1680156104995780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b838110156104d25780820151818401526020810190506104b7565b50505050905090810190601f1680156104ff5780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b8381101561053857808201518184015260208101905061051d565b50505050905090810190601f1680156105655780820380516001836020036101000a031916815260200191505b509d505050505050505050505050505060405180910390f35b34801561058a57600080fd5b506105bf600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611198565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156106025780820151818401526020810190506105e7565b505050509050019250505060405180910390f35b34801561062257600080fd5b506106cd60048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061122f565b604051808215151515815260200191505060405180910390f35b60606000808381526020019081526020016000206008018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107925780601f1061076757610100808354040283529160200191610792565b820191906000526020600020905b81548152906001019060200180831161077557829003601f168201915b50505050509050919050565b600080600090505b600280549050811115156107fb57826000808381526020019081526020016000206003015414156107ee576000808281526020019081526020016000206000015491506107fc565b80806001019150506107a6565b5b50919050565b6000806001600280549050019050600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819080600181540180825580915050906001820390600052602060002001600090919290919091505550600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190806001815401808255809150509060018203906000526020600020016000909192909190915055506002819080600181540180825580915050906001820390600052602060002001600090919290919091505550610120604051908101604052808281526020018873ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1681526020018981526020018681526020016040805190810160405280600181526020017f300000000000000000000000000000000000000000000000000000000000000081525081526020016040805190810160405280600981526020017fe8b4b7e6acbee4b8ad00000000000000000000000000000000000000000000008152508152602001858152602001848152506000808381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301556080820151816004019080519060200190610ab592919061130f565b5060a0820151816005019080519060200190610ad292919061130f565b5060c0820151816006019080519060200190610aef92919061130f565b5060e0820151816007019080519060200190610b0c92919061130f565b50610100820151816008019080519060200190610b2a92919061130f565b509050507f942174132816372ef9f7eaacdbe8cd6273434d255f92b853406dc3fce1dcf0298188888b898989604051808881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018060200180602001806020018060200180602001868103865289818151815260200191508051906020019080838360005b83811015610c16578082015181840152602081019050610bfb565b50505050905090810190601f168015610c435780820380516001836020036101000a031916815260200191505b50868103855260018152602001807f3000000000000000000000000000000000000000000000000000000000000000815250602001868103845260098152602001807fe8b4b7e6acbee4b8ad0000000000000000000000000000000000000000000000815250602001868103835288818151815260200191508051906020019080838360005b83811015610ce4578082015181840152602081019050610cc9565b50505050905090810190601f168015610d115780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b83811015610d4a578082015181840152602081019050610d2f565b50505050905090810190601f168015610d775780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390a160019150509695505050505050565b600080600060608060608060608897506000808a815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1696506000808a815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1695506000808a81526020019081526020016000206004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ec65780601f10610e9b57610100808354040283529160200191610ec6565b820191906000526020600020905b815481529060010190602001808311610ea957829003601f168201915b505050505094506000808a81526020019081526020016000206005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f765780601f10610f4b57610100808354040283529160200191610f76565b820191906000526020600020905b815481529060010190602001808311610f5957829003601f168201915b505050505093506000808a81526020019081526020016000206006018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561102657","80601f10610ffb57610100808354040283529160200191611026565b820191906000526020600020905b81548152906001019060200180831161100957829003601f168201915b505050505092506000808a81526020019081526020016000206007018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110d65780601f106110ab576101008083540402835291602001916110d6565b820191906000526020600020905b8154815290600101906020018083116110b957829003601f168201915b505050505091506000808a81526020019081526020016000206008018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111865780601f1061115b57610100808354040283529160200191611186565b820191906000526020600020905b81548152906001019060200180831161116957829003601f168201915b50505050509050919395975091939597565b6060600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561122357602002820191906000526020600020905b81548152602001906001019080831161120f575b50505050509050919050565b60006040805190810160405280600981526020017fe5b7b2e8bf98e5ae8c0000000000000000000000000000000000000000000000815250600080868152602001908152602001600020600601908051906020019061128f92919061138f565b5060008085815260200190815260200160002060040160008086815260200190815260200160002060050190805460018160011615610100020316600290046112d992919061140f565b5081600080868152602001908152602001600020600701908051906020019061130392919061138f565b50600190509392505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061135057805160ff191683800117855561137e565b8280016001018555821561137e579182015b8281111561137d578251825591602001919060010190611362565b5b50905061138b9190611496565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106113d057805160ff19168380011785556113fe565b828001600101855582156113fe579182015b828111156113fd5782518255916020019190600101906113e2565b5b50905061140b9190611496565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106114485780548555611485565b8280016001018555821561148557600052602060002091601f016020900482015b82811115611484578254825591600101919060010190611469565b5b5090506114929190611496565b5090565b6114b891905b808211156114b457600081600090555060010161149c565b5090565b905600a165627a7a72305820676a5013d016030b6d92fa295f1f058356ca714a69fefe5210e59fd48be0ac750029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"}],\"name\":\"getLoanDueTime\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"loanID\",\"type\":\"uint256\"}],\"name\":\"getLoanReciptByLoanId\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"loanID\",\"type\":\"uint256\"},{\"name\":\"enterprise\",\"type\":\"address\"},{\"name\":\"bank\",\"type\":\"address\"},{\"name\":\"allMoney\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"},{\"name\":\"dueTime\",\"type\":\"string\"}],\"name\":\"createLoanRecipt\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"}],\"name\":\"getLoanReciptByID\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"enterprise\",\"type\":\"address\"},{\"name\":\"bank\",\"type\":\"address\"},{\"name\":\"allMoney\",\"type\":\"string\"},{\"name\":\"reMoney\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"},{\"name\":\"dueTime\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"accNo\",\"type\":\"address\"}],\"name\":\"getMyLoanList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"money\",\"type\":\"string\"},{\"name\":\"time\",\"type\":\"string\"}],\"name\":\"RepayLoanRecipt\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"enterprise\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"bank\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"loanID\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"allMoney\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"reMoney\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"status\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"updateTime\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"dueTime\",\"type\":\"string\"}],\"name\":\"createLoanReciptEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETLOANDUETIME = "getLoanDueTime";

    public static final String FUNC_GETLOANRECIPTBYLOANID = "getLoanReciptByLoanId";

    public static final String FUNC_CREATELOANRECIPT = "createLoanRecipt";

    public static final String FUNC_GETLOANRECIPTBYID = "getLoanReciptByID";

    public static final String FUNC_GETMYLOANLIST = "getMyLoanList";

    public static final String FUNC_REPAYLOANRECIPT = "RepayLoanRecipt";

    public static final Event CREATELOANRECIPTEVENT_EVENT = new Event("createLoanReciptEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected LoanRecipt(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LoanRecipt(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LoanRecipt(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LoanRecipt(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<String> getLoanDueTime(BigInteger no) {
        final Function function = new Function(FUNC_GETLOANDUETIME, 
                Arrays.<Type>asList(new Uint256(no)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getLoanReciptByLoanId(BigInteger loanID) {
        final Function function = new Function(FUNC_GETLOANRECIPTBYLOANID,
                Arrays.<Type>asList(new Uint256(loanID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> createLoanRecipt(BigInteger loanID, String enterprise, String bank, String allMoney, String updateTime, String dueTime) {
        final Function function = new Function(
                FUNC_CREATELOANRECIPT,
                Arrays.<Type>asList(new Uint256(loanID),
                new Address(enterprise),
                new Address(bank),
                new Utf8String(allMoney),
                new Utf8String(updateTime),
                new Utf8String(dueTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createLoanRecipt(BigInteger loanID, String enterprise, String bank, String allMoney, String updateTime, String dueTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATELOANRECIPT,
                Arrays.<Type>asList(new Uint256(loanID),
                new Address(enterprise),
                new Address(bank),
                new Utf8String(allMoney),
                new Utf8String(updateTime),
                new Utf8String(dueTime)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createLoanReciptSeq(BigInteger loanID, String enterprise, String bank, String allMoney, String updateTime, String dueTime) {
        final Function function = new Function(
                FUNC_CREATELOANRECIPT,
                Arrays.<Type>asList(new Uint256(loanID),
                new Address(enterprise),
                new Address(bank),
                new Utf8String(allMoney),
                new Utf8String(updateTime),
                new Utf8String(dueTime)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple6<BigInteger, String, String, String, String, String> getCreateLoanReciptInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATELOANRECIPT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple6<BigInteger, String, String, String, String, String>(

                (BigInteger) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue()
                );
    }

    public Tuple1<Boolean> getCreateLoanReciptOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATELOANRECIPT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Tuple8<BigInteger, String, String, String, String, String, String, String>> getLoanReciptByID(BigInteger no) {
        final Function function = new Function(FUNC_GETLOANRECIPTBYID,
                Arrays.<Type>asList(new Uint256(no)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple8<BigInteger, String, String, String, String, String, String, String>>(
                new Callable<Tuple8<BigInteger, String, String, String, String, String, String, String>>() {
                    @Override
                    public Tuple8<BigInteger, String, String, String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, String, String, String, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue(),
                                (String) results.get(4).getValue(),
                                (String) results.get(5).getValue(),
                                (String) results.get(6).getValue(),
                                (String) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<List> getMyLoanList(String accNo) {
        final Function function = new Function(FUNC_GETMYLOANLIST,
                Arrays.<Type>asList(new Address(accNo)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> RepayLoanRecipt(BigInteger no, String money, String time) {
        final Function function = new Function(
                FUNC_REPAYLOANRECIPT,
                Arrays.<Type>asList(new Uint256(no),
                new Utf8String(money),
                new Utf8String(time)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void RepayLoanRecipt(BigInteger no, String money, String time, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REPAYLOANRECIPT,
                Arrays.<Type>asList(new Uint256(no),
                new Utf8String(money),
                new Utf8String(time)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String RepayLoanReciptSeq(BigInteger no, String money, String time) {
        final Function function = new Function(
                FUNC_REPAYLOANRECIPT,
                Arrays.<Type>asList(new Uint256(no),
                new Utf8String(money),
                new Utf8String(time)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, String, String> getRepayLoanReciptInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REPAYLOANRECIPT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getRepayLoanReciptOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REPAYLOANRECIPT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public List<CreateLoanReciptEventEventResponse> getCreateLoanReciptEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATELOANRECIPTEVENT_EVENT, transactionReceipt);
        ArrayList<CreateLoanReciptEventEventResponse> responses = new ArrayList<CreateLoanReciptEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateLoanReciptEventEventResponse typedResponse = new CreateLoanReciptEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.enterprise = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.bank = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.loanID = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.allMoney = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.reMoney = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.updateTime = (String) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.dueTime = (String) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registercreateLoanReciptEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CREATELOANRECIPTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registercreateLoanReciptEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CREATELOANRECIPTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static LoanRecipt load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LoanRecipt(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LoanRecipt load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LoanRecipt(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LoanRecipt load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new LoanRecipt(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LoanRecipt load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LoanRecipt(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<LoanRecipt> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LoanRecipt.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<LoanRecipt> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LoanRecipt.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<LoanRecipt> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(LoanRecipt.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<LoanRecipt> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LoanRecipt.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class CreateLoanReciptEventEventResponse {
        public Log log;

        public BigInteger id;

        public String enterprise;

        public String bank;

        public BigInteger loanID;

        public String allMoney;

        public String reMoney;

        public String status;

        public String updateTime;

        public String dueTime;
    }
}