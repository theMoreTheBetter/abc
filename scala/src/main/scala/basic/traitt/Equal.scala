package basic.traitt

trait Equal {

  def isEqual(x:Any):Boolean
  def isNotEqual(x:Any):Boolean = !isNotEqual(x)

}
