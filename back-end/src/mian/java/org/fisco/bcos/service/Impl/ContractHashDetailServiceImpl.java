package org.fisco.bcos.service.Impl;

import org.fisco.bcos.chaindata.ContractHashDetail;
import org.fisco.bcos.mapper.ContractHashDetailMapper;
import org.fisco.bcos.service.ContractHashDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mazhixiu
 * @date 2021/4/7 17:21
 * @Email:861359297@qq.com
 */
@Service
public class ContractHashDetailServiceImpl implements ContractHashDetailService {
    @Autowired
    private ContractHashDetailMapper contractHashDetailMapper;
    @Override
    public int addContractHashDetail(ContractHashDetail ContractHashDetail) {
        return contractHashDetailMapper.addContractHashDetail(ContractHashDetail);
    }

    @Override
    public ContractHashDetail queryContractHashDetailByNo(int no) {
        return contractHashDetailMapper.queryContractHashDetailByNo(no);
    }
}
