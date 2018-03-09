/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.laf.list;

import com.alee.managers.tooltip.AbstractComponentArea;
import com.alee.managers.tooltip.ComponentArea;

import javax.swing.*;
import java.awt.*;

/**
 * {@link ComponentArea} implementation describing {@link JList} cell area.
 *
 * @param <V> cell value type
 * @param <C> component type
 * @author Mikle Garin
 */

public class ListCellArea<V, C extends JList> extends AbstractComponentArea<V, C>
{
    /**
     * List cell index.
     */
    protected final int index;

    /**
     * Constructs new {@link ListCellArea}.
     *
     * @param index list cell index
     */
    public ListCellArea ( final int index )
    {
        super ();
        this.index = index;
    }

    /**
     * Returns list cell index.
     *
     * @return list cell index
     */
    public int index ()
    {
        return index;
    }

    @Override
    public Rectangle getBounds ( final C component )
    {
        // Calculating cell bounds
        final Rectangle bounds = component.getCellBounds ( index, index );

        // Adjusting tooltip location
        final V value = getValue ( component );
        final boolean isSelected = component.isSelectedIndex ( index );
        final boolean cellHasFocus = component.getLeadSelectionIndex () == index;
        final ListCellRenderer cellRenderer = component.getCellRenderer ();
        final Component renderer = cellRenderer.getListCellRendererComponent ( component, value, index, isSelected, cellHasFocus );
        adjustBounds ( component, renderer, bounds );

        return bounds;
    }

    @Override
    public V getValue ( final C component )
    {
        return ( V ) component.getModel ().getElementAt ( index );
    }

    @Override
    public boolean equals ( final Object other )
    {
        return other != null && other instanceof ListCellArea && this.index == ( ( ListCellArea ) other ).index;
    }
}