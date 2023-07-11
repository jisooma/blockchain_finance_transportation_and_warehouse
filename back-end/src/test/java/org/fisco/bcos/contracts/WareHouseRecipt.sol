pragma solidity 0.4.25;

contract WareHouseRecipt {
    struct WarehouseRecipt{
        uint no;//no
        uint id;//对应数据库中的仓单id
        string holder;
        string logistics;
        uint reno;
        string valMoney;
        string addr;
        string status;
        string updateTime;
    }

    mapping(uint=>WarehouseRecipt) WarehouseRecipts;
    uint[] idLists;

    event WarehouseReciptEvent(uint no,uint id,string holder,string logistics,uint reno,string valMoney,string addr,string status,string updateTime);

    function setWarehouseRecipt(uint id,string holder,string logistics,uint reno,string valMoney,string addr,string status,string updateTime) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        WarehouseRecipts[no].no=no;
        WarehouseRecipts[no].id=id;
        WarehouseRecipts[no].holder=holder;
        WarehouseRecipts[no].logistics=logistics;
        WarehouseRecipts[no].reno=reno;
        WarehouseRecipts[no].valMoney=valMoney;
        WarehouseRecipts[no].addr = addr;
        WarehouseRecipts[no].status=status;
        WarehouseRecipts[no].updateTime=updateTime;
        emit WarehouseReciptEvent(no,id,holder,logistics,reno,valMoney,addr,status,updateTime);
        return no;
    }

    function getWarehouseRecipt(uint id)public view returns(uint no,uint ID,string holder,string logistics,uint reno,string valMoney,string addr,string status,string updateTime){
        for(uint i=1;i<=idLists.length;i++){
            if(WarehouseRecipts[i].id==id){
                no =  WarehouseRecipts[i].no;
                ID =  WarehouseRecipts[i].id;
                holder= WarehouseRecipts[i].holder;
                logistics= WarehouseRecipts[i].logistics;
                reno = WarehouseRecipts[i].reno;
                valMoney = WarehouseRecipts[i].valMoney;
                addr = WarehouseRecipts[i].addr;
                status = WarehouseRecipts[i].status;
                updateTime = WarehouseRecipts[i].updateTime;
            }
        }
    }
    function updateWarehouseReciptStatus(uint id,string status,string updateTime)public returns(bool){
        for(uint i=1;i<=idLists.length;i++){
            if(WarehouseRecipts[i].id==id){
                WarehouseRecipts[i].status=status;
                WarehouseRecipts[i].updateTime=updateTime;
                return true;
            }
        }
        return false;
    }
    function updateWarehouseReciptHolder(uint id,string holder,string updateTime)public returns(bool){
        for(uint i=1;i<=idLists.length;i++){
            if(WarehouseRecipts[i].id==id){
                WarehouseRecipts[i].holder=holder;
                WarehouseRecipts[i].updateTime=updateTime;
                return true;
            }
        }
        return false;
    }
    function updateWarehouseReciptStatusAndHolder(uint id,string holder,string status,string updateTime)public returns(bool){
        for(uint i=1;i<=idLists.length;i++){
            if(WarehouseRecipts[i].id==id){
                WarehouseRecipts[i].holder=holder;
                WarehouseRecipts[i].status=status;
                WarehouseRecipts[i].updateTime= updateTime;
                return true;
            }
        }
        return false;
    }
}
