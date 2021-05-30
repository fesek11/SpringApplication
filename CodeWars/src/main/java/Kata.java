import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Kata {

    public static void main(String[] args) {
//        solution("abcdefghii");
//        System.out.println(res(665862));
//        System.out.println(validPhoneNumber("(123) 456-7890"));
//        System.out.println(getPeaks(new int[]{0, 1, 2, 5, 1, 0}).toString()); //Pick peaks
//        System.out.println(isIsogram("Abcdefgh"));
//        System.out.println(findNextSquareSecondTry(134));
        /*String[] s1 = new String[]{"hoqq", "bbllkw", "oox", "ejjuyyy", "plmiis", "xxxzgpsssa", "xxwwkktt", "znnnnfqknaz", "qqquuhii", "dvvvwz"};
        String[] s2 = new String[]{"cccooommaaqqoxii", "gggqaffhhh", "tttoowwwmmww"};
        System.out.println(mxdiflgSecondTry(s1, s2));*/
//        System.out.println(Arrays.toString(sortArraySecondTry(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})));
//        System.out.println(numberSecondTry(Arrays.asList("a", "b", "c")));
//        System.out.println(findUniqSecondTry(new double[]{2, 1, 1, 1, 1, 1})); //Find the unique number
/*        String[] a = {"AA", "Bb", "Aaa", "a", "AaAa", "Aaa"};
//        String[] b = {"Abc", "aCb", "bb", "Bac", "Cab", "bac"};
        findUniqString(a);*/
        System.out.println(camelCase("camelCasingTest"));

    }

    public static String camelCase(String input) {

        // Создание Pattern объекта
        Pattern r = Pattern.compile("\\D[A-Z]");
        String[] arr = Stream.of(input).distinct();
        // Создание matcher объекта
        Matcher m = r.matcher(input);
        int start = 0;
        String camel;
        while (m.find()) {
            start =  m.start();
            camel = " " + input.indexOf(start);
            input.replaceFirst("\\D[A-Z]", camel);
            System.out.println(start);
            System.out.println(camel);
        }
        System.out.println(input);

        return null;
    }


        public static String findUniqString(String[] strings) {

            String notSame = strings[strings[0].toLowerCase().equals(strings[2].toLowerCase().indexOf(0)) ? 0 : 2];


            return strings[0];
        }

        public static double findUniq(double arr[]) {
        double notSameF = 0;
        double notSameD = 0;
        Arrays.sort(arr);
        int i = 0;
        for (; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                notSameF = arr[i];
                notSameD = arr[i + 1];
                break;
            }
        }
        System.out.println(notSameF + " "+ notSameD);
//        System.out.println(arr[arr.length - 1 - i]);
        if (notSameF != arr[arr.length - 1 - i]) {
            return notSameF;
        }
        return notSameD;



    }
    public static double findUniqSecondTry(double arr[]) {
        double el = arr[arr[0] == arr[2] ? 0 : 2];
        for (double d :
                arr) {
            if (el != d) {
                return d;
            }
        }
        throw new RuntimeException("no unique");
    }
    public static List<String> number(List<String> lines) {
        // => ["1: a", "2: b", "3: c"]

            for (int i = 1; i <= lines.size(); i++) {
                lines.set(i - 1, "\"" + i + ": " + lines.get(i - 1) + "\"");
            }

        return lines;
    }
    public static List<String> numberSecondTry(List<String> lines) {
        // => ["1: a", "2: b", "3: c"]
        int i =0 ;
        for (String s :
                lines) {
            lines.set(i, ++i + ": " + lines.get(i - 1));
        }

        return lines;
    }

    public static int[] sortArray(int[] array) {
        List<Integer> stackOdd = new ArrayList<>();
        int arr[] = new int[array.length];
        List<Integer> listArr = new ArrayList<>();


        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                listArr.add(i, array[i]);
            } else {
                stackOdd.add(array[i]);
                listArr.add(i, null);
            }
        }

        Collections.sort(stackOdd);
        Collections.reverse(stackOdd);

        for (int i = 0, j = 1; i < listArr.size(); i++) {
            if (listArr.get(i) == null) {
                listArr.remove(i);
                listArr.add(i, stackOdd.get(stackOdd.size()-j));
                j++;

            }
            arr[i] = listArr.get(i);
        }

        return arr;
    }
    public static int[] sortArraySecondTry(int[] array) {
        int arr[] = Arrays.stream(array).filter(value -> value % 2 == 1).sorted().toArray();

        for (int i = 0, j = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                array[i] = arr[j];
                j++;
            }
        }

        return array;
    }

    public static int mxdiflg(String[] a1, String[] a2) {
        if (a1.length * a2.length == 0) {
            return -1;
        }
        List<Integer> arrayList1 = new ArrayList<>();
        List<Integer> arrayList2 = new ArrayList<>();
        Arrays.stream(a1).forEach(s -> arrayList1.add(s.length()));
        Arrays.stream(a2).forEach(s -> {
            for (Integer integer : arrayList1) {
                arrayList2.add(Math.abs(integer - s.length()));
            }
        });


        return Collections.max(arrayList2);
    }

    public static int mxdiflgSecondTry(String[] a1, String[] a2) {
        if (a1.length * a2.length == 0) {
            return -1;
        }
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = 0, max2 = 0;
        for (String s :
                a1) {
            min1 = Math.min(min1, s.length());
            max1 = Math.max(max1, s.length());
        }
        for (String s :
                a2) {
            min2 = Math.min(min2, s.length());
            max2 = Math.max(max2, s.length());
        }

        return Math.max(Math.abs(min1 - max2), Math.abs(min2 - max1));
    }

    public static long findNextSquare(long sq) {
        double d = Math.pow(sq, 0.5);
        long l = (long) d;
        if (l * 1.0 == d) {
            return (long) Math.pow(l + 1, 2);
        }
        return -1;
    }

    public static long findNextSquareSecondTry(long sq) {
        return Math.sqrt(sq) % 1 == 0 ? (long) Math.pow(Math.sqrt(sq) + 1, 2) : -1;
    }

    public static boolean isIsogram(String str) {
        char[] chars = str.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isIsogramSecondTry(String str) {
        return str.chars().distinct().count() == str.length();
    }

    public static void solution(String s) {
        String[] a = s.split("");
        String[] result = new String[a.length / 2 + 1];
        for (int i = 0, j = 0; i < s.length() - 1; i++) {
            if (i % 2 == 0) {
                result[j] = a[i] + a[i + 1];
                j++;
            }
        }
        System.out.println(Arrays.toString(result));

    }

    public static int res(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) result += i;
        return result;
    }

    public static boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}\\D\\d{4}");
    }

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        int[] temp;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= arr[i - 1]) {

            }
        }
        int arrPeak = Arrays.stream(arr).max().getAsInt();
        int arrPos = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arrPeak == arr[i]) {
                arrPos = i;
                break;
            }
        }
//        int finalArrPos = arrPos;
        temp = Arrays.copyOfRange(arr, arrPos + 1, arr.length);
        System.out.println(Arrays.toString(temp));
        Map<String, List<Integer>> result = new HashMap<>();
//        result.put("pos", Collections.singletonList(finalArrPos));
        result.put("peaks", Collections.singletonList(arrPeak));

        return result;
    }
}
