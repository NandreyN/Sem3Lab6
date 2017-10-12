/*
3. Из заданной строки удалить из каждой группы идущих подряд цифр, которой не предшествует точка, все начальные нули.
 */
package NandreyN;


import java.util.StringTokenizer;
import java.util.StringJoiner;
import java.util.*;

public class Main {

    private static String removeRegex(String arg) {
        return arg.replaceAll("\\.[0]+", ".");
    }

    private static String processString(String arg) {

        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(arg,".", true);
        boolean prevWatToken = false;

        if (tokenizer.countTokens() == 1)
            return arg;

        while(tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            if (token.equals("."))
            {
                builder.append(token);
                prevWatToken = true;
                continue;
            }

            if (!prevWatToken)
            {
                builder.append(token);
                continue;
            }
            StringBuilder localBuilder = new StringBuilder(token);
            while(localBuilder.length() > 0 && localBuilder.charAt(0) == '0')
            {
                localBuilder.deleteCharAt(0);
            }

            builder.append(localBuilder.toString());
            prevWatToken = false;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        if (args.length == 0) return;

        System.out.println(processString(args[0]));
        test();
    }

    private static void test() {
        Map<String, String> testResult = new HashMap<String, String>();
        testResult.put(".asd.007a.00", ".asd.7a.");
        testResult.put(".asd.007a.78.", ".asd.7a.78.");
        testResult.put("qwerty", "qwerty");
        testResult.put("0045,500.07.89798.fdsf..", "0045,500.7.89798.fdsf..");
        testResult.put("00qwerty", "00qwerty");
        testResult.put("00qwerty.", "00qwerty.");
        testResult.put("abcd...055..", "abcd...55..");
        testResult.put("4589", "4589");
        testResult.put("aa8.04fff.74", "aa8.4fff.74");
        testResult.put("004589", "004589");
        testResult.put(".004589", ".4589");
        testResult.put(".004589.dfsdf.f.000487d.3.0", ".4589.dfsdf.f.487d.3.");

        for (String key : testResult.keySet()) {
            String got = processString(key);
            if (!testResult.get(key).equals(got)) {
                System.out.println("Test failed: Key = " + key + ", Expected = " + testResult.get(key) + ", Got = " + got);
            } else {
                System.out.println("test passed");
            }
        }
    }
}