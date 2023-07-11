package org.fisco.bcos.service;

import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Goods;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/3/25 15:12
 * @Email:861359297@qq.com
 */
public interface GoodsService {
    public int addGoods(Goods Goods);
    public int updateGoods(Goods Goods);
    public int deleteGoods( int id);
    public List<Goods> queryAllGoods();
    public Goods queryGoodsByID( int id);
    public List<Goods> queryGoodsByHolder( String holder);
    public List<Goods> queryGoodsByStatus( String status);
    public List<Goods> queryGoodsByHolderAndStatus( String holder,String status);
}
