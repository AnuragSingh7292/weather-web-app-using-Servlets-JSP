<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Weather Web</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f9;
    }
    /*.alert {*/
    /*  padding: 20px;*/
    /*  margin: 20px;*/
    /*  border: 1px solid #f44336;*/
    /*  background-color: #f8d7da;*/
    /*  color: #721c24;*/
    /*  font-weight: bold;*/
    /*  border-radius: 5px;*/
    /*}*/
  </style>
</head>
<body>

<%
  // Retrieve the error message from the request attribute
  String errorMessage = (String) request.getAttribute("errorMessage");
%>

<% if (errorMessage != null) { %>
<!-- Display the error message using a styled alert box -->
<%--<div class="alert">--%>
<%--  <%= errorMessage %>--%>
<%--</div>--%>
<!-- Optional: Trigger an alert box as well -->
<script>
  alert("<%= errorMessage %>");
</script>
<% } %>

</body>
</html>
