# Smart Classroom App
This app was contrived to allow for greater interaction between teachers and students. 

## Features
1. Question posting

* Students can ask questions and both teachers and students will be allowed to respond to them
* Questions that are good can be upvoted

2. Lesson activities

* Teachers can create quizzes or short answer questions to check students' understanding
* These activities can be triggered on teacher's command in the mobile application

3. Progress tracking

* Students can track their own progress across different quizzes for the same subject
* Teachers will be notified of the poor grades of students who have consistently been performing badly

## Implementation details
The mobile application is done mainly using Android Studio, with AWS DynamoDB providing the database in which information is stored. A WebSocket hosted on an AWS server provides instant communication between the different devices and hence different users. 

## Testing
System Testing wise, the moblie application is tested in two main ways: Unit testing and App testing. In unit testing, the reading and writing of the database is checked, as well as to ensure the Presenter that contains the database logic binds with the View that provides the UI for the fragment when the fragment is launched. This is done using Mockito and Junit. In app testing(or screen testing), the interaction of the app on the phone is programatically simulated using Espresso and Junit to reflect actual use. System Robustness testing involves testing for misuse cases such as brute force attack by malicious users to gain access to a user's account.

As with any other sosftware applications out there, there still remains minor bugs to be fixed. Do let us know if you discovered new ones!

## To-do list
1. Login fragment

* Mainly minor bug fixing on UI side such as accounting for "Monkey Testing" where a user can login and press as many buttons as he/she desires and ensures mobile application still does not crash. Bug fixes is in progress and is pretty easy to fix.

2. Dashboard

* Rectification of Recyclerview to ensure new courses are dynamically loaded when added
* Mainly bug fixing

3. Sessions

* Implement start of session and end of session on teacher's command and ensure these commands are received by students
* Bug fixing

4. Activities (Questions and quiz)

* Ensure reliability of WebServer
* Contextualize quizzes shown to the session one is currently in. 
* Bug fixing 

5. Progress

* Change absolute scores to percentages for better visualization

6. Testing

* Finish up unit testing for presenters and fragments for some use cases such as teacher post questions.
* Finish up app testing for one or two use cases.

7. All

* Beautify the UI in order to make the user experience a pleasant one :)


## Workload delegation
Workload delegation is as it is in the report in order to meet the requirements of To-Do-List for the next few days.

## Video 
https://bit.ly/2HdyI3G

