import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void ass(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "yaomy");
        map.put("password", "123");

        JSONObject json = new JSONObject(map);
        System.out.println(json);
    }
}
