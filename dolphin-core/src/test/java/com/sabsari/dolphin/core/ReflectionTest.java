package com.sabsari.dolphin.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.junit.Test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReflectionTest {

    @Test
    public void _test() {
        TestData data = new TestData();
        
        testFunc(data);
        
        System.out.println(data);
    }
    
    public static void testFunc(Object o) {
        Class<?> c = o.getClass();
        
        Field[] fields = c.getDeclaredFields();
        
        for (Field f : fields) {
            if (f.getType() == String.class) {
                try {
                    Method getter = c.getDeclaredMethod(getGetter(f.getName()));
                    Method setter = c.getDeclaredMethod(getSetter(f.getName()), String.class);
                    
                    String value = (String) getter.invoke(o);
                    setter.invoke(o, trim(value));
                }
                catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String trim(String str) {
        if (str == null)
            return null;
        
        String t = str.trim();
        
        if (t.length() == 0)
            return null;
        else
            return t;
    }
    
    public static String getGetter(String name) {
        return "get" + WordUtils.capitalize(name);
    }
    
    public static String getSetter(String name) {
        return "set" + WordUtils.capitalize(name);
    }
    
    @Getter
    @Setter
    @ToString
    public static class TestData {
        
        private int a;
        
        private Map<String, String> map;
        
        private String key;
        
        private String id;
        
        private String ci;
        
        private String name;
        
        private String email;
        
        public TestData() {
            a = 1;
            map = new HashMap<String, String>();
            key = "key";
            id = null;
            ci = "";
            name = "  ";
            email = " email   ";
        }
    }
}