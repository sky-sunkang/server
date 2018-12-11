package com.sunkang.common;

/**
 * 返回结果
 */
public class Result<T> {

    public Integer code;//代码

    public String codeDec;//代码详情

    public T resultData;//数据

    public Result() {
    }

    public Result(Integer code, T resultData) {
        this.code = code;
        this.resultData = resultData;
    }

    public Result(Integer code, String codeDec, T resultData) {
        this.code = code;
        this.codeDec = codeDec;
        this.resultData = resultData;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeDec() {
        return codeDec;
    }

    public void setCodeDec(String codeDec) {
        this.codeDec = codeDec;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
