package testsql;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SparkSetApplication {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("SparkSqlTest").getOrCreate();

//        Person person = new Person("Spaaark",10);
//        Encoder<Person> encoder = Encoders.bean(Person.class);
//        Dataset<Person> dataset = sparkSession.createDataset(Collections.singletonList(person),encoder);
//        dataset.show();
//----------------------------------------------------------------------------------------------------------------------
        /*常见类型的编码器*/
//        Encoder<Integer> integerEncoder = Encoders.INT();
//        Dataset<Integer> integerDataset = sparkSession.createDataset(Arrays.asList(1,2),integerEncoder);
//        Dataset<Integer> result = integerDataset.map(new MapFunction<Integer, Integer>() {
//            @Override
//            public Integer call(Integer value) {
//                return value+1;
//            }
//        },integerEncoder);
//        result.collect();
//        result.show();

//----------------------------------------------------------------------------------------------------------------------
        /*通过提供一个类，可以将数据流转换为数据集。基于名称的映射*/
//        String url = "a.txt";
//        Dataset<Person> personDataset = sparkSession.read().json(url).as(encoder);
//        personDataset.show();

//反射的方式推导出schema----------------------------------------------------------------------------------------------------------------------
//        JavaRDD<Person> personJavaRDD = sparkSession.read().textFile("b.txt").toJavaRDD().map(
//                new Function<String, Person>() {
//                    @Override
//                    public Person call(String s) throws Exception {
//                        String[] param = s.split(":");
//                        Person person = new Person();
//                        person.setName(param[0]);
//                        person.setAge(Integer.valueOf(param[1].trim()));
//                        return person;
//                    }
//                }
//        );
//        //转换成DataFrame
//        Dataset<Row> personDataset = sparkSession.createDataFrame(personJavaRDD,Person.class);
//
//        //创建临时视图
//        personDataset.createOrReplaceTempView("user");
//        Dataset<Row> result = sparkSession.sql("SELECT name,age FROM user");
//        Encoder<String> encoder = Encoders.STRING();
//
//        //第一种方式:通过下标获取value
//        Dataset<String> dataset = result.map(new MapFunction<Row, String>() {
//            @Override
//            public String call(Row value) {
//                return value.getString(0);
//            }
//        },encoder);
//        dataset.show();
//
//        //第二种方式:通过字段获取value
//        Dataset<String> fieldValue = result.map(new MapFunction<Row, String>() {
//            @Override
//            public String call(Row value) {
//                return value.getAs("name");
//            }
//        },encoder);
//        fieldValue.show();

//代码显式的构造schema----------------------------------------------------------------------------------------------------------------------
//        //创建普通的JavaRDD
//        JavaRDD<String> javaRDD = sparkSession.sparkContext().textFile("b.txt", 1).toJavaRDD();
//        //字符串编码的模式
//        String schema = "name:age";
//
//        //根据模式的字符串生成模式
//        List<StructField> structFieldList = new ArrayList<>();
//        for (String fieldName : schema.split(":")) {
//            StructField structField = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
//            structFieldList.add(structField);
//        }
//        StructType structType = DataTypes.createStructType(structFieldList);
//
//        JavaRDD<Row> rowJavaRDD = javaRDD.map(new Function<String, Row>() {
//            @Override
//            public Row call(String v1) {
//                String[] attirbutes = v1.split(":");
//                return RowFactory.create(attirbutes[0], attirbutes[1].trim());
//            }
//        });
//
//        //将模式应用于RDD
//        Dataset<Row> dataset = sparkSession.createDataFrame(rowJavaRDD, structType);
//
//        //创建临时视图
//        dataset.createOrReplaceTempView("user");
//        Dataset<Row> result = sparkSession.sql("select * from user");
//        result.show();

    }
}
