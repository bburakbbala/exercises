package g181210058;

import java.io.File;
import java.net.URISyntaxException;
import net.sourceforge.jFuzzyLogic.FIS;

public class Child {
	private final FIS fis;
	public Child(Gender gender, double age, double addedSugarConsumption) throws URISyntaxException {
		File file = new File(getClass().getResource("ChildSugarConsumption.fcl").toURI());
		fis = FIS.load(file.getPath());
		
		if(gender.equals(Gender.FEMALE))
			fis.setVariable("gender", 1);
		else if(gender.equals(Gender.MALE))
			fis.setVariable("gender", 2);
		fis.setVariable("age", age);
		fis.setVariable("addedSugarConsumption", addedSugarConsumption);
		
		fis.evaluate();
	}
	public FIS getModel() {
		return fis;
	}
}


