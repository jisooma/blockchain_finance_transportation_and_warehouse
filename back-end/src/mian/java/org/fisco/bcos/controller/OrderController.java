package org.fisco.bcos.controller;

import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Order;
import org.fisco.bcos.entity.OrderContract;
import org.fisco.bcos.entity.OrderItem;
import org.fisco.bcos.service.OrderContractService;
import org.fisco.bcos.service.OrderItemService;
import org.fisco.bcos.service.OrderService;
import org.fisco.bcos.utils.OrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/29 17:11
 * @Email:861359297@qq.com
 */

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderContractService orderContractService;

    @PostMapping("/addOrder")
    public int addOrder(HttpServletRequest request){
        String  name = request.getParameter("name");
        String  specification = request.getParameter("specification");
        String  number = request.getParameter("number");
        String  supplier = request.getParameter("supplier");
        String  buyer = request.getParameter("buyer");
        String  remark = request.getParameter("remark");
        String  orderTime = request.getParameter("orderTime");
        System.out.println(buyer);
        String uuid = OrderNumber.getOrderNumber();
        System.out.println(uuid);
        Timestamp ts = Timestamp.valueOf(orderTime);
        OrderItem orderItem= new OrderItem(uuid,name,specification,number,"等待供应商确认采购订单",remark,ts);
        Order order = new Order(uuid,buyer,supplier,"等待供应商确认采购订单",ts);
        int i = orderService.addOrder(order);
        int j = orderItemService.addOrderItem(orderItem);
        if(i==1&&j==1){
            return 1;
        }
        return  -1;
    }

    @PostMapping("/receiveOrder")
    public int receiveOrder(HttpServletRequest request){
        String id = request.getParameter("id");
        String totalMoney = request.getParameter("totalMoney");
        Order order = orderService.queryOrderById(Integer.parseInt(id));
        order.setTotalMoney(totalMoney);
        order.setStatus("供应商已确认");
        int i=orderService.updateOrder(order);
        String uuid = order.getUuid();
        OrderItem orderItem = orderItemService.queryOrderItemByUUID(uuid);
        orderItem.setStatus("供应商已确认");
        return i;
    }


    @PostMapping("disagreeOrderContract")
    public int disagreeOrderContract(HttpServletRequest request){
        String id = request.getParameter("id");
        Order order = orderService.queryOrderById(Integer.parseInt(id));
        order.setStatus("拒绝订单");
        orderService.updateOrder(order);
        String uuid = order.getUuid();
        OrderItem orderItem = orderItemService.queryOrderItemByUUID(uuid);
        orderItem.setStatus("拒绝订单");
        orderItemService.updateOrderItem(orderItem);
        return 1;
    }

    @GetMapping("/queryMyOrder/{name}")
    public List<Order> queryMyOrder(@PathVariable("name") String name){
        List<Order> orders = orderService.queryOrderByBuyer(name);
        orders.addAll(orderService.queryOrderBySupplier(name));
        return orders;
    }
    @GetMapping("/queryOrderBySupplier/{supplier}")
    public List<Order> queryOrderBySupplier(@PathVariable("supplier") String supplier){
        return orderService.queryOrderBySupplier(supplier);
    }

    @GetMapping("/queryOrderByBuyer/{buyer}")
    public List<Order> queryOrderByBuyer(@PathVariable("buyer") String buyer){
        return  orderService.queryOrderByBuyer(buyer);
    }
    @GetMapping("/queryAllOrder")
    public List<Order> queryAllOrder(){
        return orderService.queryAllOrder();
    }

    @GetMapping("/queryOrderByBuyerAndStatus/{buyer}/{status}")
    public List<Order> queryOrderByBuyerAndStatus(@PathVariable("buyer") String buyer,@PathVariable("status") String status){
        return  orderService.queryOrderByBuyerAndStatus(buyer,status);
    }
    @GetMapping("/queryOrderBySupplierAndStatus/{supplier}/{status}")
    public List<Order> queryOrderBySupplierAndStatus(@PathVariable("supplier") String supplier,@PathVariable("status") String status){
        return orderService.queryOrderBySupplierAndStatus(supplier, status);
    }
}
