# Android GPX Parser

A library to parse XML Gpx files, built for Android. The reference schema is the [Topografix GPX 1.1](http://www.topografix.com/GPX/1/1/). Pull requests are welcome!

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-android--gpx--parser-green.svg?style=flat)](https://android-arsenal.com/details/1/2500)

## Projects using this library:

* [Maplocs Cycling Route Planner](https://play.google.com/store/apps/details?id=abhiank.maplocs)
* [Boatspeed Sailing and Tracking](https://play.google.com/store/apps/details?id=de.herberlin.boatspeed&hl=de)

_To have your project listed here, send me an email or open a PR._


## Download

Grab via Gradle:

```groovy
api 'io.ticofab.androidgpxparser:parser:1.6.0'
// compile 'io.ticofab.androidgpxparser:parser:1.6.0' - for gradle plugin < 3.0.0
```

## Dependencies

* [Joda DateTime for Android](https://github.com/dlew/joda-time-android)

## Usage

Get a parser instance:

```java
GPXParser mParser = new GPXParser(); // consider injection
```

Then there are two options: given an InputStream,

```java
Gpx parsedGpx = null;
try {
    InputStream in = getAssets().open("test.gpx");
    parsedGpx = mParser.parse(in);
} catch (IOException | XmlPullParserException e) {
    // do something with this exception
    e.printStackTrace();
}
if (parsedGpx == null) {
    // error parsing track
} else {
    // do something with the parsed track
    // see included example app and tests
}
```

or you might want to fetch the Gpx track from a server and parse it. In that case, pass the track Url and a listener. Both fetching and parsing happen on a background thread.

```java
mParser.parse("http://myserver.com/track.gpx", new GpxFetchedAndParsed() {
      @Override
      public void onGpxFetchedAndParsed(Gpx gpx) {
          if (gpx == null) {
              // error parsing track
          } else {
              // do something with the parsed track
              // see included example app and tests
          }
      }
  });
```

## Contribute

Contributions are welcome! Please check the [issues](https://github.com/ticofab/android-gpx-parser/issues) and open a pull request when done: you will have made the world a better place.

## License

    Copyright 2015 - 2019 Fabio Tiriticco - Fabway

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
