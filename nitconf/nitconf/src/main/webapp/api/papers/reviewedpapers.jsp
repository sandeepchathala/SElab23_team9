<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.tester.demo.model.Paper" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviewed Papers</title>
    <style>
        /* Styling for the container */
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            margin-top: 20px;
        }

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
        a {
        color: #ffffff;
        text-decoration: none;/* Blue /* Remove underline */
         }
    </style>
</head>
<body>
<%@include file="/api/pcmember/dashboard.jsp"%>
    <div class="container">
        <h2>Reviewed Papers</h2>
        <div class="action-buttons">
            <button class="notification-button"><a href="/api/papers/reviewedpapers" style="text-decoration:none;">Reviewed Papers</a></button>
            <button class="notification-button"><a href="/api/papers/unreviewedpapers" style="text-decoration:none;">Unreviewed Papers</a></button>
        </div>
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
                int si_no=1;
                List<Paper> papers = (List<Paper>) request.getAttribute("reviewed_papers");
                if(papers != null && !papers.isEmpty()){
                    for ( Paper data : papers){
                %>
                <tr>
                    <td><%=si_no++%></td>
                    <td><%=data.getTitle()%></td>
                    <td><%=data.getTags()%></td>
                    <td><a href="<%=data.getLink() %>" target="_blank" style="color: #007bff;">View PDF</a></td>
                    <td> 
                        <button class="view-reviews-button">
                            <a href="/api/reviewers/showreview?paper_id=<%=data.getId() %>" style="text-decoration:none;">
                                View Reviews
                            </a>
                        </button>
                    </td>
                </tr>
                <% }
                }
                %>
            </tbody>
        </table>
    </div>

</body>
</html>
