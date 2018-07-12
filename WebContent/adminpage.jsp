<%@ page import="java.sql.Connection" %>
<%@ page import="javas.DbManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>

body{
		font-family: 'Open Sans', sans-serif;
}
.userdiv{
	display:inline-block;
	width: 135px;
	height: 100px;
	border: 1px solid #EAE8E8;
	margin: auto;
}

.usertext{
	display: block;
	color:black;
	font-weight: bold;
	font-size: 15px;
	text-align: center;
}

.bookDetailButtonDiv
{
	width: 100px;
	height: 40px;
	margin-left: 17.5px;
	margin-top: 10px;
	font-weight: bold;
	font-size: 14px;
	text-align: center;
	background-color: #f6f6f6;
	line-height: 40px;
	display: block;
	color:red;
}

a{
	text-decoration: none;
}	


.bookDiv{
	display: inline-block;
	width: 170px;
	height: 360px;
	border: 1px solid #EAE8E8;
	margin-left: 10px;
	margin-top: 10px;
}


.bookDiv:hover{
	background-color: #E1E0E0;
}

.userdiv:hover{
	background-color: #E1E0E0;
}

.bookImgDiv{
	width: 115px;
	height: 170px;
	background-size: 100% 100%;
	margin-left: 27.5px;
	display:block;
	margin-top: 10px;
}

.bookDetailDiv{
	width: 115px;
	height: 50px;
	margin-left:27.5px;
	text-align: center;
	font-size: 15px;
	margin-top: 10px;
	display: block;
}

.bookPriceDiv{
	width: 135px;
	height: 40px;
	margin-left: 17.5px;
	margin-top: 15px;
	font-weight: bold;
	font-size: 17px;
	text-align: center;
	background-color: #f6f6f6;
	line-height: 40px;
	display: block;
}

.bookDeleteButton
{
	width: 135px;
	height: 40px;
	margin-left: 17.5px;
	margin-top: 10px;
	font-weight: bold;
	font-size: 14px;
	text-align: center;
	background-color: #f6f6f6;
	line-height: 40px;
	display: block;
	color:red;
}


form{

	margin: auto;
	height: auto;
	width:400px;
	height: 500px;
	margin-top:10%;
	border-radius: 10px;
	padding:10px;
	border: 1px solid #EAE8E8;
}

.text{
	width: 300px;
	height: 45px;
	margin-left: 50px;
	margin-top: 10px;
	border: 1px solid #EAE8E8;
}

.inputdiv{
	display:block;
	margin-top: 10px;
	margin-left:5px;
}

.buttonclass{
	width: 250px ; 
	height: 50px ; 
	border-radius: 10px ;
	border:0px;
	margin-top:15px;
	background-color: #b0b6c0;
	font-weight: bold;
	display:block;
	margin-left: 75px;
	margin-top:25px;
}

</style>
</head>


<body>
<h1 style="width: 100%;text-align: center">Users</h1>
<%
DbManager db = new DbManager();
Connection conn = db.getConnection();
Statement stmt =null;
String delete = request.getParameter("delete");
if(delete != null)
{
	db.deleteuser(Integer.parseInt(delete));
}

try {
	stmt =conn.createStatement();
	String sql;
	sql ="SELECT * FROM users ";
	ResultSet rs =stmt.executeQuery(sql);//STEP 5: Extract data from result set			
	while(rs.next()){//Retrieve by column name
		int id  =rs.getInt("userid");
		String username =rs.getString("username");
		String password =rs.getString("password");
	%>
	
	<div class="userdiv">
	<div class="usertext"><%= username %></div>
	<div class="usertext"><%= password %></div>
	<a href="adminpage.jsp?delete=<%= id %>" target="_self"><div class="bookDetailButtonDiv">DELETE</div></a>
	</div>
	<%
		
	}	
	rs.close();
	stmt.close();
}
catch (Exception e) {
	e.printStackTrace();
}
%>
<h1 style="width: 100%;text-align: center">Books</h1>
<%

String statu = request.getParameter("statu");
String bookid = request.getParameter("bookid");
if(statu != null)
{
	db.deletebook(Integer.parseInt(bookid));
}

stmt =null;

try {
	stmt =conn.createStatement();
	String sql;
	sql ="SELECT * FROM books";
	ResultSet rs =stmt.executeQuery(sql);//STEP 5: Extract data from result set			
	while(rs.next()){//Retrieve by column name
		int id  =rs.getInt("bookID");
		String detail =rs.getString("detail");
		String image =rs.getString("image");
		int price = rs.getInt("price");
	%>
	
	<div class="bookDiv">
	<div class="bookImgDiv" style="background-image: url(<%= image %>);"></div>
	<div class="bookDetailDiv"><%= detail %></div>
	<a href="adminpage.jsp?statu=delete&bookid=<%= id %>"><div class="bookDeleteButton">DELETE</div></a>
	<div class="bookPriceDiv"><%= price %> PLN</div>
	</div>
	<%
	}	
		rs.close();
		stmt.close();
}
catch (Exception e) {
	e.printStackTrace();
}

%>

<form action="adminpageservlet" method="POST">
<h1 style="width: 100%;text-align: center;font-weight: bold;">Add Book</h1>
<div class="inputdiv">
 <div style="width: 100%;text-align: center;">Book Name</div>
  <input class="text" type="text" name="bookname">
</div>
<div class="inputdiv">
  <div style="width: 100%;text-align: center">Price</div>
  <input class="text" type="text" name="price">
</div>
<div class="inputdiv">
  <div style="width: 100%;text-align: center">Image Url</div>
  <input class="text" type="text" name="imageurl">
</div>
  <input class="buttonclass" type="submit" value="Add Book">
</form>


</body>
</html>