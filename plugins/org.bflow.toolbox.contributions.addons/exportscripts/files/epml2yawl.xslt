<?xml version="1.0"?>
<xsl:stylesheet version="2.0" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:saxon="http://saxon.sf.net/" 
	extension-element-prefixes="saxon" 
	xmlns:loop="http://informatik.hu-berlin.de/loop">
<!-- angaben ueber das Ausgabe format, indent bedeutet einruecken entsprechend der Hirachie im XML Tree -->
<xsl:output method="xml" indent="yes" />
<!-- ### Anmerkungen ### -->
<!-- Skript zur Konvertierung von EPML to yawl-->
<!-- Die erzeugte YAWL-Datei ist fehlerhaft lt. YAWL-Schema, wenn in der EPK Ereignisse direkt aufeinanderfolgen -->
<!-- Root Element -->

<xsl:variable name="Boolean" saxon:assignable="yes"/>
<xsl:variable name="CodeInfo" saxon:assignable="yes"/>
<xsl:variable name="CodeInfoJoin" saxon:assignable="yes"/>
<xsl:variable name="TempJoin" saxon:assignable="yes"/>
<xsl:variable name="TempSplit" saxon:assignable="yes"/>

	<xsl:template match="/">
		<specificationSet xmlns="http://www.citi.qut.edu.au/yawl" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="Beta 4" xsi:schemaLocation="http://www.citi.qut.edu.au/yawl d:/yawl/schema/YAWL_SchemaBeta4.xsd">
			<specification uri="dummy.ywl">
				<metaData />
				<schema xmlns="http://www.w3.org/2001/XMLSchema" />
				<decomposition id="epc_to_yawl" isRootNet="true" xsi:type="NetFactsType">
					<!-- Start des Modells-->
					<processControlElements>
					<!-- set input condition-->
						<inputCondition id="yawl_input_condition">
  							<flowsInto>
								<!-- Verzweigung auf kuenstliche anzulegende Element-->
    							<nextElementRef id="yawl_initial_or" />
  							</flowsInto>
						</inputCondition>
						<task id="yawl_initial_or">
							<xsl:for-each select="//*[name()='event']">
								<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>
								<xsl:for-each select="//*[name()='arc']">				
									<xsl:if test="flow/@target=$EventID">
										<saxon:assign name="Boolean">false</saxon:assign>		
									</xsl:if>
								</xsl:for-each>
								<xsl:if test="$Boolean!='false'">
									<flowsInto>
										<nextElementRef>
											<xsl:attribute name="id">i_<xsl:value-of select="$EventID"/></xsl:attribute>
										</nextElementRef>
									</flowsInto>
								</xsl:if>
							</xsl:for-each>
							<xsl:for-each select="//*[name()='function']">
								<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>
								<xsl:for-each select="//*[name()='arc']">				
									<xsl:if test="flow/@target=$EventID">
										<saxon:assign name="Boolean">false</saxon:assign>	
									</xsl:if>
								</xsl:for-each>
								<xsl:if test="$Boolean!='false'">
									<flowsInto>
										<nextElementRef>
											<xsl:attribute name="id">i_<xsl:value-of select="$EventID"/></xsl:attribute>
										</nextElementRef>
									</flowsInto>
								</xsl:if>
							</xsl:for-each>
							<join code="and" />
							<split code="or" />
							<decomposesTo id="d_yawl_initial_or" />
						</task>												
						<!-- 1. event-->
						<xsl:for-each select="//*[name()='event']">	
							<condition>
								<xsl:attribute name="id">i_<xsl:value-of select="@id"/></xsl:attribute>
								<name><xsl:value-of select="name"/></name>
								<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>					
								<!-- wird fuer alle Elemente ausgefuehrt und demnach auch mehrere flowsInto angelegt-->
								<xsl:for-each select="//*[name()='arc']">				
									<xsl:if test="flow/@source=$EventID">
										<xsl:variable name="TargetID"><xsl:value-of select="flow/@target"/></xsl:variable>
										<!-- 1. Event-->
										<xsl:for-each select="//*[name()='event'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>
										</xsl:for-each>
										<!-- 2. function-->
										<xsl:for-each select="//*[name()='function'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>
										</xsl:for-each>
										<xsl:for-each select="//*[name()='or'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>
										</xsl:for-each>
										<!-- 7. Konnektor and-->
										<xsl:for-each select="//*[name()='and'][@id=$TargetID]">	
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>							
										</xsl:for-each>
										<!-- 8. Konnektor xor-->
										<xsl:for-each select="//*[name()='xor'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>
										</xsl:for-each>									
										</xsl:if>		
								</xsl:for-each>  
								<xsl:if test="$CodeInfo=''">
									<flowsInto>
										<nextElementRef>
											<xsl:attribute name="id">yawl_final_or</xsl:attribute>
										</nextElementRef>
									</flowsInto>
								</xsl:if>																			
							</condition>
						</xsl:for-each>
						<!-- 2. function-->
						<xsl:for-each select="//*[name()='function']">	
							<task>
								<xsl:attribute name="id">i_<xsl:value-of select="@id"/></xsl:attribute>
								<name><xsl:value-of select="name"/></name>
								<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>					
								<xsl:for-each select="//*[name()='arc']">		
										<xsl:if test="flow/@source=$EventID">
											<xsl:variable name="TargetID"><xsl:value-of select="flow/@target"/></xsl:variable>
											<xsl:for-each select="//*[name()='event'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
												<saxon:assign name="CodeInfo">and</saxon:assign>
											</xsl:for-each>
											<!-- 2. function-->
											<xsl:for-each select="//*[name()='function'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
												<saxon:assign name="CodeInfo">and</saxon:assign>
											</xsl:for-each>
											<xsl:for-each select="//*[name()='or'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>
										</xsl:for-each>
										<!-- 7. Konnektor and-->
										<xsl:for-each select="//*[name()='and'][@id=$TargetID]">	
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>							
										</xsl:for-each>
										<!-- 8. Konnektor xor-->
										<xsl:for-each select="//*[name()='xor'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
											<saxon:assign name="CodeInfo">and</saxon:assign>
										</xsl:for-each>						
										</xsl:if>		
									</xsl:for-each>  
									<xsl:if test="$CodeInfo=''">
										<flowsInto>
											<nextElementRef>
												<xsl:attribute name="id">yawl_final_or</xsl:attribute>
											</nextElementRef>
										</flowsInto>
									</xsl:if>
								<join>
									<xsl:for-each select="//*[name()='arc']">
										<xsl:if test="flow/@target=$EventID">
											<xsl:variable name="SourceID"><xsl:value-of select="flow/@source"/></xsl:variable>
											<xsl:for-each select="//*[name()='xor'][@id=$SourceID]">
												<saxon:assign name="CodeInfoJoin">xor</saxon:assign>
											</xsl:for-each>
											<xsl:for-each select="//*[name()='or'][@id=$SourceID]">
												<saxon:assign name="CodeInfoJoin">or</saxon:assign>
											</xsl:for-each>
											<xsl:for-each select="//*[name()='and'][@id=$SourceID]">
												<saxon:assign name="CodeInfoJoin">and</saxon:assign>
											</xsl:for-each>
										</xsl:if>
									</xsl:for-each>
									<xsl:if test="$CodeInfoJoin!=''">
										<xsl:attribute name="code"><xsl:value-of select="$CodeInfoJoin"/></xsl:attribute>
									</xsl:if>			
									<xsl:if test="$CodeInfoJoin=''">
										<xsl:attribute name="code">and</xsl:attribute>
									</xsl:if>										
									<xsl:if test="$CodeInfo=''">
										<xsl:attribute name="code">and</xsl:attribute>
									</xsl:if>			
								</join>
								<split>
									<xsl:if test="$CodeInfo!=''">
										<xsl:attribute name="code"><xsl:value-of select="$CodeInfo"/></xsl:attribute>
									</xsl:if>
									<xsl:if test="$CodeInfo=''">
										<xsl:attribute name="code">and</xsl:attribute>
									</xsl:if>
								</split>
							</task>
						</xsl:for-each>				
						<!-- TODO Decompostions bei Konnektoren rausnehmen-->
						<!-- 3. Konnektor or-->
						<xsl:for-each select="//*[name()='or']">
						<task>
							<xsl:attribute name="id">i_<xsl:value-of select="@id"/></xsl:attribute>
							<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>					
							<xsl:for-each select="//*[name()='arc']">
								<!-- TODO kann hier eigentlich die Typ unterscheidung weglassen-->
								<xsl:if test="flow/@source=$EventID">
											<xsl:variable name="TargetID"><xsl:value-of select="flow/@target"/></xsl:variable>
											<xsl:for-each select="//*[name()='event'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
											</xsl:for-each>
											<!-- 2. function-->
											<xsl:for-each select="//*[name()='function'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
											</xsl:for-each>
											<xsl:for-each select="//*[name()='or'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
										</xsl:for-each>
										<!-- 7. Konnektor and-->
										<xsl:for-each select="//*[name()='and'][@id=$TargetID]">	
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>							
										</xsl:for-each>
										<!-- 8. Konnektor xor-->
										<xsl:for-each select="//*[name()='xor'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
										</xsl:for-each>					
								</xsl:if>
							</xsl:for-each>
							<join>
								<xsl:for-each select="//*[name()='arc']">
									<xsl:if test="flow/@target=$EventID">
										<!-- Bei erster Kante schreiben True-->
										<xsl:if test="$TempJoin=''">
											<saxon:assign name="TempJoin">true</saxon:assign>
										</xsl:if>
										<!-- Bei zweiten Kante schreiben false-->
										<xsl:if test="$TempJoin='true'">
											<saxon:assign name="TempJoin">false</saxon:assign>
										</xsl:if>
										<!-- restlichen Faelle werden durch if nicht besucht-->
									</xsl:if>
								</xsl:for-each>
								<xsl:if test="$TempJoin='true'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>			
								<xsl:if test="$TempJoin='false'">
									<xsl:attribute name="code">or</xsl:attribute>
								</xsl:if>			
							</join>
							<split>
								<xsl:for-each select="//*[name()='arc']">
									<xsl:if test="flow/@source=$EventID">
										<!-- Bei erster Kante schreiben True-->
										<xsl:if test="$TempSplit=''">
											<saxon:assign name="TempSplit">true</saxon:assign>
										</xsl:if>
										<!-- Bei zweiten Kante schreiben false-->
										<xsl:if test="$TempSplit='true'">
											<saxon:assign name="TempSplit">false</saxon:assign>
										</xsl:if>
										<!-- restlichen Faelle werden durch if nicht besucht-->
									</xsl:if>
								</xsl:for-each>							
								<xsl:if test="$TempSplit='true'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>
								<xsl:if test="$TempSplit='false'">
									<xsl:attribute name="code">or</xsl:attribute>
								</xsl:if>
							</split>	
							<decomposesTo>
								<xsl:attribute name="id">or_d_<xsl:value-of select="@id"/></xsl:attribute>
							</decomposesTo>
						</task>
						</xsl:for-each>
						<!-- 4. Konnektor and-->
						<xsl:for-each select="//*[name()='and']">
						<task>
							<xsl:attribute name="id">i_<xsl:value-of select="@id"/></xsl:attribute>
							<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>					
							<xsl:for-each select="//*[name()='arc']">
								<!-- TODO kann hier eigentlich die Typ unterscheidung weglassen-->
								<xsl:if test="flow/@source=$EventID">
											<xsl:variable name="TargetID"><xsl:value-of select="flow/@target"/></xsl:variable>
											<xsl:for-each select="//*[name()='event'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
											</xsl:for-each>
											<!-- 2. function-->
											<xsl:for-each select="//*[name()='function'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
											</xsl:for-each>
											<xsl:for-each select="//*[name()='or'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
										</xsl:for-each>
										<!-- 7. Konnektor and-->
										<xsl:for-each select="//*[name()='and'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>							
										</xsl:for-each>
										<!-- 8. Konnektor xor-->
										<xsl:for-each select="//*[name()='xor'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
										</xsl:for-each>					
								</xsl:if>
							</xsl:for-each>		
						<join>
								<xsl:for-each select="//*[name()='arc']">
									<xsl:if test="flow/@target=$EventID">
										<!-- Bei erster Kante schreiben True-->
										<xsl:if test="$TempJoin=''">
											<saxon:assign name="TempJoin">true</saxon:assign>
										</xsl:if>
										<!-- Bei zweiten Kante schreiben false-->
										<xsl:if test="$TempJoin='true'">
											<saxon:assign name="TempJoin">false</saxon:assign>
										</xsl:if>
										<!-- restlichen Faelle werden durch if nicht besucht-->
									</xsl:if>
								</xsl:for-each>
								<xsl:if test="$TempJoin='true'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>			
								<xsl:if test="$TempJoin='false'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>			
							</join>
							<split>
								<xsl:for-each select="//*[name()='arc']">
									<xsl:if test="flow/@source=$EventID">
										<!-- Bei erster Kante schreiben True-->
										<xsl:if test="$TempSplit=''">
											<saxon:assign name="TempSplit">true</saxon:assign>
										</xsl:if>
										<!-- Bei zweiten Kante schreiben false-->
										<xsl:if test="$TempSplit='true'">
											<saxon:assign name="TempSplit">false</saxon:assign>
										</xsl:if>
										<!-- restlichen Faelle werden durch if nicht besucht-->
									</xsl:if>
								</xsl:for-each>
								<xsl:if test="$TempSplit='true'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>
								<xsl:if test="$TempSplit='false'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>
							</split>	
							<!-- create DecomposeTo-->
							<decomposesTo>
								<xsl:attribute name="id">and_d_<xsl:value-of select="@id"/></xsl:attribute>
							</decomposesTo>
						</task>												
						</xsl:for-each>
						<!-- 5. Konnektor xor-->
						<xsl:for-each select="//*[name()='xor']">
						<task>
							<xsl:attribute name="id">i_<xsl:value-of select="@id"/></xsl:attribute>
							<xsl:variable name="EventID"><xsl:value-of select="@id"/></xsl:variable>					
							<xsl:for-each select="//*[name()='arc']">
								<!-- TODO kann hier eigentlich die Typ unterscheidung weglassen-->
								<xsl:if test="flow/@source=$EventID">
											<xsl:variable name="TargetID"><xsl:value-of select="flow/@target"/></xsl:variable>
											<xsl:for-each select="//*[name()='event'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
											</xsl:for-each>
											<!-- 2. function-->
											<xsl:for-each select="//*[name()='function'][@id=$TargetID]">
												<flowsInto>
													<nextElementRef>
														<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
													</nextElementRef>
												</flowsInto>
											</xsl:for-each>
											<xsl:for-each select="//*[name()='or'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
										</xsl:for-each>
										<!-- 7. Konnektor and-->
										<xsl:for-each select="//*[name()='and'][@id=$TargetID]">		
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>							
										</xsl:for-each>
										<!-- 8. Konnektor xor-->
										<xsl:for-each select="//*[name()='xor'][@id=$TargetID]">
											<flowsInto>
												<nextElementRef>
													<xsl:attribute name="id">i_<xsl:value-of select="$TargetID"/></xsl:attribute>
												</nextElementRef>
											</flowsInto>
										</xsl:for-each>					
								</xsl:if>
							</xsl:for-each>
						<join>
								<xsl:for-each select="//*[name()='arc']">
									<xsl:if test="flow/@target=$EventID">
										<!-- Bei erster Kante schreiben True-->
										<xsl:if test="$TempJoin=''">
											<saxon:assign name="TempJoin">true</saxon:assign>
										</xsl:if>
										<!-- Bei zweiten Kante schreiben false-->
										<xsl:if test="$TempJoin='true'">
											<saxon:assign name="TempJoin">false</saxon:assign>
										</xsl:if>
										<!-- restlichen Faelle werden durch if nicht besucht-->
									</xsl:if>
								</xsl:for-each>
								<xsl:if test="$TempJoin='true'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>			
								<xsl:if test="$TempJoin='false'">
									<xsl:attribute name="code">xor</xsl:attribute>
								</xsl:if>			
							</join>
							<split>
								<xsl:for-each select="//*[name()='arc']">
									<xsl:if test="flow/@source=$EventID">
										<!-- Bei erster Kante schreiben True-->
										<xsl:if test="$TempSplit=''">
											<saxon:assign name="TempSplit">true</saxon:assign>
										</xsl:if>
										<!-- Bei zweiten Kante schreiben false-->
										<xsl:if test="$TempSplit='true'">
											<saxon:assign name="TempSplit">false</saxon:assign>
										</xsl:if>
										<!-- restlichen Faelle werden durch if nicht besucht-->
									</xsl:if>
								</xsl:for-each>		
								<xsl:if test="$TempSplit='true'">
									<xsl:attribute name="code">and</xsl:attribute>
								</xsl:if>
								<xsl:if test="$TempSplit='false'">
									<xsl:attribute name="code">xor</xsl:attribute>
								</xsl:if>
							</split>	
							<!-- create DecomposeTo-->
							<decomposesTo>
								<xsl:attribute name="id">xor_d_<xsl:value-of select="@id"/></xsl:attribute>
							</decomposesTo>
						</task>
						</xsl:for-each>
						<!-- set output condition-->
						<task id="yawl_final_or">
						  <flowsInto>
							<nextElementRef id="yawl_output_condition" />
						  </flowsInto>
						  <join code="or" />
						  <split code="and" />
						  <decomposesTo id="d_yawl_final_or" />
						</task>		
					<outputCondition id="yawl_output_condition" />
					</processControlElements>
				</decomposition>
				<!-- fuer jeden Namen der Elemente wird noch eine decompositon angelegt-->
				<!-- 3. konnektor and-->
				<xsl:for-each select="//*[name()='and']">	
					<decomposition>
						<xsl:attribute name="id">and_d_<xsl:value-of select="@id"/></xsl:attribute>
						<xsl:attribute name="xsi:type">WebServiceGatewayFactsType</xsl:attribute>
					</decomposition>
				</xsl:for-each>		
				<!-- 4. konnektor or-->
				<xsl:for-each select="//*[name()='or']">	
					<decomposition>
						<xsl:attribute name="id">or_d_<xsl:value-of select="@id"/></xsl:attribute>
						<xsl:attribute name="xsi:type">WebServiceGatewayFactsType</xsl:attribute>
					</decomposition>
				</xsl:for-each>		
				<!-- 5. Konnektor xor-->
				<xsl:for-each select="//*[name()='xor']">	
					<decomposition>
						<xsl:attribute name="id">xor_d_<xsl:value-of select="@id"/></xsl:attribute>
						<xsl:attribute name="xsi:type">WebServiceGatewayFactsType</xsl:attribute>
					</decomposition>
				</xsl:for-each>		
				<decomposition>
					<xsl:attribute name="id">d_yawl_initial_or</xsl:attribute>
					<xsl:attribute name="xsi:type">WebServiceGatewayFactsType</xsl:attribute>
				</decomposition>
				<decomposition>
					<xsl:attribute name="id">d_yawl_final_or</xsl:attribute>
					<xsl:attribute name="xsi:type">WebServiceGatewayFactsType</xsl:attribute>
				</decomposition>
			</specification>
		</specificationSet>
	</xsl:template>
</xsl:stylesheet>