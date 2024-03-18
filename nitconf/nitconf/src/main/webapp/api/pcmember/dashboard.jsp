
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
        <li><a href="/api/pcmember/Profile">Profile</a></li>
        <li><a href="/api/papers/assignedpapers">Assigned Papers</a></li>
        <li><a href="/api/papers/unassignedpapers">Unassigned Papers</a></li>
        <li><a href="/api/papers/acceptedpapers">Accepted Papers</a></li>
        <li><a href="/api/papers/rejectedpapers">Rejected Papers</a></li>
    </ul>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</nav>

<!-- Your page content goes here -->


</body>
</html>
