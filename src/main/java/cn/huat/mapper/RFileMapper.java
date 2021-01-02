package cn.huat.mapper;

import cn.huat.pojo.RFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(RFile record);

    int insertSelective(RFile record);

    RFile selectByPrimaryKey(String id);

    List<String> selectByFileNo();

    RFile selectFileByFileNo(String fileno);

    int updateByPrimaryKeySelective(RFile record);

    int updateByPrimaryKey(RFile record);

    List<RFile> findAll();
    /*
     * 改 2020-12-16
     * */
    RFile selectRFileByFileNo(String fileno);
}