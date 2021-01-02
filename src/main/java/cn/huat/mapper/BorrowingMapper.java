package cn.huat.mapper;

import cn.huat.pojo.Borrowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BorrowingMapper {
    int deleteByPrimaryKey(String borrowingno);

    int insert(Borrowing record);

    int insertSelective(Borrowing record);

    Borrowing selectByPrimaryKey(String borrowingno);

    int updateByPrimaryKeySelective(Borrowing record);

    int updateByPrimaryKey(Borrowing record);

    List<Borrowing> getBorrowings();

    List<Borrowing> selectBorrowingByLimit(int page, int pageSize);

    int getCountOfBorrowing();

    List<Borrowing> getBorrowingsByState();

    List<Borrowing> selectBorrowingByAccountAndFileNo(@Param("ileno") String ileno, @Param("borrower") Integer account);

    List<Borrowing> selectBorrowingByLimit1(int page, int pageSize, int borrower);

    int getCountOfBorrowing1(int borrower);
}