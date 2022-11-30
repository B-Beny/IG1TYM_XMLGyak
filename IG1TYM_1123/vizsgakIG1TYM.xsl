<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

        <html>
            <body>
                <h2>Vizsgak</h2>

                <table border="4">
                    <tr bgcolor = "#9acd23">
                        <th>Kurzus</th>
                        <th>Helyszin</th>
                        <th>Nap</th>
                        <th>Tol</th>
                        <th>Ig</th>
                        <th>Oktato</th>
                        <th>Jegy</th>
                        <th>Atlag</th>
                    </tr>

                    <xsl:for-each select="vizsgak/vizsga">
                        <tr>
                            <td><xsl:value-of select="kurzus" /></td>
                            <td><xsl:value-of select="helyszin" /></td>
                                <xsl:for-each select="idopont">
                                    <td><xsl:value-of select="nap" /></td>
                                    <td><xsl:value-of select="tol" /></td>
                                    <td><xsl:value-of select="ig" /></td>
                                </xsl:for-each>
                            <td><xsl:value-of select="oktato" /></td>
                            <td><xsl:value-of select="jegy" /></td>
                            
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td>Atlag</td>
                        <td><xsl:value-of select="sum(/vizsgak/vizsga/jegy) div count(/vizsgak/vizsga/jegy)" /></td>
                     </tr>
                </table>
            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>