package cn.huat.mapper;


import cn.huat.pojo.SFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SFileMapper {
    //根据根据草拟文序号删除发文
    int deleteByPrimaryKey(String draftno);
    //方式一:添加发文
    int insert(SFile record);
    int insertByDraftsman(SFile record);
    //方式二:添加发文
    int insertSelective(SFile record);
    //根据草拟文序号查询发文
    SFile selectByPrimaryKey(String draftno);
    //方式一:根据草拟文序号更新发文
    int updateByPrimaryKeySelective(SFile record);
    //方式二:根据草拟文序号更新发文
    int updateByPrimaryKey(SFile record);

    /**
     * @return 找到所有发文
     */
    List<SFile> findAll();

    /**
     * 根据发文状态查询发文
     * @param state 发文状态
     * @return 发文
     */
    List<SFile> findByState(String state);

    SFile findByfileNo(String fileNo);

    /*
    * 邵传中
    * */
    List<SFile> selectByState(String state);

    SFile selectByFileNo(String fileno);

    /**
     * 程根
     * @return
     * @apiNote 查询所有未存档的发文
     */
    List<SFile> findByStateUnArchive();
}