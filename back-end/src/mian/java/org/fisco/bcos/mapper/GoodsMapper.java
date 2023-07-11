package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/2/25 15:07
 * @Email:861359297@qq.com
 */
@Repository
@Mapper
public interface GoodsMapper {
    public int addGoods(Goods Goods);
    public int updateGoods(Goods Goods);
    public int deleteGoods(@Param("id") int id);
    public List<Goods> queryAllGoods();
    public Goods queryGoodsByID(@Param("id") int id);
    public List<Goods> queryGoodsByHolder(@Param("holder") String holder);
    public List<Goods> queryGoodsByStatus(@Param("status") String status);
    public List<Goods> queryGoodsByHolderAndStatus(@Param("holder") String holder,@Param("status")String status);
}
