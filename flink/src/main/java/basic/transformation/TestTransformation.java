package basic.transformation;


import basic.sinkMysql.SinkToMySQL;
import basic.sourceMysql.Student;
import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;

import java.util.Properties;

public class TestTransformation {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");

        SingleOutputStreamOperator<Student> student = env.addSource(new FlinkKafkaConsumer011<>(
                "testtopic1",   //这个 kafka topic 需要和上面的工具类的 topic 一致
                new SimpleStringSchema(),
                props)).setParallelism(1)
                .map(string -> JSON.parseObject(string, Student.class));

        //map
        SingleOutputStreamOperator<Student> map= student.map(new MapFunction<Student, Student>() {
            @Override
            public Student map(Student student) throws Exception {
                Student s1 = new Student();
                s1.id =student.id;
                s1.name = student.name;
                s1.password = student.password;
                s1.age = student.age + 5;
                return s1;
            }
        });
//        map.print();

        //flatmap 采用一条记录并输出零个，一个或多个记录。
        SingleOutputStreamOperator<Student> flatMap = student.flatMap(new FlatMapFunction<Student, Student>() {
            @Override
            public void flatMap(Student student, Collector<Student> collector) throws Exception {
                if(student.id % 2 ==0)
                    collector.collect(student);
            }
        });
//        flatMap.print();

        //filter 条件判断出结果
        SingleOutputStreamOperator<Student> filter = student.filter(new FilterFunction<Student>() {
            @Override
            public boolean filter(Student student) throws Exception {
                if(student.id > 7)
                    return true;
                return false;
            }
        });

        //keyBy
        KeyedStream<Student,Integer> keyBy = student.keyBy(new KeySelector<Student,Integer>(){

            @Override
            public Integer getKey(Student student) throws Exception {
                return student.age;
            }
        });

        //reduce 返回单个的结果值，并且 reduce 操作每处理一个元素总是创建一个新值
        SingleOutputStreamOperator<Student> reduce = keyBy.reduce(new ReduceFunction<Student>() {
            @Override
            public Student reduce(Student t1, Student t2) throws Exception {
                Student student = new Student();
                student.name = t1.name + t2.name;
                student.id = (t1.id + t2.id)/2;
                student.password = t1.password +t2.password;
                student.age = t1.age + t2.age;
                return student;
            }
        });

        //fold  通过将最后一个文件夹流与当前记录组合来推出 KeyedStream。 它会发回数据流。
        //DataStream API 支持各种聚合，例如 min，max，sum 等。 这些函数可以应用于 KeyedStream 以获得 Aggregations 聚合。

        reduce.addSink(new SinkToMySQL()); //数据 sink 到 mysql

        env.execute("Flink add sink");
    }
}
