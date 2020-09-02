import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args){
        test();
    }

    public static void test(){

        List<String> list = Arrays.asList("a","b","c");

        list.forEach(sbu::getStr);

    }

    static class sbu{

        public static void getStr(String str){
            System.out.println(str + "lala");
        }

    }

}
