package cn.huat.service;

import cn.huat.pojo.EnreSFlie;

import java.util.List;

/**
 * Description
 *
 * @author helaxest
 * @date 2020/11/29  17:01
 * @mail:
 * @since JDK 1.8
 */
public interface EnreSFileService {

    /**
     * 添加发文登记
     * @param enreSFile
     * @return 大于0则操作成功
     */
    int addEnreSFile(EnreSFlie enreSFile);



}
