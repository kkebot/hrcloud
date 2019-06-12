package hr.web.service.utils;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PendingResourceBuilder {
    private Path parent;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    public PendingResourceBuilder(Path parent) {
        this.parent = parent;
    }

    public PendingResource prepareMonthlyResource() {
        return prepareResourceUnder(simpleDateFormat.format(new Date()));
    }

    public PendingResource prepareResourceUnder(String folder) {
        return new PendingResource(parent.resolve(folder));
    }
}
