package org.fisco.bcos.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.fisco.bcos.entity.Respository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RespositoryMapper {
    public int addRespository(Respository respository);
    public int updateRespository(Respository respository);
    public int deleteRespository(@Param("id") int id);
    public List<Respository> queryAllRespository();
    public Respository queryRespositoryByID(@Param("id") int id);
    public List<Respository> queryRespositoryByHolder(@Param("holder") String holder);
    public List<Respository> queryRespositoryByLogistics(@Param("logistics") String logistics);
    public List<Respository> queryRespositoryByStatus(@Param("status") String status);
    public List<Respository> queryRespositoryByHolderAndStatus(@Param("holder") String holder,@Param("status")String status);
    public List<Respository> queryRespositoryByLogisticsAndStatus(@Param("logistics")String logistics,@Param("status")String status);
    public Respository queryRespositoryByNameAndSpecification(@Param("name")String name,@Param("specification")String specification);
}
