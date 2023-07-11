package org.fisco.bcos.service.Impl;


import org.fisco.bcos.entity.WarehouseReceipt;
import org.fisco.bcos.mapper.WarehouseReceiptMapper;
import org.fisco.bcos.service.WarehouseReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/21 17:08
 * @Email:861359297@qq.com
 */
@Service
public class WarehouseReceiptServiceImpl implements WarehouseReceiptService {
    @Autowired
    private WarehouseReceiptMapper warehouseReceiptMapper;
    @Override
    public int addWarehouseReceipt(WarehouseReceipt WarehouseReceipt) {
        System.out.println(WarehouseReceipt);
        return warehouseReceiptMapper.addWarehouseReceipt(WarehouseReceipt);
    }

    @Override
    public int updateWarehouseReceipt(WarehouseReceipt WarehouseReceipt) {
        return warehouseReceiptMapper.updateWarehouseReceipt(WarehouseReceipt);
    }

    @Override
    public int deleteWarehouseReceipt(int id) {
        return 0;
    }

    @Override
    public List<WarehouseReceipt> queryAllWarehouseReceipt() {
        return warehouseReceiptMapper.queryAllWarehouseReceipt();
    }

    @Override
    public WarehouseReceipt queryWarehouseReceiptByID(int id) {
        return warehouseReceiptMapper.queryWarehouseReceiptByID(id);
    }

    @Override
    public List<WarehouseReceipt> queryWarehouseReceiptByHolder(String holder) {
        return warehouseReceiptMapper.queryWarehouseReceiptByHolder(holder);
    }

    @Override
    public List<WarehouseReceipt> queryWarehouseReceiptByLogistics(String logistics) {
        return warehouseReceiptMapper.queryWarehouseReceiptByLogistics(logistics);
    }

    @Override
    public List<WarehouseReceipt> queryWarehouseReceiptByLogisticsAndHolder(String logistics, String holder) {
        return null;
    }

    @Override
    public WarehouseReceipt queryWarehouseReceiptByLogisticsAndHolderAndTime(String logistics, String holder, Timestamp time) {
        return warehouseReceiptMapper.queryWarehouseReceiptByLogisticsAndHolderAndTime(logistics, holder, time);
    }

    @Override
    public List<WarehouseReceipt> queryWarehouseReceiptByStatus(String status) {
        return warehouseReceiptMapper.queryWarehouseReceiptByStatus(status);
    }

    @Override
    public List<WarehouseReceipt> queryWarehouseReceiptByLogisticsAndStatus(String status, String logistics) {
        return warehouseReceiptMapper.queryWarehouseReceiptByLogisticsAndStatus(status, logistics);
    }

    @Override
    public List<WarehouseReceipt> queryWarehouseReceiptByHolderAndStatus(String holder, String status) {
        return warehouseReceiptMapper.queryWarehouseReceiptByHolderAndStatus(holder, status);
    }
}
