package cn.huat.service.impl;

import cn.huat.mapper.SFileMapper;
import cn.huat.pojo.SFile;
import cn.huat.service.SFileService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description
 *
 * @author helaxest
 * @date 2020/11/29  16:38
 * @mail:
 * @since JDK 1.8
 */
@Service("sFileService")
public class SFileServiceImpl implements SFileService {
    @Resource
    private SFileMapper sFileMapper;

    @Override
    public int addSFile(SFile sFile) {
        return sFileMapper.insert(sFile);
    }

    @Override
    public int addSFileByDraftsman(SFile sFile) {
        return sFileMapper.insert(sFile);
    }

    @Override
    public List<SFile> findByState(String state) {
        return sFileMapper.findByState(state);
    }

    @Override
    public int deleteSFileByDraftno(String draftno) {
        return sFileMapper.deleteByPrimaryKey(draftno);
    }
    @Override
    public int updateSFile(SFile sFile) {
        return sFileMapper.updateByPrimaryKeySelective(sFile);
    }

    @Override
    public SFile findSFileByDraftno(String draftno) {
        return sFileMapper.selectByPrimaryKey(draftno);
    }

    @Override
    public List<SFile> findAll() {
        return sFileMapper.findAll();
    }

    @Override
    public SFile findSFileByFileno(String fileNo) {
        return sFileMapper.findByfileNo(fileNo);
    }
}
