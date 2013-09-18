<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>WebScrapped Data Search Results</title>
</head>
<body>
	<table>
		<%-- <c:forEach var="jsonData" items="${searchResultJson}">
<tr><td> --%>
		<c:out value="${searchResultJson}" />
		<%-- </td>
 </tr>
</c:forEach>	 --%>
	</table>
</body>
</html>