// seq: sequencial list
val salaries = Seq(200, 300, 100)
// no need to provide type of x, since x is known already
val newsalary = salaries.map(x => x*x)
// no need to provide variable name (x), since x is known already
val newsalary = salaries.map(_*12)


object SalaryRaiser {
  private def promtion(salaries: List[Double], pf: Double => Double): List[Double] = {
      salaries.map(pf)
    }

  def smallp(salary: List[Double]): List[Double] = {promtion(salary, _*2)}
  def bigp(salary: List[Double]): List[Double] = {promtion(salary, _*10)}
  def hugep(salary: List[Double]): List[Double] = {promtion(salary, _*100)}
}








