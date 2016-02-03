package controlLayer;

import java.util.Scanner;


public class ManageObligation {
	public ManageObligation(){
		 
	}
	/**
	 * 
	 * @param number is the number of people included in the bill
	 * @param sum is the sum of the bill 
	 * @return sum the sum which each person included in the bill owe
	 */
	public double devideSum(double number, double sum){
		 sum =  sum/number;
		 sum = Math.round(sum * 100.0) / 100.0;
		 return sum;
	}

	

	
	

}
