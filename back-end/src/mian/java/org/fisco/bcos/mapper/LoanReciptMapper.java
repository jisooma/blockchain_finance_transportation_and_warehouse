package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.AppointContract;
import org.fisco.bcos.entity.LoanRecipt;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface LoanReciptMapper {
    public int addLoanRecipt(LoanRecipt loanRecipt);
    public int updateLoanRecipt(LoanRecipt loanRecipt);
//    public int deleteLoanRecipt(@Param("id") int id);
    public List<LoanRecipt> queryAllLoanRecipt();
    public List<LoanRecipt> queryLoanReciptByEnterpirse(@Param("enterprise") String enterprise);
    public List<LoanRecipt> queryLoanReciptByEnterpirseAndStatus(@Param("enterprise") String enterprise,@Param("status") String status);
    public List<LoanRecipt> queryLoanReciptByStatus(@Param("status") String status);
    public List<LoanRecipt> queryLoanReciptByBankAndStatus(@Param("bank") String bank,@Param("status") String status);
    public List<LoanRecipt> queryLoanReciptByenterpriseAndBank(@Param("enterprise") String enterprise,@Param("bank") String bank);
    public LoanRecipt queryLoanReciptByenterpriseAndBankAndTime(@Param("enterprise")String enterprise, @Param("bank")String bank,@Param("time") Timestamp time);
    public List<LoanRecipt> queryLoanReciptByBank(@Param("bank") String bank);
    public LoanRecipt queryLoanReciptByID(@Param("id") int id);
    public LoanRecipt queryLoanReciptByloanID(@Param("loanID") int loanID);

}
