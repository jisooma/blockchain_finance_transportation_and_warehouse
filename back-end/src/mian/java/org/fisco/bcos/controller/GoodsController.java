package org.fisco.bcos.controller;

import org.fisco.bcos.entity.Goods;
import org.fisco.bcos.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/25 15:27
 * @Email:861359297@qq.com
 */
@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService GoodsService;

    @PostMapping("/addGoods")
    public int addGoods(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        String specification = httpServletRequest.getParameter("specification");
        String number = httpServletRequest.getParameter("number");
        String addr = httpServletRequest.getParameter("addr");
        String holder = httpServletRequest.getParameter("holder");
        String remark = httpServletRequest.getParameter("remark");

        Date InputTime = new Date();
        Timestamp ts = new Timestamp(InputTime.getTime());

        Goods Goods = new Goods(name,specification,Integer.parseInt(number),addr,holder,ts,"未抵押",remark);
        int i = GoodsService.addGoods(Goods);
        return i;
    }

    @PostMapping("/agreeResopsitory")
    public int agreeGoods(HttpServletRequest httpServletRequest) throws Exception {
        String id = httpServletRequest.getParameter("id");
        Goods Goods = GoodsService.queryGoodsByID(Integer.parseInt(id));
        Date InputTime = new Date();
        Timestamp ts = new Timestamp(InputTime.getTime());
        Goods.setInputTime(ts);
        Goods.setStatus("同意入库");
        int i=GoodsService.updateGoods(Goods);
        return  i;
    }

    @PostMapping("/updateGoods")
    public int updateGoodsholder(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        String holder = httpServletRequest.getParameter("holder");
        Goods Goods=GoodsService.queryGoodsByID(Integer.parseInt(id));
        Goods.setHolder(holder);
        Date InputTime = new Date();
        Timestamp ts = Timestamp.valueOf(String.valueOf(InputTime.getTime()));
        Goods.setInputTime(ts);
        return GoodsService.updateGoods(Goods);
    }
    @GetMapping("/queryGoodsbyId/{id}")
    public List<Goods> queryGoodsbyId(@PathVariable("id") int id){
        List<Goods> lists = new LinkedList<>();
        lists.add(GoodsService.queryGoodsByID(id));
        return  lists;
    }
    @GetMapping("/queryGoodsById/{id}")
    public Goods queryGoodsById(@PathVariable("id") int id){
        return  GoodsService.queryGoodsByID(id);
    }
    @GetMapping("/queryGoodsByholder/{holder}")
    public List<Goods> queryGoodsByholder(@PathVariable("holder") String holder){
        return  GoodsService.queryGoodsByHolder(holder);
    }
    @GetMapping("/queryGoodsByholder/{holder}/{status}")
    public List<Goods> queryGoodsByholderAndStatus(@PathVariable("holder") String holder,@PathVariable("status") String status){
        return  GoodsService.queryGoodsByHolderAndStatus(holder,status);
    }
    @GetMapping("/queryGoodsBystatus/{status}")
    public List<Goods> queryGoodsBystatus(@PathVariable("status")String status){
        return  GoodsService.queryGoodsByStatus(status);
    }
}
