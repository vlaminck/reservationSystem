<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Update your Account</title>
  <meta name="layout" content="main"/>
</head>
<body>
<g:form name="createForm" method="post" controller="account" action="save" class="account">
  First name:
  <br/>
  <input type="text" name="firstName" value="${person.firstName}"/>
  <br/>
  Last name:
  <br/>
  <input type="text" name="lastName" value="${person.lastName}"/>
  <br/>
  <input type="hidden" name="email" value="${person.email}"/>
  Please enter your password:
  <br/>
  <input type="password" name="password"/>
  <br/>
  <input type="submit" value="Submit"/>
</g:form>
</body>
</html>