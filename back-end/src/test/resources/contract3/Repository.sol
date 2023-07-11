pragma solidity ^0.4.0;

contract Repository {
   struct repository{
       uint no;
       uint reNo;//编号
       string name;//名字
       string specification;//规格
       uint number;//数量
       string addr;//产地
       string holder;//申请企业
       string logistics;//申请企业
       string updateTime;//申请时间
       string status;//申请状态
       string remark;//备注
}
    mapping (uint=>repository) repositoryMap;
    mapping(address=>uint[]) myRepositoryList;
    uint[] repositoryIdList;

    //记录库存的变化
    event RepositoryEvent(uint id,address holder,address transport,string text,string addr,string valMoney,string status,string updateTime);
    //上链
    function createRepository(uint reno,string name,string specification,uint number,string addr,address enterprise,address transport,string updateTime,string status,string remark)public  returns(bool){
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
    //状态更新
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
