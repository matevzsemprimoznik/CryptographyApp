package main;

import controllers.MainPanelController;
import models.MainPanelModel;
import views.MainPanelView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(1000, 600));
        frame.setLocation(200, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanelView mainPanel = new MainPanelView();
        frame.add(mainPanel);

        MainPanelModel mainPanelModel = new MainPanelModel();
        MainPanelController mainPanelController = new MainPanelController(mainPanel, mainPanelModel);

        //frame.pack();
    }
}
