package online.ragavan.example.binarytreeserialization

object TreeReconstructor {

  def main(args: Array[String]): Unit = {
    val tree: Node[String] = reconstruct[String]("1 2 e 4 e e 3 e e", " ", "e")(x => x.toString)
  }

  /**
   * Assembles the binary tree back from the pre-order serialized tree.
   */
  def reconstruct[T](serialized: String, delim: String, empty: String)(f: String => T): Node[T] = {
    val elements: List[String] = serialized.split(delim).toList

    def assemble(elements: List[String]): (Node[T], List[String]) = {
      val value = elements.head
      val rest = elements.tail
      if (value == empty) {
        (Empty, rest)
      } else {
        val (leftNode, leftRem) = assemble(rest)
        val (rightNode, rightRem) = assemble(leftRem)
        (NonEmpty(f(value), leftNode, rightNode), rightRem)
      }
    }

    assemble(elements)._1
  }
}
