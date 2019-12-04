package basic.classobject

class Point(val xc: Int,val yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点: " + x);
    println ("y 的坐标点: " + y);
  }
}
class Location(override val xc: Int,override val yc: Int,
                zc :Int) extends Point(xc, yc){
  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int) {
    x = x + dx
    y = y + dy
    z = z + dz
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
    println ("z 的坐标点 : " + z);
  }
}


class Person{
  var name = ""
  //重写一个非抽象方法，必须用override修饰符。
  override def toString = getClass.getName + "[name=" + name + "]"
}

class Employee extends Person{
  var salary = 0.0
  override def toString: String = super.toString + "[salary=" + salary + "]"
}

object Test {

  def main(args: Array[String]) {
    val fred = new Employee
    fred.name = "Fred"
    fred.salary = 50000
    println(fred)

    val point = new Point(10,20)
    def printPoing: Unit ={
      println ("x 的坐标点 : " + point.x);
      println ("y 的坐标点 : " + point.y);
    }
  }
}
