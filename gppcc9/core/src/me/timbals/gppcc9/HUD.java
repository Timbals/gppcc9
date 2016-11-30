package me.timbals.gppcc9;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tim on 30.11.2016.
 */

public class HUD {

    private static final int TILE_NUMBERS_WIDTH = 3;
    private static final int TILE_NUMBERS_HEIGHT = 5;
    private static final int SCORE_WIDTH = 22;
    private static final int SCORE_HEIGHT = 5;
    private static final float SCALE = 10;

    private static TextureRegion[] numbers; // mapping: numbers[2] corresponds to the texture of 2
    private static Texture score;

    public static void init() {
        // load assets
        AssetManager assetManager = Game.getInstance().assetManager;

        assetManager.load("numbers.png", Texture.class);
        assetManager.load("score.png", Texture.class);

        assetManager.finishLoadingAsset("numbers.png");
        numbers = Utils.splitTextureAtlas(assetManager.get("numbers.png", Texture.class), TILE_NUMBERS_WIDTH, TILE_NUMBERS_HEIGHT);

        assetManager.finishLoadingAsset("score.png");
        score = assetManager.get("score.png", Texture.class);
    }

    public static void update(float delta) {
        SpriteBatch batch = Game.getInstance().batch;

        batch.begin();

        batch.draw(score, 0, Game.HEIGHT - SCORE_HEIGHT * SCALE, SCORE_WIDTH * SCALE, SCORE_HEIGHT * SCALE);
        batch.draw(numbers[0], SCORE_WIDTH * SCALE, Game.HEIGHT - TILE_NUMBERS_HEIGHT * SCALE, TILE_NUMBERS_WIDTH * SCALE, TILE_NUMBERS_HEIGHT * SCALE);

        batch.end();
    }

}
