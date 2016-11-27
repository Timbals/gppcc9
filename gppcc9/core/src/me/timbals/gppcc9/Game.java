package me.timbals.gppcc9;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import me.timbals.gppcc9.entity.components.AnimationComponent;
import me.timbals.gppcc9.entity.components.PositionComponent;
import me.timbals.gppcc9.entity.components.SizeComponent;
import me.timbals.gppcc9.entity.components.VelocityComponent;
import me.timbals.gppcc9.entity.systems.MovementSystem;
import me.timbals.gppcc9.entity.systems.RenderSystem;

public class Game extends ApplicationAdapter {

	public static final String NAME = "gppcc9";

	public static final int WIDTH = 1280; // virtual resolution for pixel coordinates
	public static final int HEIGHT = 720;
	public static int SCREEN_WIDTH = WIDTH;
	public static int SCREEN_HEIGHT = HEIGHT;

	public static final Random RANDOM = new Random();

	public static final float TARGET_FPS = 60;

	public PooledEngine entityEngine;

	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;

	public AssetManager assetManager;

	private Game() {
	}

	@Override
	public void create () {
		// graphics
		Gdx.gl.glClearColor(1, 1, 1, 0);

		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

		// entity
		entityEngine = new PooledEngine();

        entityEngine.addSystem(new MovementSystem());
        entityEngine.addSystem(new RenderSystem());

		// assets
		assetManager = new AssetManager();

        // create player
        Entity player = entityEngine.createEntity();
        player.add(entityEngine.createComponent(PositionComponent.class));
        player.add(entityEngine.createComponent(VelocityComponent.class));

        AnimationComponent animationComponent = entityEngine.createComponent(AnimationComponent.class);

        assetManager.load("player_walk.png", Texture.class);
        assetManager.finishLoadingAsset("player_walk.png");
        Texture textureAnimation = assetManager.get("player_walk.png");
        TextureRegion[][] region2dArray = TextureRegion.split(textureAnimation, 32, 32);
        TextureRegion[] regionArray = new TextureRegion[region2dArray.length + region2dArray[0].length];

        int index = 0;
        for(int i = 0; i < region2dArray.length; i++) {
            for(int j = 0; j < region2dArray[0].length; j++) {
                regionArray[index++] = region2dArray[i][j];
            }
        }

        Animation animation = new Animation(0.1f, regionArray);
        animation.setPlayMode(Animation.PlayMode.LOOP);

        animationComponent.animation = animation;
        player.add(animationComponent);

        SizeComponent sizeComponent = entityEngine.createComponent(SizeComponent.class);
        sizeComponent.width = 256;
        sizeComponent.height = 256;
        player.add(sizeComponent);

        entityEngine.addEntity(player);

	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime() / (1f / TARGET_FPS); // this will make delta 1 if target fps is exactly reached

        assetManager.update();

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        super.render();

        batch.end();

        entityEngine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		camera.position.set(Game.WIDTH / 2, Game.HEIGHT / 2, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
	}

	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

    private static Game instance;

    public static synchronized Game getInstance() {
        if(instance == null)
            instance = new Game();
        return instance;
    }

}
