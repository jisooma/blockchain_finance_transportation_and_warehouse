package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.LoanContract;
import org.fisco.bcos.mapper.LoanContractMapper;
import org.fisco.bcos.service.LoanContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/26 10:46
 * @Email:861359297@qq.com
 */
@Service
public class LoanContractServiceImpl implements LoanContractService {
    @Autowired
    private LoanContractMapper LoanContractMapper;
    @Override
    public int addLoanContract(LoanContract LoanContract) {
        return LoanContractMapper.addLoanContract(LoanContract);
    }

    @Override
    public int updateLoanContract(LoanContract LoanContract) {
        return LoanContractMapper.updateLoanContract(LoanContract);
    }

    @Override
    public List<LoanContract> queryAllLoanContract() {
        return LoanContractMapper.queryAllLoanContract();
    }

    @Override
    public LoanContract queryLoanContractByID(int id) {
        return LoanContractMapper.queryLoanContractByID(id);
    }

    @Override
    public LoanContract queryLoanContractByReciptID(int id) {
        return LoanContractMapper.queryLoanContractByReciptID(id);
    }

    @Override
    public List<LoanContract> queryLoanContractByStatus(String status) {
        return LoanContractMapper.queryLoanContractByStatus(status);
    }

    @Override
    public List<LoanContract> queryLoanContractByEnterprise(String enterprise) {
        return LoanContractMapper.queryLoanContractByEnterprise(enterprise);
    }

    @Override
    public List<LoanContract> queryLoanContractByBank(String bank) {
        return LoanContractMapper.queryLoanContractByBank(bank);
    }

    @Override
    public List<LoanContract> queryLoanContractByBankAndEnterprise(String bank, String enterprise) {
        return LoanContractMapper.queryLoanContractByBankAndEnterprise(bank,enterprise);
    }

    @Override
    public LoanContract queryLoanContractByBankAndEnterpriseAndTime(String bank, String enterprise, Timestamp ts) {
        return LoanContractMapper.queryLoanContractByBankAndEnterpriseAndTime(bank, enterprise, ts);
    }

    @Override
    public List<LoanContract> queryLoanContractByBankAndStatus(String bank, String status) {
        return LoanContractMapper.queryLoanContractByBankAndStatus(bank,status);
    }

    @Override
    public List<LoanContract> queryLoanContractByEnterpriseAndStatus(String enterprise, String status) {
        return LoanContractMapper.queryLoanContractByEnterpriseAndStatus(enterprise, status);
    }

    @Override
    public List<LoanContract> queryLoanContractByBankAndEnterpriseAndStatus(String bank, String enterprise, String status) {
        return LoanContractMapper.queryLoanContractByBankAndEnterpriseAndStatus(bank, enterprise, status);
    }
}
