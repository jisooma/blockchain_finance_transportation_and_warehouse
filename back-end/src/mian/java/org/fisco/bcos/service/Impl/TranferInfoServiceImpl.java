package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.TransferInfo;
import org.fisco.bcos.mapper.TransferInfoMapper;
import org.fisco.bcos.service.TransferInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranferInfoServiceImpl implements TransferInfoService {
    @Autowired
    private TransferInfoMapper transferInfoMapper;
    @Override
    public int addTransferInfo(TransferInfo transferInfo) {
        return transferInfoMapper.addTransferInfo(transferInfo);
    }

    @Override
    public List<TransferInfo> queryMyTransferInfo(String name) {
        List<TransferInfo> transferInfos = transferInfoMapper.queryTransferInfoByPayee(name);
        transferInfos.addAll(transferInfoMapper.queryTransferInfoByPayer(name));
        return transferInfos;
    }

    @Override
    public List<TransferInfo> queryMyTransferInfoByType(String name,String type) {
        List<TransferInfo> transferInfos = transferInfoMapper.queryTransferInfoByPayeeAndType(name,type);
        transferInfos.addAll(transferInfoMapper.queryTransferInfoByPayerAndType(name,type));
        return transferInfos;
    }


    @Override
    public List<TransferInfo> queryTransferInfoByPayee(String payee) {
        return transferInfoMapper.queryTransferInfoByPayee(payee);
    }

    @Override
    public List<TransferInfo> queryTransferInfoByPayer(String payer) {
        return transferInfoMapper.queryTransferInfoByPayer(payer);
    }

    @Override
    public List<TransferInfo> queryAllTransferInfo() {
        return transferInfoMapper.queryAllTransferInfo();
    }

    @Override
    public int deleteTransferInfo(int id) {
        return transferInfoMapper.deleteTransferInfo(id);
    }

    @Override
    public List<TransferInfo> queryTransferInfoByPayerAndType(String payer, String type) {
        return transferInfoMapper.queryTransferInfoByPayerAndType(payer, type);
    }

    @Override
    public List<TransferInfo> queryTransferInfoByPayeeAndType(String payee, String type) {
        return transferInfoMapper.queryTransferInfoByPayeeAndType(payee, type);
    }
}
