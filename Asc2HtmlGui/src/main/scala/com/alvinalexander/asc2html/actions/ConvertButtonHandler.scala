package com.alvinalexander.asc2html.actions

import com.alvinalexander.asc2html.controller.MainController
import com.alvinalexander.asc2html.view.{GuiUtils, HtmlDialogPane}
import javafx.application.Platform

class ConvertButtonHandler (mainController: MainController)
extends BaseHandler(mainController)
{

    override def updateGui(html: String): Unit = {
        val runnable = new Runnable {
            override def run(): Unit = {
                val htmlPane = new HtmlDialogPane
                htmlPane.htmlTextArea.setText(html)
                GuiUtils.showHtmlDialog("Your HTML", htmlPane)
            }
        }
        Platform.runLater(runnable)
    }

}
