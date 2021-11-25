package io.ticofab.androidgpxparser.parser.domain;

/**
 *
 * NOTE:
 *
 *   Extensions to the GPX 1.1 format are completely arbitrary. The "speed" extension is
 *   added as it seems to be quite common, but anything else is by default not supported. If an
 *   extension is supported, it must be considered best-effort and it might fail in some cases.
 *
 *   For instance, someone might have a track where speed is reported as
 *
 *     <speed>15 km/h</speed>
 *
 *   The current Speed implementation uses Double numbers. The example above would fail.
 *
 */
public class Extensions {

    private final Double mSpeed;

    private Extensions(Extensions.Builder builder) {
        mSpeed = builder.mSpeed;
    }

    public Double getSpeed() {
        return mSpeed;
    }

    public static class Builder {
        private Double mSpeed;

        public Builder setSpeed(Double speed) {
            mSpeed = speed;
            return this;
        }

        public Extensions build() {
            return new Extensions(this);
        }
    }
}
