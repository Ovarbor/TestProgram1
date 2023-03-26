public class Main {

    public static void main(String[] args) throws Exception {

        Report report = new Report();
        report.readReport("resources/test.txt");
        report.writeReport("resources/out.txt");
    }
}
