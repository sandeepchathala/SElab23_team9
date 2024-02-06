<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement,java.sql.PreparedStatement, java.sql.ResultSet" %>

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
        }

        table {
            width: 80%;
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
    <h2>Show Review</h2>
    <%String paper_title=(String)request.getAttribute("paper_title"); %>
    <h2>Reviews of paper : <%=paper_title%></h2>
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
             Long paper_id=(Long)request.getAttribute("paper_id");
    Connection con=null;
    Statement st=null;
    ResultSet data= null;
    long serialno=1;
      try{
          Class.forName("com.mysql.jdbc.Driver");
          con= DriverManager.getConnection("jdbc:mysql://localhost:3306/SE_DB","root","Balu@321");
          st = con.createStatement();
          String query = "SELECT * FROM paper_reviewer " +
                  "LEFT JOIN paper ON paper.id = paper_reviewer.paper_id " +
                  "LEFT JOIN Reviewer ON Reviewer.id= paper_reviewer.reviewer_id " +
                  "WHERE (paper_reviewer.paper_id = ?) AND (paper.status = 2 OR paper.status = 1)";
          PreparedStatement pstmt = con.prepareStatement(query);
          pstmt.setLong(1,paper_id);
          data=pstmt.executeQuery();
            while (data.next()) {
            %>
            <tr>
                <td><%= serialno++ %></td>
                <td><%= data.getString("name") %></td>
                <td><%= data.getString("comment") %></td>
                <td><%= data.getFloat("rating") %></td>
            </tr>
            <%
            }
      } catch(Exception e){}
          finally {
              try {
                  if (data != null) data.close();
                  if (st != null) st.close();
                  if (con != null) con.close();
              } catch (Exception e) {
              }
          }
          
            %>
        </tbody>
    </table>

    <a class="button" href="/api/actions/accept?paper_id=<%=paper_id%>">Accept</a>
    <a class="button" href="/api/actions/reject?paper_id=<%=paper_id%>">Reject</a>
</body>
</html>