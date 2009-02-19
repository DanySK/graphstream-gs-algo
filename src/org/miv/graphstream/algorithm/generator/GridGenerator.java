/*
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package org.miv.graphstream.algorithm.generator;

import org.miv.graphstream.graph.*;

/**
 * Generator for grids.
 *
 * @author Antoine Dutot
 * @author Yoann Pign�
 * @since 2007
 */
public class GridGenerator extends BaseGenerator
{
// Attributes
	
	/**
	 * Create diagonal links.
	 */
	protected boolean cross = false;
	
	/**
	 * Close the grid as a tore.
	 */
	protected boolean tore = false;
	
	/**
	 * Current width and height of the grid.
	 */
	protected int currentSize = 0;
	
	/**
	 * Used to generate edge names.
	 */
	protected int edgeNames = 0;
	
// Constructors
	
	/**
	 * New grid generator. By default no diagonal links are
	 * made and the grid is not a tore.
	 */
	public GridGenerator()
	{
		this( false, false );
	}
	
	/**
	 * New grid generator.
	 * @param cross Create diagonal links?.
	 * @param tore Close the grid as a tore?.
	 */
	public GridGenerator( boolean cross, boolean tore )
	{
		this.cross = cross;
		this.tore  = tore;
	}
	
// Attributes
	
// Commands
	
	public void begin( Graph graph )
	{
		this.graph = graph;
		
		addNode( nodeName( 0, 0 ) );
	}

	public void end()
	{
		if( tore )
		{
			if( currentSize > 0 )
			{				
				for( int y=0; y<=currentSize; ++y )
				{
					addEdge( Integer.toString( edgeNames++ ),
							nodeName( currentSize, y ), nodeName( 0, y ) );
					
					if( cross )
					{
						if( y > 0 )
						{
							addEdge( Integer.toString( edgeNames++ ),
								nodeName( currentSize, y ), nodeName( 0, y-1 ) );
							addEdge( Integer.toString( edgeNames++ ),
									nodeName( currentSize, y-1 ), nodeName( 0, y ) );
						}
					}
				}
				
				for( int x=0; x<=currentSize; ++x )
				{
					addEdge( Integer.toString( edgeNames++ ),
							nodeName( x, currentSize ), nodeName( x, 0 ) );
					
					if( cross )
					{
						if( x > 0 )
						{
							addEdge( Integer.toString( edgeNames++ ),
									nodeName( x, currentSize ), nodeName( x-1, 0 ) );
							addEdge( Integer.toString( edgeNames++ ),
									nodeName( x-1, currentSize ), nodeName( x, 0 ) );							
						}
					}
				}
				
				if( cross )
				{
					addEdge( Integer.toString( edgeNames++ ),
							nodeName( currentSize, 0 ), nodeName( 0, currentSize ) );
					addEdge( Integer.toString( edgeNames++ ),
							nodeName( 0, 0 ), nodeName( currentSize, currentSize ) );
				}
			}
		}
	}

	public boolean nextElement()
	{
		currentSize++;
		
		for( int y=0; y<currentSize; ++y )
		{
			String id = nodeName( currentSize, y );
			
			addNode( id );
			addEdge( Integer.toString( edgeNames++ ),
					nodeName( currentSize-1, y ), id );

			if( y > 0 )
			{
				addEdge( Integer.toString( edgeNames++ ),
						nodeName( currentSize, y-1 ), id );
				
				if( cross )
				{
					addEdge( Integer.toString( edgeNames++ ),
							nodeName( currentSize-1, y-1 ), id );
					addEdge( Integer.toString( edgeNames++ ),
							nodeName( currentSize-1, y ), nodeName( currentSize, y-1 ) );
				}
			}
		}
		
		for( int x=0; x<=currentSize; ++x )
		{
			String id = nodeName( x, currentSize );
			
			addNode( id );
			addEdge( Integer.toString( edgeNames++ ),
					nodeName( x, currentSize-1 ), id );
			
			if( x > 0 )
			{
				graph.addEdge( Integer.toString( edgeNames++ ),
						nodeName( x-1, currentSize ), id );
				
				if( cross )
				{		
					graph.addEdge( Integer.toString( edgeNames++ ),
							nodeName( x-1, currentSize-1 ), id );
					graph.addEdge( Integer.toString( edgeNames++ ),
							nodeName( x, currentSize-1 ), nodeName( x-1, currentSize ) );
				}
			}
		}
		
		return false;
	}
	
	protected String nodeName( int x, int y )
	{
		return Integer.toString( x ) + "_" + Integer.toString( y );
	}
}