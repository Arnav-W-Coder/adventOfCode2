package adventOfCodeDay3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Advent {
	
	public static int calculateMulSum(String input) {
        // Regular expression to match only valid mul(x,y) instructions
        String regex = "mul\\([0-9]+,[0-9]+\\)";
        
        int sum = 0;
        boolean mulEnabled = true;  // Initially mul instructions are enabled

        // Find matches for valid mul instructions and "do()" / "don't()" instructions
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex + "|do\\(\\)|don't\\(\\)");
        java.util.regex.Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            String matched = matcher.group();
            
            // Check if it's a "do()" instruction
            if (matched.equals("do()")) {
                mulEnabled = true;  // Enable mul instructions
            }
            // Check if it's a "don't()" instruction
            else if (matched.equals("don't()")) {
                mulEnabled = false;  // Disable mul instructions
            }
            // Otherwise, it's a valid mul(x,y) instruction
            else if (mulEnabled) {
                // Remove the non-numeric characters (mul( and )) to extract the numbers
                String numbers = matched.replaceAll("[^0-9,]", "");
                
                // Split the numbers based on the comma
                String[] parts = numbers.split(",");
                int num1 = Integer.parseInt(parts[0]);
                int num2 = Integer.parseInt(parts[1]);
                
                // Calculate the product and add to the sum
                sum += num1 * num2;
            }
        }
        
        return sum;
    }
	
	public static void main(String[] args) {
		File myFile = new File("adventDay3.txt");
		try {
			int total = 0;
			Scanner scan = new Scanner(myFile);
			while(scan.hasNext()) {
				String line = scan.nextLine();
				System.out.println(line);
				total += calculateMulSum(line);
				
				
			}
			
			System.out.println(total);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
