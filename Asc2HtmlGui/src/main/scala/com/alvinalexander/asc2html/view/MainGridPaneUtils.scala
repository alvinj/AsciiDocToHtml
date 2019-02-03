package com.alvinalexander.asc2html.view

import javafx.geometry.{Insets, Pos}
import javafx.scene.control._
import javafx.scene.layout.{ColumnConstraints, HBox, Priority, RowConstraints}


/**
  * This object provides “helper” functions for the MainGridPane class.
  * I put these methods in a separate object to make more obvious the fields
  * that each method was working with.
  */
object MainGridPaneUtils {

    def configureGridPaneGeometry(gridPane: MainGridPane): Unit = {
        gridPane.setPadding(new Insets(10))
        gridPane.setHgap(10)
        gridPane.setVgap(10)
        //this.setPrefWidth(1000)
    }

    def addWidgetsToGridPane(
        gridPane: MainGridPane,
        scrollPane: ScrollPane,
        bottomHbox: HBox
    ): Unit = {
        // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
        //gridPane.add(topHbox,    0, 0)
        gridPane.add(scrollPane,  0, 1)
        gridPane.add(bottomHbox, 0, 2)
    }

    def configureGridPaneColumnConstraints(gridPane: MainGridPane): Unit = {
        val col1 = new ColumnConstraints
        col1.setPercentWidth(100)
        gridPane.getColumnConstraints.addAll(col1)
    }

    def configureGridPaneRowConstraints(gridPane: MainGridPane): Unit = {
        // row constraints (currently two rows)
        val rowAlwaysGrows = new RowConstraints; rowAlwaysGrows.setVgrow(Priority.ALWAYS)
        val rowNeverGrows = new RowConstraints;  rowNeverGrows.setVgrow(Priority.NEVER)
        gridPane.getRowConstraints.addAll(rowNeverGrows, rowAlwaysGrows)
    }

//    def addWidgetsToTopHbox(
//        topHbox: HBox,
//        searchField: TextField,
//        tagSearchButton: Button,
//        textSearchButton: Button
//    ): Unit = {
//        topHbox.getChildren.addAll(searchField, textSearchButton, tagSearchButton)
//    }

//    def configureTopHBoxGeometry(
//        topHbox: HBox,
//        searchField: TextField
//    ): Unit = {
//        searchField.setPrefWidth(400)                //keep small, for when user resizes window smaller
//        HBox.setHgrow(searchField, Priority.ALWAYS)  //always grow in hbox
//        HBox.setHgrow(topHbox, Priority.ALWAYS)      //hbox always grows/fills width
//    }

    def configureBottomHBoxGeometry(
        bottomHbox: HBox
    ): Unit = {
        bottomHbox.setAlignment(Pos.BASELINE_CENTER)
        HBox.setHgrow(bottomHbox, Priority.ALWAYS)      //hbox always grows/fills width
    }

    def addWidgetsToBottomHbox(
        bottomHbox: HBox,
        convertAsciidocToHtmlButton: Button
    ): Unit = {
        bottomHbox.getChildren.addAll(convertAsciidocToHtmlButton)
    }


}




