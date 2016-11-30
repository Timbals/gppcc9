package me.timbals.gppcc9.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import me.timbals.gppcc9.entity.Mappers;
import me.timbals.gppcc9.entity.components.AnimationComponent;
import me.timbals.gppcc9.entity.components.InputComponent;
import me.timbals.gppcc9.entity.components.VelocityComponent;

/**
 * Created by Tim on 30.11.2016.
 */

public class InputSystem extends IteratingSystem {

    public InputSystem() {
        super(Family.all(AnimationComponent.class, VelocityComponent.class, InputComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = Mappers.animationMapper.get(entity);
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocityComponent.x = 0;
            animationComponent.time = 0;
        } else {
            velocityComponent.x = 4;
        }
    }

}
