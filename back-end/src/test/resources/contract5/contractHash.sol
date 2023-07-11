pragma solidity 0.4.25;

contract contractHash {
    //合同hash上链的结构体
    struct contractHash{
        uint no;//no
        uint id;//对应数据库中的合同id
        string partyA;//合同参与方
        string partyB;
        string Type;//合同类型
//        string addr;//合同下载地址
        string hash;
        string updateTime;
    }

    mapping(uint=>contractHash) contractHashes;
    uint[] idLists;

    event ContarctHashEvent(uint no,uint id,string partyA,string partyB,string Type,string hash,string updateTime);


    function setContarctHash(uint id,string partyA,string partyB,string Type,string hash,string updateTime) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        contractHashes[no].no=no;
        contractHashes[no].id=id;
        contractHashes[no].partyA=partyA;
        contractHashes[no].partyB=partyB;
        contractHashes[no].hash=hash;
        contractHashes[no].Type=Type;
//        contractHashes[no].addr=addr;
        contractHashes[no].updateTime=updateTime;
        emit ContarctHashEvent(no,id,partyA,partyB,Type,hash,updateTime);
        return no;
    }

    function getContarctHash(uint id,string type1)public view returns(uint no,uint ID,string partyA ,string partyB,string Type,string hash,string updateTime){
        for(uint i=1;i<=idLists.length;i++){
            if((contractHashes[i].id==id) && (keccak256(abi.encodePacked(contractHashes[i].Type)) == keccak256(abi.encodePacked(type1)))){
                no = contractHashes[i].no;
                ID = contractHashes[i].id;
                partyA=contractHashes[i].partyA;
                partyB=contractHashes[i].partyB;
                Type = contractHashes[i].Type;
                hash=contractHashes[i].hash;
                updateTime=contractHashes[i].updateTime;
//                addr=contractHashes[i].addr;
            }
        }
    }
}