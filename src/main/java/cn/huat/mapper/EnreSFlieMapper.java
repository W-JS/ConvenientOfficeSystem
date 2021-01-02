package cn.huat.mapper;

import cn.huat.pojo.EnreSFlie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EnreSFlieMapper {
    int deleteByPrimaryKey(String id);

    int insert(EnreSFlie record);

    int insertSelective(EnreSFlie record);

    EnreSFlie selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EnreSFlie record);

    int updateByPrimaryKey(EnreSFlie record);

    int addEnreSFile(EnreSFlie enreSFlie);

    /*
    * 邵传中
    * */

    int selectReceiverByFileNo(String fileNo);

    String selectAffixByFileNo(String fileNo);

    /*
     * 2020-12-16
     * */
    List<EnreSFlie> selectByReceiver(Integer receiver);

}