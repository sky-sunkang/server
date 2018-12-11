package com.sunkang.IDao;

import com.sunkang.entity.Monitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface MonitorMapper {
    List<Monitor> selectMonitor();
}