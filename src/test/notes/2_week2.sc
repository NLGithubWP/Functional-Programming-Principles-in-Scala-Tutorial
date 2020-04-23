
"""
  |1. higher order functions
  |   Functions that either take other functions as parameters or return functions as results.
  |
  |2. Function Types
  |   A => B is a function type. Stands for a function that takes type A and returns type B. Ex: Int => Int is the type of function.
  |
  |3.Anonymous functions
  |   Syntax (x: Int) => x * x * x (x: Int) is the parameter of the function and x * x * x is it's body.
  |
  |4.Currying
  | Functions that return other functions
  |
  |"""

def sum(f: Int => Int): (Int, Int) => Int ={
  def worker(x:Int, y:Int) : Int ={
    f(x)+y
  }
  worker
}

sum(x =>2*x)(1,2)

"""
  |4, Functions that return other functions are used frequently in Scala and Scala provides a easier syntax to achieve that.
  |
  |  def outer-func(outer-func-params): <signature-of-returned-func> = {
  |    def inner-func(inner-func-params): <inner-func-return-type> = {
  |      <inner-func-body>
  |    }
  |    inner-func
  |  }
  |can be replaced by def outer-func(outer-func-params)(inner-func-params): <outer-func-return-type> = {}
  |
  |5, function types associate to the right, so Int => Int => Int is equivalent to Int => (Int => Int).
  |""".stripMargin

def sum_easy(f:Int => Int)(x:Int, y:Int):Int= {
  f(x)+y
}
sum_easy(x =>2*x)(1,2)

"""
  |6. Scala Syntax Summary
  |Syntax
  |Types can be:
  |
  |   Numeric type (Int, Double, Byte, Short, Char, Long, Float)
  |Boolean type (true, false)
  |   String type
  |   Function type (Int => Int, (Int, Int) => Int)
  |Expressions can be:
  |
  |An identifier like x, isGoodEnough
  |   A literal like 0, 1.0, "abc"
  |   A function application like sqrt(x)
  |   An operator application like -x, y + x
  |   A selection like math.abs
  |   A conditional expression like if (x > 0) x else -x
  |   A block like { val x = math.abs(y) ; x * 2 }
  |   An anonymous function like x => x + 1
  |Definitions can be:
  |
  |   A function definition like def sqrt(x: Int): Double = {}
  |   A value definition like val y = sqrt(2)
  |Parameter can be:
  |
  |   Call by value parameter like (x: Int)
  |   Call by name parameter like (y: => Double)
"""