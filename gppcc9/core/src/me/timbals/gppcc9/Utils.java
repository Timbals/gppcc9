package me.timbals.gppcc9;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tim on 30.11.2016.
 */

public class Utils {

    public static TextureRegion[] splitTextureAtlas(Texture atlas, int tileWidth, int tileHeight) {
        TextureRegion[][] region2dArray = TextureRegion.split(atlas, tileWidth, tileHeight);
        TextureRegion[] regionArray = new TextureRegion[region2dArray.length + region2dArray[0].length];

        int index = 0;
        for(int i = 0; i < region2dArray.length; i++) {
            for(int j = 0; j < region2dArray[0].length; j++) {
                regionArray[index++] = region2dArray[i][j];
            }
        }

        return regionArray;
    }

}
