package org.fisco.bcos.mapper;

/**
 * @author mazhixiu
 * @date 2021/2/17 14:35
 * @Email:861359297@qq.com
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.AppointContract;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface AppointContractMapper {

    public int addAppointContract(AppointContract AppointContract);
    public int addAppointContract1(AppointContract AppointContract);
    public int updateAppointContract(AppointContract AppointContract);
    public int deleteAppointContract(@Param("id") int id);
    public List<AppointContract> queryAllAppointContract();
    public AppointContract queryAppointContractByID(@Param("id") int id);
    public List<AppointContract> queryAppointContractByLogistics(@Param("logistics") String logistics);
    public List<AppointContract> queryAppointContractByLogisticsAndStatus(@Param("logistics") String logistics,@Param("status")String status);
    public AppointContract queryAppointContractByLogisticsAndBank(@Param("logistics") String logistics,@Param("bank") String bank);
    public AppointContract queryAppointContractByLogisticsAndBankAndTime(@Param("logistics") String logistics,@Param("bank") String bank,@Param("time") Timestamp time);
    public List<AppointContract> queryAppointContractByBank(@Param("bank") String bank);
    public List<AppointContract> queryAppointContractByBankAndStatus(@Param("bank") String bank,@Param("status")String status);

}
