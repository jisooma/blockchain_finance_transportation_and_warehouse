pragma solidity 0.4.25;


contract PaillierPrecompiled{
    function paillierAdd(string cipher1, string cipher2) public view returns(string);
}

contract digitalCurrency{
    //同态加密接口
    PaillierPrecompiled paillierPrecompiled;
    //合约发布者
    address public minter;
    //发行的全部金额
    string totalMoney;
    //一个地址对应的账户余额。
    mapping (address => string) public balances;
    //账号信息
    struct Account { //
        uint accNo;			//账户号
        string accType;         //公司类型：银行0、供应链企业1、物流2
        string name;            //公司名，唯一
        string bal;				//余额
        string status;			//用户状态:正常经营0/破产1
        uint nonce;          //发送交易计数
        address addr;      //账号地址
    }
    //账单
    struct TransferInfo{
        uint traNo;           	//转账编号
        address payer;           //付款方
        address payee;           //收款方
        string money;             //转账金额，密文
        string Type;             //转账类型：借贷转账，业务转账，代币兑付
        string updateTime;      //转账时间
    }
    mapping(address => Account) accountMap; //存储账户的mapping
    mapping(uint=>address) NoToAddr;//存储账户id对应的账户地址
    mapping(string=>uint) accountNo;//存储账号名字对应的账户id
    uint[] accountIdList;//存储账户的编号

    mapping(uint => TransferInfo) transferInfoMap; //存储转账的mapping
    //存储与自己有关的转账的mapping，当监管机构进行审查时，可以进行根据账户地址，在区块链查询到所有的该地址的所有转账信息的id，对监管的内容进行更新。
    mapping(address => uint[]) myTransferInfoIdList; //有关我的转账
    mapping(address => uint[]) TransferInfoFromMy; //我发起的转账
    uint[] transferInfoIdList;//存储转账的key


    event Sent(address from,address to, string amount);
    event Exchange(address from,address to, string amount);
    event Mint(address from,address to, string amount);

    //构造函数
    constructor() public {
        minter = msg.sender;
        paillierPrecompiled = PaillierPrecompiled(0x5003);
    }

    modifier onlyBank{
        require(msg.sender == minter);
        _;
    }

    //转账交易
    function tokenSend(address sender,address receiver,string amount1,string amount2,string Type,string time) public {
        //账户余额模型：只有在余额搞错的情况下，才会出现双花
        //使用nonce是为了确保所有的节点计算的是相同的余额和正确的交易，等同于防止了“双重支付/重放攻击”，
        //但是因为不像比特币使用UTXO机制，因此以太坊只有在错误计算账户的余额时候才会发生“双重支付/重放攻击”。
        //防止双花：利用账户转账记录的数量与账户发送交易的交易的数量对比
        if(accountMap[sender].nonce==myTransferInfoIdList[sender].length){
            accountMap[sender].nonce +=1;
        }
        //密文相加
        balances[sender]=paillierPrecompiled.paillierAdd(balances[sender],amount2);
        balances[receiver]=paillierPrecompiled.paillierAdd(balances[receiver],amount1);
        uint traNo=transferInfoIdList.length+1;

        //增加信息
        myTransferInfoIdList[sender].push(traNo);
        myTransferInfoIdList[receiver].push(traNo);
        TransferInfoFromMy[sender].push(traNo);
        transferInfoIdList.push(traNo);

        //生成账单
        transferInfoMap[traNo] = TransferInfo(
            {traNo:traNo,payer:sender,payee:receiver,money:amount1,Type:Type,updateTime:time});

        //账户余额变化
        accountMap[sender].bal=paillierPrecompiled.paillierAdd(balances[sender],amount2);
        accountMap[receiver].bal=paillierPrecompiled.paillierAdd(balances[receiver],amount1);
        emit Sent(sender, receiver, amount1);
    }

    //
    function checkBalance(address receiver) public returns(string){
        return balances[receiver];
    }

    //---------------------------------------------1.账号管理-----------------------------------------------------------//
    //1.注册账号
    //输入3：账号、企业类型、企业名称
    event createAccountEvent(uint no,string accType,string name,string bal,string status,address addr);

    function createAccount(address bank,string accType,string name,string bal,address addr) public returns(bool){
        uint no = accountIdList.length+1;
        //判断账号是不是存在。是否正常营业且名字重复
        if (is_register(name)) {
            return false;
        }

        //增加信息
        accountMap[addr] = Account(
            {accNo:no, accType:accType,name:name,bal:bal,status:"合法经营",nonce:uint(0),addr:addr});
        accountIdList.push(no);
        accountNo[name]=no;
        NoToAddr[no]=addr;

        //发放数字货币到注册方的账户
        //注册时，根据注册金额，发放对应比率的数字货币到对应的账户
        balances[addr] = bal;
//        totalMoney = paillierPrecompiled.paillierAdd(totalMoney,bal);
//        emit Mint(bank,addr,bal);
        emit createAccountEvent(no,accType,name,bal,"合法经营",addr);
        return true;
    }
    function is_register(string name)public returns(bool){
        for(uint i=0;i<=accountIdList.length;i++){
            if((keccak256(abi.encodePacked(accountMap[NoToAddr[i]].name))==keccak256(abi.encodePacked(name)))&&((keccak256(abi.encodePacked(accountMap[NoToAddr[i]].status))==keccak256(abi.encodePacked(""))))){
                return true;
            }
        }
        return false;
    }
    //从链上查询企业信息
    function findByName(string accName)public returns(uint id,string Type,string name,string bal,string status,uint nonce,address  addr){
        uint i = accountNo[accName];
        id = accountMap[NoToAddr[i]].accNo;
        Type = accountMap[NoToAddr[i]].accType;
        name = accountMap[NoToAddr[i]].name;
        bal = accountMap[NoToAddr[i]].bal;
        status =  accountMap[NoToAddr[i]].status;
        nonce = accountMap[NoToAddr[i]].nonce;
        addr = accountMap[NoToAddr[i]].addr;
    }
    //从链上查询企业信息
    function findByAddr(address addr1)public returns(uint id,string Type,string name,string bal,string status,uint nonce,address addr){
        //        if((keccak256(abi.encodePacked(accountMap[addr1].name))==keccak256(abi.encodePacked(accName)))){
        id = accountMap[addr1].accNo;
        Type = accountMap[addr1].accType;
        name = accountMap[addr1].name;
        bal = accountMap[addr1].bal;
        status =  accountMap[addr1].status;
        nonce = accountMap[addr1].nonce;
        addr = accountMap[addr1].addr;
        //        }
    }
    //登录成功之后返回个人信息，显示在页面上
    function login(string accName)public returns(uint id,string Type,string name,string bal,string status,address addr){

        for(uint i=0;i<=accountIdList.length;i++){
            //判断名字是不是存在。是否正常营业。
            if((keccak256(abi.encodePacked(accountMap[NoToAddr[i]].name))==keccak256(abi.encodePacked(accName)))&&((keccak256(abi.encodePacked(accountMap[NoToAddr[i]].status))==keccak256(abi.encodePacked(""))))){
                id = accountMap[NoToAddr[i]].accNo;
                Type = accountMap[NoToAddr[i]].accType;
                name = accountMap[NoToAddr[i]].name;
                bal = accountMap[NoToAddr[i]].bal;
                status =  accountMap[NoToAddr[i]].status;
                addr = accountMap[NoToAddr[i]].addr;
            }
        }
    }
    //查找与用户有关的转账编号数组
    function getMyTransferInfoIdList(address addr) public view returns(uint[]) {
        return myTransferInfoIdList[addr];
    }
    //展示一条记录
    function displayTransferInfo(uint no) public view returns (uint id,address payer,address payee,string money,string Type,string updateTime){
        id=transferInfoMap[no].traNo;
        payer=transferInfoMap[no].payer;
        payee=transferInfoMap[no].payee;
        money=transferInfoMap[no].money;
        Type = transferInfoMap[no].Type;
        updateTime=transferInfoMap[no].updateTime;
    }
}
