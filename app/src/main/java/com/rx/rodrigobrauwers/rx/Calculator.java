package com.rx.rodrigobrauwers.rx;


import java.util.ArrayList;
import java.util.Collection;
import android.content.Intent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rodrigobrauwers on 04/12/17.
 */

public class Calculator {

    public static double averageDistance(double x1, double y1, double x2, double y2, double x3, double y3) {
        double d1 = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
        double d2 = Math.sqrt(Math.pow(x3-x2, 2) + Math.pow(y3-y2, 2));
        double d3 = Math.sqrt(Math.pow(x1-x3, 2) + Math.pow(y1-y3, 2));
        return (d1 + d2 + d3) / 3f;
    }

    public static class Data {
        private Integer number, frequency;

        public Data(int number) {
            this.number = number;
        }
    }

    public static int mostPopularNumber(int[] values, int length) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Values cannot be null or empty");
        }

        if (values.length != length) {
            throw new IllegalArgumentException("Length does not matches");
        }

        Map<Integer, Data> frequencyMap = new HashMap<>();
        for (int value : values) {
            if (value < 1 || value > 5000) {
                throw new IllegalArgumentException("Value must be within [1, 5000]");
            }

            Data data = frequencyMap.getOrDefault(value, new Data(value));
            data.frequency++;
            frequencyMap.put(value, data);
        }

        List<Data> list = new ArrayList<>(frequencyMap.values());
        list.sort(new Comparator<Data>() {
            @Override
            public int compare(Data d1, Data d2) {
                return d2.frequency.compareTo(d1.frequency);
            }
        });

        Data d1 = list.get(0);
        if (length > 1) {
            Data d2 = list.get(1);
            return  d1.number < d2.number ? d1.number : d2.number;
        }
        else {
            return d1.number;
        }
    }

}
