<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create an Account</title>
  <meta name="layout" content="main"/>
</head>
<body>
Hello ${currentUser.firstName} ${currentUser.lastName},
<g:if test="${account.fines}">
  <br/>
  <br/>
  You owe $${account.calculateFines()} in fines.
  <g:link action="payFines">Pay Fines</g:link>
  <br/>
</g:if>
<br/>
<g:if test="${account.reservationList}">
  You have the following reserved:
  <ul class="reservationList">
    <li class="title"><b>TITLE</b></li>
    <li class="format"><b>FORMAT</b></li>
    <li class="return"></li>
    <g:each var="reservation" in="${account.reservationList.reservations}">
      <li class="title">
        ${reservation.media.title}
      </li>
      <li class="format">
        ${reservation.media.format.toString().toLowerCase()}
      </li>
      <li class="return">
        <g:link controller="media" action="returnMedia" id="${reservation.media.id}">
          return
        </g:link>
      </li>
    </g:each>
  </ul>
</g:if>
<g:else>
  You don't have anything reserved.
</g:else>
</body>
</html>