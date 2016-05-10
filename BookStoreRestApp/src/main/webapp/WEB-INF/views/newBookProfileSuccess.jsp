<%@ include file="./include.jsp"%>
<html>
<head>
<title>New Book Record Created</title>
</head>
<body>

<h2>A new book record has been successfully created for:
      <font color="blue"/>${book.bookTitle}${" with isbn "}${book.isbn} </font></h2>
<br>
<h2>
<a href="${context}">Home</a>
</h2>
      
</body>
</html>
