package com.vitily.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化相关
 * @author lether
 *
 */
public class SerializeUtil {
     public static byte[] serialize(Object object) {
          ObjectOutputStream oos;
           ByteArrayOutputStream baos;
           try {
                // 序列化
               baos = new ByteArrayOutputStream();
               oos = new ObjectOutputStream(baos);
               oos.writeObject(object);
                return baos.toByteArray();
          } catch (Exception e) {
               e.printStackTrace();
          }
           return null;
    }

     public static Object unserialize( byte[] bytes) {
          ByteArrayInputStream bais;
           try {
                // 反序列化
               bais = new ByteArrayInputStream(bytes);
               ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
          } catch (Exception e) {
               e.printStackTrace();
          }
           return null;
    }
}
