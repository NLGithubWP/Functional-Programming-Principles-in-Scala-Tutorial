
// class

class User
val user1 = new User
class Point(var x:Int, var y:Int){
  def move(dx:Int, dy:Int):Unit={
    x = x + dx
    y = y + dy
  }
  override def toString:String=s"($x, $y)"
}
val point1 = new Point(1,2)
point1.x
point1.toString

// constructor

class Point2(var x: Int=0, var y:Int=0)
val origin = new Point2()

//private variable and getter, setter

class Point3{
  private var _x = 0
  private var _y = 0
  private val bound = 100
  // use the method to get : getter
  def x:Int = _x
  // use the method to set: setter
  def x_= (nv:Int): Unit = {_x = nv}
  def y:Int = _y
  def y_= (nv: Int): Unit = {_y = nv}
  private def printWarning:Unit=print("warning")
}
val p3 = new Point3
p3.x
p3.x=123
p3.x
//with val, or var, x, y are public by default
class Point4(val x:1, val y: 2)
val p4 = new Point4(1,2)
//p4.x=3 error: val can not be changed
//x,y are private without val or var
class Point5(x:Int, y:Int)
val p5=new Point5(1,0)
//p5.x error: x,y are private


class testa(x:Int, y:Int){

  val xxx = x
  val xxx2 = y
}

class testb extends testa(1,2){
}

val tb = new testb()

print(tb.xxx)
print(tb.xxx2)






