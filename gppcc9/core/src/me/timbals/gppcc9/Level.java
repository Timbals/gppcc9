package me.timbals.gppcc9;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import me.timbals.gppcc9.entity.components.AnimationComponent;
import me.timbals.gppcc9.entity.components.CameraFollowComponent;
import me.timbals.gppcc9.entity.components.CoverComponent;
import me.timbals.gppcc9.entity.components.DisguiseComponent;
import me.timbals.gppcc9.entity.components.InputComponent;
import me.timbals.gppcc9.entity.components.PositionComponent;
import me.timbals.gppcc9.entity.components.SizeComponent;
import me.timbals.gppcc9.entity.components.TextureComponent;
import me.timbals.gppcc9.entity.components.VelocityComponent;

/**
 * Created by Tim on 28.11.2016.
 */

public class Level {

    public static void init() {
        PooledEngine entityEngine = Game.getInstance().entityEngine;
        AssetManager assetManager = Game.getInstance().assetManager;

        // create player
        Entity player = entityEngine.createEntity();
        player.add(new PositionComponent());

        VelocityComponent velocityComponentPlayer = new VelocityComponent();
        velocityComponentPlayer.x = 4;
        player.add(velocityComponentPlayer);

        player.add(new InputComponent());

        player.add(new DisguiseComponent());

        CameraFollowComponent cameraFollowComponentPlayer = new CameraFollowComponent();
        cameraFollowComponentPlayer.followY = false;
        cameraFollowComponentPlayer.offX = Game.WIDTH / 2;
        player.add(cameraFollowComponentPlayer);

        AnimationComponent animationComponentPlayer = new AnimationComponent();

        assetManager.load("player_walk.png", Texture.class);
        assetManager.finishLoadingAsset("player_walk.png");
        Texture textureAnimation = assetManager.get("player_walk.png");
        Animation animation = new Animation(0.1f, Utils.splitTextureAtlas(textureAnimation, 32, 32));
        animation.setPlayMode(Animation.PlayMode.LOOP);

        animationComponentPlayer.animation = animation;
        player.add(animationComponentPlayer);

        SizeComponent sizeComponentPlayer = new SizeComponent();
        sizeComponentPlayer.width = 256;
        sizeComponentPlayer.height = 256;
        player.add(sizeComponentPlayer);

        entityEngine.addEntity(player);

        // create guard
        Entity guard = entityEngine.createEntity();

        PositionComponent positionComponentGuard = new PositionComponent();
        positionComponentGuard.x = 1024;
        guard.add(positionComponentGuard);

        VelocityComponent velocityComponentGuard = new VelocityComponent();
        velocityComponentGuard.x = 2;
        guard.add(velocityComponentGuard);

        TextureComponent textureComponentGuard = new TextureComponent();
        assetManager.load("guard.png", Texture.class);
        assetManager.finishLoadingAsset("guard.png");
        textureComponentGuard.texture = assetManager.get("guard.png");
        guard.add(textureComponentGuard);

        SizeComponent sizeComponentGuard = new SizeComponent();
        sizeComponentGuard.width = 256;
        sizeComponentGuard.height = 256;
        guard.add(sizeComponentGuard);

        entityEngine.addEntity(guard);

        // create bush
        for(int i = 0; i < 20; i++) {
            Entity bush = entityEngine.createEntity();

            PositionComponent positionComponentBush = new PositionComponent();
            positionComponentBush.x = i * 720 + 256;
            bush.add(positionComponentBush);

            SizeComponent sizeComponentBush = new SizeComponent();
            sizeComponentBush.width = 196;
            sizeComponentBush.height = 196;
            bush.add(sizeComponentBush);

            TextureComponent textureComponentBush = new TextureComponent();
            assetManager.load("bush.png", Texture.class);
            assetManager.finishLoadingAsset("bush.png");
            textureComponentBush.texture = assetManager.get("bush.png");
            bush.add(textureComponentBush);

            bush.add(new CoverComponent());

            entityEngine.addEntity(bush);
        }
    }

}
