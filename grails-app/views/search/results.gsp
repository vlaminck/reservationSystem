<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Search Results</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  <ul class="resultsList">

    <li class="header">PRINT</li>
    <li class="artist">AUTHOR</li>
    <li class="title">TITLE</li>
    <g:each var="media" in="${results.print}">
      <li class="artist">
        ${media.artist}
      </li>
      <li class="title">
        <g:link controller="media" action="show" id="${media.id}">${media.title}</g:link>
      </li>
    </g:each>

    <li class="header">AUDIO</li>
    <li class="artist">ARTIST</li>
    <li class="title">TITLE</li>
    <g:each var="media" in="${results.audio}">
      <li class="artist">
        ${media.artist}
      </li>
      <li class="title">
        <g:link controller="media" action="show" id="${media.id}">${media.title}</g:link>
      </li>
    </g:each>

    <li class="header">VIDEO</li>
    <li class="artist">ACTOR(S)</li>
    <li class="title">TITLE</li>
    <g:each var="media" in="${results.video}">
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
