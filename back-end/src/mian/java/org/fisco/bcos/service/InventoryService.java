package org.fisco.bcos.service;

import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Inventory;

import java.sql.Timestamp;
import java.util.List;

public interface InventoryService {
    public int addInventory(Inventory inventory);
    public int updateInventory(Inventory inventory);
    public List<Inventory> queryInventoryByEnterprise(String enterprise);
    public List<Inventory> queryInventoryByLogistics(String logistics);
    public Inventory queryInventoryByLogisticsAndEnterprise(String logistics,String enterprise);
    public Inventory queryInventoryByLogisticsAndEnterpriseAndTime(String logistics, String enterprise, Timestamp ts);
    public List<Inventory> queryInventoryByLogisticsAndEnterpriseAndStatus(String logistics, String enterprise, String Status);
    public int deleteInventory(int id);
    public Inventory queryInventoryByID(int id);
    public List<Inventory> queryInventoryByEnterpriseAndStatus(String enterprise,String status);
    public List<Inventory> queryInventoryByLogisticsAndStatus(String logistics,String status);
    public List<Inventory> queryInventoryByStatus(String status);
}
