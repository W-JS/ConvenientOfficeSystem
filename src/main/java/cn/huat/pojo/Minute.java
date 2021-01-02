package cn.huat.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
/*****/
@Component

public class Minute implements Serializable {
    private Integer id;

    private String meetingserialno;

    private String meetingname;

    private String fileno;

    private String filecaption;

    private String keyword;

    private Integer chieforganizer;

    private String meetinglocus;

    private Date starttime;

    private Date endtime;

    private String attendee;

    private String topicandargumentation;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetingserialno() {
        return meetingserialno;
    }

    public void setMeetingserialno(String meetingserialno) {
        this.meetingserialno = meetingserialno == null ? null : meetingserialno.trim();
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname == null ? null : meetingname.trim();
    }

    public String getFileno() {
        return fileno;
    }

    public void setFileno(String fileno) {
        this.fileno = fileno == null ? null : fileno.trim();
    }

    public String getFilecaption() {
        return filecaption;
    }

    public void setFilecaption(String filecaption) {
        this.filecaption = filecaption == null ? null : filecaption.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getChieforganizer() {
        return chieforganizer;
    }

    public void setChieforganizer(Integer chieforganizer) {
        this.chieforganizer = chieforganizer;
    }

    public String getMeetinglocus() {
        return meetinglocus;
    }

    public void setMeetinglocus(String meetinglocus) {
        this.meetinglocus = meetinglocus == null ? null : meetinglocus.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAttendee() {
        return attendee;
    }

    public void setAttendee(String attendee) {
        this.attendee = attendee == null ? null : attendee.trim();
    }

    public String getTopicandargumentation() {
        return topicandargumentation;
    }

    public void setTopicandargumentation(String topicandargumentation) {
        this.topicandargumentation = topicandargumentation == null ? null : topicandargumentation.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", meetingserialno=").append(meetingserialno);
        sb.append(", meetingname=").append(meetingname);
        sb.append(", fileno=").append(fileno);
        sb.append(", filecaption=").append(filecaption);
        sb.append(", keyword=").append(keyword);
        sb.append(", chieforganizer=").append(chieforganizer);
        sb.append(", meetinglocus=").append(meetinglocus);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", attendee=").append(attendee);
        sb.append(", topicandargumentation=").append(topicandargumentation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}