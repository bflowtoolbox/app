<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
     xmlns:epml="http://www.epml.de"
     xmlns:xs="http://www.w3.org/2001/XMLSchema"
     xmlns:epc="org.bflow.toolbox.epc"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation"
     xmlns:xmi="http://www.omg.org/XMI"

 
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
     xmlns:dia="http://www.lysator.liu.se/~alla/dia/">

<xsl:output method="xml" indent="yes"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/">
<epml:epml xmlns:epml="http://www.epml.de" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:epc="org.bflow.toolbox.epc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation" xmlns:xmi="http://www.omg.org/XMI">
  <coordinates xOrigin="leftToRight" yOrigin="topToBottom"/>

     <xsl:apply-templates select='*//dia:layer'/>

</epml:epml>
</xsl:template>

<xsl:template match="dia:layer">
    <!-- Standard size for connectors -->
    <xsl:variable name="kgroesse">
        <xsl:text>30</xsl:text>
    </xsl:variable>

  <definitions>
     <definition xmlns:addon="http://org.bflow.addon" defId="1">
  </definition>
  
  <xsl:for-each select="dia:object">
      <definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>
  </xsl:for-each>
  </definitions>

  <directory name="Root">
    <epc epcId="1" name="imported-from-dia" IdBflow="1"> 
     <xsl:for-each select="dia:object">
         <!-- <xsl:variable name="varname" select="dia:composite/dia:attribute/dia:string"/> -->
         <xsl:variable name="varname">
             <xsl:for-each select="dia:attribute">
                 <xsl:for-each select="dia:composite/dia:attribute">
                     <xsl:if test="@name='string'">
                         <xsl:value-of select="substring-before( substring-after( dia:string , '#' ), '#' )"/>
                     </xsl:if>
                 </xsl:for-each>
             </xsl:for-each>
	 </xsl:variable> 

         <xsl:variable name="vid" select='@id'/>

         <xsl:variable name="posx">
             <xsl:value-of select="substring-before(dia:attribute[@name='obj_pos']/dia:point/@val,',') *20"/>
         </xsl:variable>
         <xsl:variable name="posy">
             <xsl:value-of select="substring-after(dia:attribute[@name='obj_pos']/dia:point/@val,',') *20"/>
         </xsl:variable>
         <xsl:variable name="height">
             <xsl:value-of select="dia:attribute[@name='elem_height']/dia:real/@val *20"/>
         </xsl:variable>
         <xsl:variable name="width">
             <xsl:value-of select="dia:attribute[@name='elem_width']/dia:real/@val *20"/>
         </xsl:variable>


         <xsl:choose>
             <!-- Elements -->
             <xsl:when test="@type='Flowchart - Diamond' or @type='Flowchart - Preparation'"> <!-- Event -->
               <event id="{@id}" IdBflow="{@id}" defRef="{@id}">
                 <name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="$varname"/></name>
                 <graphics>
                   <!-- <position x="{$posx}" y="{$posy}" height="{$height}" width="{$width}"/> -->
                   <position x="{$posx}" y="{$posy}" height="{$height}" width="{$width}"/>
                 </graphics>
               </event> 
             </xsl:when>
             <xsl:when test="@type='EDPC - Function'" > <!-- Function -->
               <function id="{@id}" IdBflow="{@id}" defRef="{@id}">
                 <graphics>
                   <position x="{$posx}" y="{$posy}" height="{$height}" width="{$width}"/>
                 </graphics>
                 <name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="$varname"/></name>
                </function> 
             </xsl:when>
             <xsl:when test="@type='EDPC - Organisation Unit'"> <!-- Org. Unit -->
               <participant id="{@id}" IdBflow="{@id}" defRef="{@id}">       
                 <graphics>
                   <position x="{$posx}" y="{$posy}" height="{$height}" width="{$width}"/>
                 </graphics>
                 <name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="$varname"/></name>         
               </participant>
             </xsl:when>
             <xsl:when test="@type='Flowchart - Predefined Process'"> <!-- Application -->
               <application id="{@id}" IdBflow="{@id}" defRef="{@id}">       
                 <graphics>
                   <position x="{$posx}" y="{$posy}" height="{$height}" width="{$width}"/>
                 </graphics>
                 <name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="$varname"/></name>         
               </application>
             </xsl:when>
             <!-- Connections (Arcs) -->
             <xsl:when test="@type='Standard - Line'">

                 <xsl:variable name="vfrom" select="dia:connections/dia:connection[@handle='0']/@to" />
                 <xsl:variable name="vto"   select="dia:connections/dia:connection[last()]/@to" />
                 <xsl:variable name="startx" select="substring-before( dia:attribute[@name='conn_endpoints']/dia:point[1]/@val, ',') *20"/>
                 <xsl:variable name="starty" select="substring-after ( dia:attribute[@name='conn_endpoints']/dia:point[1]/@val, ',') *20"/>
                 <xsl:variable name="endx"   select="substring-before( dia:attribute[@name='conn_endpoints']/dia:point[last()]/@val, ',') *20"/>
                 <xsl:variable name="endy"   select="substring-after ( dia:attribute[@name='conn_endpoints']/dia:point[last()]/@val, ',') *20"/>

                 <xsl:variable name="typefrom">
                     <xsl:for-each select="//dia:layer/dia:object">
                         <xsl:if test="@id = $vfrom">
                             <xsl:value-of select="@type"/>
                         </xsl:if>
                     </xsl:for-each>
                 </xsl:variable>
                 
                  <xsl:variable name="typeto">
                      <xsl:for-each select="//dia:layer/dia:object">
                         <xsl:if test="@id = $vto">
                             <xsl:value-of select="@type"/>
                         </xsl:if>
                     </xsl:for-each>
                 </xsl:variable>

                 <xsl:choose>
                     <xsl:when test="$typeto='Flowchart - Predefined Process' or $typefrom='Flowchart - Predefined Process'">
                       <relation id="{@id}" IdBflow="{@id}" from="{$vfrom}" to="{$vto}">
                         <graphics>
                           <position x="{$startx}" y="{$starty}"/>
                           <position x="{$endx}" y="{$endy}"/>
                         </graphics>
                       </relation>
                     </xsl:when>
                     <xsl:when test="$typeto='EDPC - Organisation Unit' or $typefrom='EDPC - Organisation Unit'">
                       <relation id="{@id}" IdBflow="{@id}" from="{$vfrom}" to="{$vto}">
                         <graphics>
                           <position x="{$startx}" y="{$starty}"/>
                           <position x="{$endx}" y="{$endy}"/>
                         </graphics>
                       </relation>
                     </xsl:when>
                     <xsl:otherwise>
                       <arc id="{@id}" IdBflow="{@id}">
                         <flow source="{$vfrom}" target="{$vto}"/>
                         <graphics>
                           <position x="{$startx}" y="{$starty}"/>
                           <position x="{$endx}" y="{$endy}"/>
                         </graphics>
                       </arc>
                     </xsl:otherwise>
                 </xsl:choose>
             </xsl:when>
             <!-- Connectors -->
             <xsl:when test="@type='EDPC - And Operator'">
               <and id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
	             <graphics> 
                   <position x="{$posx}" y="{$posy}" height="{$kgroesse}" width="{$kgroesse}"/> 
                 </graphics> 
               </and>
             </xsl:when>
             <xsl:when test="@type='EDPC - Or Operator'">
               <or id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
                 <graphics> 
                   <position x="{$posx}" y="{$posy}" height="{$kgroesse}" width="{$kgroesse}"/> 
                 </graphics> 
               </or>  
             </xsl:when>
             <xsl:when test="@type='EDPC - XOR Operator'">
               <xor id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
                 <graphics> 
                   <position x="{$posx}" y="{$posy}" height="{$kgroesse}" width="{$kgroesse}"/> 
                 </graphics> 
               </xor>  
             </xsl:when>
      <!--       <xsl:otherwise>
               <elements xmi:type="{concat('no_indentification - ',@type)} " xmi:id="{concat('_',$vid)}" name="{concat('',$varname)}"/>
            </xsl:otherwise>-->
         </xsl:choose>

     </xsl:for-each>
    </epc>
  </directory>
</xsl:template>
</xsl:stylesheet>
