<%
	System.out.print(request.getParameter("url"));
	response.sendRedirect(request.getParameter("url"));
%>