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
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
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
public class Agreement extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610cb3806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635585ea0e14610051578063f9a8c742146102ae575b600080fd5b34801561005d57600080fd5b5061007c6004803603810190808035906020019092919050505061044d565b60405180878152602001806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b838110156100d25780820151818401526020810190506100b7565b50505050905090810190601f1680156100ff5780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b8381101561013857808201518184015260208101905061011d565b50505050905090810190601f1680156101655780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b8381101561019e578082015181840152602081019050610183565b50505050905090810190601f1680156101cb5780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b838110156102045780820151818401526020810190506101e9565b50505050905090810190601f1680156102315780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b8381101561026a57808201518184015260208101905061024f565b50505050905090810190601f1680156102975780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b3480156102ba57600080fd5b5061043760048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061084f565b6040518082815260200191505060405180910390f35b600060608060608060606000806060806060806060600196505b6001805490508711151561083e578d600080898152602001908152602001600020600101541415610831576000808881526020019081526020016000206001015495506000808881526020019081526020016000206002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105535780601f1061052857610100808354040283529160200191610553565b820191906000526020600020905b81548152906001019060200180831161053657829003601f168201915b505050505094506000808881526020019081526020016000206003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106035780601f106105d857610100808354040283529160200191610603565b820191906000526020600020905b8154815290600101906020018083116105e657829003601f168201915b505050505093506000808881526020019081526020016000206004018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106b35780601f10610688576101008083540402835291602001916106b3565b820191906000526020600020905b81548152906001019060200180831161069657829003601f168201915b505050505092506000808881526020019081526020016000206005018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107635780601f1061073857610100808354040283529160200191610763565b820191906000526020600020905b81548152906001019060200180831161074657829003601f168201915b505050505091506000808881526020019081526020016000206006018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108135780601f106107e857610100808354040283529160200191610813565b820191906000526020600020905b8154815290600101906020018083116107f657829003601f168201915b505050505090508585858585859c509c509c509c509c509c5061083f565b8680600101975050610467565b5b5050505050505091939550919395565b60008060018080549050019050600181908060018154018082558091505090600182039060005260206000200160009091929091909150555087600080838152602001908152602001600020600101819055508660008083815260200190815260200160002060020190805190602001906108cb929190610be2565b508560008083815260200190815260200160002060030190805190602001906108f5929190610be2565b5083600080838152602001908152602001600020600501908051906020019061091f929190610be2565b50846000808381526020019081526020016000206004019080519060200190610949929190610be2565b50826000808381526020019081526020016000206006019080519060200190610973929190610be2565b507f2dbc578a8dfc46f9c81ca7a863c88edddfa91ff82bc100acf1f591d84af1974f8189898989898960405180888152602001878152602001806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b838110156109f85780820151818401526020810190506109dd565b50505050905090810190601f168015610a255780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b83811015610a5e578082015181840152602081019050610a43565b50505050905090810190601f168015610a8b5780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b83811015610ac4578082015181840152602081019050610aa9565b50505050905090810190601f168015610af15780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b83811015610b2a578082015181840152602081019050610b0f565b50505050905090810190601f168015610b575780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b83811015610b90578082015181840152602081019050610b75565b50505050905090810190601f168015610bbd5780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390a1809150509695505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c2357805160ff1916838001178555610c51565b82800160010185558215610c51579182015b82811115610c50578251825591602001919060010190610c35565b5b509050610c5e9190610c62565b5090565b610c8491905b80821115610c80576000816000905550600101610c68565b5090565b905600a165627a7a72305820ee9f13f42eb393aa7bd5d4d733bd5e122f034363a7e8742b41f2b13cb95e5b0a0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getContarctHash\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"partyA\",\"type\":\"string\"},{\"name\":\"partyB\",\"type\":\"string\"},{\"name\":\"Type\",\"type\":\"string\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"setContarctHash\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"partyA\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"partyB\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"Type\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"hash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"ContarctHashEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"test\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETCONTARCTHASH = "getContarctHash";

    public static final String FUNC_SETCONTARCTHASH = "setContarctHash";

    public static final Event CONTARCTHASHEVENT_EVENT = new Event("ContarctHashEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event TEST_EVENT = new Event("test", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Agreement(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Agreement(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Agreement(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Agreement(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple6<BigInteger, String, String, String, String, String>> getContarctHash(BigInteger id) {
        final Function function = new Function(FUNC_GETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple6<BigInteger, String, String, String, String, String>>(
                new Callable<Tuple6<BigInteger, String, String, String, String, String>>() {
                    @Override
                    public Tuple6<BigInteger, String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setContarctHash(BigInteger id, String partyA, String partyB, String Type, String hash, String updateTime) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyA), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyB), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Type), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setContarctHash(BigInteger id, String partyA, String partyB, String Type, String hash, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyA), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyB), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Type), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setContarctHashSeq(BigInteger id, String partyA, String partyB, String Type, String hash, String updateTime) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyA), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyB), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Type), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple6<BigInteger, String, String, String, String, String> getSetContarctHashInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
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

    public Tuple1<BigInteger> getSetContarctHashOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public List<ContarctHashEventEventResponse> getContarctHashEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONTARCTHASHEVENT_EVENT, transactionReceipt);
        ArrayList<ContarctHashEventEventResponse> responses = new ArrayList<ContarctHashEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContarctHashEventEventResponse typedResponse = new ContarctHashEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.no = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.partyA = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.partyB = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.Type = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.hash = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.updateTime = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerContarctHashEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CONTARCTHASHEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerContarctHashEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(CONTARCTHASHEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TestEventResponse> getTestEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TEST_EVENT, transactionReceipt);
        ArrayList<TestEventResponse> responses = new ArrayList<TestEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TestEventResponse typedResponse = new TestEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registertestEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TEST_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registertestEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TEST_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Agreement load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Agreement(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Agreement load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Agreement(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Agreement load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Agreement(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Agreement load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Agreement(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Agreement> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Agreement.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Agreement> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Agreement.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Agreement> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Agreement.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Agreement> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Agreement.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ContarctHashEventEventResponse {
        public Log log;

        public BigInteger no;

        public BigInteger id;

        public String partyA;

        public String partyB;

        public String Type;

        public String hash;

        public String updateTime;
    }

    public static class TestEventResponse {
        public Log log;

        public BigInteger id;
    }
}