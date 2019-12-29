package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.MbTranHist;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EsUtils {
    private static final Logger LOG = LoggerFactory.getLogger(EsUtils.class);
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final String GET = "GET";
    private static final String POST = "POST";
    String esServerHost = ("localhost:9200");

    public HttpHost[] getHttpHosts(){
        String schema = "http";
        List<HttpHost> hosts = new ArrayList<HttpHost>();
        String[] hostArray1 = esServerHost.split(",");
        for (String host : hostArray1) {
            String[] ipPort = host.split(":");
            HttpHost hostNew = new HttpHost(ipPort[0], Integer.valueOf(ipPort[1]), schema);
            hosts.add(hostNew);
        }
        HttpHost[] httpHosts = hosts.toArray(new HttpHost[]{});
        return httpHosts;
    }

    public RestClientBuilder initRestClientBuilder(){
        int MaxRetryTimeoutMillis = 300000;
        int ConnectTimeout = 5000;
        int SocketTimeout = 60000;

        RestClientBuilder builder = RestClient.builder(getHttpHosts());

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
        return builder;
    }

    public RestClient initRestClient(){
        RestClient restClient;
        restClient = initRestClientBuilder().build();
        restClient.setHosts(getHttpHosts());
        return restClient;
    }

    /**
     * 查看api信息
     * @throws Exception
     */
    public void CatApi() {
        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(GET,"/_cat");
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                LOG.info("查询Es Api successful.");
                LOG.info(EntityUtils.toString(response.getEntity()));
            }else {
                LOG.error("查询Es Api failed.");
            }
        } catch (IOException e) {
            LOG.error("查询Es Api failed.",e);
        }

    }

    /**
     * 创建索引(无配置文件)
     */
    public void CreateIndex(String indexName) {
        CreateIndex(indexName,null);
    }

    /**
     * 创建索引(带配置文件)
     * @throws Exception
     */
    public void CreateIndex(String indexName,String mappingPath) {
        try(RestClient restClient = initRestClient()) {
            Map<String, String> params = Collections.singletonMap("pretty", "true");
            Response response = null;

            if(null != mappingPath){
                String path = EsUtils.class.getClassLoader().getResource(mappingPath).getPath();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
                StringBuffer stringBuffer = new StringBuffer();
                String line = null;
                while ((line =bufferedReader.readLine())!=null){
                    stringBuffer.append(line+"\n\r");
                }
                HttpEntity entity = new NStringEntity(stringBuffer.toString(), ContentType.APPLICATION_JSON);
                response = restClient.performRequest(PUT,"/"+indexName,params, entity);
                if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    LOG.info("创建Es Index successful.\n" + EntityUtils.toString(response.getEntity()));
                }else {
                    LOG.error("创建Es Index failed.");
                }
            }else{
                response = restClient.performRequest(PUT,"/"+indexName);
            }

        } catch (IOException e) {
            LOG.error("创建索引失败",e);
        }

    }

    /**
     * 创建文档（不指定ID）
     * @throws Exception
     */
    public void CreateDocument(String indexName,String type,String json){
        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(POST,"/"+indexName+"/"+type, Collections.<String, String>emptyMap(),entity);
            LOG.info("创建文件成功:"+EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            LOG.error("创建文档失败",e);
        }
    }
    /**
     * 创建文档
     * @throws Exception
     */
    public void CreateDocument(String indexName,String type,String id,String json){
        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(PUT,"/"+indexName+"/"+type+"/" + id, Collections.<String, String>emptyMap(),entity);
            LOG.info("创建文件成功:"+EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            LOG.error("创建文档失败",e);
        }
    }

    public void CreateDocumentBulk(String indexName,String type, JSONArray str) {
        String requestHeader = "{ \"index\" : { \"_index\" : \""+indexName+"\", \"_type\" : \""+type+"\"} }";
        StringBuffer buffer = new StringBuffer();
        Object[] objects = str.toArray();

//        List<MbTranHist> list = new ArrayList<>();
//        MbTranHist m = new MbTranHist();
//        m.setSeqNo("老板带带我aaaaaaaaaaaaaaaaa");
//        list.add(m);
//        MbTranHist n = new MbTranHist();
//        n.setSeqNo("老板娘带带我bbbbbbbbbbbbbbb");
//        list.add(n);
//        JSONArray ja = JSON.parseArray(JSON.toJSONString(list));
        for (Object o:objects) {
            buffer.append(requestHeader).append("\n");
            buffer.append(o.toString()).append("\n");
        }
        StringEntity entity  = new StringEntity(buffer.toString(), ContentType.APPLICATION_JSON);
        entity.setContentEncoding("UTF-8");
        Response rsp = null;
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        try (RestClient restClient = initRestClient()) {
            rsp = restClient.performRequest("PUT", "/_bulk", params, entity);
            if (HttpStatus.SC_OK == rsp.getStatusLine().getStatusCode()) {
                System.out.println("Bulk successful.");
            } else {
                LOG.error("Bulk failed.");
            }
            System.out.println("Bulk response entity is : " + EntityUtils.toString(rsp.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据Id获取文档
     * @throws Exception
     */
    public String getDocument(String indexName,String type,String id){
        String endpoint = "/"+indexName+"/"+type+"/"+id;

        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(GET,endpoint);
            LOG.info("获取到的文档为：" + EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOG.error("获取文档失败：" + e);
        }
        return "";
    }

    /**
     * 根据指定条件获取文档
     * @throws Exception
     */
    public String QueryByJSON(String indexName,String type,String queryJson) {
        String method = "POST";
        String endpoint = "/"+indexName+"/"+type+"/_search?pretty";
//        queryJson="{\n" +
//                "\"query\":{\n" +
//                "\"match\":{\n" +
//                "\"accountingStatus\":\"false\"\n" +
//                "}\n" +
//                "}\n" +
//                "}";
        HttpEntity entity = new NStringEntity(queryJson, ContentType.APPLICATION_JSON);

        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
            LOG.info("获取到文档："+EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            LOG.error("获取文档失败:",e);
        }
        return "";
    }


    /**
     * 获取所有文档
     * @throws Exception
     */
    public String QueryAll(String indexName,String type) {
        String endpoint = "/"+indexName+"/"+type+"/_search";
        HttpEntity entity = new NStringEntity("{ \"query\": { \"match_all\": {} } }", ContentType.APPLICATION_JSON);

        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(POST,endpoint,Collections.<String, String>emptyMap(),entity);
            LOG.info("查询所有数据：" + EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOG.error("查询所有数据失败：",e);
        }
        return "";
    }

    /**
     * 更新数据
     * @throws Exception
     */
    public void UpdateByScript(String indexName,String type,String id,String modelJson) {
        String method = "POST";
        String endpoint = "/"+indexName+"/"+type+"/"+id+"/_update";
        String queryJson = "{" +
                "  \"doc\": {"
                +modelJson.substring(1,modelJson.length()-1)+
                "	}" +
                "}";
        HttpEntity entity = new NStringEntity(queryJson, ContentType.APPLICATION_JSON);

        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
            LOG.info("更新数据成功");
        } catch (IOException e) {
            LOG.error("更新数据失败");
        }
    }

    public void DeleteIndex(String IndexName) {
        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(DELETE,"/"+IndexName);
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                LOG.info("删除Es Index successful.");
            }else {
                LOG.error("删除Es Index failed.");
            }
        } catch (IOException e) {
            LOG.error("删除索引失败",e);
        }
    }

    public void DeleteIndexDocument(String IndexName,String type,String id) {
        try(RestClient restClient = initRestClient()) {
            Response response = restClient.performRequest(DELETE,"/"+IndexName+"/"+type +"/" + id);
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                LOG.info("删除Es Document successful.");
            }else {
                LOG.error("删除Es Document failed.");
            }
        } catch (IOException e) {
            LOG.error("删除索引数据失败",e);
        }
    }
}
