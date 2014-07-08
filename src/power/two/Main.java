package power.two;

import java.io.File;
import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.CropResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.ScreenCapture;
import org.andengine.entity.util.ScreenCapture.IScreenCaptureCallback;
import org.andengine.ui.activity.BaseGameActivity;

import android.os.Environment;
import android.view.KeyEvent;
import android.widget.Toast;
import power.two.Scene.SceneManager;

public class Main extends BaseGameActivity{
	
	public static int CAMERA_WIDTH = 480;
	public static int CAMERA_HEIGHT = 800;
	protected Camera _camera;
	public final ScreenCapture screenCapture = new ScreenCapture();
    
	CropResolutionPolicy crp;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		_camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		crp = new CropResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions options = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, crp, _camera);
		options.setWakeLockOptions(WakeLockOptions.SCREEN_DIM);
		
		return options;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback p) throws IOException {
		ResourcesManager.getResources(getEngine(), _camera, this, getVertexBufferObjectManager(),this);
		p.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback p) throws IOException {
		SceneManager.getInstance().setMenuScene(p);
	}

	@Override
	public void onPopulateScene(Scene pScene,OnPopulateSceneCallback p) throws IOException {
		p.onPopulateSceneFinished();
	}
	
	public void onDestroy(){
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK){
				SceneManager.getInstance().getCurrentScene().keyPresed();
		}
		return super.onKeyDown(keyCode, event);
	}
	
    
    // ===========================================================
 	// SCREEN CAPTURE
 	// ===========================================================
	
	public void screenCapture(){
        
    	File direct = new File(Environment.getExternalStorageDirectory()+ "/POWER");

        if (!direct.exists())
            direct.mkdir();
        	
        final int viewWidth = Main.this.mRenderSurfaceView.getWidth();
        final int viewHeight = Main.this.mRenderSurfaceView.getHeight();
        
    	screenCapture.capture(viewWidth,viewHeight,direct.getAbsolutePath() + "/power"+ System.currentTimeMillis() + ".png", new IScreenCaptureCallback() {
            @Override
            public void onScreenCaptured(final String pFilePath) {
                    Main.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    Toast.makeText(ResourcesManager.getInstance().contex, "Screenshot taken!", Toast.LENGTH_SHORT).show();
                            }
                    });
            }
            @Override
            public void onScreenCaptureFailed(final String pFilePath, final Exception pException) {
            		Main.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    Toast.makeText(ResourcesManager.getInstance().contex, "FAILED capturing Screenshot !", Toast.LENGTH_SHORT).show();
                            }
                    });
            }
    	});
	}
}
