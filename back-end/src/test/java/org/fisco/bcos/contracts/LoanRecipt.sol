pragma solidity ^0.4.0;

contract LoanRecipt {
    struct loanRecipt{
        uint  ID;//账单ID
        address enterprise;
        address bank;
        uint loanID;//贷款申请ID
        string allMoney;//贷款金额
        string reMoney;//剩余金额
        string status;//状态：待还款，已还完。
        string updateTime;//更新时间
        string dueTime; //截止日期
    }

    mapping(uint=>loanRecipt) loanReciptMap;
    mapping(address=>uint[]) myLoanReciptList;
    uint[] loanReciptList;

    event createLoanReciptEvent(uint id,address enterprise,address bank,uint loanID,string allMoney,string reMoney,string status,string updateTime,string dueTime);

    function createLoanRecipt(uint loanID,address enterprise,address bank,string allMoney,string updateTime,string dueTime)public  returns(bool){
        uint no = loanReciptList.length+1;
        myLoanReciptList[enterprise].push(no);
        myLoanReciptList[bank].push(no);
        loanReciptList.push(no);
        loanReciptMap[no] = loanRecipt({
            ID:no,enterprise:enterprise,bank:bank,loanID:loanID,allMoney:allMoney,reMoney:allMoney,status:"待还款",updateTime:updateTime,dueTime:dueTime
            });
        emit createLoanReciptEvent(no,enterprise,bank,loanID,allMoney,allMoney,"待还款",updateTime,dueTime);
        return true;
    }
    //还款
    function RepayLoanRecipt(uint no,string money,string time)public returns(bool){
        loanReciptMap[no].status="已还完";
        loanReciptMap[no].reMoney=money;
        loanReciptMap[no].updateTime = time;
        return true;
    }
    //分批还款
    function updateLoanRecipt(uint no,string money,string time)public returns(bool){
        loanReciptMap[no].reMoney=loanReciptMap[no].money;
        loanReciptMap[no].updateTime = time;
        return true;
    }
    function getMyLoanList(address accNo) public view returns(uint []){
        return myLoanReciptList[accNo];
    }
    function getLoanDueTime(uint no) public view returns(string){
        return  loanReciptMap[no].dueTime;
    }
    function getLoanReciptByLoanId(uint loanID) public view returns(uint id){
        for(uint i=0;i<=loanReciptList.length;i++){
            if(loanReciptMap[i].loanID==loanID){
                return loanReciptMap[i].ID;
            }
        }
    }
    function getLoanReciptByID(uint no)public view returns(uint id,address enterprise,address bank,string allMoney,string reMoney ,string status ,string updateTime,string dueTime){
        id = no;
        enterprise = loanReciptMap[no].enterprise;
        bank =loanReciptMap[no].bank;
        allMoney= loanReciptMap[no].allMoney;
        reMoney = loanReciptMap[no].reMoney;
        status = loanReciptMap[no].status;
        updateTime = loanReciptMap[no].updateTime;
        dueTime  = loanReciptMap[no].dueTime;
    }
}
