pragma solidity 0.4.25;

contract agreement {
    //合同hash上链的结构体
    struct contractHash{
        uint no;//no
        uint id;//对应数据库中的合同id
        string partyA;//合同参与方
        string partyB;
        string Type;//合同类型
        string hash;
        string updateTime;
    }

    mapping(uint=>contractHash) contractHashes;
    uint[] idLists;

    event ContarctHashEvent(uint no,uint id,string partyA,string partyB,string Type,string hash,string updateTime);
    event test(uint id);

    function setContarctHash(uint id,string partyA,string partyB,string Type,string hash,string updateTime) public returns(uint){
        uint no = idLists.length+1;
        idLists.push(no);
        contractHashes[no].id=id;
        contractHashes[no].partyA=partyA;
        contractHashes[no].partyB=partyB;
        contractHashes[no].hash=hash;
        contractHashes[no].Type=Type;
        contractHashes[no].updateTime=updateTime;
        emit ContarctHashEvent(no,id,partyA,partyB,Type,hash,updateTime);
        return no;
    }

    function getContarctHash(uint id,string type)public constant returns(uint,string ,string ,string,string,string){

        for(uint i=1;i<=idLists.length;i++){
            if(contractHashes[i].id==id){

                uint  ID = contractHashes[i].id;
                string memory partyA=contractHashes[i].partyA;
                string memory partyB=contractHashes[i].partyB;
                string memory Type = contractHashes[i].Type;
                string memory hash=contractHashes[i].hash;
                string memory updateTime= contractHashes[i].updateTime;
                return (ID,partyA,partyB,Type,hash,updateTime);
        }
        }
    }
}
