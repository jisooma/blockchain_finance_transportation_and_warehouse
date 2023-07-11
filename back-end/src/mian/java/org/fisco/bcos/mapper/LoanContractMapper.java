package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.LoanContract;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/26 10:02
 * @Email:861359297@qq.com
 */
@Mapper
@Repository
public interface LoanContractMapper {
    public int addLoanContract(LoanContract LoanContract);
    public int updateLoanContract(LoanContract LoanContract);
    public List<LoanContract> queryAllLoanContract();
    public LoanContract queryLoanContractByID(@Param("id") int id);
    public LoanContract queryLoanContractByReciptID(@Param("id") int id);
    public List<LoanContract> queryLoanContractByStatus(@Param("status") String status);
    public List<LoanContract> queryLoanContractByEnterprise(@Param("enterprise") String enterprise);
    public List<LoanContract> queryLoanContractByBank(@Param("bank") String bank);
    public List<LoanContract> queryLoanContractByBankAndEnterprise(@Param("bank") String bank,@Param("enterprise") String enterprise);
    public LoanContract queryLoanContractByBankAndEnterpriseAndTime(@Param("bank") String bank, @Param("enterprise") String enterprise, @Param("startTime") Timestamp ts);
    public List<LoanContract> queryLoanContractByBankAndStatus(@Param("bank") String bank,@Param("status") String status);
    public List<LoanContract> queryLoanContractByEnterpriseAndStatus(@Param("enterprise") String enterprise,@Param("status") String status);
    public List<LoanContract> queryLoanContractByBankAndEnterpriseAndStatus(@Param("bank") String bank,@Param("enterprise") String enterprise,@Param("status") String status);
}
