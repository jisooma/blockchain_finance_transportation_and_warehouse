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
import org.fisco.bcos.web3j.tuples.generated.Tuple6;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
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
public class ContractHash extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610ed9806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630855cb5c14610051578063f9a8c742146102fb575b600080fd5b34801561005d57600080fd5b506100c260048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061049a565b60405180888152602001878152602001806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b8381101561011e578082015181840152602081019050610103565b50505050905090810190601f16801561014b5780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b83811015610184578082015181840152602081019050610169565b50505050905090810190601f1680156101b15780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b838110156101ea5780820151818401526020810190506101cf565b50505050905090810190601f1680156102175780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b83811015610250578082015181840152602081019050610235565b50505050905090810190601f16801561027d5780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b838110156102b657808201518184015260208101905061029b565b50505050905090810190601f1680156102e35780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390f35b34801561030757600080fd5b5061048460048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a5b565b6040518082815260200191505060405180910390f35b60008060608060608060606000600190505b60018054905081111515610a4e57896000808381526020019081526020016000206001015414801561069b5750886040516020018082805190602001908083835b60208310151561051257805182526020820191506020810190506020830392506104ed565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310151561057b5780518252602082019150602081019050602083039250610556565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600080838152602001908152602001600020600401604051602001808280546001816001161561010002031660029004801561061f5780601f106105fd57610100808354040283529182019161061f565b820191906000526020600020905b81548152906001019060200180831161060b575b50509150506040516020818303038152906040526040518082805190602001908083835b6020831015156106685780518252602082019150602081019050602083039250610643565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916145b15610a41576000808281526020019081526020016000206000015497506000808281526020019081526020016000206001015496506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107795780601f1061074e57610100808354040283529160200191610779565b820191906000526020600020905b81548152906001019060200180831161075c57829003601f168201915b505050505095506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108295780601f106107fe57610100808354040283529160200191610829565b820191906000526020600020905b81548152906001019060200180831161080c57829003601f168201915b505050505094506000808281526020019081526020016000206004018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108d95780601f106108ae576101008083540402835291602001916108d9565b820191906000526020600020905b8154815290600101906020018083116108bc57829003601f168201915b505050505093506000808281526020019081526020016000206005018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109895780601f1061095e57610100808354040283529160200191610989565b820191906000526020600020905b81548152906001019060200180831161096c57829003601f168201915b505050505092506000808281526020019081526020016000206006018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a395780601f10610a0e57610100808354040283529160200191610a39565b820191906000526020600020905b815481529060010190602001808311610a1c57829003601f168201915b505050505091505b80806001019150506104ac565b5092959891949750929550565b60008060018080549050019050600181908060018154018082558091505090600182039060005260206000200160009091929091909150555080600080838152602001908152602001600020600001819055508760008083815260200190815260200160002060010181905550866000808381526020019081526020016000206002019080519060200190610af1929190610e08565b50856000808381526020019081526020016000206003019080519060200190610b1b929190610e08565b50836000808381526020019081526020016000206005019080519060200190610b45929190610e08565b50846000808381526020019081526020016000206004019080519060200190610b6f929190610e08565b50826000808381526020019081526020016000206006019080519060200190610b99929190610e08565b507f2dbc578a8dfc46f9c81ca7a863c88edddfa91ff82bc100acf1f591d84af1974f8189898989898960405180888152602001878152602001806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b83811015610c1e578082015181840152602081019050610c03565b50505050905090810190601f168015610c4b5780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b83811015610c84578082015181840152602081019050610c69565b50505050905090810190601f168015610cb15780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b83811015610cea578082015181840152602081019050610ccf565b50505050905090810190601f168015610d175780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b83811015610d50578082015181840152602081019050610d35565b50505050905090810190601f168015610d7d5780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b83811015610db6578082015181840152602081019050610d9b565b50505050905090810190601f168015610de35780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390a1809150509695505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e4957805160ff1916838001178555610e77565b82800160010185558215610e77579182015b82811115610e76578251825591602001919060010190610e5b565b5b509050610e849190610e88565b5090565b610eaa91905b80821115610ea6576000816000905550600101610e8e565b5090565b905600a165627a7a723058204e447ba64972d98ad9e8867681ca51d3d1ce8cf3bce17abd3b3817f8f0b4c1310029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"type1\",\"type\":\"string\"}],\"name\":\"getContarctHash\",\"outputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"ID\",\"type\":\"uint256\"},{\"name\":\"partyA\",\"type\":\"string\"},{\"name\":\"partyB\",\"type\":\"string\"},{\"name\":\"Type\",\"type\":\"string\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"partyA\",\"type\":\"string\"},{\"name\":\"partyB\",\"type\":\"string\"},{\"name\":\"Type\",\"type\":\"string\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"setContarctHash\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"partyA\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"partyB\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"Type\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"hash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"ContarctHashEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETCONTARCTHASH = "getContarctHash";

    public static final String FUNC_SETCONTARCTHASH = "setContarctHash";

    public static final Event CONTARCTHASHEVENT_EVENT = new Event("ContarctHashEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ContractHash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractHash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractHash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple7<BigInteger, BigInteger, String, String, String, String, String>> getContarctHash(BigInteger id, String type1) {
        final Function function = new Function(FUNC_GETCONTARCTHASH, 
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(type1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple7<BigInteger, BigInteger, String, String, String, String, String>>(
                new Callable<Tuple7<BigInteger, BigInteger, String, String, String, String, String>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, String, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (String) results.get(3).getValue(),
                                (String) results.get(4).getValue(),
                                (String) results.get(5).getValue(),
                                (String) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setContarctHash(BigInteger id, String partyA, String partyB, String Type, String hash, String updateTime) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH,
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(partyA),
                new Utf8String(partyB),
                new Utf8String(Type),
                new Utf8String(hash),
                new Utf8String(updateTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setContarctHash(BigInteger id, String partyA, String partyB, String Type, String hash, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH,
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(partyA),
                new Utf8String(partyB),
                new Utf8String(Type),
                new Utf8String(hash),
                new Utf8String(updateTime)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setContarctHashSeq(BigInteger id, String partyA, String partyB, String Type, String hash, String updateTime) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH,
                Arrays.<Type>asList(new Uint256(id),
                new Utf8String(partyA),
                new Utf8String(partyB),
                new Utf8String(Type),
                new Utf8String(hash),
                new Utf8String(updateTime)),
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
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CONTARCTHASHEVENT_EVENT, transactionReceipt);
        ArrayList<ContarctHashEventEventResponse> responses = new ArrayList<ContarctHashEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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

    @Deprecated
    public static ContractHash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractHash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractHash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractHash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractHash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractHash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractHash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractHash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractHash.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractHash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractHash.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ContractHash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ContractHash.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ContractHash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ContractHash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
}
