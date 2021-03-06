<%@ page import="reservationsystem.Person" %>
<div id="LRS_Logo"><g:link uri="/">Library Reservation System</g:link></div>
<div class="nav">
  <g:if test="${Person.currentUser}">
    <g:link controller="account" action="show">My Account</g:link>
  </g:if>
  <g:else>
    <g:link controller="account" action="create">Create Account</g:link>
  </g:else>
  <ul class="open">
    <g:if test="${Person.currentUser}">
      <li><g:link controller="logout">Logout</g:link></li>
      <li><g:link controller="account" action="cart">View Cart</g:link></li>
    </g:if>
    <g:else>
      <li><g:link controller="login" action="auth">Log in</g:link></li>
      <li><g:link controller="account" action="cart">View Cart</g:link></li>
    </g:else>
  </ul>
</div>

  