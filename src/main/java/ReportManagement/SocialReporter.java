package ReportManagement;

public abstract class SocialReporter implements Reporter, Runnable {
    protected Reporter reporter;

    public SocialReporter(Reporter reporter) {
        this.reporter = reporter;
    }
}
