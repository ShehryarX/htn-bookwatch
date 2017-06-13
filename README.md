## Hack the North: Bookwatch Android Application

An experimental app for Android that performs optical character recognition (OCR) on images captured using the device camera.

Runs the Tesseract OCR engine using Tesseract. Several open source and private projects such as translation services like Google Translate were also used in the process

This application is intended to help university students select the best books for the cheapest cost. Since financial stability is a common struggle amongst university students, it is imperative to be efficient with money. Bookwatch uses the GoodReads API and the Yelp API to map out locations to the nearest bookstores and find the best books. A tree data structure was used to organize the books based on quality.

## Requires
* A Google Translate API key (for translation) - [Documentation](https://code.google.com/apis/console/?api=translate)
* A Windows Azure Marketplace Client ID and Client Secret (for translation) - [Documentation](http://msdn.microsoft.com/en-us/library/hh454950.aspx)

## Installation

To build and run the app, clone this project, open it as an existing project in Android Studio, and click Run.

## Screenshots

![Identification in Action](https://i.imgur.com/1rRSxXD.png)
![Translation in Action](https://i.imgur.com/gR9hcVm.png)
