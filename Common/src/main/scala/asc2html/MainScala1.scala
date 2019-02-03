//package asc2html
//
//import java.io.File
//import java.util
//
//import org.asciidoctor.Asciidoctor
//
///**
//  * This was my first attempt at learning how to use AsciidoctorJ.
//  */
//object MainScala1 extends App {
//
//    //val filename = "implicits.adoc"
//    //val fileContents = Source.fromFile(filename).getLines.mkString
//
//    val asciidoctor: Asciidoctor = Asciidoctor.Factory.create
//    val output = asciidoctor
//        .convertFile(
//            new File("implicits.adoc"),
//            new util.HashMap[String, Object]()
//        )
//    println(output)
//
//}
