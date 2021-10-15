package karat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdsConversion {

    public void adsConversionRate(List<String> completedPurchaseUserIds, List<String> adClicks,
                                  List<String> allUserIps) {
        Set<String> userIds = new HashSet<>(completedPurchaseUserIds);
        Map<String, int[]> conversion = new HashMap<>();
        Map<String, String> ipToUserId = new HashMap();
        for (String userIp : allUserIps) {
            String[] userIdIps = userIp.split(",");
            ipToUserId.put(userIdIps[0], userIdIps[1]);
        }
        for (String click : adClicks) {
            String[] ipAdText = click.split(",");
            if (conversion.containsKey(ipAdText[2])) {
                conversion.get(ipAdText[2])[1]++;
                if (userIds.contains(ipToUserId.get(ipAdText[0]))) {
                    conversion.get(ipAdText[2])[0]++;
                }
            } else {
                int bought = userIds.contains(ipToUserId.get(ipAdText[2])) ? 1 : 0;
                conversion.put(ipAdText[1], new int[]{bought, 1});
            }
        }
        for (Map.Entry<String, int[]> entry : conversion.entrySet()) {
            System.out.println(entry.getValue()[0] + " of " + entry.getValue()[1] + " " + entry.getKey());
        }
    }
}
