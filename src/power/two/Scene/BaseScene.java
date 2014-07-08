package power.two.Scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import power.two.Main;
import power.two.ResourcesManager;
import power.two.Scene.SceneManager.SceneType;

public abstract class BaseScene extends Scene {
	
	public Engine engine;
	public Camera camera;
	public Main main;
	public VertexBufferObjectManager vbox;
	
	public BaseScene(){
		
		this.engine = ResourcesManager.getInstance().engine;
		this.camera = ResourcesManager.getInstance().camera;
		this.main = ResourcesManager.getInstance().main;
		this.vbox = ResourcesManager.getInstance().vbox;
	}
	public abstract void createScene();
	public abstract void disposeScene();
	public abstract void keyPresed();
	public abstract SceneType getSceneType();
}
