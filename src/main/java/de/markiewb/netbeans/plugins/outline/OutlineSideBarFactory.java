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

import bluej.editor.moe.NaviView;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;

/**
 *
 * @author markiewb
 */
public class OutlineSideBarFactory implements org.netbeans.spi.editor.SideBarFactory {

    private final static int NAVIVIEW_WIDTH = 90;       // width of the "naviview" (min-source) box
    private boolean enabled = true;

    @Override
    public JComponent createSideBar(JTextComponent jtc) {

        if (!enabled) {
            return null;
        }

        Container parent = jtc.getParent();
        if (null == parent) {
            return null;
        }
        parent = parent.getParent();
        if (null == parent) {
            return null;
        }
        if (parent instanceof JScrollPane && null != jtc.getDocument()) {

            JScrollPane scrollPane = (JScrollPane) parent;
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            NaviView naviView = new NaviView(jtc.getDocument(), scrollPane.getVerticalScrollBar());
            naviView.setPreferredSize(new Dimension(NAVIVIEW_WIDTH, 0));
            naviView.setMaximumSize(new Dimension(NAVIVIEW_WIDTH, Integer.MAX_VALUE));
            naviView.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            return naviView;
        }
        return null;

    }

}
