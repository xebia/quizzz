My first Android app
====================

Introduction
------------
With everybody going mobile, we could not stay behind. This meant we had
to create an Android application, because we do not own one of them fancy iPhones.
Also, Android is way cooler.

To begin our journey to become Android developers, we decided to
create Quizzz: an app to help you learn the names of your colleagues. Because we felt a little uncomfortable putting everyones picture in the Android appstore, we decided to use pictures of animals in stead. Indian birds in this case to emphasize our connection with our Indian colleagues. This
would give us a simple application that would require only a small subset of the Android API. We also wanted to familiarize ourselves with the Android development cycle
from interaction design to development, testing, and deployment.

After installing the [Android development
environment](http://developer.android.com/index.html) we where faced
with the problem of designing our application.

Design
------
The idea of Quizzz is to show a screen with a picture, from our Xebia facebook originally but now a picture of a bird, and then provide the user with three names to choose from. On a white board it looks like this:

<IMAGE HERE>

The top left part shows the starting screen: three buttons with random names, one of which is the name of the bird shown in the picture
above. If you choose the correct name, you end up in the top right
screen. If you press the wrong button, you end up in the lower right
screen which shows you the correct answer and a button that takes you to
the top right screen where you can see the picture and name of the bird you
selected.

We decided to implement the application as a single Activity. Activities
are subsets of an application which can combine into a workflow by
going from one activity to another. The back button on the phone takes you to the previous activity (not necessarily the previous content of the screen) so Activities structure the way the user navigates through the app. Because there is no back
functionality in our application, we decided to create the whole
thing in one Activity. We use layout switching to present the user
with different screens. This isn't true in general, of course. In later versions we'll add more Activities and Intentions and such, but for now we'll keep things simple. 

Code
----
To download the code, visit http://github.com/xebia/quizzz and clone the
repository.

We defined two layouts, main.xml and result.xml. The first shows the
left side of the design and the second the right part of the design (see picture below). 

<screen shots>

If you start the app, QuizActivity is started. The Android framework calls the onCreate() method to do the required initialization. In our case this includes loading a set of images from a directory. We choose a list of birds common to India as a sample set, but any set of pictures will do. 

<java>	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		quizSet = QuizList.fromAssetsDirectory(this, "sets/Birds of India");
		layoutQuestion();
	}
</java>

The set of pictures for the quiz is loaded by a helper class named QuizList. The fromAssetsDirectory used the Android platform to load image files from a directory called 'assets' in the root of the quizzz project. The list is static now because it gets deployed along with the application. We will change that in a future version. 

<java>
   public static QuizList fromAssetsDirectory(Context context, String directory) {
        QuizList quizList = new QuizList();
        try {
            String[] list = context.getResources().getAssets().list(directory);
            for (String pictureAssetName : list) {
                quizList.add(new AssetQuizItem(directory + "/" + pictureAssetName));
            }
        } catch (IOException e) {
            //Empty quizset
        }
        return quizList;
    }
</java>

The last act of onCreate() is to call layoutQuestion(). This method randomly selects three images from the set loaded before, assigns them to buttons and then hooks up itself in the setOnClickListener() method of each button.  
The buttons are stored in a HashMap<Button, AssetQuizItem> so they can be retrieved later in the onClick() method.

onClick() uses the clicked button as an index in the HashMap mentioned above to find the answer that was chosen by the users and delegates to either layoutIfAnswerIsCorrect() or layoutIfAnswerIsWrong(). Both methods fill in data on a new view based on the answer supplied by the user. The methods end with a block of code that sets up a new Button and a listener to allow the user to take the next step. 

Now the app sits and waits for a button to be pressed. Once this happens, the button's onClickListener() method is executed. This causes onClick() to be called and the app takes appropriate action based on the user's selection. 

Note that we haven't implemented other Activity life cycle methods (onPause(), onStop() and onDestroy()). We're leaving this for later. 

Result
------
To test our work we used the emulator in Eclipse. Right-click (or double-tap) the quizzz project in Eclipse and select RunAs -> Android application. Starting the emulator can be slow at times (it seems to use only a single core, this is a touch to realistic in our opinion...) but once started it is fast enough. 

Deploying on your phone takes only a little longer. Choose Export -> Android -> Android Application and follow the steps of the wizard. 

One thing that took a while to figure out: how to make a screen shot of the desktop of your phone? It turns out there is a function for that: hold down the back button and then press the button next to it. 
Android confirms a screen shot has been taken and stores the image in the ScreenCapture directory.

Next steps
----------
Now the basics work, we plan to explore further by adding the following functions (list is in no particular order): - add an options button to load a set of pictures (so we can have the colleague quiz after all). - build the app with Maven. - find out how to unit/integration test Android 
