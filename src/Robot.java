import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Robot {

    public static List<String> doesCircleExist(List<String> commands) {
        List<String> result = new ArrayList<String>();
        for(String s : commands){
            if(s.equalsIgnoreCase("G")){
                result.add("NO");
            } else if (s.indexOf("G") == -1) {
                result.add("YES");
            } else if (s.indexOf("L") == -1 && s.indexOf("R") != -1) {
                result.add("YES");
            } else if (s.indexOf("R") == -1 && s.indexOf("L") != -1){
                result.add("YES");
            } else {
                result.add("NO");
            }
        }
        return result;
    }

}
