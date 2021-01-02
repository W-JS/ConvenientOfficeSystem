package cn.huat.pojo;

import java.io.Serializable;
import java.util.Date;

public class EnreSFlie implements Serializable {
    private String id;

    private String fileno;

    private String filecaption;

    private String keyword;

    private Date senddate;

    private String receiverorgan;

    private Integer receiver;

    private String haveaffix;

    private String confidential;

    private String express;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getReceiverorgan() {
        return receiverorgan;
    }

    public void setReceiverorgan(String receiverorgan) {
        this.receiverorgan = receiverorgan == null ? null : receiverorgan.trim();
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getHaveaffix() {
        return haveaffix;
    }

    public void setHaveaffix(String haveaffix) {
        this.haveaffix = haveaffix == null ? null : haveaffix.trim();
    }

    public String getConfidential() {
        return confidential;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential == null ? null : confidential.trim();
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileno=").append(fileno);
        sb.append(", filecaption=").append(filecaption);
        sb.append(", keyword=").append(keyword);
        sb.append(", senddate=").append(senddate);
        sb.append(", receiverorgan=").append(receiverorgan);
        sb.append(", receiver=").append(receiver);
        sb.append(", haveaffix=").append(haveaffix);
        sb.append(", confidential=").append(confidential);
        sb.append(", express=").append(express);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}