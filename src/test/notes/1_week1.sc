"""
  |1. call by name and call by values
  |    func()：返回值是Int，没有参数
  |    callByValue(x:Int)：返回值是Unit，传入Int类型的值
  |    callByName(x:=>Int)：返回值是Unit，传入的是一个匿名函数(该函数传入的参数为空，返回值是Int)
  |  call by value , evaluated before calling the function
  |  call by name:  evaluated on each use
"""
var Total = 0
def  func():Int={
  Total += 1 ; Total
}

def callByValue(i: Int)
{
  println("on day one is 1 - Total = " + i)
  println("on day two is 1 - Total = " + i)
  println("on day three is 1 - Total = " + i)
  println("on day four is 1 - Total = " + i)
}

callByValue{
  func()}

def callByName(i: => Int)
{
  println("on day one is 1 - Total = " + i)
  println("on day two is 1 - Total = " + i)
  println("on day three is 1 - Total = " + i)
  println("on day four is 1 - Total = " + i)
}

callByName{
  func()}

"""
  |2. tail recursion: if a function call itself as its last action, the fucntion's stack frame can be reused
  |we can require a function is tail-recursive using a @tailrec annotation.
"""