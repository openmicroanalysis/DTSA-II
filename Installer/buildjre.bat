rem jdeps --print-module-deps dtsa2.jar epq.jar commons-codec-1.10.jar commons-collections4-4.4.jar commons-math3-3.6.1.jar derby-10.14.1.0.jar derbyclient-10.14.1.0.jar commons-math3-3.6.1.jar fastQuant.jar jai-imageio-core-1.3.1.jar jama-1.0.3.jar jcalendar-1.4.jar jgoodies-common-1.8.0.jar jgoodies-forms-1.8.0.jar jna-3.0.9.jar junit-4.12.jar poi-3.17.jar semantics.jar graf.jar xmlpull-1.1.3.1.jar xpp3_min-1.1.4c.jar xstream-1.4.11.1.jar 
rem jdeps --print-module-deps graf.jar epq.jar commons-codec-1.10.jar commons-collections4-4.4.jar commons-math3-3.6.1.jar derby-10.14.1.0.jar derbyclient-10.14.1.0.jar commons-math3-3.6.1.jar fastQuant.jar jai-imageio-core-1.3.1.jar jama-1.0.3.jar jcalendar-1.4.jar jgoodies-common-1.8.0.jar jgoodies-forms-1.8.0.jar jna-3.0.9.jar junit-4.12.jar poi-3.17.jar semantics.jar graf.jar xmlpull-1.1.3.1.jar xpp3_min-1.1.4c.jar xstream-1.4.11.1.jar poi-3.17.jar jcalendar-1.4.jar jython-standalone-2.7.4.jar 

rem jdeps --print-module-deps commons-codec-1.10.jar commons-collections4-4.4.jar commons-math3-3.6.1.jar derby-10.14.1.0.jar derbyclient-10.14.1.0.jar derbynet-10.14.1.0.jar dtsa2.jar epq.jar fastQuant.jar graf.jar hamcrest-core-1.3.jar jai-imageio-core-1.3.1.jar jama-1.0.3.jar jcalendar-1.4.jar jgoodies-common-1.8.0.jar jgoodies-forms-1.8.0.jar jna-3.0.9.jar junit-4.12.jar jython-standalone-2.7.4.jar NISTGlassDB.jar poi-3.17.jar relocate.jar semantics.jar xmlpull-1.1.3.1.jar xpp3_min-1.1.4c.jar xstream-1.4.11.1.jar 
cd "C:\Users\nritchie\repositories\DTSA-II\Installer\DTSA-II Build"
rmdir /S /Q java-runtime
"C:\Program Files\Eclipse Adoptium\jdk-21.0.5.11-hotspot\bin\jlink.exe" --module-path "C:\Program Files\Eclipse Adoptium\jdk-21.0.5.11-hotspot\jmods" --no-header-files --no-man-pages --strip-debug --add-modules java.base,java.compiler,java.desktop,java.management,java.naming,java.prefs,java.scripting,java.sql,jdk.unsupported,jdk.xml.dom --output java-runtime