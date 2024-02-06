<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unassigned Papers</title>
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
    <h2>Unassigned Papers</h2>

    <button onclick="showTagFilter()">Filter by Tag</button>
    <form id="selecttagForm">
        <div id="tagFilter">
            <select id="tagDropdown">
                <!-- Populate the dropdown with all available tags from your database -->
                <% 
                Connection con = null;
                Statement st = null;
                ResultSet tags = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB", "root", "Balu@321");
                    st = con.createStatement();
                    String tagQuery = "SELECT DISTINCT tags FROM paper";
                    tags = st.executeQuery(tagQuery);
                    while (tags.next()) {
                %>
                    <option value="<%= tags.getString("tags") %>"><%= tags.getString("tags") %></option>
                <% }
                } catch (Exception e) {
                } finally {
                    try {
                        if (tags != null) tags.close();
                        if (st != null) st.close();
                        if (con != null) con.close();
                    } catch (Exception e) {
                    }
                }
                %>
            </select>
            <button onclick="applyTagFilter()">Apply Filter</button>
            <input type="hidden" name="selectedTag">
        </div>
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
            Connection con1 = null;
            Statement st1 = null;
            ResultSet data = null;
            int serialNumber = 1;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB", "root", "Balu@321");
                st1 = con1.createStatement();
                String str = "SELECT * FROM paper WHERE status =0";
                data = st1.executeQuery(str);
                while (data.next()) {
            %>
                <tr>
                    <td><%= serialNumber++ %></td>
                    <td><%= data.getString("title") %></td>
                    <td><%= data.getString("tags") %></td>
                    <td><%= data.getDate("uploadeddate") %></td>
                    <td>
                        <a href="/api/filter/reviewertags?paperid=<%=data.getLong("id")%>" class="assign-reviewer-link">Assign Reviewer</a>
                    </td>
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
            form.action = "/api/filter/bytags";
            form.method = "post";
            form.querySelector('[name="selectedTag"]').value = selectedTag;
            
            form.submit();
             
        }
        
    </script>
</body>
</html>