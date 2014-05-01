<?xml version="1.0"?>
<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:epml="http://www.epml.de" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:epc="org.bflow.toolbox.epc" 
	xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation" 
	xmlns:xmi="http://www.omg.org/XMI" 
	xmlns:on="http://icl.com/on" 
	extension-element-prefixes="on">
<!-- angaben ueber das Ausgabe format, indent bedeutet einruecken entsprechend der Hirachie im XML Tree -->
<xsl:output method="xml" indent="yes"/>
<!-- ### TODO ###-->
<!-- ### -->
<!-- ### Anmerkungen ###-->
<!-- ### -->
<xsl:variable name="Modellname">
		<xsl:for-each select="//*[name()='epc']">
			<xsl:value-of select="@name"/>
        </xsl:for-each>
</xsl:variable>
<xsl:variable name="ModellId">
	<xsl:for-each select="//*[name()='epc']">
		<xsl:value-of select="@epcId"/>
    </xsl:for-each>
</xsl:variable> 
<xsl:template match="/">
	<epml:epml>	
		<coordinates yOrigin="topToBottom" xOrigin="leftToRight"/>
		<!-- einfuegen Defintions part-->
		<xsl:copy-of select="epml:epml/Temp/*"/>
		<directory name="Root">
			<epc>
				<!-- anlegen Modell Id-->	
				<xsl:attribute name="epcId">1</xsl:attribute>
				<!-- Der name wird ausgelesen aus der Diagramm information, die Ausgabe muesste  so funktionieren -->
				<xsl:attribute name="name"><xsl:value-of select="$Modellname"/></xsl:attribute>
				<!-- anlegen neues Attributes, mit alten id-->
				<xsl:attribute name="IdBflow"><xsl:value-of select="$ModellId"/></xsl:attribute>
				<!-- Ausgabe aller bereits erzeugten Elemente ausser Arcs und Relations-->
				<xsl:copy-of select="epml:epml/directory/epc/*"/>
				<!-- bearbeiten der Temporaeren Arcs und erzeugen der Finalen-->
				<xsl:for-each select="//*[name()='arcTemp']">	
					<xsl:variable name="ElementID">
						<xsl:value-of select="@id"/>
					</xsl:variable>
					<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@IdBflow"/>
					</xsl:variable>
					<!-- Einlesen Bflow Id, fuer spaeteren Vergleich-->
					<xsl:variable name="ArcFrom">
						<xsl:value-of select="flow/@source"/>
					</xsl:variable>
					<xsl:variable name="ArcTO">
						<xsl:value-of select="flow/@target"/>
					</xsl:variable>						
					<arc>
						<xsl:attribute name="id"><xsl:value-of select="$ElementID"/></xsl:attribute>
						<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>	
						<xsl:copy-of select="description"/>
						<!-- Vergleich aller Elemente ob diesse die entsprechende BflowId haben. Zuordnunung ist bijektiv-->
						<flow>	
							<xsl:call-template name="ArcAndRelation">
								<xsl:with-param name="ArcFrom">
									<xsl:value-of select="$ArcFrom"/>
								</xsl:with-param>
								<xsl:with-param name="ArcTO">
									<xsl:value-of select="$ArcTO"/>
								</xsl:with-param>
							</xsl:call-template>	
						</flow>	
						<graphics>
							<!-- Ausgabe der Position -->
							<xsl:copy-of select="graphics/position"/>
						</graphics>
					</arc>						
				</xsl:for-each>
				<!-- Anlegen Relation-->
				<xsl:for-each select="//*[name()='relation']">	
					<xsl:variable name="ElementID">
						<xsl:value-of select="@id"/>
					</xsl:variable>
					<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@IdBflow"/>
					</xsl:variable>
					<!-- Einlesen Bflow Id, fuer spaeteren Vergleich-->
					<xsl:variable name="RelFrom">
						<xsl:value-of select="@from"/>
					</xsl:variable>
					<xsl:variable name="RelTO">
						<xsl:value-of select="@to"/>
					</xsl:variable>		
					<relation>
						<xsl:attribute name="id"><xsl:value-of select="$ElementID"/></xsl:attribute>
						<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>	
						<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/></xsl:attribute>	
						<xsl:call-template name="ArcRelation">
							<xsl:with-param name="ArcFrom">
								<xsl:value-of select="$RelFrom"/>
							</xsl:with-param>
							<xsl:with-param name="ArcTO">
								<xsl:value-of select="$RelTO"/>
							</xsl:with-param>
						</xsl:call-template>
						<xsl:copy-of select="description"/>
						<graphics>
							<!-- Ausgabe der Position -->
							<xsl:copy-of select="graphics/position"/>
						</graphics>
					</relation>
				</xsl:for-each>
			</epc>
		</directory>
	</epml:epml>
</xsl:template>

<xsl:template name="ArcAndRelation">
	<xsl:param name="ArcFrom"/>
	<xsl:param name="ArcTO"/>
<!-- 1. Event, kann weiter in Templates aufgeteilt werden, aber keine wirklicher ersparnis-->
	<xsl:for-each select="//*[name()='event'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='event'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>	
	<!-- 2. function-->
	<xsl:for-each select="//*[name()='function'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='function'][@IdBflow=$ArcTO]">
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>	
	<!-- 3. Konnektor and-->
	<xsl:for-each select="//*[name()='and'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='and'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 4. Konnektor or -->
	<xsl:for-each select="//*[name()='or'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='or'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 5. Konnektor xor -->
	<xsl:for-each select="//*[name()='xor'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='xor'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 6. application -->
	<xsl:for-each select="//*[name()='application'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='application'][@IdBflow=$ArcTO]">
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 7. participant -->
	<xsl:for-each select="//*[name()='participant'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='participant'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 8. dataField -->
	<xsl:for-each select="//*[name()='dataField'][@IdBflow=$ArcFrom]">
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='dataField'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 9. processInterface -->
	<xsl:for-each select="//*[name()='processInterface'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="source"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='processInterface'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="target"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
</xsl:template>
<xsl:template name="ArcRelation">
	<xsl:param name="ArcFrom"/>
	<xsl:param name="ArcTO"/>
<!-- 1. Event, kann weiter in Templates aufgeteilt werden, aber keine wirklicher ersparnis-->
	<xsl:for-each select="//*[name()='event'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='event'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>	
	<!-- 2. function-->
	<xsl:for-each select="//*[name()='function'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='function'][@IdBflow=$ArcTO]">
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>	
	<!-- 3. Konnektor and-->
	<xsl:for-each select="//*[name()='and'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='and'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 4. Konnektor or -->
	<xsl:for-each select="//*[name()='or'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='or'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 5. Konnektor xor -->
	<xsl:for-each select="//*[name()='xor'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='xor'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 6. application -->
	<xsl:for-each select="//*[name()='application'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='application'][@IdBflow=$ArcTO]">
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 7. participant -->
	<xsl:for-each select="//*[name()='participant'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='participant'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 8. dataField -->
	<xsl:for-each select="//*[name()='dataField'][@IdBflow=$ArcFrom]">
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='dataField'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
	<!-- 9. processInterface -->
	<xsl:for-each select="//*[name()='processInterface'][@IdBflow=$ArcFrom]">	
		<xsl:variable name="ArcFromId">
			<xsl:value-of select="@id"/>
		</xsl:variable>
		<xsl:attribute name="from"><xsl:value-of select="$ArcFromId"/></xsl:attribute>		
	</xsl:for-each>	
	<!-- Kanten-Target-->
	<xsl:for-each select="//*[name()='processInterface'][@IdBflow=$ArcTO]">	
		<xsl:variable name="ArcTOId">
			<xsl:value-of select="@id"/>
		</xsl:variable>	
		<xsl:attribute name="to"><xsl:value-of select="$ArcTOId"/></xsl:attribute>
	</xsl:for-each>
</xsl:template>
</xsl:stylesheet>