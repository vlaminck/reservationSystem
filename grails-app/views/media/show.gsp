<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Media Details</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  <g:if test="${media}">
    <h2>${media.title}</h2>
    <ul>
      <li>Type: ${media.type}</li>
      <li>Artist: ${media.description}</li>
      <li>Description: ${media.description}</li>
      <li>Formats Available: ${formats}</li>
    </ul>
  </g:if>
</div>
</body>
</html>