package cn.huat.service;



import cn.huat.pojo.SFile;

import java.util.List;

/**
 * Description
 *
 * @author helaxest
 * @date 2020/11/29  16:35
 * @mail:
 * @since JDK 1.8
 */
public interface SFileService {

    /**
     * 添加发文
     *
     * @param sFile 发文
     * @return 大于0则操作成功
     */
    int addSFile(SFile sFile);

    /**
     * 草拟人提交发文
     * @param sFile 发文
     * @return 大于0则操作成功
     */
    int addSFileByDraftsman(SFile sFile);


    /**
     * 根据草拟文序号删除发文
     *
     * @param draftno 草拟文序号
     * @return 大于0则操作成功
     */
    int deleteSFileByDraftno(String draftno);

    /**
     * 更新发文
     *
     * @param sFile 发文
     * @return 大于0则操作成功
     */

    int updateSFile(SFile sFile);

    /**
     * 根据草拟文序号查询发文
     *
     * @param draftno 草拟文序号
     * @return 发文
     */

    SFile findSFileByDraftno(String draftno);


    /**
     * 根据用户登录的名字,查询所有发文
     * @return 发文列表
     */
    List<SFile> findAll();

    List<SFile> findByState(String state);

    SFile findSFileByFileno(String fileNo);
}
