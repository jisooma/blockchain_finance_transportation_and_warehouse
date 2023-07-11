package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/29 16:57
 * @Email:861359297@qq.com
 */
@Mapper
@Repository
public interface OrderMapper {
    public int addOrder(Order Order);
    public int updateOrder(Order Order);
    public List<Order> queryOrderBySupplier(@Param("supplier") String supplier);
    public List<Order> queryOrderBySupplierAndStatus(@Param("supplier") String supplier,@Param("status") String status);
    public List<Order> queryOrderByBuyer(@Param("buyer") String buyer);
    public List<Order> queryOrderByBuyerAndStatus(@Param("buyer") String buyer,@Param("status") String status);
    public List<Order> queryAllOrder();
    public Order queryOrderById(@Param("id") int id);
    public Order queryOrderByUUId(@Param("uuid") String id);
    public int deleteOrder(@Param("id") int id);
}
