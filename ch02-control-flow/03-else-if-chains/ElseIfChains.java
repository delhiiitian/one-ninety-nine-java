// 2.3 else-if chains: choosing among many options
// Run: java ElseIfChains.java
public class ElseIfChains {
    public static void main(String[] args) {
        int[] testScores = {95, 83, 71, 64, 40};

        for (int score : testScores) {
            String grade;
            // Checked top to bottom; the FIRST true condition wins
            if (score >= 90) {
                grade = "A";
            } else if (score >= 80) {
                grade = "B";
            } else if (score >= 70) {
                grade = "C";
            } else if (score >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }
            System.out.println("Score " + score + " -> grade " + grade);
        }

        // Real world: shipping cost by order total
        double orderTotal = 72.50;
        double shipping;
        if (orderTotal >= 100) {
            shipping = 0;
        } else if (orderTotal >= 50) {
            shipping = 4.99;
        } else {
            shipping = 9.99;
        }
        System.out.println("Order $" + orderTotal + " ships for $" + shipping);
    }
}
