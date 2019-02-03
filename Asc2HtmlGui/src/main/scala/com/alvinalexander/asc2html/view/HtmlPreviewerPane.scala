package com.alvinalexander.asc2html.view

import javafx.geometry.Insets
import javafx.scene.layout.{ColumnConstraints, GridPane, Priority}
import javafx.scene.web.WebView

class HtmlPreviewerPane extends GridPane {

    val webView = new WebView
    GridPane.setVgrow(webView, Priority.ALWAYS)

    // row(s)
    this.addRow(0, webView)

    // columns
    val col1 = new ColumnConstraints;  col1.setPercentWidth(100)
    this.getColumnConstraints.addAll(col1)

    // things that affect size
    this.setPadding(new Insets(10))
    this.setHgap(10)
    this.setVgap(10)
    this.setMinWidth(600)
    this.setPrefWidth(600)

}




