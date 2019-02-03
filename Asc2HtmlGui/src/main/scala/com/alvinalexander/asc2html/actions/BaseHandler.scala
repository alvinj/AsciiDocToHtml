package com.alvinalexander.asc2html.actions

import java.util.{HashMap => JHashMap}

import asc2html.Asc2HtmlUtils
import com.alvinalexander.asc2html.controller.MainController
import com.alvinalexander.asc2html.view.{GuiUtils, HtmlDialogPane}
import javafx.application.Platform
import javafx.event.{ActionEvent, EventHandler}
import org.asciidoctor.Asciidoctor
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future => ConcurrentTask}
import scala.util.{Failure, Success}

class BaseHandler(mainController: MainController)
extends EventHandler[ActionEvent] {

    override def handle(event: ActionEvent): Unit = {

        val asciidoc = mainController.mainGridPane.textArea.getText

        // conversion can take a while
        val task: ConcurrentTask[String] = ConcurrentTask {
            val html = convertAsciidocToHtml(asciidoc)
            html
        }

        task.onComplete {
            case Success(html) => updateGui(html)
            case Failure(e) => updateGui(e.getMessage)
        }

    }

    def updateGui(html: String): Unit = {
        val runnable = new Runnable {
            override def run(): Unit = {
                val htmlPane = new HtmlDialogPane
                htmlPane.htmlTextArea.setText(html)
                GuiUtils.showHtmlDialog("Your HTML", htmlPane)
            }
        }
        Platform.runLater(runnable)
    }

    //TODO this code is a dup from the other projects, refactor it
    def convertAsciidocToHtml(asciidoc: String): String = {

        val asciidoctor: Asciidoctor = Asciidoctor.Factory.create
        val html: String = asciidoctor
            .convert(
                asciidoc,
                new JHashMap[String, Object]()
            )

        val wl = Whitelist.simpleText  //allows b, em, i, strong, & u
        Asc2HtmlUtils.configureWhitelistTagsToKeep(wl)
        Asc2HtmlUtils.configureWhitelistAttributes(wl)

        val cleanButUglyHtml = Jsoup.clean(html, wl)
        val prettierHtml = Asc2HtmlUtils.insertBlankLinesBeforeHtmlTags(cleanButUglyHtml)
        prettierHtml

    }

}
