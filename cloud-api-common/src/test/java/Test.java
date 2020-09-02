public class Test {

    public static void main(String[] args){
        int a = 2;
        int b = 5;
        System.out.println(a % b);
        test(a, b);
        System.out.println(2 / 5);
    }

    public static void test(int a, int b){
        System.out.println(a - (a / b) * b);
    }

}
