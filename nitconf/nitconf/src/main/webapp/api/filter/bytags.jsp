
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.nitconf.model.Paper" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filter</title>
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

        /* Styling for buttons and links */
        button, #resetButton{
            background-color: #4CAF50;
            color: white;
            padding: 8px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 5px 0;
        }

        button:hover, #resetButton:hover {
            background-color: #45a049;
        }

        /* Styling for the filter dropdown */
        #tagFilter {
            display: block;
            margin: 0 auto; /* Center align */
            text-align: center; /* Center align */
            margin-bottom: 10px;
        }

        #tagDropdown {
            padding: 8px;
            margin-right: 5px;
        }

        /* Styling to make Reset button same size as Filter button 
        #resetButton {
            background-color: #f44336;
            color: white;
            padding: 8px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            margin: 5px 0;
        }

        #resetButton:hover {
            background-color: #ff6666;
        }*/
    </style>
</head>
<body>
    <%@include file="/api/pcmember/dashboard.jsp"%>
    <h2 style="text-align: center;">Unassigned Papers</h2>

    <div id="tagFilter">
        
        <a href="/api/papers/unassignedpapers" id="resetButton">Reset</a>
        <form id="selecttagForm" style="display: inline-block;">
            <select id="tagDropdown">
                <!-- Populate the dropdown with all available tags from your database -->
                <% List<String> alltags = (List<String>) request.getAttribute("alltags");
                   if(alltags!=null && !alltags.isEmpty()){
                    for (String tag : alltags) {
                %>
                    <option value="<%= tag %>"><%= tag %></option>
                <%
                }
                }
                %>
            </select>
            <button onclick="applyTagFilter()">Apply Filter</button>
            <input type="hidden" name="selectedTag">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
        </form>
    </div>
    
    <table border="1">
        <thead>
            <tr>
                <th>Sl No</th>
                <th>Title</th>
                <th>Tags</th>
                <th>Assign Reviewer</th>
                <th>View PDF</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <% 
            int serialNumber = 1;
            List<Paper> list= (List<Paper>)request.getAttribute("tag_papers");
            if(list!=null && !list.isEmpty()){
                for (Paper data : list) {
            %>
                <tr>
                    <td><%= serialNumber++ %></td>
                    <td><%= data.getTitle() %></td>
                    <td><%= data.getTags() %></td>
                    <td>
                        <a href="/api/filter/reviewertags?paperid=<%=data.getId()%>" style="text-decoration: none;">Assign Reviewer</a>
                    </td>
                    <td><a href="<%= data.getLink() %>" style="text-decoration: none;">View PDF</a></td>
                </tr>
            <%
            }
            }
            %>
        </tbody>
    </table>
<script>
    // Function to show/hide the tag filter dropdown
    function showTagFilter() {
        var tagFilter = document.getElementById("tagFilter");
        var tagDropdown = document.getElementById("tagDropdown");
        var applyFilterButton = document.querySelector('#tagFilter button');

        if (tagFilter.style.display === "none" || tagFilter.style.display === "") {
            tagFilter.style.display = "inline-block";
            tagDropdown.style.display = "inline-block";
            applyFilterButton.style.display = "inline-block";
        } else {
            tagFilter.style.display = "none";
            tagDropdown.style.display = "none";
            applyFilterButton.style.display = "none";
        }
    }

    // Function to apply tag filter
    function applyTagFilter() {
        var selectedTag = document.getElementById("tagDropdown").value;
        var form = document.getElementById('selecttagForm');
        form.action = "/api/filter/bytag";
        form.method = "post";
        form.querySelector('[name="selectedTag"]').value = selectedTag;     
        form.submit();
    }
</script>

</body>
</html>
