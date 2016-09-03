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

import de.markiewb.netbeans.plugins.outline.options.Options;
import java.awt.Color;
import java.awt.Font;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.settings.FontColorNames;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;

/**
 *
 * @author markiewb
 */
public class ColorAndFontProvider {

    /**
     * Get the colour for drawing text.
     */
    public static Color getTextColor(JTextComponent jtc) {
        return StyleConstants.getForeground(getFontAttributes(jtc));
    }

    public static Color getBackgroundColor(JTextComponent jtc) {
        return StyleConstants.getBackground(getFontAttributes(jtc));
    }

    public static Color getHighlightColor(JTextComponent jtc) {
        return new Color(0, 0, 0, Options.getDarkeningValue());
    }

    public static Font getFont(JTextComponent jtc) {
        String defaultFontName = "Monospaced";

        String fontName = StyleConstants.getFontFamily(getFontAttributes(jtc));

        Font smallFont = new Font(fontName != null ? fontName : defaultFontName, Font.PLAIN, Options.getFontSize());
        return smallFont;
    }

    private static AttributeSet getFontAttributes(JTextComponent textComponent) {
        String mimeType = DocumentUtilities.getMimeType(textComponent);
        return MimeLookup.getLookup(mimeType)
                .lookup(FontColorSettings.class)
                .getFontColors(FontColorNames.DEFAULT_COLORING);
    }
}
