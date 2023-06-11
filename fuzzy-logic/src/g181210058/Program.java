package g181210058;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class Program {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Gender [Female/Male]: ");
		String genderString = in.next();
		genderString = genderString.toLowerCase();
		Gender gender = null;
		if(genderString.equals("female"))
			gender = Gender.FEMALE;
		else if(genderString.equals("male"))
			gender = Gender.MALE;
		
		System.out.println("Age [4-18]: ");
		double age = in.nextDouble();
		System.out.println("Added sugar consumption (gr): ");
		double addedSugarConsumption = in.nextDouble();
		in.close();
		try {
			Child child = new Child(gender, age, addedSugarConsumption);
			FIS fis = child.getModel();
			for(Rule r : fis.getFunctionBlock("ChildSugarConsumption").getFuzzyRuleBlock("ruleBlock1").getRules()) {
				// show rules that processed
				if(r.getDegreeOfSupport() > 0)
					System.out.println(r);
			}
			JFuzzyChart.get().chart(fis);
		} catch(URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
