package com.xiyuanli.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {

    public void createWaterSheel(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void createPropertySheel(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
