/**
 * gov.nist.microanalysis.Trixy.HTMLReport Created by: nritchie Date: Jun 5,
 * 2007
 */
package gov.nist.microanalysis.dtsa2;

import java.awt.Component;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import gov.nist.microanalysis.EPQLibrary.EPQException;
import gov.nist.microanalysis.EPQTools.ErrorDialog;

/**
 * <p>
 * A helper to create the base HTML report object.
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
public class HTMLReport {

   private final String mReportName;
   private File mFile;

   static private Map<String, HTMLReport> mInstances = new TreeMap<>();

   private HTMLReport(String reportName) {
      mReportName = reportName;
      final File f = getFile();
      boolean useCSS = true;
      {
         final File css = new File(f.getParentFile(), "style.css");
         if (!css.exists()) {
            // Write the style sheet
            try (final PrintWriter osw = new PrintWriter(css)) {
               try (final BufferedReader isr = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("style.css")))) {
                  for (String str = isr.readLine(); str != null; str = isr.readLine()) {
                     osw.println(str);
                  }
               }
            } catch (final Exception e) {
               useCSS = false;
            }
         }
      }
      if (!f.exists()) {
         try {
            final String date = DateFormat.getDateInstance().format(new Date());
            final String time = DateFormat.getTimeInstance().format(new Date());
            try (final PrintWriter pw = new PrintWriter(f)) {
               pw.println("<html>");
               pw.println(" <head>");
               if (useCSS) {
                  pw.println("  <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
               }
               pw.println("  <title>" + reportName + " - " + date + "</title>");
               pw.println(" </head>");
               pw.println(" <body>");
               pw.println(
                     "<table class=\"noborder\"><tr><td><h1>NIST DTSA-II</h1></td><td><p align=\"right\">Power tools for x-ray microanalysis</p></td></tr></table>");
               pw.println("  <table>");
               pw.println("   <tr><th align=\"right\">" + DTSA2.APP_NAME + " Version</th><td>" + DTSA2.getRevision(DTSA2.class) + "</td></tr>");
               pw.println("   <tr><th align=\"right\">EPQ Algorithm Library Version</th><td>" + DTSA2.getRevision(EPQException.class) + "</td></tr>");
               pw.println("   <tr><th align=\"right\">System User</th><td>" + System.getProperty("user.name") + "</td></tr>");
               pw.println("   <tr><th align=\"right\">Date</th><td>" + date + "</td></tr>");
               pw.println("   <tr><th align=\"right\">Time</th><td>" + time + "</td></tr>");
               pw.println("  </table>");
               pw.println("  <br>");
               pw.println(" </body>");
               pw.println("</html>");
            }
         } catch (final FileNotFoundException e) {
            throw new Error("Unable to create the report file.");
         }
      }
      mInstances.put(mReportName, this);
   }

   static public synchronized HTMLReport getInstance(String reportName) {
      HTMLReport res = mInstances.get(reportName);
      if (res == null) {
         res = new HTMLReport(reportName);
      }
      return res;
   }

   public synchronized File getFile() {
      if (mFile == null) {
         final String base = AppPreferences.getInstance().getBaseReportPath();
         File file = new File(base);
         assert file.exists();
         assert file.isDirectory();
         assert file.canWrite();
         final Calendar c = Calendar.getInstance();
         final Locale locale = Locale.getDefault();
         final File year = new File(file, Integer.toString(c.get(Calendar.YEAR)));
         final File month = new File(year, c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale));
         final File subDir = new File(month, Integer.toString(c.get(Calendar.DAY_OF_MONTH)) + "-"
               + c.getDisplayName(Calendar.MONTH, Calendar.SHORT, locale) + "-" + Integer.toString(c.get(Calendar.YEAR)));
         if (!(subDir.exists() || subDir.mkdirs())) {
            final Error err = new Error("Unable to create report directory.\n" + subDir.getAbsolutePath());
            ErrorDialog.createErrorMessage(DTSA2.getInstance(null).getFrame(), "Fatal error", err);
            throw err;
         }
         for (int i = 1; i < 1000; ++i) {
            mFile = new File(subDir, "index" + Integer.toString(i) + ".html");
            if (!mFile.exists()) {
               break;
            }
         }
      }
      return mFile;
   }



   public void openInBrowser(Component c) {
      try {
         Desktop.getDesktop().browse(getFile().toURI());
      } catch (final Exception e) {
         ErrorDialog.createErrorMessage(c, "Open report", e);
      }
   }
}
