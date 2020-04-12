package ReadingWebLogs;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/short-test_log");
        System.out.println("The number of unique addresses are " + logAnalyzer.countUniqueIPs());
    }

    public void testprintAllHigherThanNum() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/short-test_log");
        logAnalyzer.printAllHigherThanNum(300);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog-short_log");
        ArrayList<String> uniqueIp = logAnalyzer.uniqueIPVisitsOnDay("Sep 30");
        System.out.println(uniqueIp.size());
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/short-test_log");
        System.out.println(logAnalyzer.countUniqueIPsInRange(300, 399));
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog3-short_log");
        System.out.println(logAnalyzer.countVisitsPerIP());
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog3-short_log");
        HashMap<String, Integer> count = logAnalyzer.countVisitsPerIP();
        System.out.println(logAnalyzer.mostNumberVisitsByIP(count));
    }

    public void testIpsMostVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog3-short_log");
        HashMap<String, Integer> count = logAnalyzer.countVisitsPerIP();
        System.out.println(logAnalyzer.iPsMostVisits(count));
    }

    public void testIpsForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog3-short_log");
        System.out.println(logAnalyzer.iPsForDays());
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog3-short_log");
        HashMap<String, ArrayList<String>> map = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.dayWithMostIPVisits(map));
    }

    public void testIpsWithMostVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("/home/kiran/Zemoso/Java/Programs/JavaCouesera/AssignmentQues/WebLogProgram/weblog3-short_log");
        HashMap<String, ArrayList<String>> map = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.iPsWithMostVisitsOnDay(map, "Sep 30"));
    }

    public static void main(String args[]) {
        Tester tester = new Tester();
        //tester.testLogAnalyzer();
        //tester.testUniqueIP();
        //tester.testprintAllHigherThanNum();
        // tester.testUniqueIPVisitsOnDay();
        //tester.testCountUniqueIPsInRange();
        // tester.testCountVisitsPerIP();
        // tester.testMostNumberVisitsByIP();
        //tester.   testIpsMostVisits();
        // tester.testIpsForDays();
        //tester.testDayWithMostIPVisits();
        tester.testIpsWithMostVisitsOnDay();
    }

}
