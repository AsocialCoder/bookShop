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
<style>

body{
	margin: 0;
	font-family: 'Open Sans', sans-serif;

}
.menubar{
width: 100%;
height: 70px;
background-color: #EAE8E8;
}

.basketDiv
{
background-image: url('img/basket.png'); 
width: 30px;
height: 30px;
margin-top:20px;
background-size: 100% 100%;
float:right;
margin-right: 20px
}

.leftMenu{
	background-color:;
	width: 14.5%;
	height: 100%;
	float:left;
	border-right: 1px solid #B4B2B2;


}

.bookCategoryText{
	font-weight: bold;
	font-size: 17px;
	background-color: #006666;
	width: 100%;
	height: 50px;
	text-align: center;
	line-height: 45px;
	display: block;
	margin-top: 10px
}

.userNameDiv{
	float:right;
	text-align: center;
	margin-top: 25px;
	margin-right: 30px;
	font-size: 20px;

}

.booksContiner{
	float:left;
	width: 100%;
	height: 100%;
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

.bookDetailButtonDiv
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
	color:black;
}

a{
	text-decoration: none;
}

.logodiv
{
	font-weight: bold;
	font-size: 30px;
	margin-top:10px;
	float: left;
	margin-left: 50px;
	background-image: url('img/logo.png');
	background-size: 100% 100%;
	width: 80px;
	height: 55px;
}

.search{
	width: 900px;
	height: 48px;
	background-color: white;
	border:0px;
	margin-top: 10px;
	float: left;
	
}

.searchicon{
	width: 50px;
	height: 50px;
	background-color: white;
	background-image: url('img/searchicon.png');
	background-size: 100% 100%;
	float:left;
	margin-top:10px;
	border-right: 1px solid #EAE8E8;
}

.searchtyping{
	
	background-color: white;
	width: 110px;
	height: 50px;
	float: left;
	margin-top: 10px;
	border-left: 1px solid #EAE8E8;
	text-align: center;
}

.searchtyping p {

	font-size: 18px;
	font-weight: bold;
	font-family: 'Open Sans', sans-serif;
	margin-top: 17px
	
}

.menuimgbtn{
	
	width: 85px;
	height: 30px;
	background-image: url('img/menuimg.png');
	background-size: 100% 100%;
	margin-top: 10px;
	margin-left: 17px;

}

.menuimgdiv{
	float:left;
	width:130px;
	height: 50px ;
	border: 1px solid #B4B4B4;
	margin-left: 100px;
	margin-top: 10px;
}

.adsenseimg{
	width: 1200px;
	height: 700px;
	margin:auto;
	background-image: url('img/adsensepng.png');
	background-size: 100% 100%;
}


</style>
</head>

<body>

<% 
String statu="";
String basket;
	if (session.getAttribute("username")==null)
	{
		statu="Sign in";
		basket="";
	}	
	else
	{
		statu=session.getAttribute("username").toString();
		basket=session.getAttribute("basket").toString();
	}
		
	%>
  
<div class="menubar">
<a href="index.jsp" target="_self"><div class="logodiv"></div></a>
<a ><div class="menuimgdiv"><div class="menuimgbtn"></div></div></a>
<div style="width: 1065px;float:left;margin-left: 20px">
<div class="searchicon"></div>
<input type="text" class="search"/>
<div class="searchtyping"><p>SEARCH<p></div>
</div>
<div class="userNameDiv" style="margin-right:100px"><%= basket %></div>
<a href="basket.jsp" target="_self"><div class="basketDiv"></div></a>
<a href="logout" target="_self" style="color: black"><div class="userNameDiv"><%= statu %></div></a>
</div>
<!--
<div style="width: 100%;height: 100%;">
<div class="leftMenu">
<div class="bookCategoryText" style="margin-top: 1px">Software</div>
<div class="bookCategoryText">Action</div>
<div class="bookCategoryText">Science</div>
<div class="bookCategoryText">Literature</div>
<div class="bookCategoryText">Comic book</div>	
</div>
-->

<div class="booksContiner">
<div class="adsenseimg"></div>
<div style="margin:auto;width: 1200px">

<%
DbManager db = new DbManager();
Connection conn = db.getConnection();

Statement stmt =null;

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
	<a href="https://www.facebook.com"><div class="bookDetailButtonDiv">Detail</div></a>
	<a href="index?statu=buy&bookid=<%= id %>" target="_self" style="color: black"><div class="bookPriceDiv"><%= price %> PLN</div></a>
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

</div>
</div>


</body>
</html>