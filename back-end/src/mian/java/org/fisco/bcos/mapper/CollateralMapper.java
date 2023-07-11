package org.fisco.bcos.mapper;

/**
 * @author mazhixiu
 * @date 2021/2/17 14:48
 * @Email:861359297@qq.com
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Collateral;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface CollateralMapper {
    public int addCollateral(Collateral collateral);
    public int updateCollateral(Collateral collateral);
    public int deleteCollateral(@Param("id") int id);
    public List<Collateral> queryAllCollateral();
    public List<Collateral> queryCollateralByConsigner(@Param("consigner") String consigner);
    public List<Collateral> queryCollateralByConsignerAndStatus(@Param("consigner") String consigner,@Param("status") String status);
    public List<Collateral> queryCollateralByEnterpriseAndStatus(@Param("enterprise") String enterprise,@Param("status") String status);
    public List<Collateral> queryCollateralByEnterprise(@Param("enterprise") String enterprise);
    public List<Collateral> queryCollateralByStatus(@Param("status") String status);
    public Collateral queryCollateralByEnterpriseAndConsigner(@Param("enterprise") String enterprise, @Param("consigner") String consigner);
    public Collateral queryCollateralByEnterpriseAndConsignerAndTime(@Param("enterprise") String enterprise, @Param("consigner") String consigner, @Param("time")Timestamp time);
    public Collateral queryCollateralByID(@Param("id") int id);
}
