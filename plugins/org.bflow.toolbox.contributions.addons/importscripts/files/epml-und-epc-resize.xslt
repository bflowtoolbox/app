<?xml version="1.0"?>
<xsl:stylesheet version="2.0" 
	xmlns:epml="http://www.epml.de" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xmi="http://www.omg.org/XMI"  
	xmlns:xs="http://www.w3.org/2001/XMLSchema" 
	xmlns:saxon="http://saxon.sf.net" 
	extension-element-prefixes="saxon" 
	xmlns:epc="org.bflow.toolbox.epc" 
	xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation">
<!--### TODO ###-->
<!-- TODO can eleminate variables-->
<!-- TODO anschauen ob Description bei Groessenenderung epc verlohren geht, bzw ersteinmal einbauen-->
<!-- letzte Version ohne Streckung ist die Version 5, wird besser empfohlener Streckungsfaktro 3-->
<!-- ### ###-->
<xsl:param name="Multiplikator"/>
<xsl:param name="Streckung"/>
<xsl:param name="MultiKonnektor"/>
<!-- Proberty of the output-->
<xsl:output method="xml" indent="yes"/>

<!-- Verarbeitung der epml Datei-->
<xsl:template match="epml:epml">
<epml:epml xmlns:epml='http://www.epml.de'>
<coordinates xOrigin="leftToRight" yOrigin="topToBottom" />	
	<!-- Ausgabe der Definitions-->
	<definitions>
		<xsl:copy-of select="definitions/*"/>
	</definitions>
	<directory>
		<xsl:attribute name="name"><xsl:value-of select="directory/@name"/></xsl:attribute>
		<!-- anlegen epc-->
		<epc>
			<xsl:attribute name="epcId"><xsl:value-of select="directory/epc/@epcId"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="directory/epc/@name"/></xsl:attribute>
			<xsl:attribute name="IdBflow"><xsl:value-of select="directory/epc/@IdBflow"/></xsl:attribute>
			<!-- 1. Event-->
			<xsl:for-each select="//*[name()='event']">	
				<event>
					<xsl:call-template name="CreateEPML"/>
				</event>
			</xsl:for-each>	
			<!-- 2. Funktion-->
			<xsl:for-each select="//*[name()='function']">	
				<function>
					<xsl:call-template name="CreateEPML"/>
				</function>
			</xsl:for-each>	
			<!-- 3. Partizipant-->
			<xsl:for-each select="//*[name()='participant']">	
				<participant>
					<xsl:call-template name="CreateEPML"/>
				</participant>
			</xsl:for-each>	
			<!-- 4. Data Field-->
			<xsl:for-each select="//*[name()='dataField']">	
				<dataField>
					<xsl:call-template name="CreateEPML"/>
				</dataField>
			</xsl:for-each>	
			<!-- 5. application-->
			<xsl:for-each select="//*[name()='application']">	
				<application>
					<xsl:call-template name="CreateEPML"/>
				</application>
			</xsl:for-each>	
			<!-- 6. or-->
			<xsl:for-each select="//*[name()='or']">	
				<or>
					<xsl:call-template name="CreateEPMLKonnektor"/>
				</or>
			</xsl:for-each>	
			<!-- 7. xor-->
			<xsl:for-each select="//*[name()='xor']">	
				<xor>
					<xsl:call-template name="CreateEPMLKonnektor"/>
				</xor>
			</xsl:for-each>	
			<!-- 8. xor-->
			<xsl:for-each select="//*[name()='and']">	
				<and>
					<xsl:call-template name="CreateEPMLKonnektor"/>
				</and>
			</xsl:for-each>				
			<!-- 11. Process Interface-->
			<xsl:for-each select="//*[name()='processInterface']">	
				<processInterface>
					<xsl:call-template name="CreateEPML"/>
				</processInterface>
			</xsl:for-each>	
			<!-- 9. Arcs, koennen direkt uebernommen werden-->
			<xsl:for-each select="//*[name()='arc']">	
				<arc>
					<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
					<xsl:attribute name="IdBflow"><xsl:value-of select="@IdBflow"/></xsl:attribute>	
					<xsl:variable name="ArcFrom">
						<xsl:value-of select="flow/@source"/>
					</xsl:variable>
					<xsl:variable name="ArcTO">
						<xsl:value-of select="flow/@target"/>
					</xsl:variable>
					<xsl:copy-of select="description"/>
					<flow>
						<xsl:attribute name="source"><xsl:value-of select="$ArcFrom"/></xsl:attribute>
						<xsl:attribute name="target"><xsl:value-of select="$ArcTO"/></xsl:attribute>	
					</flow>
					<graphics>	
						<!-- einlesen Koordinaten Ausgangknoten--> 
						<xsl:for-each select="//*[@id=$ArcFrom]">								
							<xsl:variable name="height">
								<xsl:value-of select="graphics/position/@height"/>
							</xsl:variable>
							<xsl:variable name="width">
								<xsl:value-of select="graphics/position/@width"/>
							</xsl:variable>		
							<position>
								<xsl:attribute name="x"><xsl:value-of select="round(graphics/position/@x*$Streckung+$width*0.5*$Multiplikator) div 1"/></xsl:attribute>
								<xsl:attribute name="y"><xsl:value-of select="round(graphics/position/@y*$Streckung+$height*$Multiplikator) div 1"/></xsl:attribute>
							</position>				
						</xsl:for-each>	
						<!-- Zielknoten, all epml Elements have the same structur--> 
						<xsl:for-each select="//*[@id=$ArcTO]">		
							<xsl:variable name="height">
								<xsl:value-of select="graphics/position/@height"/>
							</xsl:variable>
							<xsl:variable name="width">
								<xsl:value-of select="graphics/position//@width"/>
							</xsl:variable>
							<position>
								<xsl:attribute name="x"><xsl:value-of select="round(graphics/position//@x*$Streckung+$width*0.5*$Multiplikator) div 1"/></xsl:attribute>
								<xsl:attribute name="y"><xsl:value-of select="round(graphics/position/@y*$Streckung) div 1"/></xsl:attribute>
							</position>											
						</xsl:for-each>		
					</graphics>	
				</arc>			
			</xsl:for-each>
			<!-- 10. Relation-->
			<xsl:for-each select="//*[name()='relation']">	
				<relation>
					<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
					<xsl:attribute name="IdBflow"><xsl:value-of select="@IdBflow"/></xsl:attribute>
					<xsl:attribute name="from"><xsl:value-of select="@from"/></xsl:attribute>
					<xsl:attribute name="to"><xsl:value-of select="@to"/></xsl:attribute>
					<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/></xsl:attribute>
					<xsl:variable name="ArcFrom">
						<xsl:value-of select="@from"/>
					</xsl:variable>
					<xsl:variable name="ArcTO">
						<xsl:value-of select="@to"/>
					</xsl:variable>	
					<xsl:copy-of select="description"/>
					<!-- einlesen Koordinaten Ausgangknoten--> 
					<graphics>
						<xsl:for-each select="//*[@id=$ArcFrom]">								
							<xsl:variable name="height">
								<xsl:value-of select="graphics/position/@height"/>
							</xsl:variable>
							<xsl:variable name="width">
								<xsl:value-of select="graphics/position/@width"/>
							</xsl:variable>		
							<position>
								<xsl:attribute name="x"><xsl:value-of select="round(graphics/position/@x*$Streckung+$width*0.5*$Multiplikator) div 1"/></xsl:attribute>
								<xsl:attribute name="y"><xsl:value-of select="round(graphics/position/@y*$Streckung+$height*$Multiplikator) div 1"/></xsl:attribute>
							</position>				
						</xsl:for-each>	
						<!-- Zielknoten, all epml Elements have the same structur--> 
						<xsl:for-each select="//*[@id=$ArcTO]">		
							<xsl:variable name="height">
								<xsl:value-of select="graphics/position/@height"/>
							</xsl:variable>
							<xsl:variable name="width">
								<xsl:value-of select="graphics/position//@width"/>
							</xsl:variable>
							<position>
								<xsl:attribute name="x"><xsl:value-of select="round(graphics/position//@x*$Streckung+$width*0.5*$Multiplikator) div 1"/></xsl:attribute>
								<xsl:attribute name="y"><xsl:value-of select="round(graphics/position/@y*$Streckung) div 1"/></xsl:attribute>
							</position>											
						</xsl:for-each>	
					</graphics>	
				</relation>
			</xsl:for-each>
		</epc>			
	</directory>
</epml:epml>	
</xsl:template>
<!-- Verarbeitung der epc Datei-->
<xsl:template match="xmi:XMI">
	<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:epc="org.bflow.toolbox.epc" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation">
		<xsl:copy-of select="epc:Epc"/>
		<notation:Diagram>
		<xsl:attribute name="xmi:id"><xsl:value-of select="notation:Diagram/@xmi:id"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="notation:Diagram/@type"/></xsl:attribute>
		<xsl:attribute name="element"><xsl:value-of select="notation:Diagram/@element"/></xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="notation:Diagram/@name"/></xsl:attribute>
		<xsl:attribute name="measurementUnit"><xsl:value-of select="notation:Diagram/@measurementUnit"/></xsl:attribute>
		<!-- 1. Event-->
		<xsl:for-each select="//*[name()='children'][@type='2006']">
			<xsl:call-template name="CreateCildren"/>
		</xsl:for-each>	
		<!-- 2. function-->
		<xsl:for-each select="//*[name()='children'][@type='2007']">
			<xsl:call-template name="CreateCildren"/>
		</xsl:for-each>	
		<!-- 3. Application-->
		<xsl:for-each select="//*[name()='children'][@type='2004']">
			<xsl:call-template name="CreateCildren"/>
		</xsl:for-each>
		<!-- 4. Participation-->
		<xsl:for-each select="//*[name()='children'][@type='2002']">
			<xsl:call-template name="CreateCildren"/>
		</xsl:for-each>
		<!-- 5. Data Field-->
		<xsl:for-each select="//*[name()='children'][@type='2016']">
			<xsl:call-template name="CreateCildren"/>
		</xsl:for-each>
		<!-- 6. Process Interface-->
		<xsl:for-each select="//*[name()='children'][@type='2005']">
			<xsl:call-template name="CreateCildren"/>
		</xsl:for-each>
		<!-- 7. Konnektoren werden nur copiert, TODO spaeter noch eventuell Multiplikator-->
		<xsl:for-each select="//*[name()='children'][@type='2003']">
			<xsl:call-template name="CreateKonnektor"/>
		</xsl:for-each>
		<!--8. Konnektor -->
		<xsl:for-each select="//*[name()='children'][@type='2001']">
			<xsl:call-template name="CreateKonnektor"/>
		</xsl:for-each>
		<!-- 9. Konnektor -->
		<xsl:for-each select="//*[name()='children'][@type='2008']">
			<xsl:call-template name="CreateKonnektor"/>
		</xsl:for-each>
		<!-- 10. Description -->
		<xsl:for-each select="//*[name()='children'][@type='Note']">
			<children>
				<xsl:attribute name="xmi:type"><xsl:value-of select="@xmi:type"/></xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="@xmi:id"/></xsl:attribute>
				<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
				<!-- Unterknoten, TODO aufpassen hier gibt es jeweils mehrere und noch Unterknoten-->
				<xsl:copy-of select="children"/>
				<xsl:copy-of select="styles"/>
				<xsl:copy-of select="element"/>
				<layoutConstraint>
					<xsl:attribute name="xmi:type"><xsl:value-of select="layoutConstraint/@xmi:type"/></xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="layoutConstraint/@xmi:id"/></xsl:attribute>						
					<xsl:attribute name="x"><xsl:value-of select="round(layoutConstraint/@x*$Streckung) div 1"/></xsl:attribute>
					<xsl:attribute name="y"><xsl:value-of select="round(layoutConstraint/@y*$Streckung) div 1"/></xsl:attribute>
					<xsl:if test="layoutConstraint/@width!=''">
						<xsl:attribute name="width"><xsl:value-of select="round(layoutConstraint/@width*$Multiplikator) div 1"/></xsl:attribute>
						<xsl:attribute name="height"><xsl:value-of select="round(layoutConstraint/@height*$Multiplikator) div 1"/></xsl:attribute>
					</xsl:if>
				</layoutConstraint>
			</children>
		</xsl:for-each>
		<!-- edges und style koennen uebernommern werden, 10. und 11.-->
		<!-- <xsl:copy-of select="notation:Diagram/styles"/>-->
		<xsl:for-each select="notation:Diagram/styles">
			<xsl:call-template name="resizePage"/>
		</xsl:for-each>
		<xsl:copy-of select="notation:Diagram/edges"/>
	</notation:Diagram>
	</xmi:XMI>
</xsl:template>

<xsl:template name="resizePage">
	<styles>
	<xsl:copy-of select="@xmi:type"/>
	<xsl:copy-of select="@xmi:id"/>
	<xsl:attribute name="pageWidth" select="round(@pageWidth*$Streckung)"/>
	<xsl:attribute name="pageHeight" select="round(@pageHeight*$Streckung)"/>
	<xsl:copy-of select="@description"/>
	</styles>
</xsl:template>

<!-- erzeugen der neuen Cildren Struktur, mit Multiplikator der heigh und width, EPC-->
<xsl:template name="CreateCildren">
	<children>
		<xsl:attribute name="xmi:type"><xsl:value-of select="@xmi:type"/></xsl:attribute>
		<xsl:attribute name="xmi:id"><xsl:value-of select="@xmi:id"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:attribute name="element"><xsl:value-of select="@element"/></xsl:attribute>
		<!-- Unterknoten-->
		<xsl:copy-of select="children"/>
		<xsl:copy-of select="styles"/>
		<layoutConstraint>
			<xsl:attribute name="xmi:type"><xsl:value-of select="layoutConstraint/@xmi:type"/></xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="layoutConstraint/@xmi:id"/></xsl:attribute>						
			<xsl:attribute name="x"><xsl:value-of select="round(layoutConstraint/@x*$Streckung) div 1"/></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="round(layoutConstraint/@y*$Streckung) div 1"/></xsl:attribute>
			<xsl:if test="layoutConstraint/@width!=''">
				<xsl:attribute name="width"><xsl:value-of select="round(layoutConstraint/@width*$Multiplikator) div 1"/></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="round(layoutConstraint/@height*$Multiplikator) div 1"/></xsl:attribute>
			</xsl:if>
		</layoutConstraint>
	</children>
</xsl:template>
<!-- create childstructur Konnektor-->
<xsl:template name="CreateKonnektor">
	<children>
		<xsl:attribute name="xmi:type"><xsl:value-of select="@xmi:type"/></xsl:attribute>
		<xsl:attribute name="xmi:id"><xsl:value-of select="@xmi:id"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:attribute name="element"><xsl:value-of select="@element"/></xsl:attribute>
		<xsl:copy-of select="styles"/>
		<layoutConstraint>
			<xsl:attribute name="xmi:type"><xsl:value-of select="layoutConstraint/@xmi:type"/></xsl:attribute>
			<xsl:attribute name="xmi:id"><xsl:value-of select="layoutConstraint/@xmi:id"/></xsl:attribute>						
			<xsl:attribute name="x"><xsl:value-of select="round(layoutConstraint/@x*$Streckung) div 1"/></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="round(layoutConstraint/@y*$Streckung) div 1"/></xsl:attribute>
			<xsl:if test="layoutConstraint/@width!=''">
				<xsl:attribute name="width"><xsl:value-of select="round(layoutConstraint/@width*$MultiKonnektor) div 1"/></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="round(layoutConstraint/@height*$MultiKonnektor) div 1"/></xsl:attribute>
			</xsl:if>
		</layoutConstraint>
	</children>	
</xsl:template>
<!-- create graphics, mit Multiplikator fuer heigh und width, Epml-->
<xsl:template name="CreateEPML">
	<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>	
	<xsl:attribute name="IdBflow"><xsl:value-of select="@IdBflow"/></xsl:attribute>
	<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/></xsl:attribute>
	<graphics>
		<position>
			<xsl:attribute name="x"><xsl:value-of select="round(graphics/position/@x*$Streckung) div 1"/></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="round(graphics/position/@y*$Streckung) div 1"/></xsl:attribute>	
			<xsl:attribute name="height"><xsl:value-of select="round(graphics/position/@height*$Multiplikator) div 1"/></xsl:attribute>
			<xsl:attribute name="width"><xsl:value-of select="round(graphics/position/@width*$Multiplikator) div 1"/></xsl:attribute>
		</position>
	</graphics>
	<xsl:copy-of select="name"/>
	<xsl:copy-of select="description"/>
</xsl:template>
<!-- create Konnektor fuer die Streckung-->
<xsl:template name="CreateEPMLKonnektor">
	<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>	
	<xsl:attribute name="IdBflow"><xsl:value-of select="@IdBflow"/></xsl:attribute>
	<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/></xsl:attribute>
	<graphics>
		<position>
			<xsl:attribute name="x"><xsl:value-of select="round(graphics/position/@x*$Streckung) div 1"/></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="round(graphics/position/@y*$Streckung) div 1"/></xsl:attribute>	
			<xsl:attribute name="height"><xsl:value-of select="round(graphics/position/@height*$MultiKonnektor) div 1"/></xsl:attribute>
			<xsl:attribute name="width"><xsl:value-of select="round(graphics/position/@width*$MultiKonnektor) div 1"/></xsl:attribute>
		</position>
	</graphics>
	<xsl:copy-of select="description"/>
</xsl:template>
</xsl:stylesheet>