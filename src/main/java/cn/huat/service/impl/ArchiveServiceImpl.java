package cn.huat.service.impl;

import cn.huat.config.MailClient;
import cn.huat.mapper.*;
import cn.huat.pojo.*;
import cn.huat.service.ArchiveService;
import cn.huat.util.DateUtils;
import cn.huat.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static cn.huat.util.UUIDGenerator.get16UUID;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveMapper archiveMapper;

    @Autowired
    private SFileMapper sFileMapper;

    @Autowired
    private SFileArchiveMapper sFileArchiveMapper;

    @Autowired
    private EnreRFileMapper enreRFileMapper;

    @Autowired
    private RFileArchiveMapper rFileArchiveMapper;

    @Autowired
    private RFileMapper rFileMapper;

    @Autowired
    private MinuteMapper minuteMapper;

    @Autowired
    private MinuteArchiveMapper minuteArchiveMapper;

    @Autowired
    private BorrowingMapper borrowingMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertArchive(Archive archive) {
        int insert = archiveMapper.insertSelective(archive);
        return insert;
    }

    @Override
    public PageUtil<Archive> getArchivesByLimit(int page, String archivename) {
        PageUtil<Archive> archives = new PageUtil<>();
        archives.setPage(page);
        int count = archiveMapper.countOfArchive(archivename);
        List<Archive> data = archiveMapper.limitOfArchive(archives.getStart(), archives.getPageSize(), archivename);
        archives.setTotal(count);
        archives.setData(data);
        return archives;
    }

    @Override
    public List<SFile> getAllSFile() {
        return sFileMapper.findByStateUnArchive();
    }

    @Override
    public List<SFileArchive> getSFileArchiveByArchiveNo(String archiveno) {
        return sFileArchiveMapper.selectSFileArchiveByArchiveno(archiveno);
    }

    @Override
    public List<EnreRFile> getAllRFile() {
        return enreRFileMapper.selectByState();
    }

    @Override
    public List<RFileArchive> getRFileArchiveByArchiveNo(String archiveno) {
        return rFileArchiveMapper.selectRFileArchiveByArchiveno(archiveno);
    }


    @Override
    public int sFileArchive(String fileno, String archiveno) {
        SFile sFile = sFileMapper.selectByFileNo(fileno);
//        int i = sFileMapper.deleteByPrimaryKey(sFile.getDraftno());
        //将state设置为"已存档"
        SFile sFile1 = new SFile();
        sFile1.setState("已存档");
        sFile1.setDraftno(sFile.getDraftno());
        sFileMapper.updateByPrimaryKeySelective(sFile1);
        SFileArchive sFileArchive = new SFileArchive();
        String date = DateUtils.format(new Date()).replaceAll("-", "");
        String id = "GG_" + date + "_" + get16UUID();
        sFileArchive.setCc(sFile.getCc());
        sFileArchive.setAffixcaption(sFile.getAffixcaption());
        sFileArchive.setAffixcontent(sFile.getAffixcontent());
        sFileArchive.setArchiveno(archiveno);
        sFileArchive.setConfidential(sFile.getConfidential());
        sFileArchive.setCloseingdate(sFile.getCloseingdate());
        sFileArchive.setDraftsman(sFile.getDraftsman());
        sFileArchive.setExpress(sFile.getExpress());
        sFileArchive.setFilecaption(sFile.getFilecaption());
        sFileArchive.setFilecontent(sFile.getFilecontent());
        sFileArchive.setFileno(sFile.getFileno());
        sFileArchive.setKeyword(sFile.getKeyword());
        sFileArchive.setMainto(Integer.parseInt(sFile.getMainto()));
        sFileArchive.setId(id);

        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        int copies = archive.getCopies();
        if (copies >= archive.getCapacity()) {
            return 0;
        }
        copies = copies + 1;
        Archive archive1 = new Archive();
        archive1.setArchiveno(archiveno);
        archive1.setCopies(copies);

        int insert = sFileArchiveMapper.insert(sFileArchive);
        archiveMapper.updateByPrimaryKeySelective(archive1);
        return insert;
    }

    @Override
    public int deletesFileArchive(String fileno, String archiveno) {
        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        SFile sFile = sFileMapper.selectByFileNo(fileno);
        SFile sFile1 = new SFile();
        sFile1.setDraftno(sFile.getDraftno());
        sFile1.setState("登记完毕");
        sFileMapper.updateByPrimaryKeySelective(sFile1);
        int copies = archive.getCopies() - 1;
        Archive archive1 = new Archive();
        archive1.setArchiveno(archiveno);
        archive1.setCopies(copies);
        archiveMapper.updateByPrimaryKeySelective(archive1);
        return sFileArchiveMapper.deleteByFileNo(fileno);
    }

    @Override
    public List<Minute> getAllMinute() {
        return minuteMapper.selectAll();
    }

    @Override
    public List<MinuteArchive> getMinuteArchiveByArchiveNo(String archiveno) {
        return minuteArchiveMapper.selectMinuteArchiveByArchiveNo(archiveno);
    }

    @Override
    public int rFileArchive(String fileno, String archiveno) {
        EnreRFile enreRFile = enreRFileMapper.selectFileByFileNo(fileno);
        RFile rFile = rFileMapper.selectFileByFileNo(fileno);
        //int i = enreRFileMapper.deleteByPrimaryKey(enreRFile.getId());
//        int j = rFileMapper.deleteByPrimaryKey(rFile.getId());
        EnreRFile enreRFile1 = new EnreRFile();
        enreRFile1.setId(enreRFile.getId());
        enreRFile1.setState(7);
        enreRFileMapper.updateByPrimaryKeySelective(enreRFile1);
        RFileArchive rFileArchive = new RFileArchive();
        String date = DateUtils.format(new Date()).replaceAll("-", "");
        String id = "GG_" + date + "_" + get16UUID();
        rFileArchive.setAffixcaption(rFile.getAffixcaption());
        rFileArchive.setAffixcontent(rFile.getAffixcontent());
        rFileArchive.setArchiveno(archiveno);
        rFileArchive.setCc(rFile.getCc());
        rFileArchive.setExpress(enreRFile.getExpress());
        rFileArchive.setFileno(fileno);
        rFileArchive.setConfidential(enreRFile.getConfidential());
        rFileArchive.setKeyword(enreRFile.getKeyword());
        rFileArchive.setFilecaption(enreRFile.getFilecaption());
        rFileArchive.setFilecontent(rFile.getFilecontent());
        rFileArchive.setMainto(rFile.getMainto());
        rFileArchive.setId(id);

        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        int copies = archive.getCopies();
        if (copies >= archive.getCapacity()) {
            return 0;
        }
        copies = copies + 1;
        Archive archive1 = new Archive();
        archive1.setArchiveno(archiveno);
        archive1.setCopies(copies);
        int insert = rFileArchiveMapper.insert(rFileArchive);
        archiveMapper.updateByPrimaryKeySelective(archive1);
        return insert;
    }

    @Override
    public int minuteArchive(String fileno, String archiveno) {
        String date = DateUtils.format(new Date()).replaceAll("-", "");
//        String id = "GG_" + date + "_" + get16UUID();
        Minute minute = minuteMapper.selectByFileNo(fileno);
        int i = minuteMapper.deleteByPrimaryKey(minute.getId());
        MinuteArchive minuteArchive = new MinuteArchive();

        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        int copies = archive.getCopies();
        if (copies >= archive.getCapacity()) {
            return 0;
        }
        copies = copies + 1;
        Archive archive1 = new Archive();
        archive1.setArchiveno(archiveno);
        archive1.setCopies(copies);

        minuteArchive.setArchiveno(archiveno);
        minuteArchive.setAttendee(minute.getAttendee());
        minuteArchive.setChieforganizer(minute.getChieforganizer());
        minuteArchive.setEndtime(minute.getEndtime());
        minuteArchive.setFilecaption(minute.getKeyword());
        minuteArchive.setId(Integer.toString(minute.getId()));
        minuteArchive.setFileno(fileno);
        minuteArchive.setMeetinglocus(minute.getMeetinglocus());
        minuteArchive.setMeetingname(minute.getMeetingname());
        minuteArchive.setMeetingserialno(minute.getMeetingserialno());
        minuteArchive.setTopicandargumentation(minute.getTopicandargumentation());
        minuteArchive.setStarttime(minute.getStarttime());
        minuteArchive.setKeyword(minute.getKeyword());
        int insert = minuteArchiveMapper.insert(minuteArchive);
        archiveMapper.updateByPrimaryKeySelective(archive1);
        return insert;
    }

    @Override
    public int deleteRFileArchiveByFileNo(String fileno, String archiveno) {
        EnreRFile enreRFile = enreRFileMapper.selectFileByFileNo(fileno);
        EnreRFile enreRFile1 = new EnreRFile();
        enreRFile1.setId(enreRFile.getId());
        enreRFile1.setState(6);
        enreRFileMapper.updateByPrimaryKeySelective(enreRFile1);
        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        int copies = archive.getCopies() - 1;
        Archive archive1 = new Archive();
        archive1.setArchiveno(archiveno);
        archive1.setCopies(copies);
        archiveMapper.updateByPrimaryKeySelective(archive1);
        return rFileArchiveMapper.deleteByFileNo(fileno);
    }

    @Override
    public int deleteMinuteByFileNo(String fileno, String archiveno) {
        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        MinuteArchive minuteArchive = minuteArchiveMapper.selectByFileNo1(fileno);
        Minute minute = new Minute();
        int copies = archive.getCopies() - 1;
        Archive archive1 = new Archive();
        archive1.setArchiveno(archiveno);
        archive1.setCopies(copies);
        archiveMapper.updateByPrimaryKeySelective(archive1);

        minute.setAttendee(minuteArchive.getAttendee());
        minute.setChieforganizer(minuteArchive.getChieforganizer());
        minute.setEndtime(minuteArchive.getEndtime());
        minute.setFilecaption(minuteArchive.getKeyword());
        minute.setId(Integer.valueOf(minuteArchive.getId()));
        minute.setFileno(fileno);
        minute.setMeetinglocus(minuteArchive.getMeetinglocus());
        minute.setMeetingname(minuteArchive.getMeetingname());
        minute.setMeetingserialno(minuteArchive.getMeetingserialno());
        minute.setTopicandargumentation(minuteArchive.getTopicandargumentation());
        minute.setStarttime(minuteArchive.getStarttime());
        minute.setKeyword(minuteArchive.getKeyword());

        minuteMapper.insert(minute);
        return minuteArchiveMapper.deleteByFileNo(fileno);
    }

    @Override
    public PageUtil<Borrowing> getBorrowingsByLimit(int page, Integer borrower) {
        PageUtil<Borrowing> borrowings = new PageUtil<>();
        borrowings.setPage(page);
        int count;
        List<Borrowing> data;
        if (borrower == null) {
            count = borrowingMapper.getCountOfBorrowing();
            data = borrowingMapper.selectBorrowingByLimit(borrowings.getStart(), borrowings.getPageSize());
        } else {
            count = borrowingMapper.getCountOfBorrowing1(borrower);
            data = borrowingMapper.selectBorrowingByLimit1(borrowings.getStart(), borrowings.getPageSize(), borrower);
        }
        borrowings.setTotal(count);
        borrowings.setData(data);
        return borrowings;
    }

    @Override
    public BorrowingVo getBorrowingByBorrowingNo(String borrowingno) {
        BorrowingVo borrowingVo = new BorrowingVo();
        Borrowing borrowing = borrowingMapper.selectByPrimaryKey(borrowingno);
        borrowingVo.setIleno(borrowing.getIleno());
        borrowingVo.setBorrower(borrowing.getBorrower());
        borrowingVo.setBorrowingno(borrowing.getBorrowingno());
        borrowingVo.setState(borrowing.getState());
        borrowingVo.setApplicationtime(borrowing.getApplicationtime());
        borrowingVo.setReturntime(borrowing.getReturntime());
        borrowingVo.setApprovedtime(borrowing.getApprovedtime());
        borrowingVo.setBorrowingperiod(borrowing.getBorrowingperiod());
        borrowingVo.setArchiveno(borrowing.getArchiveno());
        User user = userMapper.selectByPrimaryKey(borrowing.getBorrower());
        if (user.getAccessauthority().equals("subAdministrator")) {
            borrowingVo.setBorrowerAuth(borrowingVo.getBorrower()+"(有权限)");
        } else {
            borrowingVo.setBorrowerAuth(borrowingVo.getBorrower()+"(无权限)");
        }
        return borrowingVo;
    }

    @Override
    public int updateStateByBorrowingNo(String borrowingno, String state, Integer period) {
        Borrowing borrowing = new Borrowing();
        borrowing.setState(state);
        borrowing.setBorrowingno(borrowingno);
        if (state.equals("1") || state.equals("2")) {
            borrowing.setApprovedtime(new Date());
        }


        if (state.equals("1")) {
//            long time = borrowing.getApprovedtime().getTime() + (long) (period * 60 * 60 * 24 * 1000);
            Date date = DateUtils.addDateDays(borrowing.getApprovedtime(), period);
            borrowing.setReturntime(date);
            borrowing.setBorrowingperiod(period);
            new Thread(()-> {
                String subject = "通知";
                String content = "你好,你借阅的文件已经审批通过";
               mailClient.sendMail("827714432@qq.com", subject, content);

            }).start();
        }

        if(state.equals("2")) {
            new Thread(()-> {
                String subject = "通知";
                String content = "你好,你借阅的文件已经审批未通过,你没有权限查看";
                mailClient.sendMail("827714432@qq.com", subject, content);

            }).start();
        }
        int i = borrowingMapper.updateByPrimaryKeySelective(borrowing);
        return i;
    }

    @Override
    public boolean deleteByArchiveNo(String archiveno) {
        boolean flag = false;
        if (archiveno.endsWith("1")) {
            List<SFileArchive> sFileArchives = sFileArchiveMapper.selectSFileArchiveByArchiveno(archiveno);
            if (sFileArchives.size() <= 0) {
                flag = true;
                archiveMapper.deleteByPrimaryKey(archiveno);
            }
        } else if (archiveno.endsWith("2")){
            List<RFileArchive> rFileArchives = rFileArchiveMapper.selectRFileArchiveByArchiveno(archiveno);
            if (rFileArchives.size() <= 0) {
                flag = true;
                archiveMapper.deleteByPrimaryKey(archiveno);
            }
        } else {
            List<MinuteArchive> minuteArchives = minuteArchiveMapper.selectMinuteArchiveByArchiveNo(archiveno);
            if (minuteArchives.size() <= 0) {
                flag = true;
                archiveMapper.deleteByPrimaryKey(archiveno);
            }
        }

        return flag;
    }

    @Override
    public boolean getArchiveByArchiveNo(String archiveno) {
        Archive archive = archiveMapper.selectByPrimaryKey(archiveno);
        if (archive != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean getArchiveByArchiveName(String archivename) {
        Archive archive = archiveMapper.selectByName(archivename);
        if (archive != null) {
            return false;
        } else {

            return true;
        }
    }

    @Override
    public int changeArchiveState(int closeornot, String archiveno) {
        Archive archive = new Archive();
        archive.setCloseornot(closeornot);
        archive.setArchiveno(archiveno);
        return archiveMapper.updateByPrimaryKeySelective(archive);
    }

    @Override
    public List<Borrowing> getBorrowings() {
        return borrowingMapper.getBorrowings();
    }

    @Override
    public List<Borrowing> getBorrowingsByState() {
        return borrowingMapper.getBorrowingsByState();
    }

    @Override
    public List<Archive> getAllArchive() {
        return archiveMapper.getAllArchive();
    }

    @Override
    public boolean insertBorrowing(Borrowing borrowing) {
        List<Borrowing> borrowings = borrowingMapper.selectBorrowingByAccountAndFileNo(borrowing.getIleno(), borrowing.getBorrower());
        if (borrowings != null && borrowings.size() > 0) {
            for (Borrowing e : borrowings) {

                if (e != null &&(
                        e.getState().equals("0") ||
                                e.getState().equals("1") ||
                                e.getState().equals("3")) ) {
                    return false;
                }
            }
        }
        Date applicationTime = new Date();
        String id = applicationTime.getTime() + "";
        borrowing.setBorrowingno(id);
        borrowing.setApplicationtime(applicationTime);
        borrowing.setState("0");
        borrowingMapper.insertSelective(borrowing);
        return true;
    }


}
