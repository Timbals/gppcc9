package me.timbals.gppcc9.entity.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.timbals.gppcc9.Game;
import me.timbals.gppcc9.entity.Mappers;
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
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        SpriteBatch batch = Game.getInstance().batch;

        batch.begin();

        for(Entity entity : entities) {
            PositionComponent positionComponent = Mappers.positionMapper.get(entity);
            TextureComponent textureComponent = Mappers.textureMapper.get(entity);

            int width = textureComponent.texture.getWidth();
            int height = textureComponent.texture.getHeight();

            if(Mappers.sizeMapper.has(entity)) {
                SizeComponent sizeComponent = Mappers.sizeMapper.get(entity);
                width = sizeComponent.width;
                height = sizeComponent.height;
            }

            batch.draw(textureComponent.texture, positionComponent.x, positionComponent.y, width, height);
        }

        batch.end();
    }

}
