package APIManagement;

import java.io.IOException;

public abstract class API {
    protected abstract void encode(String Query);
    public abstract int connect(Query query) throws IOException;
}
