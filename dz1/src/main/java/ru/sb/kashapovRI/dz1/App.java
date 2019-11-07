package ru.sb.kashapovRI.dz1;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        DIYarrayList<Integer> diYarrayListInteger = new DIYarrayList<Integer>();
        DIYarrayList<Integer> diYarrayListInteger2 = new DIYarrayList<>();
        addAll(diYarrayListInteger, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21);
        addAll(diYarrayListInteger2, 0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        copy(diYarrayListInteger2,diYarrayListInteger);
        DIYarrayList<String> diYarrayListString, diYarrayListString2;
        diYarrayListString = new DIYarrayList<String>();
        diYarrayListString2 = new DIYarrayList<String>();
        addAll(diYarrayListString,"one","two","three","four","five","one","two","three","four","five","one","two","three","four","five","one","two","three","four","five","six");
        addAll(diYarrayListString2,"a","b","c","d","f","a","b","c","d","f","a","b","c","d","f","a","b","c","d","f","six");
        copy(diYarrayListString2,diYarrayListString);
        System.out.println(diYarrayListInteger.toString());
        System.out.println(diYarrayListString.toString());
        System.out.println("diYarrayListInteger2 = "  + diYarrayListInteger2.toString());
        addAll(diYarrayListString2,"a","b","c","d","f","a","b","c","d","f","a","b","c","d","f","a","b","c","d","f","six");
        System.out.println("diYarrayListString2 = " + diYarrayListString2.toString());
        sort(diYarrayListString2, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
        System.out.println("diYarrayListString2 (after sort()) = " + diYarrayListString2.toString());
        addAll(diYarrayListInteger, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21);
        System.out.println("diYarrayListInteger (after addAll()) = "+diYarrayListInteger.toString());
        sort(diYarrayListInteger, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer.compareTo(t1);
            }
        });
        System.out.println("diYarrayListInteger (after sort()) = "+diYarrayListInteger.toString());
    }
}
