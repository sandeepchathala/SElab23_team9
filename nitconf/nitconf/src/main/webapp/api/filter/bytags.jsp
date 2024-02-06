<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.PreparedStatement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Filter</title>
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

        /* Styling for buttons and links */
        button, a {
            background-color: #4CAF50;
            color: white;
            padding: 8px; /* Adjusted padding for buttons */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 5px 0;
        }

        button:hover, a:hover {
            background-color: #45a049;
        }

        /* Styling for the filter dropdown */
        #tagFilter {
            display: inline-block;
            margin-bottom: 10px;
        }

        #tagDropdown {
            padding: 8px;
            margin-right: 5px;
        }

        /* Styling to make Reset button same size as Filter button */
        #resetButton {
            padding: 8px; /* Adjusted padding for the Reset button */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 5px 0;
        }

        #resetButton:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Unassigned Papers</h2>

    <div id="tagFilter">
        <button onclick="showTagFilter()">Filter by Tag</button>
        <a href="/api/papers/unassignedpapers" style="text-decoration:none;">Reset</a>
        <form id="selecttagForm">
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
            Connection con1 = null;
            Statement st1 = null;
            ResultSet data = null;
            int serialNumber = 1;
            String selectedTag = (String)request.getAttribute("selectedTag");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB", "root", "Balu@321");
                st1 = con1.createStatement();
                String str1 = "SELECT * FROM Paper WHERE tags =? AND status = 0";
                PreparedStatement pstmt = con1.prepareStatement(str1);
                pstmt.setString(1, selectedTag);
                data = pstmt.executeQuery();
                while (data.next()) {
            %>
                <tr>
                    <td><%= serialNumber++ %></td>
                    <td><%= data.getString("title") %></td>
                    <td><%= data.getString("tags") %></td>
                    <td>
                        <a href="/api/filter/reviewertags?paperid=<%=data.getLong("id")%>">Assign Reviewer</a>
                    </td>
                    <td><a href="<%= data.getString("link") %>">View PDF</a></td>
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
                tagFilter.style.display = "inline-block";
            } else {
                tagFilter.style.display = "none";
            }
        }
        
        // Function to apply tag filter
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