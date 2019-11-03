package test01;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class SparkSqlTest {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local")
                .appName("Java Spark SQL")
                .getOrCreate();

        Dataset<Row> dataset = sparkSession.read().json("D:\\a.txt");
        //只返回name字段
        dataset.select("name").show();
        //返回两个字段,所有age的value+1
        dataset.select(col("name"),col("age").plus(1)).show();
        //选择age大于21岁的人
        dataset.filter(col("age").gt(21)).show();
        //分组聚合,group age
        dataset.groupBy("age").count().show();
        //显示
        dataset.show();


        /*以编程的方式运行SQL查询*/
        //注册临时表
        dataset.createOrReplaceTempView("user");
        Dataset<Row> users = sparkSession.sql("SELECT * FROM user");

        JavaRDD<Object> toText = users.toJavaRDD().map((Function<Row, Object>) v1 -> v1.getString(0));
        users.show();

    }
}
