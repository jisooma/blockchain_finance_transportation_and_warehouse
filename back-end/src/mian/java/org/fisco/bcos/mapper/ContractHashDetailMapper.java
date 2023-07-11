package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.chaindata.ContractHashDetail;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author mazhixiu
 * @date 2021/4/7 17:05
 * @Email:861359297@qq.com
 */
@Mapper
@Repository
public interface ContractHashDetailMapper {
    public int addContractHashDetail(ContractHashDetail ContractHashDetail);
    public ContractHashDetail queryContractHashDetailByNo(@Param("no") int no);
}
