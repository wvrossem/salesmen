<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:tt="http://purl.org/net/timetrack"
		xmlns:exsl="http://exslt.org/common"
		xmlns:cvs="http://nwalsh.com/rdf/cvs#"
		xmlns:dc='http://purl.org/dc/elements/1.1/'
		xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'
		xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
		exclude-result-prefixes="tt exsl cvs rdf rdfs dc"
		version="1.0">

  <rdf:Description rdf:about="http://purl.org/net/timetrack/xsl/timetrack-to-html.xsl">
    <rdf:type rdf:resource="http://norman.walsh.name/knows/taxonomy#XSL"/>
    <dc:type rdf:resource="http://purl.org/dc/dcmitype/Text"/>
    <dc:format>application/xsl+xml</dc:format>
    <dc:title>XSLT to Transform TimeTrack to HTML</dc:title>
    <cvs:date>$Date$</cvs:date>
    <dc:rights>Copyright &#169; 2009 Sina Khakbaz Heshmati</dc:rights>
    <dc:description>
      This XSLT stylesheet takes an XML timesheet expressed in TimeTrack
      and transforms it into HTML.
    </dc:description>
    <dc:lincense rdf:resource="http://creativecommons.org/licenses/GPL/2.0/"/>
  </rdf:Description>
  
  <xsl:output indent="yes" method="text" encoding="UTF-8"/>
 
  <xsl:template match="tt:timesheets">
    <div class="timetrack">
      <div class="{local-name(.)}">
	<xsl:apply-templates select="tt:title"/>
	<xsl:apply-templates select="@from"/>
	<xsl:apply-templates select="@to|tt:person"/>
      </div>
    </div>
  </xsl:template>

  <xsl:template match="@from">
    <xsl:call-template name="key-value-pair">
      <xsl:with-param name="key" select="'From'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="@to">
    <xsl:call-template name="key-value-pair">
      <xsl:with-param name="key" select="'To'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="tt:title">
    <h2>
      <xsl:apply-templates/>
    </h2>
  </xsl:template>

  <xsl:template match="tt:published">
    <xsl:call-template name="key-value-pair">
      <xsl:with-param name="key" select="'First Published'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="tt:updated">
    <xsl:call-template name="key-value-pair">
      <xsl:with-param name="key" select="'Last Updated'"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="tt:person">
    <xsl:variable name="total">
      <xsl:call-template name="compute-total-contrib-in-hour"/>
    </xsl:variable>
    <div class="{local-name(.)}">
      <xsl:apply-templates/>
      <p class="total">
	<xsl:call-template name="key-value-pair">
	  <xsl:with-param name="key" select="'Total Contribution'"/>
	  <xsl:with-param name="val" select="concat($total, ' ', 'Hours')"/>
	</xsl:call-template>
      </p>
    </div>
  </xsl:template>

  <xsl:template name="compute-total-contrib-in-hour">
    <xsl:variable name="mins">
      <durations>
	<xsl:for-each select="tt:timesheet/tt:item">
	  <duration>
	    <xsl:call-template name="xsd-duration-to-minutes">
	      <xsl:with-param name="duration" select="@duration"/>
	    </xsl:call-template>
	  </duration>
	</xsl:for-each>
      </durations>
    </xsl:variable>
    <xsl:value-of 
      select="round(sum(exsl:node-set($mins)//*[name(.)='duration']) div 60)"/>
  </xsl:template>

  <xsl:template match="tt:person/tt:name">
    <div class="{local-name(.)}">
      <h3>
	<xsl:apply-templates/>
      </h3>
    </div>
  </xsl:template>

  <xsl:template match="tt:timesheet">
    <table>
      <thead>
	<tr>
	  <th>Duration</th>
	  <th>Description</th>
	  <th>References</th>
	</tr>
      </thead>
      <tbody>
	<xsl:apply-templates select="tt:item"/>
      </tbody>
    </table>
  </xsl:template>

  <xsl:template match="tt:item">
    <tr>
      <xsl:apply-templates select="@duration"/>
      <xsl:apply-templates select="tt:desc"/>
      <xsl:choose>
	<xsl:when test="tt:refs">
	  <xsl:apply-templates select="tt:refs"/>	  
	</xsl:when>
	<xsl:otherwise>
	  <td class="refs">N/A</td>
	</xsl:otherwise>
      </xsl:choose>
    </tr>
  </xsl:template>

  <xsl:template match="@duration">
    <xsl:variable name="mins">
      <xsl:call-template name="xsd-duration-to-minutes">
	<xsl:with-param name="duration" select="."/>
      </xsl:call-template>
    </xsl:variable>
    <td class="{local-name(.)}">
      <xsl:value-of select="concat(string($mins), ' ', 'Minutes')"/>      
    </td>
  </xsl:template>

  <xsl:template match="tt:refs">
    <td class="{local-name(.)}">
      <xsl:apply-templates/>
    </td>
  </xsl:template>

  <xsl:template match="tt:ref">
    <xsl:variable name="val" select="."/>
    <span class="{local-name(.)}">
      <xsl:choose>
	<xsl:when test="starts-with(string($val), 'http://')">
	  <a href="{$val}" title="{$val}">
	    <xsl:text>Link</xsl:text>
	  </a>
	</xsl:when>
	<xsl:otherwise>
	  <xsl:value-of select="$val"/>
	</xsl:otherwise>
      </xsl:choose>
    </span>
  </xsl:template>

  <xsl:template match="tt:desc">
    <td class="{local-name(.)}">
      <xsl:value-of select="."/>
    </td>
  </xsl:template>

  <xsl:template match="xml:id"></xsl:template>

  <xsl:template name="key-value-pair">
    <xsl:param name="key"/>
    <xsl:param name="val" select="."/>
    <xsl:param name="separator" select="': '"/>
    <div class="keyval">
      <span class="key">
	<xsl:value-of select="$key"/>
      </span>
      <span class="separator">
	<xsl:value-of select="$separator"/>
      </span>
      <span class="val">
	<xsl:value-of select="$val"/>
      </span>
    </div>
  </xsl:template>

  <!--
      string -> number

      Given a duration, this template returns the number of minutes
      represented by that duration. Please note that, the number of 
      seconds are not considered in this implementation.

      @param: duration must comply with the xsd:duration
  -->
  <xsl:template name="xsd-duration-to-minutes">
    <xsl:param name="duration"/>

    <xsl:variable name="str" 
		  select="normalize-space(substring($duration, 2))"/>

    <xsl:variable name="hours">
      <xsl:call-template name="extract-hours-from-xsd-duration">
	<xsl:with-param name="str" select="$str"/>
      </xsl:call-template>
    </xsl:variable>

    <xsl:variable name="minutes">
      <xsl:call-template name="extract-minutes-from-xsd-duration">
	<xsl:with-param name="str" select="$str"/>
      </xsl:call-template>
    </xsl:variable>

    <xsl:choose>
      <xsl:when test="$hours = ''">
	<xsl:value-of select="number($minutes)"/>
      </xsl:when>
      <xsl:when test="$minutes = ''">
	<xsl:value-of select="number($hours) * 60"/>
      </xsl:when>
      <xsl:otherwise>
	<xsl:value-of select="number($minutes) + number($hours) * 60"/>
      </xsl:otherwise>
    </xsl:choose> 
  </xsl:template>

  <xsl:template name="extract-hours-from-xsd-duration">
    <xsl:param name="str"/>
    <xsl:value-of select="substring-before(substring-after($str, 'T'), 'H')"/>
  </xsl:template>

  <xsl:template name="extract-minutes-from-xsd-duration">
    <xsl:param name="str"/>
    <!-- Determine the previous delimiter; if there's no hour then it 
	 would be 'T', which has to be there according to the standard. -->
    <xsl:variable name="back-delim">
      <xsl:choose>
	<xsl:when test="contains($str, 'H')">H</xsl:when>
	<xsl:otherwise>T</xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:value-of 
	select="substring-before(substring-after($str, $back-delim), 'M')"/>
  </xsl:template>

</xsl:stylesheet>