
<%
	session.removeAttribute("user");
	session = null;
	response.sendRedirect("admin_login.jsp");
%>