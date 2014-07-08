package power.two;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.content.Context;
import android.graphics.Color;

import power.two.TexturePacker.Button;
import power.two.TexturePacker.Cell;
import power.two.TexturePacker.Hud;
import power.two.TexturePacker.Over;

public class ResourcesManager {
	
	private static final ResourcesManager INSTANCE = new ResourcesManager();
	
	public Engine engine;
	public Camera camera;
	public Main main;
	public Context contex;
	public VertexBufferObjectManager vbox;
	
	private TexturePackTextureRegionLibrary texturePackLibrary,texturePackLibrary1,texturePackLibrary2;
	private TexturePack texturePack;
	private TexturePack gameTexturePack;
	private TexturePack hudTexturePack;

	public ITextureRegion textureRegion_0;
	public ITextureRegion textureRegion_1;
	public ITextureRegion textureRegion_2;
	public ITextureRegion textureRegion_3;
	public ITextureRegion textureRegion_4;
	public ITextureRegion textureRegion_5;
	public ITextureRegion textureRegion_6;
	public ITextureRegion textureRegion_7;
	public ITextureRegion textureRegion_8;
	public ITextureRegion textureRegion_9;
	public ITextureRegion textureRegion_10;
	public ITextureRegion textureRegion_11;
	public ITextureRegion textureRegion_12;
	public ITextureRegion textureRegion_13;
	public ITextureRegion textureRegion_14;
	public ITextureRegion textureRegion_15;
	public ITextureRegion textureRegion_16;
	public ITextureRegion textureRegion_17;
	public ITextureRegion textureRegion_18;
	public ITextureRegion textureRegion_19;
	public ITextureRegion textureRegion_20;
	public ITextureRegion textureRegion_21;
	public ITextureRegion textureRegion_22;
	
	public Font font;

	private ResourcesManager() {}
	
	public static ResourcesManager getInstance(){
		return INSTANCE;
	}
	
	public static void getResources(Engine engine,Camera camera,Main main,VertexBufferObjectManager vbox,Context c){
		getInstance().engine = engine;
		getInstance().camera = camera;
		getInstance().main = main;
		getInstance().vbox = vbox;
		getInstance().contex = c; 
	}
	
	// ===========================================================
	// METHOD - LOAD & UNLOAD MENU
	// ===========================================================
	
	public void loadMenuResources(){
		try {
			texturePack = new TexturePackLoader(main.getTextureManager(), "images/").loadFromAsset( main.getAssets(), "button.xml");
			texturePack.loadTexture();
			texturePackLibrary = texturePack.getTexturePackTextureRegionLibrary();
		} catch (TexturePackParseException e) {
			Debug.e(e);
		}
		textureRegion_0 = texturePackLibrary.get(Button.KEEP_ID);
		textureRegion_1 = texturePackLibrary.get(Button.NEW_ID);
		textureRegion_2 = texturePackLibrary.get(Button.SOUND_ID);
		textureRegion_3 = texturePackLibrary.get(Button.SOUND_OFF_ID);
	}
	
	public void unloadMenuResources(){
		
		texturePack.unloadTexture();
		texturePack = null;
		textureRegion_0 = null;
		textureRegion_1 = null;
		textureRegion_2 = null;
		textureRegion_3 = null;
	}
	
	public void loadFont(){
		
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(main.getTextureManager(), 126, 126, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    font = FontFactory.createStrokeFromAsset(main.getFontManager(), mainFontTexture, main.getAssets(), "SansPosterBold.ttf", 18, true, Color.WHITE, 0, Color.WHITE);
	    font.load();
	}
	
	// ===========================================================
	// METHOD - LOAD & UNLOAD GAME
	// ===========================================================
	
	public void loadGameResources(){
		
		try {
			gameTexturePack = new TexturePackLoader(main.getTextureManager(), "images/").loadFromAsset( main.getAssets(), "cell.xml");
			gameTexturePack.loadTexture();
			texturePackLibrary1 = gameTexturePack.getTexturePackTextureRegionLibrary();
		} catch (TexturePackParseException e) {
			Debug.e(e);
		}
		textureRegion_4 = texturePackLibrary1.get(Cell._2_ID);
		textureRegion_5 = texturePackLibrary1.get(Cell._4_ID);
		textureRegion_6 = texturePackLibrary1.get(Cell._8_ID);
		textureRegion_7 = texturePackLibrary1.get(Cell._16_ID);
		textureRegion_8 = texturePackLibrary1.get(Cell._32_ID);
		textureRegion_9 = texturePackLibrary1.get(Cell._64_ID);
		textureRegion_10 = texturePackLibrary1.get(Cell._128_ID);
		textureRegion_11 = texturePackLibrary1.get(Cell._256_ID);
		textureRegion_12 = texturePackLibrary1.get(Cell._512_ID);
		textureRegion_13 = texturePackLibrary1.get(Cell._1024_ID);
		textureRegion_14 = texturePackLibrary1.get(Cell._2048_ID);
		textureRegion_15 = texturePackLibrary1.get(Cell._4096_ID);
		
	}
	
	public void loadHudResources(){
		try {
			hudTexturePack = new TexturePackLoader(main.getTextureManager(), "images/").loadFromAsset( main.getAssets(), "hud.xml");
			hudTexturePack.loadTexture();
			texturePackLibrary2 = hudTexturePack.getTexturePackTextureRegionLibrary();
		} catch (TexturePackParseException e) {
			Debug.e(e);
		}
		loadFont();
		textureRegion_16 = texturePackLibrary2.get(Hud._2048_ID);
		textureRegion_17 = texturePackLibrary2.get(Hud.SCORE_ID);
		textureRegion_18 = texturePackLibrary2.get(Hud.BEST_ID);
		textureRegion_19 = texturePackLibrary2.get(Hud.NEW_ID);
		textureRegion_20 = texturePackLibrary2.get(Hud.SCREEN_ID);
		textureRegion_21 = texturePackLibrary2.get(Hud.BACKGROUND_ID);
	}
	
	public void loadGameOverResources(){
		
		try {
			texturePack = new TexturePackLoader(main.getTextureManager(), "images/").loadFromAsset( main.getAssets(), "over.xml");
			texturePack.loadTexture();
			texturePackLibrary = texturePack.getTexturePackTextureRegionLibrary();
		} catch (TexturePackParseException e) {
			Debug.e(e);
		}
		loadFont();
		textureRegion_22 = texturePackLibrary.get(Over.GAMEOVER_ID);
	}
	public void unloadGameOverResources(){
		texturePack.unloadTexture();
		texturePack = null;
		textureRegion_22 = null;
	}
}