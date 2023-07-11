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
import org.fisco.bcos.web3j.abi.datatypes.Bool;
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
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
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
public class AccountTest extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611634806100206000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633a7d280c146100725780636735b5bb146100b45780637ded60cd146100f1578063bc30bbad1461012e578063fa1e3ffb14610170575b600080fd5b34801561007e57600080fd5b506100996004803603610094919081019061109a565b6101b2565b6040516100ab969594939291906113fa565b60405180910390f35b3480156100c057600080fd5b506100db60048036036100d69190810190611104565b6105ae565b6040516100e891906113df565b60405180910390f35b3480156100fd57600080fd5b506101186004803603610113919081019061109a565b610747565b60405161012591906113df565b60405180910390f35b34801561013a57600080fd5b50610155600480360361015091908101906110db565b610965565b60405161016796959493929190611354565b60405180910390f35b34801561017c57600080fd5b50610197600480360361019291908101906110db565b610c6e565b6040516101a996959493929190611354565b60405180910390f35b60008060608060008060008090505b600180549050811115156105a457876040516020018082805190602001908083835b60208310151561020857805182526020820191506020810190506020830392506101e3565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b602083101515610271578051825260208201915060208101905060208303925061024c565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008083815260200190815260200160002060020160405160200180828054600181600116156101000203166002900480156103155780601f106102f3576101008083540402835291820191610315565b820191906000526020600020905b815481529060010190602001808311610301575b50509150506040516020818303038152906040526040518082805190602001908083835b60208310151561035e5780518252602082019150602081019050602083039250610339565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161480156103b15750600080600083815260200190815260200160002060040154145b15610597576000808281526020019081526020016000206000015496506000808281526020019081526020016000206001015495506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561048f5780601f106104645761010080835404028352916020019161048f565b820191906000526020600020905b81548152906001019060200180831161047257829003601f168201915b505050505094506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561053f5780601f106105145761010080835404028352916020019161053f565b820191906000526020600020905b81548152906001019060200180831161052257829003601f168201915b5050505050935060008082815260200190815260200160002060040154925060008082815260200190815260200160002060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691505b80806001019150506101c1565b5091939550919395565b600080600180805490500190506105c485610747565b156105d2576000915061073e565b60c060405190810160405280828152602001878152602001868152602001858152602001600081526020018473ffffffffffffffffffffffffffffffffffffffff1681525060008083815260200190815260200160002060008201518160000155602082015181600101556040820151816002019080519060200190610659929190610f77565b506060820151816003019080519060200190610676929190610f77565b506080820151816004015560a08201518160050160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505060018190806001815401808255809150509060018203906000526020600020016000909192909190915055507f3988a5d79489b1d16c854c5d2be9af56953fed8f463098c338381a256e88906181878787600088604051610731969594939291906113fa565b60405180910390a1600191505b50949350505050565b600080600090505b6001805490508111151561095a57826040516020018082805190602001908083835b6020831015156107965780518252602082019150602081019050602083039250610771565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040526040518082805190602001908083835b6020831015156107ff57805182526020820191506020810190506020830392506107da565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660008083815260200190815260200160002060020160405160200180828054600181600116156101000203166002900480156108a35780601f106108815761010080835404028352918201916108a3565b820191906000526020600020905b81548152906001019060200180831161088f575b50509150506040516020818303038152906040526040518082805190602001908083835b6020831015156108ec57805182526020820191506020810190506020830392506108c7565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191614801561093f5750600080600083815260200190815260200160002060040154145b1561094d576001915061095f565b808060010191505061074f565b600091505b50919050565b60608060608060608060008060009150600190505b60018054905081111515610c635760008082815260200190815260200160002060040154891415610c56576000808281526020019081526020016000206000015488838151811015156109c957fe5b90602001906020020181815250506000808281526020019081526020016000206001015487838151811015156109fb57fe5b90602001906020020181815250506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ab25780601f10610a8757610100808354040283529160200191610ab2565b820191906000526020600020905b815481529060010190602001808311610a9557829003601f168201915b50505050508683815181101515610ac557fe5b906020019060200201819052506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b7b5780601f10610b5057610100808354040283529160200191610b7b565b820191906000526020600020905b815481529060010190602001808311610b5e57829003601f168201915b50505050508583815181101515610b8e57fe5b90602001906020020181905250600080828152602001908152602001600020600401548483815181101515610bbf57fe5b906020019060200201818152505060008082815260200190815260200160002060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168383815181101515610c1157fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505081806001019250505b808060010191505061097a565b505091939550919395565b60608060608060608060008060009150600190505b60018054905081111515610f6c5760008082815260200190815260200160002060010154891415610f5f57600080828152602001908152602001600020600001548883815181101515610cd257fe5b9060200190602002018181525050600080828152602001908152602001600020600101548783815181101515610d0457fe5b90602001906020020181815250506000808281526020019081526020016000206002018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610dbb5780601f10610d9057610100808354040283529160200191610dbb565b820191906000526020600020905b815481529060010190602001808311610d9e57829003601f168201915b50505050508683815181101515610dce57fe5b906020019060200201819052506000808281526020019081526020016000206003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e845780601f10610e5957610100808354040283529160200191610e84565b820191906000526020600020905b815481529060010190602001808311610e6757829003601f168201915b50505050508583815181101515610e9757fe5b90602001906020020181905250600080828152602001908152602001600020600401548483815181101515610ec857fe5b906020019060200201818152505060008082815260200190815260200160002060050160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168383815181101515610f1a57fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505081806001019250505b8080600101915050610c83565b505091939550919395565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610fb857805160ff1916838001178555610fe6565b82800160010185558215610fe6579182015b82811115610fe55782518255916020019190600101","90610fca565b5b509050610ff39190610ff7565b5090565b61101991905b80821115611015576000816000905550600101610ffd565b5090565b90565b6000611028823561157d565b905092915050565b600082601f830112151561104357600080fd5b813561105661105182611496565b611469565b9150808252602083016020830185838301111561107257600080fd5b61107d8382846115a7565b50505092915050565b6000611092823561159d565b905092915050565b6000602082840312156110ac57600080fd5b600082013567ffffffffffffffff8111156110c657600080fd5b6110d284828501611030565b91505092915050565b6000602082840312156110ed57600080fd5b60006110fb84828501611086565b91505092915050565b6000806000806080858703121561111a57600080fd5b600061112887828801611086565b945050602085013567ffffffffffffffff81111561114557600080fd5b61115187828801611030565b935050604085013567ffffffffffffffff81111561116e57600080fd5b61117a87828801611030565b925050606061118b8782880161101c565b91505092959194509250565b6111a081611547565b82525050565b60006111b1826114e9565b8084526020840193506111c3836114c2565b60005b828110156111f5576111d9868351611197565b6111e282611520565b91506020860195506001810190506111c6565b50849250505092915050565b600061120c826114f4565b80845260208401935083602082028501611225856114cf565b60005b8481101561125e57838303885261124083835161130f565b925061124b8261152d565b9150602088019750600181019050611228565b508196508694505050505092915050565b600061127a826114ff565b80845260208401935061128c836114dc565b60005b828110156112be576112a2868351611345565b6112ab8261153a565b915060208601955060018101905061128f565b50849250505092915050565b6112d381611567565b82525050565b60006112e482611515565b8084526112f88160208601602086016115b6565b611301816115e9565b602085010191505092915050565b600061131a8261150a565b80845261132e8160208601602086016115b6565b611337816115e9565b602085010191505092915050565b61134e81611573565b82525050565b600060c082019050818103600083015261136e818961126f565b90508181036020830152611382818861126f565b905081810360408301526113968187611201565b905081810360608301526113aa8186611201565b905081810360808301526113be818561126f565b905081810360a08301526113d281846111a6565b9050979650505050505050565b60006020820190506113f460008301846112ca565b92915050565b600060c08201905061140f6000830189611345565b61141c6020830188611345565b818103604083015261142e81876112d9565b9050818103606083015261144281866112d9565b90506114516080830185611345565b61145e60a0830184611197565b979650505050505050565b6000604051905081810181811067ffffffffffffffff8211171561148c57600080fd5b8060405250919050565b600067ffffffffffffffff8211156114ad57600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b6000602082019050919050565b6000602082019050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008115159050919050565b6000819050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b82818337600083830152505050565b60005b838110156115d45780820151818401526020810190506115b9565b838111156115e3576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a7230582001d1cefbadd416c51a6ae2fa1193a2f2dcec76c7f72ab5ae16528220c50a5f5c6c6578706572696d656e74616cf50037"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"accName\",\"type\":\"string\"}],\"name\":\"login\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256\"},{\"name\":\"Type\",\"type\":\"uint256\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"bal\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"uint256\"},{\"name\":\"addr\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accType\",\"type\":\"uint256\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"bal\",\"type\":\"string\"},{\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"createAccount\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"is_register\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"accStatus\",\"type\":\"uint256\"}],\"name\":\"displayAccountByStatus\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256[]\"},{\"name\":\"Type\",\"type\":\"uint256[]\"},{\"name\":\"name\",\"type\":\"string[]\"},{\"name\":\"bal\",\"type\":\"string[]\"},{\"name\":\"status\",\"type\":\"uint256[]\"},{\"name\":\"addr\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"accType\",\"type\":\"uint256\"}],\"name\":\"displayAccountByType\",\"outputs\":[{\"name\":\"id\",\"type\":\"uint256[]\"},{\"name\":\"Type\",\"type\":\"uint256[]\"},{\"name\":\"name\",\"type\":\"string[]\"},{\"name\":\"bal\",\"type\":\"string[]\"},{\"name\":\"status\",\"type\":\"uint256[]\"},{\"name\":\"addr\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"no\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"accType\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"name\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"bal\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"status\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"createAccountEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_LOGIN = "login";

    public static final String FUNC_CREATEACCOUNT = "createAccount";

    public static final String FUNC_IS_REGISTER = "is_register";

    public static final String FUNC_DISPLAYACCOUNTBYSTATUS = "displayAccountByStatus";

    public static final String FUNC_DISPLAYACCOUNTBYTYPE = "displayAccountByType";

    public static final Event CREATEACCOUNTEVENT_EVENT = new Event("createAccountEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected AccountTest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AccountTest(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AccountTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AccountTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> login(String accName) {
        final Function function = new Function(
                FUNC_LOGIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(accName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void login(String accName, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_LOGIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(accName)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String loginSeq(String accName) {
        final Function function = new Function(
                FUNC_LOGIN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(accName)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getLoginInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LOGIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple6<BigInteger, BigInteger, String, String, BigInteger, String> getLoginOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_LOGIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple6<BigInteger, BigInteger, String, String, BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (String) results.get(5).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> createAccount(BigInteger accType, String name, String bal, String addr) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accType), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(bal), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void createAccount(BigInteger accType, String name, String bal, String addr, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accType), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(bal), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createAccountSeq(BigInteger accType, String name, String bal, String addr) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accType), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(bal), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<BigInteger, String, String, String> getCreateAccountInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<BigInteger, String, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public Tuple1<Boolean> getCreateAccountOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> is_register(String name) {
        final Function function = new Function(
                FUNC_IS_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void is_register(String name, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_IS_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String is_registerSeq(String name) {
        final Function function = new Function(
                FUNC_IS_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getIs_registerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_IS_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getIs_registerOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_IS_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>>> displayAccountByStatus(BigInteger accStatus) {
        final Function function = new Function(FUNC_DISPLAYACCOUNTBYSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accStatus)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>>>(
                new Callable<Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>>>() {
                    @Override
                    public Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Utf8String>) results.get(2).getValue()), 
                                convertToNative((List<Utf8String>) results.get(3).getValue()), 
                                convertToNative((List<Uint256>) results.get(4).getValue()), 
                                convertToNative((List<Address>) results.get(5).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> displayAccountByType(BigInteger accType) {
        final Function function = new Function(
                FUNC_DISPLAYACCOUNTBYTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void displayAccountByType(BigInteger accType, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DISPLAYACCOUNTBYTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accType)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String displayAccountByTypeSeq(BigInteger accType) {
        final Function function = new Function(
                FUNC_DISPLAYACCOUNTBYTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(accType)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getDisplayAccountByTypeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DISPLAYACCOUNTBYTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>> getDisplayAccountByTypeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_DISPLAYACCOUNTBYTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple6<List<BigInteger>, List<BigInteger>, List<String>, List<String>, List<BigInteger>, List<String>>(

                convertToNative((List<Uint256>) results.get(0).getValue()), 
                convertToNative((List<Uint256>) results.get(1).getValue()), 
                convertToNative((List<Utf8String>) results.get(2).getValue()), 
                convertToNative((List<Utf8String>) results.get(3).getValue()), 
                convertToNative((List<Uint256>) results.get(4).getValue()), 
                convertToNative((List<Address>) results.get(5).getValue())
                );
    }

    public List<CreateAccountEventEventResponse> getCreateAccountEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEACCOUNTEVENT_EVENT, transactionReceipt);
        ArrayList<CreateAccountEventEventResponse> responses = new ArrayList<CreateAccountEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateAccountEventEventResponse typedResponse = new CreateAccountEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.no = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.accType = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.bal = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
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
    public static AccountTest load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountTest(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AccountTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccountTest(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AccountTest load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AccountTest(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AccountTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AccountTest(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AccountTest> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountTest.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountTest> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountTest.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AccountTest> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AccountTest.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AccountTest> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccountTest.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class CreateAccountEventEventResponse {
        public Log log;

        public BigInteger no;

        public BigInteger accType;

        public String name;

        public String bal;

        public BigInteger status;

        public String addr;
    }
}
