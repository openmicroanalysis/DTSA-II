package gov.nist.microanalysis.dtsa2;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gov.nist.microanalysis.EPQLibrary.AbsoluteIonizationCrossSection;
import gov.nist.microanalysis.EPQLibrary.AlgorithmClass;
import gov.nist.microanalysis.EPQLibrary.AlgorithmUser;
import gov.nist.microanalysis.EPQLibrary.BremsstrahlungAngularDistribution;
import gov.nist.microanalysis.EPQLibrary.CorrectionAlgorithm;
import gov.nist.microanalysis.EPQLibrary.MassAbsorptionCoefficient;
import gov.nist.microanalysis.EPQLibrary.Strategy;
import gov.nist.microanalysis.EPQLibrary.Detector.DetectorProperties;
import gov.nist.microanalysis.EPQTools.ErrorDialog;

/**
 * <p>
 * A dialog for edit various different application preferences and configuration
 * options.
 * </p>
 * <p>
 * Copyright: Pursuant to title 17 Section 105 of the United States Code this
 * software is not subject to copyright protection and is in the public domain
 * </p>
 * <p>
 * Institution: National Institute of Standards and Technology
 * </p>
 *
 * @author nritchie
 * @version 1.0
 */
public class AppPreferences {

   enum Appearance {
      System, FlatLight, FlatMacLightLaf, FlatMacDarkLaf, Darcula, Ugly
   }

   private static final String DEFAULT_DETECTOR_KEY = "Default Detector";
   static final String EPQ_JAVA_DOC_DEFAULT = "http://www.cstl.nist.gov/div837/837.02/epq/dtsa2/JavaDoc/index.html";

   // User information
   private String mUserName;
   private String mBaseReportPath;
   private String mStartupScript = "";
   private String mShutdownScript = "";
   private String mEPQJavaDoc = EPQ_JAVA_DOC_DEFAULT;
   private boolean mVariableFF = true;
   private boolean mOFudge = false;
   private List<File> mJythonPaths = new ArrayList<>();
   // Algorithms
   private String mCorrectionAlgorithm;
   private String mMACAlgorithm;
   private String mBremAngular;
   private String mIonizationXSec;

   /**
    * Default tolerance for assuming that two spectra are calibrated the same.
    * Use with SpectrumUtils.areCalibratedSimilar.
    */
   static public final double DEFAULT_TOLERANCE = 0.01;

   static private AppPreferences mInstance = new AppPreferences();

   static public AppPreferences getInstance() {
      return mInstance;
   }

   private AppPreferences() {
      final Preferences userPref = Preferences.userNodeForPackage(getClass());
      mUserName = userPref.get("UserName", System.getProperty("user.name"));
      mStartupScript = userPref.get("StartupScript", "");
      mShutdownScript = userPref.get("ShutdownScript", "");
      mEPQJavaDoc = userPref.get("EPQJavaDoc", EPQ_JAVA_DOC_DEFAULT);
      mCorrectionAlgorithm = userPref.get("CorrectionAlgorithm", CorrectionAlgorithm.XPPExtended.getName());
      mMACAlgorithm = userPref.get("MACAlgorithm", MassAbsorptionCoefficient.Default.getName());
      mBremAngular = userPref.get("Bremsstrahlung angular distribution", BremsstrahlungAngularDistribution.Acosta2002L.getName());
      mIonizationXSec = userPref.get("Ionization cross section", AbsoluteIonizationCrossSection.BoteSalvat2008.getName());
      mBaseReportPath = initBaseReportPath();
      mVariableFF = userPref.getBoolean("Variable FF", true);
      mOFudge = userPref.getBoolean("Oxygen fudge", false);
      mJythonPaths = readJythonLibraryPaths();
      updateStrategy();
   }


   /**
    * Gets the current value assigned to correctionAlgorithm
    *
    * @return Returns the correctionAlgorithm.
    */
   public String getCorrectionAlgorithm() {
      return mCorrectionAlgorithm;
   }

   /**
    * Sets the value assigned to correctionAlgorithm.
    *
    * @param correctionAlgorithm
    *           The value to which to set correctionAlgorithm.
    */
   public void setCorrectionAlgorithm(String correctionAlgorithm) {
      if (!mCorrectionAlgorithm.equals(correctionAlgorithm)) {
         mCorrectionAlgorithm = correctionAlgorithm;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("CorrectionAlgorithm", mCorrectionAlgorithm);
         updateStrategy();
      }
   }

   public String getIonizationCrossSection() {
      return mIonizationXSec;
   }

   public void setIonizationCrossSection(String alg) {
      if (!mIonizationXSec.equals(alg)) {
         mIonizationXSec = alg;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("Ionization cross section", mIonizationXSec);
         updateStrategy();
      }
   }

   public String getBremsstrahlungAngularDistribution() {
      return mBremAngular;
   }

   public void setBremsstrahlungAngularDistribution(String alg) {
      if (!mBremAngular.equals(alg)) {
         mBremAngular = alg;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("Bremsstrahlung angular distribution", mBremAngular);
         updateStrategy();
      }
   }

   public boolean useVariableFF() {
      return mVariableFF;
   }

   public void setUseVariableFF(boolean b) {
      if (mVariableFF != b) {
         mVariableFF = b;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.putBoolean("Variable FF", mVariableFF);
      }
   }

   public boolean useOFudge() {
      return mOFudge;
   }

   public void setUseOFudge(boolean b) {
      if (mOFudge != b) {
         mOFudge = b;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.putBoolean("Oxygen fudge", mOFudge);
      }
   }

   /**
    * Gets the current value assigned to mACAlgorithm
    *
    * @return Returns the mACAlgorithm.
    */
   public String getMACAlgorithm() {
      return mMACAlgorithm;
   }

   /**
    * Sets the value assigned to mACAlgorithm.
    *
    * @param algorithm
    *           The value to which to set mACAlgorithm.
    */
   public void setMACAlgorithm(String algorithm) {
      if (!mMACAlgorithm.equals(algorithm)) {
         mMACAlgorithm = algorithm;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("MACAlgorithm", mMACAlgorithm);
         updateStrategy();
      }
   }

   /**
    * Gets the current value assigned to userName
    *
    * @return Returns the userName.
    */
   public String getUserName() {
      return mUserName;
   }

   public String getStartupScript() {
      return mStartupScript;
   }

   /**
    * Sets the value assigned to startup script path
    *
    * @param path
    */
   public void setStartupScript(String path) {
      if (!mStartupScript.equals(path)) {
         mStartupScript = path;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("StartupScript", mStartupScript);
      }
   }

   public String getShutdownScript() {
      return mShutdownScript;
   }

   /**
    * Sets the value assigned to shutdown script path.
    *
    * @param path
    */
   public void setShutdownScript(String path) {
      if (!mShutdownScript.equals(path)) {
         mShutdownScript = path;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("ShutdownScript", mShutdownScript);
      }
   }

   public void setEPQJavaDoc(String path) {
      if (!mEPQJavaDoc.equals(path)) {
         mEPQJavaDoc = path;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("EPQJavaDoc", mEPQJavaDoc);
      }
   }

   public String getEPQJavaDoc() {
      return mEPQJavaDoc;
   }

   public void setAppearance(Appearance laf) {
      final Preferences userPref = Preferences.userNodeForPackage(getClass());
      userPref.put("LookAndFeel", laf.toString());
   }

   public void applyAppearance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
      final Preferences userPref = Preferences.userNodeForPackage(getClass());
      Appearance laf = Appearance.valueOf(userPref.get("LookAndFeel", Appearance.FlatLight.toString()));
      switch (laf) {
         case Darcula :
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            break;
         case FlatLight :
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Table.showHorizontalLines", Boolean.TRUE);
            UIManager.put("Table.showVerticalLines", Boolean.TRUE);
            UIManager.put("TabbedPane.selectedBackground", Color.white);
            break;
         case FlatMacLightLaf :
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("Table.showHorizontalLines", Boolean.TRUE);
            UIManager.put("Table.showVerticalLines", Boolean.TRUE);
            UIManager.put("TabbedPane.selectedBackground", Color.white);
            break;
         case FlatMacDarkLaf :
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
            break;
         case Ugly :
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            break;
         case System :
         default :
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            break;
      }
   }

   public void setJythonLibraryPaths(List<File> paths) {
      final Preferences userPref = Preferences.userNodeForPackage(getClass());
      int i = 0;
      for (File path : paths) {
         try {
            userPref.put("Jython Path[" + i + "]", path.getCanonicalPath().toString());
            ++i;
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      userPref.putInt("Jython Path Count", i);
   }

   public List<File> getJythonLibraryPaths() {
      return mJythonPaths;
   }

   private ArrayList<File> readJythonLibraryPaths() {
      final Preferences userPref = Preferences.userNodeForPackage(getClass());
      int cx = userPref.getInt("Jython Path Count", 0);
      ArrayList<File> res = new ArrayList<>();
      for (int i = 0; i < cx; ++i) {
         String file = userPref.get("Jython Path[" + i + "]", null);
         if (file != null) {
            File ff = new File(file);
            if (ff.isDirectory()) {
               res.add(new File(file));
            }
         }
      }
      return res;
   }

   public void openEPQJavaDoc() {
      try {
         openURL(getEPQJavaDoc());
      } catch (final Exception e1) {
         try {
            openURL(EPQ_JAVA_DOC_DEFAULT);
         } catch (final Exception e2) {
            ErrorDialog.createErrorMessage(DTSA2.getInstance(null).getFrame(), "Open EPQ library documentation", e2);
         }
      }

   }

   private void openURL(String path) {
      try {
         if (path.startsWith("http://")) {
            Desktop.getDesktop().browse(new URI(path));
         } else {
            final File f = new File(path, "index.html");
            Desktop.getDesktop().browse(f.toURI());
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Sets the value assigned to userName.
    *
    * @param userName
    *           The value to which to set userName.
    */
   public void setUserName(String userName) {
      if (!mUserName.equals(userName)) {
         mUserName = userName;
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         userPref.put("UserName", mUserName);
      }
   }

   /**
    * Gets the current value assigned to the base report path
    *
    * @return String
    */
   public String getBaseReportPath() {
      return mBaseReportPath;
   }

   public AlgorithmClass lookUp(List<AlgorithmClass> lac, String name) {
      for (final AlgorithmClass ac : lac) {
         if (ac.getName().equals(name)) {
            return ac;
         }
      }
      return null;
   }

   private void updateStrategy() {
      final Strategy strat = new Strategy();
      {
         final AlgorithmClass ac = lookUp(CorrectionAlgorithm.NullCorrection.getAllImplementations(), mCorrectionAlgorithm);
         if (ac != null) {
            strat.addAlgorithm(CorrectionAlgorithm.class, ac);
         }
      }
      {
         final AlgorithmClass ac = lookUp(MassAbsorptionCoefficient.Null.getAllImplementations(), mMACAlgorithm);
         if (ac != null) {
            strat.addAlgorithm(MassAbsorptionCoefficient.class, ac);
         }
      }
      {
         final AlgorithmClass ac = lookUp(AbsoluteIonizationCrossSection.BoteSalvat2008.getAllImplementations(), mIonizationXSec);
         if (ac != null) {
            strat.addAlgorithm(AbsoluteIonizationCrossSection.class, ac);
         }
      }
      {
         final AlgorithmClass ac = lookUp(BremsstrahlungAngularDistribution.Acosta2002.getAllImplementations(), mBremAngular);
         if (ac != null) {
            strat.addAlgorithm(BremsstrahlungAngularDistribution.class, ac);
         }
      }
      AlgorithmUser.applyGlobalOverride(strat);
   }

   public DetectorProperties getDefaultDetector() {
      try {
         final Preferences userPref = Preferences.userNodeForPackage(getClass());
         final String det = userPref.get(DEFAULT_DETECTOR_KEY, null);
         if (det != null) {
            for (final DetectorProperties dp : DTSA2.getSession().getDetectors()) {
               if (dp.toString().equals(det)) {
                  return dp;
               }
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   public void setDefaultDetector(DetectorProperties det) {
      final Preferences userPref = Preferences.userNodeForPackage(getClass());
      if (det != null) {
         userPref.put(DEFAULT_DETECTOR_KEY, det.toString());
      } else {
         userPref.remove(DEFAULT_DETECTOR_KEY);
      }
   }

   private static final String sfBASE_PATH = "Base Path";

   
   public static String initBaseReportPath() {
      final String tmp = Preferences.userNodeForPackage(AppPreferences.class).get(sfBASE_PATH, null);
      final File tmpFile = tmp != null ? new File(tmp) : null;
      if ((tmp == null) || !(tmpFile.exists() && tmpFile.isDirectory() && tmpFile.canWrite())) {
         final JFileChooser fc = new JFileChooser();
         final File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory(), "DTSA-II Reports");
         final boolean made = file.mkdirs();
         try {
            if (file.isDirectory()) {
               try {
                  fc.setCurrentDirectory(file);
               } catch (Exception e) {
                  fc.setCurrentDirectory(null);
               }
            }
            fc.setDialogType(JFileChooser.SAVE_DIALOG);
            fc.setDialogTitle("Select a location to store " + DTSA2.APP_NAME + " reports,");
            // There appears to be a bug with setCurrentDirectory if setFileSelectionMode(DIRECTORIES_ONLY) is called first.
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            boolean ok = false;
            while (!ok) {
               if (fc.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION) {
                  final File result = fc.getSelectedFile();
                  try {
                     if(!result.exists()) {
                        result.mkdirs();
                     }
                     ok = false;
                     if (result.exists() && result.canWrite()) {
                        final File tester = File.createTempFile("test", ".txt", result);
                        try {
                           ok = tester.isFile();
                        }
                        finally {
                           tester.delete();
                        }
                        Preferences.userNodeForPackage(AppPreferences.class).put(sfBASE_PATH, result.getCanonicalPath());
                        return result.getCanonicalPath();
                     }
                  } catch (IOException e) {
                     ErrorDialog.createErrorMessage(null, "Report Directory Creation Error", "The report directory specified is not writable.",
                           e.getMessage());
                  }
               }
            }
         } finally {
            if (made)
               file.delete();
         }
      }
      return tmp;
   }

   public boolean setBaseReportPath(String path) {
      final File f = new File(path);
      // Suitable
      boolean set = f.exists() && f.isDirectory() && f.canWrite();
      if (set) {
         // Not same as previous
         String oldPath = Preferences.userNodeForPackage(AppPreferences.class).get(sfBASE_PATH, null);
         if (oldPath != null) {
            final File old = new File(oldPath);
            set = !f.equals(old);
         }
      }
      if (set) {
         JOptionPane.showMessageDialog(DTSA2.getInstance(null).getFrame(),
               "The change in report directories will take place when " + DTSA2.APP_NAME + " is restarted.", DTSA2.APP_NAME,
               JOptionPane.INFORMATION_MESSAGE);
         Preferences.userNodeForPackage(AppPreferences.class).put(sfBASE_PATH, path);
         return true;
      }
      return false;
   }

}
