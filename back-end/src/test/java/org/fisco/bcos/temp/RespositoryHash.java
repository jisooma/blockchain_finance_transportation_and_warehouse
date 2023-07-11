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
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
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
public class RespositoryHash extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506106d2806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063b2905d1214610051578063e29dca061461011e575b600080fd5b34801561005d57600080fd5b5061010860048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061023e565b6040518082815260200191505060405180910390f35b34801561012a57600080fd5b5061014960048036038101908080359060200190929190505050610423565b604051808581526020018481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561019957808201518184015260208101905061017e565b50505050905090810190601f1680156101c65780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156101ff5780820151818401526020810190506101e4565b50505050905090810190601f16801561022c5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b600080600180805490500190506001819080600181540180825580915050906001820390600052602060002001600090919290919091505550806000808381526020019081526020016000206000018190555084600080838152602001908152602001600020600101819055508360008083815260200190815260200160002060020190805190602001906102d4929190610601565b508260008083815260200190815260200160002060030190805190602001906102fe929190610601565b507f6d2e30f77ed6e21a91e6ecefc36a8cdf7cbaa8e881498c9ce99c502b2b65136481868686604051808581526020018481526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015610374578082015181840152602081019050610359565b50505050905090810190601f1680156103a15780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156103da5780820151818401526020810190506103bf565b50505050905090810190601f1680156104075780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1809150509392505050565b6000806060806000600190505b600180549050811115156105f957856000808381526020019081526020016000206001015414156105ec576000808281526020019081526020016000206000015494506000808281526020019081526020016000206001015493506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105345780601f1061050957610100808354040283529160200191610534565b820191906000526020600020905b81548152906001019060200180831161051757829003601f168201915b505050505092506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105e45780601f106105b9576101008083540402835291602001916105e4565b820191906000526020600020905b8154815290600101906020018083116105c757829003601f168201915b505050505091505b8080600101915050610430565b509193509193565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061064257805160ff1916838001178555610670565b82800160010185558215610670579182015b8281111561066f578251825591602001919060010190610654565b5b50905061067d9190610681565b5090565b6106a391905b8082111561069f576000816000905550600101610687565b5090565b905600a165627a7a723058209c68e8ebc91d8dcbb175bf88c2c1bacf979fb9049a5c05ff187949c8f874269f0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"time\",\"type\":\"string\"}],\"name\":\"setRespositoryHash\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getRespositoryHash\",\"outputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"ID\",\"type\":\"uint256\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"time\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"hash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"time\",\"type\":\"string\"}],\"name\":\"RespositoryHashEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_SETRESPOSITORYHASH = "setRespositoryHash";

    public static final String FUNC_GETRESPOSITORYHASH = "getRespositoryHash";

    public static final Event RESPOSITORYHASHEVENT_EVENT = new Event("RespositoryHashEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected RespositoryHash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RespositoryHash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RespositoryHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RespositoryHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> setRespositoryHash(BigInteger id, String hash, String time) {
        final Function function = new Function(
                FUNC_SETRESPOSITORYHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setRespositoryHash(BigInteger id, String hash, String time, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETRESPOSITORYHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(time)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setRespositoryHashSeq(BigInteger id, String hash, String time) {
        final Function function = new Function(
                FUNC_SETRESPOSITORYHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(time)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, String, String> getSetRespositoryHashInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETRESPOSITORYHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<BigInteger> getSetRespositoryHashOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETRESPOSITORYHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<Tuple4<BigInteger, BigInteger, String, String>> getRespositoryHash(BigInteger id) {
        final Function function = new Function(FUNC_GETRESPOSITORYHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id)), 
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

    public List<RespositoryHashEventEventResponse> getRespositoryHashEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RESPOSITORYHASHEVENT_EVENT, transactionReceipt);
        ArrayList<RespositoryHashEventEventResponse> responses = new ArrayList<RespositoryHashEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RespositoryHashEventEventResponse typedResponse = new RespositoryHashEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.no = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.hash = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.time = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRespositoryHashEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(RESPOSITORYHASHEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRespositoryHashEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(RESPOSITORYHASHEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static RespositoryHash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RespositoryHash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RespositoryHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RespositoryHash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RespositoryHash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RespositoryHash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RespositoryHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RespositoryHash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RespositoryHash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RespositoryHash.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RespositoryHash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RespositoryHash.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<RespositoryHash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RespositoryHash.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<RespositoryHash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RespositoryHash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RespositoryHashEventEventResponse {
        public Log log;

        public BigInteger no;

        public BigInteger id;

        public String hash;

        public String time;
    }
}
