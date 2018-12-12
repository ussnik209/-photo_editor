# Test Plan
### 1. Introduction
This document presents a test plan for the PhotoEditor application. It is intended for people conducting testing.
The tester needs to have a device with Windows 7 operating system and higher. The purpose of testing is verification
application performance and identification of errors made in the implementation of this application.
### 2. Test items
The object of the test is the PhotoEditor application for editing photos.

1. Functional suitability
* Functional fullness
The application must perform the basic functions indicated in the SRS.
* Functional correctness
The application should display the correct data.
* Functional expediency
The user is given the opportunity to perform the minimum necessary steps to solve the task.
2. Usability
* User Interface Aesthetics
Correct display of data.
* Protected from user error
Checking images opened by the user.
3. Reliability.
* Open only certain image formats.
### 3. Risks Issues
Risks include:
* No images required resolution.
* Lack of space on the hard disk when saving images.
### 4. Aspects of testing
Testing of functions requirements stated in SRS:
1. Opening image
* opening images of different resolutions
* opening images of various formats
2. Using filters
* Using AbstractFilter
* Using AbstractWeightedFilter
* Using BlackandwhiteFilter
* Using ContrastFilter
* Using EdgeDetectFilter
* Using EdgeHighlightFilter
* Using FlipHorizontalFilter
* Using FlipVerticalFilter
* Useing GrayFilter
* Using InvertFilter
* Using SepiaFilter
* Using SharpenFilter
* Using SoftenFilter
* Using multiple filters on one image.
3. Rotate image
* Rotate image 90° 
* Rotate image 180° 

Testing non-functional requirements stated in SRS:

1. The interface of all windows should be in the same color scheme.

2. Resizing the application window.

3. All errors and actions of the application must be accompanied by explanatory messages.

4. Should open certain image formats.

5. Saved images must be protected from changes.

### 5. Test Approaches
To test the application, you must test every aspect of testing manually.
### 6. Presentation of results
Test results are presented in the [Test Results] document.
### 7. Conclusions
This test plan covers the entire functionality of the application and makes it possible to evaluate the correct operation of the application. Successful passing of all tests ensures that the application has the declared attributes of quality.
