package cn.huat.pojo;



/**
 * Description
 *
 * @author helaxest
 * @date 2020/12/04  15:18
 * @mail:
 * @since JDK 1.8
 */

public class Department {
    String departmentNo;
    String departmentName;

    public Department() {
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentNo='" + departmentNo + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
