/*
 * Copyright (C) 2015 markiewb
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.markiewb.netbeans.plugins.outline.options;

import java.util.prefs.Preferences;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.openide.util.NbPreferences;

/**
 *
 * @author markiewb
 */
public class Options {

    public static final int DEFAULT_FONT_SIZE = 2;

    public static final boolean DEFAULT_OUTLINE = true;
    public static final String DEFAULT_POSITION = Position.RIGHT.name();
    public static final int DEFAULT_WIDTH = 90;
    public static final boolean DEFAULT_DARKENING = false;
    public static final String KEY_FONT_SIZE = "outline.fontsize";
    public static final String KEY_OUTLINE = "enable.outline";
    public static final String KEY_POSITION = "outline.position";
    public static final String KEY_WIDTH = "outline.width";
    public static final String KEY_DARKENING = "outline.darkening";

    public static int getFontSize() {
        return NbPreferences.forModule(CodeoutlineOptionsPanel.class).getInt(KEY_FONT_SIZE, DEFAULT_FONT_SIZE);
    }

    public static Position getPosition() {
        String get = NbPreferences.forModule(CodeoutlineOptionsPanel.class).get(KEY_POSITION, DEFAULT_POSITION);
        return Position.valueOf(get.toUpperCase());
    }

    public static int getWidth() {
        return NbPreferences.forModule(CodeoutlineOptionsPanel.class).getInt(KEY_WIDTH, DEFAULT_WIDTH);
    }

    public static float getDarkeningValue() {
        boolean isDarkening = isDarkening();
        return isDarkening ?/*for dark themes*/ 0.5f :/*for light themes*/ 0.125f;
    }

    public static boolean isDarkening() {
        return NbPreferences.forModule(CodeoutlineOptionsPanel.class).getBoolean(KEY_DARKENING, DEFAULT_DARKENING);
    }

    public static boolean isVisible() {
        Preferences prefs = MimeLookup.getLookup(MimePath.EMPTY).lookup(Preferences.class);
        return prefs.getBoolean(Options.KEY_OUTLINE, Options.DEFAULT_OUTLINE);
    }

    public static void setFontSize(Integer value) {
        NbPreferences.forModule(CodeoutlineOptionsPanel.class).putInt(KEY_FONT_SIZE, value);
    }

    public static void setPosition(String toString) {
        NbPreferences.forModule(CodeoutlineOptionsPanel.class).put(KEY_POSITION, Position.valueOf(toString.toUpperCase()).name());
    }

    public static void setDarkening(boolean value) {
        NbPreferences.forModule(CodeoutlineOptionsPanel.class).putBoolean(KEY_DARKENING, value);
    }

    public static void setWidth(Integer value) {
        NbPreferences.forModule(CodeoutlineOptionsPanel.class).putInt(KEY_WIDTH, value);
    }

    public enum Position {

        LEFT, RIGHT
    }

}
