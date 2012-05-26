<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:sr="urn:ebay:apis:eBLBaseComponents">
	<xsl:template match="/">
		<html>
			<head>
				<script type="text/javascript">
					function show(c)
					{
					var ele=document.getElementById('tab'+c);
					var disp=ele.style.display;
					if(disp=="none")
					{
					document.getElementById('ec'+c).src="resources/xml/minus.jpg";
					document.getElementById('tab'+c).style.display="block";
					}
					else
					{
					document.getElementById('ec'+c).src="resources/xml/plus.jpg";
					document.getElementById('tab'+c).style.display="none";
					}
					}
  </script>
			</head>
			<body>


				<div
					style="background-color:white;margin:0px 20px 0px 20px;padding:20px 20px 20px 20px;height:82%">
					<table border="0" style="height:100%;background-color:white;">
						<td width="30%"
							style="min-width:50%;border-right:solid 2px gray;padding:20px"
							valign="top">
							<ul style="margin-left:0px;list-style:none;">
								<li>
									
									Categories
									<ul id="tabm" style="list-style:none;display:none">
										<xsl:for-each select="/sr:Categories/sr:Category">
											<li>
												
												<xsl:value-of select="CategoryName" />

											</li>
										</xsl:for-each>
									</ul>
								</li>
							</ul>
						</td>
						<td width="70%" style="min-width:50%">
						</td>
					</table>

				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>