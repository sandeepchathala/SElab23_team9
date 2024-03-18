
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.nitconf.model.PaperReviewer, com.nitconf.model.Reviewer" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Review</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }

        h2 {
            color: #333;
            text-align: center; /* Align the text center */
        }

        .container {
            text-align: center; /* Align the content center */
            margin: auto; /* Center the container */
            width: 80%; /* Adjust the width as needed */
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a.button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            text-align: center;
            text-decoration: none;
            background-color: #008CBA;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<%@include file="/api/pcmember/dashboard.jsp"%>
    <div class="container">
        <h2>Show Review</h2>
        <h2>Reviews of paper : <%=(String)request.getAttribute("paper_title")%></h2>
        <table>
            <thead>
                <tr>
                    <th>Sl No</th>
                    <th>Reviewer Name</th>
                    <th>Comment</th>
                    <th>Rating</th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <%
               List<Object[]> list = (List<Object[]>) request.getAttribute("showreviewlist");
               if(!list.isEmpty() && list!=null){
        		int serialno=1;
                for (Object[] data : list) {
                	Reviewer r = (Reviewer) data[1];
                	PaperReviewer pr = (PaperReviewer) data[0];
                %>
                <tr>
                    <td><%= serialno++ %></td>
                    <td><%= r.getName() %></td>
                    <td><%= pr.getComment() %></td>
                    <td><%= pr.getRating() %></td>
                </tr>
                <%
                }
              }
            %>
            </tbody>
        </table>
        <%Long paper_id = (Long) request.getAttribute("paper_id"); %>
        <a class="button" href="/api/actions/accept?paper_id=<%=paper_id%>">Accept</a>
        <a class="button" href="/api/actions/reject?paper_id=<%=paper_id%>">Reject</a>
    </div>
 
</body>
</html>
