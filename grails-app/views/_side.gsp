<div class="search">
  <g:form controller="search" action="results">
    <ul>
      <li>SEARCH:</li>
      <li><input type="text" name="query"/></li>
      <li>Filters:
        <ul class="filters">
          <li><input type="checkbox" name="type" value="PRINT" checked="checked"/>PRINT</li>
          <li><input type="checkbox" name="type" value="AUDIO" checked="checked"/>AUDIO</li>
          <li><input type="checkbox" name="type" value="VIDEO" checked="checked"/>VIDEO</li>
        </ul>
      <li>
        Format:
      </li>
      <li>
        <ul class="filters">
          <li><input type="checkbox" name="format" value="PAPER" checked="checked"/>PAPER</li>
          <li><input type="checkbox" name="format" value="EPUB" checked="checked"/>EPUB</li>
          <li><input type="checkbox" name="format" value="CD" checked="checked"/>CD</li>
          <li><input type="checkbox" name="format" value="MP3" checked="checked"/>MP3</li>
          <li><input type="checkbox" name="format" value="DVD" checked="checked"/>DVD</li>
          <li><input type="checkbox" name="format" value="AVI" checked="checked"/>AVI</li>
        </ul>
      </li>
      <li><input type="submit" value="Search"/></li>
    </ul>
  </g:form>
  <g:link controller="media" action="list">Browse All</g:link>
</div>
