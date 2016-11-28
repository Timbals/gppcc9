package me.timbals.gppcc9.entity;

import com.badlogic.ashley.core.ComponentMapper;

import me.timbals.gppcc9.entity.components.AnimationComponent;
import me.timbals.gppcc9.entity.components.CameraFollowComponent;
import me.timbals.gppcc9.entity.components.PositionComponent;
import me.timbals.gppcc9.entity.components.SizeComponent;
import me.timbals.gppcc9.entity.components.TextureComponent;
import me.timbals.gppcc9.entity.components.VelocityComponent;
import me.timbals.gppcc9.entity.systems.CameraFollowSystem;

/**
 * Created by Tim on 27.11.2016.
 */

public class Mappers {

    public static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<SizeComponent> sizeMapper = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<CameraFollowComponent> cameraFollowMapper = ComponentMapper.getFor(CameraFollowComponent.class);

}
