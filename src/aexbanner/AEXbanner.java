/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Sam
 */
public class AEXbanner extends Application {

//    public Label label7;
    private CopyOnWriteArrayList<Label> runningLabelList;
    private CopyOnWriteArrayList<Fonds> fondsList;

    private int nextFondIndex = 0;

//    private boolean canAddNewLabel = false;
    private int amountOfElements;

    private Scene scene;

    public AEXbanner() {
        //fondsList = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BannerController bC = new BannerController(this);

//        label7 = new Label();
        runningLabelList = new CopyOnWriteArrayList<>();
        fondsList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < amountOfElements; i++) {
            runningLabelList.add(new Label());
        }

        StackPane root = new StackPane();

        for (Label label : runningLabelList) {
            label.setText("labellabellabel");
            label.setFont(Font.font("Arial Black", 24.0));
            root.getChildren().add(label);
        }

//        label7.setText("label7");
//        label7.setFont(Font.font("Arial Black", 24.0));
//        root.getChildren().add(label7);
        scene = new Scene(root, 700, 80);

        primaryStage.setScene(scene);
        primaryStage.show();

        for (int k = 0; k < runningLabelList.size(); k++) {
            Label label = runningLabelList.get(k);
            label.setManaged(false);
            double loc = ((primaryStage.getWidth() + label.getWidth()) / amountOfElements) * (k + 1);
            label.relocate(loc, 20);
        }
//        label7.setManaged(false);
//        label7.relocate(300, 20);

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (!runningLabelList.isEmpty()) {
                    for (Label label : runningLabelList) {
                        label.relocate(label.getLayoutX() - 2, 20);

                        if (label.getLayoutX() + label.getWidth() < 0) {
                            //runningLabelList.remove(label);
                            //root.getChildren().remove(label);
                            //canAddNewLabel = true;
                            label.relocate(primaryStage.getWidth(), 20);
                            setText(label, fondsList.get(nextFondIndex).toString());
                            nextFondIndex++;
                            if (nextFondIndex >= fondsList.size()) {
                                nextFondIndex = 0;
                            }
                        }
                    }
                }
//                if (fondsList.size() != 0) {
//                    if (canAddNewLabel == true) {
//
//                        System.out.println(canAddNewLabel);
//                        //System.out.println("WLL size: " + waitingLabelList.size());
//                        //Label label = waitingLabelList.get(nextLabelIndex);
//                       // label7 = new Label();
//                        setText(label7, fondsList.get(nextFondIndex).toString());
//                        //label7.setText("sdfsd");
//                        label7.setFont(Font.font("Arial Black", 24.0));
//
//                        //root.getChildren().add(label7);
//
//                        //label7.setManaged(false);
//                        //waitingLabelList.remove(label);
//                        runningLabelList.add(label7);
//                        //Fonds fonds = (Fonds) fondsMap.get("AMD");
//                        label7.relocate(300, 20);
//                        //System.out.println(label7.getLayoutX());
//                        //label.setText(fonds.getName() + ": " + fonds.getKoers());
//                        canAddNewLabel = false;
//                        //scene = new Scene(root, 700, 80);
//                        //primaryStage.setScene(scene);
//                        primaryStage.show();
//                    }
//                }

//                if (label7 != null) {
//                    System.out.println(label7.getLayoutX());
//                }
            }

        }.start();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    stop();
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(AEXbanner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void addFond(Fonds fonds) {
        System.out.println(fonds.toString());
        boolean fondsFound = false;
        if (!fondsList.isEmpty()) {
            for (Fonds fonds2 : fondsList) {
                if (fonds2.getName().equals(fonds.getName())) {
                    fonds2.setKoers(fonds.getKoers());
                    fondsFound = true;
                }
            }
        }
        if (fondsFound == false) {
            fondsList.add(fonds);
        }
    }

    public void setText(Label label, String value) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //System.out.println("Value: " + value);
                label.setText(value);
            }
        });
    }

    public void setAmountOfElements(int aOE) {
        amountOfElements = aOE;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(AEXbanner.class, args);      
    }

}
