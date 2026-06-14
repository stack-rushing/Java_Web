<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>教材选购管理系统 - 登录</title>
    <style>
        body { font-family: '微软雅黑', sans-serif; background-color: #f0f2f5; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .login-box { background: white; padding: 40px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 320px; text-align: center; }
        .login-box h2 { margin-top: 0; color: #333; margin-bottom: 25px; }
        .input-group { margin-bottom: 20px; text-align: left; }
        .input-group label { display: block; margin-bottom: 5px; color: #666; font-size: 14px;}
        .input-group input { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; outline: none; }
        .input-group input:focus { border-color: #1890ff; }
        .btn { width: 100%; padding: 12px; background: #1890ff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; transition: 0.3s; }
        .btn:hover { background: #40a9ff; }
        .error { color: #ff4d4f; margin-bottom: 15px; font-size: 14px; min-height: 20px; }
    </style>
</head>
<body>

<div class="login-box">
    <h2>教材选购管理系统</h2>

    <div class="error">${error}</div>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="input-group">
            <label for="v_name">登录账号：</label>
            <input type="text" id="v_name" name="v_name" placeholder="请输入教务员或教师账号" required autocomplete="off">
        </div>
        <div class="input-group">
            <label for="v_pass">登录密码：</label>
            <input type="password" id="v_pass" name="v_pass" placeholder="请输入密码" required>
        </div>
        <button type="submit" class="btn">立 即 登 录</button>
    </form>
</div>

</body>
</html>