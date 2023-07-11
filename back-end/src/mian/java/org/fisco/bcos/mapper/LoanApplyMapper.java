package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.LoanApply;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface LoanApplyMapper {
    public int addLoanApply(LoanApply loanApply);
    public int updateLoanApply(LoanApply loanApply);
    public List<LoanApply> queryAllLoanApply();
    public LoanApply queryLoanApplyByID(@Param("id") int id);
    public List<LoanApply> queryLoanApplyByStatus(@Param("status") String status);
    public List<LoanApply> queryLoanApplyByEnterprise(@Param("enterprise") String enterprise);
    public List<LoanApply> queryLoanApplyByBank(@Param("bank") String bank);
    public List<LoanApply> queryLoanApplyByBankAndEnterprise(@Param("bank") String bank,@Param("enterprise") String enterprise);
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndTime(@Param("bank") String bank, @Param("enterprise") String enterprise, @Param("updateTime")Timestamp ts);
    public List<LoanApply> queryLoanApplyByBankAndStatus(@Param("bank") String bank,@Param("status") String status);
    public List<LoanApply> queryLoanApplyByEnterpriseAndStatus(@Param("enterprise") String enterprise,@Param("status") String status);
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndStatus(@Param("bank") String bank,@Param("enterprise") String enterprise,@Param("status") String status);
}
