<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
    <table>
        <tr>
            <th>Vezetéknév</th>
            <th>Keresztnév</th>
            <th>Becenév</th>
            <th>Kor</th>
            <th>Ösztöndíj</th>
        </tr>
        <xsl:for-each select="class/student">
            <tr>
                <td><xsl:value-of select="vezeteknev"/></td>
                <td><xsl:value-of select="keresztnev"/></td>
                <td><xsl:value-of select="becenev"/></td>
                <td><xsl:value-of select="kor"/></td>
                <td><xsl:value-of select="osztondij"/></td>
            </tr>
        </xsl:for-each>
    </table>
    </xsl:template>
</xsl:stylesheet>