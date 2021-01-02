package cn.huat.service;

import cn.huat.pojo.Notice;
import cn.huat.pojo.User;

import java.util.Date;
import java.util.List;

public interface NoticeService {

    // 查询已发布的所有公告
    public List<Notice> findAllNotices();

    // 查询已发布的所有公告
    public List<Notice> findNotices(String deaprtment, int offset, int limit);

    // 查询当前用户已发布的所有公告
    public List<Notice> findNoticesById(String publishngmaker, int offset, int limit);

    // 查询已发布未审核的所有公告
    public List<Notice> findAuditNoticesById(int offset, int limit);

    // 查询当前用户已保存的所有公告
    public List<Notice> findSaveNoticesById(String publishngmaker, int offset, int limit);

    // 查询当前用户已删除的所有公告
    public List<Notice> findDeleteNoticesById(String publishngmaker, int offset, int limit);

    // 查询已发布的所有公告的行数
    public int findNoticesRows();

    // 查询当前用户已发布的所有公告的行数
    public int findNoticesRowsById(String publishngmaker);

    // 查询已发布未审核的所有公告的行数
    public int findAuditNoticesRowsById();

    // 查询当前用户已保存的所有公告的行数
    public int findSaveNoticeRowsById(String publishngmaker);

    // 查询当前用户已删除的所有公告的行数
    public int findDeleteNoticeRowsById(String publishngmaker);

    // 查询已发布的一个公告
    public Notice findNoticeById(String noticeno);

    // 查询已发布未审核的一个公告
    public Notice findAuditNoticeById(String noticeno);

    // 查询当前用户已保存的一个公告
    public Notice findSaveNoticeById(String noticeno);

    // 查询已删除的一个公告
    public Notice findDeleteNoticeById(String noticeno);

    // 新建一个公告
    public int addNotice(Notice notice);

    // 修改已保存的一个公告，并发布或保存
    public int updateNotice(String topic, String content, Date publishingtime, Date expirationtime, int state, int auditstatus, String noticeno);

    // 定时发布一个公告，实际上是修改公告的状态
    public int updateNoticeByStatus(int state, String noticeno);

    // 审核公告，实际上是修改公告的状态
    public int updateNoticeByAuditStatus(int auditstatus, String noticeno);

    // 假删除一个公告，实际上是修改公告的状态
    public int updateNoticeByDelete(Date publishingtime, int state, int auditstatus, String noticeno);

    // 将已删除的公告重新发布
    public int updateNoticeByRePublish(Date publishingtime, int state, String noticeno);

    // 查询当前登录用户
    public User findUserByAccount(Integer account);

}
