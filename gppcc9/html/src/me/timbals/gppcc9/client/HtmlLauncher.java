package me.timbals.gppcc9.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import me.timbals.gppcc9.Game;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Game.WIDTH, Game.HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return Game.getInstance();
        }
}