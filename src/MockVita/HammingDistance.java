import java.util.*;

public class HammingDistance {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        List<Integer> listOfA = new ArrayList<>();
        List<Integer> listOfB = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int t = 0; t < T; t++) {
            // Read the binary string and the values of a and b
            String inputStr = sc.nextLine().trim();
            strList.add(inputStr);
            int a = sc.nextInt();
            int b = sc.nextInt();
            listOfA.add(a);
            listOfB.add(b);
            sc.nextLine();
        }
        for (int t = 0; t < T; t++) {
            System.out.println(processData(strList.get(t), listOfA.get(t), listOfB.get(t)));
        }
        sc.close();
    }

    public static String processData(String inputStr, int a, int b) {
        for (char c : inputStr.toCharArray()) {
            if (c != '0' && c != '1') {
                return "INVALID";
            }
        }
        if (a < 0 || b > 10000) {
            return "INVALID";
        }

        // Count the number of '0'inputStr and '1'inputStr in the string
        int count0 = 0, count1 = 0;
        for (char c : inputStr.toCharArray()) {
            if (c == '0') {
                count0++;
            } else {
                count1++;
            }
        }

        // All 0s followed by all 1s
        String arranged01 = "0".repeat(Math.max(0, count0)) +
                "1".repeat(Math.max(0, count1));

        // All 1s followed by all 0s
        String arranged10 = "1".repeat(Math.max(0, count1)) +
                "0".repeat(Math.max(0, count0));

        int totalCost01 = calculateTotalCost(arranged01, a, b);
        int totalCost10 = calculateTotalCost(arranged10, a, b);
        int originalCost = calculateTotalCost(inputStr, a, b);

        // Return the minimum total cost as per formula
        return String.valueOf(Math.min(originalCost, Math.min(totalCost01, totalCost10)));
    }

    private static int calculateTotalCost(String str, int a, int b) {
        int count01 =0;
        int count10 =0;
        char prev = 0;

        for (int i = 0; i < str.length(); i++) {
            if (i!=0){
                if(str.charAt(i-1) == '0' && str.charAt(i) == '1'){
                    count01++;
                } else if (str.charAt(i-1) == '1' && str.charAt(i) == '0'){
                    count10++;
                }
            }
        }
        return (count01*a) + (count10*b);
    }
}
