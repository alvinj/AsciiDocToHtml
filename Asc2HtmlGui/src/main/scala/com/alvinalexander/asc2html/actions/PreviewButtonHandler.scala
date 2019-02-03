package com.alvinalexander.asc2html.actions

import java.util.{HashMap => JHashMap}

import scala.concurrent.{Future => ConcurrentTask}
import scala.concurrent.ExecutionContext.Implicits.global
import asc2html.Asc2HtmlUtils
import javafx.event.{ActionEvent, EventHandler}
import com.alvinalexander.asc2html.controller.MainController
import com.alvinalexander.asc2html.view.{GuiUtils, HtmlPreviewerPane}
import org.asciidoctor.Asciidoctor
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import javafx.application.Platform

import scala.util.{Failure, Success}

//TODO This class is basically a copy and paste of ConverButtonHandler; make it DRY.
class PreviewButtonHandler (mainController: MainController)
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

    private def updateGui(html: String): Unit = {
        val runnable = new Runnable {
            override def run(): Unit = {
                val previewPane = new HtmlPreviewerPane
                val webEngine = previewPane.webView.getEngine
                webEngine.loadContent(html)
                GuiUtils.showHtmlDialog("HTML Preview", previewPane)
            }
        }
        Platform.runLater(runnable)
    }

    //TODO this code is a dup from the other projects, refactor it
    private def convertAsciidocToHtml(asciidoc: String): String = {

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
