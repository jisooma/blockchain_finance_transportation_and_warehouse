package org.fisco.bcos.temp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tuples.generated.Tuple5;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

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
public class Account extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506117fc806100206000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302822dca146100885780633b61556b146102795780635f515226146104805780638e9580851461053c578063c26ea696146105d4578063d1f8dbe714610615578063d5f97eec14610704575b600080fd5b34801561009457600080fd5b506100b360048036038101908080359060200190929190505050610819565b6040518086815260200180602001806020018060200180602001858103855289818151815260200191508051906020019080838360005b838110156101055780820151818401526020810190506100ea565b50505050905090810190601f1680156101325780820380516001836020036101000a031916815260200191505b50858103845288818151815260200191508051906020019080838360005b8381101561016b578082015181840152602081019050610150565b50505050905090810190601f1680156101985780820380516001836020036101000a031916815260200191505b50858103835287818151815260200191508051906020019080838360005b838110156101d15780820151818401526020810190506101b6565b50505050905090810190601f1680156101fe5780820380516001836020036101000a031916815260200191505b50858103825286818151815260200191508051906020019080838360005b8381101561023757808201518184015260208101905061021c565b50505050905090810190601f1680156102645780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b34801561028557600080fd5b506102ba600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610bf1565b6040518086815260200180602001806020018060200180602001858103855289818151815260200191508051906020019080838360005b8381101561030c5780820151818401526020810190506102f1565b50505050905090810190601f1680156103395780820380516001836020036101000a031916815260200191505b50858103845288818151815260200191508051906020019080838360005b83811015610372578082015181840152602081019050610357565b50505050905090810190601f16801561039f5780820380516001836020036101000a031916815260200191505b50858103835287818151815260200191508051906020019080838360005b838110156103d85780820151818401526020810190506103bd565b50505050905090810190601f1680156104055780820380516001836020036101000a031916815260200191505b50858103825286818151815260200191508051906020019080838360005b8381101561043e578082015181840152602081019050610423565b50505050905090810190601f16801561046b5780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b34801561048c57600080fd5b506104c1600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610fb6565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156105015780820151818401526020810190506104e6565b50505050905090810190601f16801561052e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561054857600080fd5b5061057d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611099565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156105c05780820151818401526020810190506105a5565b505050509050019250505060405180910390f35b3480156105e057600080fd5b506105ff60048036038101908080359060200190929190505050611130565b6040518082815260200191505060405180910390f35b34801561062157600080fd5b50610702600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611153565b005b34801561071057600080fd5b50610817600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506113a1565b005b60006060806060806000806000806000600160008c8152602001908152602001600020600001549450600080600160008e815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002019350600080600160008e815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002019250600160008c81526020019081526020016000206003019150600160008c815260200190815260200160002060040190508484848484838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109fe5780601f106109d3576101008083540402835291602001916109fe565b820191906000526020600020905b8154815290600101906020018083116109e157829003601f168201915b50505050509350828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a9a5780601f10610a6f57610100808354040283529160200191610a9a565b820191906000526020600020905b815481529060010190602001808311610a7d57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b365780601f10610b0b57610100808354040283529160200191610b36565b820191906000526020600020905b815481529060010190602001808311610b1957829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bd25780601f10610ba757610100808354040283529160200191610bd2565b820191906000526020600020905b815481529060010190602001808311610bb557829003601f168201915b5050505050905099509950995099509950505050505091939590929450565b60006060806060806000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000015494506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d125780601f10610ce757610100808354040283529160200191610d12565b820191906000526020600020905b815481529060010190602001808311610cf557829003601f168201915b505050505093506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610dee5780601f10610dc357610100808354040283529160200191610dee565b820191906000526020600020905b815481529060010190602001808311610dd157829003601f168201915b505050505092506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610eca5780601f10610e9f57610100808354040283529160200191610eca565b820191906000526020600020905b815481529060010190602001808311610ead57829003601f168201915b505050505091506000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610fa65780601f10610f7b57610100808354040283529160200191610fa6565b820191906000526020600020905b815481529060010190602001808311610f8957829003601f168201915b5050505050905091939590929450565b60606000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffff","ffffffffffffffff1681526020019081526020016000206003018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561108d5780601f106110625761010080835404028352916020019161108d565b820191906000526020600020905b81548152906001019060200180831161107057829003601f168201915b50505050509050919050565b6060600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561112457602002820191906000526020600020905b815481526020019060010190808311611110575b50505050509050919050565b600060038281548110151561114157fe5b90600052602060002001549050919050565b60006001600480549050019050600260008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819080600181540180825580915050906001820390600052602060002001600090919290919091505550600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819080600181540180825580915050906001820390600052602060002001600090919290919091505550600481908060018154018082558091505090600182039060005260206000200160009091929091909150555060a0604051908101604052808281526020018673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff16815260200184815260200183815250600160008381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301908051906020019061137992919061172b565b50608082015181600401908051906020019061139692919061172b565b509050505050505050565b6000600160038054905001905060405160200180807fe6ada3e5b8b8e7bb8fe890a50000000000000000000000000000000000000000815250600c0190506040516020818303038152906040526040518082805190602001908083835b60208310151561142357805182526020820191506020810190506020830392506113fe565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160405160200180828054600181600116156101000203166002900480156114f35780601f106114d15761010080835404028352918201916114f3565b820191906000526020600020905b8154815290600101906020018083116114df575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561153c5780518252602082019150602081019050602083039250611517565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916141561157857611724565b60c0604051908101604052808281526020018681526020018581526020018481526020016040805190810160405280600c81526020017fe6ada3e5b8b8e7bb8fe890a5000000000000000000000000000000000000000081525081526020018373ffffffffffffffffffffffffffffffffffffffff168152506000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000155602082015181600101908051906020019061165592919061172b565b50604082015181600201908051906020019061167292919061172b565b50606082015181600301908051906020019061168f92919061172b565b5060808201518160040190805190602001906116ac92919061172b565b5060a08201518160050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505060038190806001815401808255809150509060018203906000526020600020016000909192909190915055505b5050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061176c57805160ff191683800117855561179a565b8280016001018555821561179a579182015b8281111561179957825182559160200191906001019061177e565b5b5090506117a791906117ab565b5090565b6117cd91905b808211156117c95760008160009055506001016117b1565b5090565b905600a165627a7a72305820b16972399fed88b01fed71eb0fec50105903d4cc372d199562a767fa49fdb4ff0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"}],\"name\":\"displayTransferInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"pub\",\"type\":\"address\"}],\"name\":\"displayAccount\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"accType\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"bal\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"accNo\",\"type\":\"address\"}],\"name\":\"checkBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"accNo\",\"type\":\"address\"}],\"name\":\"getmyTransferInfoIdList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"}],\"name\":\"displayAccountList\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"payer\",\"type\":\"address\"},{\"name\":\"payee\",\"type\":\"address\"},{\"name\":\"money\",\"type\":\"string\"},{\"name\":\"time\",\"type\":\"string\"}],\"name\":\"createTransfer\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accType\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"bal\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"createAccount\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"accType\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"bal\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"status\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"createAccountEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_DISPLAYTRANSFERINFO = "displayTransferInfo";

    public static final String FUNC_DISPLAYACCOUNT = "displayAccount";

    public static final String FUNC_CHECKBALANCE = "checkBalance";

    public static final String FUNC_GETMYTRANSFERINFOIDLIST = "getmyTransferInfoIdList";

    public static final String FUNC_DISPLAYACCOUNTLIST = "displayAccountList";

    public static final String FUNC_CREATETRANSFER = "createTransfer";

    public static final String FUNC_CREATEACCOUNT = "createAccount";

    public static final Event CREATEACCOUNTEVENT_EVENT = new Event("createAccountEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Account(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Account(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Account(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Account(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple5<BigInteger, String, String, String, String>> displayTransferInfo(BigInteger no) {
        final Function function = new Function(FUNC_DISPLAYTRANSFERINFO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(no)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple5<BigInteger, String, String, String, String>>(
                new Callable<Tuple5<BigInteger, String, String, String, String>>() {
                    @Override
                    public Tuple5<BigInteger, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<Tuple5<BigInteger, String, String, String, String>> displayAccount(String pub) {
        final Function function = new Function(FUNC_DISPLAYACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(pub)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple5<BigInteger, String, String, String, String>>(
                new Callable<Tuple5<BigInteger, String, String, String, String>>() {
                    @Override
                    public Tuple5<BigInteger, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<String> checkBalance(String accNo) {
        final Function function = new Function(FUNC_CHECKBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(accNo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getmyTransferInfoIdList(String accNo) {
        final Function function = new Function(FUNC_GETMYTRANSFERINFOIDLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(accNo)), 
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

    public RemoteCall<BigInteger> displayAccountList(BigInteger no) {
        final Function function = new Function(FUNC_DISPLAYACCOUNTLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(no)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> createTransfer(String payer, String payee, String money, String time) {
        final Function function = new Function(
                FUNC_CREATETRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(payer), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(payee), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(money), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createTransfer(String payer, String payee, String money, String time, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATETRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(payer), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(payee), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(money), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(time)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createTransferSeq(String payer, String payee, String money, String time) {
        final Function function = new Function(
                FUNC_CREATETRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(payer), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(payee), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(money), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(time)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, String, String> getCreateTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATETRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> createAccount(String accType, String name, String bal, String addr) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(accType), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(bal), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createAccount(String accType, String name, String bal, String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(accType), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(bal), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createAccountSeq(String accType, String name, String bal, String addr) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(accType), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(bal), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, String, String> getCreateAccountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public List<CreateAccountEventEventResponse> getCreateAccountEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEACCOUNTEVENT_EVENT, transactionReceipt);
        ArrayList<CreateAccountEventEventResponse> responses = new ArrayList<CreateAccountEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateAccountEventEventResponse typedResponse = new CreateAccountEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.no = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.accType = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.bal = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registercreateAccountEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CREATEACCOUNTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registercreateAccountEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CREATEACCOUNTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Account load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Account(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Account load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Account(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Account load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Account(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Account load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Account(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Account> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Account.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Account> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Account.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Account> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Account.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Account> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Account.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class CreateAccountEventEventResponse {
        public Log log;

        public BigInteger no;

        public String accType;

        public String name;

        public String bal;

        public String status;

        public String addr;
    }
}
