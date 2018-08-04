package util;

import exception.InvalidRandomRangeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Random {

    public static int randomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static long randomLongInRange(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    public static float randomFloatInRange(float min, float max) {
        return ThreadLocalRandom.current().nextFloat() * (max - min) + min;
    }



    public static List<Long> uniqueRandomLongsInRange(long min, long max, int count) {

        List<Long> res = LongStream.range(min, max).boxed().collect(Collectors.toList());
        Collections.shuffle(res);
        return res.subList(0, count);
    }

    public static List<Integer> uniqueRandomIntsInRange(int min, int max, int count) throws InvalidRandomRangeException {

        if(min == max)
            return Collections.nCopies(count, 0);
        if(max - min < count)
            throw new InvalidRandomRangeException();

        List<Integer> res = IntStream.range(min, max).boxed().collect(Collectors.toList());
        Collections.shuffle(res);
        return res.subList(0, count);
    }

    /*public static List<Float> uniqueRandomFloatsInRange(float min, float max, int count) {

        List<Float> res = new ArrayList<>((int) (max - min));
        float step = (max - min) / count;
        for (float i = min; i < max - min; i+=) {
            res.add(i);
        }
        Collections.shuffle(res);
        return res.subList(0, count);
    }*/

}
