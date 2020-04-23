package recfun
import scala.language.postfixOps
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


object RecfunMain {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    println("Pascal's methods")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println("balance")
    println(balance("(())())))"))

    println("countChange")
    println(countChange(4,List(1,2)))
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */

  def balance(str: String):Boolean = {

    var inlist = str.toList
    var stack = ListBuffer[Char]()

    def isParentheses(ch: Char): Boolean = {
      if(ch =='(' || ch== ')') true
      else false
    }
    def isMatch(headin: Char, headstack :Char):Boolean={
      if (headin == ')' && headstack == '(') true
      else false
    }

    @tailrec
    def checkban(inlist: List[Char], stack: ListBuffer[Char]):Boolean={

      if (inlist.isEmpty && stack.isEmpty) true
      else if (inlist.isEmpty && stack.nonEmpty) false
      else{
          if(!isParentheses(inlist.head)) {
          checkban(inlist.tail,stack)
          }
          else{
            if (stack.isEmpty) {
              stack.append(inlist.head)
              checkban(inlist.tail, stack)
            }
            else{
              val headin = inlist.head
              val headstack = stack.head
              if (isMatch(headin, headstack)){
                checkban(inlist.tail, stack.tail)
              }
              else{
                stack.append(inlist.head)
                checkban(inlist.tail, stack)
              }
            }
          }
        }
    }
    checkban(inlist, stack)
  }

  /**
   * Exercise 3
   */

  def countChange(money:Int, coins:List[Int]):Int={
    if (money == 0) 1
    else if (money > 0 && coins.nonEmpty){
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
    else 0
  }


}

