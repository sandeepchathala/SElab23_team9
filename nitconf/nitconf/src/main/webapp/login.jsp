
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
        }
        .error-message {
            color: red;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;          /* backgroung="salaar.png"*/
            text-align: center; /* Center the content within the background-color: #f4f4f4;form */
        }
        a{
            color: blue;
            text-decoration:none;
        }
        a:hover{
            text-decoration: underline;
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
            float: center;
        }
        h4 {
            color: red;
        }
        button:hover {
            background-color: green;
        }
    </style>
</head>
<body>         

<form action="/login" method="post">
    <h2>Welcome to Program Committee Login Page</h2>
    <%
    // Check if the "error" parameter exists in the request URL parameters
    String error = request.getParameter("error");
    if (error != null && error.equals("true")) {
        // Display an error message if the "error" parameter is present
        out.println("<p class=\"error-message\">Invalid email or password. Please try again.</p>");
    }
    %>
    
    <label for="username">Email:</label>
    <input type="text" id="username" name="username" required>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <button type="submit">Login</button>
    
</form>
</body>
</html>
