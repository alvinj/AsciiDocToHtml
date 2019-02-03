package com.alvinalexander.asc2html.actions

import com.alvinalexander.asc2html.controller.MainController
import com.alvinalexander.asc2html.view.{GuiUtils, HtmlPreviewerPane}
import javafx.application.Platform

class PreviewButtonHandler (mainController: MainController)
extends BaseHandler(mainController)
{

    override def updateGui(html: String): Unit = {
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

}
