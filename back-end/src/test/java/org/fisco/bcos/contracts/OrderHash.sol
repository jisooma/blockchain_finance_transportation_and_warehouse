pragma solidity ^0.4.0;

contract OrderHash {
    struct OrderHash{
        uint no;//no
        uint id;//对应数据库中的仓单id
        string hash;
        string time;
    }

    mapping(uint=>OrderHash) OrderHashes;
    uint[] idLists;

    event OrderHashEvent(uint no,uint id,string hash,string time);


    function setOrderHash(uint  id,string  hash,string time) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        OrderHashes[no].no=no;
        OrderHashes[no].id=id;
        OrderHashes[no].hash=hash;
        OrderHashes[no].time=time;
        emit OrderHashEvent(no,id,hash,time);
        return no;
    }

    function getOrderHash(uint id)public view returns(uint no,uint ID,string hash,string time){
        for(uint i=1;i<=idLists.length;i++){
            if((OrderHashes[i].id==id)){
                no = OrderHashes[i].no;
                ID = OrderHashes[i].id;
                hash=OrderHashes[i].hash;
                time=OrderHashes[i].time;
            }
        }
    }
}
