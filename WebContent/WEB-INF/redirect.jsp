<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转...</title>
<script type="text/javascript">
	location.href = "http://localhost:8080/music/index?${user.id}&${user.name}";
</script>
</head>
<body>
</body>
</html>