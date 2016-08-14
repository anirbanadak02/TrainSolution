package com.anirban.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.anirban.algorithm.Edge;
import com.anirban.algorithm.Node;
import com.anirban.algorithm.Routes;

public class TrainsTest {
	static Routes graph;
	static Node a, b, c, d, e;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = new Routes(); //Build graph

		a = new Node("A");
		b = new Node("B");
		c = new Node("C");
		d = new Node("D");
		e = new Node("E");

		/*Input given in programming challenge
		Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7*/
		graph.routeTable.put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
		graph.routeTable.put(b, new Edge(b, c, 4));
		graph.routeTable.put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
		graph.routeTable.put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
		graph.routeTable.put(e, new Edge(e, b, 3));
	}

	@Test
	public void testDistanceBetween_ABC() throws Exception {
		List<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(b);
		route.add(c);
		assertEquals(9, graph.distance(route));
	}

	@Test
	public void testDistanceBetween_AD() throws Exception {
		List<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(d);
		assertEquals(5, graph.distance(route));
	}

	@Test
	public void testDistanceBetween_ADC() throws Exception  {
		List<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(d);
		route.add(c);
		assertEquals(13, graph.distance(route));
	}

	@Test
	public void testDistanceBetween_AEBCD() throws Exception  {
		List<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(e);
		route.add(b);
		route.add(c);
		route.add(d);
		assertEquals(22, graph.distance(route));
	}

	@Test(expected=Exception.class)
	public void testDistanceBetween_AED() throws Exception  {
		List<Node> route = new ArrayList<Node>(); 
		route.add(a);
		route.add(e);
		route.add(d);
		assertEquals(-1, graph.distance(route));
	}

	@Test
	public void testNumStops_CC3() throws Exception {
		int numStops = graph.numStops(c, c, 3);
		assertEquals(2, numStops);
	}

	@Test
	public void testNumStops_AC4() throws Exception {
		int numStops = graph.numStops(a, c, 4);
		assertEquals(4, numStops);
	}

	@Test
	public void testShortestRoute_AC() throws Exception {
		int shortestRoute = graph.shortestRoute(a, c);
		assertEquals(9, shortestRoute);
	}

	@Test
	public void testShortestRoute_BB() throws Exception {
		int shortestRoute = graph.shortestRoute(b, b);
		assertEquals(9, shortestRoute);
	}

	@Test
	public void numRoutesWithin_CC30() throws Exception {
		int numRoutesWithin = graph.numRoutesWithin(c, c, 30);
		assertEquals(7, numRoutesWithin);
	}

}