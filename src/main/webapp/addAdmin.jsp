<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加教务管理员</title>
    <style>
        body { font-family: '微软雅黑', sans-serif; margin: 0; padding: 0; background-color: #f4f5f7; }
        .header { background-color: #1890ff; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
        .container { display: flex; min-height: calc(100vh - 62px); }
        .sidebar { width: 240px; background-color: #fff; border-right: 1px solid #e8e8e8; }
        .sidebar ul { list-style: none; padding: 0; margin: 0; }
        .sidebar a { text-decoration: none; color: #333; display: block; padding: 16px 24px; }
        .sidebar a:hover, .sidebar .active { background-color: #e6f7ff; color: #1890ff; border-right: 3px solid #1890ff; }
        .main-content { flex: 1; padding: 30px; }

        .form-card { background: white; padding: 30px; border-radius: 6px; max-width: 500px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; color: #333; }
        .form-group input { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        .btn-submit { background: #1890ff; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px;}
        .msg { color: #52c41a; margin-bottom: 15px; font-weight: bold;}
        .error { color: #ff4d4f; margin-bottom: 15px; font-weight: bold;}
    </style>
</head>
<body>

<div class="header">
    <h2 style="margin: 0; font-size: 22px;">📚 教材选购管理系统</h2>
    <div>欢迎您，${sessionScope.loginUser.v_name}！ <a href="${pageContext.request.contextPath}/logout" style="color: white; border: 1px solid white; padding: 6px 12px; text-decoration: none; border-radius: 4px; margin-left: 10px;">注销</a></div>
</div>

<div class="container">
    <div class="sidebar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/bookList">📖 教材信息查询</a></li>
            <li><a href="${pageContext.request.contextPath}/updatePwd.jsp">🔑 修改个人密码</a></li>
            <c:if test="${sessionScope.loginUser.role == '0'}">
                <li><a href="${pageContext.request.contextPath}/teacherList">👨‍🏫 教师信息管理</a></li>
                <li><a href="${pageContext.request.contextPath}/addAdmin.jsp" class="active">🛡️ 添加教务管理员</a></li>
            </c:if>
        </ul>
    </div>

    <div class="main-content">
        <div class="form-card">
            <h3>添加教务管理员</h3>

            <c:if test="${not empty msg}"><div class="msg">${msg}</div></c:if>
            <c:if test="${not empty error}"><div class="error">${error}</div></c:if>

            <form action="${pageContext.request.contextPath}/addAdmin" method="post">
                <div class="form-group">
                    <label>新管理员账号：</label>
                    <input type="text" name="v_name" required placeholder="请输入新账号">
                </div>
                <div class="form-group">
                    <label>初始密码：</label>
                    <input type="password" name="v_pass" required placeholder="请输入初始密码">
                </div>
                <button type="submit" class="btn-submit">确认添加</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>