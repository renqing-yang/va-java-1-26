package week3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * request -> Tomcat(blocking server) thread pool
 *                  one thread pick up connection  -> DispatcherServlet (/*) -> handler mapping -> controller - > service
 *
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  JVM: stack + heap
 *
 *  Thread  1 - 1 Stack
 *
 *
 *  T1, stack1
 *  push..
 *  method2
 *  method1
 *
 *
 *  T2, stack2
 *  method1
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Stream api
 *
 *  list.stream().map(x -> x * 2).filter(x -> x > 2)
 *      .collect(Collectors.groupingBy(x -> x, Collectors.counting));
 *
 *
 *  ReferencePipeline(iterator) <-> ReferencePipeline(map function) <-> ReferencePipeline(filter) <-> ReferencePipeline(collect)
 *
 *  Sink1(iterator) -> Sink2(map) -> Sink3(filter) -> Sink4(collect)
 */
class MyStreamTest {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 2, 2, 2, 2);
        Map<Integer, Long> map1 = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Integer, Long> map2 = list.stream()
                .collect(
                            HashMap::new,
                            (m, obj) -> m.merge(obj, 1l, Long::sum),
                            (m1, m2) -> {}
                        );
        System.out.println(map1 == map2);
        System.out.println(map1.equals(map2));
    }
}

/**
 *
 */
class PassByValueExample {
    public static void main(String[] args) {
        //list1 ref address 00xx -> [arraylist address yybb]
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        //helper([arraylist address yybb])
        helper(list1);

        System.out.println(list1);

        String s1 = "abc"; //constant string pool
        String s2 = "abc";
        String s3 = new String("abc");

        System.out.println(s1 == s2); //true
        System.out.println(s2 == s3); //false
        System.out.println(s1.equals(s3));
        System.out.println(s1 == s3.intern());

        //new StringBuilder() to combine "abc" and "a"
        String s4 = "abc" + "a"; //how many objects  "abca"
        // do not write following code
        for(int i = 0; i < 10; i++) {
            s4 += "a";
        }
        //we should use StringBuilder
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            sb.append('a' + i);
        }
        String finalRes = sb.reverse().toString();

        System.out.println(
            """
                count freq: 
                ss
                ss
                ss
                s
            """
        );
        countFreq("aaaaabbc");
    }
    //list2 00yy -> [arraylist address yybb]
    public static void helper(List<Integer> list2) {
        //list2 00yy -> [new arraylist yyaa]
        list2 = new ArrayList<>();
    }

    public static void reverseString(String input) {
        if(input == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        //sol1
        String s1 = new StringBuilder(input).reverse().toString();

        //sol2
        char[] ch = input.toCharArray();
        for(int l = 0, r = ch.length - 1; l < r; l++, r--) {
            char tmp = ch[l];
            ch[l] = ch[r];
            ch[r] = tmp;
        }
        String s2 = new String(ch);
    }

    public static void countFreq(String input) {
        //sol1
        Map<Character, Long> map1 = input.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        //sol2
        Map<Character, Long> map2 = new HashMap<>();
        input.chars()
                .mapToObj(c -> (char)c)
                .forEach(c -> map2.merge(c, 1l, Long::sum));

        //sol3
        Map<Character, Long> map3 = new HashMap<>();
        for(int i = 0; i < input.length(); i++) {
            map3.merge(input.charAt(i), 1l, Long::sum);
        }

        //sol4
        Map<Character, Long> map4 = new HashMap<>();
        IntStream.range(0, input.length())
                .mapToObj(input::charAt)
                .forEach(c -> map4.merge(c, 1l, Long::sum));

        System.out.println(map1.equals(map2));
        System.out.println(map2.equals(map3));
        System.out.println(map3.equals(map4));
    }
}


