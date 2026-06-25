# Release Notes

### NIST DTSA-II Polaris 2026-01-30

*   Updating Java requirements to Java 24
*   Start Jython scripts from the command line.
*   Improved KLM lines (save/restore KLM lines)
*   New streamlined build process
*   Alternative output formats in 'tabulate(...)' function
*   Modified output of spectrum plot cursor text output to match scaling mode.
*   Improved the interpolation of custom window efficiencies.
*   Updated nominal atomic weights to IUPAC 2021 values.
*   Added Jython path option to facilitate user specified Python libraries.
*   Many small improvements.

### NIST DTSA-II Oberon 2023-11-22

*   Fixed a bug on first startup selecting the reports directory
*   Updated Java on Windows to Java 21.0.5
*   Updated Jython to 2.7.4

### NIST DTSA-II Oberon 2023-06-11

*   Implemented a dynamic width filter-fit to improve low energy line resolving power
*   Add documentation for changing spectrum line color: [Documentation](SettingCustomSpectrumLineColors.pdf) [Example](specColors.csv)
*   Improved uncertainty calculation when normalizing compositions
*   Bug fix: Correct calculation of detector efficiency at negative energies
*   Update atomic weights with IUPAC 2021 values
*   Bug fix: Fix spectrum similarity metric calculation for peaks above the beam energy
*   Toned down the colors of images in the GUI
*   Resize keV buttons on spectrum display panel on Mac
*   Add BadgerFilm export option
*   Add backscatter angle histogram class for MC simulations (scriptable-only)
*   Other small improvements and fixes

### NIST DTSA-II Neptune 2023-04-05

*   Added STEM-in-SEM quantification using bulk standards
*   Added the [multi-spectrum metric](https://doi.org/10.1017/S143192762200263X) to the Standard Bundler dialog
*   Read and write image files written with spectra
*   Improved low-energy fitting residuals
*   Updated support for recent Java Runtime Environments (comes with JRE19)
*   Added a "buried layer" model to the Monte Carlo GUI
*   User-selectable look-and-feels (under File->Preferences)
*   Improved command memory on the command line (Ctrl-Space searches for matches)
*   Many other small improvements

### NIST DTSA-II Microscopium 2022-06-06

*   Minor update to DTSA-II with a handful of minor bug-fixes, minor improvements and library updates.
*   Remembers recent scripting commands between restarts

### NIST DTSA-II Microscopium 2021-01-06

*   [Microscopium](https://en.wikipedia.org/wiki/Microscopium) ("the Microscope") is a minor constellation in the southern celestial hemisphere,
*   The iteration algorithm performance has been improved when the analytical total deviates from unity.
*   Added support for simulation and quantification of elements with 92 < Z < 100.
*   Added support for the Sabbatucci MACs and Williams 2011 edge energies

*   Sabbatucci, L., & Salvat, F. (2016). Theory and calculation of the atomic photoeffect. Radiation Physics and Chemistry, 121, 122-140.
*   Williams, G. P. (1992). Electron binding energies of the elements. CRC Handbook of Chemistry and Physics, 92nd edition, Section, 10, 221-226.

*   Removed a handful of archaic or rarely used algorithms.
*   Updated library dependencies
*   The source code is available from [OpenMicroanalysis](https://github.com/openmicroanalysis) on GitHub.

### NIST DTSA-II Lorentz 2020-06-26

*   Fit a regression in filter-fit quant!!!

### NIST DTSA-II Lorentz 2020-05-18

*   Updated the integrated JRE to Java 14 Adopt OpenJDK.
*   Added support for the Bruker PDZ file common to handheld XRF units.
*   KLM display improvements
*   Improved filter fit code - optimization and uncertainties
*   Trimmed some unused code
*   Improved the control chart in the QC report

### NIST DTSA-II Lorentz 2019-07-11

*   Added support for conductive coatings on standards and unknowns when quantifying spectra.
*   Added NIST Graf - An application for analyzing automated particle analysis data - to the install.
*   Integrated the Java 11 OpenJDK runtime into the installer.

### NIST DTSA-II Kelvin 2018-06-01

*   Requires Java 8 (v. 1.8.0\_144 or more recent)
*   Resolved issues with installation application when Java 9 and 10 is installed.
*   Added the emitted spectrum output to the Monte Carlo
*   Added BSED depth statistics to mcSimulate3 scripting
*   Many improvements to Monte Carlo scripting
*   Improve quantitative analysis HTML reporting
*   Bug fix in total and partial scattering cross-sections
*   Many minor improvements and bug fixes

### NIST DTSA-II Jupiter 2017-11-06

*   Fixed a bug introduced by Win10 that caused DTSA-II to crash immediately.
*   Improved batch export
*   Added spectrum sorting to the spectrum list box

### NIST DTSA-II Jupiter 2017-02-28

*   Improvements in the Standard Bundle creator
*   Spectrum renamer placement improved.
*   Improvements to tabulate(...) function for Michael

### NIST DTSA-II Jupiter 2016-07-05 - Heather's release

*   Improved the way residuals are calculated on complex spectra. The residuals are more reflective of the elements present in the material.
*   Added a "default" button to the "quantification line selection" panel in the "quantification alien"
*   Add the ability to side scroll spectra using "Ctrl-Left click-and-drag"
*   Improved usability with Noran SystemSix EMSA spectra

### NIST DTSA-II Jupiter 2016-06-23

*   Fixed a bug reading in Standard Bundles due to character encoding in Zip files.

*   Previous Standard Bundles may need to be rebuilt.

### NIST DTSA-II Jupiter 2016-06-17

*   Fixed a bug in the inclusion Monte Carlo model.

### NIST DTSA-II Jupiter 2016-05-24

*   Bug fix - multiQuant(...)

### NIST DTSA-II Jupiter 2016-05-05

*   Try the new [DTSA-II Forum](https://probesoftware.com/smf/index.php?board=32.0) on the Probe Software User's Forum
*   Requires [Oracle Java 8](https://www.java.com/en/download/index.jsp)
*   A major upgrade from Iona - many major and minor updates, new features and improvements
*   New - ["Standard Bundles"](Standard Bundles.pdf) make using complex standards much easier
*   New - [ζ-factor quantification](Zeta-Factors.pdf) of thin films
*   Many subtle improvements when quantifying low-energy x-rays
*   Cleaner, more modern icons and appearance.
*   Moved to Jython 2.7 for scripting

### DTSA-II Iona - 2015-08-28

*   Updated OS X release to be current with other versions
*   Small changes to support OS X better

### DTSA-II Iona - 2015-07-01

*   Monte Carlo: Normalized intensity scale on emission/continuum images.
*   Monte Carlo: Improved the linearity of the grey scale index values on emission/continuum images.
*   Monte Carlo: Added the ability to display images of continuum generation

### DTSA-II Iona - 1-May-2015

*   Fixed a bug in the reported generated and emitted intensities from Monte Carlo simulation

### DTSA-II Iona - 17-Apr-2015

*   Many, many improvements large and small
*   Support for silicon nitride windows
*   Faster high quality Bremsstrahlung modeling in Monte Carlo
*   Improved and enhanced reporting for simulation and quantification

*   Added generation volume calculations to Monte Carlo report

*   Many new "Armstrong particle shapes" added to the Monte Carlo GUI
*   Added a [spectrum rename facility](Renaming_spectra.pdf)
*   Improved uncertainty calculations when quantifying spectra
*   Better algorithms for fitting spectra with modeled spectra (Calibration Alien)
*   Many updated weights of lines based on measured and fitted spectra
*   Better quant optimization
*   More tools for crude peak integration
*   Support for more vendor's variants of the EMSA file format
*   Algorithms for fitting references to spectra have been optimized
*   Return k-ratios for stripped elements
*   Improvements to spectrum display including output of high quality graphics
*   The source code is zipped inside the dtsa2.jar and epq.jar files.

### DTSA-II Gemini - 20-Dec-2012

*   NOW REQUIRES JAVA JRE 7!
*   Improved scheme for KLM line display
*   Better quality output when coping the spectrum plot to the clipboard.
*   Added a voxelated detector to plot x-ray generation by voxelated region
*   Added sign-up for Yahoo Group suggestion.

### DTSA-II Gemini - 15-Nov-2012

*   NOW REQUIRES JAVA JRE 7!
*   Improved report format.
*   Improved quant LLSQ report
*   Update to latest Jython release (2.5.3)
*   Installs to AppData\\Local\\NIST\\... by default to eliminate some potential user priviledge related problems

### DTSA-II Gemini - 13-Nov-2012

*   NOW REQUIRES JAVA JRE 7!
*   Resolved a bug when elements are zeroed during the fit process.

### DTSA-II Gemini - 17-Oct-2012

*   NOW REQUIRES JAVA JRE 7!
*   Improved residuals / k-ratios from performing MLLSQ fitting on low energy characteristic lines
*   Added accuracy terms to the uncertainty budget for matrix corrects (see forthcoming Analytical Chemistry article)
*   Improved L line weights for transition metal elements
*   Improved Monte Carlo modeling of Bremsstrahlung particularly at high beam energy
*   Many small bug fixes
*   Improved non-linear fit algorithm for analytical model fitting of characteristic peaks (used by Calibration and QC tools)
*   Added Duane-Hunt limit checks to the MLLSQ quant alien and report.
*   Added background modeling to the extraction of peak intensities for the purpose of residual calculation.
*   (Windows)Created Windows executable (DTSA-II.exe) wrapping dtsa2.jar. Validates Java version.

### DTSA-II Fornax - 1-June-2012

*   Added support for quirks in DM exported MSA files

### DTSA-II Fornax - 1-June-2012

*   A number of small bug fixes
*   Updated the logic used to update densities of materials in the database.

### DTSA-II Fornax - 1-May-2012

*   Fornax introduces a new function - the Experiment Optimization tool (Tools -> Optimization Alien). This tool is designed to assist in designing the optimal selection of standard and beam energy for an EDS experiment. It works in conjunction with a Standards Database (Tools -> Edit Standards Database). The standards database should be edit to contain a list of all standards available in your lab. It defaults to a set of common standards. The optimizer searches the standards to select the best standard for each element in the unknown material.
*   The results of the spectrum tools no longer is displayed in a seperate window. It now displays for 10 seconds in a box on the spectrum background. You can copy the contents using the right-click menu (Copy->Status Text).
*   Many other small bug fixes

### DTSA-II 433 Eros - 8-Sep-2011

*   Fixed a bug with detectors in the scripting window ('findDetector', 'dets' etc.)
*   Ctrl-Enter from the KLM Line - Element edit box automatically displays the KLM lines for one or more element or material

### DTSA-II 433 Eros - 2-Sep-2011

*   Added an addition output table when performing Monte Carlo simulations in a VP environment
*   Corrected a bug when working at low VP pressures
*   Introduced an alternative KLM ordering scheme which allows the user to scroll through the KLM lines in energy order in addition to atomic number order.

### DTSA-II 433 Eros - 5-Aug-2011

*   Improved scripting - particular long-running tasks from the command line
*   Start up and shut down scripts
*   Quality control tools (Tools - Quality Control alien)

### DTSA-II Deneb - 29-July-2010

*   Publication quality output (Right click on the spectrum display - Select "Save", "as displayed"

*   Direct to Scalable Vector Graphics (SVG) (for Inkscape, Adobe Illustrator, CorelDRAW etc.)
*   High res bitmaps in BMP, PNG, JPG formats. (Select output mode from Save As dialog.)
*   Improved appearance of spectrum display

*   Added variable pressure options to the Monte Carlo Simulation Alien
*   Copy current spectrum view to the HTML report
*   Improved base functionality for Python scripting

*   Improved Monte Carlo scripting ('import dtsa2.mcSimulate3 as mc' from command line)
*   Improved 'tabulate(specs)' function for summarizing quant results
*   Improved 'quant', 'quantify' and 'multiQuant' to facilitate scripting the process of quantifying unknowns against standards.
*   Spectrum display annotations using 'annotComposition()' function

### DTSA-II Cassiopeia - 4-June-2010

*   Various improvements in the Monte Carlo simulation of secondary fluorescence

*   Speed optimization
*   Algorithm improvement
*   Corrected bugs in tabulated intensity result report
*   Enhanced intensity reporting

*   Improved Python scripting. Added support for the standard Python library as implemented by Jython

*   Added base Jython libraries to the install
*   Created a 'dtsa2' package containing useful Python routines for easier scripting
*   Added 'import dtsa2.mcSimulate3' for simplified script-based Monte Carlo simulation
*   Added 'import dtsa2.dbTools' to work with the database
*   Added 'import dtsa2.minBeamEnergy for estimating minimum required beam energy
*   Added 'import dtsa2.tabulate for tabulating compositional results
*   Added 'import dtsa2.wds with basic WDS functions
*   All the scripting environment initialization routines have been moved to \\Lib\\dtsa2\\\_\_init\_\_.py

*   Enhanced the KLM dialog to permit selecting groups or individual KLM lines
*   Added the ability to search for sum peaks by selecting a region on the spectrum display and clicking the 'ID Sum Peak' button.
*   Added two presentations containing 'Getting started...' material

### DTSA-II Betelgeuse - 15-Mar-2010

*   Fixed a bug in the calibration routines when used with multi-element, multi-line standards.
*   Added intensity output to the report for Monte Carlo simulations with secondary fluorescence.

### DTSA-II Betelgeuse - 9-Mar-2010

*   Improved "Quantification Alien" to perform MLLSQ quantification using standards

*   Optional user selected lines used for quant
*   Improved HTML result output

*   Third-generation Monte Carlo spectrum simulation

*   Secondary fluorescence modeling (characteristic and Bremsstrahlung)
*   Simulate a interface between two materials

*   Many refinements in Python scripting interface

*   material("K412") function checks the database before trying to parse composition
*   mindat(comp) looks up 'comp' on the mindat.org Mineral database

*   Added tags to permit storing the composition inside EMSA spectrum files. Facilitates standards-based quant.
*   Improved handling of non-US locales (number formats and date/time formats)

### DTSA-II Andromeda B - 28-May-2009

*   Corrected a problem creating detectors on some computers
*   Added a mechanism for remapping spectra from one detector channel width/offset to anothers

### DTSA-II Andromeda A - 19-Apr-2009

*   Resolved a significant error in the implementation of Armstrong's CITZAF for particles
*   Improved the usability of the shape edit function in the quantification alien
*   Bug notice: There seems to be a fundental problem with Heinrich's equations for mass absorption coefficients which leads to the occasional divide-by-zero error. This can cause the simulation and quantification modules to fail. The author suggests using the Chantler 2005 MACs.

### DTSA-II Andromeda - 8-April-2009

*   See the note below regarding incompatible detector descriptions
*   Added a object browser with API help to the scripting window
*   Implemented Armstrong's CITZAF particle corrections

*   Select "Armstrong CITZAF - Particle" from the Preference - Quantification Algorithms - Correction Algorithm drop down list box.
*   Change the shape on the "Specify unknown spectra" page of the "Quantification Alien" select the spectra and use the "Sample Shape" button to specify the particle shape.

*   Consolidated filter fitting code for bulk and particle spectra.
*   Instituted new alphabetically named release naming scheme
*   Improved the way the spectrum fitting routines handle moderately close peaks in the standard spectrum.

#### **Bug notice:** If you can't create new detectors in the Preferences dialog...

Older versions of DTSA-II stored the descriptions of x-ray detectors in a format which is incompatible with the later releases. This can manifest itself as no detectors present in the Preferences dialog and/or an inability to add new detectors. The quick fix is to delete the DTSA-II database. You can find out where the database is located by opening the Preferences dialog and looking for "Base report path" on the "User Information" page. The database is located in the "Database v2" folder within the "Base report path." Delete the "Database v2" folder and restart DTSA-II. The database will be recreated. You should find one default Instrument called "Probe" with one default detector "Detector - Si(Li)". You will now be able to define new detectors.

You may want to record the properties of your detectors before upgrading to ensure that you can reproduce them after deleting the database.

### Version 1795 - 15-Dec-2008

*   Apple Macintosh OS X support - requires the latest Java update
*   Active links in reports reload spectra
*   Quant residual stored with report
*   Bug fixes moving back in Quant alien
*   Added ability to reopen reports (suggestion: use Google desktop or Searchlight to find report)
*   Slightly less offensive buttons in spectrum toolbar (still needs work!)
*   _Report a bug or request a feature..._ menu item added to help menu.
*   Horizontal and vertical grid lines in the spectrum display.
*   Worked around a bug introduced by Sun in 1.6.10 which made dragging regions in the spectrum window painful.

### Version 1741 - 6-Nov-2008

*   **BUG FIX:**Fixed an error in 1738 which caused erroneous results quantifying spectra using MLLSQ.
*   Divided the IonizationCrossSection class into two - one for absolute cross sections and the other for relative.

### Version 1738 - 6-Nov-2008

*   Integrated Villarubia's Monte Carlo modification to allow energy loss during a scatter event.
*   Implemented a more sophisticated Bremsstrahlung angular dependence model due to Acosta
*   Updated Bote/Salvat ionization cross section from pre-publication to publication form.
*   Added ability to select the number of electron trajectories to run in Simulation Alien with more granularity.

### Version 1715 - 8-Sep-2008

*   Fixed a bugs which would not permit saving modifications to detector preferences

### Version 1715 - 8-Sep-2008

*   Fixed a bugs which would not permit saving modifications to detector preferences

### Version 1706 - 29-Aug-2008

*   Fixed a couple minor bugs when editing detector preferences
*   Added a detector page to the web site
*   Added an option for 100× the number of electrons to the simulation wizard
*   Added MACs to the quant report and fixed a spelling error.
*   Made starting the database more robust.

### Version 1705 - 28-Aug-2008

*   Added lines, ZAF factors and algorithm information to the EDS quant report
*   Corrected a bug in how oxygen-by-stoiciometry was handled in EDS quant by standards
*   Added spectrum zoom tool bar
*   Implemented grid transparency for grid-mounted windows
*   Added integrated count reporting to ROI report
*   Now report each ROI separately in 'Count Events' spectrum menu item.

### Version 1676 - 11-Aug-2008

*   Implemented Bote / Salvat ionization cross section
*   Resolved consistency issues with dead layer thickness units
*   Improved the way density is handled by the material editor

### Version 1665 - 1-Aug-2008

*   M&M 2008 official release version
*   Added new spectrum properties to the database
*   Improved copy facilities for spectrum properties and compositions

### Version 1654 - 28-Jul-2008

*   Streamlined the quantification wizard
*   Improved reporting for quantification of k-ratios
*   Added the JavaDoc for the EPQ library to the installation

### Version 1639 - 24-Jul-2008

*   Improved non-linear fitting for detector calibration against spectra
*   Ability to disable detectors
*   Fixed bugs with scaling simulated spectra to probe current
*   Added emission images to Monte Carlo output

### Version 1586 - 30-May-2008

*   Added an option to compute generation images for Monte Carlo simulated spectra
*   Improved the non-linear peak fitter
*   Added addition details columns to the quant standards table
*   Corrected bug in detector implementation with respect to detector-to-sample distance
*   Added MAC output to script
*   Improved database result dialog
*   Favor encapsulated PostScript output in spectrum display
*   Fixed a line termination bug in EMSA 1.0 file output
*   Updated microcalorimeter detector model

### Version 1560 - 22-Apr-2008

*   Enhanced the out from Monte Carlo spectrum simulations to include a VRML file and line intensities.
*   Resolved an issue reading DTSA files containing corrupted elemental information

### Version 1553 - 18-Apr-2008

*   Implemented detector calibration against pure element references (Mn, Cu, Zn, Fe)
*   Tried to fix a bug creating user-friendly names for Edax spectra on Macs

### Version 1541 - 4-Apr-2008

*   Fixed a bug creating a new database.

### Version 1539 - 4-Apr-2008

*   Added a calibration wizard and started to implement it.
*   Deep changes in how detectors are handled to better fit the database.

### Version 1463 - 21-Feb-2008

*   Improved scripting
*   Improved handling of detectors (still needs work)
*   Annotations in report
*   Trimmed menus
*   Added drag scaling for the spectrum vertical axis
*   Added ZAF output to script
*   Added documentation for the scripting functions

### Version 1439 - 8-Feb-2008

*   Improved the scripting interface
*   Fixed a bug in the SPC file import class
*   Addressed some threading issues with scripting and the GUI

### Version 1429 - 6-Feb-2008

*   Added help menu items for the EPQ library, python, jython and the DTSA-II website.
*   Changed the product name to DTSA-II
*   Added the FEI Quanta 200F and EDAX detectors
*   Simplified the menus by removing many unused menu items.
*   Enhanced the base scripting capabilities

### Version 1420 - 4-Feb-2008

*   Added a command line and scripting interface.

### Version 1403 - 29-Jan-2008

*   The installation program for release 1398 omitted a library required for database support (derby.jar)
*   The Getting Started and Simulation links on the web site have been written

### Version 1398 - 24-Jan-2008

*   The first test release
