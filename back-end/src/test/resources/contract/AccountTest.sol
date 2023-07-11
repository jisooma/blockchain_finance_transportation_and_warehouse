pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

contract AccountTest{
    //-----------------------------------------------//
    //-------------账号、转账功能---------------------//
    //------------------------------------------------//

    //账号信息
    struct Account { //
        uint accNo;			//账户号
        uint accType;         //公司类型：银行0、企业1、物流2
        string name;            //公司名，唯一
        string bal;				//余额
        uint status;			//用户状态:正常经营0/破产1
        address addr;      //账号地址
    }
    //    //账单信息
    //    struct TransferInfo{ //5
    //        uint traNo;           	//转账编号
    //        address payer;           //付款方
    //        address payee;           //收款方
    //        string  money;             //转账金额
    //        string updateTime;      //转账时间
    //    }

    mapping(uint => Account) accountMap; //存储账户的mapping
    uint[] accountIdList;//存储账户的编号
    //---------------------------------------------1.账号管理-----------------------------------------------------------//
    //1.注册账号
    //输入3：账号、企业类型、企业名称
    event createAccountEvent(uint no,uint accType,string name,string bal,uint status,address addr);
    function createAccount(uint accType,string name,string bal,address addr) public returns(bool){
        uint no = accountIdList.length+1;
        //判断账号是不是存在。是否正常营业且名字重复
        if (is_register(name)) {
            return false;
        }
        //增加信息
        accountMap[no] = Account(
            {accNo:no, accType:accType,name:name,bal:bal,status:uint(0),addr:addr});
        accountIdList.push(no);
        emit createAccountEvent(no,accType,name,bal,uint(0),addr);
        return true;
    }
    function is_register(string name)public returns(bool){
        for(uint i=0;i<=accountIdList.length;i++){
            if((keccak256(abi.encodePacked(accountMap[i].name))==keccak256(abi.encodePacked(name)))&&(accountMap[i].status==0)){
                return true;
            }
        }
        return false;
    }
    //登录成功之后返回个人信息，显示在页面上
    function login(string accName)public returns(uint id,uint Type,string name,string bal,uint status,address  addr){

        for(uint i=0;i<=accountIdList.length;i++){
            //判断名字是不是存在。是否正常营业。
            if((keccak256(abi.encodePacked(accountMap[i].name))==keccak256(abi.encodePacked(accName)))&&(accountMap[i].status==0)){
                id = accountMap[i].accNo;
                Type = accountMap[i].accType;
                name = accountMap[i].name;
                bal = accountMap[i].bal;
                status =  accountMap[i].status;
                addr = accountMap[i].addr;
            }
        }

    }
    //根据状态查找账户信息
    function displayAccountByStatus(uint accStatus)public view returns(uint[] id,uint[]Type,string[]name,string[] bal,uint[] status,address[] addr){
        uint count=0;

        for(uint i=1;i<=accountIdList.length;i++){
            if(accStatus==accountMap[i].status){
                id[count] = accountMap[i].accNo;
                Type[count]=accountMap[i].accType;
                name[count] = accountMap[i].name;
                bal[count] = accountMap[i].bal;
                status[count] =  accountMap[i].status;
                addr[count]= accountMap[i].addr;
                count++;
            }
        }
    }



    //根据类型查找账户信息
    function displayAccountByType(uint accType)public  returns(uint[] id,uint[]Type,string[]name,string[] bal,uint[] status,address[] addr){
        uint count=0;

        for(uint i=1;i<=accountIdList.length;i++){
            if(accType==accountMap[i].accType){
                id[count] = accountMap[i].accNo;
                Type[count]=accountMap[i].accType;
                name[count] = accountMap[i].name;
                bal[count] = accountMap[i].bal;
                status[count] =  accountMap[i].status;
                addr[count]= accountMap[i].addr;
                count++;
            }
        }
    }
}