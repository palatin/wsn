package util;

import javafx.util.Pair;

import java.util.Collections;
import java.util.List;

public class ArrayHelper {


    public static <E extends Comparable> Pair<Integer, E> getSmallestElement(List<E> array) {
        int index = array.indexOf(Collections.min(array));
        return new Pair<>(index, array.get(index));
    }

    public static <E extends Comparable> Pair<Integer, E> getLargestElement(List<E> array) {
        int index = array.indexOf(Collections.max(array));
        return new Pair<>(index, array.get(index));
    }
}
