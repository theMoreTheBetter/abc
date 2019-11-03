package test01;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.codehaus.janino.Java;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

public class OperatesTest implements Serializable {

    private static final Pattern PATTERN = Pattern.compile(" ");

    //操作map
    public void testMap(JavaSparkContext javaSparkContext){
        JavaRDD<Integer> num = javaSparkContext.parallelize(Arrays.asList(1,2,3,4,5,6,7,8,9));

        JavaRDD<Integer> result = num.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1*v1;
            }
        });
        System.out.println(StringUtils.join(result.collect(),","));
    }

    //操作flatMap
    public void testFlatMap(JavaSparkContext javaSparkContext){
        JavaRDD<String> lines = javaSparkContext.parallelize(Arrays.asList("hello word","spark","shark","tomcat"));

        JavaRDD<String> flatMapResult = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(PATTERN.split(s)).iterator();
            }
        });

        System.out.println("============>" + flatMapResult.first());
    }

    //操作reduce
    public void testReduce(JavaSparkContext javaSparkContext){
        JavaRDD<Integer> nums = javaSparkContext.parallelize(Arrays.asList(1,2,3,4,5,6,7,8,9));
        Integer sum = nums.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) throws Exception {
                return i1 + i2;
            }
        });
        System.out.println("reduce result===========>" + sum);
    }

    //pairRDD
    public void testPairRdd(JavaSparkContext javaSparkContext){
        JavaRDD<String> rdd = javaSparkContext.parallelize(Arrays.asList("amazing","jingdong","taobao"));

        PairFunction<String,String,String> pairFunction = new PairFunction<String, String, String>() {
            @Override
            public Tuple2<String, String> call(String s) throws Exception {
                return new Tuple2<>(s.split(" ")[0], s);
            }
        };

        JavaPairRDD javaPairRDD = rdd.mapToPair(pairFunction);

        /*合并含有相同键的值*/
        javaPairRDD.reduceByKey(new Function2<String,String,String>() {
            @Override
            public String call(String s1, String s2) throws Exception {
                return s1 + s2;
            }
        });

        /*相同key的元素进行分组*/
        javaPairRDD.groupByKey();

        /*对pair中的每个值进行应用*/
        javaPairRDD.mapValues(new Function<String,Object>() {
            @Override
            public Object call(String s) throws Exception {
                return s + " yesssss~~~";
            }
        });

        /*返回只包含键的RDD*/
        javaPairRDD.keys();

        /*返回只包含值的RDD*/
        javaPairRDD.values();

        /*返回根据键排序的RDD*/
        javaPairRDD.sortByKey();

    }

    /*针对多个pairRDD元素的操作*/
    public static void runPair(JavaSparkContext sparkContext) {

        JavaRDD<String> rdd = sparkContext.parallelize(Arrays.asList("test", "java", "python"));
        JavaRDD<String> otherRDD = sparkContext.parallelize(Arrays.asList("golang", "php", "hadoop"));

        PairFunction<String, String, String> pairFunction = new PairFunction<String, String, String>() {
            @Override
            public Tuple2<String, String> call(String s) {
                return new Tuple2<>(s.split(" ")[0], s);
            }
        };
        JavaPairRDD<String, String> pairRDD = rdd.mapToPair(pairFunction);
        JavaPairRDD<String, String> pairRDDOther = otherRDD.mapToPair(pairFunction);

        //创建好两个PairRDD之后开始操作

        //删除 ==pairRDD== 中键与pairRDDOther相同的元素
        JavaPairRDD<String, String> subRDD = pairRDD.subtractByKey(pairRDDOther);

        //内连接 inner join 查询
        JavaPairRDD<String, Tuple2<String, String>> jsonRDD = pairRDD.join(pairRDDOther);

        //右连接 right join 查询   //TODO 此处我理解是可以为null的二元组
        JavaPairRDD<String, Tuple2<Optional<String>, String>> rightRDD = pairRDD.rightOuterJoin(pairRDDOther);

        //左连接 left join 查询
        JavaPairRDD<String, Tuple2<String, Optional<String>>> leftRDD = pairRDD.leftOuterJoin(pairRDDOther);

        //将两个RDD中有相同键的数据分组  //TODO 此处我理解是迭代器
        JavaPairRDD<String, Tuple2<Iterable<String>, Iterable<String>>> groupRDD = pairRDD.cogroup(pairRDDOther);

        //pairRDD也可以使用RDD的函数
        //筛选length小于20的元素
        Function<Tuple2<String, String>, Boolean> filterRDD = new Function<Tuple2<String, String>, Boolean>() {
            @Override
            public Boolean call(Tuple2<String, String> v1) {
                return (v1._2.length() < 20);
            }
        };
        JavaPairRDD<String, String> filter = pairRDD.filter(filterRDD);

        JavaRDD<String> wordCount = sparkContext.parallelize(Arrays.asList("1", "2", "3", "4", "5"));

        //返回一个可以迭代的集合
        JavaRDD<String> c = wordCount.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String v1) throws Exception {
                return Arrays.asList(v1.split(" ")).iterator();
            }
        });

        //现在的数据是 1,2,3,4,5
        JavaPairRDD<String, Integer> result = c.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
                //此时的数据是 {1,1},{2,1},{3,1},{4,1}...
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        /*还可以通过countByValue快速实现单词计数*/
        c.countByValue();

        JavaPairRDD<String, Integer> javaPairRDD = sparkContext.parallelizePairs(Arrays.asList(new Tuple2<>("alert", 10)));
        javaPairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return null;
            }
        }, 1);
    }

    public static void main(String[] args) {
        OperatesTest ot = new OperatesTest();
        SparkConf sparkConf = new SparkConf().setAppName("operatestest").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);


//        ot.testMap(sparkContext);
//        ot.testFlatMap(sparkContext);

//        ot.testReduce(sparkContext);

        ot.testPairRdd(sparkContext);

//        JavaRDD<Integer> javaRDD = sparkContext.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
//        RddAvg rddAvg = new RddAvg(0, 0);
//        RddAvg result = javaRDD.aggregate(rddAvg, rddAvg.avgFunction2, rddAvg.rddAvgFunction2);
//        System.out.println(result.avg());
    }

}

class RddAvg implements Serializable{
    private Integer total;
    private Integer num;

    public RddAvg(Integer total, Integer num) {
        this.total = total;
        this.num = num;
    }

    public double avg() {
        return total / num;
    }

    Function2<RddAvg, Integer, RddAvg> avgFunction2 = new Function2<RddAvg, Integer, RddAvg>() {
        @Override
        public RddAvg call(RddAvg v1, Integer v2) {
            v1.total += v2;
            v1.num += 1;
            return v1;
        }
    };

    Function2<RddAvg,RddAvg,RddAvg> rddAvgFunction2 = new Function2<RddAvg, RddAvg, RddAvg>() {
        @Override
        public RddAvg call(RddAvg v1, RddAvg v2) {
            v1.total += v2.total;
            v1.num += v2.num;
            return v1;
        }
    };
}
