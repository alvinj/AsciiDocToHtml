package asc2html

import java.util.HashMap
import org.asciidoctor.Asciidoctor
import scala.io.Source
import java.io.File
import Asc2HtmlUtils._

object AsciidocToHtml extends App {

    if (args.length != 1) {
        showUsage
        System.exit(1)
    }


    // PART 1: READ THE INPUT FILE
    // ---------------------------
    val filename = args(0).trim
    val file = new File(filename)
    if (!file.exists()) {
        System.err.println(s"The file $filename does not exist. Quitting.")
        System.exit(2)
    }
    val fileContents = Source.fromFile(filename).getLines.mkString("\n")


    // PART 2: Convert AsciiDoc -> HTML with AsciidoctorJ
    // --------------------------------------------------
    val asciidoctor: Asciidoctor = Asciidoctor.Factory.create
    val html: String = asciidoctor
        .convert(
            fileContents,
            new HashMap[String, Object]()
        )


    // PART 3: I don’t like the AsciidoctorJ HTML, so clean it up with JSoup
    // ---------------------------------------------------------------------
    import org.jsoup.Jsoup
    import org.jsoup.safety.Whitelist

    val wl = Whitelist.simpleText  //allows b, em, i, strong, & u
    configureWhitelistTagsToKeep(wl)
    configureWhitelistAttributes(wl)

    val cleanButUglyHtml = Jsoup.clean(html, wl)
    val prettierHtml = insertBlankLinesBeforeHtmlTags(cleanButUglyHtml)
    println(prettierHtml)

}




