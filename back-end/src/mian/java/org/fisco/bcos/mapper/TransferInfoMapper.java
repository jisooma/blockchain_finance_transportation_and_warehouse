package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.TransferInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TransferInfoMapper {
    public int addTransferInfo(TransferInfo transferInfo);
    public List<TransferInfo> queryTransferInfoByPayee(@Param("payee") String payee);
    public List<TransferInfo> queryTransferInfoByPayer(@Param("payer") String payer);
    public List<TransferInfo> queryTransferInfoByPayerAndType(@Param("payer") String payer,@Param("type") String type);
    public List<TransferInfo> queryTransferInfoByPayeeAndType(@Param("payee") String payee,@Param("type") String type);
    public List<TransferInfo> queryAllTransferInfo();
    public int deleteTransferInfo(@Param("id") int id);
}
