<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Search Results</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  <g:render template="/mediaList" model="[mediaList:results]"/>
</div>
</body>
</html>
