package cn.huat.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
/****/
@Component
public class Attendee implements Serializable {
    private Integer id;

    private String meetingserialno;

    private String department;

    private String occupation;

    private Integer account;

    private String name;

    private Integer informationornot;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getInformationornot() {
        return informationornot;
    }

    public void setInformationornot(Integer informationornot) {
        this.informationornot = informationornot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", meetingserialno=").append(meetingserialno);
        sb.append(", department=").append(department);
        sb.append(", occupation=").append(occupation);
        sb.append(", account=").append(account);
        sb.append(", name=").append(name);
        sb.append(", informationornot=").append(informationornot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}