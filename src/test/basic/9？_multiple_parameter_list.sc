


def foldleft[B](z: B)(op: (B, A)=>B):B
def test(a:Int):Unit={
  print(a)
}
val nums = List(1,2,3,4,5,6,7,8,9,10)
val res = nums.foldRight(0)((m, n)=>m+n)

