package source

import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.scala._

object FlinkSource01 {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

//    基于File
//    val stream = env.readTextFile("test00.txt")

//    val path = new Path("/opt/modules/test.txt")
//    val stream = env.readFile(new TextInputFormat(path),"/opt/modules/test.txt")

//    基于Socket
//    val stream = env.socketTextStream("localhost",11111)

//    基于集合
//    val list = List(1,2,3,4)
//    val stream = env.fromCollection(list)

//    val iterator = Iterator(1,2,3,4)
//    val stream = env.fromCollection(iterator)

//    val list = List(5,6,7,8)
//    val stream = env.fromElements(list)


//    val stream = env.generateSequence(1,10)

//    stream.print()
    env.execute("FirstJob")
  }

}
