

def sum(num:Int, res:Int): Int ={
  if (num==1) res
  else sum(num-1, res+num)
}

println(sum(999, 0))