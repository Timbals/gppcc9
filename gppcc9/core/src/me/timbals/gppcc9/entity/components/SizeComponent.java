package me.timbals.gppcc9.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim on 27.11.2016.
 */

public class SizeComponent implements Component, Pool.Poolable {

    public int width = 0;
    public int height = 0;

    @Override
    public void reset() {
        width = 0;
        height = 0;
    }

}
