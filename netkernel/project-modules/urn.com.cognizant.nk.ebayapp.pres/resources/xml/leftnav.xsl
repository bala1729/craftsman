<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:sr="urn:ebay:apis:eBLBaseComponents">
<xsl:output method="xml" />
<xsl:template match="/">
  <html>
    <head>
     <action method="addJs"><script language="javascript" type="text/javascript"> windows.onload = function(){
alert("hi");
}
   function show(c)
   {
         var ele=document.getElementById(c);
     var disp=ele.style.display;
     if(disp=="none")
     {
      document.getElementById(c).style.display="block";
     }
     else
     {
      document.getElementById(c).style.display="none";
     }
   }
</script>
</action>
    </head>
  <body>
<div  style="background-color:white;margin:0px 20px 0px 20px;padding:20px 20px 20px 20px;height:82%">
    <table border="0"  style="height:100%;background-color:white;">
      <td width="30%" style="min-width:50%;border-right:solid 2px gray;padding:20px" valign="top">
       <ul  style="margin-left:0px;list-style:none;"> 
         <li><input type="button" value="+" id="ecm" style="width:15px;height:15px;float:left;" onclick="show('tab')" />
         Categories
          <ul id="tab" style="list-style:none;display:none"> 
            <xsl:for-each select="/sr:Categories/sr:Category">
             <li>
              <xsl:value-of select="sr:CategoryName"/>
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