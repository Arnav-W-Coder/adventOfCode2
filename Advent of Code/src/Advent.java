import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Advent {
	
	
	public static boolean decreasing(ArrayList<String> p) {
		int count = 0;
		
		for(int i = 1; i < p.size(); i++) {
			int e11 = Integer.valueOf(p.get(i));
			int e10 = Integer.valueOf(p.get(i-1));
			
			if(e11 >= e10) {
				return false;
			}else if(e10 - e11 > 3) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean increasing(ArrayList<String> p) {
		int count = 0;
		
		for(int i = 1; i < p.size(); i++) {
			int e11 = Integer.valueOf(p.get(i));
			int e10 = Integer.valueOf(p.get(i-1));
			
			if(e11 <= e10) {
				return false;
			}else if(e10 - e11 > 3) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		
		File myFile = new File("AdventFile.txt");
		ArrayList<Integer> leftList = new ArrayList<Integer>();
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		ArrayList<Integer> leftTemp = new ArrayList<Integer>();
		ArrayList<Integer> rightTemp = new ArrayList<Integer>();
		int totalDistance = 0;
		int similarityScore = 0;
		try {
			Scanner scan = new Scanner(myFile);
			while(scan.hasNext()) {
				//call nextInt 2x each iteration
				leftList.add(scan.nextInt());
				rightList.add(scan.nextInt());
				
			}
			leftTemp = leftList;
			rightTemp = rightList;
			//look for the smallest element in left
			//look for the smallest in right
			//find difference
			//remove the smallest smallest element and repeat

			for(int i = 0; i < leftList.size(); i++) {
				int times = 0;
				int num = leftList.get(i);
				for(int j = 0; j < rightList.size(); j++) {
					if(rightList.get(j) == num) {
						times ++;
					}
				}
				similarityScore += (num * times);
			}
			
			while(leftList.size() > 0) {
				int distance = 0;
				int smallestLeft = leftList.get(0);
				int indexLeft = 0;
				
				for(int i = 0; i < leftList.size(); i++) {
					if(leftList.get(i) < smallestLeft) {
						smallestLeft = leftList.get(i);
						indexLeft = i;
					}
				}
				
				leftList.remove(indexLeft);
				
				int smallestRight = rightList.get(0);
				int indexRight = 0;
				
				for(int i = 0; i < rightList.size(); i++) {
					if(rightList.get(i) < smallestRight) {
						smallestRight = rightList.get(i);
						indexRight = i;
					}
				}
				
				rightList.remove(indexRight);
				
				
				distance = Math.abs(smallestRight - smallestLeft);
				//System.out.println(smallestLeft + " " + smallestRight + " " + distance);
				totalDistance += distance;
			}
			
			
			
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(totalDistance);
		System.out.println(similarityScore);
		
		//DayTwo
		File report = new File("levelsFile.txt");
		int safeReports = 0;
		int safeLevels = 0;
		boolean damp = true;
		try {
			Scanner levelScan = new Scanner(report);
			while(levelScan.hasNext()) {
				damp = true;
				safeLevels = 0;
				String line = levelScan.nextLine();
				String[] levels = line.split(" ");
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(levels));
				
				if(increasing(list) || decreasing(list)) {
					safeReports ++;
				}else{
					for(int i = 0; i < list.size(); i++) {
						String temp = list.get(i);
						list.remove(i);
						if((increasing(list) || decreasing(list)) && damp) {
							damp = false;
							safeReports ++;
						}
						list.add(i, temp);
					}
				}
				
				
		
			}
			
			System.out.println(safeReports);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
