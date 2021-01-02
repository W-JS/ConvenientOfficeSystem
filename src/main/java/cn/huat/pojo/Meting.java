package cn.huat.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class Meting implements Serializable {
    private String meetingserialno;

    private String meetingname;

    private String topic;

    private Integer organizer;

    private Integer chieforganizer;

    private Integer spokesman;

    private String meetinglocus;

    private Date starttime;

    private Date endtime;

    private Integer applicator;

    private Integer auditor;

    private String auditing;

    private Integer informer;

    private Integer minuterecorder;

    private String state;

    private static final long serialVersionUID = 1L;

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public Integer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Integer organizer) {
        this.organizer = organizer;
    }

    public Integer getChieforganizer() {
        return chieforganizer;
    }

    public void setChieforganizer(Integer chieforganizer) {
        this.chieforganizer = chieforganizer;
    }

    public Integer getSpokesman() {
        return spokesman;
    }

    public void setSpokesman(Integer spokesman) {
        this.spokesman = spokesman;
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

    public Integer getApplicator() {
        return applicator;
    }

    public void setApplicator(Integer applicator) {
        this.applicator = applicator;
    }

    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    public String getAuditing() {
        return auditing;
    }

    public void setAuditing(String auditing) {
        this.auditing = auditing == null ? null : auditing.trim();
    }

    public Integer getInformer() {
        return informer;
    }

    public void setInformer(Integer informer) {
        this.informer = informer;
    }

    public Integer getMinuterecorder() {
        return minuterecorder;
    }

    public void setMinuterecorder(Integer minuterecorder) {
        this.minuterecorder = minuterecorder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", meetingserialno=").append(meetingserialno);
        sb.append(", meetingname=").append(meetingname);
        sb.append(", topic=").append(topic);
        sb.append(", organizer=").append(organizer);
        sb.append(", chieforganizer=").append(chieforganizer);
        sb.append(", spokesman=").append(spokesman);
        sb.append(", meetinglocus=").append(meetinglocus);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", applicator=").append(applicator);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditing=").append(auditing);
        sb.append(", informer=").append(informer);
        sb.append(", minuterecorder=").append(minuterecorder);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}