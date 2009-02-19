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

package org.miv.graphstream.algorithm.test;

import java.io.*;

import org.miv.graphstream.io.*;
import org.miv.graphstream.graph.*;
import org.miv.graphstream.graph.implementations.DefaultGraph;
import org.miv.graphstream.algorithm.coloring.*;


/**
 * Test the Welsh and Powell coloring algorithm.
 *
 * @author Antoine Dutot
 * @author Yoann Pign�
 * @since 2007
 */
public class TestWelshPowell
{
	public static void main( String args[] )
	{
		if( args.length > 0 )
		{
			try
			{
				new TestWelshPowell( args[0] );
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}
			catch( GraphParseException e )
			{
				e.printStackTrace();
			}
		}
	}
	
	public TestWelshPowell( String graphFileName )
		throws IOException, GraphParseException
	{
		Graph       g  = new DefaultGraph();
		WelshPowell wp = new WelshPowell( g, true, "color" ); 
		
		g.read( graphFileName );
		g.display();
		wp.compute();
		
		System.err.printf( "%n%nNumber of colors: %d%n%n", wp.getLastComputedResult() );
	}
}