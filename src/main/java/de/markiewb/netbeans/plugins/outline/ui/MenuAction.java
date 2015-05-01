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
package de.markiewb.netbeans.plugins.outline.ui;

import de.markiewb.netbeans.plugins.outline.OutlineSideBarFactory;
import de.markiewb.netbeans.plugins.outline.options.Options;
import java.awt.event.ActionEvent;
import org.netbeans.api.editor.EditorActionRegistration;
import org.openide.util.NbBundle;

@EditorActionRegistration(name = "toggle-outline-view",
        menuPath = "View",
        menuPosition = 1000,
        preferencesKey = Options.KEY_OUTLINE,
        preferencesDefault = Options.DEFAULT_OUTLINE
)
@NbBundle.Messages("toggle-outline-view=Show Outline")
public class MenuAction extends javax.swing.AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
