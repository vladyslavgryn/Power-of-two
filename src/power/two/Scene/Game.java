package power.two.Scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.align.HorizontalAlign;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import power.two.ResourcesManager;
import power.two.Game.GameField;
import power.two.Scene.SceneManager.SceneType;

public class Game extends BaseScene {
	
	public static Text score,best;
	public BaseScene game = this;
	private GestureDetector detector;
	private HUD gameHUD;
	
	GameField gameField = new GameField();
	Sprite[] sprite = new Sprite[5];
	Sprite background;
	
	@Override
	public void createScene() {
		
		setBackground(SceneManager.background);
		createHUD();
		createDetector();
		gameField.addRandom();
		gameField.addRandom();
	}
	
	@Override
	public void disposeScene() {
		for (int i = 0; i < 5; i++)
			gameHUD.detachChild(sprite[i]);
		gameHUD.detachChild(main.screenCapture);
		detachChild(background);
		detachChild(score);
		detachChild(best);
		camera.setHUD(null);
	}
	
	private void createHUD(){
		
		gameHUD = new HUD();
		gameField.getScore();
		
		score = new Text(235, 690, ResourcesManager.getInstance().font, "Score: 0123456789", new TextOptions(HorizontalAlign.CENTER), vbox);
		score.setAnchorCenter(0, 1);
		score.setText("0");
		
		best = new Text(350, 690, ResourcesManager.getInstance().font, "Score: 0123456789", new TextOptions(HorizontalAlign.CENTER), vbox);
		best.setAnchorCenter(0, 1);
		best.setText(""+gameField.best);
	    
	    sprite[0] = new Sprite(100, 660, ResourcesManager.getInstance().textureRegion_16, vbox);
	    sprite[1] = new Sprite(260, 690, ResourcesManager.getInstance().textureRegion_17, vbox);
	    sprite[2] = new Sprite(380, 690, ResourcesManager.getInstance().textureRegion_18, vbox);
	    sprite[3] = new Sprite(260, 623, ResourcesManager.getInstance().textureRegion_19, vbox){
	    	@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()) {
	    			gameField.newGame();
	    			best.setText(""+gameField.best);
	    			score.setText("0");
					sprite[3].setScale(1f);
					return true;
				}
				else if (pSceneTouchEvent.isActionDown()){
					sprite[3].setScale(1.12f);
					return true;
	    		}
				return false;
	    	}
	    };
	    sprite[4] = new Sprite(380, 623, ResourcesManager.getInstance().textureRegion_20, vbox){
	    	@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					main.screenCapture();
					sprite[4].setScale(1f);
					return true;
				}
				else if (pSceneTouchEvent.isActionDown()){
					sprite[4].setScale(1.12f);
					return true;
	    		}
				return false;
	    	}
	    };
	    
	    gameHUD.registerTouchArea(sprite[3]);
	    gameHUD.registerTouchArea(sprite[4]);
	    
	    for (int i = 0; i < 5; i++)
	    	gameHUD.attachChild(sprite[i]);
	    
	    gameHUD.attachChild(score);
	    gameHUD.attachChild(best);
		camera.setHUD(gameHUD);
		
		background = new Sprite(239.99f,296.0f, ResourcesManager.getInstance().textureRegion_21, vbox);
		attachChild(background);
		gameHUD.attachChild(main.screenCapture);
	}
	
	@Override
	public void keyPresed() {
		gameField.saveScore();
		main.onDestroy();
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.GAME_SCENE;
	}
	
	// ===========================================================
	// METHOD - CREATE DETECTOR & LISTNER
	// ===========================================================
	
	private void createDetector(){
		main.runOnUiThread(new Runnable() {
    		  public void run() {
    			  detector = new GestureDetector(main, new MyDetector());
    		  }
    		});
	}
	
	@Override
    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		this.clearEntityModifiers();
        detector.onTouchEvent(pSceneTouchEvent.getMotionEvent());
        return true;
    }
	
	// ===========================================================
	// PRIVATE CLASS - DETECTE SWIPE DIRECTION
	// ===========================================================
	
	private class MyDetector extends SimpleOnGestureListener {
    	
    	private static final int SWIPE_MIN_DISTANCE = 80;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                        float velocityY) {
                if (Math.abs(velocityX) > Math.abs(velocityY)) {
                        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
                        		onLeft();
                                return true;
                        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
                        		onRight();
                                return true;
                        }
                } else {
                        if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE) {
                        		onUp();
                                return true;
                        } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {
                        		onDownn();
                                return true;
                        }
                } 
                return false;
        }
	}
	
	// ===========================================================
	// METHOD - IMPLEMETNS WHAT TO DO ON SWIPE
	// ===========================================================
	
    public void onUp() {
    	gameField.onUp();
    	score.setText(""+gameField.score);
    }
    public void onDownn() {
    	gameField.onDown();
    	score.setText(""+gameField.score);
    }
    public void onLeft() {
    	gameField.onLeft();
    	score.setText(""+gameField.score);
    }
    public void onRight() {
    	gameField.onRight();
    	score.setText(""+gameField.score);
    }
}
