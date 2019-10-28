package transformation

import org.apache.flink.streaming.api.scala._

object Transformation01 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //map
//    val stream = env.generateSequence(1,10)
//    val streamMap = stream.map(item => item * 2)
//    streamMap.print()

    //flatmap
//    val stream = env.readTextFile("test00.txt")
//    val streamFlat = stream.flatMap(item => item.split(" "))
//    streamFlat.print()

//    filter
//    val stream = env.generateSequence(1,10)
//    val streamFilter = stream.filter(item => item!=1)
//    streamFilter.print()

////    connect(DataStream,DataStream -> ConnectedStreams)
//    val stream01 = env.generateSequence(1,10)
//    val stream02 = env.readTextFile("test00.txt").flatMap(item => item.split(" "))
//    val streamConnect = stream01.connect(stream02)
//    streamConnect.map(item=>println(item),item=>println(item))
//    val streamComap = streamConnect.map(item=>item * 2,item=>(item,1L))
//    streamComap.print()
//
////    CoMap CoFlatMap (ConnectedStreams=>DataStream)

//    Split && Select
//    val stream = env.readTextFile("test00.txt").flatMap(item => item.split(" "))
//    val streamSplit = stream.split(
//      word => ("java".equals(word) match {
//        case true =>List("java")
//        case false => List("other")
//      })
//    )
//    val streamSelect01 = streamSplit.select("java")
//    val streamSelect02 = streamSplit.select("other")
//    streamSelect01.print()
//    streamSelect02.print()

    //Union
//    val stream01 = env.readTextFile("test00.txt").flatMap(item =>item.split(" "))
//    val stream02 = env.readTextFile("test01.txt").flatMap(item =>item.split(" "))
//    val streamUnion = stream01.union(stream02)
//    streamUnion.print()


    env.execute("Transformation01")
  }

}
