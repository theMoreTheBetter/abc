package highlevelrest;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.transport.TransportRequestOptions;

import java.io.IOException;

public class Test01 {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        GetRequest getRequest = new GetRequest("test","user","3");
//        client.exists(getRequest, TransportRequestOptions.);
        client.close();
    }
}
