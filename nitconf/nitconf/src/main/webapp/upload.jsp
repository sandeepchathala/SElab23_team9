<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" -->

<!DOCTYPE html>
<html>
<head>
    <title>Upload Paper</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e0e0e0;
            margin: 20px;
            overflow: hidden;
        }

        h2 {
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            transition: box-shadow 0.3s;
        }

        form:hover {
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        input {
            width: calc(100% - 16px);
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s;
        }

        input:focus {
            border-color: #4caf50;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }

        button:active {
            transform: scale(0.95);
        }

        .bubble {
            position: absolute;
            background-color: #4caf50;
            width: 20px;
            height: 20px;
            border-radius: 50%;
            opacity: 0.6;
            animation: floatBubble 5s infinite;
        }

        @keyframes floatBubble {
            0% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-50px);
            }
            100% {
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>

<h2>Upload Paper</h2>

<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<!-- Add more bubbles as needed -->

<form action="/Paper/StorePaper" method="POST" enctype="multipart/form-data">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required/>

    <label for="tags">Tags:</label>
    <input type="text" id="tags" name="tags" required/>

    <label for="status">Status:</label>
    <input type="text" id="status" name="status" required/>

    <label for="link">Link:</label>
    <input type="text" id="link" name="link" required/>

    <button type="submit">Upload</button>
</form>

</body>
</html>
