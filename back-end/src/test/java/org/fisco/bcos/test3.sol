pragma solidity ^0.4.25;

contract test3{
  //-----------------------------------------------//
  //-------------账号、转账功能---------------------//
  //------------------------------------------------//

  struct Account { //
    uint accNo;			//账户号
    string accType;         //公司类型：银行、企业、物流
    string name;            //公司名
    uint bal;				//余额
    string status;			//用户状态:合法/不合法
   // address publicKey;      //地址
  }

  struct TransferInfo{ //5
    uint traNo;           	//转账编号
    address payer;           //付款方
    address payee;           //收款方
    uint money;             //转账金额
    string updateTime;      //转账时间
  }

  mapping(address => Account) accountMap; //存储账户的mapping
  mapping(uint => TransferInfo) transferInfoMap; //存储转账的mapping

  mapping(address => uint[]) myTransferInfoIdList; //存储与自己有关的转账的mapping

  uint[] accountIdList;//存储账户的key

  uint[] transferInfoIdList;//存储转账的key

  modifier isVaild{
    require((keccak256(abi.encodePacked(accountMap[msg.sender].status))==keccak256(abi.encodePacked("合法"))));
    _;
  }


  modifier onlyBank{
    require((keccak256(abi.encodePacked(accountMap[msg.sender].accType))==keccak256(abi.encodePacked("银行"))));
    _;
  }
  modifier onlyLogistics{
    require((keccak256(abi.encodePacked(accountMap[msg.sender].accType))==keccak256(abi.encodePacked("物流"))));
    _;
  }
  modifier onlyEnterprise{
    require((keccak256(abi.encodePacked(accountMap[msg.sender].accType))==keccak256(abi.encodePacked("普通企业"))));
    _;
  }

  modifier check(string _str1,string _str2) {
    require(keccak256(abi.encodePacked(_str1)) == keccak256(abi.encodePacked(_str2)));
    _;
  }
  //1.注册账号
  //输入3：账号、企业类型、企业名称
  function createAccount(string accType,string name,uint bal) public {
    
    uint no = accountIdList.length+1;
    //判断是不是存在
    if (keccak256(abi.encodePacked(accountMap[msg.sender].status)) == keccak256(abi.encodePacked("合法"))) {
      return;
    }
    //增加信息
    accountMap[msg.sender] = Account(
      {accNo:no, accType:accType,name:name,bal:bal,status:"合法"});
    accountIdList.push(no);

    return;
  }

  //2.发起转账
  //输入4：付款方、收款方、金额、时间
  function createTransfer(address payer,address payee,uint money,string time) public {

    require(money>0);
    //获取编号
    uint traNo=transferInfoIdList.length+1;

    //判断余额是否足够
    require(accountMap[payer].bal>=money);

    //
    //uint prevAmount=accountMap[payer].bal+accountMap[payee].bal;

    //增加信息
    myTransferInfoIdList[payer].push(traNo);
    myTransferInfoIdList[payee].push(traNo);

    transferInfoIdList.push(traNo);
    transferInfoMap[traNo] = TransferInfo(
      {traNo:traNo,payer:payer,payee:payee,money:money,updateTime:time});
    accountMap[payer].bal-=money;
    accountMap[payee].bal+=money;
    //判断异常
    //assert(accountMap[payer].bal+accountMap[payee].bal==prevAmount);
  }

  //3.查询账户信息
  //输入：账号

  //根据下标查找账户
  function displayAccountList(uint no) public view returns (uint){
    return accountIdList[no];
  }

  //展示一条记录
  function displayAccount(address pub) public view returns (uint id,string accType,string name,uint bal,string status){
    id=accountMap[pub].accNo;
    accType=accountMap[pub].accType;
    name=accountMap[pub].name;
    bal=accountMap[pub].bal;
    status=accountMap[pub].status;
  }

  //4.查询转账信息
  //输入：账号

  //查找与用户有关的转账编号数组
  function getmyTransferInfoIdList(address accNo) public view returns(uint[]) {
    return myTransferInfoIdList[accNo];
  }
  //可以再写一个函数，把具体的转账信息展示出来

  //展示一条记录
  function displayTransferInfo(uint no) public view returns (uint ,string ,string ,uint ,string ){
    uint u1=transferInfoMap[no].traNo;
    string storage s2=accountMap[transferInfoMap[no].payer].name;
    string storage s3=accountMap[transferInfoMap[no].payee].name;
    uint u4=transferInfoMap[no].money;
    string storage s5=transferInfoMap[no].updateTime;
    return(u1,s2,s3,u4,s5);
  }


  //5.查询
  //输入：账号；输出:
  function checkBalance(address accNo) public view returns (uint ){
    return accountMap[accNo].bal;
  }
  /*////////////////////////////////////////////////
 /    第二部分:银行委托物流功能                     /
 /    委托合同：委托合同创建，同意/拒绝，解除合同 /
 /                  /
 */////////////////////////////////////////////////

  struct appoint{
    uint ID;           	//编号
    address logistics;      //物流企业
    address bank;            //银行
    string status;    //状态:提交委托，同意委托(合法)，解除委托
    string updateTime;      //更新状态时间
  }
  mapping(uint=>appoint) appointMap;
  mapping(address=>uint[]) myAppointList;
  uint[] appointIdList;

  event createAppointEvent(uint ID,address logistics,address bank,string status,string updateTime);
  event agreeEvent(uint ID,address logistics,address bank,string status,string updateTime);

  //银行 委托 物流企业
  function createAppoint(address logistics,string time) public isVaild onlyBank returns (bool res){

    // string  l=accountMap[logistics].accNo;
    // string  b=accountMap[bank].accNo;

    //  require((l.length!=0),"物流企业不存在");
    //  require((b.length!=0),"银行不存在");

    if((keccak256(abi.encodePacked(accountMap[logistics].status))==keccak256(abi.encodePacked("合法")))){
      //获取编号
      uint creNo=appointIdList.length+1;

      myAppointList[logistics].push(creNo);
      myAppointList[msg.sender].push(creNo);

      appointIdList.push(creNo);
      appointMap[creNo] = appoint(
        {ID:creNo,logistics:logistics,bank:msg.sender,status:"提交委托中",updateTime:time});
      emit createAppointEvent(creNo,logistics,msg.sender,"提交委托中",time);
      res = true;

    }else{
      res = false;
    }

  }
  //物流企业同意委托
  function agreeAppoint(uint no,string time,bool choice)public onlyLogistics returns (bool res){
    require((keccak256(abi.encodePacked(appointMap[no].status))==keccak256(abi.encodePacked("提交委托中"))),"委托合同状态不符");
    require(appointMap[no].logistics==msg.sender,"不是委托该物流企业");

    if(choice){
      appointMap[no].status="同意委托";
      appointMap[no].updateTime = time;
      res = true;
      emit agreeEvent(appointMap[no].ID,appointMap[no].logistics,appointMap[no].bank,appointMap[no].status,appointMap[no].updateTime);
    }else{
      appointMap[no].status="拒绝委托";
      appointMap[no].updateTime = time;
      res = false;
      emit agreeEvent(appointMap[no].ID,appointMap[no].logistics,appointMap[no].bank,appointMap[no].status,appointMap[no].updateTime);
    }

  }
  //银行解除委托
  function removeAppoint(uint no,string time,bool choice)public isVaild  onlyBank returns(bool res){
    // string memory b = appointMap[no].bank;
    // require((keccak256(abi.encodePacked(b))==keccak256(abi.encodePacked(bank))),"");
    require((keccak256(abi.encodePacked(appointMap[no].status))==keccak256(abi.encodePacked("同意委托"))),"委托合同状态不符");
    require(appointMap[no].bank==msg.sender,"不是该委托合同对应的银行");
    if(choice){
      appointMap[no].status="解除委托";
      appointMap[no].updateTime = time;
      return true;
    }else{
      return false;
    }
  }
  function getMyAppointIdList(address accNo) public view returns(uint[]) {
    return myAppointList[accNo];
  }

  //显示一条记录
  function displayAppoint(uint no) public view returns (uint id,string logistics,string bank,string status,string updateTime){
    id = no;
    logistics=accountMap[appointMap[no].logistics].name;
    bank=accountMap[appointMap[no].bank].name;
    status=appointMap[no].status;
    updateTime=appointMap[no].updateTime;
    // return(u1,s2,s3,s5,s6);
  }

  //-------------------------------------------------------------------//
  //--------------------------------担保合同---------------------------//
  //-----------------------------------------------------------------//
  struct collateral{
    uint ID;//担保合同id
    address enterprise;//融资企业
    address consigner;//核心企业
    string status;//合同状态
    string updateTime;//状态更新时间
    string endTime;//截止时间
  }
  mapping(uint=>collateral) collateralMap;
  mapping(address=>uint[]) myCollateralList;
  uint[] collateralIdList;

  event createCollateralEvent(uint ID,address enterprise,address consigner,string status,string updateTime,string endTime);

  //融资企业 向担保企业申请担保
  function createCollateral(address consigner,string time,string endTime)public isVaild onlyEnterprise returns(bool){
    //
    if((keccak256(abi.encodePacked(accountMap[consigner].status))==keccak256(abi.encodePacked("合法")))){

      uint No = collateralIdList.length+1;
      myCollateralList[msg.sender].push(No);
      myCollateralList[consigner].push(No);

      collateralIdList.push(No);
      collateralMap[No] = collateral({
        ID:No,enterprise:msg.sender,consigner:consigner,status:"申请中",updateTime:time,endTime:endTime
        });
      emit createCollateralEvent(No,msg.sender,consigner,"申请中",time,endTime);
      return true;
    }else{
      return false;
    }
  }
  //核心企业 同意担保或拒绝担保
  function agreeCollateral(uint no,bool choice,string currentTime)public isVaild onlyEnterprise returns(bool res){

    require((keccak256(abi.encodePacked(collateralMap[no].status))==keccak256(abi.encodePacked("申请中"))),"担保合同状态不符");
    require(collateralMap[no].consigner==msg.sender,"不是该担保合同对应的担保企业");
    if(choice){
      collateralMap[no].status = "同意担保";
      collateralMap[no].updateTime =  currentTime;
      return true;
    }else{
      collateralMap[no].status = "拒绝担保";
      collateralMap[no].updateTime =  currentTime;
      return false;
    }

  }
  //核心企业 解除担保
  function removeCollateral(uint no,bool choice,string currentTime)public isVaild onlyEnterprise returns(bool res){
    //  string memory c = collateralMap[no].consigner;
    //(keccak256(abi.encodePacked(c))==keccak256(abi.encodePacked(consigner))
    require((keccak256(abi.encodePacked(collateralMap[no].status))==keccak256(abi.encodePacked("同意担保"))),"担保合同状态不符");
    require(collateralMap[no].consigner==msg.sender,"不是该担保合同对应的担保企业");
    if(choice){
      collateralMap[no].status="解除担保";
      collateralMap[no].updateTime =  currentTime;
      return true;

    }else{
      return false;
    }
  }

  //查找我的担保列表
  function getMyCollateralIdList(address accNo) public view returns(uint[]) {
    return myCollateralList[accNo];
  }

  //显示一条记录
  function displaycollateral(uint no) public view returns (uint id,string enterprise,string consigner,string status ,string updateTime,string endTime){
    id = no;
    enterprise=accountMap[collateralMap[no].enterprise].name;
    consigner=accountMap[collateralMap[no].consigner].name;
    status=collateralMap[no].status;
    updateTime=collateralMap[no].updateTime;
    endTime=collateralMap[no].endTime;
    // return(no,s2,s3,s5,s6,s7);
  }


  //-------------------------------------------------------------------//
  //--------------------------------库存---------------------------//
  //-----------------------------------------------------------------//
  struct repository{
    uint No;//库存编号
    address holder;//货权拥有人
    address transport;//物流企业
    string text;//存货内容
    string status;//库存状态：准备入库，在库，出库。
    string updateTime;//更新时间
  }
  mapping (uint=>repository) repositoryMap;
  mapping(address=>uint[]) myRepositoryList;
  uint[] repositoryIdList;

  event createRepositoryEvent(uint id,address holder,address transport,string text,string status,string updateTime);
  // 融资企业创建  待入库的库存，向物流企业申请
  function createRepository(address transport,string text,string updateTime)public isVaild onlyEnterprise returns(bool){
    if((keccak256(abi.encodePacked(accountMap[transport].status))==keccak256(abi.encodePacked("合法")))){

      uint no = repositoryIdList.length+1;

      myRepositoryList[msg.sender].push(no);
      myRepositoryList[transport].push(no);
      repositoryIdList.push(no);
      repositoryMap[no] = repository({
        No:no,holder:msg.sender,transport:transport,text:text,status:"准备入库",updateTime:updateTime
        });
      emit createRepositoryEvent(no,msg.sender,transport,text,"准备入库",updateTime);
      return true;
    }else{
      return false;
    }


  }
//  //当库存合同签好之后，修改库存为在库状态
//  function agreeRepository(uint no,bool choice,string currentTime)public isVaild onlyLogistics returns(bool res){
//    //  require(collateralMap[no]!=null,"");
//    //(keccak256(abi.encodePacked(c))==keccak256(abi.encodePacked(consigner)))
////    require((keccak256(abi.encodePacked())==keccak256(abi.encodePacked(consigner))));
//    string memory r = repositoryMap[no].status;
//    require(keccak256(abi.encodePacked("准备入库"))==keccak256(abi.encodePacked(r)),"");
//    if(choice){
//      repositoryMap[no].status = "在库";
//      repositoryMap[no].updateTime = currentTime;
//      return true;
//    }else{
//      return false;
//    }
//  }
//  function removeRepository(uint no,bool choice,string currentTime)public isVaild onlyLogistics returns(bool res){
//    //  string memory c = collateralMap[no].consigner;
//    //(keccak256(abi.encodePacked(c))==keccak256(abi.encodePacked(consigner)))
//    if(choice){
//      repositoryMap[no].status="出库";
//      repositoryMap[no].status=currentTime;
//      return true;
//
//    }else{
//      return false;
//    }
//  }


  function getMyrepository(address accNo)public returns(uint[]){
    return myRepositoryList[accNo];
  }

  function displayRepository(uint no)public view returns(uint id,string holder,string transport,string text,string status,string updateTime){
    // require(repositoryMap[no]!=null,"");
    id = no;
    holder = accountMap[repositoryMap[no].holder].name;
    transport =accountMap[repositoryMap[no].transport].name;
    text = repositoryMap[no].text;
    status = repositoryMap[no].status;
    updateTime = repositoryMap[no].updateTime;
    // return (no,holder, transport,text,status,updateTime);
  }


  //-------------------------------------------------------------------//
  //--------------------------------库存合同---------------------------//
  //-----------------------------------------------------------------//
  struct inventory{
    uint ID;//库存合同
    address enterprise;//存货企业
    address transport;//物流企业
    uint reNo;//库存编号
    uint reMomey;//融资企业贷款完成之后，需要对物流企业付中介费。
    string status;//合同状态:等待签署,合法,失效
    string updateTime;//
  }
  mapping(uint=>inventory) inventoryMap;
  mapping(address=>uint[]) myInventoryList;
  uint[] inventoryIdList;

  event createInventoryEvent(uint no,address enterprise,address transport,uint reNo,uint reMomey,string status,string updateTime);
  //创建库存合同
  function createInventory(address enterprise,uint reNo,uint reMomey,string updateTime)public
 isVaild onlyLogistics returns(bool){
    // require(Account[enterprise]==null,"");
    // require(Account[transport]==null,"")
    // require(repositoryMap[reNo]==null,"");

    if((keccak256(abi.encodePacked(accountMap[enterprise].status))==keccak256(abi.encodePacked("合法")))){

     uint no = inventoryIdList.length+1;
      myInventoryList[enterprise].push(no);
      myInventoryList[msg.sender].push(no);
     inventoryMap[no] = inventory({
     ID:no,enterprise:enterprise,transport:msg.sender,reNo:reNo,reMomey:reMomey,status:"待签署",updateTime:updateTime
     });
      emit createInventoryEvent(no,enterprise,msg.sender,reNo,reMomey,"待签署",updateTime);
      return true;

   }else{
      return false;
    }
   }

  function agreeInventory(uint no,bool choice,string time)public isVaild onlyEnterprise returns (bool){
    if(choice){
      require((keccak256(abi.encodePacked(inventoryMap[no].status))==keccak256(abi.encodePacked("待签署"))),"合同状态不符");
      inventoryMap[no].status = "签署成功";
      inventoryMap[no].updateTime = time;
      repositoryMap[inventoryMap[no].reNo].status = "在库";
      return true;
    }else{
      inventoryMap[no].status = "拒绝签署";
      inventoryMap[no].updateTime = time;
      return false;
    }
  }
  function removeInventory(uint no,bool choice,string time)public isVaild returns(bool res){
    if(choice){
      require((keccak256(abi.encodePacked(inventoryMap[no].status))==keccak256(abi.encodePacked("签署成功"))),"合同状态不符");
      inventoryMap[no].status = "解除签署";
      inventoryMap[no].updateTime = time;
      repositoryMap[inventoryMap[no].reNo].status = "出库";
     // accountMap[inventoryMap[no].enterprise].bal -=inventoryMap[no].reMomey;

      res = true;
    }else{
      res = false;
    }
  }
  function getInventory(address accNo)public returns(uint[]){
    return myInventoryList[accNo];
  }

  function displayInventory(uint no)public returns(uint id,string enterprise,string transport,uint reNo,uint reMomey,string status,string updateTime){
    id = no;
    enterprise = accountMap[inventoryMap[no].enterprise].name;
    transport = accountMap[inventoryMap[no].transport].name;
    reNo = inventoryMap[no].reNo;
    reMomey = inventoryMap[no].reMomey;
    status = inventoryMap[no].status;
    updateTime = inventoryMap[no].updateTime;
  }
  //-------------------------------------------------------------------------//
  //---------------------------价值评估报告--------------------------------------//
  //-------------------------------------------------------------------------//


  //价值评估报告
  struct valueReport{
    uint ID;//价值评估报告
    address transport;//物流企业
    address enterprise;//
    uint reNo;//存货No
    uint valMomey;//评估价值
    string status;//
    string updateTime;
  }

  mapping(uint=>valueReport) valueReportMap;
  mapping(address=>uint[]) myValueReportList;
  uint[] valueReportIdList;

  event createReportEvent(uint ID,address transport,address enterprise,uint reNo, uint valMomey,string status,string updateTime);

  function createValueReport(address enterprise,uint reNo,uint valMomey,string updateTime)public isVaild onlyLogistics returns(bool){
    //todo
    if((keccak256(abi.encodePacked(accountMap[enterprise].status))==keccak256(abi.encodePacked("合法")))){
      uint no=valueReportIdList.length+1;
      myValueReportList[msg.sender].push(no);
      myValueReportList[enterprise].push(no);

      valueReportMap[no] = valueReport({
        ID:no,transport:msg.sender,enterprise:enterprise,reNo:reNo,valMomey:valMomey,status:"合格",updateTime:updateTime
        });
      emit createReportEvent(no,msg.sender,enterprise,reNo,valMomey,"合格",updateTime);
      return true;
    }else{
      return false;
    }

  }

  function getMyValueReport(address accNo)public returns(uint[]){
    return myValueReportList[accNo];
  }
  function displayValueReport(uint no) public view returns (uint id,string transport,string enterprise,uint reNo,uint valMomey,string status,string updateTime){
    id = no;
    transport = accountMap[valueReportMap[no].transport].name;
    enterprise = accountMap[valueReportMap[no].enterprise].name;
    reNo = valueReportMap[no].reNo;
    valMomey = valueReportMap[no].valMomey;
    status =  valueReportMap[no].status;
    updateTime = valueReportMap[no].updateTime;

    // return (no,transport,enterprise,reNo,valMomey,updateTime);
  }
  //-------------------------------------------------------------------------//
  //----------------------------贷款申请--------------------------------------//
  //-------------------------------------------------------------------------//
  //贷款申请信息
  struct loanApply{
    uint ID;//贷款申请ID
    address enterprise;//企业
    address bank;//贷款银行
    uint apID;//委托ID
    uint coID;//担保id
    uint inID;//库存合同编号
    uint valID;//价值评估报告id
    uint loMomey;//贷款金额
    string status;//贷款状态：申请中，已发放,已拒绝,还款完成
    string updateTime;
  }
  mapping(uint=>loanApply) loanApplyMap;

  mapping(address=>uint[]) myApplyList;
  uint[] loanApplyIdList;
  
  event loanApplyEvent(uint ID,address enterprise,address bank,uint apID,uint coID,uint inID,uint valID,uint loMomey,string status,string updateTime);
  
  function createloanApply(address bank,uint apID,uint coID,uint inID,uint valID,string time)public isVaild onlyEnterprise returns(bool){

    if((keccak256(abi.encodePacked(accountMap[msg.sender].status))==keccak256(abi.encodePacked("合法")))){
      //todo合法判断
      require(appointMap[apID].bank==bank,"委托合同不是该银行的");
      require((keccak256(abi.encodePacked(appointMap[apID].status))==keccak256(abi.encodePacked("同意委托"))),"委托合同状态不对");
      require((keccak256(abi.encodePacked(collateralMap[coID].status))==keccak256(abi.encodePacked("同意担保"))),"担保合同状态不对");
      require((keccak256(abi.encodePacked(inventoryMap[inID].status))==keccak256(abi.encodePacked("签署成功"))),"库存合同状态不对");
      require((keccak256(abi.encodePacked(valueReportMap[valID].status))==keccak256(abi.encodePacked("合格"))),"价值评估报告不合格");

      uint valMomey = valueReportMap[valID].valMomey;
      uint no = loanApplyIdList.length+1;

            myApplyList[bank].push(no);
           myApplyList[msg.sender].push(no);
      
       loanApplyIdList.push(no);
       loanApplyMap[no] = loanApply({
        ID:no,enterprise:msg.sender,bank:bank,apID:apID,coID:coID,inID:inID,valID:valID,loMomey:valMomey,status:"申请中",updateTime:time
        });
      emit loanApplyEvent(no,msg.sender,bank,apID,coID,inID,valID,valMomey,"申请中",time);
      return true;
    }else{
      return false;
    }
  }
  function agreeLoanApply(uint no,bool choice,string updateTime,string dueTime)public isVaild onlyBank returns(bool){
    require(loanApplyMap[no].bank==bank,"贷款申请不是该银行的");
    if(choice){
      //判断
      loanApplyMap[no].status="已发放";
      uint Momey = loanApplyMap[no].loMomey;
      address  enterprise =collateralMap[loanApplyMap[no].coID].consigner;
      address bank =  loanApplyMap[no].bank;
     createTransfer(bank,enterprise,Momey,updateTime);
        //  accountMap[bank].bal -= Momey;
        //  accountMap[enterprise].bal +=Momey;
      uint inID = loanApplyMap[no].inID;
      uint reNo = inventoryMap[inID].reNo;
      repositoryMap[reNo].holder = bank;
      if(createLoanRecipt(no,bank,enterprise,Momey,updateTime,dueTime)){
        return true;
      }
        return false;
    }else{
      loanApplyMap[no].status="拒绝";
      return false;
    }
  }
  
  function endLoan(uint no)returns(bool){
     
     //根据贷款申请信息拿到库存合同id
      uint inID = loanApplyMap[no].inID;
      //根据库存合同ID拿到库存抵押品的编号NO
      uint reNo = inventoryMap[inID].reNo;
      //根据贷款申请信息拿到对应抵押品的价值评估报告的id
      uint valID = loanApplyMap[no].valID;
      
      //库存合同失效
      inventoryMap[inID].status = "失效";
      //归还货权给融资企业
      repositoryMap[reNo].holder = loanApplyMap[no].enterprise;
      //抵押品出库
      repositoryMap[reNo].status = "出库";
      //价值评估报告失效
      valueReportMap[valID].status="失效";
      //贷款申请状态改变
      loanApplyMap[no].status ="还款完成";
      return true;
  
      
  }

  function getMyloanApply(address accNo) public returns(uint []){
    return myApplyList[accNo];
  }

  function displayloanApply(uint no)public returns(uint id,string enterprise,string bank,uint apID,uint coID,uint inID,uint valID,uint loMomey,string status,string updateTime){
    //todo合法判断
    id = no; 
    enterprise = accountMap[loanApplyMap[no].enterprise].name;
    bank = accountMap[loanApplyMap[no].bank].name;
    apID = loanApplyMap[no].apID;
    coID = loanApplyMap[no].coID;
    inID = loanApplyMap[no].inID;
    valID = loanApplyMap[no].valID;
    loMomey = loanApplyMap[no].loMomey;
    status = loanApplyMap[no].status;
    updateTime = loanApplyMap[no].updateTime;
  }
  //-----------------------------------------------------------------------------------------//
  //------------------------------------贷款账单---------------------------------------------//
  //----------------------------------------------------------------------------------------//
  struct loanRecipt{
    uint  ID;//账单ID
    address enterprise;
    address bank;
    uint loanID;//贷款申请ID
    uint allMoney;//贷款金额
    uint reMoney;//已还金额
    string status;//状态：贷款中，已还完。
    string updateTime;//更新时间
    string dueTime; //截止日期
  }
  mapping(uint=>loanRecipt) loanReciptMap;
  mapping(address=>uint[]) myLoanReciptList;
  uint[] loanReciptList;
  event createLoanReciptEvent(uint id,address enterprise,address bank,uint loanID,uint allMoney,uint reMoney,string status,string updateTime,string dueTime);
  function createLoanRecipt(uint loanID,address enterprise,address bank,uint allMoney,string updateTime,string dueTime)public isVaild onlyBank returns(bool){

    if((keccak256(abi.encodePacked(accountMap[enterprise].status))==keccak256(abi.encodePacked("合法")))){
      uint no = loanReciptList.length+1;

      myLoanReciptList[enterprise].push(no);
      myLoanReciptList[bank].push(no);
      loanReciptList.push(no);
      loanReciptMap[no] = loanRecipt({
        ID:no,enterprise:enterprise,bank:bank,loanID:loanID,allMoney:allMoney,reMoney:0,status:"贷款中",updateTime:updateTime,dueTime:dueTime
        });
      emit createLoanReciptEvent(no,enterprise,bank,loanID,allMoney,0,"贷款中",updateTime,dueTime);
      return true;

    }else{
      return false;
    }
  }
  //可分批还款
  function RepayLoanRecipt(uint no,uint money,string time)public isVaild onlyEnterprise returns(bool){

    if(loanReciptMap[no].enterprise==msg.sender){
      uint allMoney = loanReciptMap[no].allMoney;
      if(money<allMoney){
        //这里调用转账方法。
        createTransfer(loanReciptMap[no].enterprise,loanReciptMap[no].bank,money,time);

        // accountMap[loanReciptMap[no].bank].bal+=money;
        // accountMap[loanReciptMap[no].enterprise].bal-=money;
        // loanReciptMap[no].alMomey-=money;
        loanReciptMap[no].reMoney +=money;
        loanReciptMap[no].updateTime = time;
      }else{
        loanReciptMap[no].status="已还完";
        //再还allMoney-loanReciptMap[no].reMoney
        uint money1 = allMoney-loanReciptMap[no].reMoney;
        createTransfer(loanReciptMap[no].enterprise,loanReciptMap[no].bank,money1,time);
        // accountMap[loanReciptMap[no].bank].bal+=almoney;
        // accountMap[loanReciptMap[no].enterprise].bal-=almoney;
        // loanReciptMap[no].alMomey=0;
        loanReciptMap[no].reMoney=allMoney;
        loanReciptMap[no].updateTime = time;
        uint loanID = loanReciptMap[no].loanID;
        if(endLoan(loanID)){
            return true;
        }
        
      }
      return true;
    }else{
      return false;
    }

  }

  function getMyLoanList(address accNo) public view returns(uint []){
    return myLoanReciptList[accNo];
  }
  function displayLoanRecipt(uint no)public view returns(uint id,string enterprise,string bank,uint allMoney,uint reMoney ,string status ,string updateTime,string dueTime){
     id = no;  
     enterprise = accountMap[loanReciptMap[no].enterprise].name;
     bank =accountMap[loanReciptMap[no].bank].name;
     allMoney= loanReciptMap[no].allMoney;
     reMoney = loanReciptMap[no].reMoney;
     status = loanReciptMap[no].status;
     updateTime = loanReciptMap[no].updateTime;
     dueTime  = loanReciptMap[no].dueTime;
  }
}

