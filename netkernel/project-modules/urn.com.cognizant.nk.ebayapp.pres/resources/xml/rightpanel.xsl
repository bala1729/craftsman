<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                 version="1.0">



<xsl:output method="html"/>
<xsl:template match="/">
 <html>
   <head>
     <title>Items for a category</title>
   </head>
   <body>
     <table border="1" cellpadding="10">
      <tr>
       <th align="left">ItemId</th>
       <th align="left">Title</th>
       <th align="left">CategoryId</th>
       <th align="left">CategoryName</th>
       <th align="left">ViewItemURL</th>
      </tr>
      <xsl:apply-templates select="Items/Item">
       
      </xsl:apply-templates>
    </table><p />
   </body>
 </html>
</xsl:template>


<xsl:template match="Item">
   <tr>
    <td><xsl:value-of select="ItemID"/></td>
    <td><xsl:value-of select="Title"/></td>
    <td><xsl:value-of select="PrimaryCategoryID"/></td>
    <td><xsl:value-of select="PrimaryCategoryName"/></td>
    <td><a href="viewItemURL"><xsl:value-of select="ViewItemUrl"/></a></td>
   </tr>
</xsl:template>
</xsl:stylesheet>