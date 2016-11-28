package me.timbals.gppcc9;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.timbals.gppcc9.entity.components.AnimationComponent;
import me.timbals.gppcc9.entity.components.CameraFollowComponent;
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
        player.add(entityEngine.createComponent(PositionComponent.class));

        VelocityComponent velocityComponentPlayer = entityEngine.createComponent(VelocityComponent.class);
        velocityComponentPlayer.x = 4;
        player.add(velocityComponentPlayer);

        CameraFollowComponent cameraFollowComponentPlayer = entityEngine.createComponent(CameraFollowComponent.class);
        cameraFollowComponentPlayer.followY = false;
        cameraFollowComponentPlayer.offX = Game.WIDTH / 2;
        player.add(cameraFollowComponentPlayer);

        AnimationComponent animationComponentPlayer = entityEngine.createComponent(AnimationComponent.class);

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

        animationComponentPlayer.animation = animation;
        player.add(animationComponentPlayer);

        SizeComponent sizeComponentPlayer = entityEngine.createComponent(SizeComponent.class);
        sizeComponentPlayer.width = 256;
        sizeComponentPlayer.height = 256;
        player.add(sizeComponentPlayer);

        entityEngine.addEntity(player);

        // create guard
        Entity guard = entityEngine.createEntity();

        PositionComponent positionComponentGuard = entityEngine.createComponent(PositionComponent.class);
        positionComponentGuard.x = 1024;
        guard.add(positionComponentGuard);

        VelocityComponent velocityComponentGuard = entityEngine.createComponent(VelocityComponent.class);
        velocityComponentGuard.x = 2;
        guard.add(velocityComponentGuard);

        TextureComponent textureComponentGuard = entityEngine.createComponent(TextureComponent.class);
        assetManager.load("guard.png", Texture.class);
        assetManager.finishLoadingAsset("guard.png");
        textureComponentGuard.texture = assetManager.get("guard.png");
        guard.add(textureComponentGuard);

        SizeComponent sizeComponentGuard = entityEngine.createComponent(SizeComponent.class);
        sizeComponentGuard.width = 256;
        sizeComponentGuard.height = 256;
        guard.add(sizeComponentGuard);

        entityEngine.addEntity(guard);

        // create bush
        for(int i = 0; i < 20; i++) {
            Entity bush = entityEngine.createEntity();

            PositionComponent positionComponentBush = entityEngine.createComponent(PositionComponent.class);
            positionComponentBush.x = i * 720 + 256;
            bush.add(positionComponentBush);

            SizeComponent sizeComponentBush = entityEngine.createComponent(SizeComponent.class);
            sizeComponentBush.width = 196;
            sizeComponentBush.height = 196;
            bush.add(sizeComponentBush);

            TextureComponent textureComponentBush = entityEngine.createComponent(TextureComponent.class);
            assetManager.load("bush.png", Texture.class);
            assetManager.finishLoadingAsset("bush.png");
            textureComponentBush.texture = assetManager.get("bush.png");
            bush.add(textureComponentBush);

            entityEngine.addEntity(bush);
        }
    }

}
