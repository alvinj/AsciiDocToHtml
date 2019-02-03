package com.alvinalexander.asc2html.actions

import javafx.event.{ActionEvent, EventHandler}
import com.alvinalexander.asc2html.controller.MainController
import com.alvinalexander.asc2html.view.{GuiUtils, HtmlDialogPane}

class ConvertButtonHandler (mainController: MainController)
extends EventHandler[ActionEvent] {

    override def handle(event: ActionEvent): Unit = {
        //TODO
        // get asciidoc text from the text area
        val asciidoc = mainController.mainGridPane.textArea.getText

        // TODO convert asciidoc to html
        val html = "Hello, world"

        // show the html in a new dialog
        val htmlPane = new HtmlDialogPane
        htmlPane.htmlTextArea.setText(html)
        GuiUtils.showHtmlDialog("Your HTML", htmlPane)

    }

}
