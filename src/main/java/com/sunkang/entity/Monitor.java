package com.sunkang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Monitor {
    private Integer id;

    private Double cpu;

    private Integer memeryTotal;

    private Integer memeryUsed;

    private Integer memeryFree;

    private Integer memeryBuff;

    private Integer diskUsed;

    private Integer diskTotalspace;

    private Integer inSpeed;

    private Integer outSpeed;

    @JsonFormat(pattern="HH:mm:ss",timezone="GMT-6")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Integer getMemeryTotal() {
        return memeryTotal;
    }

    public void setMemeryTotal(Integer memeryTotal) {
        this.memeryTotal = memeryTotal;
    }

    public Integer getMemeryUsed() {
        return memeryUsed;
    }

    public void setMemeryUsed(Integer memeryUsed) {
        this.memeryUsed = memeryUsed;
    }

    public Integer getMemeryFree() {
        return memeryFree;
    }

    public void setMemeryFree(Integer memeryFree) {
        this.memeryFree = memeryFree;
    }

    public Integer getMemeryBuff() {
        return memeryBuff;
    }

    public void setMemeryBuff(Integer memeryBuff) {
        this.memeryBuff = memeryBuff;
    }

    public Integer getDiskUsed() {
        return diskUsed;
    }

    public void setDiskUsed(Integer diskUsed) {
        this.diskUsed = diskUsed;
    }

    public Integer getDiskTotalspace() {
        return diskTotalspace;
    }

    public void setDiskTotalspace(Integer diskTotalspace) {
        this.diskTotalspace = diskTotalspace;
    }

    public Integer getInSpeed() {
        return inSpeed;
    }

    public void setInSpeed(Integer inSpeed) {
        this.inSpeed = inSpeed;
    }

    public Integer getOutSpeed() {
        return outSpeed;
    }

    public void setOutSpeed(Integer outSpeed) {
        this.outSpeed = outSpeed;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}