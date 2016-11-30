package me.timbals.gppcc9.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim on 30.11.2016.
 */

public class DisguiseComponent implements Component, Pool.Poolable {

    public boolean disguised = false;

    @Override
    public void reset() {
        disguised = false;
    }

}
