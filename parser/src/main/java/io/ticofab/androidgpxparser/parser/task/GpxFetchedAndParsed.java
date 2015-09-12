package io.ticofab.androidgpxparser.parser.task;

import io.ticofab.androidgpxparser.parser.domain.Gpx;

public interface GpxFetchedAndParsed {
    void onGpxFetchedAndParsed(Gpx gpx);
}
