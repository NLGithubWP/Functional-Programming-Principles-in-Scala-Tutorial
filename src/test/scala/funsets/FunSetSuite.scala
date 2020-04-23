package funsets

import org.scalatest.FunSuite

class FunSetSuite extends FunSuite {
  import FunSets._

  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)

  test("testing existing"){
    assert(exists(s1, s1))
  }

  test("testing mapping"){
    val s = map(s1, x=> 2*x)
    assert(s(2))
  }

}
