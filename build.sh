#! /bin/bash
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

# compile
mkdir -p bin
javac -d bin src/*.java
if [ $? -ne 0 ]; then exit 1; fi
echo "compile ok"

# archive
OUTDIR="JavaLaunchExample.app/Contents/Resources/Java"
mkdir -p "$OUTDIR"
jar cvf "$OUTDIR/SimpleJavaApp.jar" -C bin .
if [ $? -ne 0 ]; then exit 1; fi
echo "archive ok"

# set app-bundle flag
chmod a+x JavaLaunchExample.app/Contents/MacOS/JavaLaunchExample.sh
SetFile -a B JavaLaunchExample.app
if [ $? -ne 0 ]; then exit 1; fi
echo "set flag ok"

# cleanup
rm -fr bin
echo "done."
