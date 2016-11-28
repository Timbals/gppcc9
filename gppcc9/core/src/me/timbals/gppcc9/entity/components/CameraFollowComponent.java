package me.timbals.gppcc9.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim on 28.11.2016.
 */

public class CameraFollowComponent implements Component, Pool.Poolable {

    public float tween = 0.07f;
    public boolean followX = true;
    public boolean followY = true;
    public int offX = 0;
    public int offY = 0;

    @Override
    public void reset() {
        tween = 0.07f;
        followX = true;
        followY = true;
        offX = 0;
        offY = 0;
    }

}
