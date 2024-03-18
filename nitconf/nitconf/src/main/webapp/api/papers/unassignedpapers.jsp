
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.nitconf.model.Paper" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unassigned Papers</title>
    <style>
        /* Styling for the table */
        table {
            width: 80%;
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
        display: block;
        margin: 0 auto;  /* Center-align the button */
    }

     /* Styling for the filter dropdown */
    #tagFilter {
        display: none;
        text-align: center;  /* Center-align the dropdown */
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
        display: block;
        margin: 0 auto;  /* Center-align the button */
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
    <h2>Unassigned Papers</h2>

    <button onclick="showTagFilter()">Filter by Tag</button>
    <form id="selecttagForm">
        <div id="tagFilter">
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
        </div>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    
    <table border="1">
        <thead>
            <tr>
                <th>Sl No</th>
                <th>Title</th>
                <th>Tags</th>
                <th>Uploaded Date</th>
                <th>Assign Reviewer</th>
                <th>View PDF</th>
            </tr>
        </thead>
        <tbody id="tableBody">
            <% 
            int serialNumber = 1;
            List<Paper> papers = (List<Paper>) request.getAttribute("unassigned_papers");
            if(papers!=null && !papers.isEmpty()){
                for (Paper data : papers) {
            %>
                <tr>
                    <td><%= serialNumber++ %></td>
                    <td><%= data.getTitle() %></td>
                    <td><%= data.getTags() %></td>
                    <td><%= data.getUploadeddate() %></td>
                    <td>
                        <a href="/api/filter/reviewertags?paperid=<%=data.getId()%>" class="assign-reviewer-link">Assign Reviewer</a>
                    </td>
                    <td><a href="<%= data.getLink() %>" class="view-pdf-link">View PDF</a></td>
                </tr>
            <%
            }
            }
            %>
        </tbody>
    </table>
    </div>
    <script>
        // Function to show/hide the tag filter dropdown
        function showTagFilter() {
            var tagFilter = document.getElementById("tagFilter");
            if (tagFilter.style.display === "none") {
                tagFilter.style.display = "block";
            } else {
                tagFilter.style.display = "none";
            }
        }
        
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
