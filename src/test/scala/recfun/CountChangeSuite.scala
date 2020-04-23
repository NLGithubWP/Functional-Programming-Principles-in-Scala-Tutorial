package recfun

import org.scalatest.FunSuite

class CountChangeSuite extends FunSuite{
  import RecfunMain.countChange
  test("money is 4 and list is [1,2], total combination is 3"){
    assert(countChange(money=4, coins=List(1,2))==3)
  }


}
