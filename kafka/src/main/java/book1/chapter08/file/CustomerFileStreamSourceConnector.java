package book1.chapter08.file;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFileStreamSourceConnector extends SourceConnector {

    // 定义主题配置变量
    public static final String TOPIC_CONFIG = "topic";
    // 定义文件配置变量
    public static final String FILE_CONFIG = "file";
    // 实例化一个配置对象
    private static final ConfigDef CONFIG_DEF = new ConfigDef().define(FILE_CONFIG, Type.STRING, Importance.HIGH, "Source filename.").define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, "The topic to publish data to");
    // 声明文件名变量
    private String filename;
    // 声明主题变量
    private String topic;

    /** 获取版本. */
    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }

    /** 开始初始化. */
    @Override
    public void start(Map<String, String> map) {
        filename = map.get(FILE_CONFIG);
        topic = map.get(TOPIC_CONFIG);
        if (topic == null || topic.isEmpty())
            throw new ConnectException("FileStreamSourceConnector configuration must include 'topic' setting");
        if (topic.contains(","))
            throw new ConnectException("FileStreamSourceConnector should only have a single topic when used as a source.");
    }

    /** 实例化输入类. */
    @Override
    public Class<? extends Task> taskClass() {
        return CustomerFileStreamSourceTask.class;
    }

    /** 获取配置信息. */
    @Override
    public List<Map<String, String>> taskConfigs(int i) {
        ArrayList<Map<String, String>> configs = new ArrayList<>();
        Map<String, String> config = new HashMap<>();
        if (filename != null)
            config.put(FILE_CONFIG, filename);
        config.put(TOPIC_CONFIG, topic);
        configs.add(config);
        return configs;
    }

    @Override
    public void stop() {

    }

    /** 获取配置对象. */
    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }
}
