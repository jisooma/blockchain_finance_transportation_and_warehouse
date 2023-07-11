//pragma solidity ^0.4.0;
//
//contract digitalCash {
//    address  public minter;
//    struct account{
//        address addr;
//        string token;
//        uint nonce;
//    }
//    mapping(address=>account) public balance;
//    mapping (address => string) public balances;
//
//    event Sent(address from,address to, string amount,string token);
//    event Exchange(address from,address to, string amount);
//    event Mint(address from,address to, string amount);
//
//    constructor() public {
//        minter = msg.sender;
//    }
//    modifier onlyBank{
//        require(msg.sender == minter);
//        _;
//    }
//
//    //使用同态加密  在后端先查出来接受者余额的密文，余额密文加上新增的数字货币密文。发送到链上
//    function tokenMint(address bank,address receiver, string amount) onlyBank public {
//        balances[receiver].token = amount;
//        balances[receiver].nonce +=1;
//        emit Mint(bank,receiver,amount);
//    }
//
//    //数字货币转账同理如此。
//    //在后端先查出来发送者和接受者余额的密文，发送者余额密文减去转账的金额密文。接受者余额加上转账的金额密文。发送到链上
//    function tokenSend(address sender,address receiver,string amount1,string amount2,string amount) public {
//        balances[sender] = amount1;
//        balances[receiver] = amount2;
//        emit Sent(sender, receiver, amount);
//    }
//
//    //货币兑付同理如此
//    function tokenExchange(address bank,address payer,string amount,string token) public{
//        balances[payer] = amount;
//        emit Exchange(bank,payer,amount,token);
//    }
//    function checkBalance(address receiver) public returns(string){
//        return balances[receiver];
//    }
//}
