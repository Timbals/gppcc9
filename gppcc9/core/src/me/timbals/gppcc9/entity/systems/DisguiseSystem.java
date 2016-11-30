package me.timbals.gppcc9.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;

import me.timbals.gppcc9.Game;
import me.timbals.gppcc9.entity.Mappers;
import me.timbals.gppcc9.entity.components.CoverComponent;
import me.timbals.gppcc9.entity.components.DisguiseComponent;
import me.timbals.gppcc9.entity.components.PositionComponent;
import me.timbals.gppcc9.entity.components.SizeComponent;

/**
 * Created by Tim on 30.11.2016.
 */

public class DisguiseSystem extends IteratingSystem {

    public DisguiseSystem() {
        super(Family.all(PositionComponent.class, SizeComponent.class, DisguiseComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        SizeComponent sizeComponent = Mappers.sizeMapper.get(entity);
        DisguiseComponent disguiseComponent = Mappers.disguiseMapper.get(entity);

        Rectangle hitboxEntity = new Rectangle(
                positionComponent.x + sizeComponent.width / 2,
                positionComponent.y,
                1,
                1);

        ImmutableArray<Entity> covers = Game.getInstance().entityEngine.getEntitiesFor(
                Family.all(PositionComponent.class, SizeComponent.class, CoverComponent.class).get());

        disguiseComponent.disguised = false;

        for(Entity cover : covers) {
            PositionComponent positionComponentCover = Mappers.positionMapper.get(cover);
            SizeComponent sizeComponentCover = Mappers.sizeMapper.get(entity);
            Rectangle hitboxCover = new Rectangle(positionComponentCover.x, positionComponentCover.y, sizeComponentCover.width, sizeComponentCover.height);

            if(hitboxCover.overlaps(hitboxEntity)) {
                disguiseComponent.disguised = true;
                break;
            }
        }
    }
}
