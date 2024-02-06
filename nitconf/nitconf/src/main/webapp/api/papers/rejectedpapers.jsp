<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rejected Papers</title>
    <style>
        /* Styling for the table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        /* Styling for the "Assign Reviewer" link */
        .assign-reviewer-link {
            color: #1E88E5; /* Blue color */
            text-decoration: none;
            cursor: pointer;
        }

        .assign-reviewer-link:hover {
            text-decoration: underline;
        }

        /* Styling for the "View PDF" link */
        .view-pdf-link {
            color: #2196F3; /* Dark Blue color */
            text-decoration: none;
            cursor: pointer;
        }

        .view-pdf-link:hover {
            text-decoration: underline;
        }

        /* Styling for the filter button */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        /* Styling for the filter dropdown */
        #tagFilter {
            display: none;
        }

        #tagDropdown {
            padding: 8px;
            margin-right: 5px;
        }

        /* Styling for the apply filter button */
        #tagFilter button {
            background-color: #1E88E5;
            color: white;
            padding: 8px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Rejected Papers</h2>

    <table border="1">
        <thead>
            <tr>
                <th>Sl No</th>
                <th>Title</th>
                <th>Tags</th>
                <th>Uploaded Date</th>
                <th>View PDF</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <% 
            Connection con1 = null;
            Statement st1 = null;
            ResultSet data = null;
            int serialNumber = 1;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB", "root", "Balu@321");
                st1 = con1.createStatement();
                String str = "SELECT * FROM paper WHERE status =4";
                data = st1.executeQuery(str);
                while (data.next()) {
            %>
                <tr>
                    <td><%= serialNumber++ %></td>
                    <td><%= data.getString("title") %></td>
                    <td><%= data.getString("tags") %></td>
                    <td><%= data.getDate("uploadeddate") %></td>
                    <td><a href="<%= data.getString("link") %>" class="view-pdf-link">View PDF</a></td>
                </tr>
            <% }
            } catch (Exception e) {
            } finally {
                try {
                    if (data != null) data.close();
                    if (st1 != null) st1.close();
                    if (con1 != null) con1.close();
                } catch (Exception e) {
                }
            }
            %>
        </tbody>
    </table>
    
</body>
</html>