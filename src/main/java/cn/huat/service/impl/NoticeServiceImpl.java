package cn.huat.service.impl;

import cn.huat.mapper.NoticeMapper;
import cn.huat.pojo.Notice;
import cn.huat.pojo.User;
import cn.huat.service.NoticeService;
import cn.huat.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.huat.util.UUIDGenerator.get16UUID;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    // 查询已发布的所有公告
    public List<Notice> findAllNotices() {
        return noticeMapper.selectAllNotices();
    }

    // 查询已发布的所有公告
    public List<Notice> findNotices(String deaprtment, int offset, int limit) {
        return noticeMapper.selectNotices(deaprtment, offset, limit);
    }

    // 查询当前用户已发布的所有公告
    public List<Notice> findNoticesById(String publishngmaker, int offset, int limit) {
        return noticeMapper.selectNoticesById(publishngmaker, offset, limit);
    }

    // 查询已发布未审核的所有公告
    public List<Notice> findAuditNoticesById(int offset, int limit) {
        return noticeMapper.selectAuditNoticesById(offset, limit);
    }

    // 查询当前用户已保存的所有公告
    public List<Notice> findSaveNoticesById(String publishngmaker, int offset, int limit) {
        return noticeMapper.selectSaveNoticesById(publishngmaker, offset, limit);
    }

    // 查询当前用户已删除的所有公告
    public List<Notice> findDeleteNoticesById(String publishngmaker, int offset, int limit) {
        return noticeMapper.selectDeleteNoticesById(publishngmaker, offset, limit);
    }

    // 查询已发布的所有公告的行数
    public int findNoticesRows() {
        return noticeMapper.selectNoticesRows();
    }

    // 查询当前用户已发布的所有公告的行数
    public int findNoticesRowsById(String publishngmaker) {
        return noticeMapper.selectNoticesRowsById(publishngmaker);
    }

    // 查询已发布未审核的所有公告的行数
    public int findAuditNoticesRowsById() {
        return noticeMapper.selectAuditNoticesRowsById();
    }

    // 查询当前用户已保存的所有公告的行数
    public int findSaveNoticeRowsById(String publishngmaker) {
        return noticeMapper.selectSaveNoticeRowsById(publishngmaker);
    }

    // 查询当前用户已删除的所有公告的行数
    public int findDeleteNoticeRowsById(String publishngmaker) {
        return noticeMapper.selectDeleteNoticeRowsById(publishngmaker);
    }

    // 查询已发布的一个公告
    public Notice findNoticeById(String noticeno) {
        return noticeMapper.selectNoticeById(noticeno);
    }

    // 查询已发布未审核的一个公告
    public Notice findAuditNoticeById(String noticeno) {
        return noticeMapper.selectAuditNoticeById(noticeno);
    }

    // 查询当前用户已保存的一个公告
    public Notice findSaveNoticeById(String noticeno) {
        return noticeMapper.selectSaveNoticeById(noticeno);
    }

    // 查询已删除的一个公告
    public Notice findDeleteNoticeById(String noticeno) {
        return noticeMapper.selectNoticeByDelete(noticeno);
    }

    // 新建一个公告
    public int addNotice(Notice notice) {
        if (notice == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 转义HTML标记，将 <p>通知</p> 转义为 &lt;p&gt;通知&lt;/p&gt;
//        notice.setTopic(HtmlUtils.htmlEscape(notice.getTopic()));
//        notice.setContent(HtmlUtils.htmlEscape(notice.getContent()));

        return noticeMapper.insertNotice(notice);
    }

    // 修改已保存的一个公告，并发布或保存
    public int updateNotice(String topic, String content, Date publishingtime, Date expirationtime, int state, int auditstatus, String noticeno) {
        return noticeMapper.updateNotice(topic, content, publishingtime, expirationtime, state, auditstatus, noticeno);
    }

    // 定时发布一个公告，实际上是修改公告的状态
    public int updateNoticeByStatus(int state, String noticeno) {
        return noticeMapper.updateNoticeByStatus(state, noticeno);
    }

    // 审核公告，实际上是修改公告的状态
    public int updateNoticeByAuditStatus(int auditstatus, String noticeno) {
        return noticeMapper.updateNoticeByAuditStatus(auditstatus, noticeno);
    }

    // 假删除一个公告，实际上是修改公告的状态
    public int updateNoticeByDelete(Date publishingtime, int state, int auditstatus, String noticeno) {
        return noticeMapper.updateNoticeByDelete(publishingtime, state, auditstatus, noticeno);
    }

    // 将已删除的公告重新发布
    public int updateNoticeByRePublish(Date publishingtime, int state, String noticeno) {
        return noticeMapper.updateNoticeByRePublish(publishingtime, state, noticeno);
    }

    // 查询当前登录用户
    public User findUserByAccount(Integer account) {
        return noticeMapper.selectUserByAccount(account);
    }

    /**
     * 将Notice存进Map，再将Map存进List
     *
     * @param list 公告列表
     * @return 包含Notice的Map列表
     */
    public List<Map<String, Object>> getNoticeMapList(List<Notice> list) {
        List<Map<String, Object>> notices = new ArrayList<>();
        if (list != null) {
            for (Notice notice : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("notice", notice);
                notices.add(map);
            }
        }
        return notices;
    }

    /**
     * @param publishngmaker 发布人
     * @param offset         从第几条数据查询
     * @param limit          需要查询的记录条数
     * @return 包含Notice的Map列表
     */
    public List<Map<String, Object>> getNotices(String publishngmaker, int offset, int limit) {
        List<Notice> list = findNoticesById(publishngmaker, offset, limit);
        List<Map<String, Object>> notices = getNoticeMapList(list);
        return notices;
    }

    /**
     * @param offset 从第几条数据查询
     * @param limit  需要查询的记录条数
     * @return 包含Notice的Map列表
     */
    public List<Map<String, Object>> getAuditNotices(int offset, int limit) {
        List<Notice> list = findAuditNoticesById(offset, limit);
        List<Map<String, Object>> notices = getNoticeMapList(list);
        return notices;
    }

    /**
     * 定时发布公告
     *
     * @param topic     主题
     * @param content   正文
     * @param timeRange 有效期
     * @param state     状态
     * @param time      定时发布的时间
     * @return 操作成功状态
     */
    public void timingDraftNotice(String topic, String content, String timeRange, int state, int auditstatus, String time) {
        List<Date> dateList = getDate(time, timeRange);

        Notice notice = new Notice();
        notice.setNoticeno(getNoticeNo());
        notice.setPublishngmaker(getUser().getName());
        notice.setDeaprtment(getUser().getDepartment());
        notice.setTopic(topic);
        notice.setContent(content);
        notice.setPublishingtime(dateList.get(0));
        notice.setExpirationtime(dateList.get(1));
        notice.setState(state);
        notice.setAuditstatus(auditstatus);
        addNotice(notice);

        new Thread() {
            @Override
            public void run() {
                super.run();
                Date now = new Date();
                Date draft = dateList.get(0);
                long timeLength = draft.getTime() - now.getTime();
                try {
                    Thread.sleep(timeLength);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updateNoticeByStatus(1, notice.getNoticeno());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
                System.out.println(sdf.format(now) + " 定时发布的公告： 发布时间 " + sdf.format(draft) + "  公告编号 " + notice.getNoticeno() + " 已存到数据库！");
                printDays(dateList.get(0), dateList.get(1), "timingDraftNotice");
            }
        }.start();
    }

    /**
     * 发布或保存公告
     *
     * @param topic       主题
     * @param content     正文
     * @param timeRange   有效期
     * @param state       状态
     * @param auditstatus 审核状态
     * @return 操作成功状态
     */
    public void draftNotice(String topic, String content, String timeRange, int state, int auditstatus) {
        List<Date> dateList = getDate(null, timeRange);

        Notice notice = new Notice();
        notice.setNoticeno(getNoticeNo());
        notice.setPublishngmaker(getUser().getName());
        notice.setDeaprtment(getUser().getDepartment());
        notice.setTopic(topic);
        notice.setContent(content);
        notice.setPublishingtime(dateList.get(0));
        notice.setExpirationtime(dateList.get(1));
        notice.setState(state);
        notice.setAuditstatus(auditstatus);

        addNotice(notice);

        if (state == 1) {
            printDays(dateList.get(0), dateList.get(1), "draftNotice");
        } else {
            printDays(dateList.get(0), dateList.get(1), "saveNotice");
        }
    }

    public void UpdateNotice(String topic, String content, String timeRange, int state, int auditstatus, String noticeno) {
        List<Date> dateList = getDate(null, timeRange);

        updateNotice(topic, content, dateList.get(0), dateList.get(1), state, auditstatus, noticeno);

        if (state == 1) {
            printDays(dateList.get(0), dateList.get(1), "updateNoticeDraft");
        } else {
            printDays(dateList.get(0), dateList.get(1), "updateNoticeSave");
        }
    }

    public void deleteNotice(int state, int auditstatus, String checkedID) {
        String[] result = checkedID.split(",");
        for (String noticeno : result) {
            updateNoticeByDelete(new Date(), state, auditstatus, noticeno);
        }
    }

    /**
     * @param beforeDate  发布日期
     * @param currentDate 过期日期
     * @return 天数
     */
    public int printDays(Date beforeDate, Date currentDate, String functionName) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beforeDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(currentDate);

        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int days = 0;

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {   //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {    //闰年
                    timeDistance += 366;
                } else {    //不是闰年
                    timeDistance += 365;
                }
            }
            days = timeDistance + (day2 - day1);
        } else {    //不同年
            days = day2 - day1;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
        System.out.println("发布日期: " + sdf.format(beforeDate) + "   过期日期: " + sdf.format(currentDate));
        System.out.println("有效期: " + days + " 天   " + functionName + "\n");
        return days;
    }

    /**
     * @param timeRange 公告有效期限
     * @return Date列表，第一项为发布日期，第二项为过期日期
     */
    public List<Date> getDate(String time, String timeRange) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
        List<Date> dateList = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        Date publishingtime = null;

        if (time == null) {
            publishingtime = new Date();
        } else {
            try {
                publishingtime = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Date expirationtime = publishingtime;
        calendar.setTime(publishingtime);
        if (timeRange.equals("threeDays")) {// 三天
            calendar.add(calendar.DAY_OF_YEAR, 3);// 加三天
        } else if (timeRange.equals("oneWeek")) {// 一周
            calendar.add(calendar.WEEK_OF_MONTH, 1);// 加一周
        } else if (timeRange.equals("oneMonth")) {// 一个月
            calendar.add(calendar.MONTH, 1);// 加一个月
        } else {// 一年
            calendar.add(calendar.YEAR, 1);// 加一年
        }
        String date = sdf.format(calendar.getTime());
        try {
            expirationtime = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateList.add(publishingtime);
        dateList.add(expirationtime);
        return dateList;
    }

    /**
     * @return 当前登陆用户
     */
    public User getUser() {
        // 获取
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer account = Integer.valueOf(authentication.getName());
        User user = findUserByAccount(account);
        return user;
    }

    /**
     * @return 随机生成用户ID
     */
    public String getNoticeNo() {
        String date = DateUtils.format(new Date()).replaceAll("-", "");
        String noticeno = "GG_" + date + "_" + get16UUID();
        return noticeno;
    }

    /**
     * @return 得到当前用户的权限
     */
    public int getaAuthority() {
        User user = getUser();
        int authority = 0;
        if (user.getAccessauthority().equals("administrator") || user.getAccessauthority().equals("subAdministrator")) {
            authority = 1;
        }
        return authority;
    }

}
