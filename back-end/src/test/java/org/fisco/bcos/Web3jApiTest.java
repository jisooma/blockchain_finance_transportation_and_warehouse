package org.fisco.bcos;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

//import org.junit.jupiter.api.Test;
import org.fisco.bcos.entity.*;
import org.fisco.bcos.mapper.*;
//import org.fisco.bcos.service.WarrantService;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class Web3jApiTest extends BaseTest {

    @Autowired Web3j web3j;

    @Test
    public void getBlockNumber() throws IOException {
        BigInteger blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        System.out.println("-------------------------"+blockNumber);
        assertTrue(blockNumber.compareTo(new BigInteger("0")) >= 0);
    }

//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private User1Mapper user1Mapper;
//    @Test
//    public void t() throws SQLException {
//        System.out.println(dataSource.getClass());
//        System.out.println(dataSource.getConnection());
//    }
//
//    @Test
//    public void t1(){
//        int type = 0;
//        String name = "银行";
//        String address = "dsdsdd";
//        String privateKey="1213123";
//        String password = "dsds";
//        String bal = "12121";
////        User1 user1 = new User1(0,name,password,bal,0,address,privateKey);
////        System.out.println(user1);
////        int i = user1Mapper.addUser(user1);
//        user1Mapper.addUser(new User1(0,"bank_1","abc","1233",0,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2"));
//        user1Mapper.addUser(new User1(1,"logistics_1","abc","1000",0,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2"));
//        user1Mapper.addUser(new User1(1,"logistics_2","abc","1001",0,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2"));
//        user1Mapper.addUser(new User1(2,"enterprise_1","abc","100",0,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2"));
//        user1Mapper.addUser(new User1(2,"enterprise_2","abc","1233",0,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2"));
//        user1Mapper.addUser(new User1(2,"enterprise_3","abc","1001",0,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2"));
////        System.out.println(i);
//    }
//    @Test
//    public void t2(){
//        List<User1> users=user1Mapper.queryByType(2);
//        System.out.println("测试------------"+users);
//    }
//
//    @Test
//    public void t3(){
//        List<User1> users=user1Mapper.queryByStatusAndType(2,0);
//        System.out.println("测试------------"+users);
//    }
//
//    @Test
//    public void t4(){
//        String address = "0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2";
//        String privateKey="0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2";
//        User1 user1= new User1(17,2,"enterprise_2","abc","1233",1,"0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2","0xcdcce60801c0a2e6bb534322c32ae528b9dec8d2");
//        int i=user1Mapper.updateUser(user1);
//        System.out.println("测试------------"+i);
//    }
//
//    @Test
//    public void t5(){
//        User1  user1 = user1Mapper.login(0,"bank_1","abc");
//        System.out.println(user1);
//
//    }
//
//
//
//
//    @Autowired
//    private AppointMapper appointMapper;
//
////    @Test
////    public void addAppoint(){
//////        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//////        String time = df.format(new Date());
////        Date time= new Date();
////        Timestamp t = new Timestamp(time.getTime());
////        Date date = new Date();
////        Timestamp timeStamep = new Timestamp(date.getTime());
////        System.out.println(timeStamep);
//////        Appoint appoint;
////        appointMapper.addAppoint(new Appoint("logistics_1","bank_1",0,t));
////        appointMapper.addAppoint(new Appoint("logistics_2","bank_1",0,t));
//////        appointMapper.addAppoint(new Appoint("logistics","bank_1",0,new Date()));
//////        appointMapper.addAppoint(new Appoint("logistics","bank_1",0,new Date()));
////    }
////
////    @Test
////    public void updateAppoint(){
////        Date time= new Date();
////        Timestamp t = new Timestamp(time.getTime());
////        int i = appointMapper.updateAppoint(new Appoint(1,"logistics_1","bank_1",1,t));
////        System.out.println(i);
////
////    }
//    @Test
//    public void queryAllAppoint(){
//
//        List<Appoint> appoints = appointMapper.queryAllAppoint();
//        System.out.println(appoints);
//    }
////    public List<Appoint> queryAppointByLogistics(@Param("logistics") String logistics);
//    @Test
//    public void  queryAppointByLogistics(){
//        List<Appoint> appoints =appointMapper.queryAppointByLogistics("logistics_2");
//        System.out.println(appoints);
//
//    }
//
//
//    @Autowired
//    private CollateralMapper collateralMapper;
//    @Test
//    public void addCollateral(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//
//        collateralMapper.addCollateral(new Collateral("enterprise_1","enterprise_2",0,t,t));
//        collateralMapper.addCollateral(new Collateral("enterprise_2","enterprise_3",0,t,t));
////        collateralMapper.addCollateral(new Collateral());
////        collateralMapper.addCollateral(new Collateral());
//    }
//    @Test
//    public void updateCollateral(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//        collateralMapper.updateCollateral(new Collateral(1,"enterprise_1","enterprise_2",1,t,t));
//
//    }
//    @Test
//    public void queryAllCollateral(){
//        List<Collateral> collaterals = collateralMapper.queryAllCollateral();
//        System.out.println(collaterals);
//
//    }
//    @Test
//    public void queryCollateralByConsigner(){
//        List<Collateral> collaterals = collateralMapper.queryCollateralByConsigner("enterprise_2");
//        System.out.println(collaterals);
//
//    }
//    @Test
//    public  void queryCollateralByEnterprise(){
//       List<Collateral> collaterals  = collateralMapper.queryCollateralByEnterprise("enterprise_1");
//        System.out.println(collaterals);
//    }
//
////
////    public int addRespository(Respository respository);
////    public int updateRespository(Respository respository);
////    public int deleteRespository(@Param("id") int id);
////    public List<Respository> queryAllRespository();
////    public List<Respository> queryRespositoryByHolder(@Param("holder") String holder);
////    public List<Respository> queryRespositoryByLogistics(@Param("logistics") String logistics);
////    public List<Respository> queryRespositoryByStatus(@Param("status") int status);
//
//    @Autowired
//    private RespositoryMapper respositoryMapper;
////    @Test
////    public void addRespository(){
////        Date time= new Date();
////        Timestamp t = new Timestamp(time.getTime());
////        respositoryMapper.addRespository(new Respository("enterprise_1","logistics_1","100本笔记本",0,t));
////        respositoryMapper.addRespository(new Respository("enterprise_2","logistics_1","100台电脑",0,t));
////
////    }
////    @Test
////    public void updateRespository(){
////        Date time= new Date();
////        Timestamp t = new Timestamp(time.getTime());
////        respositoryMapper.updateRespository(new Respository(2,"bank_1","logistics_1","100台电脑",1,t));
////
////    }
//    @Test
//    public void  queryAllRespository(){
//        List<Respository> respositories  = respositoryMapper.queryAllRespository();
//        List<Respository> respositories1 = respositoryMapper.queryRespositoryByHolder("bank_1");
//        List<Respository> respositories2 = respositoryMapper.queryRespositoryByLogistics("logistics_1");
//        List<Respository> respositories3 = respositoryMapper.queryRespositoryByStatus(1);
//        System.out.println(respositories);
//        System.out.println(respositories1);
//        System.out.println(respositories2);
//        System.out.println(respositories3);
//
//    }
//    @Autowired
//    private ValueReportMapper valueReportMapper;
//    @Test
//    public  void addValueReport(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//        valueReportMapper.addValueReport(new ValueReport("logistics_1","enterprise_1",1,"1234",0,t));
//        valueReportMapper.addValueReport(new ValueReport("logistics_1","enterprise_2",2,"4321",0,t));
//    }
//
////    @Test
////    public void queryAllValueReport(){
////        List<ValueReport> valueReports = valueReportMapper.queryAllValueReport();
////        List<ValueReport> valueReports2 = valueReportMapper.queryValueReportByReno(2);
////        //这个有问题
////        List<ValueReport> valueReports1 =valueReportMapper.queryAllValueReportByEnterPrise("enterprise_1");
////        System.out.println(valueReports);
////        System.out.println(valueReports1);
////        System.out.println(valueReports2);
////
////    }
//
////    public int addValueReport(ValueReport valueReport);
////    public int deleteValueReport(@Param("id") int id);
////    public List<ValueReport> queryAllValueReport();
////    public List<ValueReport> queryValueReportByReno(@Param("reno") int reno);
////    public List<ValueReport> queryValueReportByLogistics(@Param("logistics") String logistics);
////    public List<ValueReport> queryAllValueReportByEnterPrise(@Param(" enterprise") String enterprise);
//
//    @Autowired
//    private  InventoryMapper inventoryMapper;
//    @Test
//    public void addLoanApply(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//        inventoryMapper.addInventory(new Inventory("enterprise_1","logistics_1",1,1,"123",0,t));
//        inventoryMapper.addInventory(new Inventory("enterprise_2","logistics_1",2,2,"321",0,t));
//    }
//    @Test
//    public void update(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//        inventoryMapper.updateInventory(new Inventory(1,"enterprise_1","logistics_1",1,1,"123",1,t));
//    }
//    @Test
//    public void query(){
//        List<Inventory> inventories = inventoryMapper.queryInventoryByEnterprise("enterprise_1");
//        List<Inventory> inventories1 = inventoryMapper.queryInventoryByLogistics("logistics_1");
//        System.out.println(inventories);
//        System.out.println(inventories1);
//    }
//
//    @Autowired
//    private LoanApplyMapper loanApplyMapper;
////    @Test
////    public void addloanApply(){
////        Date time= new Date();
////        Timestamp t = new Timestamp(time.getTime());
////        loanApplyMapper.addLoanApply(new LoanApply("enterprise_1","bank_1",1,1,"1234",0,t));
////        loanApplyMapper.addLoanApply(new LoanApply("enterprise_2","bank_1",2,2,"4321",0,t));
////    }
////    @Test
////    public void updateApply(){
////        Date time= new Date();
////        Timestamp t = new Timestamp(time.getTime());
////        loanApplyMapper.updateLoanApply(new LoanApply(1,"enterprise_1","bank_1",1,1,"1234",1,t));
////
////    }
//    @Test
//    public void queryApply(){
//       List<LoanApply> loanApplies = loanApplyMapper.queryAllLoanApply();
//        List<LoanApply>loanApplies1 = loanApplyMapper.queryLoanApplyByBank("bank_1");
//      List<LoanApply> loanApplies2 =  loanApplyMapper.queryLoanApplyByEnterprise("enterprise_1");
//      System.out.println(loanApplies);
//      System.out.println(loanApplies1);
//      System.out.println(loanApplies2);
//    }
//
//
//    @Autowired
//    UserServie userServie;
//    @Test
//    public List<String> queryAllLogisticsName(){
//        List<User1> user1s = userServie.queryByType(1);
//        ArrayList<String> names= new ArrayList<String>();
////        for(int i =0;i<user1s.size();i++){
////            names.set(i, user1s.get(i).getName());
////        }
//        System.out.println(user1s);
//        return  names;
//    }

//    @Autowired
//    private TokenExchangeService tokenExchangeService;
//
//    @Test
//    void t1(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//        tokenExchangeService.addTokenExchange(new TokenExchange("bank_1","logistics_1","12",0,t));
//        tokenExchangeService.addTokenExchange(new TokenExchange("bank_2","logistics_1","12",0,t));
//    }
//    @Test
//    void t2(){
//        Date time= new Date();
//        Timestamp t = new Timestamp(time.getTime());
//        tokenExchangeService.updateTokenExchange(new TokenExchange(1,"bank_1","lll","12",1,t));
//    }
//
//    @Test
//    void t3(){
//       List<TokenExchange> tokenExchanges=tokenExchangeService.queryAllTTokenExchange();
//        System.out.println(tokenExchanges);
//    }
//    @Test
//    void t4(){
//        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByInitiator("lll");
//        System.out.println(tokenExchanges);
//    }
//    @Test
//    void t5(){
//        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByBank("bank_1");
//        System.out.println(tokenExchanges);
//    }
//
//    @Test
//    void t6(){
//        List<TokenExchange> tokenExchanges=tokenExchangeService.queryTokenExchangeByInitiatorAndBank("lll","bank_1");
//        System.out.println(tokenExchanges);
//    }
//
//    @Test
//    void t7(){
//      tokenExchangeService.queryTokenExchangeByID(1);
//    }
//    @Autowired
//    private WarehouseReceiptMapper warehouseReceiptMapper;
//    @Autowired
//    private WarrantService warrantService;
//    @Test
//    public void test1(){
////        holder,logistics,Integer.parseInt(reno),address,valMoney,status,ts
//        Date date = new Date();
//        int j= warrantService.addWarrant(new Warrant("enet","lohgi",1,"xian","111","de",new Timestamp(date.getTime())));
//        int i= warehouseReceiptMapper.addWarrant(new Warrant("enet","lohgi",1,"xian","111","de",new Timestamp(date.getTime())));
//        System.out.println(i+j);
//    }

//    @Autowired
//    private InventoryService inventoryService;
//    @Test
//    public void test2(){
//        inventoryService.addInventory(new Inventory())
//    }
//

}
