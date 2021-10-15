import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionLibrary {

    static class Function {
        String name;
        List<String> argumentTypes;
        boolean isVariadic;

        Function(String name, List<String> argumentTypes, boolean isVariadic) {
            this.name = name;
            this.argumentTypes = argumentTypes;
            this.isVariadic = isVariadic;
        }
    }


    Map<String, List<Function>> nonVariadic = new HashMap<>();
    Map<String, List<Function>> variadic = new HashMap<>();

    public void register(Set<Function> functionSet) {
        for (Function f : functionSet) {
            String key = appendArgs(f.argumentTypes, f.argumentTypes.size());
            if (f.isVariadic) {
                variadic.putIfAbsent(key, new LinkedList<>());
                variadic.get(key).add(f);
            } else {
                nonVariadic.putIfAbsent(key, new LinkedList<>());
                nonVariadic.get(key).add(f);
            }
        }
    }

    public List<Function> findMatches(List<String> argumentTypes) {
        List<Function> matches = new ArrayList<>();
        String key = appendArgs(argumentTypes, argumentTypes.size());

        if (nonVariadic.containsKey(key)) {
            matches.addAll(nonVariadic.get(key));
        }
        if (variadic.containsKey(key)) {
            matches.addAll(variadic.get(key));
        }

        int count = argumentTypes.size();
        for (int i = argumentTypes.size() - 2; i >= 0; --i) {
            if (argumentTypes.get(i).equals(argumentTypes.get(i + 1))) {
                --count;
            } else {
                break;
            }
            key = appendArgs(argumentTypes, count);
            if (variadic.containsKey(key)) {
                matches.addAll(variadic.get(key));
            }
        }

        return matches;
    }

    public String appendArgs(List<String> argumentTypes, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            String arg = argumentTypes.get(i);
            sb.append(arg);
            sb.append("+");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Function funcA = new Function("FuncA", Arrays.asList("String", "Integer", "Integer"), false);
        Function funcB = new Function("FuncB", Arrays.asList("String", "Integer"), true);
        Function funcC = new Function("FuncC", Arrays.asList("Integer"), true);
        Function funcD = new Function("FuncD", Arrays.asList("Integer", "Integer"), true);
        Function funcE = new Function("FuncE", Arrays.asList("Integer", "Integer", "Integer"), false);
        Function funcF = new Function("FuncF", Arrays.asList("String"), false);
        Function funcG = new Function("FuncG", Arrays.asList("Integer"), false);

        FunctionLibrary funcLib = new FunctionLibrary();
        List<Function> funcs = Arrays.asList(funcA, funcB, funcC, funcD, funcE, funcF, funcG);
        Set<Function> funcSet = new HashSet<>();
        funcSet.addAll(funcs);
        funcLib.register(funcSet);

        printFuncs(funcLib.findMatches(Arrays.asList("String")));  // -> [FuncF]
        printFuncs(funcLib.findMatches(Arrays.asList("Integer"))); // -> [FuncC, FuncG]
        printFuncs(funcLib.findMatches(Arrays.asList("Integer","Integer","Integer","Integer"))); // -> [FuncC, FuncD]

        printFuncs(funcLib.findMatches(Arrays.asList("Integer", "Integer", "Integer")));// -> [FuncC, FuncD, FuncE]
        printFuncs(funcLib.findMatches(Arrays.asList("String", "Integer", "Integer", "Integer")));// -> [FuncB]
        printFuncs(funcLib.findMatches(Arrays.asList("String", "Integer", "Integer")));// -> [FuncA, FuncB]
    }

    public static void printFuncs(List<Function> funcs) {
        for (Function func: funcs) {
            System.out.print(func.name + ", ");
        }
        System.out.println();
    }
}