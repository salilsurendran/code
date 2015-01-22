package com.salil.graphs.util

import com.salil.graphs.{Edge, Vertex, Graph}

import scala.io.Source

/**
 * Created by sasurendran on 12/24/2014.
 */
object GraphUtil {

  def getGraphFromAdjacencyList(fileName: String): Graph = {

    val graph = new Graph()
    //Read a line from the file for eg. A : B C denoting A is connected to B and C
    for (line <- Source.fromFile(fileName).getLines()) {
      val arr = line.trim.split(":") //Split the line into two using the delimiter ':"
      graph.addVertex(Vertex(arr(0))) //Add the vertex A
      if (arr.length > 1)
        for (e <- arr(1).trim.split("\\s+"))
          graph.addEdge(new Edge(new Vertex(arr(0)), new Vertex(e))) //Add the edge A -> B and A -> C
    }
    graph
  }

}
