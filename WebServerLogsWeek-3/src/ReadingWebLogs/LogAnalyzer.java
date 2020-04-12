package ReadingWebLogs;
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource resource = new FileResource(filename);
        for (String line : resource.lines()) {
            LogEntry entry = WebLogParser.parseEntry(line);
            records.add(entry);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> ipAddresses = new ArrayList<>();
        for (LogEntry logEntry : records) {
            String ipAddress = logEntry.getIpAddress();
            if (!ipAddresses.contains(ipAddress)) {
                ipAddresses.add(ipAddress);
            }
        }
        return ipAddresses.size();
    }

    public void printAllHigherThanNum(int num) {
        System.out.println("The log entry whose status code is greater than " + num + " are ");
        for (LogEntry logEntry : records) {
            int currentStatusCode = logEntry.getStatusCode();
            if (currentStatusCode > num) {
                System.out.println(logEntry);
            }
        }
    }

    public ArrayList uniqueIPVisitsOnDay(String someDay) {
        ArrayList<String> ipAddressesOnDay = new ArrayList<>();
        for (LogEntry logEntry : records) {
            String ipAddress = logEntry.getIpAddress();
            Date currentDate = logEntry.getAccessTime();
            String s = currentDate.toString().substring(4, 10);
            if (s.equals(someDay) && !ipAddressesOnDay.contains(ipAddress)) {
                ipAddressesOnDay.add(ipAddress);
            }
        }
        System.out.println("The number of unique IP addresses visited on the day " + someDay + " are ");
        return ipAddressesOnDay;
    }

    public int countUniqueIPsInRange(int low, int high) {
        System.out.println("The number of unique IP addresses whose status code bewteen " + low + " and " + high + " are ");
        ArrayList<String> uniqueIP = new ArrayList<>();
        for (LogEntry logEntry : records) {
            String ipAddress = logEntry.getIpAddress();
            int currentStatusCode = logEntry.getStatusCode();
            if ((currentStatusCode >= low && currentStatusCode <= high) && !uniqueIP.contains(ipAddress)) {
                uniqueIP.add(ipAddress);
            }
        }
        return uniqueIP.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> IpAddressCount = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (IpAddressCount.containsKey(ip)) {
                IpAddressCount.put(ip, IpAddressCount.get(ip) + 1);

            } else {
                IpAddressCount.put(ip, 1);
            }
        }
        return IpAddressCount;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> count) {
        int max = Integer.MIN_VALUE;
        for (int num : count.values()) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public ArrayList iPsMostVisits(HashMap<String, Integer> ipCount) {
        int maxCount = mostNumberVisitsByIP(ipCount);
        ArrayList<String> mostVisitIp = new ArrayList<>();
        for (String s : ipCount.keySet()) {
            if (ipCount.get(s) == maxCount) {
                mostVisitIp.add(s);
            }
        }
        System.out.println("The Ip addresses that most visited are");
        return mostVisitIp;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysIps = new HashMap<>();

        for (LogEntry le : records) {
            ArrayList<String> date = new ArrayList<String>();
            String ip = le.getIpAddress();
            Date d = le.getAccessTime();
            String str = d.toString();
            str = str.substring(4, 10);
            if (!daysIps.containsKey(ip)) {
                date.add(str);
                daysIps.put(ip, date);

            } else {
                date = daysIps.get(ip);
                date.add(str);
            }

        }
        return daysIps;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipCount) {
        int maxDates = Integer.MIN_VALUE;
        String date = "";
        HashMap<String, Integer> datesCount = new HashMap<>();
        for (ArrayList<String> al : ipCount.values()) {
            for (int i = 0; i < al.size(); i++) {
                if (datesCount.containsKey(al.get(i))) {
                    datesCount.put(al.get(i), datesCount.get(al.get(i)) + 1);
                } else {
                    datesCount.put(al.get(i), 1);
                }
            }
        }
        for (String s : datesCount.keySet()) {
            if (datesCount.get(s) > maxDates) {
                maxDates = datesCount.get(s);
                date = s;
            }
        }
        return date;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        int mostVisits = Integer.MIN_VALUE;
        ArrayList<String> ipAddress = new ArrayList<>();
        HashMap<String, Integer> ipToDateCount = new HashMap<>();
        for (String s : map.keySet()) {
            int count = 0;
            ArrayList<String> al = map.get(s);
            for (String date : al) {
                if (date.equals(day)) {
                    count++;
                }
            }
            if (count > mostVisits) {
                mostVisits = count;
            }
            ipToDateCount.put(s, count);
        }
        for (String key : ipToDateCount.keySet()) {
            if (ipToDateCount.get(key) == mostVisits) {
                ipAddress.add(key);
            }
        }

        return ipAddress;
    }
}
