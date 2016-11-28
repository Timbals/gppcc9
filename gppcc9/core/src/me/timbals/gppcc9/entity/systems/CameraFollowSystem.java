package me.timbals.gppcc9.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;

import me.timbals.gppcc9.Game;
import me.timbals.gppcc9.entity.Mappers;
import me.timbals.gppcc9.entity.components.CameraFollowComponent;
import me.timbals.gppcc9.entity.components.PositionComponent;

/**
 * Created by Tim on 28.11.2016.
 */

public class CameraFollowSystem extends IteratingSystem {

    public CameraFollowSystem() {
        super(Family.all(PositionComponent.class, CameraFollowComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        CameraFollowComponent cameraFollowComponent = Mappers.cameraFollowMapper.get(entity);

        Vector3 cameraPosition = Game.getInstance().camera.position;
        if(cameraFollowComponent.followX) {
            cameraPosition.x += (positionComponent.x - cameraPosition.x + cameraFollowComponent.offX)
                    * cameraFollowComponent.tween * deltaTime;
        }
        if(cameraFollowComponent.followY) {
            cameraPosition.y += (positionComponent.y - cameraPosition.y + cameraFollowComponent.offY)
                    * cameraFollowComponent.tween * deltaTime;
        }
    }

}
