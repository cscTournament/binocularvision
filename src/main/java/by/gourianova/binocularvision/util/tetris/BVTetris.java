package by.gourianova.binocularvision.util.tetris;

import by.gourianova.binocularvision.util.tetris.ui.Window;

import java.awt.*;

public class BVTetris {
    public void run(){

        Window window=new Window();
        //TODO: redo fullscreen mode
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(window);
        window.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
     //   window.setExtendedState(Window.MAXIMIZED_BOTH);
        window.run();
        javax.swing.SwingUtilities.invokeLater(window);
    }
}
