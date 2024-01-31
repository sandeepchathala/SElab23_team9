<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
      text-align: center;
    }

    h1 {
      background-color: #4CAF50;
      color: #fff;
      padding: 10px;
      margin: 0;
    }

    table {
      border-collapse: collapse;
      width: 80%;
      margin: 20px auto;
      background-color: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 10px;
    }

    th {
      background-color: #4CAF50;
      color: #fff;
    }

    button {
      padding: 8px;
      background-color: #4CAF50;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
  </style>
  <title>Stylish Table</title>
</head>
<body>

  <h1>UNREVIEWED PAPERS</h1>
  <table>
    <thead>
      <tr>
        <th>SI NO</th>
        <th>AUTHOR NAME</th>
        <th>TAGS</th>
        <th>VIEW PAGE</th>
        <th>Assignment</th>
      </tr>
    </thead>
    <tbody>
      <!-- Add your table rows here -->
      <tr>
        <td>1</td>
        <td>John Doe</td>
        <td>Technology</td>
        <td><button onclick="viewPageFunction()">View</button></td>
        <td><button onclick="assignmentFunction()">Assign</button></td>
      </tr>
      <!-- Add more rows as needed -->
    </tbody>
  </table>

  <script>
    function viewPageFunction() {
      // Add logic for viewing page
      alert('View Page clicked!');
    }

    function assignmentFunction() {
      // Add logic for assignment
      alert('Assignment clicked!');
    }
  </script>

</body>
</html>
    