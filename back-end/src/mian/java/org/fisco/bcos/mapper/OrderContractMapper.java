package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.OrderContract;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/30 10:36
 * @Email:861359297@qq.com
 */
@Mapper
@Repository
public interface OrderContractMapper {
    public int addOrderContract(OrderContract OrderContract);
    public int updateOrderContract(OrderContract OrderContract);
    public List<OrderContract> queryOrderContractBySupplier(@Param("supplier") String supplier);
    public List<OrderContract> queryOrderContractBySupplierAndStatus(@Param("supplier") String supplier,@Param("status") String status);
    public List<OrderContract> queryOrderContractByBuyer(@Param("buyer") String buyer);
    public List<OrderContract> queryOrderContractByBuyerAndStatus(@Param("buyer") String buyer,@Param("status") String status);
    public List<OrderContract> queryAllOrderContract();
    public OrderContract queryOrderContractById(@Param("id") int id);
    public OrderContract queryOrderContractByUUId(@Param("uuid") String id);
    public OrderContract queryOrderContractByStatus(@Param("status") String status);
    public int deleteOrderContract(@Param("id") int id);

}
