package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.ValueReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ValueReportMapper{
    public int addValueReport(ValueReport valueReport);
    public int deleteValueReport(@Param("id") int id);
    public List<ValueReport> queryAllValueReport();
    public ValueReport queryValueReportByReno(@Param("reno") int reno);
    public List<ValueReport> queryValueReportByLogistics(@Param("logistics") String logistics);
    public List<ValueReport> queryAllValueReportByEnterPrise(@Param(" enterprise") String enterprise);
}
