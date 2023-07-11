package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.Respository;
import org.fisco.bcos.mapper.RespositoryMapper;
import org.fisco.bcos.service.RespositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespositoryServiceImpl implements RespositoryService {
    @Autowired
    private RespositoryMapper respositoryMapper;
    @Override
    public int addRespository(Respository respository) {
        return respositoryMapper.addRespository(respository);
    }
    @Override
    public int updateRespository(Respository respository) {
        return respositoryMapper.updateRespository(respository);
    }

    @Override
    public int deleteRespository(int id) {
        return 0;
    }

    @Override
    public List<Respository> queryAllRespository() {
        return respositoryMapper.queryAllRespository();
    }

    @Override
    public Respository queryRespositoryByID(int id) {
        return respositoryMapper.queryRespositoryByID(id);
    }

    @Override
    public List<Respository> queryRespositoryByHolder(String holder) {
        return respositoryMapper.queryRespositoryByHolder(holder);
    }

    @Override
    public List<Respository> queryRespositoryByHolderAndStatus(String holder, String status) {
        return respositoryMapper.queryRespositoryByHolderAndStatus(holder,status);
    }

    @Override
    public List<Respository> queryRespositoryByLogistics(String logistics) {
        return respositoryMapper.queryRespositoryByLogistics(logistics);
    }

    @Override
    public List<Respository> queryRespositoryByLogisticsAndStatus(String logistics, String status) {
        return respositoryMapper.queryRespositoryByLogisticsAndStatus(logistics,status);
    }

    @Override
    public List<Respository> queryRespositoryByStatus(String status) {
        return respositoryMapper.queryRespositoryByStatus(status);
    }

    @Override
    public Respository queryRespositoryByNameAndSpecification(String name, String specification) {
        return respositoryMapper.queryRespositoryByNameAndSpecification(name, specification);
    }

}
