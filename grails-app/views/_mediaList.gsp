<ul class="mediaList">
  <li class="header">PRINT</li>
  <li class="artist">AUTHOR</li>
  <li class="title">TITLE</li>
  <g:each var="media" in="${mediaList.print}">
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
  <g:each var="media" in="${mediaList.audio}">
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
  <g:each var="media" in="${mediaList.video}">
    <li class="artist">
      ${media.artist}
    </li>
    <li class="title">
      <g:link controller="media" action="show" id="${media.id}">${media.title}</g:link>
    </li>
  </g:each>
</ul>
