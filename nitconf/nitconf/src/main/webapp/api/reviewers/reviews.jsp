<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.nitconf.model.PaperReviewer, com.nitconf.model.Reviewer" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviews</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #28a745;
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
    <h2>Reviews</h2>
    <%String paper_title=(String)request.getAttribute("paper_title"); %>
    <h2>Reviews of paper : <%=paper_title%></h2>
    </div>
    <table>
        <thead>
            <tr>
                <th>Sl No</th>
                <th>Reviewer Name</th>
                <th>Reviewer Email</th>
                <th>Comment</th>
                <th>Rating</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <%
            List<Object[]> data = (List<Object[]>) request.getAttribute("reviews");
            //List<Reviewer> reviewer = (List<Reviewer>) request.getAttribute("reviews_reviewer");
            if(data!=null && !data.isEmpty()){
            int serialno=1;
            for (Object[] obj : data) {
            	PaperReviewer pr=(PaperReviewer)obj[0];
            	Reviewer r=(Reviewer)obj[2];
            %>
            <tr>
                <td><%= serialno++ %></td>
                <td><%= r.getName() %></td>
                <td><%= r.getEmail() %></td>
                <td><%= pr.getComment() %></td>
                <td><%= pr.getRating() %></td>
            </tr>
            <%
            }
            }
            %>
        </tbody>
    </table>
</body>
</html>
