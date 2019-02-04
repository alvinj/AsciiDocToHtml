# Asc2Html Shell Script

This is the shell script version of my “AsciiDoc to HTML” converter
project.

To run this shell script you need to build the JAR file in the
Common project. As you can see in the second line of the shell
script, that JAR file needs to be on the classpath. At the moment
the second line of the shell script looks like this:

````
exec scala -savecompiled -classpath "../target/scala-2.12/AsciiDocToHtml-assembly-0.1.jar" "$0" "$@"
````

In production what I do is:

- Copy this script to my _~/bin_ directory
- Put the JAR file in a _lib_ directory underneath that
- Change the second line to this:

````
exec scala -savecompiled -classpath "lib/AsciiDocToHtml-assembly-0.1.jar" "$0" "$@"
````

This is the first release of this code, so hopefully I’ll come up
with better approaches over time, but the shell script shows the 
current approach.


