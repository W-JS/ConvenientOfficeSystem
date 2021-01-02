package cn.huat.service.impl.EnreRFileServiceImpl;

import cn.huat.mapper.*;
import cn.huat.pojo.EnreRFile;
import cn.huat.pojo.EnreSFlie;
import cn.huat.pojo.RFile;
import cn.huat.pojo.SFile;
import cn.huat.service.EnreRFileService.EnreRFileService;
import cn.huat.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EnreRFileServiceImpl implements EnreRFileService {

    @Autowired
    SFileMapper sFileMapper;

    @Autowired
    RFileMapper rFileMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    EnreRFileMapper enreRFileMapper;

    @Autowired
    EnreSFlieMapper enreSFlieMapper;
    /*
     * 通过状态获取以签发的发文
     * */

    @Override
    public List<SFile> getAllSFileByState(String state) {
        List<SFile> sFiles;
        sFiles = sFileMapper.selectByState(state);
        return sFiles;
    }


    @Override
    public List<String> getAllEnreRFileFileNo() {
        //获取收文登记表中所有的文号
        List<String> listR = rFileMapper.selectByFileNo();
        return listR;
    }


    @Override
    public List<EnreRFile> getAllEnreRFileByStateAndReceiver(int state , int receiver) {
        List<EnreRFile> EnreRFilelists =  enreRFileMapper.selectByStateAndReceiver(state,receiver);
        return EnreRFilelists;
    }

    public List getEnreRFile(){
        /*
        * 2020-12-16
        * */
//        List<SFile> sFilesAll =newRegistrationFiles();

        List<EnreSFlie> sFilesAll =newRegistrationFiles();

        System.out.println("2121212"+sFilesAll.toString());

        List<Map<String,Object>> resList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        if(sFilesAll.size()>0){
            for (int i = 0; i < sFilesAll.size(); i++) {
                Map<String,Object> res = new HashMap<>();
                res.put("senderOrgan", userMapper.selectByPrimaryKey(sFileMapper.selectByFileNo(sFilesAll.get(i).getFileno()).getDraftsman()).getDepartment());//发文部门
//                res.put("receiver", enreSFlieMapper.selectReceiverByFileNo(sFilesAll.get(i).getFileno()));//发文的签收人
                res.put("receiver", sFilesAll.get(i).getReceiver());//发文的签收人
                res.put("haveAffix",sFilesAll.get(i).getHaveaffix());
                res.put("sfile",sFilesAll.get(i));
                res.put("copies",sFileMapper.selectByFileNo(sFilesAll.get(i).getFileno()).getCopies());
                if (loginId.equals(String.valueOf(res.get("receiver")))){
                    resList.add(res);
                }
            }
        }
        return resList;

    }

    /*
    * 获取所要录入的收文
    * */

    public List<EnreRFile> getAllRFileByState(int state,int id){
        return  enreRFileMapper.selectByStateAndId(state,id);
    }

    public List<EnreRFile> getAllRFileByStateAndAudit(int state,int id){
        return  enreRFileMapper.selectByStateAndAudit(state,id);
    }


    public List<EnreRFile> getAllRFileByStateAndDraftsman(int state,int id){
        return  enreRFileMapper.selectByStateAndDraftsman(state,id);
    }
    public List<EnreRFile> getAllRFileByStateAndAuthorizeman(int state,int id){
        return  enreRFileMapper.selectByStateAndAuthorizeman(state,id);
    }
    public List<EnreRFile> getAllRFileByStateAndTranPerson(int state,int id){
        return  enreRFileMapper.selectByStateAndTranPerson(state,id);
    }


    /*
    * 通过文号获取收文
    * */

    public SFile getAllSFileByFileNo(String fileno){
        return sFileMapper.selectByFileNo(fileno);
    }

    /*
     * 通过文号获取收文表中数据
     * */

    public RFile getAllRFileByFileNo(String fileno){
        return rFileMapper.selectRFileByFileNo(fileno);
    }


    /*
    *
    * 获取新登记收文
    * */
    public List<EnreSFlie> newRegistrationFiles() {
//        List<SFile> list = getAllSFileByState("登记完毕");//获取发文状态的发文

        /*
        * 2020-12-16
        * */

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<EnreSFlie> list = enreSFlieMapper.selectByReceiver(Integer.parseInt(authentication.getName()));

        System.out.println(list.toString());

        List<String> EnreRFileFileNolists = getAllEnreRFileFileNo();//获取收文登记表中所有收文的文号
        EnreSFlie sFile = new EnreSFlie();

        if (list.size() == 0) {
            return list;
        } else if (EnreRFileFileNolists.size() == 0) {
            return list;
        } else {
            /*
            * 去除发文登记表中和发文一样的文号，
            * 剩下的就是需要进行登记的发文
            * */
            Iterator<EnreSFlie> sFiles = list.iterator();
            List<EnreSFlie> listres = new ArrayList<>();
            boolean flag =true;
            while (sFiles.hasNext()) {
                sFile = sFiles.next();
                flag =true;//进行重置
                for (int i = 0; i < EnreRFileFileNolists.size(); i++) {
                    if(sFile.getFileno().equals(EnreRFileFileNolists.get(i))){
                        sFiles.remove();
                        flag=false;//有重复
                    }
                }
                if(flag){
                    listres.add(sFile);
                }
            }
            return listres;
        }
    }

    /*
    * 增加收文
    * */
    @Transactional
    public void insertRFile(RFile rFile,EnreRFile enreRFile){
        rFile.setId(UUIDGenerator.get32UUID());
        rFileMapper.insert(rFile);
        enreRFileMapper.updateByPrimaryKeySelective(enreRFile);
    }

    /*
    * 更新收文登记表的状态
    * */
    public void updateRFile(EnreRFile enreRFile){
        enreRFileMapper.updateByPrimaryKeySelective(enreRFile);
    }

}
