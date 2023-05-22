import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class test {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int num = 0;
        int score = -1;
        int rand;
        int answer;
        ArrayList<Integer> divisibles = new ArrayList<>();
        boolean correct = true;

        //User input such that num is between 1 and 10
        while (num == 0) {
            num = pickANumber(scanner);
        }

        //Random gameplay such that the next game is either addition/subtraction, multiplication, or division
        int whichMode;
        while (correct) { // change this, bc it ends game when num=0, which can happen with addition and multiplication
            whichMode = random.nextInt(3); //Random number 0 to 2
            switch(whichMode) {
                case 0: //addition
                    rand = random.nextInt(61) - 30 - num; // random.nextInt(61) is 0 to 60, any answer must be in [-30, 30]
                    answer = num + rand;
                    num = nextSum(num, rand, scanner);
                    correct = (num == answer);
                    break;
                case 1: //multiplication
                    /* since multiplication bounds of [-4,4] is way too easy: os
                    if 0 <= Math.abs(num) < 50 --> multiply up to +-25 --> 1225
                    if 50 <= Math.abs(num) < 100 --> multiply up to +-10 --> 990
                    if 100 <= Math.abs(num) < 250 --> multiply up to +- 2 --> 198
                    if 250 <= Math.abs(num) --> multiply up to +- 1 --> 1225 from the if statement from the initial
                    therefore, the highest possible value that can be obtained through each is 1176
                    */
                    if (Math.abs(num) < 50) {
                        rand = random.nextInt(51)-25; // [-25,25]
                    } else if (Math.abs(num) < 100) {
                        rand = random.nextInt(21)-10;
                    } else if (Math.abs(num) < 250) {
                        rand = random.nextInt(5)-2;
                    } else {
                        rand = random.nextInt(3) -1;
                    }
                    answer = num * rand;
                    num = nextMultiply(num, rand, scanner);
                    correct = (num == answer);
                    break;
                case 2: //division
                    if (num != 0) {
                        for (int i = 1 ; i <= Math.sqrt(Math.abs(num)) ; i++) { //all plus or minus factors of num
                            if (num % i == 0) {
                                divisibles.add(i);
                                divisibles.add(-1*i);
                                if(num/i != i) {
                                    divisibles.add(num/i);
                                    divisibles.add(-1*num/i);
                                }
                            }
                        }
                    } else {
                        divisibles.add(1);
                    }
                    rand = divisibles.get(random.nextInt(divisibles.size()));
                    answer = num / rand;
                    num = nextDivision(num, rand, scanner);
                    correct = (num == answer);
                    divisibles.clear();
                    break;
            }
            score++;
        } 
        System.out.println("you got a score of " + score + ". good job");
        
        scanner.close();
    }
    public static int pickANumber(Scanner scanner) {
        System.out.print("Pick an integer from 1 to 10: ");
        try {
            int iNum = scanner.nextInt();
            if (iNum >= 1 && iNum <= 10) {
                return iNum;
            } else {
                System.out.println("you dumbass, that isn't between 1 and  10. try again");
                return 0;
            }
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine();
            System.out.println("you absolute dumbass, that isn't even an integer. try again");
            return 0;
        }
    }
    public static int nextSum(int incoming, int rand, Scanner scanner) {
        int answer = incoming + rand;
        if (rand >= 0) {
            System.out.println("Add " + rand + " to that number");
        } else {
            System.out.println("Subtract " + -1*rand + " from that number");
        }
        try {
            int response = scanner.nextInt();
            if (response == answer) {
                System.out.println("Correct!");
                return response;
            } else {
                System.out.println("Rip, you got it wrong.");
                return response;
            }
        } catch (java.util.InputMismatchException e) {
           scanner.nextLine();
           System.out.println("dumbass, you got it wrong.");
           return answer + 1;
        }
    }
    public static int nextMultiply(int incoming, int rand, Scanner scanner) {
        int answer = incoming * rand;
        System.out.println("Multipy by " + rand);
        try {
            int response = scanner.nextInt();
            if (response == answer) {
                System.out.println("Correct!");
                return response;
            } else {
                System.out.println("Rip, you got it wrong.");
                return response;
            }
        } catch (java.util.InputMismatchException e) {
           scanner.nextLine();
           System.out.println("dumbass, you got it wrong.");
           return answer + 1;
        }
    }
    public static int nextDivision(int incoming, int rand, Scanner scanner) {
        int answer = incoming / rand;
        System.out.println("Divide by " + rand);
        try {
            int response = scanner.nextInt();
            if (response == answer) {
                System.out.println("Correct!");
                return response;
            } else {
                System.out.println("Rip, you got it wrong.");
                return response;
            }
        } catch (java.util.InputMismatchException e) {
           scanner.nextLine();
           System.out.println("dumbass, you got it wrong.");
           return answer + 1;
        }
    }
}