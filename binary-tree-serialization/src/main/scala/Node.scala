package online.ragavan.example.binarytreeserialization

trait Node[+T] {
  /**
   * Uses pre-order traversal to serialize a binary tree with 'e' 
   * as the marker for an empty node
   */
  override def toString() = this match {
    case Empty                        => "e"
    case NonEmpty(value, left, right) => s"${value} ${left.toString} ${right.toString}"
  }
}

case object Empty extends Node[Nothing] 
case class NonEmpty[T](value: T, left: Node[T], right: Node[T]) extends Node[T] 
