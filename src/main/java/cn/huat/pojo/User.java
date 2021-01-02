package cn.huat.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class User implements Serializable {
    private Integer account;

    private String name;

    private String department;

    private String occupation;

    private String accessauthority;

    private String password;

    private static final long serialVersionUID = 1L;

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

    public String getAccessauthority() {
        return accessauthority;
    }

    public void setAccessauthority(String accessauthority) {
        this.accessauthority = accessauthority == null ? null : accessauthority.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", account=").append(account);
        sb.append(", name=").append(name);
        sb.append(", department=").append(department);
        sb.append(", occupation=").append(occupation);
        sb.append(", accessauthority=").append(accessauthority);
        sb.append(", password=").append(password);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}