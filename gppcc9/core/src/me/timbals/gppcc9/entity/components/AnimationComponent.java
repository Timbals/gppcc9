package me.timbals.gppcc9.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim on 27.11.2016.
 */

public class AnimationComponent implements Component, Pool.Poolable {

    public Animation animation;
    public float time = 0.0f;

    @Override
    public void reset() {
        animation = null;
        time = 0.0f;
    }

}
