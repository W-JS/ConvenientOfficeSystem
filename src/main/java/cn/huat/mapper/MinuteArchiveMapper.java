package cn.huat.mapper;

import cn.huat.pojo.MinuteArchive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MinuteArchiveMapper {
    int deleteByPrimaryKey(String id);

    int insert(MinuteArchive record);

    int insertSelective(MinuteArchive record);

    MinuteArchive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MinuteArchive record);

    int updateByPrimaryKey(MinuteArchive record);

    List<MinuteArchive> selectMinuteArchiveByArchiveNo(@Param("archiveno") String archiveno);

    int deleteByFileNo(String fileno);

    MinuteArchive selectByFileNo1(@Param("fileno") String fileno);
}