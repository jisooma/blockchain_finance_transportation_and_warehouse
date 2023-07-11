package org.fisco.bcos.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
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
public class DigitalCash extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610c22806100606000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302dcbd191461007d578063075461721461012657806327e235e31461017d5780635f51522614610239578063b8dfa690146102f5578063d311c5ad1461039e575b600080fd5b34801561008957600080fd5b50610124600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104d3565b005b34801561013257600080fd5b5061013b61068b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561018957600080fd5b506101be600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506106b0565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101fe5780820151818401526020810190506101e3565b50505050905090810190601f16801561022b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561024557600080fd5b5061027a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610760565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102ba57808201518184015260208101905061029f565b50505050905090810190601f1680156102e75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561030157600080fd5b5061039c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610841565b005b3480156103aa57600080fd5b506104d1600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061099e565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561052e57600080fd5b80600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080519060200190610581929190610b51565b507f9b2e32f2691b16092ad4ec8aef92ebd5c81672220a833beabd824963cb770184838383604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561064a57808201518184015260208101905061062f565b50505050905090810190601f1680156106775780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a1505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60016020528060005260406000206000915090508054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107585780601f1061072d57610100808354040283529160200191610758565b820191906000526020600020905b81548152906001019060200180831161073b57829003601f168201915b505050505081565b6060600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108355780601f1061080a57610100808354040283529160200191610835565b820191906000526020600020905b81548152906001019060200180831161081857829003601f168201915b50505050509050919050565b80600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080519060200190610894929190610b51565b507fc0a552fdec5234cc4753b38364b097e573c3cdc5f680513cac89a141c46601f8838383604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561095d578082015181840152602081019050610942565b50505050905090810190601f16801561098a5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a1505050565b82600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090805190602001906109f1929190610b51565b5081600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080519060200190610a45929190610b51565b507fcf90475cb2ee903c10b68165beed0e4b4c73f29a9d314f30a1dd25d17a259bc8858583604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610b0e578082015181840152602081019050610af3565b50505050905090810190601f168015610b3b5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a15050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b9257805160ff1916838001178555610bc0565b82800160010185558215610bc0579182015b82811115610bbf578251825591602001919060010190610ba4565b5b509050610bcd9190610bd1565b5090565b610bf391905b80821115610bef576000816000905550600101610bd7565b5090565b905600a165627a7a7230582001953c6c46fc8b4c7d123382f8416e18c3d2236a0dd1f4fe132898d7e463b17e0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"bank\",\"type\":\"address\"},{\"name\":\"receiver\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"string\"}],\"name\":\"tokenMint\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"minter\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"balances\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"receiver\",\"type\":\"address\"}],\"name\":\"checkBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"bank\",\"type\":\"address\"},{\"name\":\"payer\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"string\"}],\"name\":\"tokenExchange\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"sender\",\"type\":\"address\"},{\"name\":\"receiver\",\"type\":\"address\"},{\"name\":\"amount1\",\"type\":\"string\"},{\"name\":\"amount2\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"string\"}],\"name\":\"tokenSend\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"string\"}],\"name\":\"Sent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"string\"}],\"name\":\"Exchange\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"string\"}],\"name\":\"Mint\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_TOKENMINT = "tokenMint";

    public static final String FUNC_MINTER = "minter";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_CHECKBALANCE = "checkBalance";

    public static final String FUNC_TOKENEXCHANGE = "tokenExchange";

    public static final String FUNC_TOKENSEND = "tokenSend";

    public static final Event SENT_EVENT = new Event("Sent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event EXCHANGE_EVENT = new Event("Exchange", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event MINT_EVENT = new Event("Mint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected DigitalCash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DigitalCash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DigitalCash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DigitalCash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> tokenMint(String bank, String receiver, String amount) {
        final Function function = new Function(
                FUNC_TOKENMINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void tokenMint(String bank, String receiver, String amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TOKENMINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String tokenMintSeq(String bank, String receiver, String amount) {
        final Function function = new Function(
                FUNC_TOKENMINT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, String> getTokenMintInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TOKENMINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public RemoteCall<String> minter() {
        final Function function = new Function(FUNC_MINTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> checkBalance(String receiver) {
        final Function function = new Function(
                FUNC_CHECKBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void checkBalance(String receiver, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CHECKBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String checkBalanceSeq(String receiver) {
        final Function function = new Function(
                FUNC_CHECKBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getCheckBalanceInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHECKBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<String> getCheckBalanceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CHECKBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> tokenExchange(String bank, String payer, String amount) {
        final Function function = new Function(
                FUNC_TOKENEXCHANGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(payer), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void tokenExchange(String bank, String payer, String amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TOKENEXCHANGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(payer), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String tokenExchangeSeq(String bank, String payer, String amount) {
        final Function function = new Function(
                FUNC_TOKENEXCHANGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(bank), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(payer), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, String> getTokenExchangeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TOKENEXCHANGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> tokenSend(String sender, String receiver, String amount1, String amount2, String amount) {
        final Function function = new Function(
                FUNC_TOKENSEND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(sender), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount1), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount2), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void tokenSend(String sender, String receiver, String amount1, String amount2, String amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TOKENSEND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(sender), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount1), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount2), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String tokenSendSeq(String sender, String receiver, String amount1, String amount2, String amount) {
        final Function function = new Function(
                FUNC_TOKENSEND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(sender), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount1), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount2), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple5<String, String, String, String, String> getTokenSendInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TOKENSEND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple5<String, String, String, String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (String) results.get(4).getValue()
                );
    }

    public List<SentEventResponse> getSentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SENT_EVENT, transactionReceipt);
        ArrayList<SentEventResponse> responses = new ArrayList<SentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SentEventResponse typedResponse = new SentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSentEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerSentEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<ExchangeEventResponse> getExchangeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EXCHANGE_EVENT, transactionReceipt);
        ArrayList<ExchangeEventResponse> responses = new ArrayList<ExchangeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ExchangeEventResponse typedResponse = new ExchangeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerExchangeEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(EXCHANGE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerExchangeEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(EXCHANGE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<MintEventResponse> getMintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MINT_EVENT, transactionReceipt);
        ArrayList<MintEventResponse> responses = new ArrayList<MintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintEventResponse typedResponse = new MintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerMintEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(MINT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerMintEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(MINT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static DigitalCash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DigitalCash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DigitalCash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DigitalCash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DigitalCash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DigitalCash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DigitalCash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DigitalCash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DigitalCash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DigitalCash.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<DigitalCash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DigitalCash.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DigitalCash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DigitalCash.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DigitalCash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DigitalCash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class SentEventResponse {
        public Log log;

        public String from;

        public String to;

        public String amount;
    }

    public static class ExchangeEventResponse {
        public Log log;

        public String from;

        public String to;

        public String amount;
    }

    public static class MintEventResponse {
        public Log log;

        public String from;

        public String to;

        public String amount;
    }
}
