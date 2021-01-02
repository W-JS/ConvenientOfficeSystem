package cn.huat.pojo;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    // 公告编号
    private String noticeno;
    // 发布人
    private String publishngmaker;
    // 发布人部门
    private String deaprtment;
    // 主题
    private String topic;
    // 正文
    private String content;
    // 发布时间
    private Date publishingtime;
    // 过期时间
    private Date expirationtime;
    // 公告状态
    private Integer state;
    // 审核状态
    private Integer auditstatus;

    private static final long serialVersionUID = 1L;

    public String getNoticeno() {
        return noticeno;
    }

    public void setNoticeno(String noticeno) {
        this.noticeno = noticeno == null ? null : noticeno.trim();
    }

    public String getPublishngmaker() {
        return publishngmaker;
    }

    public void setPublishngmaker(String publishngmaker) {
        this.publishngmaker = publishngmaker;
    }

    public String getDeaprtment() {
        return deaprtment;
    }

    public void setDeaprtment(String deaprtment) {
        this.deaprtment = deaprtment == null ? null : deaprtment.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPublishingtime() {
        return publishingtime;
    }

    public void setPublishingtime(Date publishingtime) {
        this.publishingtime = publishingtime;
    }

    public Date getExpirationtime() {
        return expirationtime;
    }

    public void setExpirationtime(Date expirationtime) {
        this.expirationtime = expirationtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", noticeno=").append(noticeno);
        sb.append(", publishngmaker=").append(publishngmaker);
        sb.append(", deaprtment=").append(deaprtment);
        sb.append(", topic=").append(topic);
        sb.append(", content=").append(content);
        sb.append(", publishingtime=").append(publishingtime);
        sb.append(", expirationtime=").append(expirationtime);
        sb.append(", state=").append(state);
        sb.append(", auditstatus=").append(auditstatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}