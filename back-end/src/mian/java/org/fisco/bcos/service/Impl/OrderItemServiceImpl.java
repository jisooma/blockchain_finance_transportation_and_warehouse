package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.OrderItem;
import org.fisco.bcos.mapper.OrderItemMapper;
import org.fisco.bcos.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/30 9:23
 * @Email:861359297@qq.com
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public int addOrderItem(OrderItem OrderItem) {
        return orderItemMapper.addOrderItem(OrderItem);
    }

    @Override
    public int updateOrderItem(OrderItem orderItem) {
        return orderItemMapper.updateOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> queryOrderItemByStatus(String status) {
        return orderItemMapper.queryOrderItemByStatus(status);
    }

    @Override
    public List<OrderItem> queryAllOrderItem() {
        return orderItemMapper.queryAllOrderItem();
    }

    @Override
    public OrderItem queryOrderItemById(int id) {
        return orderItemMapper.queryOrderItemById(id);
    }

    @Override
    public OrderItem queryOrderItemByUUID(String uuid) {
        return orderItemMapper.queryOrderItemByUUID(uuid);
    }
    @Override
    public int deleteOrderItem(int id) {
        return orderItemMapper.deleteOrderItem(id);
    }
}
