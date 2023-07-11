package org.fisco.bcos.service;

import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.chaindata.ContractHashDetail;

/**
 * @author mazhixiu
 * @date 2021/4/7 17:21
 * @Email:861359297@qq.com
 */
public interface ContractHashDetailService {
    public int addContractHashDetail(ContractHashDetail ContractHashDetail);
    public ContractHashDetail queryContractHashDetailByNo( int no);
}
