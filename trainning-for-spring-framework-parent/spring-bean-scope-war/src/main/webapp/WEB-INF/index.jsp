<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
                    pageEncoding="UTF-8"/>
<html>
<head>
    <title>首页</title>
</head>
<body>
\${requestScopeUser}：${requestScopeUser.name}
\${sessionScopeUser}：${sessionScopeUser.name}
\${applicationScopeUser}：${applicationScopeUser.name}
</body>
</html>
