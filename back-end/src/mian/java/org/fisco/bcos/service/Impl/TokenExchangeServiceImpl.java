package org.fisco.bcos.service.Impl;


import org.fisco.bcos.entity.TokenExchange;
import org.fisco.bcos.mapper.TokenExchangeMapper;
import org.fisco.bcos.service.TokenExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenExchangeServiceImpl implements TokenExchangeService {
    @Autowired
    private TokenExchangeMapper tokenExchangeMapper;
    @Override
    public int addTokenExchange(TokenExchange tokenExchange) {
        return tokenExchangeMapper.addTokenExchange(tokenExchange);
    }

    @Override
    public int updateTokenExchange(TokenExchange tokenExchange) {
        return tokenExchangeMapper.updateTokenExchange(tokenExchange);
    }

    @Override
    public int deleteTokenExchange(int id) {
        return tokenExchangeMapper.deleteTokenExchange(id);
    }

    @Override
    public List<TokenExchange> queryAllTTokenExchange() {
        return tokenExchangeMapper.queryAllTTokenExchange();
    }

    @Override
    public List<TokenExchange> queryTokenExchangeByBank(String bank) {
        return tokenExchangeMapper.queryTokenExchangeByBank(bank);
    }

    @Override
    public List<TokenExchange> queryTokenExchangeByInitiator(String initiator) {
        return tokenExchangeMapper.queryTokenExchangeByInitiator(initiator);
    }

    @Override
    public List<TokenExchange> queryTokenExchangeByInitiatorAndBank(String initiator, String bank) {
        return tokenExchangeMapper.queryTokenExchangeByInitiatorAndBank(initiator, bank);
    }

    @Override
    public TokenExchange queryTokenExchangeByID(int id) {
        return tokenExchangeMapper.queryTokenExchangeByID(id);
    }

    @Override
    public List<TokenExchange> queryTokenExchangeByBankAndStatus(String bank, String status) {
        return tokenExchangeMapper.queryTokenExchangeByBankAndStatus(bank,status);
    }

    @Override
    public List<TokenExchange> queryTokenExchangeByInitiatorAndStatus(String initiator, String status) {
        return tokenExchangeMapper.queryTokenExchangeByInitiatorAndBank(initiator, status);
    }
}
