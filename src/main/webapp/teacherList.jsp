<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>教师信息管理</title>
  <style>
    body { font-family: '微软雅黑', sans-serif; margin: 0; padding: 0; background-color: #f4f5f7; }
    .header { background-color: #1890ff; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .container { display: flex; min-height: calc(100vh - 62px); }
    .sidebar { width: 240px; background-color: #fff; border-right: 1px solid #e8e8e8; }
    .sidebar ul { list-style: none; padding: 0; margin: 0; }
    .sidebar a { text-decoration: none; color: #333; display: block; padding: 16px 24px; }
    .sidebar a:hover, .sidebar .active { background-color: #e6f7ff; color: #1890ff; border-right: 3px solid #1890ff; }
    .main-content { flex: 1; padding: 30px; }

    .table-card { background: white; padding: 20px; border-radius: 6px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { border: 1px solid #f0f0f0; padding: 12px; text-align: left; }
    th { background-color: #fafafa; color: #333; }
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
        <li><a href="${pageContext.request.contextPath}/teacherList" class="active">👨‍🏫 教师信息管理</a></li>
        <li><a href="${pageContext.request.contextPath}/addAdmin.jsp">🛡️ 添加教务管理员</a></li>
      </c:if>
    </ul>
  </div>

  <div class="main-content">
    <div class="table-card">
      <h3 style="margin-top: 0;">👨‍🏫 教师信息列表</h3>
      <table>
        <thead>
        <tr>
          <th>用户 ID</th>
          <th>教师账号 / 姓名</th>
          <th>系统角色</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
          <c:when test="${empty teacherList}">
            <tr><td colspan="3" style="text-align: center; color: #999;">系统内暂无教师数据。</td></tr>
          </c:when>
          <c:otherwise>
            <c:forEach items="${teacherList}" var="teacher">
              <tr>
                <td>${teacher.id}</td>
                <td><strong>${teacher.v_name}</strong></td>
                <td><span style="background: #e6f7ff; color: #1890ff; padding: 4px 8px; border-radius: 4px; font-size: 12px;">教师</span></td>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
        </tbody>
      </table>
    </div>
  </div>
</div>

</body>
</html>