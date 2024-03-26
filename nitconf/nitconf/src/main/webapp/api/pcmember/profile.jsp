
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
      /* .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            margin-top: 20px;
        }*/
    </style>
</head>
<body >
<nav>
    <ul>
        <li><a href="/api/pcmember/Profile">Profile</a></li>
        <li><a href="/api/papers/assignedpapers">Assigned Papers</a></li>
        <li><a href="/api/papers/unassignedpapers">Unassigned Papers</a></li>
        <li><a href="/api/papers/acceptedpapers">Accepted Papers</a></li>
        <li><a href="/api/papers/rejectedpapers">Rejected Papers</a></li>
        <li><a href="/logout">Logout</a></li>
    </ul>
</nav>

<div class="container">
    <h2>User Profile</h2>
<h4>Name         : ${name} </h4>
<h4>Email        : ${email} </h4>
<h4>Phone     : ${phone} </h4>

    <button><a href="/api/pcmember/EditProfile" style="text-decoration:none">Edit Profile</a></button>
</div>

</body>
</html>
