package me.timbals.gppcc9.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.timbals.gppcc9.entity.Mappers;
import me.timbals.gppcc9.entity.components.PositionComponent;
import me.timbals.gppcc9.entity.components.VelocityComponent;

/**
 * Created by Tim on 27.11.2016.
 */

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);

        positionComponent.x += velocityComponent.x * deltaTime;
        positionComponent.y += velocityComponent.y * deltaTime;
    }
}
