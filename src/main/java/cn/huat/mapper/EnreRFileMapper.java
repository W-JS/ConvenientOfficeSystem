package cn.huat.mapper;

import cn.huat.pojo.EnreRFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EnreRFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(EnreRFile record);

    int insertSelective(EnreRFile record);

    EnreRFile selectByPrimaryKey(String id);

    List<EnreRFile> selectByStateAndReceiver(Integer state,Integer receiver);

    //获取该账户为录入员时所收到的收文
    List<EnreRFile> selectByStateAndId(Integer state,Integer id);

    List<EnreRFile> selectByStateAndAudit(Integer state,Integer auditor);

    List<EnreRFile> selectByStateAndDraftsman(Integer state,Integer draftsman);

    List<EnreRFile> selectByStateAndAuthorizeman(Integer state,Integer authorizeMan );
    List<EnreRFile> selectByStateAndTranPerson (Integer state,Integer tranPerson );

    int updateByPrimaryKeySelective(EnreRFile record);

    int updateByPrimaryKey(EnreRFile record);

    //查看需要放入档案的收文
    List<EnreRFile> selectByState();

    EnreRFile selectFileByFileNo(@Param("fileno") String fileno);
}