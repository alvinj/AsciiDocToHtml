package com.alvinalexander.asc2html.view

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.control.{ButtonType, Dialog}
import javafx.scene.layout.GridPane
import javafx.stage.Screen

object GuiUtils {

    def screenHeight(): Double = getScreenBounds.getHeight
    def screenWidth(): Double = getScreenBounds.getWidth
    def getScreenBounds(): Rectangle2D = Screen.getPrimary.getVisualBounds

    def showHtmlDialog(
        title: String = "Your HTML",
        htmlPane: GridPane
    ): Unit = {

        val dialog = new Dialog[Unit]()
        dialog.setTitle(title)
        val okButton = new ButtonType("OK", ButtonData.OK_DONE)
        dialog.getDialogPane.getButtonTypes.addAll(okButton)
        dialog.getDialogPane.setContent(htmlPane)
        dialog.setResizable(true)

        // TODO helps to use this with TextAreas
        //Platform.runLater(() => htmlPane.htmlTextArea.requestFocus)

        val result = dialog.showAndWait()

    }

}
