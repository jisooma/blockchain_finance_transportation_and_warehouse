pragma solidity 0.4.25;

contract WarehouseReciptHash{
    struct warehouseReciptHash{
        uint no;//no
        uint id;//对应数据库中的仓单id
        string hash;
        string time;
    }

    mapping(uint=>warehouseReciptHash) warehouseReciptHashes;
    uint[] idLists;

    event warehouseReciptHashEvent(uint no,uint id,string hash,string time);


    function setWarehouseReciptHash(uint  id,string  hash,string time) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        warehouseReciptHashes[no].no=no;
        warehouseReciptHashes[no].id=id;
        warehouseReciptHashes[no].hash=hash;
        warehouseReciptHashes[no].time=time;
        emit warehouseReciptHashEvent(no,id,hash,time);
        return no;
    }

    function getWarehouseReciptHash(uint id)public view returns(uint no,uint ID,string hash,string time){
        for(uint i=1;i<=idLists.length;i++){
            if((warehouseReciptHashes[i].id==id)){
                no = warehouseReciptHashes[i].no;
                ID = warehouseReciptHashes[i].id;
                hash=warehouseReciptHashes[i].hash;
                time=warehouseReciptHashes[i].time;
            }
        }
    }
}