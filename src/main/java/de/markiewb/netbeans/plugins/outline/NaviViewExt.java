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
import de.markiewb.netbeans.plugins.outline.options.CodeoutlineOptionsPanel;
import java.awt.Dimension;
import java.io.IOException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbPreferences;
import org.openide.util.WeakListeners;

/**
 *
 * @author markiewb
 */
public class NaviViewExt extends NaviView implements PreferenceChangeListener {
    private JTextComponent jtc;
    public NaviViewExt(JTextComponent jtc, Document document, JScrollBar scrollBar) {
        super(jtc, document, scrollBar);
	this.jtc = jtc;
        this.setBorder(BorderFactory.createEmptyBorder());

        {
            //add listener for View|Show Outline
            Preferences prefs = MimeLookup.getLookup(MimePath.EMPTY).lookup(Preferences.class);
            prefs.addPreferenceChangeListener(WeakListeners.create(PreferenceChangeListener.class, this, prefs));
        }

        {
            //add listener for preferences set by options dialog
            Preferences prefs = NbPreferences.forModule(CodeoutlineOptionsPanel.class);
            prefs.addPreferenceChangeListener(WeakListeners.create(PreferenceChangeListener.class, this, prefs));
        }

        //initial setup from configuration
        preferenceChange(null);
    }

    private void setupWidth(int width) {
        this.setPreferredSize(new Dimension(width, 0));
        this.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
        //relayout for on the fly-layouting
        this.revalidate();
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {

        if (evt == null
                || Options.KEY_OUTLINE.equals(evt.getKey())) {
            setVisible(Options.isVisible());
        }
        if (evt == null
                || Options.KEY_WIDTH.equals(evt.getKey())) {
            setupWidth(Options.getWidth());
        }
        if (evt == null
                || Options.KEY_POSITION.equals(evt.getKey())) {
            updatePosition();
        }
    }

    private void updatePosition() {
        /**
         * <pre>
         * <filesystem>
         * <folder name="Editors">
         * <folder name="SideBar">
         * <file name="de-markiewb-netbeans-plugins-outline-OutlineSideBarFactory.instance">
         * <attr name="position" intvalue="10000"/>
         * <attr name="location" stringvalue="East"/>
         * <attr name="scrollable" boolvalue="false"/>
         * </file>
         * </folder>
         * </folder>
         * </filesystem>
         * </pre>
         */
        try {

            FileObject configFile = FileUtil.getConfigFile("Editors/SideBar/de-markiewb-netbeans-plugins-outline-OutlineSideBarFactory.instance");
            Options.Position position = Options.getPosition();
            switch (position) {
                case LEFT:
                    //does not work at runtime, needs a restart
                    configFile.setAttribute("location", "West");
                    break;
                case RIGHT:
                    //does not work at runtime, needs a restart
                    configFile.setAttribute("location", "East");
                    break;
                default:
                    throw new AssertionError();
            }
            this.revalidate();

        } catch (IOException iOException) {
        }

    }

}
