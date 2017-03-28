package online.ragavan.example.binarytreeserialization.test

import online.ragavan.example.binarytreeserialization.TreeReconstructor
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import online.ragavan.example.binarytreeserialization.Node
import online.ragavan.example.binarytreeserialization.NonEmpty
import online.ragavan.example.binarytreeserialization.Empty
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class TreeTester extends FunSuite with Checkers {
  class QuickCheckTree extends Properties("Node") {

    implicit lazy val arbTree: Arbitrary[Node[Int]] = Arbitrary(genTree)

    lazy val genNonEmpty: Gen[Node[Int]] = {
      for {
        i <- Gen.choose(0, 100)
        left <- genTree
        right <- genTree
      } yield {
        NonEmpty(i, left, right)
      }
    }

    lazy val genEmpty: Gen[Node[Int]] = {
      Empty
    }

    lazy val genTree: Gen[Node[Int]] = {
      Gen.oneOf(genEmpty, genNonEmpty)
    }

    property("Serde of a tree") = forAll { (tree: Node[Int]) =>
      val serialized: String = tree.toString()
      val reconstructed = TreeReconstructor.reconstruct(serialized, " ", "e")(x => x.toInt)
      (serialized == reconstructed.toString && tree == reconstructed)
    }
  }

  test("Random testing of tree serde") {
    check(new QuickCheckTree, minSuccessful(100))
  }

}
