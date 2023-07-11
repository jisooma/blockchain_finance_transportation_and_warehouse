pragma solidity 0.4.25;

contract RespositoryHash{
    struct respositoryHash{
        uint no;//no
        uint id;//对应数据库中的仓单的id
        string hash;
        string time;
    }

    mapping(uint=>respositoryHash) respositoryHashes;
    uint[] idLists;

    event RespositoryHashEvent(uint no,uint id,string hash,string time);


    function setRespositoryHash(uint id,string hash,string time) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        respositoryHashes[no].no=no;
        respositoryHashes[no].id=id;
        respositoryHashes[no].hash=hash;
        respositoryHashes[no].time=time;
        emit RespositoryHashEvent(no,id,hash,time);
        return no;
    }

    function getRespositoryHash(uint id)public view returns(uint no,uint ID,string hash,string time){
        for(uint i=1;i<=idLists.length;i++){
            if((respositoryHashes[i].id==id)){
                no = respositoryHashes[i].no;
                ID = respositoryHashes[i].id;
                hash=respositoryHashes[i].hash;
                time=respositoryHashes[i].time;
            }
        }
    }
}
