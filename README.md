# ProductRxTask

Task
You need to create an android app for filling up forms. 
The difference is that the fields in the form need to match a "survey" table that is present on our server.
1. The first API provides a way to retrieve schema of this survey table. As per the schema returned, create a survey form.
Use as much information present in the table description as you can. 
2. Get user to fill the form and submit using the second API.. 
3. Display all the survey results submitted so far by using the third api in the attached.

<h1>Solution </h1>
<img src="https://github.com/rohitrfactor/ProductRxTask/blob/master/app.gif">

The solution of the above task is break down into following steps:
1. Used MVP architecture. So the business logic, view and endpoints are kept separate for writing robust and testable code.
2. For networking, I have used OkHttp3 library by square for connecting with REST api in asyncronous mode. All the UI updates 
   are done in Main UI Thread and differnet thread for networking.
3. Used Swipe Pull to refresh from android native support library.
4. Used RecyclerView to display the array of json data after parsing them.
5. App handles success, failure and somewhat ux friendly.
6. Tab and ViewPager to display FormFragment and result Fragment.

Connectivity Manager isn't integrated to tell about networking state.
A better User interface isn't implemented as this is just an assignment. Not going into production anytime.
Material Animations and transitions haven't been implemented.
