<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create an Account</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  <g:form name="createForm" method="post" controller="account" action="createAccount" class="account">
    First name:
    <br/>
    <input type="text" name="firstName"/>
    <br/>
    Last name:
    <br/>
    <input type="text" name="lastName"/>
    <br/>
    Email:
    <br/>
    <input type="email" name="email"/>
    <br/>
    Password:
    <br/>
    <input type="password" name="password"/>
    <br/>
    <input type="submit" value="Submit"/>
  </g:form>
</div>
</body>
</html>