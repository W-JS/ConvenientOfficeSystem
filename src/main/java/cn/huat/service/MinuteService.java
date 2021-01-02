package cn.huat.service;

import cn.huat.pojo.Minute;

import java.util.List;

public interface MinuteService {
    int insertMinute(Minute minute);

    Minute findById(Integer id);

    List<Minute> findall(int user);//通过user查找当前user记录的所有会议纪要


    List<Minute> findallMinutes();
}
