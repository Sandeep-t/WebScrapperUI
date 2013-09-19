<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>WebScrapper</title>
</head>



<body>
	<h2>Search Data</h2>
	<form:form method="post"  name="myForm" action="searchForData.html"	>

		<table>
			<%-- 	<tr>
				<td><form:radiobutton path="startWebScrapping"
						value="doWebScraping" /> Webscrapping</td>
				<td><form:radiobutton path="startWebScrapping"
						value="doSearching" /> Start Searching</td>
			</tr>
 --%>

			<tr>
				<td><form:label path="webScrapUrl" id="webScrapUrl">Enter URL for web Scrapping</form:label></td>
				<td><form:input path="webScrapUrl" /></td>
			</tr>
			<tr>
				<td><form:label path="searchString" id="searchString">Enter String to search</form:label></td>
				<td><form:input path="searchString" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value=" Search " /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>