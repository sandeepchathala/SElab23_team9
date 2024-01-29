<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Reviewed Papers</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            background-color: #3498db;
            color: #ecf0f1;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #2c3e50;
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 12px;
        }

        th {
            background-color: #3498db;
            color: #ecf0f1;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
            color: #2c3e50;
            font-weight: bold;
            transition: color 0.3s ease-in-out;
        }

        a:hover {
            color: #e74c3c;
        }

        .filter-btn, .action-btn, .pdf-btn, .assign-btn {
            background-color: #2ecc71;
            color: #ecf0f1;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px 5px;
            cursor: pointer;
            border-radius: 5px;
        }

        .reject-btn {
            background-color: #e74c3c;
        }

        .add-paper-form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }

        .add-paper-form input {
            margin-bottom: 10px;
            padding: 8px;
            width: 300px;
        }

        .add-paper-form button {
            background-color: #3498db;
            color: #ecf0f1;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2>Reviewed Papers</h2>
    <button class="filter-btn" onclick="filterPapers()">Filter Papers</button>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Author</th>
                <th>Title</th>
                <th>Status</th>
                <th>View Paper</th>
                <th>Accept/Reject</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="paper : ${papers}">
                <td th:text="${paper.id}"></td>
                <td th:text="${paper.author}"></td>
                <td th:text="${paper.title}"></td>
                <td th:text="${paper.status}"></td>
                <td>
                    <a th:href="@{/viewPaper/{id}(id=${paper.id})}" class="pdf-btn">View PDF</a>
                </td>
                <td>
                    <button class="action-btn" onclick="acceptPaper(${paper.id})">Accept</button>
                    <button class="action-btn reject-btn" onclick="rejectPaper(${paper.id})">Reject</button>
                </td>
            </tr>
        </tbody>
    </table>

    <button class="assign-btn" onclick="assignPapers()">Add Reviewer</button>

    <script>
        function filterPapers() {
            // Add filtering logic here
            alert('Filtering papers...');
        }

        function acceptPaper(paperId) {
            // Add logic to handle paper acceptance
            alert('Accepting paper with ID ' + paperId);
        }

        function rejectPaper(paperId) {
            // Add logic to handle paper rejection
            alert('Rejecting paper with ID ' + paperId);
        }

        function assignPapers() {
            // Add logic to assign papers
            alert('Assigning papers...');
        }
    </script>
</body>
</html>