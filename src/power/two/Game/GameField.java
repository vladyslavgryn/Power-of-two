package power.two.Game;

import java.util.Random;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import power.two.ResourcesManager;
import power.two.Scene.Game;
import power.two.Scene.SceneManager;

public class GameField {
	
	Random r = new Random();
	CellGame cell = new CellGame();
	Sprite[][] sprite = new Sprite[4][4];
	int[][] tab = new int[4][4];
	static int x = 75, y = 460;
	public int score = 0,best = 0;
	public static final String MY_PREFERENCES = "SCORE";
	private SharedPreferences prefers = ResourcesManager.getInstance().main.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);;
	private Editor edit;
	int power,index = 0;
	
	// ===========================================================
	// ADD RANDOM BLOCKS TO ARRAY
	// ===========================================================	
	
	public void addRandom(){
		
		boolean location = false;
		if (!isFull()){
			while (!location){
				int a = r.nextInt(4);
				int b = r.nextInt(4);
				
				if (tab[a][b] == 0){
					tab[a][b] = 2;
					location = true;
					sprite[a][b] = cell.createSprite(x+110*b,y-110*a, ResourcesManager.getInstance().textureRegion_4, SceneManager.game.vbox);
					SceneManager.game.attachChild(sprite[a][b]);
					sprite[a][b].registerEntityModifier(new ScaleModifier(0.2f, 0.7f, 1f));
				}
			}
		}
		else{
			if (!canMove()){
				SceneManager.getInstance().setGameoverScene();
				newGame();
				Game.best.setText(""+best);
				score = 0;
			}
		}
	}
	
	// ===========================================================
	// CHECK IF ARRAY IS FULL
	// IF IS FULL - START NEW GAME
	// ===========================================================	
	
	public boolean isFull(){
		
		for (int i = 0; i < tab.length; i++){
			for (int j = 0; j < tab.length; j++){
				if (tab[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	private void timer(){
		
		TimerHandler my_timer = new TimerHandler(0.2f, false, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				addRandom();
			}
		});
		SceneManager.game.registerUpdateHandler(my_timer);
	}
	
	// ===========================================================
	// START NEW GAME
	// ===========================================================	
	
	public void newGame(){
		tab = new int[4][4];
		disposeAllSprite();
		getScore();
		if (score > best){
			best = score;
			saveScore();
		}
		score = 0;
		addRandom();
		addRandom();
	}
	
	// ===========================================================
	// SAVE & GET SCORE
	// ===========================================================	
	
	public void getScore(){
		best = prefers.getInt("Score", 0);
	}
	
	public void saveScore(){
		edit = prefers.edit();
		edit.putInt("Score", best);
		edit.commit();
	}
	
	public boolean canMove() {
		boolean can = false;
		
		for (int i = tab.length-1; i >= 0 ; i--) 
        	for (int j = tab.length-1; j >= 0; j--) {
        		if (i-1 >= 0)
        			can |= tab[i][j] == tab[i-1][j];
        		if (i+1 <= tab.length-1)
        			can |= tab[i][j] == tab[i+1][j];
        		if (j-1 >= 0)
        			can |= tab[i][j] == tab[i][j-1];
        		if (j+1 <= tab.length-1)
        			can |= tab[i][j] == tab[i][j+1];
        	}
		return can;
	}
	
	// ===========================================================
	// DELETE ALL SPRITES FROM SCENE
	// ===========================================================
	
	public void disposeAllSprite() {
		
		 for (int i = 0; i < tab.length; i++){
			for (int j = 0; j < tab.length; j++){
				if (sprite[i][j] != null)
					sprite[i][j].detachSelf();
			}
		}
	}
	
	public void disposeScene(int i,int j) {
		SceneManager.game.detachChild(sprite[i][j]);
	}
	
	// ===========================================================
	// CHECK COLLISION
	// ===========================================================
	
	private boolean isBusy(int i,int j){
		if (tab[i][j] !=  0) return true;
		return false;
	}
	
	private boolean isSame(int a,int b, int c,int d){
		if (tab[a][b] == tab[c][d]) return true;
		return false;
	}
	
	// ===========================================================
	// METHOD - MOVE BLOCKS
	// ===========================================================
	
	public void onLeft(){
		
		for (int i = 0; i < tab.length; i++){
			for (int j = 1; j < tab.length; j++){
				int j0 = j-1;
				if (isBusy(i, j0)){
					if(isSame(i, j, i, j0)){
						tab[i][j0] = tab[i][j]*2;
						tab[i][j] = 0;
						score += tab[i][j0];
						disposeScene(i,j);
						disposeScene(i,j0);
						power = tab[i][j0];
						cell.ckeckImage(sprite, tab, power, i, j);
						
						if (j0 != 0){
							if (!isBusy(i, j0-1)){
								tab[i][j0-1] = tab[i][j0];
								tab[i][j0] = 0;
								sprite[i][j0].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j0].getX(),sprite[i][j0].getY(),sprite[i][j0].getX()-110,sprite[i][j0].getY()));
								sprite[i][j0-1] = sprite[i][j0];
								sprite[i][j0] = null;
							}
						}
					}
				}
				else{
					if (isBusy(i, j)){
						tab[i][j0] = tab[i][j];
						tab[i][j] = 0;
						sprite[i][j].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j].getX(),sprite[i][j].getY(),sprite[i][j].getX()-110,sprite[i][j].getY()));
						sprite[i][j0] = sprite[i][j];
						sprite[i][j] = null;
					}
				}
			}	
		}
		if (!isFull()){
			if (canMove())
				timer();
		}
	}
	public void onRight(){
		
		for (int i = 3; i >= 0; i--){
			for (int j = 2; j >= 0; j--){
				int j1 = j+1;
				if (isBusy(i, j1)){
					if(isSame(i, j, i, j1)){
						tab[i][j1] = tab[i][j]*2;
						score += tab[i][j1];
						tab[i][j] = 0;
						disposeScene(i,j);
						disposeScene(i,j1);
						power = tab[i][j1];
						cell.ckeckImage(sprite, tab, power, i, j+2);
						
						if (j1 != 3){
							if (!isBusy(i, j1+1)){
								tab[i][j1+1] = tab[i][j1];
								tab[i][j1] = 0;
								sprite[i][j1].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j1].getX(),sprite[i][j1].getY(),sprite[i][j1].getX()+110,sprite[i][j1].getY()));
								sprite[i][j1+1] = sprite[i][j1];
								sprite[i][j1] = null;
							}
						}
					}
				}
				else{
					if (isBusy(i, j)){
						tab[i][j1] = tab[i][j];
						tab[i][j] = 0;
						sprite[i][j].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j].getX(),sprite[i][j].getY(),sprite[i][j].getX()+110,sprite[i][j].getY()));
						sprite[i][j1] = sprite[i][j];
						sprite[i][j] = null;
					}
				}
			}
		}
		if (!isFull()){
			if (canMove())
				timer();
		}
	}
	public void onDown(){
		
		for (int j = 3; j >= 0; j--){
			for (int i = 2; i >= 0; i--){
				int i1 = i+1;
				if (isBusy(i1, j)){
					if(isSame(i, j, i1, j)){
						tab[i1][j] = tab[i][j]*2;
						score += tab[i1][j];
						tab[i][j] = 0;
						disposeScene(i,j);
						disposeScene(i1,j);
						power = tab[i1][j];
						cell.ckeckImage(sprite, tab, power, i+1, j+1);
						
						if (i1 != 3){
							if (!isBusy(i1+1,j)){
								tab[i1+1][j] = tab[i1][j];
								tab[i1][j] = 0;
								sprite[i1][j].registerEntityModifier(new MoveModifier(0.1f,sprite[i1][j].getX(),sprite[i1][j].getY(),sprite[i1][j].getX(),sprite[i1][j].getY()-110));
								sprite[i1+1][j] = sprite[i1][j];
								sprite[i1][j] = null;
							}
						}
					}
				}
				else{
					if (isBusy(i, j)){
						tab[i1][j] = tab[i][j];
						tab[i][j] = 0;
						sprite[i][j].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j].getX(),sprite[i][j].getY(),sprite[i][j].getX(),sprite[i][j].getY()-110));
						sprite[i1][j] = sprite[i][j];
						sprite[i][j] = null;
					}
				}
			}
		}
		if (!isFull()){
			if (canMove())
				timer();
		}
	}
	public void onUp(){
		
		for (int j = 0; j < tab.length; j++){
			for (int i = 1; i < tab.length; i++){
				int i0 = i - 1;
				if (isBusy(i0, j)){
					if(isSame(i, j, i0, j)){
						tab[i0][j] = tab[i][j]*2;
						tab[i][j] = 0;
						score += tab[i0][j];
						sprite[i][j].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j].getX(),sprite[i][j].getY(),sprite[i][j].getX(),sprite[i][j].getY()+110));
						disposeScene(i,j);
						disposeScene(i0,j);
						power = tab[i0][j];
						cell.ckeckImage(sprite, tab, power, i0, j+1);
					}
				}
				else{
					if (isBusy(i, j)){
						tab[i0][j] = tab[i][j];
						tab[i][j] = 0;
						sprite[i][j].registerEntityModifier(new MoveModifier(0.1f,sprite[i][j].getX(),sprite[i][j].getY(),sprite[i][j].getX(),sprite[i][j].getY()+110));
						sprite[i0][j] = sprite[i][j];
						sprite[i][j] = null;
					}
				}
			}
		}
		if (!isFull()){
			if (canMove())
				timer();
		}
	}
}
