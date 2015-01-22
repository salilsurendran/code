package com.salil.graphs.search


import com.salil.graphs.util.GraphUtil
import com.salil.graphs.{Graph, Vertex}


object DFS extends App {

  if (args.isEmpty)
    print("Please pass a filename containing the graph details to the program")
  else
    new DFS().doDFS(args(0))
}

class DFS {
  def doDFS(fileName: String) {
    val g = GraphUtil.getGraphFromAdjacencyList(fileName)
    println(preOrderDFS(g, g.vertices, List(Vertex("A"))).mkString(" "))
    println(postOrderDFS(g,g.vertices, List(Vertex("A"))).mkString(" "))
  }


  def preOrderDFS(g: Graph, unmarkedSet: Set[Vertex], vertexList: List[Vertex]): List[Vertex] = vertexList match {
    case List() => Nil
    case head :: tail => (head) :: preOrderDFS(g, unmarkedSet - head,
      g.vertexMap.getOrElse(head, List()).reverse ++ tail intersect (unmarkedSet.toList))
  }

  def postOrderDFS(g: Graph, unmarkedSet: Set[Vertex], vertexList: List[Vertex]): List[Vertex] = vertexList match {
    case List() => Nil
    case head :: tail => val visited = postOrderDFS(g, unmarkedSet - head,
      g.vertexMap.getOrElse(head, List()).reverse intersect unmarkedSet.toList) :+ head
      visited ::: postOrderDFS(g, unmarkedSet -- visited, tail diff visited)
  }
}