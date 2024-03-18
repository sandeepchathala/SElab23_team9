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
        h4{
            color: red;
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
        <script>
        function validatePhoneNumber() {
            var phoneNumber = document.getElementById("phone").value;
            if (phoneNumber.length !== 10 || isNaN(phoneNumber)) {
                alert("Please enter a valid 10-digit phone number");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<form action="/api/pcmember/Updateprofile" method="POST" onsubmit="return validatePhoneNumber()">
    <h2>Edit Profile</h2>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${name}" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${email}" required>
    
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${phone}" required>
    
    <% if ( session.getAttribute("confirmpassword_error_msg") !=null){%>
    	   <h4><%=session.getAttribute("confirmpassword_error_msg")%></h4>
    	<%}session.setAttribute("confirmpassword_error_msg",null);%>
    	
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${password}" required>
    
    <label for="confirmpassword">Confirm Password:</label>
    <input type="password" id="password" name="confirmpassword" value="${password}" required>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Save Changes</button>
</form>

</body>
</html>
