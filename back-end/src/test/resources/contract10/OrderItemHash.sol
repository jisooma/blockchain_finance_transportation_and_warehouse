pragma solidity ^0.4.0;

contract OrderItemHash {
    struct OrderItemHash{
        uint no;//no
        uint id;//对应数据库中的仓单id
        string hash;
        string time;
    }

    mapping(uint=>OrderItemHash) OrderItemHashes;
    uint[] idLists;

    event OrderItemHashEvent(uint no,uint id,string hash,string time);


    function setOrderItemHash(uint  id,string  hash,string time) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        OrderItemHashes[no].no=no;
        OrderItemHashes[no].id=id;
        OrderItemHashes[no].hash=hash;
        OrderItemHashes[no].time=time;
        emit OrderItemHashEvent(no,id,hash,time);
        return no;
    }

    function getOrderItemHash(uint id)public view returns(uint no,uint ID,string hash,string time){
        for(uint i=1;i<=idLists.length;i++){
            if((OrderItemHashes[i].id==id)){
                no = OrderItemHashes[i].no;
                ID = OrderItemHashes[i].id;
                hash=OrderItemHashes[i].hash;
                time=OrderItemHashes[i].time;
            }
        }
    }
}
