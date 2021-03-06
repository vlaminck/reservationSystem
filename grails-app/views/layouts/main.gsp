<!DOCTYPE html>
<%@ page import="reservationsystem.Person" %>
<html>
<head>
  <title><g:layoutTitle default="Grails"/></title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
  <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
  <g:layoutHead/>
  <g:javascript library="application"/>
</head>
<body>
<div id="spinner" class="spinner" style="display:none;">
  <img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
</div>
<div id="header"><g:render template="/header"/></div>
<div id="side"><g:render template="/side"/></div>
<div id="wrapper">
  <g:if test="${Person.currentUser?.account?.isLocked}">
    <h1>
      Your account has been locked.
    </h1>
    <g:link controller="account" action="unlock">Click here to unlock</g:link>
  </g:if>
  <g:else>
    <g:layoutBody/>
  </g:else>
</div>
</body>
</html>