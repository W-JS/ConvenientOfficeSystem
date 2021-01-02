package cn.huat.pojo;

import java.io.Serializable;

public class RFileArchive implements Serializable {
    private String id;

    private String fileno;

    private String filecaption;

    private String keyword;

    private String affixcaption;

    private Integer mainto;

    private String cc;

    private String confidential;

    private String express;

    private String filecontent;

    private String affixcontent;

    private String archiveno;

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

    public String getAffixcaption() {
        return affixcaption;
    }

    public void setAffixcaption(String affixcaption) {
        this.affixcaption = affixcaption == null ? null : affixcaption.trim();
    }

    public Integer getMainto() {
        return mainto;
    }

    public void setMainto(Integer mainto) {
        this.mainto = mainto;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
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

    public String getArchiveno() {
        return archiveno;
    }

    public void setArchiveno(String archiveno) {
        this.archiveno = archiveno == null ? null : archiveno.trim();
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
        sb.append(", affixcaption=").append(affixcaption);
        sb.append(", mainto=").append(mainto);
        sb.append(", cc=").append(cc);
        sb.append(", confidential=").append(confidential);
        sb.append(", express=").append(express);
        sb.append(", filecontent=").append(filecontent);
        sb.append(", affixcontent=").append(affixcontent);
        sb.append(", archiveno=").append(archiveno);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}