# Asc2HtmlGui (AsciiDoc to HTML GUI)

This is the GUI for my “AsciiDoc to HTML” conversion
application.


## Requirements

An important note is that this application requires the 
_AsciiDocToHtml-assembly-**.jar_ file that’s built in the 
Common project. It must be in the _lib_ folder of this 
project (though that could be bundled as a Maven/Ivy
dependency and included that way.) I don’t include it 
in the Git repo because it’s about 32MB.


## Building the app

I’ve only built the app on a MacOS system so far, but
it should also work on other Unix/Linux systems.

To build the app, run the _build.sbt script that’s in 
the root directory. On a MacOS system, when that process
finishes it should have created the app in this directory:

- release/bundles/Asc2Html.app

As you’ll see in the build script, the build process
primarily relies on:

- sbt-assembly
- javapackager
- As mentioned above, the build also depends on the
  _AsciiDocToHtml-assembly-**.jar_ file that’s built 
  in the Common project


## Discussion

There isn’t too much to say about the GUI except:

- It’s written with Scala and JavaFX
- I cobbled it together quickly, so the code can be
  improved a lot


## TODO

A few things the app could use:

- A progress bar during the conversion process
- The conversion happens much faster after you run
  it once, so it might be a good idea to run it 
  once (warm it up )on a background thread when 
  the app first starts
- An HTML previewer
- Maybe animate the GUI instead of using dialogs
  to show the results






