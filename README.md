# HOW TO BUILD A BFLOW* TOOLBOX

Contents:
1. Build a stable version, based on Eclipse Indigo
2. Build the newest development version based on Eclipse Kepler (instable!)
3. Integrate the bflow's export and addon-mechanism into your own Eclipse-based modelling tool




## I. Build a stable version, based on Eclipse Indigo


1. Download Eclipse 3.7 (Indigo), package version: Modeling Tools
from http://eclipsesource.com/en/downloads/eclipse-indigo-download/
Note that newer versions of Eclipse (such as Juno, Kepler or Luna) can NOT be used at the moment.

2. Start Eclipse

Update Eclipse (Help - Check for Updates)

Install Eclipse Epsilon (Note: We are currently work with Epsilon version 1.1.0):
	Help - Install new Software
	Click Button "Add" to add the update site: http://download.eclipse.org/epsilon/updates/
	Select:
	Epsilon Core
	Epsilon Core Development Tools
	Epsilon EMF Integration
	Epsilon GMF Integration (but unselect "Eugenia" below this tree)
	Click twice "Next", accept the license agreement and click "Finish"
	allow to install unsigned content
	restart Eclipse
	

Install  M2T Xpand/Xtend 
	Help - Install new Software
	Click Button "Add" to add the update site: http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/
	Select M2T Xpand/Extend-1.2.1 (and everything below) only
	Click twice "Next", accept the license agreement and click "Finish"
	restart Eclipse

3. Checkout the sources from the subversion repository
https://svn.code.sf.net/p/bflowtoolbox/code/tags/1.2.7/
(Using the trunk would NOT work, because the current - but unstable - development version uses a newer version of Eclipse)

4. Import the projects you have checked out from the repository:
(File - Import - General - Existing Project into Workspace).


5. For removing errors of the type "An API baseline is not set for the current workspace", select
Window - Preferences - Plugin Development / API Baselines and change the "Missing API baseline" from "Error" to "Ignore".
Building the workspace should now proceeds without errors.


6. When the workspace is built automatically, it can happen that some errors are shown in the Problems View.
Do Project - Clean - Clean All Projects
Building the workspace should now proceed without errors.

7. If bflow* should use another language as English, you may wish to include language packs from
http://www.eclipse.org/babel/downloads.php.
Be sure to chose the language pack for the right Eclipse version. Note that not only the "Eclipse" language pack,
but also others may be required.

8. To start a bflow* instance from within Eclipse using a Run configuration:

a. Run - Run Configurations
Double-click "Eclipse Application" (and a new window for a new run configuration will be opened)
Type in a name for the configuration (for example "my_bflow")
In the "Plugins" tab, select "Launch with: All workspace and enabled target plug-ins".
Press Button "Run".
This should be the safe method to start bflow*. 

However, the started application will use a lot of memory, because in fact too much plug-ins have been selected.
Therefore, in the next step, the number of plug-ins will be reduced.
For this purpose, select your Run configuration again. 
In the plub-ins tabk type now "Launch with: Plug-ins selected below only".
Press button "Deselect All".
In the next step, select "Workspace" (and all org.bflow.* and org.infai.* plug-ins in the workspace) in the large "Plug-ins" window.
Furthermore, select org.eclipse.platform and org.eclipse.ui.ide.application.
Next, press button "Add Required Plug-ins".
Pressing the button "Validate Plug-ins" should now tell you that there are no problems.
Press Button "Run".

b. To create a bflow* product that can be started independently from Eclipse:
In step (8a) you have successfully created a Run configuration. Based on this Run configuration, a bflow* product will be built now.

Select
File - New - Other - Plugin Development - Product Configuration
Type the file name and the parent folder which will define the destination of your product file - the file that defines the properties of your runnable bflow* product.
For example, select "org.bflow.toolbox.product" as parent folder and "my-personal-bflow.product" a file name for the product file.
Select "Use a launch configuration" with the name of the configuration that has been created in (8a) (for example my_bflow).
Click "Finish", and the file will be created at the given destination.

Now select File - Import - Plug-in Development - Plug-ins and Fragments and Press "Next".
Select "Import as Project with Source Folders" and Press "Next".
In the next window, add org.eclipse.platform to the right selection called "Plug-ins and Fragments to Import".
Click Finish

Now right-click on the generated product file and select Export - Plug-in Development - Eclipse Product
Unselect "Generate metadata repository" and select "Allow for binary cycles in target platform"
Chose a destination directory (the place to which your runnable bflow* will be exported).
Click "Finish"

You can later change your product file to specify start options for bflow*
For this purpose, double-click on the product file (such as my_bflow.product).
In the folder "Launching", you can add start options (that will go to the *.ini file of your bflow* product).
For examples of such options, have a look at a *.product file that already exists in org.bflow.toolbox.

## II. Build the newest development version based on Eclipse Kepler (instable!)

Proceed as described in Sect. I, with a few differences:
In step 1:
Download Eclipse Kepler 4.3, package version: Modeling Tools

In step 3:
Checkout the sources from the subversion repository
https://svn.code.sf.net/p/bflowtoolbox/code/trunk/
Add the src-folder in org.bflow.toolbox.sdk

In Step 8a:
Deactivate all *visio*-plugins in the Run configuration
(Double-click at the *.product file, select tab "Dependencies", select org.infai.m3b.visio.*
and click "Remove")

In Step 8b:
Replace "Unselect Generate metadata repository" by "Unselect Generate p2 repository"


## III. Integrate the bflow's export and addon-mechanism into your own Eclipse-based modelling tool

The architecture of bflow* allows to make use of some of its features in other Eclipse EMF/GMF-based modeling tools.
These features include:
- the add-on mechanism
- creating exports and imports
- user-defined attributes
- the model navigator view

In the past, these parts could be downloaded at http://eclipsemodeling.svn.sourceforge.net.
This repository has been discontinued in November 2013.
Instead, download just the following plug-ins from http://sourceforge.net/projects/bflowtoolbox:
org.bflow.toolbox
org.bflow.toolbox.actions
org.bflow.toolbox.addons
org.bflow.toolbox.attributes
org.bflow.toolbox.interchange
org.bflow.toolbox.i18n
org.bflow.toolbox.libs
org.bflow.toolbox.modelnavigator
org.bflow.toolbox.swiprolog
Information about integrating the code into your own tool can be found at:
http://sourceforge.net/projects/eclipsemodeling/files/doc/



## Documentation

Documentation can be found at:
http://sourceforge.net/projects/bflowtoolbox/files/bflow_%20Documentation/
A more complete documentation of the possibility to configure the bflow* Toolbox and
to write your own add-ons can be found at:
http://sourceforge.net/projects/eclipsemodeling/files/doc/


## Contact

If you have any questions, contact bflow@bflow.org

Please tell us about your own code modifications, code enhancements and newly developed add-ons that
could be useful for the community!











