<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xmi="http://www.omg.org/XMI"
xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:edu.toronto.cs.openome_model="http:///edu/toronto/cs/openome_model.ecore">
<xsl:output method="text" encoding="UTF-8" indent="no"/>
<xsl:strip-space elements="children"/>
<!-- Little Hack! Prevents unwanted white spaces in output  -->
<xsl:template match="text()" />
<!-- Container transformation -->
<xsl:template match="containers" name="containers">
	<xsl:variable name="forbiddenChars">'";</xsl:variable>
	<xsl:variable name="translatedName">
		<xsl:value-of select="translate(@name, $forbiddenChars, '')"/>
	</xsl:variable>
	<xsl:variable name="ContainerId">
		<xsl:value-of select="string(@xmi:id)"/>
	</xsl:variable>
	<xsl:variable name="ContainerString">
		<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/><xsl:text>('</xsl:text><xsl:value-of select="$ContainerId"/><xsl:text>').&#10;</xsl:text>
		<xsl:text>elementname('</xsl:text><xsl:value-of select="$ContainerId"/><xsl:text>', '</xsl:text><xsl:value-of select="$translatedName" /><xsl:text>')</xsl:text>
	</xsl:variable>
	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$ContainerString"/>
	</xsl:call-template>
	<xsl:for-each select="intentions">
		<xsl:variable name="ContainsString">
			<xsl:text>contains('</xsl:text><xsl:value-of select="$ContainerId"/><xsl:text>', '</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>')</xsl:text>
		</xsl:variable>
		<xsl:call-template name="PrologOutput">
			<xsl:with-param name="output" select="$ContainsString"/>
		</xsl:call-template>
		<xsl:call-template name="intentions"/>
	</xsl:for-each>
</xsl:template>
<!-- Dependencies transformation -->
<xsl:template match="dependencies" name="dependencies">
	<xsl:variable name="DependencyString">
		<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/><xsl:text>('</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>', '</xsl:text><xsl:value-of select="@dependencyFrom"/><xsl:text>', '</xsl:text><xsl:value-of select="@dependencyTo"/><xsl:text>')</xsl:text>
	</xsl:variable>
	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$DependencyString"/>
	</xsl:call-template>
</xsl:template>
<!-- Intentions transformation -->
<xsl:template match="intentions" name="intentions">
	<xsl:variable name="forbiddenChars">'";</xsl:variable>
	<xsl:variable name="translatedName">
		<xsl:value-of select="translate(@name, $forbiddenChars, '')"/>
	</xsl:variable>
	<xsl:variable name="IntentionString">
  		<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/><xsl:text>('</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>').&#10;</xsl:text>
  		<xsl:text>elementname('</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>', '</xsl:text><xsl:value-of select="$translatedName" /><xsl:text>')</xsl:text>
  	</xsl:variable>
	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$IntentionString"/>
	</xsl:call-template>
</xsl:template>
<!-- Decompositions transformation -->
<xsl:template match="decompositions" name="decompositions">
	<xsl:variable name="DecompositionString">
		<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/><xsl:text>('</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>', '</xsl:text><xsl:value-of select="@source"/><xsl:text>', '</xsl:text><xsl:value-of select="@target"/><xsl:text>')</xsl:text>
	</xsl:variable>
  	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$DecompositionString"/>
	</xsl:call-template>
</xsl:template>
<!-- Contributions transformation -->
<xsl:template match="contributions" name="contributions">
	<xsl:variable name="ContributionString">
 		<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/><xsl:text>('</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>', '</xsl:text><xsl:value-of select="@source"/><xsl:text>', '</xsl:text><xsl:value-of select="@target"/><xsl:text>')</xsl:text>
 	</xsl:variable>
  	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$ContributionString"/>
	</xsl:call-template>
</xsl:template>
<!-- Associations transformation -->
<xsl:template match="associations" name="associations">
	<xsl:variable name="AssociationString">
  		<xsl:value-of select="replace(substring-after(lower-case(@xmi:type), ':'), 'association', '')"/><xsl:text>('</xsl:text><xsl:value-of select="@xmi:id"/><xsl:text>', '</xsl:text><xsl:value-of select="@source"/><xsl:text>', '</xsl:text><xsl:value-of select="@target"/><xsl:text>')</xsl:text>
  	</xsl:variable>
  	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$AssociationString"/>
	</xsl:call-template>
</xsl:template>
<!-- Layout -->
<!-- Shapes transformation -->
<xsl:template match="children">
	<xsl:call-template name="shape"/>
	<xsl:for-each select="children">
		<xsl:variable name="Type">
			<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/>
		</xsl:variable>
		<xsl:if test="matches($Type,'basiccompartment')">
			<xsl:for-each select="children">
				<xsl:call-template name="shape"/>
			</xsl:for-each>
		</xsl:if>
	</xsl:for-each>
</xsl:template>
<!-- Edges transformation -->
<xsl:template match="edges">
	<xsl:variable name="EdgeString">
		<xsl:text>edge('</xsl:text><xsl:value-of select="@element"/><xsl:text>')</xsl:text>
	</xsl:variable>
	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$EdgeString"/>
	</xsl:call-template>
	<xsl:call-template name="Bendpoints">
		<xsl:with-param name="points" select="translate(translate(bendpoints/@points, '[]', ''), '$', 'd')"/>
		<xsl:with-param name="edgeId" select="@element"/>
	</xsl:call-template>
</xsl:template>
<!-- Shape template -->
<xsl:template name="shape">
	<xsl:param name="Type"/>
	<xsl:variable name="x">
		<xsl:value-of select="number(layoutConstraint/@x)"/>
	</xsl:variable>
	<xsl:variable name="y">
		<xsl:value-of select="number(layoutConstraint/@y)"/>
	</xsl:variable>
	<xsl:variable name="width">
		<xsl:value-of select="number(layoutConstraint/@width)"/>
	</xsl:variable>
	<xsl:variable name="height">
		<xsl:value-of select="number(layoutConstraint/@height)"/>
	</xsl:variable>
	<xsl:variable name="Type">
		<xsl:value-of select="substring-after(lower-case(@xmi:type), ':')"/>
	</xsl:variable>
	<xsl:variable name="ShapeString">
		<xsl:value-of select="$Type"/><xsl:text>('</xsl:text><xsl:value-of select="@element"/><xsl:text>', </xsl:text><xsl:value-of select="$x"/><xsl:text>, </xsl:text><xsl:value-of select="$y"/><xsl:text>, </xsl:text><xsl:value-of select="$width"/><xsl:text>, </xsl:text><xsl:value-of select="$height"/><xsl:text>)</xsl:text>
	</xsl:variable>
	<xsl:call-template name="PrologOutput">
		<xsl:with-param name="output" select="$ShapeString"/>
	</xsl:call-template>
</xsl:template>
<!-- Bendpoints template (Recursion) -->
<xsl:template name="Bendpoints">
	<xsl:param name="points"/>
	<xsl:param name="edgeId"/>
	<xsl:variable name="delimiter">d</xsl:variable>	
	<xsl:if test="not(empty($points))">
		<xsl:choose>
			<xsl:when test="contains($points, $delimiter)">
				<xsl:variable name="BendpointString">
					<xsl:text>bendpoint(</xsl:text><xsl:value-of select="$edgeId"/><xsl:text>, </xsl:text><xsl:value-of select="substring-before($points, $delimiter)"/><xsl:text>)</xsl:text>
				</xsl:variable>
				<xsl:call-template name="PrologOutput">
					<xsl:with-param name="output" select="$BendpointString"/>
				</xsl:call-template>
				<xsl:call-template name="Bendpoints">
					<xsl:with-param name="points" select="substring-after($points, $delimiter)"/>
					<xsl:with-param name="edgeId" select="$edgeId"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="BendpointString">
					<xsl:text>bendpoint(</xsl:text><xsl:value-of select="$edgeId"/><xsl:text>, </xsl:text><xsl:value-of select="$points"/><xsl:text>)</xsl:text>
				</xsl:variable>
				<xsl:call-template name="PrologOutput">
					<xsl:with-param name="output" select="$BendpointString"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:if>
</xsl:template>
<!-- PrologOutput Template -->
<xsl:template name="PrologOutput">
<xsl:param name="output"/>
<xsl:value-of select="concat(string($output), '.&#10;')"/>
</xsl:template>
</xsl:stylesheet>