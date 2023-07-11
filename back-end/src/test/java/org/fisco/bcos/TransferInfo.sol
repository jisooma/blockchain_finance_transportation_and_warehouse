pragma solidity ^0.4.0;

contract TransferInfo {


    struct TransferInfo{ //5
        uint traNo;           	//转账编号
        address payer;           //付款方
        address payee;           //收款方
        string money;             //转账金额
        string updateTime;      //转账时间
    }

    mapping(uint => TransferInfo) transferInfoMap; //存储转账的mapping

    mapping(address => uint[]) myTransferInfoIdList; //存储与自己有关的转账的mapping

    uint[] transferInfoIdList;//存储转账的key

    event setTransfer(uint id,address payer,address payee,string money,string time);

    //1.发起转账
    //输入4：付款方、收款方、金额、时间
    function setTransferInfo(address payer,address payee,string money,string time) public {
        uint traNo=transferInfoIdList.length+1;
        transferInfoIdList.push(traNo);
        myTransferInfoIdList[payer].push(traNo);
        myTransferInfoIdList[payee].push(traNo);
        transferInfoMap[traNo] = TransferInfo(
            {traNo:traNo,payer:payer,payee:payee,money:money,updateTime:time});
    }
    //查找与用户有关的转账编号数组
    function getMyTransferInfoIdList(address addr) public view returns(uint[]) {
        return myTransferInfoIdList[addr];
    }
    //展示一条记录
    function displayTransferInfo(uint no) public view returns (uint id,address payer,address payee,string money,string updateTime){
        id=transferInfoMap[no].traNo;
        payer=transferInfoMap[no].payer;
        payee=transferInfoMap[no].payee;
        money=transferInfoMap[no].money;
        updateTime=transferInfoMap[no].updateTime;
    }

}
