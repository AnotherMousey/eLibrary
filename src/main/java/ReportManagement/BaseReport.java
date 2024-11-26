package ReportManagement;

public class BaseReport implements Reporter {

    @Override
    public void report(String message) {
        System.out.println(message);
    }
}
