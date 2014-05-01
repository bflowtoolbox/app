<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" media-type="text/xml" indent="yes" omit-xml-declaration="no" />

	<xsl:template match="/">
		<epml:epml xmlns:epml="http://www.epml.de" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:epc="org.bflow.toolbox.epc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.1/notation" xmlns:xmi="http://www.omg.org/XMI">  
			<coordinates xOrigin="leftToRight" yOrigin="topToBottom" />  
			<definitions> 
				<definition xmlns:addon="http://org.bflow.addon" defId="1"></definition>  
				<xsl:for-each select="/Project/Models/Model">
					<xsl:choose>
						<xsl:when test="@displayModelType='Event'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@displayModelType='Function'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@displayModelType='Organization Unit'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@displayModelType='Process Path'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@displayModelType='Information Resource'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@modelType='EPCAndOperator'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@modelType='EPCOrOperator'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<xsl:when test="@modelType='EPCXOROperator'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{@id}"></definition>  
						</xsl:when>
						<!--
						<xsl:when test="@displayModelType='Control Flow'">
							<definition xmlns:addon="http://org.bflow.addon" defId="{position()+1}"></definition>  
						</xsl:when>
						-->
						<xsl:otherwise>
							<!-- <unused name="{@name}" /> -->
						</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
			</definitions>
			<directory name="Root"> 
				<epc epcId="1" name="BFLOW-PROJECT" IdBflow="1"> 
					<xsl:variable name="size">
						<xsl:text>30</xsl:text>
					</xsl:variable>
					<xsl:for-each select="/Project/Models/Model">
						<xsl:choose>
							<xsl:when test="@displayModelType='Event'">
								<event id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{@height}" width="{@width}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
									<name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="@name" /></name>
								</event>
							</xsl:when>
							<xsl:when test="@displayModelType='Function'">
								<function id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{@height}" width="{@width}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
									<name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="@name" /></name>
								</function>
							</xsl:when>
							<xsl:when test="@displayModelType='Organization Unit'">
								<participant id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{@height}" width="{@width}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
									<name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="@name" /></name>
								</participant>
							</xsl:when>
							<xsl:when test="@modelType='EPCAndOperator'">
								<and id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{$size}" width="{$size}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</and>
							</xsl:when>
							<xsl:when test="@modelType='EPCOrOperator'">
								<or id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{$size}" width="{$size}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</or>
							</xsl:when>
							<xsl:when test="@modelType='EPCXOROperator'">
								<xor id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{$size}" width="{$size}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</xor>
							</xsl:when>
							<xsl:when test="@displayModelType='Process Path'">
								<processInterface id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{@height}" width="{@width}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
									<name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="@name" /></name>
								</processInterface>
							</xsl:when>
							<xsl:when test="@displayModelType='Information Resource'">
								<dataField id="{@id}" IdBflow="{@id}" defRef="{@id}"> 
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Shapes/Shape">
											<xsl:if test="@model=$id">
												<position x="{@x}" y="{@y}" height="{@height}" width="{@width}" />
											</xsl:if>
										</xsl:for-each>
									</graphics>
									<name xmlns:addon="http://org.bflow.addon"><xsl:value-of select="@name" /></name>
								</dataField>
							</xsl:when>
							<xsl:when test="@modelType='EPCControlFlow'">
								<arc id="{position()+1}" IdBflow="_{@id}"> 
									<xsl:variable name="from">
										<xsl:for-each select="./ModelProperties/ModelRefProperty">
											<xsl:if test="@name='from'">
												<xsl:value-of select="./ModelRef/@id" />
											</xsl:if>
										</xsl:for-each>
									</xsl:variable>
									<xsl:variable name="to">
										<xsl:for-each select="./ModelProperties/ModelRefProperty">
											<xsl:if test="@name='to'">
												<xsl:value-of select="./ModelRef/@id" />
											</xsl:if>
										</xsl:for-each>
									</xsl:variable>
									<flow source="{$from}" target="{$to}" />
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
											<xsl:if test="@model=$id">
												<xsl:for-each select="./Points/Point">
													<position x="{@x}" y="{@y}" />
												</xsl:for-each>
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</arc>
							</xsl:when>
							<xsl:when test="@modelType='EPCOrganizationUnitAssignment'">
								<xsl:variable name="from">
									<xsl:for-each select="./ModelProperties/ModelRefProperty">
										<xsl:if test="@name='from'">
											<xsl:value-of select="./ModelRef/@id" />
										</xsl:if>
									</xsl:for-each>
								</xsl:variable>
								<xsl:variable name="to">
									<xsl:for-each select="./ModelProperties/ModelRefProperty">
										<xsl:if test="@name='to'">
											<xsl:value-of select="./ModelRef/@id" />
										</xsl:if>
									</xsl:for-each>
								</xsl:variable>
								<relation id="{@id}" IdBflow="_{@id}" from="{$from}" to="{$to}">
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
											<xsl:if test="@model=$id">
												<xsl:for-each select="./Points/Point">
													<position x="{@x}" y="{@y}" />
												</xsl:for-each>
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</relation>
							</xsl:when>
							<xsl:when test="@modelType='EPCInformationFlow'">
								<xsl:variable name="from">
									<xsl:for-each select="./ModelProperties/ModelRefProperty">
										<xsl:if test="@name='from'">
											<xsl:value-of select="./ModelRef/@id" />
										</xsl:if>
									</xsl:for-each>
								</xsl:variable>
								<xsl:variable name="to">
									<xsl:for-each select="./ModelProperties/ModelRefProperty">
										<xsl:if test="@name='to'">
											<xsl:value-of select="./ModelRef/@id" />
										</xsl:if>
									</xsl:for-each>
								</xsl:variable>
								<relation id="{@id}" IdBflow="_{@id}" from="{$from}" to="{$to}">
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
											<xsl:if test="@model=$id">
												<xsl:for-each select="./Points/Point">
													<position x="{@x}" y="{@y}" />
												</xsl:for-each>
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</relation>
							</xsl:when>
							<xsl:when test="@displayModelType='ModelRelationshipContainer'">
								<xsl:for-each select="./ChildModels/Model/ChildModels/Model">
									<xsl:choose>
										<xsl:when test="@displayModelType='Control Flow'">
											<arc id="{@id}" IdBflow="_{@id}">
												<xsl:variable name="from">
													<xsl:for-each select="./ModelProperties/ModelRefProperty">
														<xsl:if test="@name='from'">
															<xsl:value-of select="./ModelRef/@id" />
														</xsl:if>
													</xsl:for-each>
												</xsl:variable>
												<xsl:variable name="to">
													<xsl:for-each select="./ModelProperties/ModelRefProperty">
														<xsl:if test="@name='to'">
															<xsl:value-of select="./ModelRef/@id" />
														</xsl:if>
													</xsl:for-each>
												</xsl:variable>
												<flow source="{$from}" target="{$to}" />
												<graphics>
													<xsl:variable name="id">
														<xsl:value-of select="@id" />
													</xsl:variable>
													<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
														<xsl:if test="@model=$id">
															<xsl:for-each select="./Points/Point">
																<position x="{@x}" y="{@y}" />
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</graphics>
											</arc>
										</xsl:when>
										<xsl:when test="@displayModelType='Organization Unit Assignment'">
											<xsl:variable name="from">
												<xsl:for-each select="./ModelProperties/ModelRefProperty">
													<xsl:if test="@name='from'">
														<xsl:value-of select="./ModelRef/@id" />
													</xsl:if>
												</xsl:for-each>
											</xsl:variable>
											<xsl:variable name="to">
												<xsl:for-each select="./ModelProperties/ModelRefProperty">
													<xsl:if test="@name='to'">
														<xsl:value-of select="./ModelRef/@id" />
													</xsl:if>
												</xsl:for-each>
											</xsl:variable>
											<relation id="{@id}" IdBflow="_{@id}" from="{$from}" to="{$to}">
												<graphics>
													<xsl:variable name="id">
														<xsl:value-of select="@id" />
													</xsl:variable>
													<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
														<xsl:if test="@model=$id">
															<xsl:for-each select="./Points/Point">
																<position x="{@x}" y="{@y}" />
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</graphics>
											</relation>
										</xsl:when>
										<xsl:when test="@displayModelType='Information Flow'">
											<xsl:variable name="from">
												<xsl:for-each select="./ModelProperties/ModelRefProperty">
													<xsl:if test="@name='from'">
														<xsl:value-of select="./ModelRef/@id" />
													</xsl:if>
												</xsl:for-each>
											</xsl:variable>
											<xsl:variable name="to">
												<xsl:for-each select="./ModelProperties/ModelRefProperty">
													<xsl:if test="@name='to'">
														<xsl:value-of select="./ModelRef/@id" />
													</xsl:if>
												</xsl:for-each>
											</xsl:variable>
											<relation id="{@id}" IdBflow="_{@id}" from="{$from}" to="{$to}">
												<graphics>
													<xsl:variable name="id">
														<xsl:value-of select="@id" />
													</xsl:variable>
													<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
														<xsl:if test="@model=$id">
															<xsl:for-each select="./Points/Point">
																<position x="{@x}" y="{@y}" />
															</xsl:for-each>
														</xsl:if>
													</xsl:for-each>
												</graphics>
											</relation>
										</xsl:when>
									</xsl:choose>
								</xsl:for-each>
							</xsl:when>
							<xsl:when test="@name='EPCOrganizationUnitAssignment'">
								<relation id="{position()+1}" IdBflow="{@id}"> 
									<xsl:variable name="from">
										<xsl:for-each select="./ChildModels/Model/ModelProperties/ModelRefProperty">
											<xsl:if test="@name='from'">
												<xsl:value-of select="./ModelRef/@id" />
											</xsl:if>
										</xsl:for-each>
									</xsl:variable>
									<xsl:variable name="to">
										<xsl:for-each select="./ChildModels/Model/ModelProperties/ModelRefProperty">
											<xsl:if test="@name='to'">
												<xsl:value-of select="./ModelRef/@id" />
											</xsl:if>
										</xsl:for-each>
									</xsl:variable>
									<flow source="{$from}" target="{$to}" />
									<graphics>
										<xsl:variable name="id">
											<xsl:value-of select="@id" />
										</xsl:variable>
										<xsl:for-each select="/Project/Diagrams/Diagram/Connectors/Connector">
											<xsl:if test="@model=$id">
												<xsl:for-each select="./Points/Point">
													<position x="{@x}" y="{@y}" />
												</xsl:for-each>
											</xsl:if>
										</xsl:for-each>
									</graphics>
								</relation>
							</xsl:when>
							<xsl:otherwise>
								<!-- <unused name="{@name}" /> -->
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>
				</epc>
			</directory>
		</epml:epml>
	</xsl:template>

</xsl:transform>
