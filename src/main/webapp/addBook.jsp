<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>添加教材</title>
  <style>
    body { font-family: '微软雅黑', sans-serif; margin: 0; padding: 0; background-color: #f4f5f7; }
    .header { background-color: #1890ff; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
    .header h2 { margin: 0; font-size: 22px; }
    .header a { color: white; text-decoration: none; border: 1px solid white; padding: 6px 12px; border-radius: 4px; }
    .container { display: flex; min-height: calc(100vh - 62px); }
    .sidebar { width: 240px; background-color: #fff; border-right: 1px solid #e8e8e8; }
    .sidebar ul { list-style: none; padding: 0; margin: 0; }
    .sidebar a { text-decoration: none; color: #333; display: block; padding: 16px 24px; }
    .sidebar a:hover, .sidebar .active { background-color: #e6f7ff; color: #1890ff; border-right: 3px solid #1890ff; }
    .main-content { flex: 1; padding: 30px; }

    .form-card { background: white; padding: 30px; border-radius: 6px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); max-width: 600px; }
    .form-group { margin-bottom: 15px; display: flex; align-items: center;}
    .form-group label { width: 100px; color: #333; }
    .form-group input, .form-group select { flex: 1; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
    .btn-group { margin-top: 20px; text-align: center; }
    .btn-submit { background: #1890ff; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-right: 10px;}
    .btn-cancel { background: #ccc; color: #333; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none;}
  </style>
</head>
<body>

<div class="header">
  <h2>📚 教材选购管理系统</h2>
  <div>欢迎您，${sessionScope.loginUser.v_name}！ <a href="${pageContext.request.contextPath}/logout">注销</a></div>
</div>

<div class="container">
  <div class="sidebar">
    <ul>
      <li><a href="${pageContext.request.contextPath}/bookList" class="active">📖 教材信息查询</a></li>
      <li><a href="${pageContext.request.contextPath}/updatePwd.jsp">🔑 修改个人密码</a></li>
    </ul>
  </div>

  <div class="main-content">
    <div class="form-card">
      <h3>添加新教材</h3>
      <form action="${pageContext.request.contextPath}/addBook" method="post">
        <div class="form-group"><label>教材名称：</label><input type="text" name="tname" required></div>
        <div class="form-group"><label>作者：</label><input type="text" name="tauthor" required></div>
        <div class="form-group"><label>出版社：</label><input type="text" name="press" required></div>
        <div class="form-group"><label>ISBN号：</label><input type="text" name="isbn" required></div>
        <div class="form-group"><label>价格 (元)：</label><input type="number" step="0.01" name="price" required></div>
        <div class="form-group"><label>库存数量：</label><input type="number" name="count" required></div>
        <div class="form-group">
          <label>教材类型：</label>
          <select name="type">
            <option value="普通教材">普通教材</option>
            <option value="十三五规划教材">十三五规划教材</option>
            <option value="十四五规划教材">十四五规划教材</option>
            <option value="国家级精品教材">国家级精品教材</option>
            <option value="前沿新工科教材">前沿新工科教材</option>
          </select>
        </div>
        <div class="form-group"><label>教材描述：</label><input type="text" name="tdescript"></div>

        <div class="btn-group">
          <button type="submit" class="btn-submit">确认添加</button>
          <a href="${pageContext.request.contextPath}/bookList" class="btn-cancel">返回列表</a>
        </div>
      </form>
    </div>
  </div>
</div>

</body>
</html>