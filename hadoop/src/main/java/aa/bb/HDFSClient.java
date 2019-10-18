package aa.bb;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSClient {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.1.128:9000");
        FileSystem fs = FileSystem.get(conf);
        fs.mkdirs(new Path("/0529/dashen"));
        fs.close();
        System.out.println("over");

    }
}
