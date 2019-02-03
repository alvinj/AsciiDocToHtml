package com.alvinalexander.asc2html.view

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.control.{ButtonType, Dialog}
import javafx.stage.Screen

object GuiUtils {

    def screenHeight(): Double = getScreenBounds.getHeight
    def screenWidth(): Double = getScreenBounds.getWidth
    def getScreenBounds(): Rectangle2D = Screen.getPrimary.getVisualBounds

    def showHtmlDialog(
        title: String = "Your HTML",
        htmlPane: HtmlDialogPane
    ): Unit = {

        // create the "Edit Note" dialog
        val htmlDialog = new Dialog[Unit]()
        htmlDialog.setTitle(title)
        val okButton = new ButtonType("OK", ButtonData.OK_DONE)
        htmlDialog.getDialogPane.getButtonTypes.addAll(okButton)
        htmlDialog.getDialogPane.setContent(htmlPane)
        htmlDialog.setResizable(true)

        // set initial focus
        Platform.runLater(() => htmlPane.htmlTextArea.requestFocus)

        val result = htmlDialog.showAndWait()

    }

}
