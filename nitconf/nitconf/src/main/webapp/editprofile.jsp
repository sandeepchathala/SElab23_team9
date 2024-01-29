<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <style>
        /* Add your styling here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: green;
        }
    </style>
</head>
<body>

<form action="/UpdateProfile" method="POST">
    <h2>Edit Profile</h2>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${name}" required>

    <label for="phonenumber">Phone Number:</label>
    <input type="text" id="phonenumber" name="phonenumber" value="${phonenumber}" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${email}" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${password}" required>
    
    <label for="confirmpassword">Confirm Password:</label>
    <input type="confirmpassword" id="confirmpassword" name="confirmpassword" value="${password}" required>

    <button type="submit">Save Changes</button>
</form>

</body>
</html>
