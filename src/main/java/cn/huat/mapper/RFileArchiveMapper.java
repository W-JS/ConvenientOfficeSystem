package cn.huat.mapper;

import cn.huat.pojo.RFileArchive;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RFileArchiveMapper {
    int deleteByPrimaryKey(String id);

    int insert(RFileArchive record);

    int insertSelective(RFileArchive record);

    RFileArchive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RFileArchive record);

    int updateByPrimaryKey(RFileArchive record);

    List<RFileArchive> selectRFileArchiveByArchiveno(String archiveno);

    int deleteByFileNo(String fileno);
}