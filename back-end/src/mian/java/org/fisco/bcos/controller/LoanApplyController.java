package org.fisco.bcos.controller;

import org.fisco.bcos.contracts.LoanReciptCall;
import org.fisco.bcos.entity.*;
import org.fisco.bcos.service.*;
import org.fisco.bcos.utils.MD5;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/loanApply")
public class LoanApplyController {
    @Autowired
    private LoanApplyService loanApplyService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private RespositoryService respositoryService;
    @Autowired
    private WarehouseReceiptService WarehouseReceiptService;
    @Autowired
    private CollateralService collateralService;
    @Autowired
    private LoanReciptService loanReciptService;
    @Autowired
    private LoanContractService LoanContractService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    /**
     * @author: mazhixiu
     * @description: 贷款申请信息
     * @date: 2021/3/23 16:50
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/addloanApply")
    public int addLoanApply(HttpServletRequest httpServletRequest){

        String enterprise = httpServletRequest.getParameter("enterprise");
        String bank = httpServletRequest.getParameter("bank");
        String coID =  httpServletRequest.getParameter("coID");
        String inID =  httpServletRequest.getParameter("inID");
        String loanPurpose =  httpServletRequest.getParameter("loanPurpose");
        String applyTime  = httpServletRequest.getParameter("applyTime");
        String dueTime  = httpServletRequest.getParameter("dueTime");
        Timestamp ts = Timestamp.valueOf(applyTime);
        System.out.println(ts);
        Timestamp ts2 = Timestamp.valueOf(dueTime);
//        String enterprise, String bank, int coID, int inID, String loanPurpose, String status, Timestamp applyTime, Timestamp dueTime) {
        LoanApply loanApply = new LoanApply(enterprise,bank,Integer.parseInt(coID),Integer.parseInt(inID),loanPurpose,"等待审批",ts,ts2);
        return loanApplyService.addLoanApply(loanApply);
    }

    @PostMapping("/lookloanApply")
    //从区块链上取下合同hash 与 融资企业提交的进行对比。
    public boolean lookLoanApply(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        LoanApply loanApply= loanApplyService.queryLoanApplyByID(Integer.parseInt(id));
        //差一步
        Inventory inventory=inventoryService.queryInventoryByID(loanApply.getInID());
        System.out.println(MD5.md5(inventory));
        Collateral collateral =collateralService.queryCollateralByID(loanApply.getCoID());
        System.out.println(MD5.md5(collateral));
        //如果区块链上取下的hash值与之相同
//        if(){
//
//        }
        return true;
    }

    @PostMapping("/disagreeloanApply")
    public boolean disagreeLoanApply(HttpServletRequest httpServletRequest) throws Exception {

        //贷款申请的id
        String id = httpServletRequest.getParameter("id");
        LoanApply loanApply= loanApplyService.queryLoanApplyByID(Integer.parseInt(id));
        loanApply.setStatus("已拒绝");
        int i = loanApplyService.updateLoanApply(loanApply);
        if(i==1){
            return true;
        }
        return  false;
    }
    @PostMapping("/agreeloanApply")
    //从区块链上取下合同hash 与 融资企业提交的进行对比。
    public boolean agreeLoanApply(HttpServletRequest httpServletRequest) throws Exception {
        //贷款申请的id
        String id = httpServletRequest.getParameter("id");
        String interest = httpServletRequest.getParameter("interests");
        String updateTime = httpServletRequest.getParameter("updateTime");
        //拿到贷款申请的信息
        LoanApply loanApply0= loanApplyService.queryLoanApplyByID(Integer.parseInt(id));
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
//        Timestamp ts = Timestamp.valueOf(updateTime);
        loanApply0.setUpdateTime(ts);

        //银行和融资企业名字
        String enterprise =loanApply0.getEnterprise();
        System.out.println(enterprise);
        String bank = loanApply0.getBank();
        //差一步比对合同的hash值


        //根据贷款账单的id=》拿到对应库存合同id=》根据库存合同id=》拿到仓单id=》得到仓单价值
        int waNo=inventoryService.queryInventoryByID(loanApply0.getInID()).getWaNo();
        String money = WarehouseReceiptService.queryWarehouseReceiptByID(waNo).getValMoney();

        int m = Integer.parseInt(money);
        //注意加上同态加密
        //以仓单价值的0.7倍贷款,修改贷款申请的状态
        loanApply0.setLoMoney(String.valueOf(m*0.7));
        loanApply0.setStatus("同意发放");//0申请，1同意，2拒绝

        //更新贷款的申请状态
        int i=loanApplyService.updateLoanApply(loanApply0);

        //添加贷款账单。当贷款企业签订银行拟定的贷款质押合同之后，便激活账单。
        int j=loanReciptService.addLoanRecipt(new LoanRecipt(loanApply0.getEnterprise(),loanApply0.getBank(),loanApply0.getID(),
                loanApply0.getLoMoney(), loanApply0.getLoMoney(),"账单待激活",loanApply0.getUpdateTime(),loanApply0.getDueTime()));

//        //贷款账单上链。
//        //拿到银行和融资企业的地址
//        System.out.println(bank+"----"+enterprise);
//        String bankAddr=accountService.findByName(bank).getAddr();
//        String enterpriseAddr=accountService.findByName(enterprise).getAddr();
//        System.out.println(enterpriseAddr);
//
//
////       loanReciptService.addLoanRecipt(new LoanRecipt(enterprise,bank,Integer.parseInt(id),loanApply1.getLoMoney(),"0","",loanApply1.getUpdateTime(),loanApply1.getDueTime()));
//
//        //贷款账单上链
//        String dueTime = String.valueOf(loanApply0.getDueTime());
//        LoanReciptCall loanReciptCall = LoanReciptCall.getInstance(web3j,credentials);
//        boolean bool=loanReciptCall.addLoanRecipt(new BigInteger(id),enterpriseAddr,bankAddr,loanApply0.getLoMoney(),updateTime,dueTime);
//
//        Map<String,String> data = loanReciptCall.getLoanReciptByLoanId(new BigInteger(id));
//        System.out.println(data);
////
        return true;
    }
    @GetMapping("/queryAllLoanApply")
    public List<LoanApply> queryAllLoanApply(){
        return  loanApplyService.queryAllLoanApply();
    }
    @GetMapping("/queryLoanApplyByID/{id}")
    public List<LoanApply> queryLoanApplyByID(@PathVariable("id") int id){
        System.out.println("loanapply"+id);
        List<LoanApply> lists =new LinkedList<>();
        LoanApply loanApply=loanApplyService.queryLoanApplyByID(id);
        System.out.println(loanApply);
        lists.add(loanApply);
        return  lists;
    }
    @GetMapping("/queryLoanApplyByEnterprise/{enterprise}")
    public List<LoanApply> queryLoanApplyByEnterprise(@PathVariable("enterprise") String enterprise){
        return  loanApplyService.queryLoanApplyByEnterprise(enterprise);
    }
    @GetMapping("/queryLoanApplyByBank/{bank}")
    public List<LoanApply> queryLoanApplyByBank(@PathVariable("bank") String bank){
        return  loanApplyService.queryLoanApplyByBank(bank);
    }

    @GetMapping("/queryLoanApplyByStatus/{status}")
    public List<LoanApply> queryLoanApplyByStatus(@PathVariable("status") String status){
        return  loanApplyService.queryLoanApplyByStatus(status);
    }
    @PostMapping("/queryLoanApplyByBankAndEnterpriseAndTime")
    public List<LoanApply> qqueryLoanApplyByBankAndEnterpriseAndTime(HttpServletRequest httpServletRequest){
        String enterprise = httpServletRequest.getParameter("enterprise");
        String bank = httpServletRequest.getParameter("bank");
        String updateTime  = httpServletRequest.getParameter("updateTime");
        return  loanApplyService.queryLoanApplyByBankAndEnterpriseAndTime(bank,enterprise,Timestamp.valueOf(updateTime));
    }
    @GetMapping("/queryLoanApplyByBankAndStatus/{bank}/{status}")
    public List<LoanApply> queryLoanApplyByBankAndStatus(@PathVariable("bank") String bank,@PathVariable("status") String status){
        return  loanApplyService.queryLoanApplyByBankAndStatus(bank, status);
    }
    @GetMapping("/queryLoanApplyByBankAndEnterprise/{bank}/{enterprise}")
    public List<LoanApply> queryLoanApplyByBankAndEnterprise(@PathVariable("bank") String bank,@PathVariable("enterprise") String enterprise){
        return  loanApplyService.queryLoanApplyByBankAndEnterprise(bank, enterprise);
    }
    @GetMapping("/queryLoanApplyByEnterpriseAndStatus/{enterprise}/{status}")
    public List<LoanApply> queryLoanApplyByEnterpriseAndStatus(@PathVariable("enterprise") String enterprise,@PathVariable("status") String status){
        return  loanApplyService.queryLoanApplyByEnterpriseAndStatus(enterprise, status);
    }
    @GetMapping("/queryLoanApplyByBankAndEnterpriseAndStatus/{bank}/{enterprise}/{status}")
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndStatus(@PathVariable("bank") String bank,@PathVariable("enterprise") String enterprise,@PathVariable("status") String status){
        return  loanApplyService.queryLoanApplyByBankAndEnterpriseAndStatus(bank, enterprise, status);
    }
}
