package org.example._backend.util;

import java.util.ArrayList;
import java.util.List;

public class StringSpiltToList {
    public static List<String> spiltLists(String value) {
        String[] array = value.split(",");
        List<String> list = new ArrayList<>();
        for (String id : array) {
            list.add(id);
        }
        return list;
    }
}
