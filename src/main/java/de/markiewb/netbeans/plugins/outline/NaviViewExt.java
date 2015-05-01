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
package de.markiewb.netbeans.plugins.outline;

import de.markiewb.netbeans.plugins.outline.options.Options;
import bluej.editor.moe.NaviView;
import java.awt.Dimension;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import javax.swing.text.Document;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.openide.util.WeakListeners;

/**
 *
 * @author markiewb
 */
public class NaviViewExt extends NaviView implements PreferenceChangeListener {

    private final static int NAVIVIEW_WIDTH = 90;       // width of the "naviview" (min-source) box
//    private final Preferences prefs;

    public NaviViewExt(Document document, JScrollBar scrollBar) {
        super(document, scrollBar);
        this.setPreferredSize(new Dimension(NAVIVIEW_WIDTH, 0));
        this.setMaximumSize(new Dimension(NAVIVIEW_WIDTH, Integer.MAX_VALUE));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        Preferences prefs = MimeLookup.getLookup(MimePath.EMPTY).lookup(Preferences.class);
        prefs.addPreferenceChangeListener(WeakListeners.create(PreferenceChangeListener.class, this, prefs));

        //initial setup from configuration
        preferenceChange(null);
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {

        if (evt == null
                || OutlineSideBarFactory.KEY_OUTLINE.equals(evt.getKey())) {
            setVisible(Options.isVisible());
        }
    }


}
