package org.fisco.bcos.service.Impl;


import org.fisco.bcos.entity.Collateral;
import org.fisco.bcos.mapper.CollateralMapper;
import org.fisco.bcos.service.CollateralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CollateralServiceImpl implements CollateralService {
    @Autowired
    private CollateralMapper collateralMapper;
    @Override
    public int addCollateral(Collateral collateral) {
        return collateralMapper.addCollateral(collateral);
    }

    @Override
    public int updateCollateral(Collateral collateral) {
        return collateralMapper.updateCollateral(collateral);
    }

    @Override
    public int deleteCollateral(int id) {
        return 0;
    }

    @Override
    public List<Collateral> queryAllCollateral() {
        return collateralMapper.queryAllCollateral();
    }

    @Override
    public List<Collateral> queryCollateralByConsigner(String consigner) {
        return collateralMapper.queryCollateralByConsigner(consigner);
    }

    @Override
    public List<Collateral> queryCollateralByEnterprise(String enterprise) {
        return collateralMapper.queryCollateralByEnterprise(enterprise);
    }

    @Override
    public Collateral queryCollateralByEnterpriseAndConsigner(String enterprise, String consigner) {
        return collateralMapper.queryCollateralByEnterpriseAndConsigner(enterprise, consigner);
    }

    @Override
    public Collateral queryCollateralByID(int id) {
        return collateralMapper.queryCollateralByID(id);
    }

    @Override
    public List<Collateral> queryCollateralByConsignerAndStatus(String consigner, String status) {
        return collateralMapper.queryCollateralByConsignerAndStatus(consigner, status);
    }

    @Override
    public List<Collateral> queryCollateralByEnterpriseAndStatus(String enterprise, String status) {
        return collateralMapper.queryCollateralByEnterpriseAndStatus(enterprise, status);
    }

    @Override
    public Collateral queryCollateralByEnterpriseAndConsignerAndTime(String enterprise, String consigner, Timestamp ts) {
        return collateralMapper.queryCollateralByEnterpriseAndConsignerAndTime(enterprise, consigner, ts);
    }

    @Override
    public List<Collateral> queryCollateralByStatus(String status) {
        return collateralMapper.queryCollateralByStatus(status);
    }
}
