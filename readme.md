# bflow* Toolbox - An Open Source Modeling Application

## Installation
tbd

## Development
### Minimum required

1. Download the "Eclipse IDE for Eclipse Committers" (x86 preferred) from eclipse.org
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

1.  Go to the Eclipse Babel project web site and navigate to the download site with the latest packages (currently "http://download.eclipse.org/technology/babel/babel_language_packs/R0.16.0/photon/photon.php")
2.  Download the package "BabelLanguagePack-eclipse-de_4.8.0.v20180815020001.zip (85.17%)"
3.  Navigate to the archive site (http://archive.eclipse.org/technology/babel/index.php)
4.  Locate the Eclipse version where GMF localization packs are provided. 
    Currently, the last provided version is "Helios" (see http://archive.eclipse.org/technology/babel/babel_language_packs/R0.10.1/helios/helios.php).
5.  Download the following packages:
    * BabelLanguagePack-modeling.gmp.gmf-runtime-de_3.6.0.v20121120125106.zip
6.  Extract the packages anywhere on your drive. Ensure to merge each "eclipse" folder of each package.
    Finally, there is one "eclipse" folder that contains one "plug-ins" and one "features" folder, 
    where each contains all jar files.
7.  Set up a new Plug-in Development Target Platform based on the current target (see Eclipse Preferences).   
8.  Name the new platform "Bflow German Platform". 
9.  Add a new location, choose "Directory" as source. Enter the path to the directory 
    where the babel language packs have been extracted. If everything is correct, the preview 
    dialog shows a lot of found plug-ins.
10. Close the dialog and switch to the new target platform. Finally, apply the new settings.