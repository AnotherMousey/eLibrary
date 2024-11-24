package ReportManagement;

public abstract class LocalReporter implements Reporter {
    protected Reporter reporter;

    public LocalReporter(Reporter reporter) {
        this.reporter = reporter;
    }
}
