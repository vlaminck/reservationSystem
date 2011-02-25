<head>
  <meta name='layout' content='main'/>
  <title>Login</title>
</head>

<body>
<div id=wrapper>
  <div id='login'>
    <div class='inner'>
      <g:if test='${flash.message}'>
        <div class='login_message'>${flash.message}</div>
      </g:if>
      <div class='fheader'>Please Login..</div>
      <form action='${postUrl}' method='POST' id='loginForm' class='login' autocomplete='off'>
        <p>
          <label for='username'>Email</label>
          <input type='text' class='text_' name='j_username' id='username'/>
        </p>
        <p>
          <label for='password'>Password</label>
          <input type='password' class='text_' name='j_password' id='password'/>
        </p>
        <p>
          <label for='remember_me'>Remember me</label>
          <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
            <g:if test='${hasCookie}'>checked='checked'</g:if>/>
          <input type='submit' value='Login'/>
        </p>
      </form>
    </div>
  </div>
  <script type='text/javascript'>
    <!--
    (function() {
      document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
  </script>
</div>
</body>
