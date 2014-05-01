<?xml version="1.0"?>
<xsl:stylesheet version="2.0" 
	xmlns:epml="http://www.epml.de" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:epc="org.bflow.toolbox.epc" 
	xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation" 
	xmlns:xmi="http://www.omg.org/XMI" 
	xmlns:saxon="http://saxon.sf.net/"
	xmlns:addon="http://org.bflow.addon"
	exclude-result-prefixes="saxon"  
	extension-element-prefixes="saxon">
<!-- angaben ueber das Ausgabe format, indent bedeutet einruecken entsprechend der Hirachie im XML Tree -->
<xsl:output method="xml" indent="yes" />
<!-- Anlegen globaler Variablen,-->
<xsl:variable name="description_ElementId" saxon:assignable="yes"/>
<xsl:variable name="descriptionArcRel_ElementId" saxon:assignable="yes"/>
<xsl:variable name="Modellname">
		<xsl:for-each select="//*[name()='notation:Diagram']">
			<xsl:value-of select="substring-before(@name, '.epc')"/>
        </xsl:for-each>
</xsl:variable>
<xsl:variable name="ModellId">
	<xsl:for-each select="//*[name()='epc:Epc']">
		<xsl:value-of select="@xmi:id"/>
    </xsl:for-each>
</xsl:variable> 
<xsl:variable name="AnzahlElemente" saxon:assignable="yes">
	<xsl:for-each select="//*[name()='AnzTemp']">
		<xsl:value-of select="@Anzahl"/>
    </xsl:for-each>
</xsl:variable> 
<xsl:variable name="AnzahlDefId" saxon:assignable="yes">1</xsl:variable> 
<xsl:variable name="DefIdZaehler" saxon:assignable="yes">1</xsl:variable> 
<!--### Informationen ###-->
<!-- Weitere Ausgliederungen in Templates nicht moeglich-->
<!-- EPC legt zwei Modelle in einem ab, unterscheidet nicht zwischen diesen.-->
<!-- Allerdings geht hierdurch bei der Rueckrichtung die Information des Namens verloren, bei der Richtung in epml werden diese in einem File abgelegt. Bei Mendling anschauen-->
<!-- fuer Sap alle xmi: mit xsi ersetzen-->
<!-- Seite 7 ist hierfuer ein schoenes Bsp. DefRef der Elemente verweist auf den Definitionsbereich und die Id in dieser. In einem neuen Relation Blog an der aktuellen Position wird diese Information uebernommen -->
<!-- Die Definitions spiegeln die logische Definiton der Struktur wieder, 2 Bloecke die formale definitione und die Umsetzung dieser, Definition beinhaelt auch die Defintion einer Beziehung (DataField..)-->
<!-- Erklaerung zu Definitions Elements: Elemente koennen in zwei oderer mehreren Epc-Modellen in einer .epml Datei vorkommen. Damit diese nicht immer doppelt angelgt werden muessen, koennen diese ueber die Defid referenziert werden. Wenn Elemente eine Semantisch gleiche Bedeutung haben. Wird verwendet damit gleiche Elemente nicht mehrmals erscheinen.-->
<!-- Description funktioniert-->
<!-- Relation ist eine Art arc Seite 7, Verbindung zwischen Appl, Data, Parti-->
<!--### -->
<!-- ### TODO ###-->
<!-- TODO kann auch die ganzen Variablen weglassen-->
<!-- ### ###-->
<xsl:variable name="TempID" saxon:assignable="yes">1</xsl:variable>
<xsl:variable name="Temp">1</xsl:variable>
<!-- Root Element -->
	<xsl:template match="epc:Epc">
	<epml:epml xmlns:epml ="http://www.epml.de">
		<!-- Anlegen Temp Struktur fuer defintions-->
		<Temp>
			<!-- anlegen der Definitions-->
			<definitions>
				<!-- 1. Event-->
				<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:Event']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
				<!-- 2. function-->
				<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:Function']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
				<!-- 3. Xor-->
				<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:XOR']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
				<!-- 4. Or-->
				<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:OR']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
				<!-- 5. And-->
				<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:AND']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
				<!-- 6. Process-->
				<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:ProcessInterface']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
				<!-- 7. Relation-->
				<xsl:for-each select="//*[name()='connections'][@xmi:type='epc:Relation']">	
					<xsl:variable name="EmptydefRef">
						<xsl:value-of select="@defRef"/>
					</xsl:variable>
					<definition>
						<xsl:if test="$EmptydefRef=''">
							<xsl:attribute name="defId"><xsl:value-of select="$AnzahlDefId"/></xsl:attribute>
							<saxon:assign name="AnzahlDefId" select="$AnzahlDefId+1"/>
						</xsl:if>
						<xsl:if test="$EmptydefRef!=''">
							<xsl:attribute name="defId"><xsl:value-of select="$EmptydefRef"/></xsl:attribute>
						</xsl:if>
					</definition>
				</xsl:for-each>
			</definitions>
		</Temp>	
			<coordinates xOrigin="leftToRight" yOrigin="topToBottom" />	
			<directory name="Root">
				<epc>
					<!-- anlegen Modell Id-->	
					<xsl:attribute name="epcId"><xsl:value-of select="$TempID"/></xsl:attribute>
					<!-- Der name wird ausgelesen aus der Diagramm information, die Ausgabe muesste  so funktionieren -->
					<xsl:attribute name="name"><xsl:value-of select="$Modellname"/></xsl:attribute>
					<!-- anlegen neues Attributes, mit alten id-->
					<xsl:attribute name="IdBflow"><xsl:value-of select="$ModellId"/></xsl:attribute>
					<!-- Fallunterscheidung nach Element Typ-->
					<saxon:assign name="TempID" select="$TempID+$Temp"/>
					<!-- 1. Event-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:Event']">
						<xsl:variable name="ElementName">
							<xsl:value-of select="@name"/>
						</xsl:variable>
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>																	
							<event>
								<xsl:call-template name="CreateElemente">
									<xsl:with-param name="ElementIDBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:with-param>
									<xsl:with-param name="ElementName"><xsl:value-of select="$ElementName"/></xsl:with-param>
								</xsl:call-template>
							</event>
					</xsl:for-each>
					<!--2. Funktion, Type 5005-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:Function']">
						<xsl:variable name="ElementName">
							<xsl:value-of select="@name"/>
						</xsl:variable>
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<function>
							<xsl:call-template name="CreateElemente">
								<xsl:with-param name="ElementIDBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:with-param>
								<xsl:with-param name="ElementName"><xsl:value-of select="$ElementName"/></xsl:with-param>
							</xsl:call-template>					
						</function>
					</xsl:for-each>			
					<!-- Es exisitieren zwei Arten von Typs 2003.-->
					<!-- 3. Bei Konnektoren nur eine Art von Typ-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:AND']">
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<and>
							<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>
							<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
							<xsl:attribute name="defRef"><xsl:value-of select="$DefIdZaehler"/></xsl:attribute>
							<xsl:if test="@defRef!=''">
								<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/> </xsl:attribute>
								<saxon:assign name="DefIdZaehler" select="'$DefIdZaehler'-1"/>
							</xsl:if>
							<saxon:assign name="DefIdZaehler" select="$DefIdZaehler+1"/>
							<xsl:call-template name="description">
								<xsl:with-param name="ElementIDBflow">
									<xsl:value-of select="$ElementIDBflow"/>
								</xsl:with-param>
							</xsl:call-template>
							<xsl:call-template name="Graphics">
								<xsl:with-param name="ElementIDBflow">
									<xsl:value-of select="$ElementIDBflow"/>
								</xsl:with-param>
								<!-- Als Param auch noch with und hiegh uebergeben-->
								<xsl:with-param name="height">20</xsl:with-param>
								<xsl:with-param name="width">20</xsl:with-param>
							</xsl:call-template>
							<saxon:assign name="TempID" select="$TempID+$Temp"/>
						</and>
					</xsl:for-each>
					<!-- 4. Konnektoren or -->
					<!-- Typ 2001-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:OR']">
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<or>
							<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>
							<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
							<xsl:attribute name="defRef"><xsl:value-of select="$DefIdZaehler"/></xsl:attribute>
							<xsl:if test="@defRef!=''">
								<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/> </xsl:attribute>
								<saxon:assign name="DefIdZaehler" select="'$DefIdZaehler'-1"/>
							</xsl:if>
							<saxon:assign name="DefIdZaehler" select="$DefIdZaehler+1"/>
							<xsl:call-template name="description">
								<xsl:with-param name="ElementIDBflow">
									<xsl:value-of select="$ElementIDBflow"/>
								</xsl:with-param>
							</xsl:call-template>
							<xsl:call-template name="Graphics">
								<xsl:with-param name="ElementIDBflow">
									<xsl:value-of select="$ElementIDBflow"/>
								</xsl:with-param>
								<!-- Als Param auch noch with und hiegh uebergeben-->
								<xsl:with-param name="height">20</xsl:with-param>
								<xsl:with-param name="width">20</xsl:with-param>
							</xsl:call-template>
							<saxon:assign name="TempID" select="$TempID+$Temp"/>
						</or>
					</xsl:for-each>
					<!-- 5. Konnektoren xor, Type 2008 -->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:XOR']">
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<xor>
							<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>
							<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
							<xsl:attribute name="defRef"><xsl:value-of select="$DefIdZaehler"/></xsl:attribute>
							<xsl:if test="@defRef!=''">
								<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/> </xsl:attribute>
								<saxon:assign name="DefIdZaehler" select="'$DefIdZaehler'-1"/>
							</xsl:if>
							<saxon:assign name="DefIdZaehler" select="$DefIdZaehler+1"/>
							<xsl:call-template name="description">
								<xsl:with-param name="ElementIDBflow">
									<xsl:value-of select="$ElementIDBflow"/>
								</xsl:with-param>
							</xsl:call-template>
							<xsl:call-template name="Graphics">
								<xsl:with-param name="ElementIDBflow">
									<xsl:value-of select="$ElementIDBflow"/>
								</xsl:with-param>
								<!-- Als Param auch noch with und hiegh uebergeben-->
								<xsl:with-param name="height">20</xsl:with-param>
								<xsl:with-param name="width">20</xsl:with-param>
							</xsl:call-template>
							<saxon:assign name="TempID" select="$TempID+$Temp"/>
						</xor>
					</xsl:for-each>
					<!-- 6. Application, nach Seite 7 koennen diese ueber Attribute und graphic Elemente verfuegen -->
					<!-- Typ 5002-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:Application']">
						<xsl:variable name="ElementName">
							<xsl:value-of select="@name"/>
						</xsl:variable>
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<application>					
							<xsl:call-template name="CreateElementeOhneDef">
								<xsl:with-param name="ElementIDBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:with-param>
								<xsl:with-param name="ElementName"><xsl:value-of select="$ElementName"/></xsl:with-param>
							</xsl:call-template>	
						</application>
					</xsl:for-each>
					<!-- 7. Partizipation/Teilnehmer,optional, wie bei Application kann dieses Element, ueber graphics verfuegen  -->
					<!-- Type 5001-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:Participant']">
						<xsl:variable name="ElementName">
							<xsl:value-of select="@name"/>
						</xsl:variable>
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<participant>
							<xsl:call-template name="CreateElementeOhneDef">
								<xsl:with-param name="ElementIDBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:with-param>
								<xsl:with-param name="ElementName"><xsl:value-of select="$ElementName"/></xsl:with-param>
							</xsl:call-template>
						</participant>
					</xsl:for-each>
					<!-- 8. lege hier das DataField, aufgrundlage der Vorlage der Technical Terms an, TODO wichtig dies muss bei Bedarf angeglichen werden-->
					<!-- Typ 5013-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:TechnicalTerm']">
						<xsl:variable name="ElementName">
							<xsl:value-of select="@name"/>
						</xsl:variable>
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<dataField>
							<xsl:call-template name="CreateElementeOhneDef">
								<xsl:with-param name="ElementIDBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:with-param>
								<xsl:with-param name="ElementName"><xsl:value-of select="$ElementName"/></xsl:with-param>
							</xsl:call-template>
						</dataField>
					</xsl:for-each>
					<!-- 9. processInterface, verzweigt auf andere Prozesse -->
					<!-- Typ 2005, 5003-->
					<xsl:for-each select="//*[name()='elements'][@xmi:type='epc:ProcessInterface']">
						<xsl:variable name="ElementName">
							<xsl:value-of select="@name"/>
						</xsl:variable>
						<xsl:variable name="ElementIDBflow">
							<xsl:value-of select="@xmi:id"/>
						</xsl:variable>
						<processInterface>
							<xsl:call-template name="CreateElemente">
								<xsl:with-param name="ElementIDBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:with-param>
								<xsl:with-param name="ElementName"><xsl:value-of select="$ElementName"/></xsl:with-param>
							</xsl:call-template>
						</processInterface>
					</xsl:for-each>								
				</epc>
			</directory>
			<!-- Anlegen AnzahlTemp fuer das weitere anlegen er DefId und der durchlaufenden Anzahl-->
			<AnzTemp>
				<xsl:attribute name="Anzahl"> </xsl:attribute>
			</AnzTemp>
			<!-- Anlegen arcs und Relations als zwischen Schritt-->
			<xsl:for-each select="//*[name()='connections'][@xmi:type='epc:Arc']">
				<xsl:variable name="ElementIDBflow">
					<xsl:value-of select="@xmi:id"/>
				</xsl:variable>
				<!-- sind die Korrekten Bflow ids die eingelesen werden-->
				<xsl:variable name="ArcFrom">
					<xsl:value-of select="@from"/>
				</xsl:variable>
				<xsl:variable name="ArcTO">
					<xsl:value-of select="@to"/>
				</xsl:variable>																
				<arcTemp>
				<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>
				<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>	
				<xsl:call-template name="descriptionArcRel">
					<xsl:with-param name="ElementIDBflow">
						<xsl:value-of select="$ElementIDBflow"/>
					</xsl:with-param>
				</xsl:call-template>																		
				<flow>	
					<xsl:attribute name="source"><xsl:value-of select="$ArcFrom"/></xsl:attribute>						
					<!-- Typ 5004 ist Event.-->
					<xsl:attribute name="target"><xsl:value-of select="$ArcTO"/></xsl:attribute>										
				</flow>						
				<graphics>
					<!-- einlesen Koordinaten Ausgangknoten--> 
					<xsl:for-each select="//*[name()='children'][@element=$ArcFrom]">								
						<xsl:variable name="height">
							<xsl:value-of select="layoutConstraint/@height"/>
						</xsl:variable>
						<xsl:variable name="width">
							<xsl:value-of select="layoutConstraint/@width"/>
						</xsl:variable>
						<xsl:variable name="Konnektor">
							<xsl:value-of select="@type"/>
						</xsl:variable>
						<xsl:if test="$height!=''">	
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>					
							<!-- schreiben der graphischen Ausrichtung falls Konnektor-->
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
						<xsl:if test="$height=''">		
							<!-- Ausgabe nur wenn Konnektor leer ist, hoffe das hier wenigstens der or Operator funktioniert-->
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+30"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>					
							<!-- schreiben der graphischen Ausrichtung falls Konnektor-->
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
					</xsl:for-each>					
					<!-- Zielknoten--> 
					<xsl:for-each select="//*[name()='children'][@element=$ArcTO]">		
						<xsl:variable name="Konnektor">
							<xsl:value-of select="@type"/>
						</xsl:variable>
						<xsl:variable name="height">
							<xsl:value-of select="layoutConstraint/@height"/>
						</xsl:variable>
						<xsl:variable name="width">
							<xsl:value-of select="layoutConstraint/@width"/>
						</xsl:variable>
						<xsl:if test="$height!=''">	
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>					
							<!-- schreiben der graphischen Ausrichtung falls Konnektor-->
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
						<xsl:if test="$height=''">		
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+30"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>				
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX+10"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX+10"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
					</xsl:for-each>				
					</graphics>		
					<saxon:assign name="TempID" select="$TempID+$Temp"/>					
				</arcTemp>
			</xsl:for-each>
			<!-- Relation, hat im Beispiel epml kein graphic Teil -->
			<xsl:for-each select="//*[name()='connections'][@xmi:type='epc:Relation']">
				<xsl:variable name="ElementIDBflow">
					<xsl:value-of select="@xmi:id"/>
				</xsl:variable>
				<xsl:variable name="RelFrom">
					<xsl:value-of select="@from"/>
				</xsl:variable>
				<xsl:variable name="RelTO">
					<xsl:value-of select="@to"/>
				</xsl:variable>
				<!-- sind die Korrekten Bflow ids die eingelesen werden-->
				<xsl:variable name="ArcFrom">
					<xsl:value-of select="@from"/>
				</xsl:variable>
				<xsl:variable name="ArcTO">
					<xsl:value-of select="@to"/>
				</xsl:variable>	
				<relation>
				<!-- wichtig, from und to werden hier als Attribute angelegt -->
					<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>
					<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
					<xsl:attribute name="from"><xsl:value-of select="$RelFrom"/></xsl:attribute>
					<xsl:attribute name="to"><xsl:value-of select="$RelTO"/></xsl:attribute>
					<xsl:attribute name="defRef"><xsl:value-of select="$DefIdZaehler"/></xsl:attribute>
					<xsl:if test="@defRef!=''">
						<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/> </xsl:attribute>
						<saxon:assign name="DefIdZaehler" select="'$DefIdZaehler'-1"/>
					</xsl:if>
					<saxon:assign name="DefIdZaehler" select="$DefIdZaehler+1"/>
					<saxon:assign name="TempID" select="$TempID+$Temp"/>
					<xsl:call-template name="descriptionArcRel">
						<xsl:with-param name="ElementIDBflow">
							<xsl:value-of select="$ElementIDBflow"/>
						</xsl:with-param>
					</xsl:call-template>	
					<graphics>
					<!-- einlesen Koordinaten Ausgangknoten--> 
					<xsl:for-each select="//*[name()='children'][@element=$ArcFrom]">								
						<xsl:variable name="height">
							<xsl:value-of select="layoutConstraint/@height"/>
						</xsl:variable>
						<xsl:variable name="width">
							<xsl:value-of select="layoutConstraint/@width"/>
						</xsl:variable>
						<xsl:variable name="Konnektor">
							<xsl:value-of select="@type"/>
						</xsl:variable>
						<xsl:if test="$height!=''">	
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>					
							<!-- schreiben der graphischen Ausrichtung falls Konnektor-->
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+$height"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
						<xsl:if test="$height=''">		
							<!-- Ausgabe nur wenn Konnektor leer ist, hoffe das hier wenigstens der or Operator funktioniert-->
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+30"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>					
							<!-- schreiben der graphischen Ausrichtung falls Konnektor-->
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y+20"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
					</xsl:for-each>					
					<!-- Zielknoten--> 
					<xsl:for-each select="//*[name()='children'][@element=$ArcTO]">		
						<xsl:variable name="Konnektor">
							<xsl:value-of select="@type"/>
						</xsl:variable>
						<xsl:variable name="height">
							<xsl:value-of select="layoutConstraint/@height"/>
						</xsl:variable>
						<xsl:variable name="width">
							<xsl:value-of select="layoutConstraint/@width"/>
						</xsl:variable>
						<xsl:if test="$height!=''">	
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>					
							<!-- schreiben der graphischen Ausrichtung falls Konnektor-->
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="round(layoutConstraint/@x+$width*0.5) div 1"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
						<xsl:if test="$height=''">		
							<xsl:if test="$Konnektor!='2008'">	
							<xsl:if test="$Konnektor!='2003'">
							<xsl:if test="$Konnektor!='2001'">
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+30"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							</xsl:if>
							</xsl:if>				
							<!-- xor 2008-->
							<xsl:if test="$Konnektor='2008'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX+10"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- or, 2001-->
							<xsl:if test="$Konnektor='2001'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX+10"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
							<!-- and, 2003-->
							<xsl:if test="$Konnektor='2003'">	
								<xsl:variable name="CoorX">
									<xsl:value-of select="layoutConstraint/@x+10"/>
								</xsl:variable>	
								<xsl:variable name="CooY">
									<xsl:value-of select="layoutConstraint/@y"/>
								</xsl:variable>
								<position>
									<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
									<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
								</position>
							</xsl:if>
						</xsl:if>
					</xsl:for-each>				
					</graphics>	
				</relation>
			</xsl:for-each>	
		</epml:epml>
		<xsl:apply-templates/>	
	</xsl:template>
	<!-- Erzeugen der Graphischen Information fuer epml. Alle Elemente-->
	<xsl:template name="Graphics">
		<xsl:param name="ElementIDBflow"/>
		<xsl:param name="height"/>
		<xsl:param name="width"/>
		<graphics>
			<position>
			<!-- Typ 5004 ist Event.-->
			<xsl:for-each select="//*[name()='children'][@element=$ElementIDBflow]">			
			<!-- Problem Variablen muessen global definiert werden-->				
				<xsl:variable name="CoorX">
					<xsl:value-of select="layoutConstraint/@x"/>
				</xsl:variable>
				<xsl:variable name="CooY">
					<xsl:value-of select="layoutConstraint/@y"/>
				</xsl:variable>
				<!--normal 20, 60, zu klein -->
				<xsl:attribute name="x"><xsl:value-of select="$CoorX"/></xsl:attribute>
				<xsl:attribute name="y"><xsl:value-of select="$CooY"/></xsl:attribute>
				<xsl:attribute name="height"><xsl:value-of select="$height"/></xsl:attribute>
				<xsl:attribute name="width"><xsl:value-of select="$width"/></xsl:attribute>
				<!-- fuer die Zukunft falls epc aufstockt-->
				<xsl:if test="layoutConstraint/@width!=''">
					<xsl:attribute name="height"><xsl:value-of select="layoutConstraint/@height"/></xsl:attribute>
					<xsl:attribute name="width"><xsl:value-of select="layoutConstraint/@width"/></xsl:attribute>
				</xsl:if>
			</xsl:for-each>	
			</position>
		</graphics>
	</xsl:template>
	<!-- Erzeugen der Elemente-->
	<xsl:template name="CreateElemente">
		<xsl:param name="ElementIDBflow"/>
		<xsl:param name="ElementName"/>
		<!-- TODO muss Align im children suchen-->
		<xsl:for-each select="//*[name()='children'][@element=$ElementIDBflow]">
			<xsl:variable name="Alig" select="styles/@textAlignment"/>
			<!-- Ermittlung Alignment-->	
			<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>	
			<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
			<xsl:attribute name="defRef"><xsl:value-of select="$DefIdZaehler"/></xsl:attribute>
			<xsl:if test="$Alig!=''">
				<xsl:attribute name="textAlignment"><xsl:value-of select="$Alig"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="@defRef!=''">
				<xsl:attribute name="defRef"><xsl:value-of select="@defRef"/> </xsl:attribute>
				<saxon:assign name="DefIdZaehler" select="'$DefIdZaehler'-1"/>
			</xsl:if>
			<saxon:assign name="DefIdZaehler" select="$DefIdZaehler+1"/>
			<name><xsl:value-of select="$ElementName"/></name>		
			<!-- describtion-->
			<xsl:call-template name="description">
				<xsl:with-param name="ElementIDBflow">
					<xsl:value-of select="$ElementIDBflow"/>
				</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="Graphics">
				<xsl:with-param name="ElementIDBflow">
					<xsl:value-of select="$ElementIDBflow"/>
				</xsl:with-param>
				<!-- Als Param auch noch with und height uebergeben-->
				<xsl:with-param name="height">20</xsl:with-param>
				<xsl:with-param name="width">60</xsl:with-param>
			</xsl:call-template>	
			<saxon:assign name="TempID" select="$TempID+$Temp"/>
		</xsl:for-each>
	</xsl:template>
	<!-- Erzeugen der Elemente ohne defRef: Application, Data, -->
	<xsl:template name="CreateElementeOhneDef">
		<xsl:param name="ElementIDBflow"/>
		<xsl:param name="ElementName"/>
		<xsl:for-each select="//*[name()='children'][@element=$ElementIDBflow]">
			<xsl:variable name="Alig" select="styles/@textAlignment"/>
			<!-- Ermittlung Alignment-->	
			<xsl:attribute name="id"><xsl:value-of select="$TempID"/></xsl:attribute>	
			<xsl:attribute name="IdBflow"><xsl:value-of select="$ElementIDBflow"/></xsl:attribute>
			<xsl:if test="styles/@textAlignment!=''">
				<xsl:attribute name="textAlignment"><xsl:value-of select="styles/@textAlignment"/></xsl:attribute>
			</xsl:if>
			<name><xsl:value-of select="$ElementName"/></name>		
			<xsl:call-template name="description">
				<xsl:with-param name="ElementIDBflow">
					<xsl:value-of select="$ElementIDBflow"/>
				</xsl:with-param>
			</xsl:call-template>
			<xsl:call-template name="Graphics">
				<xsl:with-param name="ElementIDBflow">
					<xsl:value-of select="$ElementIDBflow"/>
				</xsl:with-param>
				<!-- Als Param auch noch with und hiegh uebergeben-->
				<xsl:with-param name="height">20</xsl:with-param>
				<xsl:with-param name="width">60</xsl:with-param>
			</xsl:call-template>	
			<saxon:assign name="TempID" select="$TempID+$Temp"/>
		</xsl:for-each>
	</xsl:template>
	<!-- Auswahl der Description funktioniert-->
	<xsl:template name="description">
		<xsl:param name="ElementIDBflow"/>
		<!-- selektiere die Description uber den typ='node', es darf keine weiteres solches Element bie bflow vorgesehen sein, welches ebenfalls eine description enthaelt-->
		<xsl:for-each select="//*[name()='children'][@type='Note']">	
			<xsl:variable name="IDChildDescription" select="@xmi:id"/>
			<!-- Selektion der description-->
			<xsl:variable name="des" select="styles/@description"/>
			<!-- ermittlen der Children ID-->
			<xsl:for-each select="//*[name()='children'][@element=$ElementIDBflow]">
				<saxon:assign name="description_ElementId" select="@xmi:id"/>
			</xsl:for-each>
			<xsl:if test="$des!=''"> 
				<xsl:for-each select="//*[name()='edges'][@source=$IDChildDescription]">
					<xsl:variable name="Tmp" select="@target"/>
					<xsl:variable name="ElementId" select="$description_ElementId"/>
					<xsl:if test="$ElementId=@target"> 
						<description><xsl:value-of select="$des"/></description>
					</xsl:if>
				</xsl:for-each> 
			 </xsl:if> 
		 </xsl:for-each>	
	</xsl:template>
	<!-- Description fuer arc und Relation-->
	<xsl:template name="descriptionArcRel">
		<xsl:param name="ElementIDBflow"/>
			<!-- selektiere die Description uber den typ='node', es darf keine weiteres solches Element bie bflow vorgesehen sein, welches ebenfalls eine description enthaelt-->
		<xsl:for-each select="//*[name()='children'][@type='Note']">
			<xsl:variable name="IDChildDescription" select="@xmi:id"/>
			<!-- Selektion der description-->
			<xsl:variable name="des" select="styles/@description"/>		
			<!-- ermittlen der Children ID-->
			<xsl:for-each select="//*[name()='edges'][@element=$ElementIDBflow]">
				<saxon:assign name="descriptionArcRel_ElementId" select="@xmi:id"/>
			</xsl:for-each>
			<xsl:if test="$des!=''"> 
				<xsl:for-each select="//*[name()='edges'][@source=$IDChildDescription]">
					<xsl:variable name="Tmp" select="@target"/>
					<xsl:variable name="ElementId" select="$descriptionArcRel_ElementId"/>
					<xsl:if test="$ElementId=@target"> 
						<description><xsl:value-of select="$des"/></description>
					</xsl:if>
				</xsl:for-each> 
			 </xsl:if> 
		 </xsl:for-each>	
	</xsl:template>	
</xsl:stylesheet>