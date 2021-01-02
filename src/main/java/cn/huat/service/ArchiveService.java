package cn.huat.service;

import cn.huat.pojo.*;
import cn.huat.util.PageUtil;

import java.util.List;

public interface ArchiveService {

    int insertArchive(Archive archive);

    PageUtil<Archive> getArchivesByLimit(int page, String archivename);

    List<SFile> getAllSFile();

    List<SFileArchive> getSFileArchiveByArchiveNo(String archiveno);

    List<EnreRFile> getAllRFile();

    List<RFileArchive> getRFileArchiveByArchiveNo(String archiveno);

    int sFileArchive(String fileno, String archiveno);

    int deletesFileArchive(String fileno, String archiveno);

    List<Minute> getAllMinute();

    List<MinuteArchive> getMinuteArchiveByArchiveNo(String archiveno);

    int rFileArchive(String fileno, String archiveno);

    int minuteArchive(String fileno, String archiveno);

    int deleteRFileArchiveByFileNo(String fileno, String archiveno);

    int deleteMinuteByFileNo(String fileno, String archiveno);

    PageUtil<Borrowing> getBorrowingsByLimit(int page, Integer borrower);

    BorrowingVo getBorrowingByBorrowingNo(String borrowingno);

    int updateStateByBorrowingNo(String borrowingno, String state, Integer period);

    boolean deleteByArchiveNo(String archiveno);

    boolean getArchiveByArchiveNo(String archiveno);


    List<Borrowing> getBorrowings();

    List<Borrowing> getBorrowingsByState();

    List<Archive> getAllArchive();

    boolean insertBorrowing(Borrowing borrowing);

    boolean getArchiveByArchiveName(String archivename);

    int changeArchiveState(int closeornot, String archiveno);
}
