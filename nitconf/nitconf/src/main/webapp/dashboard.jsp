<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
<!-- <h1>WelCome ${name} </h1> -->
<nav>
    <ul>
        <form action="/Profile" method="POST">
             <li><a href="/Profile">Profile</a></li>
        </form>
       
        <li><a href="/reviewed-papers">Reviewed Papers</a></li>
        <li><a href="/unreviewed-papers">Unreviewed Papers</a></li>
    </ul>
</nav>

<!-- Your page content goes here -->


</body>
</html>