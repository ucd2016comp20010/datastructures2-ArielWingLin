package project20280.stacksqueues;
import java.util.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public static boolean checkParentheses(String in){

      Stack<Character> letsCheck = new Stack<>();
        for(char c : in.toCharArray()){
            if(c == '{' || c == '[' || c == '('){
                letsCheck.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                if (letsCheck.isEmpty()) {
                    return false;
                }
                char top = letsCheck.peek();
                if((c == '}' && top != '{') ||
                  (c == ']' && top != '[') ||
                  (c == ')' && top != '(')) {
                    return false;
                }
                letsCheck.pop();
            }
        }
        return letsCheck.isEmpty();
    }

    public void check() {
        boolean isBalanced = checkParentheses(this.input);

        if(isBalanced){
            System.out.println("Passed!");
        } else {
            System.out.println("Failed :(");
        }
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}