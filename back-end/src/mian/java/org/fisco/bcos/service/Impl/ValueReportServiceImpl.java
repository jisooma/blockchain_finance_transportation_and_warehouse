package org.fisco.bcos.service.Impl;

import org.fisco.bcos.entity.ValueReport;
import org.fisco.bcos.mapper.ValueReportMapper;
import org.fisco.bcos.service.ValueReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueReportServiceImpl implements ValueReportService {
    @Autowired
    private ValueReportMapper valueReportMapper;
    @Override
    public int addValueReport(ValueReport valueReport) {
        return 0;
    }

    @Override
    public int deleteValueReport(int id) {
        return 0;
    }

    @Override
    public List<ValueReport> queryAllValueReport() {
        return null;
    }

    @Override
    public ValueReport queryValueReportByReno(int reno) {
        return valueReportMapper.queryValueReportByReno(reno);
    }

    @Override
    public List<ValueReport> queryValueReportByLogistics(String logistics) {
        return null;
    }

    @Override
    public List<ValueReport> queryAllValueReportByEnterPrise(String enterprise) {
        return null;
    }
}
