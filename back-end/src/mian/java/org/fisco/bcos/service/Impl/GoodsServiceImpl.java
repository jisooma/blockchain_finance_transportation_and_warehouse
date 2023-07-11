package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.Goods;
import org.fisco.bcos.mapper.GoodsMapper;
import org.fisco.bcos.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/25 15:13
 * @Email:861359297@qq.com
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public int addGoods(Goods Goods) {
        return goodsMapper.addGoods(Goods);
    }

    @Override
    public int updateGoods(Goods Goods) {
        return goodsMapper.updateGoods(Goods);
    }

    @Override
    public int deleteGoods(int id) {
        return 0;
    }

    @Override
    public List<Goods> queryAllGoods() {
        return goodsMapper.queryAllGoods();
    }

    @Override
    public Goods queryGoodsByID(int id) {
        return goodsMapper.queryGoodsByID(id);
    }

    @Override
    public List<Goods> queryGoodsByHolder(String holder) {
        return goodsMapper.queryGoodsByHolder(holder);
    }

    @Override
    public List<Goods> queryGoodsByStatus(String status) {
        return goodsMapper.queryGoodsByStatus(status);
    }

    @Override
    public List<Goods> queryGoodsByHolderAndStatus(String holder, String status) {
        return goodsMapper.queryGoodsByHolderAndStatus(holder, status);
    }
}
