<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.ui.Model, com.nitconf.model.Paper, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accepted Papers</title>
    <style>
        /* Styling for the table */
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
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

        .view-pdf-link:hover ,view-review:hover{
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
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@include file="/api/pcmember/dashboard.jsp"%>
<div class="container">
    <h2>Accepted Papers</h2></div>

    <table border="1">
        <thead>
            <tr>
                <th>Sl No</th>
                <th>Title</th>
                <th>Tags</th>
                <th>Uploaded Date</th>
                <th>View Reviews</th>
                <th>View PDF</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <% int serialNumber=1;
            List<Paper> papers = (List<Paper>) request.getAttribute("accepted_papers");
            if (papers != null && !papers.isEmpty()) {
                for (Paper data: papers) {
            %>
                <tr>
                    <td><%= serialNumber++ %></td>
                    <td><%= data.getTitle() %></td>
                    <td><%= data.getTags() %></td>
                    <td><%= data.getUploadeddate() %></td>
                    <td>
                    <a href="/api/reviewers/reviews?paper_id=<%=data.getId()%>" style="text-decoration: none;" class="view-review">
                    View Reviews
                    </a>
                    </td>
                    <td><a href="<%= data.getLink() %>" class="view-pdf-link">View PDF</a></td>
                </tr>
            <% }
            }
            %>
        </tbody>
    </table>
    
</body>
</html>
