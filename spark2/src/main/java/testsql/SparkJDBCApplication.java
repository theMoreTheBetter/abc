package testsql;

import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class SparkJDBCApplication {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("Spark SQL ").getOrCreate();
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","123456");
        sparkSession.read().jdbc("jdbc:mysql:dbserver","sys_alarm",properties);
    }
}
