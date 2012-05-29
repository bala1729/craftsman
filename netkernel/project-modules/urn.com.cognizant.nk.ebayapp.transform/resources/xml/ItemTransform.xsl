<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:sr="http://www.ebay.com/marketplace/search/v1/services" version="1.0">
	<xsl:output method="xml" />
	<xsl:template match="sr:findItemsByCategoryResponse">
		<Items>
			<xsl:for-each select="sr:searchResult/sr:item">
			<Item>
				<ItemID>
					<xsl:value-of select="sr:itemId" />
				</ItemID>
				<Title>
					<xsl:value-of select="sr:title" />
				</Title>
				<PrimaryCategoryName>
					<xsl:value-of select="sr:primaryCategory/sr:categoryName" disable-output-escaping="no"/>
				</PrimaryCategoryName>
				<PrimaryCategoryID>
					<xsl:value-of select="sr:primaryCategory/sr:categoryId" disable-output-escaping="no"/>
				</PrimaryCategoryID>
				<ViewItemUrl>
					<xsl:value-of select="sr:viewItemURL" />
				</ViewItemUrl>
			</Item>
			</xsl:for-each>
		</Items>
	</xsl:template>
</xsl:stylesheet>