package cn.huat.controller;

import cn.huat.pojo.*;
import cn.huat.service.ArchiveService;
import cn.huat.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenggen
 * @date date: 2020/12/06 14:02<br/>
 *
 */
@RequestMapping("/archive")
@Controller
public class ArchiveManagementController {
    @Autowired
    private ArchiveService archiveService;

    @RequestMapping("/index")
    public String management() {
        return "archive/advertisement";
    }

    @RequestMapping("/add")
    public String add() {
        return "archive/add";
    }

    @RequestMapping(value = "/doadd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doAdd(Archive archive) {
        Map<String, Object> modelMap = new HashMap<>();
        int i = archiveService.insertArchive(archive);
        if (i < 0) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "insert message failed");
        } else {
            modelMap.put("success", true);
        }

        return modelMap;
    }

    /**
     *
     * @param page 页码
     * @param archivename 案卷名
     * @return
     */
    @GetMapping("/archives")
    @ResponseBody
    public Map<String, Object> allArchives(int page, String archivename) {
        Map<String, Object> modelMap = new HashMap<>();
        PageUtil<Archive> archives = archiveService.getArchivesByLimit(page, archivename);
        if (archives.getData().size() > 0) {
            modelMap.put("success", true);
            modelMap.put("page", archives);
        } else {
            modelMap.put("success", false);
        }

        return modelMap;
    }

    @GetMapping("/filearchive/{archiveno}")
    public String fileArchive(@PathVariable("archiveno") String archiveno, Model model) {
        if (archiveno.endsWith("1")) {
            List<SFile> allFile = archiveService.getAllSFile();
            List<SFileArchive> fileArchives = archiveService.getSFileArchiveByArchiveNo(archiveno);
            model.addAttribute("allFile", allFile);
            model.addAttribute("fileArchives", fileArchives);
        } else if (archiveno.endsWith("2")){
            List<EnreRFile> allFile = archiveService.getAllRFile();
            List<RFileArchive> fileArchives = archiveService.getRFileArchiveByArchiveNo(archiveno);
            model.addAttribute("allFile", allFile);
            model.addAttribute("fileArchives", fileArchives);
        } else {
            List<Minute> allFile = archiveService.getAllMinute();
            List<MinuteArchive> fileArchives = archiveService.getMinuteArchiveByArchiveNo(archiveno);
            model.addAttribute("allFile", allFile);
            model.addAttribute("fileArchives", fileArchives);
        }
        model.addAttribute("archiveno", archiveno);
        return "archive/load";
    }

    @PostMapping("/doaddfile")
    @ResponseBody
    public Map<String, Object> doAddFile(String archiveno, String fileno) {
        Map<String, Object> modelMap = new HashMap<>();
        int i;
        if (archiveno.endsWith("1")) {
            i = archiveService.sFileArchive(fileno, archiveno);
        } else if (archiveno.endsWith("2")){
            i = archiveService.rFileArchive(fileno, archiveno);
        } else {
            i = archiveService.minuteArchive(fileno, archiveno);
        }

        if (i <= 0) {
            modelMap.put("success", false);
        } else {
            modelMap.put("success", true);
        }
        return modelMap;
    }

    @DeleteMapping("/removefile")
    @ResponseBody
    public Map<String, Object> removeFile(String fileno1, String archiveno) {
        Map<String, Object> modelMap = new HashMap<>();
        int i;
        if (archiveno.endsWith("1")) {
            i = archiveService.deletesFileArchive(fileno1, archiveno);
        } else if (archiveno.endsWith("2")){
            i = archiveService.deleteRFileArchiveByFileNo(fileno1, archiveno);
        } else {
            i = archiveService.deleteMinuteByFileNo(fileno1, archiveno);
        }

        if (i <= 0) {
            modelMap.put("success", false);
        } else {
            modelMap.put("success", true);
        }
        return modelMap;
    }

    @GetMapping("/borrowing")
    @ResponseBody
    public Map<String, Object> getBorrowing(int page) {
        Map<String, Object> modelMap = new HashMap<>();
        PageUtil<Borrowing> borrowings = archiveService.getBorrowingsByLimit(page, null);

        if (borrowings.getData().size() > 0) {
            modelMap.put("page", borrowings);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
        }

        return modelMap;
    }

    @RequestMapping("/borrowingindex")
    public String borrowingIndex() {
        return "archive/borrowing";
    }

    @GetMapping("/approval/{borrowingno}")
    public String approvalBorrowing(@PathVariable("borrowingno") String borrowingno, Model model) {
        BorrowingVo borrowing = archiveService.getBorrowingByBorrowingNo(borrowingno);
        model.addAttribute("borrowing", borrowing);
        return "archive/approval";
    }

    @RequestMapping("/signon/{borrowingno}")
    public String approval(@PathVariable("borrowingno") String borrowingno, Model model) {
        Borrowing borrowing = archiveService.getBorrowingByBorrowingNo(borrowingno);
        model.addAttribute("borrowing", borrowing);
        return "archive/signon";
    }

    @RequestMapping("/state/{state}/{borrowingno}/{period}")
    public String updateState(@PathVariable("state") String state,
                              @PathVariable("borrowingno") String borrowingno,
                              @PathVariable("period") int period) {
        int i = archiveService.updateStateByBorrowingNo(borrowingno, state,period);
        return "archive/borrowing";
    }

    @DeleteMapping("/filearchive1")
    @ResponseBody
    public Map<String, Object> deleteArchive(String archiveno) {
        Map<String, Object> modelMap = new HashMap<>();
        boolean b = archiveService.deleteByArchiveNo(archiveno);
        modelMap.put("success", b);
        return modelMap;

    }

    @RequestMapping("/getarchive")
    @ResponseBody
    public Map<String, Object> getArchive(String archiveno) {
        Map<String, Object> modelMap = new HashMap<>();
        boolean flag = archiveService.getArchiveByArchiveNo(archiveno);
        modelMap.put("success", flag);
        return modelMap;
    }

    @RequestMapping("/getarchive1")
    @ResponseBody
    public Map<String, Object> getArchive1(String archivename) {
        Map<String, Object> modelMap = new HashMap<>();
        boolean flag = archiveService.getArchiveByArchiveName(archivename);
        modelMap.put("success", flag);
        return modelMap;
    }

    @RequestMapping("/addBorrowing")
    public String borrow() {
        return "archive/borrow";
    }

    @GetMapping("/arc")
    @ResponseBody
    public Map<String, Object> getAllArchives() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Archive> archives = archiveService.getAllArchive();
        if (archives.size() > 0) {
            modelMap.put("success", true);
            modelMap.put("archives", archives);
        } else {
            modelMap.put("success", false);
        }

        return modelMap;
    }

    @GetMapping("/file")
    @ResponseBody
    public Map<String, Object> getFile(String archiveno) {
        Map<String, Object> modelMap = new HashMap<>();
        if (archiveno.endsWith("1")) {
            List<SFileArchive> sFile = archiveService.getSFileArchiveByArchiveNo(archiveno);
            modelMap.put("files", sFile);
        } else if (archiveno.endsWith("2")){
            List<RFileArchive> rFile = archiveService.getRFileArchiveByArchiveNo(archiveno);
            modelMap.put("files", rFile);
        } else {
            List<MinuteArchive> minute = archiveService.getMinuteArchiveByArchiveNo(archiveno);
            modelMap.put("files", minute);
        }
        modelMap.put("success", true);
        return modelMap;
    }

    @PostMapping("/apply")
    @ResponseBody
    public Map<String, Object> apply(Borrowing borrowing) {
        Map<String, Object> modelMap = new HashMap<>();
        boolean flag = archiveService.insertBorrowing(borrowing);
        if (flag) {
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
        }

        return modelMap;
    }

    @RequestMapping("/borrow")
    public String addBorrow() {
        return "archive/myborrowing";
    }

    @GetMapping("/myborrowing")
    @ResponseBody
    public Map<String, Object> getMyBorrowing(int page, HttpSession session) {
        Map<String, Object> modelMap = new HashMap<>();
        Integer account = (Integer) session.getAttribute("account");
        PageUtil<Borrowing> borrowings = archiveService.getBorrowingsByLimit(page, account);

        if (borrowings.getData().size() > 0) {
            modelMap.put("page", borrowings);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
        }

        return modelMap;
    }

    @PostMapping("/restore")
    @ResponseBody
    public Map<String, Object> restore(String borrowingno) {
        Map<String, Object> modelMap = new HashMap<>();

        int i = archiveService.updateStateByBorrowingNo(borrowingno, "3", null);
        if (i > 0) {
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
        }
        return modelMap;
    }

    @RequestMapping("/archivestate/{closeornot}/{archiveno}")
    public String archivestate(@PathVariable("closeornot") int closeornot, @PathVariable("archiveno") String archiveno) {
        int i = archiveService.changeArchiveState(closeornot, archiveno);
        return "archive/advertisement";
    }
}
