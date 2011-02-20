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
    <li class="artist">ARTIST</li>
    <li class="title">TITLE</li>
    <g:each var="media" in="${results}">
      <li class="artist">
        ${media.artist}
      </li>
      <li class="title">
        <g:link controller="media" action="show" id="${media.id}">${media.title}</g:link>
      </li>
    </g:each>
  </ul>
</div>
</body>
</html>
