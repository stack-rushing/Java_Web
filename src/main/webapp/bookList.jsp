<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>教材选购管理 - 列表</title>
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

        .search-bar { background: white; padding: 15px; border-radius: 6px; margin-bottom: 20px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
        .search-bar input[type="text"] { width: 300px; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        .search-bar button { padding: 8px 15px; background: #1890ff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .table-card { background: white; padding: 20px; border-radius: 6px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid #f0f0f0; padding: 12px; text-align: left; }
        th { background-color: #fafafa; color: #333; }

        .page-bar { display: flex; justify-content: center; align-items: center; gap: 10px; margin-top: 20px; }
        .page-bar a, .page-bar span { padding: 6px 12px; border: 1px solid #d9d9d9; border-radius: 4px; text-decoration: none; color: #333; }
        .page-bar a:hover { border-color: #1890ff; color: #1890ff; }
        .page-bar .current { background: #1890ff; color: white; border-color: #1890ff; }
        .page-bar .disabled { color: #bfbfbf; background: #f5f5f5; cursor: not-allowed; }
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
            <c:if test="${sessionScope.loginUser.role == '0'}">
                <li><a href="${pageContext.request.contextPath}/teacherList">👨‍🏫 教师信息管理</a></li>
                <li><a href="${pageContext.request.contextPath}/addAdmin.jsp">🛡️ 添加教务管理员</a></li>
            </c:if>
        </ul>
    </div>

    <div class="main-content">
        <div class="search-bar">
            <form action="${pageContext.request.contextPath}/bookList" method="get" style="display: inline-block;">
                <input type="text" name="keyword" value="${keyword}" placeholder="按教材名/作者/出版社/ISBN模糊查询...">
                <button type="submit">搜索教材</button>
            </form>
            <a href="${pageContext.request.contextPath}/addBook.jsp" style="float: right; background: #52c41a; color: white; padding: 8px 15px; text-decoration: none; border-radius: 4px;">+ 新增教材</a>
        </div>

        <div class="table-card">
            <h3>教材信息列表</h3>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>教材名称</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>价格</th>
                    <th>ISBN号</th>
                    <th>教材类型</th>
                    <th>库存</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty pb.list}">
                        <tr><td colspan="9" style="text-align: center; color: #999;">暂无教材数据，请先去数据库添加几条测试数据。</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${pb.list}" var="book">
                            <tr>
                                <td>${book.id}</td>
                                <td><strong>${book.tname}</strong></td>
                                <td>${book.tauthor}</td>
                                <td>${book.press}</td>
                                <td>￥${book.price}</td>
                                <td>${book.isbn}</td>
                                <td>${book.type}</td>
                                <td>${book.count}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/getBook?id=${book.id}" style="color: #1890ff; text-decoration: none; margin-right: 10px;">✏️修改</a>
                                    <a href="javascript:if(confirm('确定要删除《${book.tname}》吗？')) location.href='${pageContext.request.contextPath}/deleteBook?id=${book.id}'" style="color: #ff4d4f; text-decoration: none; border: none; padding: 0;">🗑️删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>

            <c:if test="${not empty pb.list}">
                <div class="page-bar">
                    <span>共 ${pb.totalCount} 条记录，每页 ${pb.pageSize} 条，共 ${pb.totalPage} 页</span>

                    <c:choose>
                        <c:when test="${pb.pageNo > 1}">
                            <a href="${pageContext.request.contextPath}/bookList?pageNo=${pb.pageNo - 1}&keyword=${keyword}">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <span class="disabled">上一页</span>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:choose>
                            <c:when test="${pb.pageNo == i}">
                                <span class="current">${i}</span>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/bookList?pageNo=${i}&keyword=${keyword}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${pb.pageNo < pb.totalPage}">
                            <a href="${pageContext.request.contextPath}/bookList?pageNo=${pb.pageNo + 1}&keyword=${keyword}">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <span class="disabled">下一页</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>