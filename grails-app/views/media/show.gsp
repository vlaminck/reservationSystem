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
      <li>
        ${media.type == reservationsystem.MediaType.PRINT ? 'Author: ' :
          media.type == reservationsystem.MediaType.AUDIO ? 'Artist: ' : 'Actor(s): '}
        ${media.artist}
      </li>
      <g:if test="${media.description}"><li>Description: ${media.description}</li></g:if>
      <li>Formats Available:
        <ul>
          <g:each var="duplicate" in="${duplicates}">
            <li><g:link controller="media" action="reserve" id="${duplicate.id}" method="POST">${duplicate.format}</g:link></li>
          </g:each>
        </ul>
      </li>
    </ul>
  </g:if>
</div>
</body>
</html>