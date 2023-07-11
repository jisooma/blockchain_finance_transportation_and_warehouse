pragma solidity ^0.4.0;

contract PaillierPrecompiled{
    function paillierAdd(string cipher1, string cipher2) public constant returns(string);
}
contract digitalCash {
    PaillierPrecompiled paillierPrecompiled;
    address  public minter;
    mapping (address => string) public balances;

    event Sent(address from,address to, string amount,string token);
    event Exchange(address from,address to, string amount);
    event Mint(address from,address to, string amount);

    constructor() public {
        minter = msg.sender;
        paillierPrecompiled = PaillierPrecompiled(0x5003);

    }
    modifier onlyBank{
        require(msg.sender == minter);
        _;
    }
    //使用同态加密  在后端先查出来接受者余额的密文，余额密文加上新增的数字货币密文。发送到链上
    function tokenMint(address bank,address receiver, string amount) onlyBank public {
        balances[receiver] = amount;
        emit Mint(bank,receiver,amount);
    }

    //数字货币转账同理如此。
    //在后端先查出来发送者和接受者余额的密文，发送者余额密文减去转账的金额密文。接受者余额加上转账的金额密文。发送到链
    //amount1:sender的余额
   // amount2：receiver的余额
    //amount：amount转账的金额
    function tokenSend(address sender,address receiver,string amount1,string amount2,string amount) public {
//        balances[sender] = amount1;
//        balances[receiver] = amount2;
         paillierPrecompiled.paillierAdd(balances[sender],amount);
         paillierPrecompiled.paillierAdd(balances[receiver],amount);
        emit Sent(sender, receiver, amount);
    }

    //货币兑付同理如此
    function tokenExchange(address bank,address payer,string amount,string token) public{
        balances[payer] = amount;
        emit Exchange(bank,payer,amount,token);
    }
    function checkBalance(address receiver) public returns(string){
        return balances[receiver];
    }
}
