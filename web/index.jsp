<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //request.setAttribute("ctx", basePath);
    response.sendRedirect(basePath+"sys/login_toLoginUI.action");
%>
