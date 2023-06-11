/**
*
* @author Burak Bala burak.bala@ogr.sakarya.edu.tr
* @since 06.04.2021
* <p>
* CppMethod class it handles method storage and printing method
* </p>
*/

package g181210058;

public class CppMethod {
    private final int numberOfParameters;
    private final String name;
    private String parameters;
    private final String returnType;

    public CppMethod(int numberOfParameters, String name, String parameters, String returnType) {
        this.numberOfParameters = numberOfParameters;
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public void Print() {
        System.out.println("\t" + name);
        String parameter = numberOfParameters > 1 ? "Parameters" : "Parameter";
        parameters = !parameters.equals("") ? " (" + parameters + ")" : parameters;
        System.out.println("\t\t" + parameter + ": " + numberOfParameters + parameters);
        System.out.println("\t\tReturn type: " + returnType);
    }
}