public class Main {

    public static void main(String[] args) {

        Report report = new Report();
        report.readReport("resources/test2.txt");
        report.writeReport("resources/out.txt");
    }
}
