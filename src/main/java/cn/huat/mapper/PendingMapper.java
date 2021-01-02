package cn.huat.mapper;

import cn.huat.pojo.Pending;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PendingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pending record);

    int insertSelective(Pending record);

    Pending selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pending record);

    int updateByPrimaryKey(Pending record);
}