package power.two.Scene;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import power.two.ResourcesManager;
import power.two.Scene.SceneManager.SceneType;


public class Menu extends BaseScene implements IOnMenuItemClickListener{
	
	private MenuScene menu;
	
	private final int MENU_PLAY = 0;
	private final int MENU_KEEP = 1;
	private final int MENU_SOUND = 2;
	
	@Override
	public void createScene() {
		setBackground(SceneManager.background);
		createMenu();
	}
	@Override
	
	public void disposeScene() {
		main.runOnUpdateThread(new Runnable() {
		    @Override
		    public void run() {
		    	detachChild(menu);
		    	detachSelf();
		    	dispose();
		    }});
	}
	
	@Override
	public void keyPresed() {
		main.onDestroy();
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.MENU_SCENE;
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene scene, IMenuItem item, float x, float y) {
		
		switch (item.getID()) {
			case MENU_PLAY:
				SceneManager.getInstance().setGameScene();
				return true;
			case MENU_KEEP:
				return true;
			case MENU_SOUND:
				return true;
			}
		return false;
	}

	
	public void createMenu(){
		
		menu = new MenuScene(camera);
		menu.setPosition(0, 0);
		
		final IMenuItem play = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, ResourcesManager.getInstance().textureRegion_1, vbox), 1.2f, 1);
		final IMenuItem keep = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_KEEP, ResourcesManager.getInstance().textureRegion_0, vbox), 1.2f, 1);
		final IMenuItem sound_on = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SOUND, ResourcesManager.getInstance().textureRegion_2, vbox), 1.2f, 1);
		
		menu.addMenuItem(play);
		menu.addMenuItem(keep);
		menu.addMenuItem(sound_on);
		menu.buildAnimations();
		menu.setBackgroundEnabled(false);
		
		play.setPosition(play.getX(), play.getY());
		keep.setPosition(keep.getX(), keep.getY()-8);
		sound_on.setPosition(sound_on.getX(), sound_on.getY()-16);
		
		menu.setOnMenuItemClickListener(this);
		setChildScene(menu);
	}
}
