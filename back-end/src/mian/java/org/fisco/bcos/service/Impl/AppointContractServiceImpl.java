package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.AppointContract;
import org.fisco.bcos.mapper.AppointContractMapper;
import org.fisco.bcos.service.AppointContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class AppointContractServiceImpl implements AppointContractService {
    @Autowired
    private  AppointContractMapper AppointContractMapper;


    /**
     * @author: mazhixiu
     * @description: TODO
     * @date: 2021/2/17 10:36
     * @param AppointContract
     * @return
     */

    @Override
    public int addAppointContract(AppointContract AppointContract) {
        return AppointContractMapper.addAppointContract(AppointContract);
    }

    @Override
    public int addAppointContract1(AppointContract AppointContract) {
        return 0;
    }

    @Override
    public int updateAppointContract(AppointContract AppointContract) {
        return AppointContractMapper.updateAppointContract(AppointContract);
    }

    @Override
    public int deleteAppointContract(int id) {
        return AppointContractMapper.deleteAppointContract(id);
    }

    @Override
    public List<AppointContract> queryAllAppointContract() {
        return AppointContractMapper.queryAllAppointContract();
    }

    @Override
    public List<AppointContract> queryAppointContractByLogistics(String logistics) {
        return AppointContractMapper.queryAppointContractByLogistics(logistics);
    }

    @Override
    public List<AppointContract> queryAppointContractByLogisticsAndStatus(String logistics, String status) {
        return AppointContractMapper.queryAppointContractByLogisticsAndStatus(logistics,status);
    }

    @Override
    public AppointContract queryAppointContractByLogisticsAndBank(String logistics, String bank) {
        return AppointContractMapper.queryAppointContractByLogisticsAndBank(logistics,bank);
    }

    @Override
    public AppointContract queryAppointContractByLogisticsAndBankAndTime(String logistics, String bank, Timestamp time) {
        return AppointContractMapper.queryAppointContractByLogisticsAndBankAndTime(logistics, bank, time);
    }

    @Override
    public List<AppointContract> queryAppointContractByBank(String bank) {
        return AppointContractMapper.queryAppointContractByBank(bank);
    }

    @Override
    public List<AppointContract> queryAppointContractByBankAndStatus(String bank, String status) {
        return AppointContractMapper.queryAppointContractByBankAndStatus(bank,status);
    }

    @Override
    public AppointContract queryAppointContractByID(int id) {
        return AppointContractMapper.queryAppointContractByID(id);
    }

}
