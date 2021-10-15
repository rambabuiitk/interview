import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FunctionTrie {
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

    Trie t = new Trie();

    class TrieNode {
        Map<String, TrieNode> children;
        List<String> varFunctions;
        List<String> nonVarFunctions;

        public TrieNode() {
            children = new HashMap<>();
            varFunctions = new ArrayList<>();
            nonVarFunctions = new ArrayList<>();
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String functionName, List<String> argTypes, boolean isVariable) {
            TrieNode start = root;
            for (String arg : argTypes) {
                if (!start.children.containsKey(arg)) {
                    start.children.put(arg, new TrieNode());
                }
                start = start.children.get(arg);
            }

            if (isVariable) {
                start.varFunctions.add(functionName);
            } else {
                start.nonVarFunctions.add(functionName);
            }
        }
    }

    private boolean allSame(List<String> listArgs, int startIndex, String argType) {
        for (int i = startIndex;i<listArgs.size();i++) {
            if (!listArgs.get(i).equals(argType)) {
                return false;
            }
        }

        return true;
    }

    public void register(Set<Function> functionSet) {
        for (Function f : functionSet) {
            t.insert(f.name, f.argumentTypes, f.isVariadic);
        }
    }


    public List<String> findMatches(List<String> argumentTypes) {
        List<String> result = new ArrayList<>();
        TrieNode start = t.root;
        for (int i = 0; i<argumentTypes.size();i++) {
            String arg = argumentTypes.get(i);
            if (!start.children.containsKey(arg)) {
                return result;
            } else {
                if ( i != argumentTypes.size() - 1 && start.children.get(arg).varFunctions.size() > 0) {
                    if (allSame(argumentTypes, i, arg)) {
                        result.addAll(start.children.get(arg).varFunctions);
                    }
                }
                if (i == argumentTypes.size() - 1) {
                    if (start.children.get(arg).varFunctions.size() > 0) {
                        result.addAll(start.children.get(arg).varFunctions);
                    }
                    if (start.children.get(arg).nonVarFunctions.size() > 0) {
                        result.addAll(start.children.get(arg).nonVarFunctions);
                    }
                }
            }
            start = start.children.get(arg);

        }

        return result;
    }

    public static void main(String[] args) {
        Function funcA = new Function("FuncA", Arrays.asList("String", "Integer", "Integer"), false);
        Function funcB = new Function("FuncB", Arrays.asList("String", "Integer"), true);
        Function funcC = new Function("FuncC", Arrays.asList("Integer"), true);
        Function funcD = new Function("FuncD", Arrays.asList("Integer", "Integer"), true);
        Function funcE = new Function("FuncE", Arrays.asList("Integer", "Integer", "Integer"), false);
        Function funcF = new Function("FuncF", Arrays.asList("String"), false);
        Function funcG = new Function("FuncG", Arrays.asList("Integer"), false);

        List<Function> funcs = Arrays.asList(funcA, funcB, funcC, funcD, funcE, funcF, funcG);
        Set<Function> funcSet = new HashSet<>();
        funcSet.addAll(funcs);

        FunctionTrie functionTrie = new FunctionTrie();
        functionTrie.register(funcSet);

        System.out.println(functionTrie.findMatches(Arrays.asList("String")));  // -> [FuncF]
        System.out.println(functionTrie.findMatches(Arrays.asList("Integer"))); // -> [FuncC, FuncG]
        System.out.println(functionTrie.findMatches(Arrays.asList("Integer","Integer","Integer","Integer"))); // -> [FuncC, FuncD]

        System.out.println(functionTrie.findMatches(Arrays.asList("Integer", "Integer", "Integer")));// -> [FuncC, FuncD, FuncE]
        System.out.println(functionTrie.findMatches(Arrays.asList("String", "Integer", "Integer", "Integer")));// -> [FuncB]
        System.out.println(functionTrie.findMatches(Arrays.asList("String", "Integer", "Integer")));// -> [FuncA, FuncB]
    }

}
