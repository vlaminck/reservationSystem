<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Create an Account</title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="wrapper">
  Hello ${currentUser.firstName} ${currentUser.lastName},
  <br/>
  <g:if test="${account.reservationList}">
    You have the following reserved:
    <ul class="reservationList">
      <li class="title" ><b>TITLE</b></li>
      <li class="format"><b>FORMAT</b></li>
      <g:each var="reservation" in="${account.reservationList.reservations}">
        <li class="title" >${reservation.media.title}</li>
        <li class="format">${reservation.media.format.toString()}</li>
      </g:each>
    </ul>
  </g:if>
  <g:else>
    You don't have anything reserved.
  </g:else>
</div>
</body>
</html>