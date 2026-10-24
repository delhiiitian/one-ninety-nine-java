// 4.4 Stacks (last in, first out) and queues (first in, first out)
// Run: java StacksAndQueues.java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class StacksAndQueues {
    public static void main(String[] args) {
        // STACK: like a pile of plates. Undo history in an editor works this way.
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("typed 'Hello'");
        undoStack.push("made text bold");
        undoStack.push("inserted image");
        System.out.println("Last action: " + undoStack.peek());
        System.out.println("Undo: " + undoStack.pop());
        System.out.println("Undo: " + undoStack.pop());
        System.out.println("Remaining history: " + undoStack);

        // QUEUE: like a line at a ticket counter. Print jobs work this way.
        Queue<String> printQueue = new ArrayDeque<>();
        printQueue.offer("report.pdf");
        printQueue.offer("photo.png");
        printQueue.offer("invoice.docx");
        System.out.println("Next to print: " + printQueue.peek());
        while (!printQueue.isEmpty()) {
            System.out.println("Printing " + printQueue.poll());
        }

        // A practical stack: checking balanced brackets
        System.out.println("(a[b]{c}) balanced? " + isBalanced("(a[b]{c})"));
        System.out.println("(a[b)c]  balanced? " + isBalanced("(a[b)c]"));
    }

    static boolean isBalanced(String text) {
        Deque<Character> open = new ArrayDeque<>();
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                open.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (open.isEmpty()) return false;
                char top = open.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return open.isEmpty();
    }
}
