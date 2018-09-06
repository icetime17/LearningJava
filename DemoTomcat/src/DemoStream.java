import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Map;

public class DemoStream {
    public static void main(String args[]) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "abc", "abcdefg");
        List<String> filtered = strings.stream()
                .filter(string -> !string.isEmpty())
                .collect(Collectors.toList());
        System.out.println(filtered);

        List<String> length7 = strings.stream()
                .filter(string -> string.length()==7)
                .collect(Collectors.toList());
        System.out.println(length7);

        int count1 = (int) strings.stream()
                .filter(string -> string.isEmpty())
                .count();
        System.out.println(count1);


        Random random = new Random();
        random.ints()
                .limit(10)
                .forEach(System.out::println);


        // 使用Collectors将流转换成集合和聚合元素. 可用于返回列表或字符串
        List<Integer> numbers = Arrays.asList(3,2,2,3,7,3,5);
        // 返回列表
        List<Integer> squaresList = numbers.stream()
                .map(i -> i*i)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(squaresList);

        // 返回字符串
        String squaresStr = strings.stream()
                .filter(string -> !string.isEmpty())
                .distinct()
                .collect(Collectors.joining("_"));
        System.out.println(squaresStr);


        random.ints()
                .limit(10)
                .sorted()
                .forEach(System.out::println);


        int count2 = (int) strings.parallelStream()
                .filter(string -> string.isEmpty())
                .count();
        System.out.println(count2);


        // 统计收集
        IntSummaryStatistics statistics = numbers.stream()
                .mapToInt(x -> x)
                .summaryStatistics();

        System.out.println("max: " + statistics.getMax());
        System.out.println("min: " + statistics.getMin());
        System.out.println("sum: " + statistics.getSum());
        System.out.println("avg: " + statistics.getAverage());
    }
}