# Example Scripts

Here are a handful of example scripts using the DTSA-II Python scripting language. You may use and modify them as you please.

It is also helpful to look over `mcSimulate4.py` which can be found in the DTSA-II installation directory under `\lib\dtsa2`. 


### Gold coated K412 spheres

This example script computes spectra for spheres of K412 glass coated with 10 nm of gold and mounted on a carbon subtrate.

```python
# -*- coding: utf-8 -*-
# A script for simulating a coated sphere on a substrate
# It makes use of the new mcSimulate3 package which provides a simpler mechanism for
# outputing multiple image and table types from a Monte Carlo simulation.
import dtsa2.mcSimulate3 as mc

det = findDetector("Si(Li)") # Replace with your detector's name
e0 = 15 # keV
auThickness = 10.0e-9 # 10 nanometers

# Define the materials
k412 = epq.Material(epq.Composition([epq.Element.Ca,epq.Element.Fe,epq.Element.O, epq.Element.Al,epq.Element.Mg, epq.Element.Si],
                                    [0.1090, 0.0774, 0.4276, 0.0491, 0.1166, 0.2120] ),
                                    epq.ToSI.gPerCC(5.0))
k412.setName("K412")                                    
au = epq.MaterialFactory.createPureElement(epq.Element.Au)
c = epq.MaterialFactory.createPureElement(epq.Element.C)

# See the documentation for mcSimulate3 which describes how to easily output
# generation and trajectory images, phi(rho z) curves and other output mechanisms.

for r in [0.1e-6, 0.2e-6, 0.4e-6, 0.8e-6, 1.6e-6]:
   display(mc.coatedSphere(k412,r,au,auThickness,det,e0,substrate=c))
```

*   The top section finds a detector by name. The name should reflect one of the detectors configured in the _Preference_ dialog. "Bruker 5" corresponds to a detector I use frequently. The next few lines configure the beam energy `e0`, the number of electron trajectories `nTraj`, the does in nanoamp·seconds `dose` and the gold layer thickness in meters `auThickness`
*   The coatedSphere method is defined in mcSimulate3. This method is a generic mechanism for simulating coated spheres on a substrate.
*   Inside coatedSphere is define a method (buildSphere) which builds the sample. This method uses the buildArgs to transfer the necessary information on the materials and geometry.
*   coatedSphere hands buildSphere to the method mc.base(...) which actually performs the simulation. mc.base handles many of the details such as configuring x-ray events (Brem and characteristic) and configuring default and optional output mechanisms. By default mc.base(...) returns a spectrum object but by specifying xtraParams it is easy to also output emission images, trajectory images, phi(rho-z) curves etc. See the built in documentation for dtsa2.mcSimulate3 for details.
*   The next section defines the relevant materials using a couple of different strategies. K412 is defined from a list of elements and a list of quantities in weight percent plus a density. Gold and carbon are common solid pure elements for which the library knows nominal densities.
*   `for r...` starts a loop which iterates over the entire block of indented code. `r` is the radius of the particle which will vary from 0.1 to 1.6 µm in factors of two.
*   The simulation takes place inside the loop. The display method outputs the spectrum to the spectrum display panel.

### Functions for computing compositional statistics

Sometimes you may quantify many instances of a similar spectrum. For example, you might make 10 measurements of a nominally homogeneous material. Given these measurements you might want to know the average and standard deviation of the measured compositions. These functions will compute this information efficiently. Most of these methods are now built into the default DTSA-II scripting environment. Use "dir()" and "help(...)" to investigate.

```python
# Functions for computing compositional statistics
# Author: Nicholas W. M. Ritchie 
# Created: 14-May-2008
import java.util as ju

def compStats(comps, norm=0):
   """Syntax: compStats([comp1,comp2,...,compN],norm=1)
   Compute compositional statistics for the list of Composition objects.  If norm=1, 
   then the Composition objects are normalized to 100%; otherwise not."""
   elms=ju.TreeSet()
   for comp in comps:
      elms.addAll(comp.getElementSet())
   dss=dict()
   for elm in elms:
      dss[elm] = epu.DescriptiveStatistics()
   for comp in comps:
      for elm in elms:
         dss[elm].add(comp.weightPercent(elm,norm))
   return dss

def quantStats(specs, prop=epq.SpectrumProperties.MicroanalyticalComposition):
   """Syntax: quantStatus([spec1,spec2,...,specN],prop=epq.SpectrumProperties.MicroanalyticalComposition)
   Computes the average Composition from the specified list of ScriptableSpectrum objects where
   the specified Composition property is extracted."""
   comps=[]
   str = None
   for spec in specs:
      c=spec.getProperties().getCompositionWithDefault(prop,None)
      if c!=None:
         if str==None:
            str=spec.toString()
         else:
            str="%s, %s" % (str, spec.toString())
         comps=comps+[c]
   print "Quantitative statistics from %d spectra (%s)\\n  [%s]" % (len(comps), prop, str)         
   cs=compStats(comps)
   print "Z\\tElement\\tAverage\\tStdDev"
   print "\\t\\t (%)\\t (%)"
   for elm, ds in cs.iteritems():
      print "%d\\t%s\\t%2.3f\\t%2.3f" % ( elm.getAtomicNumber(), elm.toAbbrev(), ds.average()*100.0, ds.standardDeviation()*100.0 )

### Low kV utilities

Various different utility functions for answering questions related to working at the lowest possible beam energy.

# A set of utility functions for working at low beam energy.  Set
# MinMElement and MinLElement to the atomic number of the lowest
# Z element for which you believe you can see M lines and L lines 
# respectively.
# All tests are with respect to the K, LIII and MV edges.
# Author:  Nicholas W. M. Ritchie
# Updated: 3-Sep-2008

MinMElement = epq.Element.elmSb
MinLElement = epq.Element.elmSc

def minEdge(elm):
   """Example:  minEdge("Ag")
   Returns the lowest energy edge which is likely to be visible in an 
   EDS detector for the specified element"""
   elm=element(elm)
   if elm.getAtomicNumber()\>=MinMElement:
      res=epq.AtomicShell(elm,epq.AtomicShell.MV)
   elif elm.getAtomicNumber()\>=MinLElement:
      res=epq.AtomicShell(elm,epq.AtomicShell.LIII)
   else:
      res=epq.AtomicShell(elm,epq.AtomicShell.K)
   return res

def maxEdge(elm, e0, over=1.5):
   """Example:  maxEdge("Ag", 20.0, 1.5)
   Returns the highest energy edge which is excited with the overvoltage
   specified (1.5) for the specified beam energy (20.0)."""
   elm=element(elm)
   e0=epq.ToSI.keV(e0)
   res = None;
   if elm.getAtomicNumber()\>MinMElement:
      sh=epq.AtomicShell.MV
      if epq.AtomicShell.getEdgeEnergy(elm,sh)*over<=e0:
         res=epq.AtomicShell(elm,sh)
   if elm.getAtomicNumber()\>MinLElement:
      sh=epq.AtomicShell.LIII
      if epq.AtomicShell.getEdgeEnergy(elm,sh)*over<=e0:
         res=epq.AtomicShell(elm,sh)
   sh=epq.AtomicShell.K
   if epq.AtomicShell.getEdgeEnergy(elm,sh)*over<=e0:
      res=epq.AtomicShell(elm,sh)
   return res

def minBeamEnergy(elms,over=1.5):
   """Example:  minBeamEnergy(["Ag","Au", "Cu"],1.3)
   Returns the lowest beam energy required to provide the specified 
   overvoltage (1.3) for all the elements listed."""
   minE=0.0
   if isinstance(elms,epq.Composition):
      elms=elms.getElementSet()
   for elm in elms:
      shell=minEdge(elm)
      e=over*shell.getEdgeEnergy()
      if e\>minE:
         minE=e
   return epq.FromSI.keV(minE)
  
def ranges(comp, e0, over=1.5):
   """Example: ranges(createMaterial(),5.0,1.5)
   Prints a list of edges, over voltages, and ionization ranges (in meters)
   for the specified material at the specified beam energy in keV (5.0) and 
   required overvoltage (1.5)"""
   print "Shell\\tOver\\tRange (m)"
   for elm in comp.getElementSet():
      elm=element(elm)
      sh=maxEdge(elm,e0,over)
      if sh!=None:
         print "%s\\t%g\\t%e" % (sh, e0/epq.FromSI.keV(sh.getEdgeEnergy()), epq.ElectronRange.KanayaAndOkayama1972.compute(comp,sh,epq.ToSI.keV(e0))/comp.getDensity())
      else:
         print "%s\\tNone\\tNone" % elm

print "Functions \\"minEdge\\", \\"maxEdge\\", \\"minBeamEnergy\\" & \\"ranges\\" now defined."
print "Use \\"help(fcnName)\\" to get documentation for each."

### Visibility of Small Features in X-ray Maps

This script investigates the visibility of small features embedded in a matrix material.

# NWMR 6-Nov-2008
# Script for estimating the visibility of small features embedded in a matrix
import gov.nist.microanalysis.EPQLibrary as epq
import gov.nist.microanalysis.EPQLibrary.Detector as epd
import gov.nist.microanalysis.NISTMonte as nm
import gov.nist.microanalysis.EPQTools as et
import java.util as jutil
import java.io as jio
import java.nio.charset as cs
nTraj=250
characteristic=1
brem=1
radius = 5.0e-7
dose = 1 # nA*second
e0 = 25.0 # keV
blocks = [
 [ \-0.2650, \-0.2650, 0.0050, 0.0100 ],
 [ \-0.2450, \-0.2650, 0.0050, 0.0100 ],
 [ \-0.2150, \-0.2650, 0.0050, 0.0100 ],
 [ \-0.1850, \-0.2650, 0.0050, 0.0100 ],
 [ \-0.1450, \-0.2650, 0.0050, 0.0100 ],
 [ \-0.1050, \-0.2650, 0.0050, 0.0100 ],
 [ \-0.2650, \-0.2450, 0.0050, 0.0100 ],
 [ \-0.2450, \-0.2450, 0.0050, 0.0100 ],
 [ \-0.2150, \-0.2350, 0.0050, 0.0100 ],
 [ \-0.1850, \-0.2350, 0.0050, 0.0100 ],
 [ \-0.1450, \-0.2250, 0.0050, 0.0100 ],
 [ \-0.1050, \-0.2250, 0.0050, 0.0100 ],
 [ \-0.2600, \-0.1800, 0.0100, 0.0200 ],
 [ \-0.2200, \-0.1800, 0.0100, 0.0200 ],
 [ \-0.1600, \-0.1800, 0.0100, 0.0200 ],
 [ \-0.2500, \-0.0900, 0.0200, 0.0400 ],
 [ \-0.1700, \-0.0900, 0.0200, 0.0400 ],
 [ \-0.2300,  0.0100, 0.0400, 0.0800 ],
 [ \-0.1900,  0.1900, 0.0800, 0.1600 ],
 [  0.0300,  0.1900, 0.0800, 0.1600 ],
 [  0.1100, \-0.1100, 0.1600, 0.3200 ] ]
sc=1.0e-6
# A detector named "Bruker 5" is defined in my DTSA-II preferences (modify!)
det = findDetector("Bruker 5")
# Create a Ripple/RAW file (native format for Dave Bright's LISPIX program)
res=et.RippleFile(64, 64, 2048, et.RippleFile.FLOAT, 4, et.RippleFile.BIG_ENDIAN, DefaultOutput+"\\K411.rpl",DefaultOutput+"\\K411.raw")
for x in range(\-32,32,32):
   for y in range(\-32,32,32):
      if terminated:
         break
      print "%d, %d" % (x,y)
      # create an instance of the model
      monte=nm.MonteCarloSS()
      monte.setBeamEnergy(epq.ToSI.keV(e0))
      beam=nm.GaussianBeam(1.0e-9)
      monte.setElectronGun(beam)
      beam.setCenter([0.32*sc*x/32.0,0.32*sc*y/32.0,\-0.05])
      # create the sample
      mns=epq.Material(epq.Composition([epq.Element.Mn,epq.Element.S],[0.6314,0.3686],"MnS"),epq.ToSI.gPerCC(3.99))
      fe=epq.MaterialFactory.createPureElement(epq.Element.Fe)
      block=nm.MultiPlaneShape.createBlock([20.0e-6,20.0e-6,20.0e-6],[0.0,0.0,10.0e-6],0.0,0.0,0.0)
      matrix=monte.addSubRegion(monte.getChamber(),fe,block)
      for b in blocks:
         monte.addSubRegion(matrix,mns,nm.MultiPlaneShape.createBlock([sc*b[3],sc*b[3],sc*b[3]],[sc*b[0],sc*b[1],sc*b[2]],0.0,0.0,0.0))
      if (x%8==0) and (y%8==0):
         # Add a VRML detector
         print "Creating VRML for %d, %d" % (x,y)
         fos=jio.FileOutputStream("%s/K411 (%d,%d).wrl" % (DefaultOutput,x,y))
         tw=jio.OutputStreamWriter(fos,cs.Charset.forName("UTF-8"))
         vrml = nm.TrajectoryVRML(monte,tw)
         vrml.setMaxTrajectories(20)
         vrml.setTrajectoryWidth(1.0e-8)
         vrml.setDisplayBackscatter(1)
         vrml.addView("Gun",[0.0,0.0,\-5.0e-5],[0.0,0.0,0.0])
         vrml.addView("X-Axis",[5.0e-5,0.0,0.0,0.0],[0.0,0.0,0.0])
         vrml.addView("Y-Axis",[0.0,5.0e-5,0.0,0.0],[0.0,0.0,0.0])
         vrml.addView("Y-Axis (close)",[0.0,5.0e-6,0.0,0.0],[0.0,0.0,0.0])
         vrml.renderSample()
         monte.addActionListener(vrml)
         monte.runMultipleTrajectories(20)
         tw.flush()
         fos.close()
      if 1:
         det.reset()
         # add an x-ray event listener
         if characteristic:
            xrel=nm.XRayEventListener2(monte,det)
            monte.addActionListener(xrel)
         if brem:
            brel = nm.BremsstrahlungEventListener(monte,det)
            monte.addActionListener(brel)
         monte.runMultipleTrajectories(nTraj)
         res.seek(x+32,y+32)
         spec=epq.SpectrumUtils.addNoiseToSpectrum(det.getSpectrum(dose*1.0e-9 / (nTraj * epq.PhysicalConstants.ElectronCharge)),1.0)
         # write to Ripple file
         res.write(epq.SpectrumUtils.toDoubleArray(spec))
         # Write to EMSA files...
         props=spec.getProperties()
         props.setTextProperty(epq.SpectrumProperties.SpectrumDisplayName, "Pixel[%d,%d]" % (x,y))
         props.setNumericProperty(epq.SpectrumProperties.LiveTime, dose)
         props.setNumericProperty(epq.SpectrumProperties.FaradayBegin,1.0)
         props.setNumericProperty(epq.SpectrumProperties.BeamEnergy,e0)
         # Write the spectrum to disk and display
         fos = jio.FileOutputStream("%s/Pixel[%d,%d].msa" % (DefaultOutput, x, y))
         ept.WriteSpectrumAsEMSA1_0.write(spec,fos,0)
         if (x%8==0) and (y%8==0):
            display(spec)
print "Done!"
```
