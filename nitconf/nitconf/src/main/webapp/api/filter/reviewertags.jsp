<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.PreparedStatement, java.sql.ResultSet" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviewers</title>
    <style>
        /* Styling for the table */
        table {
            width: 80%; /* Adjusted table size */
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px; /* Adjusted padding for table cells */
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        /* Styling for buttons and links */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px; /* Adjusted padding for buttons */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 5px 0;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function assignReviewers() {
            var selectedReviewers = [];
            var checkboxes = document.getElementsByName('reviewerCheckbox');

            // Convert NodeList to an array
            checkboxes = Array.from(checkboxes);

            checkboxes.forEach(function (checkbox) {
                if (checkbox.checked) {
                    selectedReviewers.push(checkbox.value);
                }
            });

            var form = document.getElementById('assignForm');
            form.action = "/api/reviewers/assign";
            form.method = "post";
            form.querySelector('[name="selectedReviewers"]').value = selectedReviewers.join(',');

            var paperIdInput = document.createElement('input');
            paperIdInput.type = 'hidden';
            paperIdInput.name = 'paper_id';
            paperIdInput.value = '<%=(Long)request.getAttribute("paper_id")%>'; // Accessing JSP variable
            form.appendChild(paperIdInput);

            form.submit();
        }
    </script>
</head>
<body>
    <h2>Table Display</h2>
    <%String paper_title=(String)request.getAttribute("paper_title"); %>
    <h2>Assign for paper title: <%=paper_title%></h2>
    <form id="assignForm">
        <table border="1">
            <thead>
                <tr>
                    <th>Sl No</th>
                    <th>Name</th>
                    <th>Tags</th>
                    <th>Email</th>
                    <th>Select</th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <%
                // Your existing code for retrieving data from the database
                String paper_tag=(String)request.getAttribute("paper_tag");
                Connection con=null;
                Statement st=null;
                ResultSet data= null;
                long serialno=1;
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB","root","Balu@321");
                    st = con.createStatement();
                    String str = "select * from reviewer where tags =?";
                    PreparedStatement pstmt = con.prepareStatement(str);
                    pstmt.setString(1,paper_tag);
                    data=pstmt.executeQuery();
                    while (data.next()) {
                %>
                <tr>
                    <td><%= serialno++ %></td>
                    <td><%= data.getString("name") %></td>
                    <td><%= data.getString("tags") %></td>
                    <td><%= data.getString("email") %></td>
                    <td><input type="checkbox" name="reviewerCheckbox" value="<%= data.getLong("id") %>"></td>
                </tr>
                <%
                }
                } catch(Exception e){}
                finally {
                    try {
                        if (data != null) data.close();
                        if (st != null) st.close();
                        if (con != null) con.close();
                    } catch (Exception e) {
                    }
                }
                %>
            </tbody>
        </table>

        <button type="button" onclick="assignReviewers()">Assign Reviewer</button>
        <input type="hidden" name="selectedReviewers">
    </form>
</body>
</html>