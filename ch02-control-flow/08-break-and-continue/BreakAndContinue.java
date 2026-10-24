// 2.8 break and continue: steering a loop from inside
// Run: java BreakAndContinue.java
public class BreakAndContinue {
    public static void main(String[] args) {
        // break: stop the loop completely
        String[] lockers = {"empty", "empty", "keys", "empty", "phone"};
        for (int i = 0; i < lockers.length; i++) {
            System.out.println("Checking locker " + i);
            if (lockers[i].equals("keys")) {
                System.out.println("Found keys in locker " + i + "! Stop searching.");
                break;
            }
        }

        // continue: skip the rest of THIS round and move to the next
        int[] orders = {12, -1, 30, 0, 7};
        int validTotal = 0;
        for (int qty : orders) {
            if (qty <= 0) {
                System.out.println("Skipping invalid quantity " + qty);
                continue;
            }
            validTotal += qty;
        }
        System.out.println("Total valid items: " + validTotal);

        // Labeled break: exit an outer loop from inside an inner loop
        int[][] seats = {{1, 1, 1}, {1, 1, 0}, {0, 0, 0}};  // 1 = taken
        search:
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col] == 0) {
                    System.out.println("First free seat: row " + row + ", col " + col);
                    break search;
                }
            }
        }
    }
}
