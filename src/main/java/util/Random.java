package util;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
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

    public static List<Long> uniqueRandomLongsInRange(long min, long max, long count) {

        List<Long> res = LongStream.range(min, max).boxed().collect(Collectors.toList());
        Collections.shuffle(res);
        return res;
    }

}
