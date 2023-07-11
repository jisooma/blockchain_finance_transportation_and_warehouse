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
import org.fisco.bcos.web3j.abi.datatypes.Bool;
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
import org.fisco.bcos.web3j.tuples.generated.Tuple8;
import org.fisco.bcos.web3j.tuples.generated.Tuple9;
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
public class WareHouseRecipt extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611417806100206000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806309a9d5da146100725780633a7871e31461026157806347ec295e14610538578063998614a914610609578063b445096914610720575b600080fd5b34801561007e57600080fd5b5061024b60048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506107f1565b6040518082815260200191505060405180910390f35b34801561026d57600080fd5b5061028c60048036038101908080359060200190929190505050610c59565b604051808a815260200189815260200180602001806020018881526020018060200180602001806020018060200187810387528e818151815260200191508051906020019080838360005b838110156102f25780820151818401526020810190506102d7565b50505050905090810190601f16801561031f5780820380516001836020036101000a031916815260200191505b5087810386528d818151815260200191508051906020019080838360005b8381101561035857808201518184015260208101905061033d565b50505050905090810190601f1680156103855780820380516001836020036101000a031916815260200191505b5087810385528b818151815260200191508051906020019080838360005b838110156103be5780820151818401526020810190506103a3565b50505050905090810190601f1680156103eb5780820380516001836020036101000a031916815260200191505b5087810384528a818151815260200191508051906020019080838360005b83811015610424578082015181840152602081019050610409565b50505050905090810190601f1680156104515780820380516001836020036101000a031916815260200191505b50878103835289818151815260200191508051906020019080838360005b8381101561048a57808201518184015260208101905061046f565b50505050905090810190601f1680156104b75780820380516001836020036101000a031916815260200191505b50878103825288818151815260200191508051906020019080838360005b838110156104f05780820151818401526020810190506104d5565b50505050905090810190601f16801561051d5780820380516001836020036101000a031916815260200191505b509f5050505050505050505050505050505060405180910390f35b34801561054457600080fd5b506105ef60048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061111d565b604051808215151515815260200191505060405180910390f35b34801561061557600080fd5b5061070660048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506111c7565b604051808215151515815260200191505060405180910390f35b34801561072c57600080fd5b506107d760048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061129c565b604051808215151515815260200191505060405180910390f35b60008060018080549050019050600181908060018154018082558091505090600182039060005260206000200160009091929091909150555080600080838152602001908152602001600020600001819055508960008083815260200190815260200160002060010181905550886000808381526020019081526020016000206002019080519060200190610887929190611346565b508760008083815260200190815260200160002060030190805190602001906108b1929190611346565b5086600080838152602001908152602001600020600401819055508560008083815260200190815260200160002060050190805190602001906108f5929190611346565b5084600080838152602001908152602001600020600601908051906020019061091f929190611346565b50836000808381526020019081526020016000206007019080519060200190610949929190611346565b50826000808381526020019081526020016000206008019080519060200190610973929190611346565b507f46d6e6403f3f850a7951000c3ee29dd8f07ebccfd15ec5f81585d34c428c7255818b8b8b8b8b8b8b8b604051808a815260200189815260200180602001806020018881526020018060200180602001806020018060200187810387528e818151815260200191508051906020019080838360005b83811015610a045780820151818401526020810190506109e9565b50505050905090810190601f168015610a315780820380516001836020036101000a031916815260200191505b5087810386528d818151815260200191508051906020019080838360005b83811015610a6a578082015181840152602081019050610a4f565b50505050905090810190601f168015610a975780820380516001836020036101000a031916815260200191505b5087810385528b818151815260200191508051906020019080838360005b83811015610ad0578082015181840152602081019050610ab5565b50505050905090810190601f168015610afd5780820380516001836020036101000a031916815260200191505b5087810384528a818151815260200191508051906020019080838360005b83811015610b36578082015181840152602081019050610b1b565b50505050905090810190601f168015610b635780820380516001836020036101000a031916815260200191505b50878103835289818151815260200191508051906020019080838360005b83811015610b9c578082015181840152602081019050610b81565b50505050905090810190601f168015610bc95780820380516001836020036101000a031916815260200191505b50878103825288818151815260200191508051906020019080838360005b83811015610c02578082015181840152602081019050610be7565b50505050905090810190601f168015610c2f5780820380516001836020036101000a031916815260200191505b509f5050505050505050505050505050505060405180910390a18091505098975050505050505050565b60008060608060006060806060806000600190505b6001805490508111151561110f578a600080838152602001908152602001600020600101541415611102576000808281526020019081526020016000206000015499506000808281526020019081526020016000206001015498506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d725780601f10610d4757610100808354040283529160200191610d72565b820191906000526020600020905b815481529060010190602001808311610d5557829003601f168201915b505050505097506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e225780601f10610df757610100808354040283529160200191610e22565b820191906000526020600020905b815481529060010190602001808311610e0557829003601f168201915b505050505096506000808281526020019081526020016000206004015495506000808281526020019081526020016000206005018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610eea5780601f10610ebf57610100808354040283529160200191610eea565b820191906000526020600020905b815481529060010190602001808311610ecd57829003601f168201915b505050505094506000808281526020019081526020016000206006018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f9a5780601f10610f6f57610100808354040283529160200191610f9a565b820191906000526020600020905b815481529060010190602001808311610f7d57829003601f168201915b505050505093506000808281526020019081526020016000206007018054600181600116156101000203166002900480601f01602080910402602001604051908101604052","809291908181526020018280546001816001161561010002031660029004801561104a5780601f1061101f5761010080835404028352916020019161104a565b820191906000526020600020905b81548152906001019060200180831161102d57829003601f168201915b505050505092506000808281526020019081526020016000206008018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110fa5780601f106110cf576101008083540402835291602001916110fa565b820191906000526020600020905b8154815290600101906020018083116110dd57829003601f168201915b505050505091505b8080600101915050610c6e565b509193959799909294969850565b600080600190505b600180549050811115156111ba57846000808381526020019081526020016000206001015414156111ad57836000808381526020019081526020016000206002019080519060200190611179929190611346565b508260008083815260200190815260200160002060080190805190602001906111a3929190611346565b50600191506111bf565b8080600101915050611125565b600091505b509392505050565b600080600190505b6001805490508111151561128e578560008083815260200190815260200160002060010154141561128157846000808381526020019081526020016000206002019080519060200190611223929190611346565b5083600080838152602001908152602001600020600701908051906020019061124d929190611346565b50826000808381526020019081526020016000206008019080519060200190611277929190611346565b5060019150611293565b80806001019150506111cf565b600091505b50949350505050565b600080600190505b60018054905081111515611339578460008083815260200190815260200160002060010154141561132c578360008083815260200190815260200160002060070190805190602001906112f8929190611346565b50826000808381526020019081526020016000206008019080519060200190611322929190611346565b506001915061133e565b80806001019150506112a4565b600091505b509392505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061138757805160ff19168380011785556113b5565b828001600101855582156113b5579182015b828111156113b4578251825591602001919060010190611399565b5b5090506113c291906113c6565b5090565b6113e891905b808211156113e45760008160009055506001016113cc565b5090565b905600a165627a7a72305820a3d6fce14842ad3e14196910d5dea7aca611532a58350d382fcd327f60744bf10029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"holder\",\"type\":\"string\"},{\"name\":\"logistics\",\"type\":\"string\"},{\"name\":\"reno\",\"type\":\"uint256\"},{\"name\":\"valMoney\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"setWarehouseRecipt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getWarehouseRecipt\",\"outputs\":[{\"name\":\"no\",\"type\":\"uint256\"},{\"name\":\"ID\",\"type\":\"uint256\"},{\"name\":\"holder\",\"type\":\"string\"},{\"name\":\"logistics\",\"type\":\"string\"},{\"name\":\"reno\",\"type\":\"uint256\"},{\"name\":\"valMoney\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"holder\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"updateWarehouseReciptHolder\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"holder\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"updateWarehouseReciptStatusAndHolder\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"updateWarehouseReciptStatus\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"holder\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"logistics\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"reno\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"valMoney\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"status\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"updateTime\",\"type\":\"string\"}],\"name\":\"WarehouseReciptEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_SETWAREHOUSERECIPT = "setWarehouseRecipt";

    public static final String FUNC_GETWAREHOUSERECIPT = "getWarehouseRecipt";

    public static final String FUNC_UPDATEWAREHOUSERECIPTHOLDER = "updateWarehouseReciptHolder";

    public static final String FUNC_UPDATEWAREHOUSERECIPTSTATUSANDHOLDER = "updateWarehouseReciptStatusAndHolder";

    public static final String FUNC_UPDATEWAREHOUSERECIPTSTATUS = "updateWarehouseReciptStatus";

    public static final Event WAREHOUSERECIPTEVENT_EVENT = new Event("WarehouseReciptEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected WareHouseRecipt(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected WareHouseRecipt(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected WareHouseRecipt(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected WareHouseRecipt(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> setWarehouseRecipt(BigInteger id, String holder, String logistics, BigInteger reno, String valMoney, String addr, String status, String updateTime) {
        final Function function = new Function(
                FUNC_SETWAREHOUSERECIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(logistics), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(reno), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(valMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setWarehouseRecipt(BigInteger id, String holder, String logistics, BigInteger reno, String valMoney, String addr, String status, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETWAREHOUSERECIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(logistics), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(reno), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(valMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setWarehouseReciptSeq(BigInteger id, String holder, String logistics, BigInteger reno, String valMoney, String addr, String status, String updateTime) {
        final Function function = new Function(
                FUNC_SETWAREHOUSERECIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(logistics), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(reno), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(valMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple8<BigInteger, String, String, BigInteger, String, String, String, String> getSetWarehouseReciptInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETWAREHOUSERECIPT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple8<BigInteger, String, String, BigInteger, String, String, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (String) results.get(6).getValue(), 
                (String) results.get(7).getValue()
                );
    }

    public Tuple1<BigInteger> getSetWarehouseReciptOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SETWAREHOUSERECIPT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<Tuple9<BigInteger, BigInteger, String, String, BigInteger, String, String, String, String>> getWarehouseRecipt(BigInteger id) {
        final Function function = new Function(FUNC_GETWAREHOUSERECIPT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple9<BigInteger, BigInteger, String, String, BigInteger, String, String, String, String>>(
                new Callable<Tuple9<BigInteger, BigInteger, String, String, BigInteger, String, String, String, String>>() {
                    @Override
                    public Tuple9<BigInteger, BigInteger, String, String, BigInteger, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, BigInteger, String, String, BigInteger, String, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (String) results.get(6).getValue(), 
                                (String) results.get(7).getValue(), 
                                (String) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> updateWarehouseReciptHolder(BigInteger id, String holder, String updateTime) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTHOLDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void updateWarehouseReciptHolder(BigInteger id, String holder, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTHOLDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String updateWarehouseReciptHolderSeq(BigInteger id, String holder, String updateTime) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTHOLDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, String, String> getUpdateWarehouseReciptHolderInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATEWAREHOUSERECIPTHOLDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getUpdateWarehouseReciptHolderOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPDATEWAREHOUSERECIPTHOLDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> updateWarehouseReciptStatusAndHolder(BigInteger id, String holder, String status, String updateTime) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTSTATUSANDHOLDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void updateWarehouseReciptStatusAndHolder(BigInteger id, String holder, String status, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTSTATUSANDHOLDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String updateWarehouseReciptStatusAndHolderSeq(BigInteger id, String holder, String status, String updateTime) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTSTATUSANDHOLDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(holder), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<BigInteger, String, String, String> getUpdateWarehouseReciptStatusAndHolderInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATEWAREHOUSERECIPTSTATUSANDHOLDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<BigInteger, String, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public Tuple1<Boolean> getUpdateWarehouseReciptStatusAndHolderOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPDATEWAREHOUSERECIPTSTATUSANDHOLDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> updateWarehouseReciptStatus(BigInteger id, String status, String updateTime) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void updateWarehouseReciptStatus(BigInteger id, String status, String updateTime, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String updateWarehouseReciptStatusSeq(BigInteger id, String status, String updateTime) {
        final Function function = new Function(
                FUNC_UPDATEWAREHOUSERECIPTSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(status), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(updateTime)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, String, String> getUpdateWarehouseReciptStatusInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATEWAREHOUSERECIPTSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getUpdateWarehouseReciptStatusOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPDATEWAREHOUSERECIPTSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public List<WarehouseReciptEventEventResponse> getWarehouseReciptEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WAREHOUSERECIPTEVENT_EVENT, transactionReceipt);
        ArrayList<WarehouseReciptEventEventResponse> responses = new ArrayList<WarehouseReciptEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WarehouseReciptEventEventResponse typedResponse = new WarehouseReciptEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.no = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.holder = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.logistics = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.reno = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.valMoney = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.updateTime = (String) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerWarehouseReciptEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(WAREHOUSERECIPTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerWarehouseReciptEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(WAREHOUSERECIPTEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static WareHouseRecipt load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new WareHouseRecipt(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static WareHouseRecipt load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new WareHouseRecipt(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static WareHouseRecipt load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new WareHouseRecipt(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static WareHouseRecipt load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new WareHouseRecipt(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<WareHouseRecipt> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(WareHouseRecipt.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<WareHouseRecipt> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(WareHouseRecipt.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<WareHouseRecipt> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(WareHouseRecipt.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<WareHouseRecipt> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(WareHouseRecipt.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class WarehouseReciptEventEventResponse {
        public Log log;

        public BigInteger no;

        public BigInteger id;

        public String holder;

        public String logistics;

        public BigInteger reno;

        public String valMoney;

        public String addr;

        public String status;

        public String updateTime;
    }
}
