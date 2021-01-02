package cn.huat.mapper;

import cn.huat.pojo.Notice;
import cn.huat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface NoticeMapper {

    /* start */

    // 查询已发布的所有公告
    List<Notice> selectAllNotices();

    // 查询已发布的所有公告
    List<Notice> selectNotices(String deaprtment, int offset, int limit);

    // 查询当前用户已发布的所有公告
    List<Notice> selectNoticesById(String publishngmaker, int offset, int limit);

    // 查询已发布未审核的所有公告
    List<Notice> selectAuditNoticesById(int offset, int limit);

    // 查询当前用户已保存的所有公告
    List<Notice> selectSaveNoticesById(String publishngmaker, int offset, int limit);

    // 查询当前用户已删除的所有公告
    List<Notice> selectDeleteNoticesById(String publishngmaker, int offset, int limit);

    // 查询已发布的所有公告的行数
    int selectNoticesRows();

    // 查询当前用户已发布的所有公告的行数
    int selectNoticesRowsById(String publishngmaker);

    // 查询已发布未审核的所有公告的行数
    int selectAuditNoticesRowsById();

    // 查询当前用户已保存的所有公告的行数
    int selectSaveNoticeRowsById(String publishngmaker);

    // 查询当前用户已删除的所有公告的行数
    int selectDeleteNoticeRowsById(String publishngmaker);

    // 查询已发布的一个公告
    Notice selectNoticeById(String noticeno);

    // 查询已发布未审核的一个公告
    Notice selectAuditNoticeById(String noticeno);

    // 查询当前用户已保存的一个公告
    Notice selectSaveNoticeById(String noticeno);

    // 查询已删除的一个公告
    Notice selectNoticeByDelete(String noticeno);

    // 新建一个公告
    int insertNotice(Notice notice);

    // 修改已保存的一个公告，并发布或保存
    int updateNotice(String topic, String content, Date publishingtime, Date expirationtime, int state, int auditstatus, String noticeno);

    // 定时发布一个公告，实际上是修改公告的状态
    int updateNoticeByStatus(int state, String noticeno);

    // 审核公告，实际上是修改公告的状态
    int updateNoticeByAuditStatus(int auditstatus, String noticeno);

    // 假删除一个公告，实际上是修改公告的状态
    int updateNoticeByDelete(Date publishingtime, int state, int auditstatus, String noticeno);

    // 将已删除的公告重新发布
    int updateNoticeByRePublish(Date publishingtime, int state, String noticeno);

    // 查询当前登录用户
    User selectUserByAccount(Integer account);

    /* end */

    int deleteByPrimaryKey(String noticeno);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeno);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}