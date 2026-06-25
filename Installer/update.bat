REM Run this script to pull the latest version of each
REM repository from GitHub and GitLab.
REM Warning:  It will over-write any changes that you
REM may have made to your local versions of these repositories.


REM DTSA-II
cd ..\ 
REM git stash 
git reset --hard
git pull

REM epq
cd ..\epq
REM git stash 
git reset --hard 
git pull

REM FastQuant
cd ..\FastQuant
REM git stash 
git reset --hard
git pull

REM Graf
cd ..\graf
REM git stash 
git reset --hard
git pull

REM JythonGUI
cd ..\JythonGUI
REM git stash 
git reset --hard
git pull

REM semantic
cd ..\semantics
REM git stash 
git reset --hard
git pull


REM NIST Glass Database
cd ..\nist-glass-database
REM git stash 
git reset --hard
git pull



