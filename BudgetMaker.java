import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class BudgetMaker {
	
static Map<String,Double> table = new HashMap<>();	
static double income;
static double totalexpenses;
static Scanner console = new Scanner(System.in);
static DecimalFormat numberFormat = new DecimalFormat("#.00");

	public static void main(String[] args) throws FileNotFoundException{
		table.put("Housing", .3);
		table.put("Food", .15);
		table.put("Investments", .2);
		table.put("Entertainment", .1);
		table.put("Transportation", .1);
		System.out.println("This program helps you create a monthly budget based on your net income");
		Income();
		Expenses();
		Calculation();
		
	}
	
	public static void Income() {
		System.out.print("How many streams of income do you have? ");
		Double input = console.nextDouble();
		income = 0.0;
		
		for(int i = 0; i < input; i++) {
			System.out.print("Income #" + (i+1) +"? ");
			Double incomestream = console.nextDouble();
			income += incomestream;
		}
	}
	public static void Expenses() {
		 totalexpenses = (income*table.get("Housing")) + (income*table.get("Food"))+ (income*table.get("Investments")+(income*table.get("Entertainment"))) + (income*table.get("Transportation"));
		
	}
	
	public static void Calculation() throws FileNotFoundException {
		String incomeStatement = ("\nYour total monthly income is " + income + " and you should allocate the following amounts across these 5 categories");
		String housing = "\nHousing: $" + (income*table.get("Housing"));
		String food = "\nFood: $" + (income*table.get("Food"));
		String investments = "\nInvestments: $" + (income*table.get("Investments"));
		String entertainment = "\nEntertainment: $" + (income*table.get("Entertainment"));
		String transportation = "\nTransportation: $" + (income*table.get("Transportation")) + "\n";
		String budget = "Your total monthly expenses are $" + totalexpenses + " and you will have $" + numberFormat.format(income -totalexpenses) + " to use at your discretion. (We recommend you save it!)";
		
		System.out.println(incomeStatement + housing + food + investments + entertainment + transportation + budget);
		
		System.out.println("If you would like to edit your budget respond with Yes");
		editBudget(console.next());
		System.out.println("If you would like to print your budget respond with Print");
		String input = console.next().toLowerCase();
		if(input.equals("print")) {
			System.out.println("What would you like to name your budget?");
			PrintStream output = new PrintStream((new File (console.next() + ".txt")));
			output.println(incomeStatement);
			output.println(housing);
			output.println(food);
			output.println(investments);
			output.println(entertainment);
			output.println(transportation);
			output.println(budget);
		}
		output.close();
	}
	
	private static void editBudget(String input) throws FileNotFoundException {
		input = input.toLowerCase();
		if(input.equals("yes")) {
			System.out.print("Ok, which category would you like to change? ");
			String category = console.next();
			System.out.println("How much of your income would you like to allocate to this category? (please input a dollar amount)");
			Double amount = console.nextDouble();
			if(amount >= income) {
				System.out.println("Please choose an amount less than your total income ");
				amount = console.nextDouble();
			}
			Double percentage = amount/income;
			table.put(category, percentage);
			Calculation();
		}
	}
	
	
}
