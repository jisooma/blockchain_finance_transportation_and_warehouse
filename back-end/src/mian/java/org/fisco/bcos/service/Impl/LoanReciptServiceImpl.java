package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.LoanRecipt;
import org.fisco.bcos.mapper.LoanReciptMapper;
import org.fisco.bcos.service.LoanReciptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LoanReciptServiceImpl  implements LoanReciptService {
    @Autowired
    private LoanReciptMapper loanReciptMapper;

    @Override
    public int addLoanRecipt(LoanRecipt loanRecipt) {
        return loanReciptMapper.addLoanRecipt(loanRecipt);
    }

    @Override
    public int updateLoanRecipt(LoanRecipt loanRecipt) {
        return loanReciptMapper.updateLoanRecipt(loanRecipt);
    }

    @Override
    public int deleteLoanRecipt(int id) {
//        return loanReciptMapper.deleteLoanRecipt(id);
        return 0;
    }

    @Override
    public List<LoanRecipt> queryAllLoanRecipt() {
        return loanReciptMapper.queryAllLoanRecipt();
    }

    @Override
    public List<LoanRecipt> queryLoanReciptByEnterpirse(String enterprise) {
        return loanReciptMapper.queryLoanReciptByEnterpirse(enterprise);
    }

    @Override
    public List<LoanRecipt> queryLoanReciptByenterpriseAndBank(String enterprise, String bank) {
        return loanReciptMapper.queryLoanReciptByenterpriseAndBank(enterprise, bank);
    }

    @Override
    public LoanRecipt queryLoanReciptByenterpriseAndBankAndTime(String enterprise, String bank, Timestamp time) {
        return loanReciptMapper.queryLoanReciptByenterpriseAndBankAndTime(enterprise, bank, time);
    }

    @Override
    public List<LoanRecipt> queryLoanReciptByBank(String bank) {
        return loanReciptMapper.queryLoanReciptByBank(bank);
    }

    @Override
    public LoanRecipt queryLoanReciptByID(int id) {
        return loanReciptMapper.queryLoanReciptByID(id);
    }

    @Override
    public List<LoanRecipt> queryLoanReciptByEnterpirseAndStatus(String enterprise, String status) {
        return loanReciptMapper.queryLoanReciptByEnterpirseAndStatus(enterprise, status);
    }

    @Override
    public List<LoanRecipt> queryLoanReciptByStatus(String status) {
        return loanReciptMapper.queryLoanReciptByStatus(status);
    }

    @Override
    public List<LoanRecipt> queryLoanReciptByBankAndStatus(String bank, String status) {
        return loanReciptMapper.queryLoanReciptByBankAndStatus(bank, status);
    }

    @Override
    public LoanRecipt queryLoanReciptByloanID(int loanID) {
        return loanReciptMapper.queryLoanReciptByloanID(loanID);
    }
}
