

trait Cloneable extends java.lang.Cloneable {
  override def clone(): Cloneable = {
    super.clone().asInstanceOf[Cloneable]
  }
}

trait Resetable {
  def reset: Unit
}

// obc can be either cloneable or restable,
def cloneAndRest(obj: Cloneable with Resetable):Cloneable = {
  val cloned = obj.clone()
  obj.reset
  cloned
}