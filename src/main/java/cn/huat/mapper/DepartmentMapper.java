package cn.huat.mapper;


import cn.huat.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description
 *
 * @author helaxest
 * @date 2020/12/04  15:20
 * @mail:
 * @since JDK 1.8
 */
@Mapper
@Repository
public interface DepartmentMapper {
    List<Department> findDepartment();
    String findByNo(int no);
}
