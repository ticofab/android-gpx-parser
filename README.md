[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-android--gpx--parser-green.svg?style=flat)](https://android-arsenal.com/details/1/2500)
[![](https://jitpack.io/v/ticofab/android-gpx-parser.svg)](https://jitpack.io/#ticofab/android-gpx-parser)

# Android GPX Parser

## Table of Contents:
1. [Description](#description) : What are XML GPX Files? Why is an Android GPX Parser necessary and relevant?
2. [File Structures](#filestructure)
3. [Download](#download)
4. [Dependencies](#dependencies)
5. [Usage](#usage)
6. [Contribute](#contribute)
7. [License](#license) 


## Description
A library to parse XML Gpx files, built for Android. The reference schema is the [Topografix GPX 1.1](http://www.topografix.com/GPX/1/1/).  
In addition, it parses the `speed` extension, when provided as a `double` number.

What are XML Gpx Files? 
GPX is an XML file format for sorting coordiante data. This type of data can store coordiantes, routes, waypoints, and tracks and easily process and convert them to other forms. GPX files are common for GPA data formatting, Normal GPX files can be opened in something even as basic as a text editor, however in this project we are not concerned with GPX formatting. Out code will use GPX formatting as it comes to process the file. 

Why is an Android GPX Parser necessary and relevant?
Parsing a GPX file format is extremely useful in being able to access and manipulate the right GPX data. The goal here is to create and use an easy JavaScript structure using the data present, and be able to access these structures in non-linear order. To be able to open a GPX file on your phone, you will need some "middle man" software (most probably in the form of an app) that reads and analyzes the data. The software in this repo will be extremely useful for that Android app. This application should also have the ability to catch a GPX file from a server and parse. 

Pull Requests are welcome as talked about in Contribute section 6! 


## File Structure
Main Files: 
1. App: Contains a src file with assets and Android-specific files
2. Gradle/wrapper: Only contains gradle-wrap.jar file with version bump info
3. Parser: Contains a src file with GPX parsing code and values, parser instance creation, and input stream and server GPX parsing code


## Projects using this library:

* [TomTom AmiGO](https://play.google.com/store/apps/details?id=com.tomtom.speedcams.android.map)
* [WRPElevationChart](https://play.google.com/store/apps/details?id=de.wrpsoft.wrpelevationchartmaker&hl=de&gl=US)
* [Maplocs Cycling Route Planner](https://play.google.com/store/apps/details?id=abhiank.maplocs)
* [Boatspeed Sailing and Tracking](https://play.google.com/store/apps/details?id=de.herberlin.boatspeed&hl=de)
* [Routes - GPX/KML Navigation & GPS Tracker](https://play.google.com/store/apps/details?id=de.flosdorf.routenavigation&hl=de)
* [GPS Video Logger](https://play.google.com/store/apps/details?id=app.gps_video_logger)

_To have your project listed here, please send me an email or open a PR._

## Download

Add the Jitpack repository to your root build file. The way you do this depends on the Gradle plugin you are using.

If using Gradle plugin 7.0.0. or newer (default for new apps since Android Studio Artic Fox)
```
// in settings.gradle
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

For older verions of Gradle: 
```
//in project-level build.gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Finally, in your dependencies list

```
dependencies {
    implementation 'com.github.ticofab:android-gpx-parser:2.3.0'
}
```

## Dependencies

The following is an open github repo that has date and time handling. Keep in mind that the following dependency also has dependencies of its own - follow the instructions given.

* [Joda DateTime for Android](https://github.com/dlew/joda-time-android)

## Usage

In Java:

```java
GPXParser parser = new GPXParser(); // consider injection
Gpx parsedGpx = null;
try {
    InputStream in = getAssets().open("test.gpx");
    parsedGpx = parser.parse(in); // consider using a background thread
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

In Kotlin:

```kotlin
val parser = GPXParser() // consider injection
try {
    val input: InputStream = getAssets().open("test.gpx")
    val parsedGpx: Gpx? = parser.parse(input) // consider using a background thread
    parsedGpx?.let {
        // do something with the parsed track
        // see included example app and tests
    } ?: {
        // error parsing track
    }
} catch (e: IOException) {
    // do something with this exception
    e.printStackTrace()
} catch (e: XmlPullParserException) {
    // do something with this exception
    e.printStackTrace()
}
```

## Contribute

Contributions are welcome! Please check the [issues](https://github.com/ticofab/android-gpx-parser/issues) and open a pull request when done: you will have made the world a better place.

## License

    Copyright 2015 - 2023 Fabio Tiriticco - Fabway

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
