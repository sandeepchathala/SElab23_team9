
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import=" java.util.List, com.nitconf.model.Reviewer " %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviewers</title>
    <style>
        /* Styling for the table */
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

        /* Styling for buttons and links */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 5px 0;
            text-align: center;
        }

        button:hover {
            background-color: #45a049;
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
    <script>
    function assignReviewers() {
        var selectedReviewers = [];
        var checkboxes = document.getElementsByName('reviewerCheckbox');
        var isReviewerSelected = false; // Flag to track if any reviewer is selected

        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                selectedReviewers.push(checkbox.value);
                isReviewerSelected = true;
            }
        });

        // Check if at least one reviewer is selected
        if (!isReviewerSelected) {
            alert("Please select at least one reviewer.");
            return; // Stop further execution
        }

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
<%@include file="/api/pcmember/dashboard.jsp"%>
    <%String paper_title=(String)request.getAttribute("paper_title"); %>
    <div class="container">
        <h2>Assign Reviewers for paper : <%=paper_title%></h2></div>
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
                    List<Reviewer> list = (List<Reviewer>) request.getAttribute("tag_reviewers");
                    if(list!=null && !list.isEmpty()){
                        int serialno=1;
                        for (Reviewer data : list) {
                    %>
                    <tr>
                        <td><%= serialno++ %></td>
                        <td><%= data.getName() %></td>
                        <td><%= data.getTags() %></td>
                        <td><%= data.getEmail() %></td>
                        <td><input type="checkbox" name="reviewerCheckbox" value="<%= data.getId() %>"></td>
                    </tr>
                    <%
                    }
                    }
                    %>
                </tbody>
            </table>
<div class="container">
            <button type="button" onclick="assignReviewers()">Assign Reviewer</button>
            <input type="hidden" name="selectedReviewers">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                 </div>
        </form>
   
</body>
</html>
