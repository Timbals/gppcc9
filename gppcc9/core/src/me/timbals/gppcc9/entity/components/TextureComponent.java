package me.timbals.gppcc9.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim on 27.11.2016.
 */

public class TextureComponent implements Component, Pool.Poolable{

    public Texture texture;

    @Override
    public void reset() {
        texture = null;
    }
}
