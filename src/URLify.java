public class URLify {

    public static String URL(char[] str, int length) {
        int dest = str.length - 1;

        for (int i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[dest--] = '0';
                str[dest--] = '2';
                str[dest--] = '%';
            }
            else
                str[dest--] = str[i];
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!: " + URL("Hello, World!  ".toCharArray(), 13));
        System.out.println("Mr John Smith: " + URL("Mr John Smith    ".toCharArray(), 13));
    }
}
