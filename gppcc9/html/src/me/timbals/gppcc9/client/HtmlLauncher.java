package me.timbals.gppcc9.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import me.timbals.gppcc9.Game;

public class HtmlLauncher extends GwtApplication {

        // no plans to support HTML in the near future

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return Game.getInstance();
        }
}