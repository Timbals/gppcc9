package me.timbals.gppcc9;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import me.timbals.gppcc9.entity.systems.CameraFollowSystem;
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
		entityEngine.addSystem(new CameraFollowSystem());

		// assets
		assetManager = new AssetManager();

        Level.init();
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
