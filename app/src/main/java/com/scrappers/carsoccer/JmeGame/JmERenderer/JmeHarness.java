package com.scrappers.carsoccer.JmeGame.JmERenderer;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.jme3.app.AndroidHarness;
import com.scrappers.carsoccer.JmeGame.GameDataNodes;
import com.scrappers.carsoccer.JmeGame.SceneRenderer.BallRenderer;
import com.scrappers.carsoccer.JmeGame.SceneRenderer.SceneRenderer;
import com.scrappers.carsoccer.JmeGame.SceneSelectorStage.SceneSelectorStage;
import com.scrappers.carsoccer.JmeGame.VehicleSelectorStage.VehicleSelectorStage;
import com.scrappers.carsoccer.R;

import java.util.logging.Level;
import java.util.logging.LogManager;

public class JmeHarness extends AndroidHarness {

    public static AssetManager assetManager;
    public static JmeHarness jmeHarness=null;
    public JmeHarness() {

        // Set the desired EGL configuration
        eglBitsPerPixel = 24;
        eglAlphaBits = 0;
        eglDepthBits = 16;
        eglSamples = 0;
        eglStencilBits = 0;

        // Set the maximum framerate
        // (default = -1 for unlimited)
        frameRate = -1;

        // Set main project class (fully qualified path)
        // the class that extends SimpleApplication
        appClass = "com.scrappers.carsoccer.JmeGame.JmERenderer.JmeGame";


//        // Set input configuration settings
//        joystickEventsEnabled = true;
//        keyEventsEnabled = true;
//        mouseEventsEnabled = true;

        // Set application exit settings
        finishOnAppStop = false;
        handleExitHook = false;
        exitDialogTitle = "Do you want to exit?";
        exitDialogMessage = "Use your home key to bring this app into the background or exit to terminate it.";

        // Set splash screen resource id, if used
        // (default = 0, no splash screen)
        // For example, if the image file name is "splash"...
        //     splashPicID = R.drawable.splash;
        splashPicID = R.drawable.fireengine_circle_buttons;

        // splashPicID = R.drawable.jme_white;

        // Set the default logging level (default=Level.INFO, Level.ALL=All Debug Info)
        LogManager.getLogManager().getLogger("").setLevel(Level.INFO);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assetManager=getAssets();
        jmeHarness=this;


    }

    @Override
    public void onBackPressed() {
        /*remove app states */
        JmeGame.gameContext.getStateManager().detach(JmeGame.gamePhysics);
        JmeGame.gameContext.getStateManager().detach(JmeGame.gameContext.getStateManager().getState(SceneRenderer.class));
        JmeGame.gameContext.getStateManager().detach(JmeGame.gameContext.getStateManager().getState(BallRenderer.class));
        JmeGame.gameContext.getStateManager().detach(JmeGame.gameContext.getStateManager().getState(VehicleSelectorStage.class));
        JmeGame.gameContext.getStateManager().detach(JmeGame.gameContext.getStateManager().getState(SceneSelectorStage.class));

        /*stop the game */

        GameDataNodes gameDataNodes =new GameDataNodes();
        gameDataNodes.checkForFinish();
        gameDataNodes.setMeOut();
        finish();
    }
}