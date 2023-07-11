package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Collateral;
import org.fisco.bcos.entity.TokenExchange;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TokenExchangeMapper {
    public int addTokenExchange(TokenExchange tokenExchange);
    public int updateTokenExchange(TokenExchange tokenExchange);
    public int deleteTokenExchange(@Param("id") int id);
    public List<TokenExchange> queryAllTTokenExchange();
    public List<TokenExchange> queryTokenExchangeByBank(@Param("bank") String bank);
    public List<TokenExchange> queryTokenExchangeByBankAndStatus(@Param("bank") String bank,@Param("status")String status);
    public List<TokenExchange> queryTokenExchangeByInitiatorAndStatus(@Param("initiator") String initiator,@Param("status")String status);
    public List<TokenExchange> queryTokenExchangeByInitiator(@Param("initiator") String initiator);
    public List<TokenExchange> queryTokenExchangeByInitiatorAndBank(@Param("initiator") String initiator,@Param("bank") String bank);
    public TokenExchange queryTokenExchangeByID(@Param("id") int id);
}
