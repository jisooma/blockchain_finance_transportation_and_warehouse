package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.WarehouseReceipt;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/2/21 16:45
 * @Email:861359297@qq.com
 */
@Mapper
@Repository
public interface WarehouseReceiptMapper {
    public int addWarehouseReceipt(WarehouseReceipt WarehouseReceipt);
    public int updateWarehouseReceipt(WarehouseReceipt WarehouseReceipt);
    public int deleteWarehouseReceipt(@Param("id") int id);
    public List<WarehouseReceipt> queryAllWarehouseReceipt();
    public WarehouseReceipt queryWarehouseReceiptByID(@Param("id") int id);
    public List<WarehouseReceipt> queryWarehouseReceiptByHolder(@Param("holder") String holder);
    public List<WarehouseReceipt> queryWarehouseReceiptByLogistics(@Param("logistics") String logistics);
    public List<WarehouseReceipt> queryWarehouseReceiptByLogisticsAndHolder(@Param("logistics") String logistics,@Param("holder") String holder);
    public WarehouseReceipt queryWarehouseReceiptByLogisticsAndHolderAndTime(@Param("logistics") String logistics,@Param("holder") String holder,@Param("time") Timestamp time);
    public List<WarehouseReceipt> queryWarehouseReceiptByStatus(@Param("status") String status);
    public List<WarehouseReceipt> queryWarehouseReceiptByLogisticsAndStatus(@Param("status") String status,@Param("logistics") String logistics);
    public List<WarehouseReceipt> queryWarehouseReceiptByHolderAndStatus(@Param("holder") String holder,@Param("status") String status);
}
