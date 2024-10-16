package feedbak;

import java.util.Scanner;

public class Feedbak {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resp;

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            sc.nextLine(); // Consume newline character

            Feedbak test = new Feedbak(); // Use capitalized class name

            switch (action) {
                case 1:
                    test.addFeedback();
                    break;
                case 2:
                    test.viewFeedback();
                    break;
                case 3:
                    test.viewFeedback();
                    test.updateFeedback();
                    break;
                case 4:
                    test.viewFeedback();
                    test.deleteFeedback();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.next();

        } while (resp.equalsIgnoreCase("yes"));
        
        System.out.println("Thank You!");
        sc.close(); // Close the scanner
    }

    public void addFeedback() {
        Scanner sc = new Scanner(System.in);
        config conf = new config(); // Assuming Config is a class for DB operations

        System.out.print("feedback_text: ");
        String feedbackText = sc.nextLine();

        System.out.print("rating (1-5): ");
        int rating = sc.nextInt();
        sc.nextLine(); // Consume newline character

        String sql = "INSERT INTO tbl_feedbak (f_text, f_date, f_rating) VALUES (?, NOW(), ?)";
        conf.addRecord(sql, feedbackText, rating); // Adjusted parameters for addRecord
    }

    private void viewFeedback() {
        String qry = "SELECT * FROM tbl_feedbak"; // Correct query for feedback
        String[] hdrs = {"ID", "Feedback Text", "Date", "Rating"};
        String[] clms = {"f_id", "f_text", "f_date", "f_rating"}; // Corrected column names

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms); // Adjusted method to view feedback
    }

    private void updateFeedback() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline character

        System.out.print("Enter new Feedback Text: ");
        String newFeedbackText = sc.nextLine();

        System.out.print("Enter new Rating (1-5): ");
        int newRating = sc.nextInt();

        String qry = "UPDATE tbl_feedbak SET f_text = ?, f_rating = ? WHERE f_id = ?";
        config conf = new config();
        conf.updateRecord(qry, newFeedbackText, newRating, id); // Pass correct parameters
    }

    private void deleteFeedback() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM tbl_feedbak WHERE f_id = ?";
        config conf = new config();
        conf.deleteRecord(qry, id); // Corrected to use f_id
    }
}
