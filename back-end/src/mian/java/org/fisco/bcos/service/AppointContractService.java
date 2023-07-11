package org.fisco.bcos.service;

import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.AppointContract;

import java.sql.Timestamp;
import java.util.List;

public interface AppointContractService {
    public int addAppointContract(AppointContract AppointContract);
    public int addAppointContract1(AppointContract AppointContract);
    public int updateAppointContract(AppointContract AppointContract);
    public int deleteAppointContract( int id);
    public List<AppointContract> queryAllAppointContract();
    public List<AppointContract> queryAppointContractByLogistics( String logistics);
    public List<AppointContract> queryAppointContractByLogisticsAndStatus( String logistics,String status);
    public AppointContract queryAppointContractByLogisticsAndBank(String logistics,String bank);
    public AppointContract queryAppointContractByLogisticsAndBankAndTime(String logistics, String bank, Timestamp time);
    public List<AppointContract> queryAppointContractByBank(String bank);
    public List<AppointContract> queryAppointContractByBankAndStatus(String bank,String status);
    public AppointContract queryAppointContractByID(int id);
}
