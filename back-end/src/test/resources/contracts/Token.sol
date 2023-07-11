pragma solidity ^0.4.0;

//银行部署智能合约，发行数字货币
contract Token {
        address  public minter;
        mapping (address => string) public balances;

        event Sent(address from,address to, string amount);
        event Exchange(address from,address to, string amount);
        event Mint(address from,address to, string amount);
        constructor() public {
                minter = msg.sender;
        }
        modifier onlyBank{
                require(msg.sender == minter);
                _;
        }

        //使用同态加密  在后端先查出来接受者余额的密文，然后解密得到原来的余额，余额加上新增的数字货币。再将总额加密，发送到链上
        function tokenMint(address bank,address receiver, string amount) onlyBank public {
                balances[receiver] = amount;
                emit Mint(bank,receiver,amount);
        }

        //数字货币转账同理如此。
        //在后端先查出来发送者和接受者余额的密文，然后解密得到原来的余额，发送者余额减去转发的金额。接受者余额加上转发的金额。再将各自的总额加密，发送到链上
        function tokenSend(address sender,address receiver,string amount1,string amount2,string amount) public {
                balances[sender] = amount1;
                balances[receiver] = amount2;
                emit Sent(sender, receiver, amount);
        }

        //代币兑付同理如此
        function tokenExchange(address bank,address payer,string amount) public{
                balances[payer] = amount;
                emit Exchange(bank,payer,amount);
        }
        function checkBalance(address receiver) public returns(string){
                return balances[receiver];
        }

}