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
public class Respository extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611bb4806100206000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806332601cfe146100725780637dc9ab121461014357806394fcf03b14610406578063cc80edbb146104b1578063fa76e12514610644575b600080fd5b34801561007e57600080fd5b5061012960048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106dc565b604051808215151515815260200191505060405180910390f35b34801561014f57600080fd5b5061016e60048036038101908080359060200190929190505050610c06565b604051808981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b8381101561022857808201518184015260208101905061020d565b50505050905090810190601f1680156102555780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b8381101561028e578082015181840152602081019050610273565b50505050905090810190601f1680156102bb5780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b838110156102f45780820151818401526020810190506102d9565b50505050905090810190601f1680156103215780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b8381101561035a57808201518184015260208101905061033f565b50505050905090810190601f1680156103875780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b838110156103c05780820151818401526020810190506103a5565b50505050905090810190601f1680156103ed5780820380516001836020036101000a031916815260200191505b509d505050505050505050505050505060405180910390f35b34801561041257600080fd5b5061049760048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611001565b604051808215151515815260200191505060405180910390f35b3480156104bd57600080fd5b5061062a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506114cb565b604051808215151515815260200191505060405180910390f35b34801561065057600080fd5b50610685600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611a4c565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156106c85780820151818401526020810190506106ad565b505050509050019250505060405180910390f35b60008060008581526020019081526020016000206006018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107875780601f1061075c57610100808354040283529160200191610787565b820191906000526020600020905b81548152906001019060200180831161076a57829003601f168201915b505050505092506000808581526020019081526020016000206007018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108375780601f1061080c57610100808354040283529160200191610837565b820191906000526020600020905b81548152906001019060200180831161081a57829003601f168201915b505050505091507ffe1dbb3ad6d750fd2312bf8ba1172c651e10b451b3830499e44de102fa5bec068460008087815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660008088815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166000808981526020019081526020016000206003016000808a81526020019081526020016000206004016000808b81526020019081526020016000206005018989604051808981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001806020018060200186810386528b818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610a105780601f106109e557610100808354040283529160200191610a10565b820191906000526020600020905b8154815290600101906020018083116109f357829003601f168201915b505086810385528a818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610a935780601f10610a6857610100808354040283529160200191610a93565b820191906000526020600020905b815481529060010190602001808311610a7657829003601f168201915b5050868103845289818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610b165780601f10610aeb57610100808354040283529160200191610b16565b820191906000526020600020905b815481529060010190602001808311610af957829003601f168201915b5050868103835288818151815260200191508051906020019080838360005b83811015610b50578082015181840152602081019050610b35565b50505050905090810190601f168015610b7d5780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b83811015610bb6578082015181840152602081019050610b9b565b50505050905090810190601f168015610be35780820380516001836020036101000a031916815260200191505b509d505050505050505050505050505060405180910390a1600190509392505050565b600080600060608060608060608897506000808a815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1696506000808a815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1695506000808a81526020019081526020016000206003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d2f5780601f10610d0457610100808354040283529160200191610d2f565b820191906000526020600020905b815481529060010190602001808311610d1257829003601f168201915b505050505094506000808a81526020019081526020016000206004018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ddf5780601f10610db457610100808354040283529160200191610ddf565b820191906000526020600020905b815481529060010190602001808311610dc257829003601f168201915b505050505093506000808a81526020019081526020016000206005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e8f5780601f10610e6457610100808354040283529160200191610e8f565b820191906000526020600020905b815481529060010190602001808311610e7257829003601f168201915b505050505092506000808a81526020019081526020016000206006018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f3f5780601f10610f1457610100808354040283529160200191610f3f565b820191906000526020600020905b815481529060010190602001808311610f2257829003601f168201915b505050505091506000808a81526020019081526020016000206007018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610fef5780601f10610fc457610100808354040283529160200191610fef565b820191906000526020600020905b81548152906001019060200180","8311610fd257829003601f168201915b50505050509050919395975091939597565b60008260008086815260200190815260200160002060010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000808581526020019081526020016000206007018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111005780601f106110d557610100808354040283529160200191611100565b820191906000526020600020905b8154815290600101906020018083116110e357829003601f168201915b505050505091507ffe1dbb3ad6d750fd2312bf8ba1172c651e10b451b3830499e44de102fa5bec06848460008088815260200190815260200160002060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166000808981526020019081526020016000206003016000808a81526020019081526020016000206004016000808b81526020019081526020016000206005016000808c815260200190815260200160002060060189604051808981526020018873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001806020018060200186810386528b8181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156112b85780601f1061128d576101008083540402835291602001916112b8565b820191906000526020600020905b81548152906001019060200180831161129b57829003601f168201915b505086810385528a81815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561133b5780601f106113105761010080835404028352916020019161133b565b820191906000526020600020905b81548152906001019060200180831161131e57829003601f168201915b50508681038452898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156113be5780601f10611393576101008083540402835291602001916113be565b820191906000526020600020905b8154815290600101906020018083116113a157829003601f168201915b50508681038352888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156114415780601f1061141657610100808354040283529160200191611441565b820191906000526020600020905b81548152906001019060200180831161142457829003601f168201915b5050868103825287818151815260200191508051906020019080838360005b8381101561147b578082015181840152602081019050611460565b50505050905090810190601f1680156114a85780820380516001836020036101000a031916815260200191505b509d505050505050505050505050505060405180910390a1600190509392505050565b6000806001600280549050019050600160008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819080600181540180825580915050906001820390600052602060002001600090919290919091505550600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190806001815401808255809150509060018203906000526020600020016000909192909190915055506002819080600181540180825580915050906001820390600052602060002001600090919290919091505550610100604051908101604052808281526020018973ffffffffffffffffffffffffffffffffffffffff1681526020018873ffffffffffffffffffffffffffffffffffffffff1681526020018781526020018681526020018581526020016040805190810160405280600681526020017fe585a5e5ba9300000000000000000000000000000000000000000000000000008152508152602001848152506000808381526020019081526020016000206000820151816000015560208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506060820151816003019080519060200190611739929190611ae3565b506080820151816004019080519060200190611756929190611ae3565b5060a0820151816005019080519060200190611773929190611ae3565b5060c0820151816006019080519060200190611790929190611ae3565b5060e08201518160070190805190602001906117ad929190611ae3565b509050507ffe1dbb3ad6d750fd2312bf8ba1172c651e10b451b3830499e44de102fa5bec0681898989898989604051808881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018060200180602001806020018060200186810386528a818151815260200191508051906020019080838360005b83811015611893578082015181840152602081019050611878565b50505050905090810190601f1680156118c05780820380516001836020036101000a031916815260200191505b50868103855289818151815260200191508051906020019080838360005b838110156118f95780820151818401526020810190506118de565b50505050905090810190601f1680156119265780820380516001836020036101000a031916815260200191505b50868103845288818151815260200191508051906020019080838360005b8381101561195f578082015181840152602081019050611944565b50505050905090810190601f16801561198c5780820380516001836020036101000a031916815260200191505b50868103835260068152602001807fe585a5e5ba930000000000000000000000000000000000000000000000000000815250602001868103825287818151815260200191508051906020019080838360005b838110156119f95780820151818401526020810190506119de565b50505050905090810190601f168015611a265780820380516001836020036101000a031916815260200191505b509c5050505050505050505050505060405180910390a160019150509695505050505050565b6060600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020018280548015611ad757602002820191906000526020600020905b815481526020019060010190808311611ac3575b50505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611b2457805160ff1916838001178555611b52565b82800160010185558215611b52579182015b82811115611b51578251825591602001919060010190611b36565b5b509050611b5f9190611b63565b5090565b611b8591905b80821115611b81576000816000905550600101611b69565b5090565b905600a165627a7a72305820d6e8003d2b36e0dda5d4b62877ab9e880e3f7269f165a20d4fd709a0abcc69560029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"updateRepositoryStatus\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"}],\"name\":\"displayRepository\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"holder\",\"type\":\"address\"},{\"name\":\"transport\",\"type\":\"address\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"valMoney\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"holder\",\"type\":\"address\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"updateRepositoryHolder\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"enterprise\",\"type\":\"address\"},{\"name\":\"transport\",\"type\":\"address\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"valMoney\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"createRepository\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accNo\",\"type\":\"address\"}],\"name\":\"getMyrepository\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"holder\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"transport\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"text\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"valMoney\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"status\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"RepositoryEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_UPDATEREPOSITORYSTATUS = "updateRepositoryStatus";

    public static final String FUNC_DISPLAYREPOSITORY = "displayRepository";

    public static final String FUNC_UPDATEREPOSITORYHOLDER = "updateRepositoryHolder";

    public static final String FUNC_CREATEREPOSITORY = "createRepository";

    public static final String FUNC_GETMYREPOSITORY = "getMyrepository";

    public static final Event REPOSITORYEVENT_EVENT = new Event("RepositoryEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected Respository(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Respository(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Respository(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Respository(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Boolean> updateRepositoryStatus(BigInteger no, String status, String updateTime) {
        final Function function = new Function(FUNC_UPDATEREPOSITORYSTATUS, 
                Arrays.<Type>asList(new Uint256(no),
                new Utf8String(status),
                new Utf8String(updateTime)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple8<BigInteger, String, String, String, String, String, String, String>> displayRepository(BigInteger no) {
        final Function function = new Function(FUNC_DISPLAYREPOSITORY,
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

    public RemoteCall<Boolean> updateRepositoryHolder(BigInteger no, String holder, String updateTime) {
        final Function function = new Function(FUNC_UPDATEREPOSITORYHOLDER,
                Arrays.<Type>asList(new Uint256(no),
                new Address(holder),
                new Utf8String(updateTime)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> createRepository(String enterprise, String transport, String text, String addr, String valMoney, String updateTime) {
        final Function function = new Function(
                FUNC_CREATEREPOSITORY,
                Arrays.<Type>asList(new Address(enterprise),
                new Address(transport),
                new Utf8String(text),
                new Utf8String(addr),
                new Utf8String(valMoney),
                new Utf8String(updateTime)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createRepository(String enterprise, String transport, String text, String addr, String valMoney, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATEREPOSITORY,
                Arrays.<Type>asList(new Address(enterprise),
                new Address(transport),
                new Utf8String(text),
                new Utf8String(addr),
                new Utf8String(valMoney),
                new Utf8String(updateTime)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createRepositorySeq(String enterprise, String transport, String text, String addr, String valMoney, String updateTime) {
        final Function function = new Function(
                FUNC_CREATEREPOSITORY,
                Arrays.<Type>asList(new Address(enterprise),
                new Address(transport),
                new Utf8String(text),
                new Utf8String(addr),
                new Utf8String(valMoney),
                new Utf8String(updateTime)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple6<String, String, String, String, String, String> getCreateRepositoryInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEREPOSITORY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple6<String, String, String, String, String, String>(

                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (String) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue()
                );
    }

    public Tuple1<Boolean> getCreateRepositoryOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEREPOSITORY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> getMyrepository(String accNo) {
        final Function function = new Function(
                FUNC_GETMYREPOSITORY,
                Arrays.<Type>asList(new Address(accNo)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getMyrepository(String accNo, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETMYREPOSITORY,
                Arrays.<Type>asList(new Address(accNo)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getMyrepositorySeq(String accNo) {
        final Function function = new Function(
                FUNC_GETMYREPOSITORY,
                Arrays.<Type>asList(new Address(accNo)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getGetMyrepositoryInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_GETMYREPOSITORY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<List<BigInteger>> getGetMyrepositoryOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETMYREPOSITORY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<List<BigInteger>>(

                convertToNative((List<Uint256>) results.get(0).getValue())
                );
    }

    public List<RepositoryEventEventResponse> getRepositoryEventEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REPOSITORYEVENT_EVENT, transactionReceipt);
        ArrayList<RepositoryEventEventResponse> responses = new ArrayList<RepositoryEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RepositoryEventEventResponse typedResponse = new RepositoryEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.holder = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.transport = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.text = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.valMoney = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.updateTime = (String) eventValues.getNonIndexedValues().get(7).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRepositoryEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REPOSITORYEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRepositoryEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REPOSITORYEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Respository load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Respository(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Respository load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Respository(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Respository load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Respository(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Respository load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Respository(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Respository> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Respository.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Respository> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Respository.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Respository> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Respository.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Respository> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Respository.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RepositoryEventEventResponse {
        public Log log;

        public BigInteger id;

        public String holder;

        public String transport;

        public String text;

        public String addr;

        public String valMoney;

        public String status;

        public String updateTime;
    }
}
