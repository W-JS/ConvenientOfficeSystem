package cn.huat.mapper;

import cn.huat.pojo.SFileArchive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SFileArchiveMapper {
    int deleteByPrimaryKey(String id);

    int deleteByFileNo(@Param("fileno") String fileno);

    int insert(SFileArchive record);

    int insertSelective(SFileArchive record);

    SFileArchive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SFileArchive record);

    int updateByPrimaryKey(SFileArchive record);

    List<SFileArchive> selectSFileArchiveByArchiveno(@Param("archiveno") String archiveno);
}