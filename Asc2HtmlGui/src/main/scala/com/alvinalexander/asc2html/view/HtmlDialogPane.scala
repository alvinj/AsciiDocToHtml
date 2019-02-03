package com.alvinalexander.asc2html.view

import javafx.event.EventHandler
import javafx.geometry.{HPos, Insets, VPos}
import javafx.scene.Node
import javafx.scene.control._
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.{ColumnConstraints, GridPane, Priority}
import com.sun.javafx.scene.control.skin.TextAreaSkin

class HtmlDialogPane extends GridPane {

    // widgets
    val htmlTextArea = new TextArea
    val scrollPane = new ScrollPane; scrollPane.setContent(htmlTextArea)

    // configure widgets
    htmlTextArea.setWrapText(true)
    GridPane.setVgrow(htmlTextArea, Priority.ALWAYS)

    // row(s)
    this.addRow(0, scrollPane)

    // columns
    val col1 = new ColumnConstraints;  col1.setPercentWidth(100)
    this.getColumnConstraints.addAll(col1)

    // things that affect size
    this.setPadding(new Insets(10))
    this.setHgap(10)
    this.setVgap(10)
    this.setMinWidth(600)
    this.setPrefWidth(600)
    htmlTextArea.setPrefRowCount(20)
    htmlTextArea.setPrefColumnCount(80)


    /**
      * force the textarea to treat the TAB key to mean "go to next field".
      * https://stackoverflow.com/questions/12860478/tab-key-navigation-in-javafx-textarea
      */
    htmlTextArea.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler[KeyEvent]() {
        override def handle(event: KeyEvent): Unit = {
            if (event.getCode == KeyCode.TAB) {
                val node = event.getSource.asInstanceOf[Node]
                val skin = node.asInstanceOf[TextArea].getSkin.asInstanceOf[TextAreaSkin]
                skin.getBehavior.traverseNext
                event.consume
            }
        }
    })



}




