import com.sun.tools.internal.xjc.reader.gbind.Graph

class Grahph {
  class Node {
    var connectedNodes: List[Node] = Nil

    def conntecto(node: Node): Unit = {
      if (!connectedNodes.exists(node.equals)) {
        connectedNodes = node :: connectedNodes
      }
    }
  }
  var nodes: List[Node] = Nil
  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }
}

val g1: Grahph = new Grahph
val node1: g1.Node = g1.newNode
val node2: g1.Node = g1.newNode
val node3: g1.Node = g1.newNode

print(g1.nodes)
node1.conntecto(node2)
node3.conntecto(node1)
print(node3.connectedNodes)

