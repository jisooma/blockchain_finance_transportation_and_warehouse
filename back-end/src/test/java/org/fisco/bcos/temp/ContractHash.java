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
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
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
public class ContractHash extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611847806100206000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630855cb5c146100675780634b61a2cd1461037d57806385802bf714610469578063d5fd7b7614610555575b600080fd5b34801561007357600080fd5b506100d860048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061073a565b6040518089815260200188815260200180602001806020018060200180602001806020018060200187810387528d818151815260200191508051906020019080838360005b8381101561013857808201518184015260208101905061011d565b50505050905090810190601f1680156101655780820380516001836020036101000a031916815260200191505b5087810386528c818151815260200191508051906020019080838360005b8381101561019e578082015181840152602081019050610183565b50505050905090810190601f1680156101cb5780820380516001836020036101000a031916815260200191505b5087810385528b818151815260200191508051906020019080838360005b838110156102045780820151818401526020810190506101e9565b50505050905090810190601f1680156102315780820380516001836020036101000a031916815260200191505b5087810384528a818151815260200191508051906020019080838360005b8381101561026a57808201518184015260208101905061024f565b50505050905090810190601f1680156102975780820380516001836020036101000a031916815260200191505b50878103835289818151815260200191508051906020019080838360005b838110156102d05780820151818401526020810190506102b5565b50505050905090810190601f1680156102fd5780820380516001836020036101000a031916815260200191505b50878103825288818151815260200191508051906020019080838360005b8381101561033657808201518184015260208101905061031b565b50505050905090810190601f1680156103635780820380516001836020036101000a031916815260200191505b509e50505050505050505050505050505060405180910390f35b34801561038957600080fd5b506103ee60048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610dad565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561042e578082015181840152602081019050610413565b50505050905090810190601f16801561045b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561047557600080fd5b506104da60048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061106f565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561051a5780820151818401526020810190506104ff565b50505050905090810190601f1680156105475780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561056157600080fd5b5061072460048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611331565b6040518082815260200191505060405180910390f35b6000806060806060806060806000600190505b60018054905081111515610d9f578a6000808381526020019081526020016000206001015414801561093c5750896040516020018082805190602001908083835b6020831015156107b3578051825260208201915060208101905060208303925061078e565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b60208310151561081c57805182526020820191506020810190506020830392506107f7565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008083815260200190815260200160002060040160405160200180828054600181600116156101000203166002900480156108c05780601f1061089e5761010080835404028352918201916108c0565b820191906000526020600020905b8154815290600101906020018083116108ac575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561090957805182526020820191506020810190506020830392506108e4565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916145b15610d92576000808281526020019081526020016000206000015498506000808281526020019081526020016000206001015497506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a1a5780601f106109ef57610100808354040283529160200191610a1a565b820191906000526020600020905b8154815290600101906020018083116109fd57829003601f168201915b505050505096506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610aca5780601f10610a9f57610100808354040283529160200191610aca565b820191906000526020600020905b815481529060010190602001808311610aad57829003601f168201915b505050505095506000808281526020019081526020016000206004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b7a5780601f10610b4f57610100808354040283529160200191610b7a565b820191906000526020600020905b815481529060010190602001808311610b5d57829003601f168201915b505050505094506000808281526020019081526020016000206006018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c2a5780601f10610bff57610100808354040283529160200191610c2a565b820191906000526020600020905b815481529060010190602001808311610c0d57829003601f168201915b505050505093506000808281526020019081526020016000206007018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610cda5780601f10610caf57610100808354040283529160200191610cda565b820191906000526020600020905b815481529060010190602001808311610cbd57829003601f168201915b505050505091506000808281526020019081526020016000206005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d8a5780601f10610d5f57610100808354040283529160200191610d8a565b820191906000526020600020905b815481529060010190602001808311610d6d57829003601f168201915b505050505092505b808060010191505061074d565b509295985092959890939650565b60606000600190505b60018054905081111515611068578360008083815260200190815260200160002060010154148015610fa55750826040516020018082805190602001908083835b602083101515610e1c5780518252602082019150602081019050602083039250610df7565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610e855780518252602082019150602081019050602083039250610e60565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166000808381526020019081526020016000206004016040516020018082805460018160011615610100020316600290048015610f295780601f10610f07576101008083540402835291820191610f29565b820191906000526020600020905b815481529060010190602001808311610f15575b50509150506040516020818303038152906040526040518082805190602001908083835b602083101515610f725780518252602082019150602081019050602083039250610f4d565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916145b1561105b576000808281526020019081526020016000206005018054600181600116156101000203166002900480601f01602080910402602001","60405190810160405280929190818152602001828054600181600116156101000203166002900480156110535780601f1061102857610100808354040283529160200191611053565b820191906000526020600020905b81548152906001019060200180831161103657829003601f168201915b505050505091505b8080600101915050610db6565b5092915050565b60606000600190505b6001805490508111151561132a5783600080838152602001908152602001600020600101541480156112675750826040516020018082805190602001908083835b6020831015156110de57805182526020820191506020810190506020830392506110b9565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156111475780518252602082019150602081019050602083039250611122565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008083815260200190815260200160002060040160405160200180828054600181600116156101000203166002900480156111eb5780601f106111c95761010080835404028352918201916111eb565b820191906000526020600020905b8154815290600101906020018083116111d7575b50509150506040516020818303038152906040526040518082805190602001908083835b602083101515611234578051825260208201915060208101905060208303925061120f565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916145b1561131d576000808281526020019081526020016000206006018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113155780601f106112ea57610100808354040283529160200191611315565b820191906000526020600020905b8154815290600101906020018083116112f857829003601f168201915b505050505091505b8080600101915050611078565b5092915050565b600080600180805490500190506001819080600181540180825580915050906001820390600052602060002001600090919290919091505550806000808381526020019081526020016000206000018190555088600080838152602001908152602001600020600101819055508760008083815260200190815260200160002060020190805190602001906113c7929190611776565b508660008083815260200190815260200160002060030190805190602001906113f1929190611776565b5084600080838152602001908152602001600020600601908051906020019061141b929190611776565b50856000808381526020019081526020016000206004019080519060200190611445929190611776565b5083600080838152602001908152602001600020600501908051906020019061146f929190611776565b50826000808381526020019081526020016000206007019080519060200190611499929190611776565b507f0abd3fb3d3310fbe38f499ec0550167ef2027eca47250ff7049c52aeebe1c924818a8a8a8a8a8a8a6040518089815260200188815260200180602001806020018060200180602001806020018060200187810387528d818151815260200191508051906020019080838360005b83811015611523578082015181840152602081019050611508565b50505050905090810190601f1680156115505780820380516001836020036101000a031916815260200191505b5087810386528c818151815260200191508051906020019080838360005b8381101561158957808201518184015260208101905061156e565b50505050905090810190601f1680156115b65780820380516001836020036101000a031916815260200191505b5087810385528b818151815260200191508051906020019080838360005b838110156115ef5780820151818401526020810190506115d4565b50505050905090810190601f16801561161c5780820380516001836020036101000a031916815260200191505b5087810384528a818151815260200191508051906020019080838360005b8381101561165557808201518184015260208101905061163a565b50505050905090810190601f1680156116825780820380516001836020036101000a031916815260200191505b50878103835289818151815260200191508051906020019080838360005b838110156116bb5780820151818401526020810190506116a0565b50505050905090810190601f1680156116e85780820380516001836020036101000a031916815260200191505b50878103825288818151815260200191508051906020019080838360005b83811015611721578082015181840152602081019050611706565b50505050905090810190601f16801561174e5780820380516001836020036101000a031916815260200191505b509e50505050505050505050505050505060405180910390a180915050979650505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106117b757805160ff19168380011785556117e5565b828001600101855582156117e5579182015b828111156117e45782518255916020019190600101906117c9565b5b5090506117f291906117f6565b5090565b61181891905b808211156118145760008160009055506001016117fc565b5090565b905600a165627a7a723058207d3c266db1134c50fcd7eeb0e229b3ea31d9263431289ba2b8f7893d4d204a580029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"type1\",\"type\":\"string\"}],\"name\":\"getContarctHash\",\"outputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"ID\",\"type\":\"uint256\"},{\"name\":\"partyA\",\"type\":\"string\"},{\"name\":\"partyB\",\"type\":\"string\"},{\"name\":\"Type\",\"type\":\"string\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"type1\",\"type\":\"string\"}],\"name\":\"getAddr\",\"outputs\":[{\"name\":\"addr\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"type1\",\"type\":\"string\"}],\"name\":\"getHash\",\"outputs\":[{\"name\":\"hash\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"partyA\",\"type\":\"string\"},{\"name\":\"partyB\",\"type\":\"string\"},{\"name\":\"Type\",\"type\":\"string\"},{\"name\":\"hash\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"setContarctHash\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"partyA\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"partyB\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"Type\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"hash\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"ContarctHashEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETCONTARCTHASH = "getContarctHash";

    public static final String FUNC_GETADDR = "getAddr";

    public static final String FUNC_GETHASH = "getHash";

    public static final String FUNC_SETCONTARCTHASH = "setContarctHash";

    public static final Event CONTARCTHASHEVENT_EVENT = new Event("ContarctHashEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
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

    public RemoteCall<Tuple8<BigInteger, BigInteger, String, String, String, String, String, String>> getContarctHash(BigInteger id, String type1) {
        final Function function = new Function(FUNC_GETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(type1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple8<BigInteger, BigInteger, String, String, String, String, String, String>>(
                new Callable<Tuple8<BigInteger, BigInteger, String, String, String, String, String, String>>() {
                    @Override
                    public Tuple8<BigInteger, BigInteger, String, String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, BigInteger, String, String, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (String) results.get(6).getValue(), 
                                (String) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<String> getAddr(BigInteger id, String type1) {
        final Function function = new Function(FUNC_GETADDR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(type1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getHash(BigInteger id, String type1) {
        final Function function = new Function(FUNC_GETHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(type1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setContarctHash(BigInteger id, String partyA, String partyB, String Type, String hash, String addr, String updateTime) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyA), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyB), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Type), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setContarctHash(BigInteger id, String partyA, String partyB, String Type, String hash, String addr, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyA), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyB), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Type), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setContarctHashSeq(BigInteger id, String partyA, String partyB, String Type, String hash, String addr, String updateTime) {
        final Function function = new Function(
                FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyA), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(partyB), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(Type), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(hash), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple7<BigInteger, String, String, String, String, String, String> getSetContarctHashInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETCONTARCTHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple7<BigInteger, String, String, String, String, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (String) results.get(6).getValue()
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
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.updateTime = (String) eventValues.getNonIndexedValues().get(7).getValue();
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

        public String addr;

        public String updateTime;
    }
}