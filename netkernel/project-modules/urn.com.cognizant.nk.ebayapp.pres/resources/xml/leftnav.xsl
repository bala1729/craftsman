<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                 version="1.0">



<xsl:output method="html"/>
<xsl:template match="/">
 <html>
   <head>
     <title>Category List</title>
   </head>
   <body>
     <ul>
      <xsl:apply-templates select="Categories/Category">
       
      </xsl:apply-templates>
	</ul>
   </body>
 </html>
</xsl:template>


<xsl:template match="Category">
   <li>
    <xsl:value-of select="CategoryName"/>
   </li>
</xsl:template>
</xsl:stylesheet>