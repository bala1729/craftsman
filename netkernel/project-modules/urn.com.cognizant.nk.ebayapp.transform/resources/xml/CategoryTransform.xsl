<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:sr="urn:ebay:apis:eBLBaseComponents" version="1.0">
	<xsl:output method="xml" />
	<xsl:template match="sr:GetCategoriesResponse">
		<Categories>

			<xsl:for-each select="sr:CategoryArray/sr:Category">
			<Category>
				<CategoryID>
					<xsl:value-of select="sr:CategoryID" />
				</CategoryID>
				<CategoryLevel>
					<xsl:value-of select="sr:CategoryLevel" />
				</CategoryLevel>
				<CategoryName>
					<xsl:value-of select="sr:CategoryName" disable-output-escaping="no"/>
				</CategoryName>
				<CategoryParentID>
					<xsl:value-of select="sr:CategoryParentID" disable-output-escaping="no"/>
				</CategoryParentID>
				<LeafCategory>
					<xsl:value-of select="sr:LeafCategory" />
				</LeafCategory>
			</Category>
			</xsl:for-each>

		</Categories>

	</xsl:template>
</xsl:stylesheet>