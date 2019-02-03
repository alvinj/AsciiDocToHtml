# AsciiDoc to HTML

As its name implies, this Scala project converts AsciiDoc to HTML.


## A sample conversion

As an example of what this code does, it converts AsciiDoc
that looks like this:

````
= Hello

== Hello, world

Ipsum lorem whatev ...

[source,scala]
----
println("Hello")
----
````

and converts it into HTML that looks like this:

````
<h2 id="_hello_world">Hello, world</h2>   

<p>Ipsum lorem whatev …​</p>    

<pre><code class="language-scala" data-lang="scala">println("Hello")</code></pre>
````


## Update

The project is broken up into three components:

- Common code
- The command line shell script
- A little JavaFX GUI


## Background

I’m currently writing a book using AsciiDoc, and
I thought it would make my life easier if I also
wrote blog posts in AsciiDoc right now. The 
problem with that assumption is that I haven’t
been able to find a tool to convert AsciiDoc to
HTML. Tools exist to convert AsciiDoc to Markdown,
but they drop important formatting information
that they could retain. Tools also exist to 
convert AsciiDoc to HTML, but they include a 
*lot* of CSS attributes, when all I want is 
simple HTML like I’d get with my usual
“Markdown to HTML” tools.

Since this tool is written to convert a single
AsciiDoc file to HTML text for a blog post,
I made several assumptions when writing it.


## Assumptions

The code makes several assumptions:

- The input file is written in AsciiDoc
- When you use `==` you want an `<h2>` tag, `===` means `<h3>` tag, etc.
- AsciidoctorJ creates `id` attributes in the H1-H6 tags. I retain those
  ids, but that isn’t necessary. You can see where I keep them in the
  code, currently in the `configureWhitelistAttributes` function.

Also note that I retain programming language information in pre/code tags. 
By that I mean that if you write this in your AsciiDoc file:

````
[source,scala]
----
"joe".hello
----
````

that means that you want this in your HTML:

````
<pre><code class="language-scala" data-lang="scala">
"joe".hello
</code></pre>
````

  
## Dependencies

This code depends on [AsciidoctorJ](https://asciidoctor.org/docs/asciidoctorj/) 
to handle the “AsciiDoc to HTML” conversion. That may 
require you to have [Asciidoctor](https://asciidoctor.org/) 
installed; I’m not sure about that because I installed Asciidoctor 
first.

This project also uses [JSoup](https://jsoup.org/) to clean up 
the HTML that’s created by AsciidoctorJ. AsciidoctorJ does a great
job of converting AsciiDoc to HTML, but its HTML includes
*a lot* of DIV and SPAN tags and many attributes, and since
I don’t want those things — and I can’t see any way to remove
them — I remove them with JSoup.



  


