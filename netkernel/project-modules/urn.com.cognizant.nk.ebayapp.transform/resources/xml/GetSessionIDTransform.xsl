<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:sr="urn:ebay:apis:eBLBaseComponents" version="1.0">
	<xsl:output method="xml" />
	<xsl:template match="sr:GetSessionIDResponse">
	<SessionID>
		<xsl:value-of select="sr:SessionID" />
	</SessionID>
	</xsl:template>
</xsl:stylesheet>