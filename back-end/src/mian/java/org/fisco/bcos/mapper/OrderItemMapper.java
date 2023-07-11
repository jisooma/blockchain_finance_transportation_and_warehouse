package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/29 21:41
 * @Email:861359297@qq.com
 */
@Mapper
@Repository
public interface OrderItemMapper {
    public int addOrderItem(OrderItem OrderItem);
    public int updateOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderItemByStatus(@Param("status") String status);
    public List<OrderItem> queryAllOrderItem();
    public OrderItem queryOrderItemById(@Param("id") int id);
    public OrderItem queryOrderItemByUUID(@Param("uuid")String uuid);
    public int deleteOrderItem(@Param("id") int id);
}
