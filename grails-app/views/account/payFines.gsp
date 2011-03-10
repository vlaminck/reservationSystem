<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="reservationsystem.Person" %>
<html>
  <head><title>Pay Up Sucker</title></head>
  <body>
  <h1>This is where you would pay $${Person.currentUser.account.calculateFines()}</h1>
  </body>
</html>