import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Elasticserach RestClient示例
 * @author fendo
 *
 */
public class Rest1 {

    private static RestClient restClient;

    @Before
    public void getRest(){
        String esServerHost = ("localhost:9200");
        int MaxRetryTimeoutMillis = 300000;
        int ConnectTimeout = 5000;
        int SocketTimeout = 60000;
        String type = "test";
        String schema = "http";

        List<HttpHost> hosts = new ArrayList<HttpHost>();
        String[] hostArray1 = esServerHost.split(",");
        for (String host : hostArray1) {
            String[] ipPort = host.split(":");
            HttpHost hostNew = new HttpHost(ipPort[0], Integer.valueOf(ipPort[1]), schema);
            hosts.add(hostNew);
        }
        HttpHost[] httpHosts = hosts.toArray(new HttpHost[]{});

        RestClientBuilder builder = RestClient.builder(httpHosts);

        // 设置请求的回调函数
        //1.设置连接超时时间，单位毫秒。
        //2.设置请求获取数据的超时时间，单位毫秒。如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
        //3.设置同一请求最大超时重试时间（以毫秒为单位）。
        builder  =  builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder.setConnectTimeout(ConnectTimeout).setSocketTimeout(SocketTimeout);
            }
        }).setMaxRetryTimeoutMillis(MaxRetryTimeoutMillis);

        //设置默认请求标头，它将与每个请求一起发送。请求时标头将始终覆盖任何默认标头。
        Header[] defaultHeaders = new Header[]{new BasicHeader("Accept", "application/json"),
                new BasicHeader("Content-type", "application/json")};
        builder.setDefaultHeaders(defaultHeaders);

        restClient = builder.build();
        restClient.setHosts(httpHosts);
    }



    /**
     * 查看api信息
     * @throws Exception
     */
    @Test
    public void CatApi() throws Exception{
        String method = "GET";
        String endpoint = "/_cat";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void CreateIndex() throws Exception{
        String method = "PUT";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建文档
     * @throws Exception
     */
    @Test
    public void CreateDocument()throws Exception{

        String method = "PUT";
        String endpoint = "/test-index/test/2";
        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"user\" : \"dddzhhh\",\n" +
                        "    \"post_date\" : \"2019-11-15T14:12:12\",\n" +
                        "    \"message\" : \"trying out Elasticsearch Client\"\n" +
                        "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint, Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 获取文档
     * @throws Exception
     */
    @Test
    public void getDocument()throws Exception{
        String method = "GET";
        String endpoint = "/test-index/test/1";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    /**
     * 查询所有数据
     * @throws Exception
     */
    @Test
    public void QueryAll() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID获取
     * @throws Exception
     */
    @Test
    public void QueryByField() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"user\": \"kimchy\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     * @throws Exception
     */
    @Test
    public void UpdateByScript() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "	}\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    @Test
    public void DeleteIndex() throws IOException {
        String method = "DELETE";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    //close()是tear down的方法,在销毁时执行,关闭连接
    public void close() throws Exception {
        if (restClient != null) {
            try {
                restClient.close();
            } catch (Exception e1) {
            }
        }
    }
}