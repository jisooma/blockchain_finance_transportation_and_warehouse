pragma solidity ^0.4.0;

contract agreement {
    //合同上链的结构体
    struct contractHash{
        uint id;
        string partyA;
        string partyB;
        string hash;
    }
    mapping(uint=>contractHash) contractHashes;
    uint[] idLists;


    function setContarctHash(string partyA,string partyB,string hash) returns(uint){
        uint id = idLists.length++;
        contractHashes[id].hash=hash;
        contractHashes[id].partyA=partyA;
        contractHashesp[id].partyB=partyB;
        return id;
    }

    function getContarctHash(uint id) returns(uint ID,string partyA,string partyB,string hash){
        id = contractHashes[id].id;
        partyA=contractHashes[id].partyA;
        partyB=contractHashes[id].partyB;
        hash=contractHashes[id].hash;
    }
}
