package com.salil.graphs

class Graph {
  //Returns a set of vertices
  var vertices = Set[Vertex]()
  //Maintains a list of edges
  var edges = List[Edge]()
  //Maintains a map of vertices where each vertex(key) is connected to a list of vertices(value)
  var vertexMap = Map[Vertex,List[Vertex]]()


  def addVertex(v: Vertex) = vertices = vertices + v

  def addEdge(e: Edge) = {
    edges = e :: edges
    vertexMap += e.node1 -> (e.node2::vertexMap.getOrElse(e.node1, Nil))
  }

  //Reverses the graph
  def reverse():Graph ={
    val gReverse = new Graph()
    for(v <- vertices)
      gReverse.addVertex(v)
    for(e <- edges)
      gReverse.addEdge(new Edge(e.node2,e.node1))
    gReverse
  }

  override def toString = vertexMap.toString
}

case class Vertex(id: String, weight: Float = 0){
  override def toString = id
}

case class Edge(node1: Vertex, node2: Vertex, weight: Float = 0)