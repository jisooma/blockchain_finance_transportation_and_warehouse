pragma solidity ^0.4.0;

//银行部署智能合约，发行数字货币
contract MintCoin {
    address  public minter;
    mapping (address => string) public balances;

    event Sent(address from,address to, string amount);
    event Mint(address from,address to, string amount);
    constructor() public {
        minter = msg.sender;
    }
    modifier onlyBank{
        require(msg.sender == minter);
        _;
    }
    //使用同态加密  在后端先查出来接受者余额的密文，然后解密得到原来的余额，余额加上新增的数字货币。再将总额加密，发送到链上
    function tokenMint(address receiver, string amount) onlyBank public {
        balances[receiver] = amount;
    }
    //数字货币转账同理如此。
    function tokenSend(address receiver, string amount) public {
        balances[msg.sender] -= amount;
        balances[receiver] += amount;
        emit Sent(msg.sender, receiver, amount);
    }
    function checkBalance(address receiver) public returns(uint){
        return balances[receiver];
    }
}
