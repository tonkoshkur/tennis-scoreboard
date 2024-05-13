<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        <%@ include file="/css/common.css" %>
    </style>
</head>
<body>
<div class="content vertical-center horizontal-center">
    <div class="flex-centered">
        <button onclick="location.href='${pageContext.request.contextPath}/'">Home</button>
        <button onclick="history.back()">Back</button>
    </div>

    <h2>Error</h2>
    <div>${error}</div>
</div>
</body>
</html>
