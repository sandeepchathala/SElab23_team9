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
        div a{
            color: white;
            text-decoration: none;
        }
        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 8px;
        }

        button:hover {
            background-color: green;
        }

</style>
</head>
<body>
<div>
   <form action="/Login">
     <button type="submit">
         Login as PC Member
     </button>
   </form>
</div>
</body>
</html>