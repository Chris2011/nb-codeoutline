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

import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author markiewb
 */
public class OutlineSideBarFactory implements org.netbeans.spi.editor.SideBarFactory {

	@Override
	public JComponent createSideBar(JTextComponent jtc) {

		/**
		 * <pre>
		 * jtc.getParent()                              #8227	NbEditorUI$LayeredEditorPane
		 * jtc.getParent().getParent()                  #8233	JViewport
		 * jtc.getParent().getParent().getParent()	#8234	JScrollPane
		 * </pre>
		 */
		Container parent = jtc.getParent();
		if (null == parent) {
			return null;
		}
		parent = parent.getParent();
		if (null == parent) {
			return null;
		}
		// a new container JViewport has been introduced in 8.2
		parent = parent.getParent();
		if (null == parent) {
			return null;
		}
		if (parent instanceof JScrollPane && null != jtc.getDocument()) {

			JScrollPane scrollPane = (JScrollPane) parent;

			return new NaviViewExt(jtc.getDocument(), scrollPane.getVerticalScrollBar());
		}
		return null;
	}

}
