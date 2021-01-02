package cn.huat.pojo;

import java.io.Serializable;
import java.util.Date;

public class EnreRFile implements Serializable {
    private String id;

    private String fileno;

    private String receivedate;

    private String senderorgan;

    private String filecaption;

    private String keyword;

    private String haveaffix;

    private String confidential;

    private String express;

    private Integer copies;

    private Integer state;

    private Integer recorder;

    private Integer auditor;

    private String auditing;

    private Integer draftsman;

    private String drafting;

    private Integer authorizeman;

    private String authorizing;

    private Integer tranperson;

    private String transacting;

    private String remark;

    private Integer receiver;

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

    public String getReceivedate() {
        return receivedate;
    }

    public void setReceivedate(String receivedate) {
        this.receivedate = receivedate;
    }

    public String getSenderorgan() {
        return senderorgan;
    }

    public void setSenderorgan(String senderorgan) {
        this.senderorgan = senderorgan == null ? null : senderorgan.trim();
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

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRecorder() {
        return recorder;
    }

    public void setRecorder(Integer recorder) {
        this.recorder = recorder;
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

    public Integer getDraftsman() {
        return draftsman;
    }

    public void setDraftsman(Integer draftsman) {
        this.draftsman = draftsman;
    }

    public String getDrafting() {
        return drafting;
    }

    public void setDrafting(String drafting) {
        this.drafting = drafting == null ? null : drafting.trim();
    }

    public Integer getAuthorizeman() {
        return authorizeman;
    }

    public void setAuthorizeman(Integer authorizeman) {
        this.authorizeman = authorizeman;
    }

    public String getAuthorizing() {
        return authorizing;
    }

    public void setAuthorizing(String authorizing) {
        this.authorizing = authorizing == null ? null : authorizing.trim();
    }

    public Integer getTranperson() {
        return tranperson;
    }

    public void setTranperson(Integer tranperson) {
        this.tranperson = tranperson;
    }

    public String getTransacting() {
        return transacting;
    }

    public void setTransacting(String transacting) {
        this.transacting = transacting == null ? null : transacting.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileno=").append(fileno);
        sb.append(", receivedate=").append(receivedate);
        sb.append(", senderorgan=").append(senderorgan);
        sb.append(", filecaption=").append(filecaption);
        sb.append(", keyword=").append(keyword);
        sb.append(", haveaffix=").append(haveaffix);
        sb.append(", confidential=").append(confidential);
        sb.append(", express=").append(express);
        sb.append(", copies=").append(copies);
        sb.append(", state=").append(state);
        sb.append(", recorder=").append(recorder);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditing=").append(auditing);
        sb.append(", draftsman=").append(draftsman);
        sb.append(", drafting=").append(drafting);
        sb.append(", authorizeman=").append(authorizeman);
        sb.append(", authorizing=").append(authorizing);
        sb.append(", tranperson=").append(tranperson);
        sb.append(", transacting=").append(transacting);
        sb.append(", remark=").append(remark);
        sb.append(", receiver=").append(receiver);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}