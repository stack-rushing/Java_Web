<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入 Jakarta 版本的 JSTL 核心标签库 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>教材选购管理系统 - 首页</title>
    <style>
        body { font-family: '微软雅黑', sans-serif; margin: 0; padding: 0; background-color: #f4f5f7; }
        .header { background-color: #1890ff; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
        .header h2 { margin: 0; font-size: 22px; }
        .header .user-info { font-size: 15px; }
        .header a { color: white; text-decoration: none; margin-left: 20px; border: 1px solid white; padding: 6px 12px; border-radius: 4px; transition: 0.3s;}
        .header a:hover { background-color: white; color: #1890ff; }
        .container { display: flex; min-height: calc(100vh - 62px); }
        .sidebar { width: 240px; background-color: #fff; border-right: 1px solid #e8e8e8; box-shadow: 2px 0 8px rgba(0,0,0,0.05); }
        .sidebar ul { list-style: none; padding: 0; margin: 0; }
        .sidebar li { border-bottom: 1px solid #f0f0f0; }
        .sidebar a { text-decoration: none; color: #333; display: block; padding: 16px 24px; transition: 0.2s; }
        .sidebar a:hover { background-color: #e6f7ff; color: #1890ff; border-right: 3px solid #1890ff; }
        .main-content { flex: 1; padding: 30px; }
        .welcome-card { background: white; padding: 50px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.05); text-align: center; margin-top: 50px;}
        .welcome-card h1 { color: #333; }
        .welcome-card p { color: #666; font-size: 16px; }
    </style>
</head>
<body>

<div class="header">
    <h2>📚 教材选购管理系统</h2>
    <div class="user-info">
        欢迎您，
        <c:choose>
            <c:when test="${sessionScope.loginUser.role == '0'}">
                <strong>【教务管理员】</strong>
            </c:when>
            <c:otherwise>
                <strong>【教师】</strong>
            </c:otherwise>
        </c:choose>
        ${sessionScope.loginUser.v_name}！

        <a href="${pageContext.request.contextPath}/logout">注销登录</a>
    </div>
</div>

<div class="container">
    <div class="sidebar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/bookList">📖 教材信息查询</a></li>
            <li><a href="${pageContext.request.contextPath}/updatePwd.jsp">🔑 修改个人密码</a></li>

            <c:if test="${sessionScope.loginUser.role == '0'}">
                <li><a href="${pageContext.request.contextPath}/teacherList">👨‍🏫 教师信息管理</a></li>
                <li><a href="${pageContext.request.contextPath}/addAdmin.jsp">🛡️ 添加教务管理员</a></li>
            </c:if>
        </ul>
    </div>

    <div class="main-content">
        <div class="welcome-card">
            <h1>欢迎使用教材选购管理系统</h1>
            <p>请点击左侧菜单进行相关业务操作。</p>
        </div>
    </div>
</div>

</body>
</html>