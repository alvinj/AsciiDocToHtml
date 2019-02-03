package com.alvinalexander.asc2html.view

import javafx.scene.control._
import javafx.scene.layout._

import MainGridPaneUtils._

/**
  * Note to self: You can’t extend a Scene, so i’m extending GridPane, which
  * I’ll later add to a Scene.
  */
class MainGridPane() extends GridPane {

    // PRIVATE WIDGETS
    //private val topHbox = new HBox(10)
    private val bottomHbox = new HBox(10)

    // PUBLIC WIDGETS
    val textArea = new TextArea
    val scrollPane = new ScrollPane
    scrollPane.setContent(textArea)
    scrollPane.setFitToWidth(true)
    scrollPane.setFitToHeight(true)

    val convertButton = new Button("Convert")
    convertButton.setTooltip(new Tooltip("Convert AsciiDoc to HTML"))

    val previewButton = new Button("Preview")
    previewButton.setTooltip(new Tooltip("Preview HTML"))

    // BOTTOM HBOX
    configureBottomHBoxGeometry(bottomHbox)
    addWidgetsToBottomHbox(bottomHbox, convertButton, previewButton)

    // GRID PANE CONFIG
    configureGridPaneGeometry(this)
    addWidgetsToGridPane(this, scrollPane, bottomHbox)
    configureGridPaneColumnConstraints(this)
    configureGridPaneRowConstraints(this)

}









