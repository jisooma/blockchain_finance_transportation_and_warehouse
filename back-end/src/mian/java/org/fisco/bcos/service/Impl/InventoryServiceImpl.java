package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.Inventory;
import org.fisco.bcos.mapper.InventoryMapper;
import org.fisco.bcos.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class InventoryServiceImpl  implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Override
    public int addInventory(Inventory inventory) {
        return inventoryMapper.addInventory(inventory);
    }

    @Override
    public int updateInventory(Inventory inventory) {
        return inventoryMapper.updateInventory(inventory);
    }

    @Override
    public List<Inventory> queryInventoryByEnterprise(String enterprise) {
        return inventoryMapper.queryInventoryByEnterprise(enterprise);
    }

    @Override
    public List<Inventory> queryInventoryByLogistics(String logistics) {
        return inventoryMapper.queryInventoryByLogistics(logistics);
    }

    @Override
    public Inventory queryInventoryByLogisticsAndEnterprise(String logistics, String enterprise) {
        return inventoryMapper.queryInventoryByLogisticsAndEnterprise(logistics, enterprise);
    }

    @Override
    public Inventory queryInventoryByLogisticsAndEnterpriseAndTime(String logistics, String enterprise, Timestamp ts) {
        return inventoryMapper.queryInventoryByLogisticsAndEnterpriseAndTime(logistics, enterprise, ts);
    }

    @Override
    public List<Inventory> queryInventoryByLogisticsAndEnterpriseAndStatus(String logistics, String enterprise, String Status) {
        return inventoryMapper.queryInventoryByLogisticsAndEnterpriseAndStatus(logistics, enterprise, Status);
    }

    @Override
    public int deleteInventory(int id) {
        return 0;
    }

    @Override
    public Inventory queryInventoryByID(int id) {
        return inventoryMapper.queryInventoryByID(id);
    }

    @Override
    public List<Inventory> queryInventoryByEnterpriseAndStatus(String enterprise, String status) {
        return inventoryMapper.queryInventoryByEnterpriseAndStatus(enterprise, status);
    }

    @Override
    public List<Inventory> queryInventoryByLogisticsAndStatus(String logistics, String status) {
        return inventoryMapper.queryInventoryByLogisticsAndStatus(logistics, status);
    }

    @Override
    public List<Inventory> queryInventoryByStatus(String status) {
        return inventoryMapper.queryInventoryByStatus(status);
    }
}
