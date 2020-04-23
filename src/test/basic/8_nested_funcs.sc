

def factorial(x: Int): Int= {
  def fact(x: Int, accumulator: Int) : Int = {
    if (x <= 1) accumulator
    else fact(x-1, x*accumulator)
  }
  fact(x=x,accumulator = 1 )
}

print(factorial(2))

