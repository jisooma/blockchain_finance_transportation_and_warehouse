package org.fisco.bcos.service;


import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Collateral;

import java.sql.Timestamp;
import java.util.List;

public interface CollateralService {
    public int addCollateral(Collateral collateral);
    public int updateCollateral(Collateral collateral);
    public int deleteCollateral( int id);
    public List<Collateral> queryAllCollateral();
    public List<Collateral> queryCollateralByConsigner(String consigner);
    public List<Collateral> queryCollateralByEnterprise(String enterprise);
    public Collateral queryCollateralByEnterpriseAndConsigner(String enterprise,String consigner);
    public Collateral queryCollateralByID( int id);
    public List<Collateral> queryCollateralByConsignerAndStatus(String consigner, String status);
    public List<Collateral> queryCollateralByEnterpriseAndStatus(String enterprise, String status);
    public Collateral queryCollateralByEnterpriseAndConsignerAndTime(String enterprise, String consigner, Timestamp ts);
    public List<Collateral> queryCollateralByStatus(String status);
}
