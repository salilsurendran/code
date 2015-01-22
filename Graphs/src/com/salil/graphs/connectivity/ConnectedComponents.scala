package com.salil.graphs.connectivity

import com.salil.graphs.search.GraphTraversal
import com.salil.graphs.util.GraphUtil
import com.salil.graphs.{Graph, Vertex}

/**
 * Created by sasurendran on 12/25/2014.
 */

object ConnectedComponents extends App {

  if (args.isEmpty)
    print("Please pass a filename containing the graph details to the program")
  else
    new ConnectedComponents().work(args(0))
}

class ConnectedComponents {
  val graphTraversal = new GraphTraversal()

  def work(fileName: String)={
    val g = GraphUtil.getGraphFromAdjacencyList(fileName)
    //println(connectedComponents(g,g.vertices.toList).map(x => x._1 + " -> " + x._2.mkString(",")).mkString("\n"))
    //println(reversePostOrder(g))
    println(reversePostOrder(g.reverse))
    println(stronglyConnectedComponents(g,reversePostOrder(g.reverse)).map(_.mkString(",")).mkString("\n"))
  }

  def connectedComponents(g: Graph, unmarkedList: List[Vertex], i: Int = 0):
      List[(Int, List[Vertex])] = unmarkedList match {
    case Nil => Nil
    case head :: tail =>
      val cc: List[Vertex] = graphTraversal.preOrderDFS(g, g.vertices, List(head))
      (i, cc) :: connectedComponents(g, unmarkedList diff cc, i + 1)
  }

  /*def stronglyConnectedComponents(g:Graph):List[List[Vertex]]
  = stronglyConnectedComponents2(g,topologicalSort(g.reverse))*/

  def stronglyConnectedComponents(g:Graph,vertexOrder:List[Vertex]):List[List[Vertex]] = vertexOrder match {
    case Nil => List()
    case head::tail => val sccList = graphTraversal.preOrderDFS(g,vertexOrder.toSet,List(head))
      sccList::stronglyConnectedComponents(g,vertexOrder diff sccList)
  }

  def reversePostOrder(g: Graph) = fullPostOrderDFS(g, g.vertices.toList).reverse

  def fullPostOrderDFS(g: Graph, unmarkedList: List[Vertex]): List[Vertex] = unmarkedList match {
    case Nil => Nil
    case head :: tail =>
      val markedList = graphTraversal.postOrderDFS(g, unmarkedList.toSet,List(head))
      markedList ::: fullPostOrderDFS(g, unmarkedList diff markedList)
  }
}
