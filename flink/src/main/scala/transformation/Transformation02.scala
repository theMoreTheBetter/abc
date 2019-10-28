package transformation

import org.apache.flink.streaming.api.scala._

object Transformation02 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //KeyBy
    val stream = env.readTextFile("test00.txt").flatMap(item => item.split(" ")).map(item => (item,1L))
    val streamKeyBy = stream.keyBy(0)
//    streamKeyBy.print() //进过keyby同样的单词在一个线程中打印
//    stream.print()

    val streamReduce = streamKeyBy.reduce(
      (item1,item2) =>(item1._1,item1._2 + item2._2)
    )

    streamReduce.print()

    env.execute("Transformation02")
  }

}
