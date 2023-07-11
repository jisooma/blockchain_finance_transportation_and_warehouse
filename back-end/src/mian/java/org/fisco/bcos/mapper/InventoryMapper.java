package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Inventory;
import org.fisco.bcos.entity.Respository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface InventoryMapper{
    public int addInventory(Inventory inventory);
    public int updateInventory(Inventory inventory);
    public List<Inventory> queryInventoryByEnterprise(@Param("enterprise") String enterprise);
    public List<Inventory> queryInventoryByEnterpriseAndStatus(@Param("enterprise") String enterprise,@Param("status") String status);
    public List<Inventory> queryInventoryByLogistics(@Param("logistics") String logistics);
    public List<Inventory> queryInventoryByLogisticsAndStatus(@Param("logistics") String logistics,@Param("status") String status);
    public Inventory queryInventoryByLogisticsAndEnterpriseAndTime(@Param("logistics") String logistics,@Param("enterprise") String enterprise,@Param("ts") Timestamp ts);
    public List<Inventory> queryInventoryByLogisticsAndEnterpriseAndStatus(@Param("logistics") String logistics,@Param("enterprise") String enterprise,@Param("status") String status);
    public Inventory queryInventoryByLogisticsAndEnterprise(@Param("logistics") String logistics,@Param("enterprise") String enterprise);
    public int deleteInventory(@Param("id") int id);
    public Inventory queryInventoryByID(@Param("id") int id);
    public List<Inventory> queryInventoryByStatus(@Param("status") String status);
//    public Respository queryRepositoryByreNo();
}
