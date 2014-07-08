package power.two.Scene;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import power.two.ResourcesManager;

public class SceneManager {
	
	private static SceneManager INSTANCE = new SceneManager();
	
	private BaseScene menu;
	public static BaseScene game;
	public static BaseScene over;
	private BaseScene currentScene;
	private SceneType currentSceneType = SceneType.MENU_SCENE;
	private Engine engine = ResourcesManager.getInstance().engine;
	
	public static Background background = new Background(1f,0.914f,0.831f);
	
	private SceneManager() {}
	
	public static SceneManager getInstance(){
		return INSTANCE;
	}
	
	public enum SceneType{
		MENU_SCENE,
		GAME_SCENE,
		OVER_SCENE
	}
	
	public void setScene(BaseScene scene){
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = currentScene.getSceneType();
	}
	public void setScene(SceneType sceneType){
		switch (sceneType) {
			case MENU_SCENE:
				setScene(menu);
				break;
			case GAME_SCENE:
				setScene(game);
				break;
			case OVER_SCENE:
				setScene(game);
				break;
		}
	}	
	
	public SceneType getCurrentSceneType(){
		return currentSceneType;
	}
	
	public BaseScene getCurrentScene(){
		return currentScene;
	}
	
	// ===========================================================
	// METHOD - MENU SCENE
	// ===========================================================
	
	public void setMenuScene(OnCreateSceneCallback p){
		
		ResourcesManager.getInstance().loadMenuResources();
		menu = new Menu();
		setScene(menu);
		currentScene.createScene();
		p.onCreateSceneFinished(currentScene);
	}
	
	public void disposeMenu(){
		ResourcesManager.getInstance().unloadMenuResources();
		menu.disposeScene();
		menu = null;
	}
	
	// ===========================================================
	// METHOD - GAME SCENE
	// ===========================================================
	
	public void setBackScene(){
		game = new Game();
		setScene(game);
		currentScene.createScene();
	}
	
	public void setGameScene(){
		disposeMenu();
		ResourcesManager.getInstance().loadGameResources();
		ResourcesManager.getInstance().loadHudResources();
		game = new Game();
		setScene(game);
		currentScene.createScene();
	}
	public void disposeGameScene(){
		game.disposeScene();
	}
	// ===========================================================
	// METHOD - GAMEOVER SCENE
	// ===========================================================
	
	public void setGameoverScene(){
		disposeGameScene();
		ResourcesManager.getInstance().loadGameOverResources();
		over = new Over();
		setScene(over);
		currentScene.createScene();
	}
	public void disposeGameoverScene(){
		ResourcesManager.getInstance().unloadGameOverResources();
		over.disposeScene();
		over = null;
	}
}
