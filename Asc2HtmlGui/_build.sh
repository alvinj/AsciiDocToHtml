#!/bin/sh

export JAVA_HOME=`/usr/libexec/java_home -v 1.8`

#--------------------------------------------
# (1) use sbt-assembly to create one jar file
#--------------------------------------------
echo "Running 'sbt assembly' ..."
sbt assembly
if [ $? != 0 ]
then
  echo "SBT-ASSEMBLY FAILED, STOPPING BUILD PROCESS"
  exit 1
fi
echo "\n"


#--------------------------------------------------------------
# (2) javapackager - create a native app from that one jar file
#--------------------------------------------------------------
echo "Running javapackager ..."
# SEE https://docs.oracle.com/javase/8/docs/technotes/tools/unix/javapackager.html
JAVA_HOME=`/usr/libexec/java_home -v 1.8`
APP_DIR_NAME=Asc2Html.app
APP_NAME=Asc2Html
ICON_FILE=build/Asc2Html.icns
APP_MAIN=com.alvinalexander.asc2html.Asc2HtmlGui
INPUT_JAR_FILE=target/scala-2.12/AsciiDocToHtmlGui-assembly-0.1.jar

# javapackager command notes:
#   - '-native image' creates a ".app" file (as opposed to DMG or other)
#   - '-name' is used as the app name in the menubar if you don't specify "-Bmac.CFBundleName"
#   - oracle notes says "use cms for desktop apps"
#   - 'v' is for verbose mode. remove it if you don't want/need to see all of the output
#   - '-Bmac' options are for macOS
javapackager \
  -deploy -Bruntime=${JAVA_HOME} \
  -native image \
  -srcdir . \
  -srcfiles $INPUT_JAR_FILE \
  -appclass $APP_MAIN \
  -outdir release \
  -outfile ${APP_DIR_NAME} \
  -name $APP_NAME \
  -title $APP_NAME \
  -Bicon=$ICON_FILE \
  -Bmac.CFBundleIdentifier=$APP_MAIN \
  -Bmac.CFBundleName=$APP_NAME \
  -Bmac.category=public.app-category.productivity \
  -BjvmOptions=-Xms64m \
  -BjvmOptions=-XX:+UseConcMarkSweepGC \
  -BjvmOptions=-XX:ParallelCMSThreads=2 \
  -BjvmOptions=-XX:PermSize=20m \
  -BjvmOptions=-XX:MaxPermSize=20m \
  -BjvmOptions=-Dapple.laf.useScreenMenuBar=true \
  -BjvmOptions=-Dcom.apple.smallTabs=true \
  -nosign \
  -v

if [ $? != 0 ]
then
echo "JAVAPACKAGER FAILED, STOPPING BUILD PROCESS"
  exit 2
fi
echo "\n"

echo ""
echo "If that succeeded, it created \"release/bundles/${APP_DIR_NAME}\""


#-----------------------------------------------------
# (3) manually adjust Info.plist file (add hi-res key)
#-----------------------------------------------------
sh build/_addHiResKeyToPlistFile.sh







