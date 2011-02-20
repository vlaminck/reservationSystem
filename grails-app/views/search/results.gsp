<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Search Results</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  RESULTS:
  <ul class="resultsList">
    <g:each var="media" in="${results}">
      <li><g:link controller="media" action="show" id="${media.id}">${media.title}</g:link></li>
    </g:each>
  </ul>
</div>
</body>
</html>
