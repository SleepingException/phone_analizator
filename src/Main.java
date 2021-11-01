import util.PhoneNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        var data = initData();
        System.out.println(findNumber(args[0], data));
    }
    private static Map initData() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("phones.csv"));
        reader.readLine();
        List<PhoneNumber> numbers =  reader.lines()
                .map(x -> x.split(";"))
                .map(x -> new PhoneNumber(
                        Integer.parseInt(x[0]),
                        Integer.parseInt(x[1]),
                        x[4] + x[5]))
                .collect(Collectors.toList());
        Map<Integer, List<PhoneNumber>> map = new HashMap();
        for(int i = 900; i < 1000; i++){
            map.put(i, new ArrayList<>());
        }
        for(var elem : numbers){
            map.get(elem.getPrefix()).add(elem);
        }
        for(int i = 900; i < 1000; i++){
            map.get(i).sort((n1, n2) -> (n1.getStart() < n2.getStart()) ? -1 : ((n1.getStart() == n2.getStart()) ? 0 : 1));
        }
        return map;
    }
    private static String findNumber(String number, Map data) {
        if (number.length() != 10) {
            throw new IllegalArgumentException("Phone number must consist of 10 digits");
        }
        int prefix = Integer.parseInt(number.substring(0, 3));
        int num = Integer.parseInt(number.substring(3));
        List<PhoneNumber> nums = (List) data.get(prefix);
        if(nums.size() == 0){
            return "This number does not belong to any operator";
        }

        int mid, l = 0, r = nums.size()-1;
        while (l < r){
            mid = (r + l)/2;
            if (num >= nums.get(mid).getStart() && num < nums.get(mid+1).getStart()) {
                return nums.get(mid).getOperator();
            }
            else if (num >= nums.get(mid+1).getStart()) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        return "This number does not belong to any operator";
    }

}
