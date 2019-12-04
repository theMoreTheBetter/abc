package aa;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MyUDF extends UDF {

    //固定方法名称
    public int evaluate(int data){

        return data +5;
    }
}

/**
 * create function addFive as 'aa.MyUDF';
 */