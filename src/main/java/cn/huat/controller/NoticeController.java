package cn.huat.controller;

import cn.huat.pojo.Notice;
import cn.huat.service.impl.NoticeServiceImpl;
import cn.huat.util.EasyResult;
import cn.huat.util.NoticePageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    /**
     * @return 返回主页
     */
    @RequestMapping(path = "/returnIndex", method = RequestMethod.GET)
    public String returnIndex() {
        return "forward:/user/index";
    }

    /**
     * @return 进入首页，显示当前用户已发布的所有公告
     */
    @RequestMapping(path = "/noticeIndex", method = RequestMethod.GET)
    public String getNoticeIndexPage(Model model, NoticePageUtil noticePageUtil) {
        noticePageUtil.setRows(noticeServiceImpl.findNoticesRowsById(noticeServiceImpl.getUser().getName()));
        noticePageUtil.setPath("/notice/noticeIndex");

        List<Map<String, Object>> notices = noticeServiceImpl.getNotices(noticeServiceImpl.getUser().getName(), noticePageUtil.getOffset(), noticePageUtil.getLimit());

        model.addAttribute("notices", notices);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/noticeIndex";
    }

    /**
     * @param current  页码
     * @param noticeno 公告编号
     * @return 进入首页公告详情页面，显示该公告
     */
    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String getNoticePage(@RequestParam("current") int current, @RequestParam("noticeno") String noticeno, Model model) {
        Notice notice = noticeServiceImpl.findNoticeById(noticeno);

        noticeServiceImpl.printDays(notice.getPublishingtime(), notice.getExpirationtime(), "detail");

        model.addAttribute("notice", notice);
        model.addAttribute("current", current);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/notice-detail";
    }

    /**
     * @return 进入审核公告页面
     */
    @RequestMapping(path = "/auditNotice", method = RequestMethod.GET)
    public String auditNotice(Model model, NoticePageUtil noticePageUtil) {
        noticePageUtil.setRows(noticeServiceImpl.findAuditNoticesRowsById());
        noticePageUtil.setPath("/notice/auditNotice");

        List<Map<String, Object>> notices = noticeServiceImpl.getAuditNotices(noticePageUtil.getOffset(), noticePageUtil.getLimit());

        model.addAttribute("notices", notices);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/auditNotice";
    }

    /**
     * @param current  页码
     * @param noticeno 公告编号
     * @return 进入审核公告详情页面，显示该公告
     */
    @RequestMapping(path = "/auditNotice-detail", method = RequestMethod.GET)
    public String getAuditNoticePage(@RequestParam("current") int current, @RequestParam("noticeno") String noticeno, Model model) {
        Notice notice = noticeServiceImpl.findAuditNoticeById(noticeno);

        noticeServiceImpl.printDays(notice.getPublishingtime(), notice.getExpirationtime(), "auditNotice-detail");

        model.addAttribute("notice", notice);
        model.addAttribute("current", current);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/auditNotice-detail";
    }

    /**
     * 审核公告通过
     *
     * @param noticeno    公告编号
     * @param auditstatus 审核状态
     * @return 操作成功状态
     */
    @RequestMapping(path = "/auditNoticeY", method = RequestMethod.POST)
    @ResponseBody
    public String auditNoticeY(String noticeno, int auditstatus) {

        noticeServiceImpl.updateNoticeByAuditStatus(auditstatus, noticeno);

        EasyResult easyResult = new EasyResult(null, "0", null);

        return easyResult.getResultMessage();
    }

    /**
     * 审核公告不通过
     *
     * @param noticeno 公告编号
     * @param state    发布状态
     * @return 操作成功状态
     */
    @RequestMapping(path = "/auditNoticeN", method = RequestMethod.POST)
    @ResponseBody
    public String auditNoticeN(String noticeno, int state) {

        noticeServiceImpl.updateNoticeByStatus(state, noticeno);

        EasyResult easyResult = new EasyResult(null, "0", null);

        return easyResult.getResultMessage();
    }

    /**
     * @return 进入添加公告页面
     */
    @RequestMapping(path = "/addNotice", method = RequestMethod.GET)
    public String addNotice(Model model) {
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/addNotice";
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
    @RequestMapping(path = "/timingDraftNotice", method = RequestMethod.POST)
    @ResponseBody
    public String timingDraftNotice(String topic, String content, String timeRange, int state, int auditstatus, String time) {

        noticeServiceImpl.timingDraftNotice(topic, content, timeRange, state, auditstatus, time);

        EasyResult easyResult = new EasyResult(null, "0", null);

        return easyResult.getResultMessage();
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
    @RequestMapping(path = "/draftNotice", method = RequestMethod.POST)
    @ResponseBody
    public String draftNotice(String topic, String content, String timeRange, int state, int auditstatus) {

        noticeServiceImpl.draftNotice(topic, content, timeRange, state, auditstatus);

        EasyResult easyResult = new EasyResult(null, "0", null);

        return easyResult.getResultMessage();
    }

    /**
     * @return 进入草稿箱，显示当前用户已保存的所有公告
     */
    @RequestMapping(path = "/draftsNotice", method = RequestMethod.GET)
    public String getDraftsNoticePage(Model model, NoticePageUtil noticePageUtil) {
        noticePageUtil.setRows(noticeServiceImpl.findSaveNoticeRowsById(noticeServiceImpl.getUser().getName()));
        noticePageUtil.setPath("/notice/draftsNotice");

        List<Notice> list = noticeServiceImpl.findSaveNoticesById(noticeServiceImpl.getUser().getName(), noticePageUtil.getOffset(), noticePageUtil.getLimit());
        List<Map<String, Object>> notices = noticeServiceImpl.getNoticeMapList(list);
        model.addAttribute("notices", notices);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/draftsNotice";
    }

    /**
     * @param current  页码
     * @param noticeno 公告编号
     * @return 进入修改公告页面
     */
    @RequestMapping(path = "/drafts", method = RequestMethod.GET)
    public String getUpdateNoticePage(@RequestParam("current") String current, @RequestParam("noticeno") String noticeno, Model model) {
        Notice notice = noticeServiceImpl.findSaveNoticeById(noticeno);

        int day = 0;
        day = noticeServiceImpl.printDays(notice.getPublishingtime(), notice.getExpirationtime(), "drafts");

        model.addAttribute("notice", notice);
        model.addAttribute("current", current);
        model.addAttribute("day", day);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/updateNotice";
    }

    /**
     * 修改已保存的一个公告，并发布或保存
     *
     * @param topic       主题
     * @param content     正文
     * @param timeRange   有效期
     * @param state       状态
     * @param auditstatus 审核状态
     * @param noticeno    公告编号
     * @return 操作成功状态
     */
    @RequestMapping(path = "/updateNotice", method = RequestMethod.POST)
    @ResponseBody
    public String UpdateNotice(String topic, String content, String timeRange, int state, int auditstatus, String noticeno) {

        noticeServiceImpl.UpdateNotice(topic, content, timeRange, state, auditstatus, noticeno);

        EasyResult easyResult = new EasyResult(null, "0", null);

        return easyResult.getResultMessage();
    }

    /**
     * @return 进入删除已发布公告页面
     */
    @RequestMapping(path = "/deleteDraftNotice", method = RequestMethod.GET)
    public String getDeleteDraftNoticePage(Model model, NoticePageUtil noticePageUtil) {
        noticePageUtil.setRows(noticeServiceImpl.findNoticesRowsById(noticeServiceImpl.getUser().getName()));
        noticePageUtil.setPath("/notice/deleteDraftNotice");

        List<Map<String, Object>> notices = noticeServiceImpl.getNotices(noticeServiceImpl.getUser().getName(), noticePageUtil.getOffset(), noticePageUtil.getLimit());

        model.addAttribute("notices", notices);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/deleteDraftNotice";
    }

    /**
     * @return 进入删除已保存公告页面
     */
    @RequestMapping(path = "/deleteSaveNotice", method = RequestMethod.GET)
    public String getDeleteSaveNoticePage(Model model, NoticePageUtil noticePageUtil) {
        noticePageUtil.setRows(noticeServiceImpl.findSaveNoticeRowsById(noticeServiceImpl.getUser().getName()));
        noticePageUtil.setPath("/notice/deleteSaveNotice");

        List<Notice> list = noticeServiceImpl.findSaveNoticesById(noticeServiceImpl.getUser().getName(), noticePageUtil.getOffset(), noticePageUtil.getLimit());
        List<Map<String, Object>> notices = noticeServiceImpl.getNoticeMapList(list);

        model.addAttribute("notices", notices);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/deleteSaveNotice";
    }

    /**
     * 删除一个或多个公告
     *
     * @param state       状态
     * @param auditstatus 审核状态
     * @param checkedID   一个或多个公告编号
     * @return 操作成功状态
     */
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNotice(int state, int auditstatus, String checkedID) {

        noticeServiceImpl.deleteNotice(state, auditstatus, checkedID);

        EasyResult easyResult = new EasyResult(null);

        return easyResult.getResultMessage();
    }

    /**
     * @return 进入已删除公告页面
     */
    @RequestMapping(path = "/deletedNotice", method = RequestMethod.GET)
    public String getDeletedNoticePage(Model model, NoticePageUtil noticePageUtil) {
        noticePageUtil.setRows(noticeServiceImpl.findDeleteNoticeRowsById(noticeServiceImpl.getUser().getName()));
        noticePageUtil.setPath("/notice/deletedNotice");

        List<Notice> list = noticeServiceImpl.findDeleteNoticesById(noticeServiceImpl.getUser().getName(), noticePageUtil.getOffset(), noticePageUtil.getLimit());
        List<Map<String, Object>> notices = noticeServiceImpl.getNoticeMapList(list);

        model.addAttribute("notices", notices);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        return "/notice/deletedNotice";
    }

    /**
     * @param current  页码
     * @param noticeno 公告编号
     * @return 进入已删除公告详情页面，显示该公告
     */
    @RequestMapping(path = "/deleted", method = RequestMethod.GET)
    public String getDeletedNotice(@RequestParam("current") int current, @RequestParam("noticeno") String noticeno, Model model) {
        Notice notice = noticeServiceImpl.findDeleteNoticeById(noticeno);
        model.addAttribute("notice", notice);
        model.addAttribute("current", current);
        model.addAttribute("authority", noticeServiceImpl.getaAuthority());

        noticeServiceImpl.printDays(notice.getPublishingtime(), notice.getExpirationtime(), "deleted");

        return "/notice/deleted-notice-detail";
    }

    /**
     * 将已删除的公告重新发布
     *
     * @param state    状态
     * @param noticeno 公告编号
     * @return 操作成功状态
     */
    @RequestMapping(path = "/rePublish", method = RequestMethod.POST)
    @ResponseBody
    public String RePublishNotice(int state, String noticeno) {

        noticeServiceImpl.updateNoticeByRePublish(new Date(), state, noticeno);

        EasyResult easyResult = new EasyResult(null, "0", null);

        return easyResult.getResultMessage();
    }

}
