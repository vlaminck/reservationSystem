<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create an Account</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  <g:form name="createForm" method="post" controller="account" action="createAccount" class="account">
    <ul>
      <li>First name</li>
      <li>Last name:</li>
      <li><input type="text" name="firstName"/></li>
      <li><input type="text" name="lastName"/></li>
      <li class="email">Email:</li>
      <li class="email"><input type="email" name="email"/></li>
      <li>Card Number:</li>
      <li>Phone Number:</li>
      <li><input type="text" name="cardId"/></li>
      <li><input type="text" name="phoneNumber"/></li>
      <li>Password:</li>
      <li>Confirm Password:</li>
      <li><input type="password" name="password"/></li>
      <li><input type="password" name="confirmPassword"/></li>
      <li><input type="submit" value="Submit"/></li>
    </ul>
  </g:form>
</div>
</body>
</html>