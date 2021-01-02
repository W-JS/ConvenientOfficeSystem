package cn.huat.service.EnreRFileService;

import cn.huat.pojo.EnreRFile;
import cn.huat.pojo.SFile;

import java.util.List;

public interface EnreRFileService {
    //获取所有发文根据指定的状态
    public List<SFile> getAllSFileByState(String  state);

    //获取收文登记表中所有收文文号
    public List<String> getAllEnreRFileFileNo();

    public  List<EnreRFile> getAllEnreRFileByStateAndReceiver(int state,int receiver);
}
