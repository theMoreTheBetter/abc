package test01;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class ReadDataSourceTest {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("SPARK").getOrCreate();

        //parquet
        Dataset<Row> parquetDataSet = sparkSession.read().parquet("");
        parquetDataSet.select("name","age").write().save("");
        //JSON
        Dataset<Row> jsonDataSet = sparkSession.read().json("");
        jsonDataSet.select("name","age").write().save("");

        //Custom
        Dataset<Row> customDataSet = sparkSession.read().format("json").json("");
        customDataSet.select("","").write().format("json").save("");
        customDataSet.select("","").write().mode(SaveMode.Append).save("");

        //读取JDBC数据(方式一)
        Dataset<Row> jdbc1DataSet = sparkSession.read().format("jdbc").option("url","jdbc:mysql:dbserver")
                                                                    .option("user","root")
                                                                    .option("password","root")
                                                                    .option("dbtable","sys_alarm").load();
        //数据保存到JDBC源
        jdbc1DataSet.write().format("jdbc")
                .option("url","jdbc:mysql:dbserver")
                .option("user","root")
                .option("password","root")
                .option("dbtable","sys_alarm")
                .save();

        //读取JDBC数据(方式二)
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","root");
        Dataset<Row> jdbc2DataSet = sparkSession.read().jdbc("jdbc:mysql:dbserver","sys_alarm",properties);
        //第二种方式把数据保存到JDBC源
        jdbc2DataSet.write()
                .jdbc("jdbc:mysql:dbserver","sys_alarm",properties);

        //第三种方式:指定写的时候创建表列的数据类型
        jdbc2DataSet.write()
                .option("createTableColumnTypes", "name CHAR(64), comments VARCHAR(1024)")
                .jdbc("jdbc:mysql:dbserver","sys_alarm",properties);

    }

}
