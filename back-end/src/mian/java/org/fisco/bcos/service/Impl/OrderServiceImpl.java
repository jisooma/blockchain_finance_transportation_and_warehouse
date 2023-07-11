package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.Order;
import org.fisco.bcos.mapper.OrderMapper;
import org.fisco.bcos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/29 17:08
 * @Email:861359297@qq.com
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public List<Order> queryOrderBySupplier(String supplier) {
        return orderMapper.queryOrderBySupplier(supplier);
    }

    @Override
    public List<Order> queryOrderByBuyer(String buyer) {
        return orderMapper.queryOrderByBuyer(buyer);
    }

    @Override
    public List<Order> queryAllOrder() {
        return orderMapper.queryAllOrder();
    }

    @Override
    public int deleteOrder(int id) {
        return orderMapper.deleteOrder(id);
    }

    @Override
    public Order queryOrderById(int id) {
        return orderMapper.queryOrderById(id);
    }

    @Override
    public List<Order> queryOrderByBuyerAndStatus(String buyer, String status) {
        return orderMapper.queryOrderByBuyerAndStatus(buyer, status);
    }

    @Override
    public List<Order> queryOrderBySupplierAndStatus(String supplier, String status) {
        return orderMapper.queryOrderBySupplierAndStatus(supplier, status);
    }

    @Override
    public Order queryOrderByUUId(String id) {
        return orderMapper.queryOrderByUUId(id);
    }
}
