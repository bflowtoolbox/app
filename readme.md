# bflow* Toolbox - An Open Source Modeling Application

## Installation
tbd

## Development
### Minimum required

1. Download the "Eclipse IDE for Eclipse Committers" (x86/32 bit required) from eclipse.org. Eclipse 2018-09 (4.9) is the latest version that supports 32 bit. Latest active download link [here](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2018-09/R/eclipse-committers-2018-09-win32.zip)
2. Download and install the Java JDK 1.8 (x86 preferred) if not already done.
3. Install "Graphical Modeling Framework (GMF) Runtime SDK	1.12.0.201806010809" (section Modelling) as Eclipse plug-in.
4. Install "Xpand SDK	2.2.0.v201605260315" (section Modelling) as Eclipse plug-in.
5. Install "Epsilon Validation Language EMF Integration	1.5.1.201809302027" (section Epsilon EMF Integration) as Eclipse plug-in using "http://download.eclipse.org/epsilon/updates/" as Update Site. 
6. Install "Epsilon Wizard Language EMF Integration	1.5.1.201809302027" (section Epsilon EMF Integration) as Eclipse plug-in using "http://download.eclipse.org/epsilon/updates/" as Update Site.
7. Install "Graphical Modeling Framework (GMF) Tooling - Runtime Extensions	3.2.1.201409171321" (section GMF Tooling) as Eclipse plug-in using "http://download.eclipse.org/modeling/gmp/gmf-tooling/updates/releases/" as Update Site.

### Optional to use the BPMN modeller with the underlying Graphiti adapter

1. Install "Graphiti (Incubation)	0.15.0.201806050830" (section Modelling) as Eclipse plug-in.
2. Install "BPMN2 Modeler - Diagram Editor	1.4.3.Final-v20180418-1358-B1" (section   Eclipse BPMN2 Modeler	) as Eclipse plug-in using "http://download.eclipse.org/bpmn2-modeler/updates/oxygen/1.4.3" as Update Site.

### Add German localization

1. 	Extract the "eclipse-nl.zip" package that is located in the repository path "babel-packs" 
	anywhere on your drive, for instance "c:\bflow\eclipse-nl".
2.  Set up a new Plug-in Development Target Platform based on the current target (see Eclipse Preferences).   
3.  Name the new platform "Bflow German Platform". 
4.  Add a new location, choose "Directory" as source. Enter the path to the directory 
    where the babel language packs have been extracted (for instance, "c:\bflow\eclipse-nl"). 
	If everything is correct, the preview dialog shows a lot of found plug-ins.
5. 	Close the dialog and switch to the new target platform. Finally, apply the new settings.
