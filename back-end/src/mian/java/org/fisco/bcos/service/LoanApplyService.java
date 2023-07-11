package org.fisco.bcos.service;


import org.fisco.bcos.entity.LoanApply;

import java.sql.Timestamp;
import java.util.List;

public interface LoanApplyService {
    public int addLoanApply(LoanApply loanApply);
    public int updateLoanApply(LoanApply loanApply);
    public List<LoanApply> queryAllLoanApply();
    public List<LoanApply> queryLoanApplyByEnterprise(String enterprise);
    public List<LoanApply> queryLoanApplyByBank(String bank);
    public LoanApply queryLoanApplyByID(int id);
    public List<LoanApply> queryLoanApplyByBankAndEnterprise( String bank, String enterprise);
    public List<LoanApply> queryLoanApplyByStatus(String status);
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndTime(String bank, String enterprise, Timestamp ts);
    public List<LoanApply> queryLoanApplyByBankAndStatus( String bank,String status);
    public List<LoanApply> queryLoanApplyByEnterpriseAndStatus(String enterprise, String status);
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndStatus( String bank, String enterprise,String status);


}
