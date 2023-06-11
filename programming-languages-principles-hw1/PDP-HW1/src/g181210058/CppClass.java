/**
*
* @author Burak Bala burak.bala@ogr.sakarya.edu.tr
* @since 06.04.2021
* <p>
* CppClass it holds classes public methods as CppMethod reference in List
* and CppClass name as Class member
* </p>
*/

package g181210058;

import java.util.ArrayList;
import java.util.List;

public class CppClass {
    private final String name;
    private final List<CppMethod> methodList = new ArrayList<>();

    public CppClass(String name) {
        this.name = name;
    }

    public void addMethod(CppMethod method) {
        if (!methodList.contains(method))
            this.methodList.add(method);
    }

    public void print() {
        System.out.println("Class: " + name);
        for (CppMethod i : methodList)
            i.Print();
        System.out.println("\n");
    }

}