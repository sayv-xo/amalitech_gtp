import java.util.Scanner;

public class GradeStats
{

    public static void main(String[] args)
    {
        /**
         * This program reads a list of grades from the user and then prints a graph of the grades.
         * @param args: Unused
         */
        System.out.println("Please enter grades separated by spaces \n");

        Scanner input = new Scanner(System.in);
        String grades = input.nextLine(); 
        String[] strGrades = grades.split(" ");
        input.close();
        int[] scores = new int[strGrades.length];

        // Convert the array of strings into an array of integers
        for (int i = 0; i < strGrades.length; i++)
        {
            scores[i] = Integer.parseInt(strGrades[i]);
        }

        double sum = 0;
        int minGrade = scores[0], maxGrade = scores[0];

        // Find the total scores and min score and max score
        for (int grade : scores)
        {
            sum += grade;
            if (grade < minGrade)
            {
                minGrade = grade;
            }
            if (grade > maxGrade)
            {
                maxGrade = grade;
            }
        }

        double average = sum / scores.length;

        System.out.println("Values: \n");
        System.out.println("The maximun grade is " + maxGrade);
        System.out.println("The minimum grade is " + minGrade);
        System.out.println("The average grade is " + average);

        // An array to store the frequency of each grade range
        int[] stats = new int[5];
        for (int score : scores) {
            stats[score / 21]++;
        }

        System.out.println("\nGraph:\n");

        int maxFrequency = 0;
        for (int stat : stats) {
            if (stat > maxFrequency) maxFrequency = stat;
        }

        // Draw the graph
        for (int i = maxFrequency; i > 0; i--)
        {
            System.out.printf("%4d >", i);
            for (int stat : stats) {
                if (stat >= i) {
                    System.out.print("   #######");
                } else {
                    System.out.print("          ");
                }
            }
            System.out.println();
        }

        System.out.println("     +-----------+---------+---------+---------+---------+");
        System.out.println("     I    0-20   I  21-40  I  41-60  I  61-80  I  81-100 I");
    }
}
