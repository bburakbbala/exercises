/**
*
* @author Burak Bala burak.bala@ogr.sakarya.edu.tr
* @since 06.04.2021
* <p>
* CppBaseClassMap creates a map key as a base class name as a value and
* it holds base class number of inheritence
* </p>
*/

package g181210058;

import java.util.HashMap;
import java.util.Map;

public class CppBaseClassMap {
    private final Map<String, Integer> baseClass;

    public CppBaseClassMap() {
        baseClass = new HashMap<>();
    }

    public void add(String baseClassName) {
        if(!baseClassName.equals(""))
            baseClass.merge(baseClassName, 1, Integer::sum);
            // if baseClassName does not exist, put 1 as value
            // otherwise increment one
    }

    public boolean isEmpty() {
        return baseClass.isEmpty();
    }

    public void print() {
        String pluralOrNot = baseClass.size() > 1 ? "Classes" : "Class";
        System.out.println();
        System.out.println("Base " + pluralOrNot + ":");
        for (String key : baseClass.keySet()) {
            if (!key.equals("")) System.out.println("\t" + key + ": " + baseClass.get(key));
        }
    }
}