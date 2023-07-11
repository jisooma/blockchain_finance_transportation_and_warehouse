pragma solidity ^0.4.0;

contract Respository {
    //--------------------------------仓单--------------------------//
    //-----------------------------------------------------------------//
    struct repository{
        uint No;//编号
        address holder;//货权拥有人
        address transport;//物流企业
        string text;//存货内容
        string addr;//入库地址
        string valMoney;//仓单价值
        string status;//库存状态：入库，出库。
        string updateTime;//更新时间
    }
    mapping (uint=>repository) repositoryMap;
    mapping(address=>uint[]) myRepositoryList;
    uint[] repositoryIdList;

    //记录库存的变化
    event RepositoryEvent(uint id,address holder,address transport,string text,string addr,string valMoney,string status,string updateTime);

    //上链
    function createRepository(address enterprise,address transport,string text,string addr,string valMoney,string updateTime)public  returns(bool){
        uint no = repositoryIdList.length+1;
        myRepositoryList[enterprise].push(no);
        myRepositoryList[transport].push(no);
        repositoryIdList.push(no);
        repositoryMap[no] = repository({
            No:no,holder:enterprise,transport:transport,text:text,addr:addr,valMoney:valMoney,status:"入库",updateTime:updateTime
            });
        emit RepositoryEvent(no,enterprise,transport,text,addr,valMoney,"入库",updateTime);
        return true;
    }
    //仓单所有者更新
    function updateRepositoryHolder(uint no,address holder,string updateTime)public view returns(
        bool ){
        repositoryMap[no].holder=holder;
        updateTime = repositoryMap[no].updateTime;
        emit RepositoryEvent(no,holder,repositoryMap[no].transport,repositoryMap[no].text,repositoryMap[no].addr,repositoryMap[no].valMoney,repositoryMap[no].status,updateTime);
        return true;
    }
    //仓单状态更新
    function updateRepositoryStatus(uint no,string status,string updateTime)public view returns(bool ){
        status = repositoryMap[no].status;
        updateTime = repositoryMap[no].updateTime;
        emit RepositoryEvent(no,repositoryMap[no].holder,repositoryMap[no].transport,repositoryMap[no].text,repositoryMap[no].addr,repositoryMap[no].valMoney,status,updateTime);
        return true;
    }
    function getMyrepository(address accNo)public returns(uint[]){
        return myRepositoryList[accNo];
    }
    function displayRepository(uint no)public view returns(uint id,address holder,address transport,string text,string addr,string valMoney,string status,string updateTime){
        id = no;
        holder = repositoryMap[no].holder;
        transport =repositoryMap[no].transport;
        text = repositoryMap[no].text;
        addr = repositoryMap[no].addr;
        valMoney= repositoryMap[no].valMoney;
        status = repositoryMap[no].status;
        updateTime = repositoryMap[no].updateTime;
    }

}
