CD %BPATH%\DTSA-II\Installer
REM Build basic installer (DTSA-II)
"%SED_PATH%\sed.exe" -e "s/NUMBER_VERSION/%NUM_VER%/g" -e "s/DATE_VERSION/%D2V%/g" -e "s/NAME_VERSION/%NAME_VER%/g" "%BPATH%\DTSA-II\Installer\izPack_install.template" > "%BPATH%\DTSA-II\Installer\izPack_install.xml" || exit /b
call "%IZPACK_HOME%\compile.bat" -h ${IZPACK_HOME} izPack_install.xml -b . -o dtsa2_%NAME_VER%.jar -k standard || exit /b
DEL "%BPATH%\DTSA-II\Installer\izPack_install.xml"

REM Build basic installer (no JRE) (DTSA-II)
"%SED_PATH%\sed.exe" -e "s/NUMBER_VERSION/%NUM_VER%/g" -e "s/DATE_VERSION/%D2V%/g" -e "s/NAME_VERSION/%NAME_VER%/g" "%BPATH%\DTSA-II\Installer\izPack_install_nojre.template" > "%BPATH%\DTSA-II\Installer\izPack_install_nojre.xml" || exit /b
call "%IZPACK_HOME%\compile.bat" -h ${IZPACK_HOME} izPack_install_nojre.xml -b . -o dtsa2_%NAME_VER%_nojre.jar -k standard || exit /b
DEL "%BPATH%\DTSA-II\Installer\izPack_install_nojre.xml"

if [%FULL_BUILD%] == [True] (
REM Build full installer (DTSA-II & SEMantics & Graf)
CD %BPATH%\DTSA-II\Installer
"%SED_PATH%\sed.exe" -e "s/NUMBER_VERSION/%NUM_VER%/g" -e "s/DATE_VERSION/%D2V%/g" -e "s/NAME_VERSION/%NAME_VER%/g" "%BPATH%\DTSA-II\Installer\izPack_install_full.template" > "%BPATH%\DTSA-II\Installer\izPack_install_full.xml" || exit /b
call "%IZPACK_HOME%\compile.bat" -h ${IZPACK_HOME} izPack_install_full.xml -b . -o dtsa2_%NAME_VER%_full.jar -k standard || exit /b 
DEL "%BPATH%\DTSA-II\Installer\izPack_install_full.xml"
)