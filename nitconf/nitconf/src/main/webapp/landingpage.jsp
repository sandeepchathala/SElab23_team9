<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NIT-CONF Landing Page</title>
<style>
    body {
            font-family: Arial, sans-serif;
            background-image:url('./images/landingpage.png'); /* Adjust the path based on your actual image file name and location */
            background-size:cover;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        h2 {
            color: #333;
        }
        div {
            float:  center;
            color:  black;
            overflow: hidden;
        }
        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right:40px;
            align-items: left;
            justify-content: center;
            width:180px;
            margin-top:200px;
        }
        .forms-container {
            display: flex;
            justify-content: space-between;
         
            align-items: center;
            
        }

        .form {
            margin-right: 10px;
           
        }

        button:hover {
            background-color: green;
        }

</style>
</head>
<body>
<div class="forms-container">
        <form class="form" action="/Login">
            <button type="submit" class="bt1">
                Login as PC Member
            </button>
        </form>
        <form class="form" action="/Register">
            <button type="submit">
                Register as PC Member
            </button>
        </form>
</div>
</body>
</html>