package com.alvinalexander.asc2html.controller

import com.alvinalexander.asc2html.actions.ConvertButtonHandler
import javafx.scene.Scene
import javafx.scene.input._
import javafx.stage.Stage
import com.alvinalexander.asc2html.view.{HtmlDialogPane, GuiUtils, MainGridPane}

/**
  * This is the main “controller” (as in MVC) for the application.
  */
class MainController {

    val mainGridPane = new MainGridPane()

    // connect to these widgets so we can handle their events
    private val textArea         = mainGridPane.textArea
    private val convertAscButton = mainGridPane.convertAscButton

    private val convertButtonHandler = new ConvertButtonHandler(this)
    convertAscButton.setOnAction(convertButtonHandler)

    // get a reference to the main scene so we can add listeners to it
    def addKeystrokeHandlers(scene: Scene, stage: Stage): Unit = {
        //configureCommandFHandler(scene)
        //configureCommandNHandler(scene)
        configureCommandMHandler(scene, stage)
    }

    // manually implement minimize
    private def configureCommandMHandler(scene: Scene, mainStage: Stage): Unit = {
        val keyComboCommandM: KeyCombination = new KeyCodeCombination(
            KeyCode.M,
            KeyCombination.META_DOWN
        )
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
            if (keyComboCommandM.`match`(event)) {
                mainStage.setIconified(true)
            }
        })
    }

    //    private def configureCommandNHandler(scene: Scene): Unit = {
//        val keyComboCommandN: KeyCombination = new KeyCodeCombination(
//            KeyCode.N,
//            KeyCombination.META_DOWN
//        )
//        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
//            if (keyComboCommandN.`match`(event)) {
//                addNoteEventHandler.handle(null)
//            }
//        })
//    }

//    private def configureCommandFHandler(scene: Scene): Unit = {
//        val keyComboCommandF: KeyCombination = new KeyCodeCombination(
//            KeyCode.F,
//            KeyCombination.META_DOWN
//        )
//        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
//            if (keyComboCommandF.`match`(event)) {
//                //Platform.runLater(() => searchField.requestFocus)
//                searchField.requestFocus()
//            }
//        })
//    }

}

