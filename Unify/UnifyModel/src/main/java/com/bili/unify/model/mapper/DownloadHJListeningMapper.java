package com.bili.unify.model.mapper;

import com.bili.unify.model.DownloadHJListening;
import com.bili.unify.model.DownloadHJListeningExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadHJListeningMapper {
    long countByExample(DownloadHJListeningExample example);

    int deleteByExample(DownloadHJListeningExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DownloadHJListening record);

    int insertSelective(DownloadHJListening record);

    List<DownloadHJListening> selectByExample(DownloadHJListeningExample example);

    DownloadHJListening selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DownloadHJListening record, @Param("example") DownloadHJListeningExample example);

    int updateByExample(@Param("record") DownloadHJListening record, @Param("example") DownloadHJListeningExample example);

    int updateByPrimaryKeySelective(DownloadHJListening record);

    int updateByPrimaryKey(DownloadHJListening record);
}