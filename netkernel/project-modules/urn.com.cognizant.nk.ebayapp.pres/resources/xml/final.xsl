<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                 version="1.0">



<xsl:output method="html"/>
<xsl:template match="/">
 <html>
   <head>
     <title>The XSLT list style sheet</title>
   </head>
   <body>
     <table border="1" cellpadding="10">
      <tr>
       <th style="display:none" align="left">itemId</th>
       <th align="left">title</th>
       <th align="left">categoryId</th>
       <th align="left">categoryName</th>
       <th align="left">viewItemURL</th>
      </tr>
      <xsl:apply-templates select="Categories/item">
       
      </xsl:apply-templates>
    </table><p />
   </body>
 </html>
</xsl:template>


<xsl:template match="item">
   <tr>
   <div>
    <td style="display:none"><xsl:value-of select="itemId"/></td></div>
    <td><xsl:value-of select="title"/></td>
    <td><xsl:value-of select="categoryId"/></td>
    <td><xsl:value-of select="categoryName"/></td>
    <td><a href="viewItemURL"><xsl:value-of select="viewItemURL"/></a></td>
   </tr>
</xsl:template>
</xsl:stylesheet>