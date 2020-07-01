import java.util.Stack;

public class ValidParenthesis {

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '[')
                stack.push(']');
            else if (c == '{')
                stack.push('}');
            else if (stack.isEmpty() || c != stack.pop())
                return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{[]}";
        String st = "()[]{}";

        //System.out.println(isValid(s));
        System.out.println(isValid(st));
    }
}
