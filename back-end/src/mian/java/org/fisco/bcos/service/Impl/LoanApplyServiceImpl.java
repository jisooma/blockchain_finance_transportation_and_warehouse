package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.LoanApply;
import org.fisco.bcos.mapper.LoanApplyMapper;
import org.fisco.bcos.service.LoanApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LoanApplyServiceImpl implements LoanApplyService {
    @Autowired
    private LoanApplyMapper loanApplyMapper;
    @Override
    public int addLoanApply(LoanApply loanApply) {
        return loanApplyMapper.addLoanApply(loanApply);
    }

    @Override
    public int updateLoanApply(LoanApply loanApply) {
        return loanApplyMapper.updateLoanApply(loanApply);
    }

    @Override
    public List<LoanApply> queryAllLoanApply() {
        return loanApplyMapper.queryAllLoanApply();
    }

    @Override
    public List<LoanApply> queryLoanApplyByEnterprise(String enterprise) {
        return loanApplyMapper.queryLoanApplyByEnterprise(enterprise);
    }

    @Override
    public List<LoanApply> queryLoanApplyByBank(String bank) {
        return loanApplyMapper.queryLoanApplyByBank(bank);
    }

    @Override
    public LoanApply queryLoanApplyByID(int id) {
        return loanApplyMapper.queryLoanApplyByID(id);
    }

    @Override
    public List<LoanApply> queryLoanApplyByBankAndEnterprise(String bank, String enterprise) {
        return loanApplyMapper.queryLoanApplyByBankAndEnterprise(bank, enterprise);
    }

    @Override
    public List<LoanApply> queryLoanApplyByStatus(String status) {
        return loanApplyMapper.queryLoanApplyByStatus(status);
    }

    @Override
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndTime(String bank, String enterprise, Timestamp ts) {
        return loanApplyMapper.queryLoanApplyByBankAndEnterpriseAndTime(bank, enterprise, ts);
    }

    @Override
    public List<LoanApply> queryLoanApplyByBankAndStatus(String bank, String status) {
        return loanApplyMapper.queryLoanApplyByBankAndStatus(bank, status);
    }

    @Override
    public List<LoanApply> queryLoanApplyByEnterpriseAndStatus(String enterprise, String status) {
        return loanApplyMapper.queryLoanApplyByEnterpriseAndStatus(enterprise, status);
    }

    @Override
    public List<LoanApply> queryLoanApplyByBankAndEnterpriseAndStatus(String bank, String enterprise, String status) {
        return loanApplyMapper.queryLoanApplyByBankAndEnterpriseAndStatus(bank, enterprise, status);
    }
}
