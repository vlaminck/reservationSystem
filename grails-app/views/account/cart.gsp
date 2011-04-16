<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>View Your Cart</title>
  <meta name="layout" content="main"/>
</head>
<body>
<g:if test="${mediaList}">
  <ul class="reservationList">
    <li class="title"><b>TITLE</b></li>
    <li class="format"><b>FORMAT</b></li>
    <li class="return"></li>
    <g:each var="media" in="${mediaList}">
      <li class="title">
        <g:link controller="media" action="show" id="${media.id}">
          ${media.title}
        </g:link>
      </li>
      <li class="format">
        ${media.format.toString().toLowerCase()}
      </li>
      <li class="return">
        <g:link controller="media" action="removeFromShoppingCart" id="${media.id}">
          Remove from Cart
        </g:link>
      </li>
    </g:each>
  </ul>
  <g:if test="${currentUser}">
    <g:link controller="account" action="checkout">
      <input type="button" value="Check Out"/>
    </g:link>
  </g:if>
  <g:else>
    <g:link controller="account" action="checkout">Login</g:link>
    or
    <g:link controller="account" action="create" params="[checkout:true]">create an account</g:link>
    to check out.
  </g:else>
</g:if>
<g:else>
  Your cart is empty
</g:else>
</body>
</html>