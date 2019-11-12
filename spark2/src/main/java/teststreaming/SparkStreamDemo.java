package teststreaming;

import javafx.util.Duration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInfo;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class SparkStreamDemo {

    public static void main(String[] args) {
        SparkConf sparkConf =new SparkConf().setMaster("local[2]").setAppName("Spark Streaming Test");
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(1));
        JavaReceiverInputDStream<String> dStream = javaStreamingContext.socketTextStream("localhost", 8080);
        //sparkstreaming提供了一个高级抽象叫做Dstream,代表一个连续的数据流
        JavaDStream<String> errorLine = dStream.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String s) throws Exception {
                return s.contains("error");
            }
        });
        errorLine.print();

        try {
            //开始计算
            javaStreamingContext.start();
            //等待计算完成
            javaStreamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
