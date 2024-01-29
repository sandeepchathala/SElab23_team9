<!--
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>PC Profile</title>
</head>
<body>
   <div>
    <h1>User Profile</h1>
    <!-- Display user profile information
<h4>Name: ${name} </h4>
<h4>Phone Number: ${phonenumber} </h4>
<h4>Email: ${email} </h4>
<h4>Password: ${password} </h4>
<hr /> <br />

    <a href="/edit-profile">Edit Profile</a>
</div>
  


</body>
</html>
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Add your styling here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        span{
            color: blue;
        }
        nav {
            background-color: #333;
            overflow: hidden;
        }
        div {
            float:  center;
            color:  black;
            overflow: hidden;
        }
        div a{
            color: white;
        }
        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            overflow: hidden;
        }

        button:hover {
            background-color: green;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        nav li {
            float: left;
        }

        nav a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }
    </style>
</head>
<body >
<nav>
    <ul>
        <form action="/Profile" method="POST">
             <li><a href="/Profile">Profile</a></li>
        </form>
       
        <li><a href="/ReviewedPapers">Reviewed Papers</a></li>
        <li><a href="/UnreviewedPapers">Unreviewed Papers</a></li>
    </ul>
</nav>

<!-- Your page content goes here -->
<div>
    <h2>User Profile</h2>
    <!-- Display user profile information -->
<h4>Name         : ${name} </h4>
<h4>Phone Number : ${phonenumber} </h4>
<h4>Email        : ${email} </h4>
<h4>Password     : ${password} </h4>

    <button><a href="/EditProfile" style="text-decoration:none">Edit Profile</a></button>
</div>

</body>
</html>