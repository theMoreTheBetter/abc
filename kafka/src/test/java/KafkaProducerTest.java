import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerTest implements Runnable {

    private final KafkaProducer<String, String> producer;
    private final String topic;
    public KafkaProducerTest(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.101:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("group.id", "test");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
    }

    @Override
    public void run() {
//        String[] messes = {"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105601\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105602\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105603\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105604\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105605\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105606\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105607\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105608\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105609\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105610\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105611\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105612\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105613\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105614\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105615\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105616\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105617\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105618\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105619\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105620\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105621\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105622\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105623\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105624\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105625\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105626\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105627\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105628\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105629\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105630\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105631\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105632\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105633\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105634\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105635\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105636\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105637\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105638\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105639\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105640\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105641\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105642\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105643\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105644\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105645\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105646\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105647\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105648\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105649\"}}",
//        		"{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\"86105650\"}}"
//        		};
        try {
//            for(int i = 0;i < messes.length;i++) {
//                String messageStr="你好，发送的数据是===>>>: "+messes[i];
//                System.out.println(messageStr);
//                producer.send(new ProducerRecord<String, String>(topic, messes[i]));
//            }
        	
        	for(int i = 0; i < 10;i++) {
        		String str = "{\"table\":\"ENSEMBLE.MB_TRAN_HIST\",\"op_type\":\"I\",\"after\":{\"SEQ_NO\":\""+i+"\"}}";
        		String messageStr="你好，发送的数据是===>>>: "+str;
        		System.out.println(messageStr);
        		producer.send(new ProducerRecord<String, String>(topic, str));
        	}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
    
    public static void main(String args[]) {
        KafkaProducerTest test = new KafkaProducerTest("oggtest");
        Thread thread = new Thread(test);
        thread.start();
    }
}