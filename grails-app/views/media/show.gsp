<%@ page import="reservationsystem.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Media Details</title>
  <meta name="layout" content="main"/>
</head>
<body>
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
          <li>
            <g:if test="${Person.currentUser?.hasMaterialReserved(duplicate)}">
              You have reserved the ${duplicate.format.toString().toLowerCase()} format
            </g:if>
            <g:else>
              <g:link controller="media" action="reserve" id="${duplicate.id}" method="POST">
                <g:if test="${duplicate.isAvailable}">
                  Reserve ${duplicate.format.toString().toLowerCase()} format
                </g:if>
                <g:else>
                  Join the wait list for the ${duplicate.format.toString().toLowerCase()} format.
                  <span class="watiListInfo">estimated time to wait is ${duplicate.estimatedWait()}</span>
                </g:else>
              </g:link>
            </g:else>
          </li>
        </g:each>
      </ul>
    </li>
  </ul>
</g:if>
</body>
</html>