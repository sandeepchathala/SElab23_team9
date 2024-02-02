<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Error</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #ececec; /* Light gray background color */
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            animation: fadeIn 1s ease-out; /* Fade-in animation */
        }

        h3 {
            color: #ff5252;
            font-size: 24px;
            text-align: center;
            padding: 20px;
            border: 2px solid #ff5252;
            border-radius: 8px;
            background-color: #fff; /* White background color */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            animation: shake 0.5s ease infinite alternate; /* Shake animation */
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        @keyframes shake {
            0% {
                transform: translateX(0);
            }
            100% {
                transform: translateX(-5px);
            }
        }
    </style>
</head>
<body>
    <h3>!! Entered credentials are not matching !!</h3>
</body>
</html>
    