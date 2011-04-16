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
            <g:if test="${currentUser?.hasMaterialReserved(duplicate)}">
              You have reserved the ${duplicate.format.toString().toLowerCase()} format
            </g:if>
            <g:else>
              <g:if test="${duplicate.isAvailable}">
                <g:link controller="media" action="reserve" id="${duplicate.id}" method="POST">
                  Reserve ${duplicate.format.toString().toLowerCase()} format
                </g:link>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <g:if test="${session.shoppingCart?.contains(duplicate.id.toString())}">
                  Currently in your cart
                </g:if>
                <g:else>
                  <g:link controller="media" action="addToShoppingCart" id="${duplicate.id}" method="POST">
                    Add to shopping Cart
                  </g:link>
                </g:else>
              </g:if>
              <g:elseif test="${currentUser?.isOnWaitList(duplicate)}">
                You are on the wait list for the ${duplicate.format.toString().toLowerCase()} format of this book.<br/>
                Your approximate wait time is ${currentUser?.estimatedWait(duplicate)}
              </g:elseif>
              <g:else>
                <g:link controller="media" action="reserve" id="${duplicate.id}" method="POST">
                  Join the wait list for the ${duplicate.format.toString().toLowerCase()} format.
                </g:link>
                <g:if test="${duplicate.estimatedWait()}">
                  <span class="watiListInfo">The estimated time to wait is ${duplicate.estimatedWait()}</span>
                </g:if>
              </g:else>
            </g:else>
          </li>
        </g:each>
      </ul>
    </li>
  </ul>
  <img src="${createLinkTo(dir: 'images', file: media.coverArtLocation ?: 'no_image.gif')}" alt="missing cover art" class="coverArt"/>
</g:if>
</body>
</html>