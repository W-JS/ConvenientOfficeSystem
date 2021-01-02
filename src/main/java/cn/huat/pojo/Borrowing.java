package cn.huat.pojo;

import java.io.Serializable;
import java.util.Date;

public class Borrowing implements Serializable {
    private String borrowingno;

    private String archiveno;

    private Integer borrower;

    private String ileno;

    private Integer borrowingperiod;

    private Date applicationtime;

    private Date returntime;

    private String state;

    private Date approvedtime;

    private static final long serialVersionUID = 1L;

    public String getBorrowingno() {
        return borrowingno;
    }

    public void setBorrowingno(String borrowingno) {
        this.borrowingno = borrowingno == null ? null : borrowingno.trim();
    }

    public String getArchiveno() {
        return archiveno;
    }

    public void setArchiveno(String archiveno) {
        this.archiveno = archiveno == null ? null : archiveno.trim();
    }

    public Integer getBorrower() {
        return borrower;
    }

    public void setBorrower(Integer borrower) {
        this.borrower = borrower;
    }

    public String getIleno() {
        return ileno;
    }

    public void setIleno(String ileno) {
        this.ileno = ileno == null ? null : ileno.trim();
    }

    public Integer getBorrowingperiod() {
        return borrowingperiod;
    }

    public void setBorrowingperiod(Integer borrowingperiod) {
        this.borrowingperiod = borrowingperiod;
    }

    public Date getApplicationtime() {
        return applicationtime;
    }

    public void setApplicationtime(Date applicationtime) {
        this.applicationtime = applicationtime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getApprovedtime() {
        return approvedtime;
    }

    public void setApprovedtime(Date approvedtime) {
        this.approvedtime = approvedtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", borrowingno=").append(borrowingno);
        sb.append(", archiveno=").append(archiveno);
        sb.append(", borrower=").append(borrower);
        sb.append(", ileno=").append(ileno);
        sb.append(", borrowingperiod=").append(borrowingperiod);
        sb.append(", applicationtime=").append(applicationtime);
        sb.append(", returntime=").append(returntime);
        sb.append(", state=").append(state);
        sb.append(", approvedtime=").append(approvedtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}