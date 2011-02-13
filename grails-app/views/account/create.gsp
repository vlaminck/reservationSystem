<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create an Account</title>
  <meta name="layout" content="main"/>
</head>
<body>
<g:form name="createForm" method="post" controller="account" action="save" >
  First name:<input type="text" name="firstName" />
  Last name:<input type="text" name="lastName" />
  Email:<input type="email" name="email" />
  Password:<input type="password" name="password" />
  <input type="submit" value="Submit" />
</g:form>
</body>
</html>