package test01;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class ShowBadLogs {

    /**
     * 对日志进行 转换操作和行动操作
     */
    public void log(JavaSparkContext sparkContext) {
        JavaRDD<String> inputRDD = sparkContext.textFile("/home/pi/dzhtmp/log");
        JavaRDD<String> errorRDD = inputRDD.filter(
                new Function<String, Boolean>() {
                    @Override
                    public Boolean call(String v1) throws Exception {
                        return null;
                    }}
        );
        long errorRDDCount = errorRDD.count();
        System.out.println("errorRDD count is " + errorRDDCount);
        for (String rddLine : errorRDD.take(10)) {
            System.out.println("errorRDD 数据is " + rddLine);
        }
    }

    /**
     * 使用显示内部类替代匿名内部类,进行函数传递
     */
    class ContainsError implements Function<String, Boolean> {
        @Override
        public Boolean call(String v1) throws Exception {
            return v1.contains("ERROR");
        }
    }

    /**
     * 对 ContainsError 具体类改造,改的更加灵活
     */
    class ContainsErrorDev implements Function<String, Boolean> {
        private String query;

        public ContainsErrorDev(String query) {
            this.query = query;
        }

        public Boolean call(String v1) {
            return v1.contains(query);
        }
    }
}
