package basic.bibao

object Test {

  var factor = 10;
  val multiplier = (i : Int)=> i * factor;
  def main(args: Array[String]): Unit = {
    println("multiplier(1) value = " + multiplier(1))
    println("multiplier(10) value = " + multiplier(10))
  }

}
