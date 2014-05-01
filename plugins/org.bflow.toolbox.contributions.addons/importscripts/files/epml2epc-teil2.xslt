<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="2.0" 
	xmlns:epml="http://www.epml.de" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:epc="org.bflow.toolbox.epc" 
	xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation" 
	xmlns:xmi="http://www.omg.org/XMI" 
	xmlns:saxon="http://saxon.sf.net/" 
	extension-element-prefixes="saxon">
<!-- Angaben ueber die Ausgabe-->
<xsl:output method="xml" indent="yes" />
<!-- ### TODO ###-->
<!-- Unterscheiden sich in der y Koordinate immer um 125 x kein Untersxchied-->
<!-- Parameter fuer anschalten der Description, an = true, aus = false-->
<xsl:param name="Description"/>
<xsl:variable name="AnzahlElemente" saxon:assignable="yes"/>
<xsl:variable name="XMId" saxon:assignable="yes"/>
<xsl:variable name="XMElement" saxon:assignable="yes"/>
<xsl:variable name="XmName" saxon:assignable="yes"/>

<xsl:variable name="x1" saxon:assignable="yes"/>
<xsl:variable name="x2" saxon:assignable="yes"/>
<xsl:variable name="y1" saxon:assignable="yes"/>
<xsl:variable name="y2" saxon:assignable="yes"/>

<!-- ### -->
<xsl:template match="/">
	<saxon:assign name="AnzahlElemente" select="xmi:XMI/Anzahl/@Anzahl"/>	
	<xmi:XMI xmi:version="2.0"  xmlns:epc="org.bflow.toolbox.epc" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation">
		<xsl:copy-of select="xmi:XMI/epc:Epc"/>
		<!-- Anlegen neuer Teil-->
		<xsl:for-each select="//*[name()='test']">
			<saxon:assign name="XMId" select="@xmi:id"/>		
			<saxon:assign name="XMElement" select="@element"/>		
			<saxon:assign name="XmName" select="@name"/>		
		</xsl:for-each>
		<notation:Diagram>
		<xsl:attribute name="xmi:id"><xsl:value-of select="$XMId"/></xsl:attribute>
		<xsl:attribute name="type">Epc</xsl:attribute>
		<!-- ist die EPC id == Modell ID -->
		<xsl:attribute name="element"><xsl:value-of select="$XMElement"/></xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="$XmName"/></xsl:attribute>
		<xsl:attribute name="measurementUnit">Pixel</xsl:attribute>
			<xsl:copy-of select="xmi:XMI/notation:Diagram/children"/>
			<!-- anlegen der children Desription-->
			<xsl:if test="$Description='true'">
				<xsl:for-each select="//*[name()='TempChildren']">
					<children>
					<xsl:if test="@description='True'">
						<xsl:attribute name="xmi:type"><xsl:value-of select="@xmi:type"/></xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="@xmi:id"/></xsl:attribute>
						<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
						<children>
							<xsl:attribute name="xmi:type"><xsl:value-of select="children/@xmi:type"/></xsl:attribute>
							<xsl:attribute name="xmi:id"><xsl:value-of select="children/@xmi:id"/></xsl:attribute>
							<xsl:attribute name="type"><xsl:value-of select="children/@type"/></xsl:attribute>
							<element>
								<xsl:attribute name="xsi:nil"><xsl:value-of select="children/element/@xsi:nil"/></xsl:attribute>
							</element>
						</children>
						<children>
							<xsl:attribute name="xmi:type"><xsl:value-of select="children2/@xmi:type"/></xsl:attribute>
							<xsl:attribute name="xmi:id"><xsl:value-of select="children2/@xmi:id"/></xsl:attribute>
							<xsl:attribute name="type"><xsl:value-of select="children2/@type"/></xsl:attribute>
							<element>
								<xsl:attribute name="xsi:nil"><xsl:value-of select="children2/element/@xsi:nil"/></xsl:attribute>
							</element>
						</children>
						<styles>
							<xsl:attribute name="xmi:type"><xsl:value-of select="styles/@xmi:type"/></xsl:attribute>
							<xsl:attribute name="xmi:id"><xsl:value-of select="styles/@xmi:id"/></xsl:attribute>
							<xsl:attribute name="description"><xsl:value-of select="styles/@description"/></xsl:attribute>
							<xsl:attribute name="fillColor"><xsl:value-of select="styles/@fillColor"/></xsl:attribute>
							<xsl:attribute name="lineColor"><xsl:value-of select="styles/@lineColor"/></xsl:attribute>
							<xsl:attribute name="lineWidth"><xsl:value-of select="styles/@lineWidth"/></xsl:attribute>
						</styles>
						<styles>
							<xsl:attribute name="xmi:type"><xsl:value-of select="styles2/@xmi:type"/></xsl:attribute>
							<xsl:attribute name="xmi:id"><xsl:value-of select="styles2/@xmi:id"/></xsl:attribute>
						</styles>			
						<styles>
							<xsl:attribute name="xmi:type"><xsl:value-of select="styles3/@xmi:type"/></xsl:attribute>
							<xsl:attribute name="xmi:id"><xsl:value-of select="styles3/@xmi:id"/></xsl:attribute>
						</styles>
						<element>
							<xsl:attribute name="xsi:nil"><xsl:value-of select="element/@xsi:nil"/></xsl:attribute>
						</element>
						<layoutConstraint>
							<xsl:attribute name="xmi:type"><xsl:value-of select="layoutConstraint/@xmi:type"/></xsl:attribute>
							<xsl:attribute name="xmi:id"><xsl:value-of select="layoutConstraint/@xmi:id"/></xsl:attribute>						
							<xsl:attribute name="x"><xsl:value-of select="layoutConstraint/@x"/></xsl:attribute>
							<xsl:attribute name="y"><xsl:value-of select="layoutConstraint/@y"/></xsl:attribute>
							<xsl:attribute name="width"><xsl:value-of select="layoutConstraint/@width"/></xsl:attribute>
							<xsl:attribute name="height"><xsl:value-of select="layoutConstraint/@height"/></xsl:attribute>
						</layoutConstraint>
					</xsl:if>
					</children>
				</xsl:for-each>
			</xsl:if>
			<xsl:copy-of select="xmi:XMI/notation:Diagram/styles"/>
			<!-- Relations hat identische Struktur daher ausgeben in Template, hat Atttyp 4002-->
			<xsl:for-each select="//*[name()='edgesRelation']">
				<xsl:variable name="ElementID">
					<xsl:value-of select="@xmi:id"/>
				</xsl:variable>
				<xsl:variable name="ElementElementId">
					<xsl:value-of select="@element"/>
				</xsl:variable>
				<xsl:variable name="ArcFrom">
					<xsl:value-of select="@source"/>
				</xsl:variable>
				<xsl:variable name="ArcTo">
					<xsl:value-of select="@target"/>
				</xsl:variable>	
				<xsl:call-template name="Edges">
					<xsl:with-param name="ElementID"><xsl:value-of select="$ElementID"/></xsl:with-param>
					<xsl:with-param name="ElementElementId"><xsl:value-of select="$ElementElementId"/></xsl:with-param>
					<xsl:with-param name="ArcFrom"><xsl:value-of select="$ArcFrom"/></xsl:with-param>
					<xsl:with-param name="ArcTo"><xsl:value-of select="$ArcTo"/></xsl:with-param>
					<xsl:with-param name="Type">4002</xsl:with-param>
				</xsl:call-template>	
			</xsl:for-each>
			<!-- Ausgabe der Arcs-->
			<xsl:for-each select="//*[name()='edgesArc']">
			<!-- Variablen Deklaration-->
				<xsl:variable name="ElementID">
					<xsl:value-of select="@xmi:id"/>
				</xsl:variable>
				<xsl:variable name="ElementElementId">
					<xsl:value-of select="@element"/>
				</xsl:variable>
				<xsl:variable name="ArcFrom">
					<xsl:value-of select="@source"/>
				</xsl:variable>
				<xsl:variable name="ArcTo">
					<xsl:value-of select="@target"/>
				</xsl:variable>	
				<xsl:call-template name="Edges">
					<xsl:with-param name="ElementID"><xsl:value-of select="$ElementID"/></xsl:with-param>
					<xsl:with-param name="ElementElementId"><xsl:value-of select="$ElementElementId"/></xsl:with-param>
					<xsl:with-param name="ArcFrom"><xsl:value-of select="$ArcFrom"/></xsl:with-param>
					<xsl:with-param name="ArcTo"><xsl:value-of select="$ArcTo"/></xsl:with-param>
					<xsl:with-param name="Type">4001</xsl:with-param>
				</xsl:call-template>
			</xsl:for-each>
			<!-- TODO EdgeDesription aus dem ersten Skript entfernen--> 
			<xsl:if test="$Description='true'">
				<xsl:for-each select="//*[name()='TempChildren']">
					<xsl:variable name="ArcTo">
						<xsl:value-of select="@id"/>
					</xsl:variable>	
					<xsl:call-template name="EdgesDes">
						<xsl:with-param name="ArcTo"><xsl:value-of select="$ArcTo"/></xsl:with-param>
					</xsl:call-template>	
					<saxon:assign name="AnzahlElemente" select="$AnzahlElemente+5"/>
				</xsl:for-each>
			</xsl:if>
		</notation:Diagram>
	</xmi:XMI>
</xsl:template>

<xsl:template name="EdgesDes">
	<xsl:param name="ArcTo"/>
	<edges>
		<xsl:attribute name="xmi:type">notation:Edge</xsl:attribute>
		<xsl:attribute name="xmi:id"><xsl:value-of select="$AnzahlElemente"/></xsl:attribute>
		<xsl:attribute name="type">NoteAttachment</xsl:attribute>
		<xsl:for-each select="//*[name()='TempChildren'][@id=$ArcTo]">
			<xsl:attribute name="source"><xsl:value-of select="@xmi:id"/></xsl:attribute>	
		</xsl:for-each>
		<xsl:for-each select="//*[name()='children'][@element=$ArcTo]">
			<xsl:variable name="ArcToId">
				<xsl:value-of select="@xmi:id"/>
			</xsl:variable>
			<xsl:attribute name="target"><xsl:value-of select="$ArcToId"/></xsl:attribute>	
		</xsl:for-each>
		<saxon:assign name="AnzahlElemente" select="$AnzahlElemente+1"/>
		<styles>
			<xsl:attribute name="xmi:type">notation:ConnectorStyle</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="$AnzahlElemente"/></xsl:attribute>
			<xsl:attribute name="lineWidth">1</xsl:attribute>
			<saxon:assign name="AnzahlElemente" select="$AnzahlElemente+1"/>
		</styles>
		<styles>
			<xsl:attribute name="xmi:type">notation:ArrowStyle</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="$AnzahlElemente"/></xsl:attribute>
			<saxon:assign name="AnzahlElemente" select="$AnzahlElemente+1"/>
		</styles>
		<styles>
			<xsl:attribute name="xmi:type">notation:LineTypeStyle</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="$AnzahlElemente"/></xsl:attribute>	
			<saxon:assign name="AnzahlElemente" select="$AnzahlElemente+1"/>
		</styles>
		<element>
			<xsl:attribute name="xsi:nil">true</xsl:attribute>
		</element>
		<bendpoints>
			<xsl:attribute name="xmi:type">notation:RelativeBendpoints</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="$AnzahlElemente"/></xsl:attribute>
			<xsl:attribute name="points">[0, 0, 0, 0]$[0, 0, 0, 0]</xsl:attribute>
			<saxon:assign name="AnzahlElemente" select="$AnzahlElemente+1"/>	
		</bendpoints>		
	</edges>
</xsl:template>

<xsl:template name="Edges">
	<xsl:param name="Type"/>
	<xsl:param name="ElementID"/>
	<xsl:param name="ElementElementId"/>
	<xsl:param name="ArcFrom"/>
	<xsl:param name="ArcTo"/>
	<edges>
		<xsl:attribute name="xmi:type">notation:Edge</xsl:attribute>
		<xsl:attribute name="xmi:id"><xsl:value-of select="$ElementID"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="$Type"/></xsl:attribute>
		<!-- Element erhaelt die Id analog wie oben Auswertung, der bflowId-->
		<xsl:attribute name="element"><xsl:value-of select="$ElementElementId"/></xsl:attribute>		
		<xsl:for-each select="//*[name()='children'][@element=$ArcFrom]">
			<xsl:variable name="ArcFromId">
				<xsl:value-of select="@xmi:id"/>
			</xsl:variable>
			<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>
			<saxon:assign name="x1" select="layoutConstraint/@x"/>		
			<saxon:assign name="y1" select="layoutConstraint/@y"/>		
		</xsl:for-each>
		<xsl:for-each select="//*[name()='children'][@element=$ArcTo]">
			<xsl:variable name="ArcToId">
				<xsl:value-of select="@xmi:id"/>
			</xsl:variable>
			<xsl:attribute name="target"><xsl:value-of select="$ArcToId"/></xsl:attribute>	
			<saxon:assign name="x2" select="layoutConstraint/@x"/>		
			<saxon:assign name="y2" select="layoutConstraint/@y"/>	
		</xsl:for-each>
		<styles>
			<xsl:attribute name="xmi:type">notation:RoutingStyle</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="styles/@xmi:id"/></xsl:attribute>
			<xsl:attribute name="routing">Rectilinear</xsl:attribute>
		</styles>
		<styles>
			<xsl:attribute name="xmi:type">notation:FontStyle</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="styles1/@xmi:id"/></xsl:attribute>
		</styles>
		<bendpoints>
			<xsl:attribute name="xmi:type">notation:RelativeBendpoints</xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="bendpoints/@xmi:id"/></xsl:attribute>
			<xsl:attribute name="points">[0, 0, 0, 0]$[0, 0, 0, 0]</xsl:attribute>
		</bendpoints>
	</edges>
</xsl:template>

</xsl:stylesheet>