package main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.MbTranHist;
import util.EsUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)  {
        EsUtils esUtils = new EsUtils();
        List<MbTranHist> list = new ArrayList<>();
        MbTranHist m = new MbTranHist();
        m.setSeqNo("老板带带我aaaaaaaaaaaaaaaaa");
        list.add(m);
        MbTranHist n = new MbTranHist();
        n.setSeqNo("老板娘带带我bbbbbbbbbbbbbbb");
        list.add(n);
        JSONArray ja = JSON.parseArray(JSON.toJSONString(list));
//        esUtils.CreateDocumentBulk("mbtranlist","mytype",ja);
    }
}
