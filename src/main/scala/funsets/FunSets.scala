package funsets

object FunSets {

  def main(args: Array[String]):Unit={
    print(map(x => x > 0, x => x ))
  }

  /**
   * define a new set, get int and return boolean
   */
  type Set = Int => Boolean

  /**
   *  indicates whether a set contains a given element
   */
  def contains(s:Set, elem: Int): Boolean = s(elem)

  def singletonSet(elem:Int): Set = (x:Int) => x==elem

  def union(s: Set, t: Set): Set = (x:Int) => contains(s,x) || contains(t,x)

  def intersect(s: Set, t: Set): Set = (x:Int) => contains(s, x) && contains(t, x)

  def diff(s:Set, t: Set): Set = (x:Int) => contains(s,x) && !contains(t,x)

  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean =  {
    def iter(a:Int):Boolean={
      if(a>bound) return true
      else if (contains(s, a) && !p(a)) return false
      else return iter(a+1)
    }
    iter(-bound)
  }
  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   * To be succinct:
   *
   * If you say: val set2 = map(set1, f),
   *
   * then set2(x) will returns true if and only if there exits y in set1 such as f(y) == x
   *
   * That's exactly what exists(set1, y => f(y) == x) is checking.
   *
   * To put it an other way, an integer is in set2 only if you can obtain it by applying f to an element of set1.
   *
   */

  def map(s:Set, f:Int=>Int):Set = (x:Int) => exists(s, f(_) == x)
  // above map function is the same as below one
  //  def map(s: Set, f: Int => Int): Set =  (x => exists(s, (y: Int) => f(y) == x))


  def toString(s: Set): String ={
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  def printSet(s: Set): Unit = {
    println(toString(s))
  }
}
