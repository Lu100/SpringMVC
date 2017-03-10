<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<html>
<head>
    <title>This is title</title>
</head>
<script src="${ctx}/resources/static/jquery/jquery-3.1.1.min.js"></script>
<script>
    function send() {
        var data = {};
        data.ids = ["1", "2", "3"];
        $.ajax({
            type: "post",
            url: "${ctx}/accept-array",
            data: data
        })
    }
</script>
<body>
<button onclick="send()"> 发送</button>
</body>
</html>
