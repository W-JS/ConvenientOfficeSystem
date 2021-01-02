package cn.huat.pojo;

import java.io.Serializable;

public class Archive implements Serializable {
    private String archiveno;

    private String archivename;

    private Integer storageperiod;

    private Integer copies;

    private Integer closeornot;

    private Integer capacity;

    private static final long serialVersionUID = 1L;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getArchiveno() {
        return archiveno;
    }

    public void setArchiveno(String archiveno) {
        this.archiveno = archiveno == null ? null : archiveno.trim();
    }

    public String getArchivename() {
        return archivename;
    }

    public void setArchivename(String archivename) {
        this.archivename = archivename == null ? null : archivename.trim();
    }

    public Integer getStorageperiod() {
        return storageperiod;
    }

    public void setStorageperiod(Integer storageperiod) {
        this.storageperiod = storageperiod;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Integer getCloseornot() {
        return closeornot;
    }

    public void setCloseornot(Integer closeornot) {
        this.closeornot = closeornot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", archiveno=").append(archiveno);
        sb.append(", archivename=").append(archivename);
        sb.append(", storageperiod=").append(storageperiod);
        sb.append(", copies=").append(copies);
        sb.append(", closeornot=").append(closeornot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}