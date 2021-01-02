package cn.huat.mapper;

import cn.huat.pojo.Archive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArchiveMapper {
    int deleteByPrimaryKey(String archiveno);

    int insert(Archive record);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(String archiveno);

    int updateByPrimaryKeySelective(Archive record);

    int updateByPrimaryKey(Archive record);

    List<Archive> limitOfArchive(@Param("page") int page, @Param("pageSize") int pageSize, @Param("archivename") String archivename);

    int countOfArchive(@Param("archivename") String archivename);

    List<Archive> getAllArchive();

    Archive selectByName(String archivename);
}