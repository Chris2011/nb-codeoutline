/*
 * Copyright (C) 2016 markiewb
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

import java.awt.Color;
import java.awt.Font;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.modules.editor.NbEditorUtilities;

/**
 *
 * @author markiewb
 */
public class ColorAndFontProvider {

    /**
     * Get the colour for drawing text.
     */
    public static Color getTextColor(JTextComponent jtc) {
        String mimeType = NbEditorUtilities.getMimeType(jtc);
        FontColorSettings fcs = MimeLookup.getLookup(mimeType).lookup(FontColorSettings.class);
        AttributeSet fontColors = fcs.getFontColors("default");
        Color fg = (Color) fontColors.getAttribute(StyleConstants.Foreground);
        return fg;
//        JTextComponent host = (JTextComponent) getContainer();
//        return (host.isEnabled()) ? host.getForeground() : host.getDisabledTextColor();
    }

    public static Color getBackgroundColor(JTextComponent jtc) {
        String mimeType = NbEditorUtilities.getMimeType(jtc);
        FontColorSettings fcs = MimeLookup.getLookup(mimeType).lookup(FontColorSettings.class);
        AttributeSet fontColors = fcs.getFontColors("default");
        Color bg = (Color) fontColors.getAttribute(StyleConstants.Background);
        return bg;
    }

    public static Color getHighlightColor(JTextComponent jtc) {
        return new Color(0, 0, 0, 0.5f);
    }

    public static Font getFont(JTextComponent jtc) {
        String defaultFontName = "Monospaced";

        String mimeType = NbEditorUtilities.getMimeType(jtc);
        FontColorSettings fcs = MimeLookup.getLookup(mimeType).lookup(FontColorSettings.class);
        String fontName = (String) fcs.getFontColors("default").getAttribute(StyleConstants.FontFamily);
        Font smallFont = new Font(fontName != null ? fontName : defaultFontName, Font.PLAIN, 1);
        return smallFont;
    }
}
