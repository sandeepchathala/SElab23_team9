<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviewed Papers</title>
    <style>
        /* Styling for the table */
        table {
            width: 80%; /* Adjust the width as needed */
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px; /* Adjust the padding as needed */
            text-align: left;
        }

        th {
            background-color: #28a745; /* Green */
            color: white;
        }

        /* Styling for action buttons */
        .action-buttons {
            display: flex;
            gap: 10px; /* Adjust the gap between buttons as needed */
        }

        .notification-button {
            background-color: #28a745; /* Green */
            color: white;
            text-decoration: none;
            padding: 10px; /* Adjust the padding as needed */
            border-radius: 5px; /* Add rounded corners */
            border: none;
        }

        /* Styling for View Reviews button */
        .view-reviews-button {
            background-color: #28a745; /* Green */
            color: white;
            text-decoration: none;
            padding: 8px; /* Adjust the padding as needed */
            border-radius: 5px; /* Add rounded corners */
            border: none;
        }
    </style>
</head>
<body>
    <h2>Reviewed Papers</h2>
    <button class="notification-button"><a href="/api/papers/reviewedpapers" style="text-decoration:none;">Reviewed Papers</a></button>
    <button class="notification-button"><a href="/api/papers/unreviewedpapers" style="text-decoration:none;">Unreviewed Papers</a></button>
    <table border="1">
        <thead>
            <tr>
                <th>Sl No</th>
                <th>Title</th>
                <th>Tags</th>
                <th>View PDF</th>
                <th>View Review</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <!-- Table rows will be inserted here dynamically -->
            <%
            Connection con=null;
            Statement st=null;
            ResultSet data= null;
            int si_no=1;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB","root","Balu@321");
                st = con.createStatement();
                String str = "select * from paper where status = 2";
                data=st.executeQuery(str);
                while(data.next()){
            %>
            <tr>
                <td><%=si_no++%></td>
                <td><%=data.getString("title")%></td>
                <td><%=data.getString("tags")%></td>
                <td><a href="<%=data.getString("link")%>" target="_blank">View PDF</a></td>
                <td> 
                    <button class="view-reviews-button">
                        <a href="/api/reviewers/showreview?paper_id=<%=data.getLong("id")%>" style="text-decoration:none;">
                            View Reviews
                        </a>
                    </button>
                </td>
            </tr>
            <% }
              
            } catch(Exception e){
            
            }
            %>
        </tbody>
    </table>

</body>
</html>