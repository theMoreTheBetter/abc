package main;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class TestFastJson {
    public static void main(String[] args) {
        ObjClass obj = new ObjClass("小明", 20, new Date());
//转换为json字符串
        String json1 = JSON.toJSON(obj).toString();
        String json2 = FastJsonUtil.objToJson(obj);
        System.out.println(json1);
        System.out.println(json2);
//转换为Obj对象
        System.out.println(JSON.parseObject(json1,ObjClass.class));
        System.out.println(FastJsonUtil.jsonToObj(json2, ObjClass.class));
    }
}

class ObjClass{
    private String name;
    private int age;
    private Date cretaeTime;

    public ObjClass(){//想要被反射一定要有默认构造器

    }
    public ObjClass(String name, int age, Date cretaeTime) {
        this.name = name;
        this.age = age;
        this.cretaeTime = cretaeTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getCretaeTime() {
        return cretaeTime;
    }
    public void setCretaeTime(Date cretaeTime) {
        this.cretaeTime = cretaeTime;
    }
    @Override
    public String toString() {
        return "ObjClass [name=" + name + ", age=" + age + ", cretaeTime="
                + cretaeTime + "]";
    }
}

class FastJsonUtil {
    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat;
    static {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        mapping.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    /**
     * 对象转换为Json
     */
    public static String objToJson(Object obj) {
        String str = JSON.toJSONString(obj, mapping,SerializerFeature.WriteMapNullValue);
        return str;
    }

    /**
     * Json转换为对象
     */
    public static <T> T jsonToObj(String str, Class<T> clazz) {
        T T = JSON.parseObject(str, clazz);
        return T;
    }
}