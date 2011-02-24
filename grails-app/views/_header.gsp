<div id="LRS_Logo">Library Reservation System</div>
<div class="nav">
  <g:link controller="account" action="show">My Account</g:link>
  <ul class="open">
    <g:if test="${Person?.currentUser}"><li><g:link controller="logout">Logout</g:link></li></g:if>
    <g:else><li><g:link controller="login" action="auth">Log in</g:link></li></g:else>
  </ul>
</div>

  