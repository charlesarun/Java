<%@ include file="./include.jsp"%>
<html>
<head>
	<title>New Book Information</title>
	<style>
		.error {
		    font-size: 0.8em;
			color: #ff0000;
		}
	</style>
</head>
<body>

<h1>Book Data form</h1>

  <form:form action="./processNewBookProfile" method="POST" commandName="book">
  <table>
  	<tr>
  	<td><form:label path="publisherId">publisherId</form:label></td>
        <td><form:input path="publisherId" />
            <form:errors path="publisherId" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><form:label path="isbn">isbn</form:label></td>
        <td><form:input path="isbn" />
            <form:errors path="isbn" cssClass="error"/>
        </td>
    </tr>
    <tr>
    <td><form:label path="bookTitle">bookTitle</form:label></td>
        <td><form:input path="bookTitle" />
            <form:errors path="bookTitle" cssClass="error"/>
        </td>
    </tr>
    <tr>
    <td><form:label path="price">price</form:label></td>
    <td><form:input path="price" />
            <form:errors path="price" cssClass="error"/>
        </td>
	</tr>
	<tr>
    <td><input type="submit" value="enterBtn" /> </td>
    </tr>
    </table>
  </form:form>
 
</body>
</html>
