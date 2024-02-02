<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" -->

<!DOCTYPE html>
<html>
<head>
    <title>Upload Paper</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h2>Upload Paper</h2>

<form action="/Paper/StorePaper" method="POST" enctype="multipart/form-data">
    <label for="title">title:</label>
    <input type="text" id="title" name="title" required/><br/>

     <label for="tags">tags:</label>
    <input type="text" id="tags" name="tags" required/><br/> 

    <label for="status">status:</label>
    <input type="text" id="status" name="status" required/><br/>

     <label for="link">link:</label>
    <input type="text" id="link" name="link" required/><br/> 

    <button type="submit" >Upload</button>
</form>

</body>
</html>