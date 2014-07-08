package power.two.Scene;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import power.two.ResourcesManager;
import power.two.Scene.SceneManager.SceneType;

public class Over extends BaseScene{
	
	Sprite over;
	
	@Override
	public void createScene() {
		
		setBackground(SceneManager.background);
		over = new Sprite(235, 400, ResourcesManager.getInstance().textureRegion_22, vbox){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					SceneManager.getInstance().setBackScene();
					SceneManager.getInstance().disposeGameoverScene();
					return true;
				}
				return false;
	    	}
	    };
	    registerTouchArea(over);
		attachChild(over);
	}

	@Override
	public void disposeScene() {
		over.detachSelf();
		over = null;
	}

	@Override
	public void keyPresed() {
		main.onDestroy();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.OVER_SCENE;
	}

}
