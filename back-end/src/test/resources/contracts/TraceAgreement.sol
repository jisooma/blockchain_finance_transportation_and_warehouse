pragma solidity ^0.4.0;

contract TraceAgreement {
    //合同hash上链的结构体
    struct TraceContractHash{
        uint no;//no
        uint id;//对应数据库中的合同id
        string partyA;//合同参与方
        string partyB;
        string Type;//合同类型
        string hash;
        string updateTime;
    }

    mapping(uint=>TraceContractHash) contractHashes;
    uint[] idLists;


    event ContarctHashEvent(uint no,uint id,string partyA,string partyB,string Type,string hash);

    function setContarctHash(uint id,string partyA,string partyB,string Type,string hash,string updateTime) returns(uint){
        uint id = idLists.length+1;
        idLists.push(id);
        contractHashes[id].partyA=partyA;
        contractHashes[id].partyB=partyB;
        contractHashes[id].hash=hash;
        contractHashes[id].Type=Type;
        contractHashes[id].updateTime=updateTime;
        emit ContarctHashEvent(id,partyA,partyB,Type,hash);
        return id;
    }

    function getContarctHash(uint id) returns(uint ID,string partyA,string partyB,string Type,string hash,string updateTime){
        ID = contractHashes[id].id;
        partyA=contractHashes[id].partyA;
        partyB=contractHashes[id].partyB;
        Type = contractHashes[id].Type;
        hash=contractHashes[id].hash;
        updateTime= contractHashes[id].updateTime;
    }
}
