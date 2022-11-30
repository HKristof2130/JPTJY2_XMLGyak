<?xml version = "1.0" encoding = "UTF-8"?>

<xsl:stylesheet version = "1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match = "/">
    <html>
      <body>
	    <h2> Vizsgák eredmények </h2>
        
	    <table border="3">
	    <tr bgcolor = "black">
	        <th>Kurzus</th>
	        <th>Helyszin</th>
	        <th>nap</th>
	        <th>tol</th>
	        <th>ig</th>
	        <th>oktato</th>
            <th>jegy</th>
	    </tr>

	    <xsl:for-each select="JPTJY2/vizsgak/vizsga">
	    <tr>
            <td><xsl:value-of select = "kurzus"/></td>
            <td><xsl:value-of select = "helyszin"/></td>
	            <xsl:for-each select="idopont">
					<td><xsl:value-of select = "nap"/></td>
					<td><xsl:value-of select = "tol"/></td>
					<td><xsl:value-of select = "ig"/></td>
				</xsl:for-each>
            <td><xsl:value-of select = "oktato"/></td>
            <td><xsl:value-of select = "jegy"/></td>
	    </tr>
	    </xsl:for-each>
	    </table>

        <table border="3">
        <tr bgcolor = "red">
	        <th>Kurzus átlag</th>
	    </tr>
        <tr>
            <td><xsl:value-of
                select="sum(/JPTJY2/vizsgak/vizsga/jegy) div count(/JPTJY2/vizsgak/vizsga/jegy)" /></td>
        </tr>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>