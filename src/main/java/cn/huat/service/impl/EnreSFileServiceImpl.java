package cn.huat.service.impl;


import cn.huat.mapper.EnreSFlieMapper;
import cn.huat.pojo.EnreSFlie;
import cn.huat.service.EnreSFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author helaxest
 * @date 2020/11/29  17:02
 * @mail:
 * @since JDK 1.8
 */
@Service("enreSFileService")
public class EnreSFileServiceImpl implements EnreSFileService {
    @Resource
    private EnreSFlieMapper enreSFlieMapper;

    @Override
    public int addEnreSFile(EnreSFlie enreSFile) {
        return enreSFlieMapper.insert(enreSFile);
    }


}
