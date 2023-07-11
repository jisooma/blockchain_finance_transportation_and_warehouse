package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.OrderContract;
import org.fisco.bcos.mapper.OrderContractMapper;
import org.fisco.bcos.service.OrderContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/30 10:55
 * @Email:861359297@qq.com
 */
@Service
public class OrderContractServiceImpl implements OrderContractService {
    @Autowired
    private OrderContractMapper orderContractMapper;
    @Override
    public int addOrderContract(OrderContract orderContract) {
        return orderContractMapper.addOrderContract(orderContract);
    }

    @Override
    public int updateOrderContract(OrderContract OrderContract) {
        return orderContractMapper.updateOrderContract(OrderContract);
    }

    @Override
    public List<OrderContract> queryOrderContractBySupplier(String supplier) {
        return orderContractMapper.queryOrderContractBySupplier(supplier);
    }

    @Override
    public List<OrderContract> queryOrderContractBySupplierAndStatus(String supplier, String status) {
        return orderContractMapper.queryOrderContractBySupplierAndStatus(supplier, status);
    }

    @Override
    public List<OrderContract> queryOrderContractByBuyer(String buyer) {
        return orderContractMapper.queryOrderContractByBuyer(buyer);
    }

    @Override
    public List<OrderContract> queryOrderContractByBuyerAndStatus(String buyer, String status) {
        return orderContractMapper.queryOrderContractByBuyerAndStatus(buyer, status);
    }

    @Override
    public List<OrderContract> queryAllOrderContract() {
        return orderContractMapper.queryAllOrderContract();
    }

    @Override
    public OrderContract queryOrderContractById(int id) {
        return orderContractMapper.queryOrderContractById(id);
    }

    @Override
    public OrderContract queryOrderContractByUUId(String id) {
        return orderContractMapper.queryOrderContractByUUId(id);
    }

    @Override
    public OrderContract queryOrderContractByStatus(String status) {
        return orderContractMapper.queryOrderContractByStatus(status);
    }

    @Override
    public List<OrderContract> queryMyOrderContract(String name) {
        return null;
    }

    @Override
    public int deleteOrderContract(int id) {
        return orderContractMapper.deleteOrderContract(id);
    }
}
