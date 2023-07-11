package org.fisco.bcos.contracts;

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
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
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
public class WarehouseReciptHash extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506106d2806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630a75a5241461005157806331b9ad3b14610171575b600080fd5b34801561005d57600080fd5b5061007c6004803603810190808035906020019092919050505061023e565b604051808581526020018481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156100cc5780820151818401526020810190506100b1565b50505050905090810190601f1680156100f95780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b83811015610132578082015181840152602081019050610117565b50505050905090810190601f16801561015f5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b34801561017d57600080fd5b5061022860048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061041c565b6040518082815260200191505060405180910390f35b6000806060806000600190505b600180549050811115156104145785600080838152602001908152602001600020600101541415610407576000808281526020019081526020016000206000015494506000808281526020019081526020016000206001015493506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561034f5780601f106103245761010080835404028352916020019161034f565b820191906000526020600020905b81548152906001019060200180831161033257829003601f168201915b505050505092506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103ff5780601f106103d4576101008083540402835291602001916103ff565b820191906000526020600020905b8154815290600101906020018083116103e257829003601f168201915b505050505091505b808060010191505061024b565b509193509193565b600080600180805490500190506001819080600181540180825580915050906001820390600052602060002001600090919290919091505550806000808381526020019081526020016000206000018190555084600080838152602001908152602001600020600101819055508360008083815260200190815260200160002060020190805190602001906104b2929190610601565b508260008083815260200190815260200160002060030190805190602001906104dc929190610601565b507f3213fb66c687b37dd7a1458fa1391ff0d1f561dfa20ca56af8598a668197486c81868686604051808581526020018481526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015610552578082015181840152602081019050610537565b50505050905090810190601f16801561057f5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156105b857808201518184015260208101905061059d565b50505050905090810190601f1680156105e55780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1809150509392505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061064257805160ff1916838001178555610670565b82800160010185558215610670579182015b8281111561066f578251825591602001919060010190610654565b5b50905061067d9190610681565b5090565b6106a391905b8082111561069f576000816000905550600101610687565b5090565b905600a165627a7a72305820c52d6f8918c9481cdd4ffd686f9dc37252edc7e4623b15dc7ff9db9e59c1107e0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getWarehouseReciptHash\",\"outputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"ID\",\"type\":\"uint256\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"time\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"time\",\"type\":\"string\"}],\"name\":\"setWarehouseReciptHash\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"hash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"time\",\"type\":\"string\"}],\"name\":\"warehouseReciptHashEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETWAREHOUSERECIPTHASH = "getWarehouseReciptHash";

    public static final String FUNC_SETWAREHOUSERECIPTHASH = "setWarehouseReciptHash";

    public static final Event WAREHOUSERECIPTHASHEVENT_EVENT = new Event("warehouseReciptHashEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected WarehouseReciptHash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected WarehouseReciptHash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected WarehouseReciptHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected WarehouseReciptHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple4<BigInteger, BigInteger, String, String>> getWarehouseReciptHash(BigInteger id) {
        final Function function = new Function(FUNC_GETWAREHOUSERECIPTHASH, 
                Arrays.<Type>asList(new Uint256(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<BigInteger, BigInteger, String, String>>(
                new Callable<Tuple4<BigInteger, BigInteger, String, String>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, String, String>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setWarehouseReciptHash(BigInteger id, String hash, String time) {
        final Function function = new Function(
                FUNC_SETWAREHOUSERECIPTHASH,
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(hash),
                new Utf8String(time)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setWarehouseReciptHash(BigInteger id, String hash, String time, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETWAREHOUSERECIPTHASH,
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(hash),
                new Utf8String(time)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setWarehouseReciptHashSeq(BigInteger id, String hash, String time) {
        final Function function = new Function(
                FUNC_SETWAREHOUSERECIPTHASH,
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(hash),
                new Utf8String(time)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, String, String> getSetWarehouseReciptHashInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETWAREHOUSERECIPTHASH,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getSetWarehouseReciptHashOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETWAREHOUSERECIPTHASH,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public List<WarehouseReciptHashEventEventResponse> getWarehouseReciptHashEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(WAREHOUSERECIPTHASHEVENT_EVENT, transactionReceipt);
        ArrayList<WarehouseReciptHashEventEventResponse> responses = new ArrayList<WarehouseReciptHashEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WarehouseReciptHashEventEventResponse typedResponse = new WarehouseReciptHashEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.no = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.hash = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.time = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerwarehouseReciptHashEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(WAREHOUSERECIPTHASHEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerwarehouseReciptHashEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(WAREHOUSERECIPTHASHEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static WarehouseReciptHash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new WarehouseReciptHash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static WarehouseReciptHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new WarehouseReciptHash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static WarehouseReciptHash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new WarehouseReciptHash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static WarehouseReciptHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new WarehouseReciptHash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<WarehouseReciptHash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(WarehouseReciptHash.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<WarehouseReciptHash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(WarehouseReciptHash.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<WarehouseReciptHash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(WarehouseReciptHash.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<WarehouseReciptHash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(WarehouseReciptHash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class WarehouseReciptHashEventEventResponse {
        public Log log;

        public BigInteger no;

        public BigInteger id;

        public String hash;

        public String time;
    }
}