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
package org.graphstream.algorithm.generator;

import org.graphstream.graph.*;

/**
 * Graph generator.
 * 
 * <p>
 * A graph generator is an object that takes a graph as argument and
 * continuously create and evolve it. Some generators define an end to
 * the generation process, others may continue endlessly.
 * </p>
 * 
 * <p>
 * Generator are very similar to graph readers excepted they have a direct
 * access to the graph. They can be used instead of readers each time the
 * reading process needs to have a read access to the graph.
 * </p>
 * 
 * @author Antoine Dutot
 * @author Yoann Pign�
 */
public interface Generator
{
	/**
	 * Begin the graph generation. This usually is the place for initialisation
	 * of the generator. After calling this method, call the
	 * {@link #nextElement()} method to add elements to the graph.
	 * @param graph The graph to generate.
	 */
	void begin( Graph graph );

	/**
	 * Perform the next step in generating the graph. While this method returns
	 * true, there are still more elements to add to the graph to generate it.
	 * Be careful that some generators never return false here, since they can
	 * generate graphs of arbitrary size. For such generators, simply stop
	 * calling this method when enough elements have been generated.
	 * @return True while there are elements to add to the graph.
	 */
	boolean nextElement();

	/**
	 * End the graph generation by finalising it. Once the
	 * {@link #nextElement()} method returned false (or even if you stop before),
	 * this method must be called to finish the graph.
	 */
	void end();
}