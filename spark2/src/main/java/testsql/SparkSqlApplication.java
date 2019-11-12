package testsql;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.date_add;

public class SparkSqlApplication {
    public static void main(String[] args) {
        SparkSession sparkSession =SparkSession.builder().master("local").appName("Java Spark SQL").getOrCreate();
        Dataset<Row> dataset= sparkSession.read().json("a.txt");
//        简单操作
//        dataset.show();
//        dataset.select("name").show();
//        dataset.select(col("name"),col("age").plus(1)).show();
//        dataset.select(col("age").gt(21)).show();
//        dataset.filter(col("age").gt(21)).show();
//        dataset.groupBy("age").count().show();

        //临时表Session 级别
//        dataset.createOrReplaceTempView("usertable");
//        Dataset<Row> userinfos = sparkSession.sql("select * from usertable");
//        userinfos.show();

        //全局临时视图存在于系统数据库的global_temp 中 spark程序生命周期
        try {
            //创建全局临时视图
            dataset.createGlobalTempView("usertable");
            //全局临时视图绑定到系统保存的数据库“global_temp”
            Dataset<Row> userinfos = sparkSession.sql("select * from global_temp.usertable");
            userinfos.show();
        } catch (AnalysisException e) {
            e.printStackTrace();
        }
    }
}
