package cn.huat.pojo;

import java.io.Serializable;
import java.util.Date;

public class SFile implements Serializable {
    private String draftno;

    private Date draftdate;

    private String filecaption;

    private String affixcaption;

    private String mainto;

    private Date closeingdate;

    private String keyword;

    private Integer draftsman;

    private String confidential;

    private String express;

    private String fileno;

    private Integer copies;

    private String cc;

    private Integer auditor;

    private String auditing;

    private Integer checkingman;

    private String checking;

    private Integer signator;

    private String signing;

    private Integer distributor;

    private String distributing;

    private String filecontent;

    private String affixcontent;

    private Integer send;

    private String state;

    private static final long serialVersionUID = 1L;

    public String getDraftno() {
        return draftno;
    }

    public void setDraftno(String draftno) {
        this.draftno = draftno == null ? null : draftno.trim();
    }

    public Date getDraftdate() {
        return draftdate;
    }

    public void setDraftdate(Date draftdate) {
        this.draftdate = draftdate;
    }

    public String getFilecaption() {
        return filecaption;
    }

    public void setFilecaption(String filecaption) {
        this.filecaption = filecaption == null ? null : filecaption.trim();
    }

    public String getAffixcaption() {
        return affixcaption;
    }

    public void setAffixcaption(String affixcaption) {
        this.affixcaption = affixcaption == null ? null : affixcaption.trim();
    }

    public String getMainto() {
        return mainto;
    }

    public void setMainto(String mainto) {
        this.mainto = mainto == null ? null : mainto.trim();
    }

    public Date getCloseingdate() {
        return closeingdate;
    }

    public void setCloseingdate(Date closeingdate) {
        this.closeingdate = closeingdate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getDraftsman() {
        return draftsman;
    }

    public void setDraftsman(Integer draftsman) {
        this.draftsman = draftsman;
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

    public String getFileno() {
        return fileno;
    }

    public void setFileno(String fileno) {
        this.fileno = fileno == null ? null : fileno.trim();
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
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

    public Integer getCheckingman() {
        return checkingman;
    }

    public void setCheckingman(Integer checkingman) {
        this.checkingman = checkingman;
    }

    public String getChecking() {
        return checking;
    }

    public void setChecking(String checking) {
        this.checking = checking == null ? null : checking.trim();
    }

    public Integer getSignator() {
        return signator;
    }

    public void setSignator(Integer signator) {
        this.signator = signator;
    }

    public String getSigning() {
        return signing;
    }

    public void setSigning(String signing) {
        this.signing = signing == null ? null : signing.trim();
    }

    public Integer getDistributor() {
        return distributor;
    }

    public void setDistributor(Integer distributor) {
        this.distributor = distributor;
    }

    public String getDistributing() {
        return distributing;
    }

    public void setDistributing(String distributing) {
        this.distributing = distributing == null ? null : distributing.trim();
    }

    public String getFilecontent() {
        return filecontent;
    }

    public void setFilecontent(String filecontent) {
        this.filecontent = filecontent == null ? null : filecontent.trim();
    }

    public String getAffixcontent() {
        return affixcontent;
    }

    public void setAffixcontent(String affixcontent) {
        this.affixcontent = affixcontent == null ? null : affixcontent.trim();
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
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
        sb.append(", draftno=").append(draftno);
        sb.append(", draftdate=").append(draftdate);
        sb.append(", filecaption=").append(filecaption);
        sb.append(", affixcaption=").append(affixcaption);
        sb.append(", mainto=").append(mainto);
        sb.append(", closeingdate=").append(closeingdate);
        sb.append(", keyword=").append(keyword);
        sb.append(", draftsman=").append(draftsman);
        sb.append(", confidential=").append(confidential);
        sb.append(", express=").append(express);
        sb.append(", fileno=").append(fileno);
        sb.append(", copies=").append(copies);
        sb.append(", cc=").append(cc);
        sb.append(", auditor=").append(auditor);
        sb.append(", auditing=").append(auditing);
        sb.append(", checkingman=").append(checkingman);
        sb.append(", checking=").append(checking);
        sb.append(", signator=").append(signator);
        sb.append(", signing=").append(signing);
        sb.append(", distributor=").append(distributor);
        sb.append(", distributing=").append(distributing);
        sb.append(", filecontent=").append(filecontent);
        sb.append(", affixcontent=").append(affixcontent);
        sb.append(", send=").append(send);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}