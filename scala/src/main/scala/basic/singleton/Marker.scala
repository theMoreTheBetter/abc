package basic.singleton

/**
  * Scala 中使用单例模式时，除了定义的类之外，还要定义一个同名的 object 对象，它和类的区别是，object对象不能带参数。
  * @param color
  */
class Marker private(val color:String){
  println("创建" + this)

  override def toString(): String = "颜色标记："+ color
}

object Marker {

  private val markers:Map[String,Marker]=Map(
    "red"->new Marker("red"),
    "blue"->new Marker("blue"),
    "green"->new Marker("green")
  )

  def apply( color: String) = {
    if(markers.contains(color)) markers(color) else null
  }

  def getMarker(color:String) = {
    if(markers.contains(color)) markers(color) else null
  }

  def main(args: Array[String]) {
    println(Marker("red"))
    // 单例函数调用，省略了.(点)符号
    println(Marker.getMarker("blue"))
  }

}
