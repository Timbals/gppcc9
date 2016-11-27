package me.timbals.gppcc9.entity.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.timbals.gppcc9.Game;
import me.timbals.gppcc9.entity.Mappers;
import me.timbals.gppcc9.entity.components.AnimationComponent;
import me.timbals.gppcc9.entity.components.PositionComponent;
import me.timbals.gppcc9.entity.components.SizeComponent;
import me.timbals.gppcc9.entity.components.TextureComponent;

/**
 * Created by Tim on 27.11.2016.
 */

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class).one(TextureComponent.class, AnimationComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        SpriteBatch batch = Game.getInstance().batch;

        batch.begin();

        for(Entity entity : entities) {
            PositionComponent positionComponent = Mappers.positionMapper.get(entity);

            Texture texture = null;
            TextureRegion textureRegion = null;

            int width = 0;
            int height = 0;

            if(Mappers.textureMapper.has(entity)) {
                TextureComponent textureComponent = Mappers.textureMapper.get(entity);
                texture = textureComponent.texture;
                width = textureComponent.texture.getWidth();
                height = textureComponent.texture.getHeight();
            }

            if(Mappers.animationMapper.has(entity)) {
                while(textureRegion == null) { // workaround for a weird bug that returns a null frame
                    AnimationComponent animationComponent = Mappers.animationMapper.get(entity);
                    animationComponent.time += Gdx.graphics.getDeltaTime();
                    textureRegion = animationComponent.animation.getKeyFrame(animationComponent.time);
                }
            }

            if(Mappers.sizeMapper.has(entity)) {
                SizeComponent sizeComponent = Mappers.sizeMapper.get(entity);
                width = sizeComponent.width;
                height = sizeComponent.height;
            }

            // animation component overrides textureComponent
            if(textureRegion != null) {
                batch.draw(textureRegion, positionComponent.x, positionComponent.y, width, height);
            } else if(texture != null) {
                batch.draw(texture, positionComponent.x, positionComponent.y, width, height);
            }
        }

        batch.end();
    }

}
