package power.two.Game;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import power.two.ResourcesManager;
import power.two.Scene.SceneManager;

public class CellGame {
	
	// METHOD - CREATE SPRITE
	public Sprite createSprite(float x, float y, ITextureRegion region, VertexBufferObjectManager vbox){
		Sprite sprite = new Sprite(x, y, region, vbox){
			
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		return sprite;
	}
	
	public void ckeckImage(Sprite[][] sprite,int[][] tab,int x,int i,int j){
		
		// 4
		if (x == 4){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_5, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 8
		else if (x == 8){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_6, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 16
		else if (x == 16){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_7, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 32
		else if (x == 32){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_8, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 64
		else if (x == 64){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_9, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 128
		else if (x == 128){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_10, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 256
		else if (x == 256){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_11, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 512
		else if (x == 512){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_12, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 1024
		else if (x == 1024){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_13, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 2048
		else if (x == 2048){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_14, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
		// 4096
		else if (x == 4096){
			sprite[i][j-1] = createSprite(GameField.x+110*(j-1),GameField.y-110*i,ResourcesManager.getInstance().textureRegion_15, SceneManager.game.vbox);
			SceneManager.game.attachChild(sprite[i][j-1]);
			sprite[i][j-1].registerEntityModifier(new ScaleModifier(0.2f, 1.2f, 1f));
		}
	}
}
