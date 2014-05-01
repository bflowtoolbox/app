<?xml version="1.0"?>
<xsl:stylesheet version="2.0" 
	xmlns:epml="http://www.epml.de" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:epc="org.bflow.toolbox.epc" 
	xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation" 
	xmlns:xmi="http://www.omg.org/XMI" 
	xmlns:saxon="http://saxon.sf.net/" 
	extension-element-prefixes="saxon">
<!-- ### Allgemeine informationen ###-->
<!-- Ich lege noch ein zusaetzliches Attribut defRef an-->
<!-- Konvertierung von .epml zu .epc-->
<!-- Hierbei ist die Konvertierung in zwei Teile aufzugliedern. 1. Information der Elemente, 2. Graphische Darstellung-->
<!-- 1. Auswerten defId. wenn dies eine Verwabreitung hat-->
<!-- 2. Auswerten der Attribute-->
<!-- 3. Auswerten der Directory Struktur, durch vorhanden sein mehrerer Modellen. TODO wenn dies ein Verabreitung bei bflow findet-->
<!-- 4. Wenn mehrere Modelle in einem Root Verzeichnis dargestellt sind, werden diese in einem Modell abgelgt. Auch wenn hier dadurch der Name verloren geht-->
<!-- Informationen: Die Verwendung des Saxon bietet zwei Vorteile: 1.) koennen Variablen neue Werte zugewiesen werden. 2.) funktioniert der Batch Aufruf-->
<!-- 7. X und Y wird plus 20 gerechnet, nehme die Heigh wight wieder auf-->
<!-- Konnektoren haben auch heigh und width. Die arcs werden immer mittig verbunden-->
<!-- ###-->
<!-- ### TODO-Part ###-->
<!-- TODO einbauen der idBflow funktioniert nur noch fuer alle in out noch abaendern-->
<!-- abarbeiten der Elmente, TODO es ist auch noch das Element attribute vorhanden, Mapping anschauen oder was damit zu machen ist-->
<!-- IDs im Graphic Part sind noch nicht durchlaufend-->
<!-- Kann im Graphic Template noch die children Erstellungs als Template ausgliedern, mit uebergabe der Elemente-->
<!-- Bei Verwendung Saxon 6.5 Template noch einmal testen-->
<!-- ### -->
<!-- ### Fragen/Anmerkungen ###-->
<!-- Directory wird ignoriert und nicht abgefragt. UNTERSCHIED-->
<!-- Definitions werden ignoriert und nicht abgefragt. UNTERSCHIED-->
<!-- Saxon 9.0 akzeptiert nur globale Variablen zur Aenderung, diese ruiniert das gesamte Konzept, der Templates-->
<!-- fuer die Umsetzung der Alignment ausrichtung ist ein neuer style Part erforderlich, verzichte auf fillcolort-->
<!-- In der Version zwischen 34 und 42 funktionierte die in out Berechnung nicht, fixed-->
<!-- ### -->
<!-- Angaben ueber die Ausgabe-->
<xsl:output method="xml" indent="yes" />
		<xsl:variable name="TempID" saxon:assignable="yes" select="10000"/>
		<xsl:variable name="ElementID" saxon:assignable="yes"/>

		<xsl:variable name="x1" saxon:assignable="yes"/>
		<xsl:variable name="x2" saxon:assignable="yes"/>
		<xsl:variable name="y1" saxon:assignable="yes"/>
		<xsl:variable name="y2" saxon:assignable="yes"/>
		
		<xsl:variable name="Temp" saxon:assignable="yes"/>
		<xsl:variable name="Temp4" saxon:assignable="yes"/>
		<xsl:variable name="Temp2" saxon:assignable="yes"/>
		<xsl:variable name="Temp3" saxon:assignable="yes"/>
		
		<xsl:variable name="pageWidth" select="100" saxon:assignable="yes"/>
		<xsl:variable name="pageHeight" select="100" saxon:assignable="yes"/>
		
		<xsl:variable name="WdtConstant" select="100"/>
		<xsl:variable name="HgtConstant" select="50"/>
				
		<!-- erforderlich zum abfangen der Namensausgabe-->
		<xsl:template match="definitions"/>
		<!-- erforderlich zum abfangen der Autorangabe-->
		<xsl:template match="author"/>
		<!-- erforderlich zum abfangen der VO-Angabe-->
		<xsl:template match="published"/>
		<!-- abarbeiten der Epc knoten-->
		<xsl:template match="epc">
			<xmi:XMI xmi:version="2.0"  xmlns:epc="org.bflow.toolbox.epc" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation">
			<!-- directory fuer die Zukunft verarbeiten in einer for each der epml Knoten-->
			<xsl:variable name="Modellname">
				<!-- einlesen des Modellnamens-->
				<xsl:for-each select="//*[name()='epc']">
					<xsl:value-of select="@name"/>
				</xsl:for-each>
			</xsl:variable>
			<xsl:variable name="ModellId">
			<!-- Einlesen der Modell id-->
				<xsl:for-each select="//*[name()='epc']">
					<xsl:value-of select="concat('_n33b',@epcId)"/>
				</xsl:for-each>
			</xsl:variable>
				<epc:Epc>
				<!-- anlegen ModellID, Modellname wird hier nicht ausgegeben-->
				<xsl:attribute name="xmi:id"><xsl:value-of select="$ModellId"/></xsl:attribute>				
				<!-- 1. einlesen Event-->
				<xsl:for-each select="//*[name()='event']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:Event</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>	
				<!-- 2. selbe syntaktische Struktur wie event, read and create Function-->
				<xsl:for-each select="//*[name()='function']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:Function</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>	
				<!-- 3. einlesen und anlegen Application-->
				<xsl:for-each select="//*[name()='application']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:Application</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>	
				<!-- 4. einlesen und anlegen Partizipation-->
				<xsl:for-each select="//*[name()='participant']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:Participant</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>	
				<!-- 5. einlesen und anlegen Technical Terms, hergeleitet aus dem Data Field, diese ersten 5 Elemente sind alle identisch in ihrer Struktur-->
				<xsl:for-each select="//*[name()='dataField']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:TechnicalTerm</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>			
				<!-- 6. Konnektoren, and -->
				<xsl:for-each select="//*[name()='and']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:AND</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>
				<!-- 7. Konnektoren, or -->
				<xsl:for-each select="//*[name()='or']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:OR</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>
				<!-- 8. Konnektoren, xor -->
				<xsl:for-each select="//*[name()='xor']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:XOR</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>
				<!-- 9 acrs-->
				<xsl:for-each select="//*[name()='arc']">
					<xsl:variable name="ElementID">
							<xsl:value-of select="@id"/>
					</xsl:variable>
					<xsl:variable name="ArcFrom">
							<xsl:value-of select="flow/@source"/>
					</xsl:variable>
					<xsl:variable name="ArcTo">
							<xsl:value-of select="flow/@target"/>
					</xsl:variable>		
					<connections>
						<xsl:attribute name="xmi:type">epc:Arc</xsl:attribute>	
						<xsl:attribute name="xmi:id"><xsl:value-of select="$ElementID"/></xsl:attribute>
						<!-- Verwendung der alten Bflow id falls vorhanden-->
						<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="xmi:id"><xsl:value-of select="@IdBflow"/></xsl:attribute>
						</xsl:if>
						<xsl:for-each select="//*[@id=$ArcFrom]">
							<xsl:attribute name="from"><xsl:value-of select="$ArcFrom"/></xsl:attribute>
							<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="from"><xsl:value-of select="@IdBflow"/></xsl:attribute>
							</xsl:if>
						</xsl:for-each>
						<xsl:for-each select="//*[@id=$ArcTo]">
							<xsl:attribute name="to"><xsl:value-of select="$ArcTo"/></xsl:attribute>	
							<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="to"><xsl:value-of select="@IdBflow"/></xsl:attribute>
							</xsl:if>
						</xsl:for-each>
					</connections>
				</xsl:for-each>
				<!-- 10 Relations-->
				<xsl:for-each select="//*[name()='relation']">
					<xsl:variable name="ElementID">
							<xsl:value-of select="@id"/>
					</xsl:variable>
					<xsl:variable name="ArcFrom">
							<xsl:value-of select="@from"/>
					</xsl:variable>
					<xsl:variable name="ArcTo">
							<xsl:value-of select="@to"/>
					</xsl:variable>
					<connections>
						<xsl:attribute name="xmi:type">epc:Relation</xsl:attribute>	
						<xsl:attribute name="xmi:id"><xsl:value-of select="$ElementID"/></xsl:attribute>
						<xsl:if test="@IdBflow!=''">
							<xsl:attribute name="xmi:id"><xsl:value-of select="@IdBflow"/></xsl:attribute>
						</xsl:if>
						<!-- if Anweisung, Selektion Attribute id falls vorhanden, ueberschreiben der alternativen id-->			
						<xsl:for-each select="//*[@id=$ArcFrom]">
							<xsl:attribute name="from"><xsl:value-of select="$ArcFrom"/></xsl:attribute>
							<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="from"><xsl:value-of select="@IdBflow"/></xsl:attribute>
							</xsl:if>
						</xsl:for-each>
						<xsl:for-each select="//*[@id=$ArcTo]">
							<xsl:attribute name="to"><xsl:value-of select="$ArcTo"/></xsl:attribute>	
							<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="to"><xsl:value-of select="@IdBflow"/></xsl:attribute>
							</xsl:if>
						</xsl:for-each>			
					</connections>
				</xsl:for-each>
				<!-- 11. Anlegen ProcessInterface-->
				<xsl:for-each select="//*[name()='processInterface']">
					<xsl:call-template name="CreateElements">
						<xsl:with-param name="Type">epc:ProcessInterface</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>
				</epc:Epc>
				<!-- Temp, war fuer die Information und Attribute der Notation, da im zweiten Skript neu angelegt werden muss-->
				<!-- TODO auf fortlaufende id mit der Variablen TempId umschreiben-->
				<test>
					<xsl:attribute name="xmi:id">10000</xsl:attribute>
					<xsl:attribute name="type">Epc</xsl:attribute>
					<xsl:attribute name="element"><xsl:value-of select="$ModellId"/></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="$Modellname"/></xsl:attribute>
					<xsl:attribute name="measurementUnit">Pixel</xsl:attribute>
				</test>			 			
				<!-- Aufruf des Templates um die graphischen Informationen aller Knoten auszuwerten-->			
				<xsl:call-template name="Graphics">
					<xsl:with-param name="ModellId"><xsl:value-of select="$ModellId"/></xsl:with-param>
					<xsl:with-param name="Modellname"><xsl:value-of select="$Modellname"/></xsl:with-param>
				</xsl:call-template>			
			</xmi:XMI>		
	</xsl:template>
	
	<!-- read and create Ecents, Functions, Technical Terms, Appl, Partizipations, Procezzinterface, Konnektoren-->
	<xsl:template name="CreateElements">
		<xsl:param name="Type"/>
		<xsl:variable name="ElementID">
				<xsl:value-of select="@id"/>
		</xsl:variable>
		<elements>
			<xsl:attribute name="xmi:type"><xsl:value-of select="$Type"/></xsl:attribute>	
			<xsl:attribute name="xmi:id"><xsl:value-of select="$ElementID"/></xsl:attribute>
			<!-- Verwendung der alten Bflow id falls vorhanden-->
			<xsl:if test="@IdBflow!=''">
					<xsl:attribute name="xmi:id"><xsl:value-of select="@IdBflow"/></xsl:attribute>
			</xsl:if>
			<!--einlesen aus dem normalen Eingabe Feld-->
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>	
			<!-- TODO unschoene Loesung ersteinmal aendere hier auf id bflow, noch fuer alle anderen Elemente einfuegen--><!--
			<xsl:if test="@IdBflow=''">
					<xsl:call-template name="ArcsInOut">
						<xsl:with-param name="ElementID">
							<xsl:value-of select="$ElementID"/>
						</xsl:with-param>
					</xsl:call-template>	
			</xsl:if>-->
			<xsl:choose>
			<xsl:when test="@IdBflow!=''">
					<xsl:call-template name="ArcsInOutBflow">
						<xsl:with-param name="ElementID">
							<xsl:value-of select="$ElementID"/>
						</xsl:with-param>
					</xsl:call-template>	
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="ArcsInOut">
						<xsl:with-param name="ElementID">
							<xsl:value-of select="$ElementID"/>
						</xsl:with-param>
					</xsl:call-template>
			</xsl:otherwise>
			</xsl:choose>
		</elements>
	</xsl:template>
	
	<xsl:template  name="Graphics">	
		<xsl:param name="ModellId"/>
		<xsl:param name="Modellname"/>
		<notation:Diagram>
			<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
			<xsl:attribute name="type">Epc</xsl:attribute>
			<!-- ist die EPC id == Modell ID -->
			<xsl:attribute name="element"><xsl:value-of select="$ModellId"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="$Modellname"/></xsl:attribute>
			<xsl:attribute name="measurementUnit">Pixel</xsl:attribute>
			<saxon:assign name="TempID" select="$TempID+1"/>
			<!-- abarbeiten aller Elemente zum zweiten mal, diesmal  zur Auswertung der graphischen Information-->
			<!-- 1. graphische Darstellung der events-->
			<xsl:for-each select="//*[name()='event']">
				<!-- create cild Node-->
				<xsl:call-template name="CreateCildNotation">
					<xsl:with-param name="CildXmiType">notation:Node</xsl:with-param>
					<xsl:with-param name="CildType">2006</xsl:with-param>
					<xsl:with-param name="CildType2">5004</xsl:with-param>
					<xsl:with-param name="StylesXmiType">notation:ShapeStyle</xsl:with-param>
					<xsl:with-param name="LayoutType">notation:Bounds</xsl:with-param>
					<xsl:with-param name="FillColor">16253176</xsl:with-param>
					<xsl:with-param name="LineColor">0</xsl:with-param>
				</xsl:call-template>
				<saxon:assign name="TempID" select="$TempID+4"/>
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>
			</xsl:for-each>
			<!-- 2. graphische Verarbeitung der Funktion-->
			<xsl:for-each select="//*[name()='function']">
				<!-- create cild Node-->
				<xsl:call-template name="CreateCildNotation">
					<xsl:with-param name="CildXmiType">notation:Node</xsl:with-param>
					<xsl:with-param name="CildType">2007</xsl:with-param>
					<xsl:with-param name="CildType2">5005</xsl:with-param>
					<xsl:with-param name="StylesXmiType">notation:ShapeStyle</xsl:with-param>
					<xsl:with-param name="LayoutType">notation:Bounds</xsl:with-param>
					<xsl:with-param name="FillColor">63488</xsl:with-param>
					<xsl:with-param name="LineColor">0</xsl:with-param>
				</xsl:call-template>
				<saxon:assign name="TempID" select="$TempID+4"/>		
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>
			</xsl:for-each>	
			<!-- 3. graphische Verarbeitung der Application-->
			<xsl:for-each select="//*[name()='application']">
			<!-- create cild Node-->
				<xsl:call-template name="CreateCildNotation">
					<xsl:with-param name="CildXmiType">notation:Node</xsl:with-param>
					<xsl:with-param name="CildType">2004</xsl:with-param>
					<xsl:with-param name="CildType2">5002</xsl:with-param>
					<xsl:with-param name="StylesXmiType">notation:ShapeStyle</xsl:with-param>
					<xsl:with-param name="LayoutType">notation:Bounds</xsl:with-param>
					<xsl:with-param name="FillColor">16776960</xsl:with-param>
					<xsl:with-param name="LineColor">0</xsl:with-param>
				</xsl:call-template>
				<saxon:assign name="TempID" select="$TempID+4"/>	
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>
			</xsl:for-each>
			<!-- 4. graphische Verarbeitung der Partizipant-->
			<xsl:for-each select="//*[name()='participant']">
			<!-- create cild Node-->
				<xsl:call-template name="CreateCildNotation">
					<xsl:with-param name="CildXmiType">notation:Node</xsl:with-param>
					<xsl:with-param name="CildType">2002</xsl:with-param>
					<xsl:with-param name="CildType2">5001</xsl:with-param>
					<xsl:with-param name="StylesXmiType">notation:ShapeStyle</xsl:with-param>
					<xsl:with-param name="LayoutType">notation:Bounds</xsl:with-param>
					<xsl:with-param name="FillColor">65535</xsl:with-param>
					<xsl:with-param name="LineColor">0</xsl:with-param>
				</xsl:call-template>
				<saxon:assign name="TempID" select="$TempID+4"/>
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>				
			</xsl:for-each>
			<!-- 5. graphische Verarbeitung der Technicel Terms-->
			<xsl:for-each select="//*[name()='dataField']">
			<!-- create cild Node-->
				<xsl:call-template name="CreateCildNotation">
					<xsl:with-param name="CildXmiType">notation:Node</xsl:with-param>
					<xsl:with-param name="CildType">2016</xsl:with-param>
					<xsl:with-param name="CildType2">5013</xsl:with-param>
					<xsl:with-param name="StylesXmiType">notation:ShapeStyle</xsl:with-param>
					<xsl:with-param name="LayoutType">notation:Bounds</xsl:with-param>
					<!-- TODO schauen ob dies mit null geht-->
					<xsl:with-param name="FillColor">0</xsl:with-param>
					<xsl:with-param name="LineColor">0</xsl:with-param>
				</xsl:call-template>
				<saxon:assign name="TempID" select="$TempID+4"/>	
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>	
			</xsl:for-each>		
			<!-- 6. graphische Verarbeitung der Konnektoren, and, haben identische Darstellung wie 1.-5.-->
			<!-- TODO Konnektoren werden anders dargestellt, hier fehlt der Children Cild Knoten, noch als Template ausgliedern-->
			<xsl:for-each select="//*[name()='and']">
				<!-- Variablen Deklaration-->
				<saxon:assign name="ElementID" select="@id"/>
				<xsl:variable name="ElementIDBflow">
						<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:variable name="Xcoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@x">
					<xsl:value-of select="graphics/position/@x"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Ycoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@y">
					<xsl:value-of select="graphics/position/@y"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>			
		<xsl:variable name="Heigh">
			<xsl:choose>
				<xsl:when test="graphics/position/@height">
					<xsl:value-of select="graphics/position/@height"/>
				</xsl:when>
				<xsl:otherwise>32</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Width">
			<xsl:choose>
				<xsl:when test="graphics/position/@width">
					<xsl:value-of select="graphics/position/@width"/>				
				</xsl:when>
				<xsl:otherwise>32</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>	
				<xsl:if test="$Xcoord+$Width+$WdtConstant>$pageWidth">
					<saxon:assign name="pageWidth" select="$Xcoord+$Width+$WdtConstant"/>
				</xsl:if>
				<xsl:if test="$Ycoord+$Heigh+$HgtConstant>$pageHeight">
					<saxon:assign name="pageHeight" select="$Ycoord+$Heigh+$HgtConstant"/>
				</xsl:if>
				<children>
					<xsl:attribute name="xmi:type">notation:Node</xsl:attribute>		
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>	
					<xsl:attribute name="type">2003</xsl:attribute>
					<xsl:attribute name="element"><xsl:value-of select="$ElementID"/></xsl:attribute>
					<xsl:if test="@IdBflow!=''">
						<xsl:attribute name="element"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
						<saxon:assign name="ElementID" select="$ElementIDBflow"/>
					</xsl:if>
					<saxon:assign name="TempID" select="$TempID+1"/>
					<styles>
						<xsl:attribute name="xmi:type">notation:ShapeStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles>
					<layoutConstraint>
						<xsl:attribute name="xmi:type">notation:Bounds</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>				
						<xsl:attribute name="x"><xsl:value-of select="$Xcoord"/></xsl:attribute>
						<xsl:attribute name="y"><xsl:value-of select="$Ycoord"/></xsl:attribute>
						<xsl:attribute name="width"><xsl:value-of select="$Width"/></xsl:attribute>
						<xsl:attribute name="height"><xsl:value-of select="$Heigh"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</layoutConstraint>
				</children>
				<!-- create Discription, create new child, fkt. bei events und Funktionen-->
				<xsl:call-template name="CreateDesciption">
					<xsl:with-param name="Xcoord" ><xsl:value-of select="$Xcoord"/></xsl:with-param>
					<xsl:with-param name="Ycoord" ><xsl:value-of select="$Ycoord"/></xsl:with-param>
					<xsl:with-param name="Width" ><xsl:value-of select="$Width"/></xsl:with-param>
					<xsl:with-param name="Heigh" ><xsl:value-of select="$Heigh"/></xsl:with-param>
					<xsl:with-param name="ID" ><xsl:value-of select="$ElementID"/></xsl:with-param>
				</xsl:call-template>	
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>		
			</xsl:for-each>
			<!-- 7. graphische Verarbeitung der Konnektoren, or, haben identische Darstellung wie 1.-5.-->
			<xsl:for-each select="//*[name()='or']">
				<!-- Variablen Deklaration-->
				<saxon:assign name="ElementID" select="@id"/>
				<xsl:variable name="ElementIDBflow">
						<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:variable name="Xcoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@x">
					<xsl:value-of select="graphics/position/@x"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Ycoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@y">
					<xsl:value-of select="graphics/position/@y"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>			
		<xsl:variable name="Heigh">
			<xsl:choose>
				<xsl:when test="graphics/position/@height">
					<xsl:value-of select="graphics/position/@height"/>
				</xsl:when>
				<xsl:otherwise>32</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Width">
			<xsl:choose>
				<xsl:when test="graphics/position/@width">
					<xsl:value-of select="graphics/position/@width"/>				
				</xsl:when>
				<xsl:otherwise>32</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>	
				<xsl:if test="$Xcoord+$Width+$WdtConstant>$pageWidth">
					<saxon:assign name="pageWidth" select="$Xcoord+$Width+$WdtConstant"/>
				</xsl:if>
				<xsl:if test="$Ycoord+$Heigh+$HgtConstant>$pageHeight">
					<saxon:assign name="pageHeight" select="$Ycoord+$Heigh+$HgtConstant"/>
				</xsl:if>
				<children>
					<xsl:attribute name="xmi:type">notation:Node</xsl:attribute>		
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="type">2001</xsl:attribute>
					<!-- Element erhaelt die Id analog wie oben Auswertung, der bflowId-->
					<xsl:attribute name="element"><xsl:value-of select="$ElementID"/></xsl:attribute>
					<xsl:if test="@IdBflow!=''">
						<xsl:attribute name="element"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
						<saxon:assign name="ElementID" select="$ElementIDBflow"/>
					</xsl:if>
					<saxon:assign name="TempID" select="$TempID+1"/>
					<styles>
						<xsl:attribute name="xmi:type">notation:ShapeStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles>
					<layoutConstraint>
						<xsl:attribute name="xmi:type">notation:Bounds</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>			
						<xsl:attribute name="x"><xsl:value-of select="$Xcoord"/></xsl:attribute>
						<xsl:attribute name="y"><xsl:value-of select="$Ycoord"/></xsl:attribute>
						<xsl:attribute name="width"><xsl:value-of select="$Width"/></xsl:attribute>
						<xsl:attribute name="height"><xsl:value-of select="$Heigh"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</layoutConstraint>
				</children>
				<!-- create Discription, create new child, fkt. bei events und Funktionen-->
				<xsl:call-template name="CreateDesciption">
					<xsl:with-param name="Xcoord" ><xsl:value-of select="$Xcoord"/></xsl:with-param>
					<xsl:with-param name="Ycoord" ><xsl:value-of select="$Ycoord"/></xsl:with-param>
					<xsl:with-param name="Width" ><xsl:value-of select="$Width"/></xsl:with-param>
					<xsl:with-param name="Heigh" ><xsl:value-of select="$Heigh"/></xsl:with-param>
					<xsl:with-param name="ID" ><xsl:value-of select="$ElementID"/></xsl:with-param>
				</xsl:call-template>	
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>		
			</xsl:for-each>
			<!-- 8. graphische Verarbeitung der Konnektoren, xor, haben identische Darstellung wie 1.-5.-->
			<xsl:for-each select="//*[name()='xor']">
				<!-- Variablen Deklaration-->
				<saxon:assign name="ElementID" select="@id"/>
				<xsl:variable name="ElementIDBflow">
						<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:variable name="Xcoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@x">
					<xsl:value-of select="graphics/position/@x"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Ycoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@y">
					<xsl:value-of select="graphics/position/@y"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>			
		<xsl:variable name="Heigh">
			<xsl:choose>
				<xsl:when test="graphics/position/@height">
					<xsl:value-of select="graphics/position/@height"/>
				</xsl:when>
				<xsl:otherwise>32</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Width">
			<xsl:choose>
				<xsl:when test="graphics/position/@width">
					<xsl:value-of select="graphics/position/@width"/>				
				</xsl:when>
				<xsl:otherwise>32</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
				<xsl:if test="$Xcoord+$Width+$WdtConstant>$pageWidth">
					<saxon:assign name="pageWidth" select="$Xcoord+$Width+$WdtConstant"/>
				</xsl:if>
				<xsl:if test="$Ycoord+$Heigh+$HgtConstant>$pageHeight">
					<saxon:assign name="pageHeight" select="$Ycoord+$Heigh+$HgtConstant"/>
				</xsl:if>	
				<children>
					<xsl:attribute name="xmi:type">notation:Node</xsl:attribute>		
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>					
					<xsl:attribute name="type">2008</xsl:attribute>
					<xsl:attribute name="element"><xsl:value-of select="$ElementID"/></xsl:attribute>
					<xsl:if test="@IdBflow!=''">
						<xsl:attribute name="element"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
						<saxon:assign name="ElementID" select="$ElementIDBflow"/>
					</xsl:if>
					<saxon:assign name="TempID" select="$TempID+1"/>
					<styles>
						<xsl:attribute name="xmi:type">notation:ShapeStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles>
					<layoutConstraint>
						<xsl:attribute name="xmi:type">notation:Bounds</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>					
						<xsl:attribute name="x"><xsl:value-of select="$Xcoord"/></xsl:attribute>
						<xsl:attribute name="y"><xsl:value-of select="$Ycoord"/></xsl:attribute>
						<xsl:attribute name="width"><xsl:value-of select="$Width"/></xsl:attribute>
						<xsl:attribute name="height"><xsl:value-of select="$Heigh"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</layoutConstraint>
				</children>
				<!-- create Discription, create new child, fkt. bei events und Funktionen-->
				<xsl:call-template name="CreateDesciption">
					<xsl:with-param name="Xcoord" ><xsl:value-of select="$Xcoord"/></xsl:with-param>
					<xsl:with-param name="Ycoord" ><xsl:value-of select="$Ycoord"/></xsl:with-param>
					<xsl:with-param name="Width" ><xsl:value-of select="$Width"/></xsl:with-param>
					<xsl:with-param name="Heigh" ><xsl:value-of select="$Heigh"/></xsl:with-param>
					<xsl:with-param name="ID" ><xsl:value-of select="$ElementID"/></xsl:with-param>
				</xsl:call-template>	
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>		
			</xsl:for-each>		
			<!-- 11. ProcessInterface-->
			<xsl:for-each select="//*[name()='processInterface']">
					<!-- create cild Node-->
				<xsl:call-template name="CreateCildNotation">
					<xsl:with-param name="CildXmiType">notation:Node</xsl:with-param>
					<xsl:with-param name="CildType">2005</xsl:with-param>
					<xsl:with-param name="CildType2">5003</xsl:with-param>
					<xsl:with-param name="StylesXmiType">notation:ShapeStyle</xsl:with-param>
					<xsl:with-param name="LayoutType">notation:Bounds</xsl:with-param>
					<xsl:with-param name="FillColor">0</xsl:with-param>
					<xsl:with-param name="LineColor">0</xsl:with-param>
				</xsl:call-template>
				<saxon:assign name="TempID" select="$TempID+4"/>
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>		
			</xsl:for-each>	
			<!-- 9. Arcs-->
			<xsl:for-each select="//*[name()='arc']">
				<saxon:assign name="ElementID" select="@id"/>
				<xsl:variable name="ElementIDBflow">
					<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:if test="@IdBflow!=''">
					<saxon:assign name="ElementID" select="$ElementIDBflow"/>
				</xsl:if>
				<xsl:call-template name="CreateDesciption">
					<xsl:with-param name="Xcoord" ><xsl:value-of select="graphics/position/@x"/></xsl:with-param>
					<xsl:with-param name="Ycoord" ><xsl:value-of select="graphics/position/@y"/></xsl:with-param>
					<xsl:with-param name="Width" >60</xsl:with-param>
					<xsl:with-param name="Heigh" >20</xsl:with-param>
					<xsl:with-param name="ID" ><xsl:value-of select="$ElementID"/></xsl:with-param>
				</xsl:call-template>
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>	
			</xsl:for-each>
			<!-- 10. Relation-->			
			<xsl:for-each select="//*[name()='relation']">
				<saxon:assign name="ElementID" select="@id"/>
				<xsl:variable name="ElementIDBflow">
					<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:if test="@IdBflow!=''">
					<saxon:assign name="ElementID" select="$ElementIDBflow"/>
				</xsl:if>
				<xsl:call-template name="CreateDesciption">
					<xsl:with-param name="Xcoord"><xsl:value-of select="graphics/position/@x"/></xsl:with-param>
					<xsl:with-param name="Ycoord"><xsl:value-of select="graphics/position/@y"/></xsl:with-param>
					<xsl:with-param name="Width">60</xsl:with-param>
					<xsl:with-param name="Heigh">20</xsl:with-param>
					<xsl:with-param name="ID" ><xsl:value-of select="$ElementID"/></xsl:with-param>
				</xsl:call-template>
				<xsl:if test="description!=''">
					<saxon:assign name="TempID" select="$TempID+8"/>
				</xsl:if>	
			</xsl:for-each>
			<!-- Als Abgrennzung zu den Kanten und Relations erscheint eine Einzelne Zeile mit Informationen-->
			<styles>
				<xsl:attribute name="xmi:type">notation:DiagramStyle</xsl:attribute>				
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
				<xsl:attribute name="pageWidth" select="$pageWidth"/>
				<xsl:attribute name="pageHeight" select="$pageHeight"/>
				<xsl:attribute name="description" select="'IGlobalColorSchema=o'"/>
				<saxon:assign name="TempID" select="$TempID+1"/>
			</styles>
			</notation:Diagram>
			<!-- 9. graphische Verarbeitung Arcs, bflow akzeptiert es wenn die bendpoints auf null gesetzt sind-->
			<!-- Bei den ids der source und target handelt es sich um die ids der childrens in der graphischen Information, diese kann ich auch nicht aus der epml Datei ermitteln-->
			<!-- Der letzte Stand der arcs und relations ist in der 22 gespeichert, diese nicht loeschen-->		
			<xsl:for-each select="//*[name()='arc']">
				<!-- Variablen Deklaration-->
				<saxon:assign name="ElementID" select="@id"/>
				<!-- Verwendung fuer target und source-->
				<!-- hier werden die ids eingelesen, dies sind allerdings die alten-->
				<xsl:variable name="ArcFrom">
					<xsl:value-of select="flow/@source"/>
				</xsl:variable>
				<xsl:variable name="ArcTo">
					<xsl:value-of select="flow/@target"/>
				</xsl:variable>	
				<!-- muss auf die koordinaten der Elemente zugreifen, jedes Element muss hierfuer ein graphics haben, sonst wird es unschoen -->
				<edgesArc>
					<!-- kuenstlich angelegte Elemente-->
					<xsl:attribute name="source"><xsl:value-of select="$ArcFrom"/></xsl:attribute>
					<xsl:attribute name="target"><xsl:value-of select="$ArcTo"/></xsl:attribute>
					<xsl:attribute name="xmi:type">notation:Edge</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="type">4001</xsl:attribute>
					<!-- Element erhaelt die Id analog wie oben Auswertung, der bflowId-->
					<xsl:attribute name="element"><xsl:value-of select="$ElementID"/></xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>
					<xsl:variable name="IDbflow" select="@IdBflow"/>
					<xsl:if test="@IdBflow!=''">
						<xsl:attribute name="element"><xsl:value-of select="$IDbflow"/></xsl:attribute>
					</xsl:if>	
					<!-- TODO kann hier keine Uebereinstimmung finden, da id neuangelegt, da source und Target auf Elemente verweist, d.h neues Skript anlegen-->			
					<xsl:for-each select="//*[@id=$ArcFrom]">
						<xsl:attribute name="source"><xsl:value-of select="$ArcFrom"/></xsl:attribute>
						<xsl:variable name="IDbflow" select="@IdBflow"/>
						<xsl:if test="@IdBflow!=''">
							<xsl:attribute name="source"><xsl:value-of select="$IDbflow"/></xsl:attribute>
						</xsl:if>		
						<saxon:assign name="x1" select="graphics/position/@x"/>		
						<saxon:assign name="y1" select="graphics/position/@y"/>		
					</xsl:for-each>
					<xsl:for-each select="//*[@id=$ArcTo]">
						<xsl:attribute name="target"><xsl:value-of select="$ArcTo"/></xsl:attribute>	
						<xsl:variable name="IDbflow" select="@IdBflow"/>
						<xsl:if test="@IdBflow!=''">
							<xsl:attribute name="target"><xsl:value-of select="$IDbflow"/></xsl:attribute>
						</xsl:if>
						<saxon:assign name="x2" select="graphics/position/@x"/>		
						<saxon:assign name="y2" select="graphics/position/@y"/>	
					</xsl:for-each>
					<styles>
						<xsl:attribute name="xmi:type">notation:RoutingStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<xsl:attribute name="routing">Rectilinear</xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles>
					<styles1>
						<xsl:attribute name="xmi:type">notation:FontStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles1>
					<bendpoints>
						<xsl:attribute name="xmi:type">notation:RelativeBendpoints</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<xsl:attribute name="points">[0, 0, 0, 0]</xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>	
					</bendpoints>
				</edgesArc>	
			</xsl:for-each>
			<!-- 10. graphische Verarbeitung Relations-->
			<xsl:for-each select="//*[name()='relation']">
				<!-- Variablen Deklaration-->
				<xsl:variable name="ElementID">
						<xsl:value-of select="@id"/>
				</xsl:variable>
				<!-- Verwendung fuer target und source-->
				<xsl:variable name="ArcFrom">
						<xsl:value-of select="@from"/>
				</xsl:variable>
				<xsl:variable name="ArcTo">
						<xsl:value-of select="@to"/>
					</xsl:variable>	
				<edgesRelation>
					<xsl:attribute name="xmi:type">notation:Edge</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="routing">Rectilinear</xsl:attribute>
					<!-- Element erhaelt die Id analog wie oben Auswertung, der bflowId-->
					<xsl:attribute name="element"><xsl:value-of select="$ElementID"/></xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>		
					<xsl:variable name="IDbflow" select="@IdBflow"/>
					<xsl:if test="@IdBflow!=''">
						<xsl:attribute name="element"><xsl:value-of select="$IDbflow"/></xsl:attribute>
					</xsl:if>
					<xsl:for-each select="//*[@id=$ArcFrom]">
							<xsl:attribute name="source"><xsl:value-of select="$ArcFrom"/></xsl:attribute>	
							<xsl:variable name="IDbflow" select="@IdBflow"/>
							<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="source"><xsl:value-of select="$IDbflow"/></xsl:attribute>
							</xsl:if>	
						</xsl:for-each>
						<xsl:for-each select="//*[@id=$ArcTo]">
							<xsl:attribute name="target"><xsl:value-of select="$ArcTo"/></xsl:attribute>	
							<xsl:variable name="IDbflow" select="@IdBflow"/>
							<xsl:if test="@IdBflow!=''">
								<xsl:attribute name="target"><xsl:value-of select="$IDbflow"/></xsl:attribute>
							</xsl:if>	
						</xsl:for-each>
					<styles>
						<xsl:attribute name="xmi:type">notation:RoutingStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<xsl:attribute name="routing">Rectilinear</xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles>
					<styles1>
						<xsl:attribute name="xmi:type">notation:FontStyle</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</styles1>
					<bendpoints>
						<xsl:attribute name="xmi:type">notation:RelativeBendpoints</xsl:attribute>
						<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
						<!-- TODO die points hier werden interessant-->
						<xsl:attribute name="points">[0, 0, 0, 0]$[0, 0, 0, 0]</xsl:attribute>
						<saxon:assign name="TempID" select="$TempID+1"/>
					</bendpoints>
				</edgesRelation>
			</xsl:for-each>			
			<!-- Anzahl der Elemente, erforderlich fuer Skript zwei-->
			<Anzahl>
				<xsl:attribute name="Anzahl"><xsl:value-of select="$TempID"/></xsl:attribute>
			</Anzahl>
	</xsl:template>
	<!-- Anlegen der Child Elemente Notation Node-->
	<xsl:template name="CreateCildNotation">
		<xsl:param name="CildType"/>
		<xsl:param name="CildXmiType"/>
		<xsl:param name="CildType2"/>
		<xsl:param name="StylesXmiType"/>
		<xsl:param name="LayoutType"/>
		<xsl:param name="FillColor"/>
		<xsl:param name="LineColor"/>
		<!-- Variablen Deklaration-->
		<saxon:assign name="ElementID" select="@id"/>
		<xsl:variable name="ElementIDBflow">
				<xsl:value-of select="@IdBflow"/>
		</xsl:variable>
		<!-- diese Information gewinne ich aus dem child Position, es befindet sich noch der graphics Ordner dazwischen-->
		<!-- dies ist ein Musterbeispiel um die for each Schleifen zu umgehen-->
		<xsl:variable name="Xcoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@x">
					<xsl:value-of select="graphics/position/@x"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Ycoord">
			<xsl:choose>
				<xsl:when test="graphics/position/@y">
					<xsl:value-of select="graphics/position/@y"/>
				</xsl:when>
				<xsl:otherwise>0</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>			
		<xsl:variable name="Heigh">
			<xsl:choose>
				<xsl:when test="graphics/position/@height">
					<xsl:value-of select="graphics/position/@height"/>
				</xsl:when>
				<xsl:otherwise>64</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="Width">
			<xsl:choose>
				<xsl:when test="graphics/position/@width">
					<xsl:value-of select="graphics/position/@width"/>				
				</xsl:when>
				<xsl:otherwise>64</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:if test="$Xcoord+$Width+$WdtConstant>$pageWidth">
			<saxon:assign name="pageWidth" select="$Xcoord+$Width+$WdtConstant"/>
		</xsl:if>
		<xsl:if test="$Ycoord+$Heigh+$HgtConstant>$pageHeight">
			<saxon:assign name="pageHeight" select="$Ycoord+$Heigh+$HgtConstant"/>
		</xsl:if>		
		<children>
			<xsl:attribute name="xmi:type"><xsl:value-of select="$CildXmiType"/></xsl:attribute>
			<!-- die Elemente verfuegen ueber eine id welche spaeter in den eges der graphischen Darstellung benutzt und verwendet werden-->
			<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
			<xsl:attribute name="type"><xsl:value-of select="$CildType"/></xsl:attribute>
			<!-- Element erhaelt die Id analog wie oben Auswertung, der bflowId-->
			<xsl:attribute name="element"><xsl:value-of select="$ElementID"/></xsl:attribute>
			<xsl:if test="@IdBflow!=''">
				<xsl:attribute name="element"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
				<saxon:assign name="ElementID" select="$ElementIDBflow"/>
			</xsl:if>
			<saxon:assign name="TempID" select="$TempID+1"/>
			<!--anlegen Kinderknoten -->
			<children>
				<xsl:attribute name="xmi:type"><xsl:value-of select="$CildXmiType"/></xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
				<xsl:attribute name="type"><xsl:value-of select="$CildType2"/></xsl:attribute>
				<saxon:assign name="TempID" select="$TempID+1"/>
			</children>
			<styles>
				<xsl:attribute name="xmi:type"><xsl:value-of select="$StylesXmiType"/></xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
				<xsl:if test="$FillColor!='0'">
					<xsl:attribute name="fillColor"><xsl:value-of select="$FillColor"/></xsl:attribute>
				</xsl:if>
				<xsl:attribute name="lineColor"><xsl:value-of select="$LineColor"/></xsl:attribute>
				<saxon:assign name="TempID" select="$TempID+1"/>
			</styles>
			<!-- TODO brauche auch eine Aenderung der Fortlaufenden ID, um eins erhoehen falls Align ungleich leer-->
			<styles>
				<xsl:attribute name="xmi:type">notation:TextStyle</xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
				<xsl:if test="@textAlignment!=''">
					<xsl:attribute name="textAlignment"><xsl:value-of select="@textAlignment"/></xsl:attribute>
				</xsl:if>
				<saxon:assign name="TempID" select="$TempID+1"/>
			</styles>	
			<layoutConstraint>
				<xsl:attribute name="xmi:type"><xsl:value-of select="$LayoutType"/></xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>						
				<xsl:attribute name="x"><xsl:value-of select="$Xcoord"/></xsl:attribute>
				<xsl:attribute name="y"><xsl:value-of select="$Ycoord"/></xsl:attribute>
				<xsl:attribute name="width"><xsl:value-of select="$Width"/></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="$Heigh"/></xsl:attribute>
				<saxon:assign name="TempID" select="$TempID+1"/>
			</layoutConstraint>
		</children>				
		<!-- create Discription, create new child, fkt. bei events und Funktionen-->
		<xsl:call-template name="CreateDesciption">
			<xsl:with-param name="Xcoord" ><xsl:value-of select="$Xcoord"/></xsl:with-param>
			<xsl:with-param name="Ycoord" ><xsl:value-of select="$Ycoord"/></xsl:with-param>
			<xsl:with-param name="Width" ><xsl:value-of select="$Width"/></xsl:with-param>
			<xsl:with-param name="Heigh" ><xsl:value-of select="$Heigh"/></xsl:with-param>
			<xsl:with-param name="ID" ><xsl:value-of select="$ElementID"/></xsl:with-param>
		</xsl:call-template>	
	</xsl:template>
	<xsl:template name="CreateDesciption">
		<xsl:param name="Xcoord"/>
		<xsl:param name="Ycoord"/>
		<xsl:param name="Width"/>
		<xsl:param name="Heigh"/>
		<xsl:param name="ID"/>
		<!-- Abfrage des Inhaltes, des Knotens-->
		<xsl:if test="description!=''">
			<TempChildren>
				<xsl:if test="description!=''">
					<xsl:attribute name="description">True</xsl:attribute>
				</xsl:if>
				<xsl:attribute name="id"><xsl:value-of select="$ID"/></xsl:attribute>
				<xsl:attribute name="xmi:type">notation:Node</xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
				<xsl:attribute name="type">Note</xsl:attribute>
				<saxon:assign name="TempID" select="$TempID+1"/>
				<children>
					<xsl:attribute name="xmi:type">notation:Node</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="type">DiagramName</xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>
					<element>
						<xsl:attribute name="xsi:nil">true</xsl:attribute>
					</element>
				</children>
				<children2>
					<xsl:attribute name="xmi:type">notation:Node</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="type">Description</xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>
					<element>
						<xsl:attribute name="xsi:nil">true</xsl:attribute>
					</element>
				</children2>
				<styles>
					<xsl:attribute name="xmi:type">notation:ShapeStyle</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="description"><xsl:value-of select="description"/></xsl:attribute>
					<xsl:attribute name="fillColor">13369343</xsl:attribute>
					<xsl:attribute name="lineColor">6737151</xsl:attribute>
					<xsl:attribute name="lineWidth">1</xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>
				</styles>
				<styles2>
					<xsl:attribute name="xmi:type">notation:TextStyle</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>
				</styles2>			
				<styles3>
					<xsl:attribute name="xmi:type">notation:LineTypeStyle</xsl:attribute>
					<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<saxon:assign name="TempID" select="$TempID+1"/>
				</styles3>
				<element>
					<xsl:attribute name="xsi:nil">true</xsl:attribute>
				</element>
				<layoutConstraint>
				<xsl:attribute name="xmi:type">notation:Bounds</xsl:attribute>
				<xsl:attribute name="xmi:id"><xsl:value-of select="$TempID"/></xsl:attribute>						
				<!-- TODO wie soll ich die Elemente anordnen dies geht leider nicht, da ich keine Informationen habe, mache ersteinmal +50-->
				<xsl:attribute name="x"><xsl:value-of select="$Xcoord"/></xsl:attribute>
				<!-- TODO macht kein Minus mit-->
				<xsl:attribute name="y"><xsl:value-of select="$Ycoord+-$Heigh+-10"/></xsl:attribute>
				<!-- Ubergebe der groesse der entprechend zugeordneten Elemente, -->
				<!-- TODO hier muss ich ansetzen da die Groessen angabe nicht mehr stimmt bzw. ich diese uebergeben muss-->
				<!--IM zweiten Skript deaktiviert-->
				<xsl:attribute name="width"><xsl:value-of select="$Width"/></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="$Heigh"/></xsl:attribute>
				<saxon:assign name="TempID" select="$TempID+1"/>
			</layoutConstraint>
			</TempChildren>
		</xsl:if>
	</xsl:template>
	<!-- Erstellung ein und Ausgaenge der Elemente mit leerzeichen-->
	<!-- TODO Umschreiben auf id Bflow-->
	<xsl:template name="ArcsInOut">
		<xsl:param name="ElementID"/>
		<saxon:assign name="Temp" select="''"/>
		<saxon:assign name="Temp4" select="''"/>
		<saxon:assign name="Temp2" select="''"/>
		<saxon:assign name="Temp3" select="''"/>
		<xsl:for-each select="//*[name()='arc']">
			<xsl:variable name="TempLeer">'!</xsl:variable>		
			<xsl:variable name="TempID">
				<xsl:value-of select="@id"/>
			</xsl:variable>
			<xsl:variable name="TempResultatTemp">							
				<xsl:if test="$Temp=''">	
					<xsl:value-of select="@id"/>
				</xsl:if>
				<xsl:if test="$Temp!=''">
					<xsl:value-of select="concat($TempLeer,$TempID)"/>
				</xsl:if>
			</xsl:variable>	
			<xsl:variable name="TempResultat">
				<xsl:value-of select="concat($Temp,$TempResultatTemp)"/>
			</xsl:variable>			
			<xsl:if test="flow[@target=$ElementID]">
				<saxon:assign name="Temp" select="translate($TempResultat,$TempLeer, ' ')"/>
				<xsl:attribute name="in"><xsl:value-of select="$Temp"/></xsl:attribute>
			</xsl:if>
		</xsl:for-each>	
		<xsl:for-each select="//*[name()='relation']">
			<xsl:if test="$Temp4=''">
				<xsl:variable name="TempLeer">'!</xsl:variable>		
				<xsl:variable name="TempID">
					<xsl:value-of select="@id"/>
				</xsl:variable>
				<xsl:variable name="TempResultatTemp">							
					<xsl:if test="$Temp=''">	
						<xsl:value-of select="@id"/>
					</xsl:if>
					<xsl:if test="$Temp!=''">
						<xsl:value-of select="concat($TempLeer,$TempID)"/>
					</xsl:if>
				</xsl:variable>	
				<xsl:variable name="TempResultat">
					<xsl:value-of select="concat($Temp,$TempResultatTemp)"/>
				</xsl:variable>			
				<xsl:if test="@to=$ElementID">
					<saxon:assign name="Temp" select="translate($TempResultat,$TempLeer, ' ')"/>
					<xsl:attribute name="in"><xsl:value-of select="$Temp"/></xsl:attribute>
				</xsl:if>
			</xsl:if>
			<xsl:if test="$Temp4!=''">
				<xsl:variable name="TempLeer">'!</xsl:variable>		
				<xsl:variable name="TempID">
					<xsl:value-of select="@id"/>
				</xsl:variable>
					<xsl:variable name="TempResultatTemp">							
						<xsl:value-of select="concat($TempLeer,$TempID)"/>		
					</xsl:variable>	
				<xsl:variable name="TempResultat">
					<xsl:value-of select="concat($Temp,$TempResultatTemp)"/>
				</xsl:variable>			
				<xsl:if test="@to=$ElementID">
					<saxon:assign name="Temp" select="translate($TempResultat,$TempLeer, ' ')"/>
					<xsl:attribute name="in"><xsl:value-of select="$Temp"/></xsl:attribute>
				</xsl:if>
			</xsl:if>			
		</xsl:for-each>	
		<!-- Ermittlung out-->
		<xsl:for-each select="//*[name()='arc']">						
			<xsl:variable name="TempLeer">'!</xsl:variable>	
			<xsl:variable name="TempAcirc">&#194; &#160;</xsl:variable>	
			<xsl:variable name="Temp2ID">
				<xsl:value-of select="@id"/>
			</xsl:variable>
			<xsl:variable name="TempResultatTemp">
				<xsl:if test="$Temp2=''">	
					<xsl:value-of select="@id"/>
				</xsl:if>
				<xsl:if test="$Temp2!=''">
					<xsl:value-of select="concat($TempLeer,$Temp2ID)"/>
				</xsl:if>
			</xsl:variable>	
			<xsl:variable name="TempResultat">
				<xsl:value-of select="concat($Temp2,$TempResultatTemp)"/>
			</xsl:variable>	
			<xsl:if test="flow[@source=$ElementID]">
				<saxon:assign name="Temp2" select="translate($TempResultat,$TempLeer, ' ')"/>
				<xsl:attribute name="out"><xsl:value-of select="$Temp2"/></xsl:attribute>
			</xsl:if>
		</xsl:for-each>	
		<saxon:assign name="Temp3" select="$Temp2"/>
		<xsl:for-each select="//*[name()='relation']">
			<!-- Mit or funktioniert nicht, Test mit schlechten Laufzeit, in einer doppelt geschachtelten for Schleife funktioniert es auch nicht-->
			<xsl:if test="$Temp3=''">
			<xsl:variable name="TempLeer">'!</xsl:variable>	
			<xsl:variable name="Temp2ID">
				<xsl:value-of select="@id"/>
			</xsl:variable>
			<xsl:variable name="TempResultatTemp">
				<xsl:if test="$Temp2=''">	
					<xsl:value-of select="@id"/>
				</xsl:if>
				<xsl:if test="$Temp2!=''">
					<xsl:value-of select="concat($TempLeer,$Temp2ID)"/>
				</xsl:if>
			</xsl:variable>	
			<xsl:variable name="TempResultat">
				<xsl:value-of select="concat($Temp2,$TempResultatTemp)"/>
			</xsl:variable>	
			<xsl:if test="@from=$ElementID">
				<saxon:assign name="Temp2" select="translate($TempResultat,$TempLeer, ' ')"/>
				<xsl:attribute name="out"><xsl:value-of select="$Temp2"/></xsl:attribute>
			</xsl:if>
			</xsl:if>		
			<xsl:if test="$Temp3!=''">
				<xsl:variable name="TempLeer">'!</xsl:variable>	
				<xsl:variable name="Temp2ID">
					<xsl:value-of select="@id"/>
				</xsl:variable>
				<xsl:variable name="TempResultatTemp">
					<xsl:value-of select="concat($TempLeer,$Temp2ID)"/>
				</xsl:variable>	
				<xsl:variable name="TempResultat">
					<xsl:value-of select="concat($Temp2,$TempResultatTemp)"/>
				</xsl:variable>	
				<xsl:if test="@from=$ElementID">
					<saxon:assign name="Temp2" select="translate($TempResultat,$TempLeer, ' ')"/>
					<xsl:attribute name="out"><xsl:value-of select="$Temp2"/></xsl:attribute>
				</xsl:if>
			</xsl:if>
		</xsl:for-each>	
	</xsl:template>
	<xsl:template name="ArcsInOutBflow">
		<xsl:param name="ElementID"/>
		<saxon:assign name="Temp" select="''"/>
		<saxon:assign name="Temp4" select="''"/>
		<saxon:assign name="Temp2" select="''"/>
		<saxon:assign name="Temp3" select="''"/>
		<xsl:for-each select="//*[name()='arc']">
			<xsl:variable name="TempLeer">'!</xsl:variable>		
			<xsl:variable name="TempID">
				<xsl:value-of select="@IdBflow"/>
			</xsl:variable>
			<xsl:variable name="TempResultatTemp">							
				<xsl:if test="$Temp=''">	
					<xsl:value-of select="@IdBflow"/>
				</xsl:if>
				<xsl:if test="$Temp!=''">
					<xsl:value-of select="concat($TempLeer,$TempID)"/>
				</xsl:if>
			</xsl:variable>	
			<xsl:variable name="TempResultat">
				<xsl:value-of select="concat($Temp,$TempResultatTemp)"/>
			</xsl:variable>			
			<xsl:if test="flow[@target=$ElementID]">
				<saxon:assign name="Temp" select="translate($TempResultat,$TempLeer, ' ')"/>
				<xsl:attribute name="in"><xsl:value-of select="$Temp"/></xsl:attribute>
			</xsl:if>
		</xsl:for-each>	
		<xsl:for-each select="//*[name()='relation']">
			<xsl:if test="$Temp4=''">
				<xsl:variable name="TempLeer">'!</xsl:variable>		
				<xsl:variable name="TempID">
					<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:variable name="TempResultatTemp">							
					<xsl:if test="$Temp=''">	
						<xsl:value-of select="@IdBflow"/>
					</xsl:if>
					<xsl:if test="$Temp!=''">
						<xsl:value-of select="concat($TempLeer,$TempID)"/>
					</xsl:if>
				</xsl:variable>	
				<xsl:variable name="TempResultat">
					<xsl:value-of select="concat($Temp,$TempResultatTemp)"/>
				</xsl:variable>			
				<xsl:if test="@to=$ElementID">
					<saxon:assign name="Temp" select="translate($TempResultat,$TempLeer, ' ')"/>
					<xsl:attribute name="in"><xsl:value-of select="$Temp"/></xsl:attribute>
				</xsl:if>
			</xsl:if>
			<xsl:if test="$Temp4!=''">
				<xsl:variable name="TempLeer">'!</xsl:variable>		
				<xsl:variable name="TempID">
					<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
					<xsl:variable name="TempResultatTemp">							
						<xsl:value-of select="concat($TempLeer,$TempID)"/>		
					</xsl:variable>	
				<xsl:variable name="TempResultat">
					<xsl:value-of select="concat($Temp,$TempResultatTemp)"/>
				</xsl:variable>			
				<xsl:if test="@to=$ElementID">
					<saxon:assign name="Temp" select="translate($TempResultat,$TempLeer, ' ')"/>
					<xsl:attribute name="in"><xsl:value-of select="$Temp"/></xsl:attribute>
				</xsl:if>
			</xsl:if>			
		</xsl:for-each>	
		<!-- Ermittlung out-->
		<xsl:for-each select="//*[name()='arc']">						
			<xsl:variable name="TempLeer">'!</xsl:variable>	
			<xsl:variable name="TempAcirc">&#194; &#160;</xsl:variable>	
			<xsl:variable name="Temp2ID">
				<xsl:value-of select="@IdBflow"/>
			</xsl:variable>
			<xsl:variable name="TempResultatTemp">
				<xsl:if test="$Temp2=''">	
					<xsl:value-of select="@IdBflow"/>
				</xsl:if>
				<xsl:if test="$Temp2!=''">
					<xsl:value-of select="concat($TempLeer,$Temp2ID)"/>
				</xsl:if>
			</xsl:variable>	
			<xsl:variable name="TempResultat">
				<xsl:value-of select="concat($Temp2,$TempResultatTemp)"/>
			</xsl:variable>	
			<xsl:if test="flow[@source=$ElementID]">
				<saxon:assign name="Temp2" select="translate($TempResultat,$TempLeer, ' ')"/>
				<xsl:attribute name="out"><xsl:value-of select="$Temp2"/></xsl:attribute>
			</xsl:if>
		</xsl:for-each>	
		<saxon:assign name="Temp3" select="$Temp2"/>
		<xsl:for-each select="//*[name()='relation']">
			<!-- Mit or funktioniert nicht, Test mit schlechten Laufzeit, in einer doppelt geschachtelten for Schleife funktioniert es auch nicht-->
			<xsl:if test="$Temp3=''">
			<xsl:variable name="TempLeer">'!</xsl:variable>	
			<xsl:variable name="Temp2ID">
				<xsl:value-of select="@IdBflow"/>
			</xsl:variable>
			<xsl:variable name="TempResultatTemp">
				<xsl:if test="$Temp2=''">	
					<xsl:value-of select="@IdBflow"/>
				</xsl:if>
				<xsl:if test="$Temp2!=''">
					<xsl:value-of select="concat($TempLeer,$Temp2ID)"/>
				</xsl:if>
			</xsl:variable>	
			<xsl:variable name="TempResultat">
				<xsl:value-of select="concat($Temp2,$TempResultatTemp)"/>
			</xsl:variable>	
			<xsl:if test="@from=$ElementID">
				<saxon:assign name="Temp2" select="translate($TempResultat,$TempLeer, ' ')"/>
				<xsl:attribute name="out"><xsl:value-of select="$Temp2"/></xsl:attribute>
			</xsl:if>
			</xsl:if>		
			<xsl:if test="$Temp3!=''">
				<xsl:variable name="TempLeer">'!</xsl:variable>	
				<xsl:variable name="Temp2ID">
					<xsl:value-of select="@IdBflow"/>
				</xsl:variable>
				<xsl:variable name="TempResultatTemp">
					<xsl:value-of select="concat($TempLeer,$Temp2ID)"/>
				</xsl:variable>	
				<xsl:variable name="TempResultat">
					<xsl:value-of select="concat($Temp2,$TempResultatTemp)"/>
				</xsl:variable>	
				<xsl:if test="@from=$ElementID">
					<saxon:assign name="Temp2" select="translate($TempResultat,$TempLeer, ' ')"/>
					<xsl:attribute name="out"><xsl:value-of select="$Temp2"/></xsl:attribute>
				</xsl:if>
			</xsl:if>
		</xsl:for-each>	
	</xsl:template>
</xsl:stylesheet>