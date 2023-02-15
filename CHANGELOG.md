# Change Log

## 2.3.1 *(Feb 15th 2023)*

* Enhances README - thanks [neevasethi](https://github.com/neevasethi)!
* Updates to Gradle 8.0 and Android Gradle Plugin 7.4.1
* Updates system dependencies (appcompat, annotations, testing dependencies).
* Updated the Joda date library to the latest version (2.12.1.1).


## 2.3.0 *(Sep 2nd 2022)*

* Adds `sym` and `cmt` parsing for waypoints. Solves [#48](https://github.com/ticofab/android-gpx-parser/issues/48) - thanks [gabrielezereik](https://github.com/gabrielezereik)!
* Updates to the 7.2.2 version of the gradle plugin.
* Updates system dependencies (appcompat).
* Updated the Joda date library to the latest version (2.11.0.1).

## 2.2.0 *(Nov 25th 2021)*

* Adds parsing of the `speed` extension. Solves [#40](https://github.com/ticofab/android-gpx-parser/issues/40).
* Updates to the 7.0.3 version of the gradle plugin.
* Updates system dependencies (appcompat, annotations).
* Updated the Joda date library to the latest version (2.10.12.2).

## 2.0.1 *(Jul 10th 2021)*

* Make the Joda library available to users without adding it explicitly (which fixes [#37](https://github.com/ticofab/android-gpx-parser/issues/37)) - thanks [Markus Deutsch](https://github.com/moopat)!
* Remove the useless resource folder from the example app - thanks [Tom Sullivan](https://github.com/msbit)!
* Updates the gradle plugin to 4.2.2

## 2.0.0 *(Apr 26th 2021)* 

Breaking change! Triggered by Bintray sunsetting JCenter, I seized the opportunity to
* Remove the async version of the parser, which was using deprecated calls.
* Bump up the minimum API level from 14 to 16.
* Upgrade the Gradle plugin and all its machinery to the latest version.
* Switch to the androidx libraries.
* Move on from the deprecated InstrumentationRegistry.
* Removed the useless test section of the example app.
* Upgrade the Joda date library to the latest version.

## 1.6.0 *(Sep 16th 2019)* 

* Skipping the `extensions` tag from Metadata parsing - such tag is meant to include extra custom fields not mentioned in the GPX specs. Fixes [23](https://github.com/ticofab/android-gpx-parser/issues/23).
* Added a first project using this library in production. Looking forward to add more!

## 1.5.0 *(Apr 18th 2019)* 

* Parsing full Metadata information - thanks [Taneli Korry](https://github.com/tkorri)!

## 1.4.0 *(Jan 1st 2019)* 

* Parsing Waypoint Type - thanks [Pygmalion69](https://github.com/Pygmalion69)!

## 1.3.0 *(Dec 8th 2018)* 

* Parsing GPX Metadata - thanks [StuStirling](https://github.com/StuStirling)!

## 1.2.0 *(July 2nd 2018)*

* Parsing WayPoint description.

## 1.1.2 *(June 26th 2018)*

* Fix to prevent endless loop in case of malformed GPX track (thanks to D. Elliot!)

## 1.1.1 *(March 15th 2018)*

* Minor bug fixes, updated dependencies.  

## 1.1.0 *(October 4th 2017)*

* Added track and route attributes (which fixes [#14](https://github.com/ticofab/android-gpx-parser/issues/14)) - thanks [StuStirling](https://github.com/StuStirling)! 

## 1.0.0 *(2017-04-11)*

* Added track name to the parseable fields.

## 0.2.0 *(2017-02-03)*

* Added support for routes and waypoints - thanks [mopfattn](https://github.com/mopfattn)!
* Bumped plugins & dependencies

## 0.1.6 *(2016-19-12)*

 * Fixed [#2](https://github.com/ticofab/android-gpx-parser/issues/2) and [#3](https://github.com/ticofab/android-gpx-parser/issues/3) - thanks [StuStirling](https://github.com/StuStirling)!
 * Bumped plugins & dependencies

## 0.1.5 *(2016-06-05)*

 * Bumped plugins
 * Fix for uninitialized Joda Time

## 0.1.4 *(2016-24-01)*

 * Bumped plugins

## 0.1.3 *(2015-09-12)*

 * Removed Google Play Services dependency
 * Isolated taks and interface (breaking change)

## 0.1.1 *(2015-09-12)*

 * Initial release
