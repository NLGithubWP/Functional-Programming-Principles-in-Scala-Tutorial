package recfun
import org.scalatest.FunSuite

class BalanceSuite extends FunSuite {
  import RecfunMain.balance
  test("balance: '(())' is balanced") {
    assert(balance("(())"))
  }

  test("balance: '(( ( ))' is not balanced") {
    assert(balance("(())"))
  }
}
