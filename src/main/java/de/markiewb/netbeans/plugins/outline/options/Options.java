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

import de.markiewb.netbeans.plugins.outline.OutlineSideBarFactory;
import java.util.prefs.Preferences;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;

/**
 *
 * @author markiewb
 */
public class Options {

    public static boolean isVisible() {
        Preferences prefs = MimeLookup.getLookup(MimePath.EMPTY).lookup(Preferences.class);
        boolean visible = prefs.getBoolean(OutlineSideBarFactory.KEY_OUTLINE, OutlineSideBarFactory.DEFAULT_OUTLINE);
        return visible;
    }

}
