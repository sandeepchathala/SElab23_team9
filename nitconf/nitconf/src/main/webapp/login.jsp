<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            overflow: hidden;
            background: linear-gradient(to right, #4caf50, #2196F3);
            animation: gradientAnimation 15s ease infinite alternate; /* Background Animation */
            position: relative;
        }

        @keyframes gradientAnimation {
            0% {
                background-position: 0% 50%;
            }
            100% {
                background-position: 100% 50%;
            }
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            z-index: 2; /* Ensure the form appears on top of the droplets and bubbles */
            position: relative;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: left;
        }

        button:hover {
            background-color: green;
        }

        a {
            color: blue;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Animated water droplets */
        .droplet {
            position: absolute;
            background-color: rgba(255, 255, 255, 0.8);
            width: 5px;
            height: 5px;
            border-radius: 50%;
            animation: fallAnimation 2s linear infinite;
        }

        @keyframes fallAnimation {
            0% {
                transform: translateY(-10vh);
                opacity: 0;
            }
            100% {
                transform: translateY(100vh);
                opacity: 1;
            }
        }

        /* Animated bubbles */
        .bubble {
            position: absolute;
            background-color: rgba(255, 255, 255, 0.6);
            width: 20px;
            height: 20px;
            border-radius: 50%;
            animation: floatAnimation 3s infinite linear alternate;
        }

        /* Bubbles on top */
        .bubble.top-left { top: 10px; left: 10px; }
        .bubble.top-right { top: 10px; right: 10px; }

        /* Bubbles on bottom */
        .bubble.bottom-left { bottom: 10px; left: 10px; }
        .bubble.bottom-right { bottom: 10px; right: 10px; }

        @keyframes floatAnimation {
            0% {
                transform: translateY(0);
            }
            100% {
                transform: translateY(20px);
            }
        }
    </style>
</head>
<body>

<!-- Add animated water droplets to the background -->
<div class="droplet" style="top: 30px; left: 20px;"></div>
<div class="droplet" style="top: 80px; left: 50px;"></div>
<div class="droplet" style="top: 120px; left: 150px;"></div>
<!-- Add more droplets as needed -->

<!-- Add animated bubbles to the background -->
<div class="bubble top-left"></div>
<div class="bubble top-right"></div>
<div class="bubble bottom-left"></div>
<div class="bubble bottom-right"></div>
<!-- Add more bubbles as needed -->

<form action="/Dashboard" method="POST">
    <h2>Welcome to Program Committee Login Page</h2>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <button type="submit">Login</button>

    <a href="/Forgetpassword">Forget Password?</a>
</form>

</body>
</html>



