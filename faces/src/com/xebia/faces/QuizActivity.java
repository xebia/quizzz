package com.xebia.faces;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xebia.faces.dao.People;
import com.xebia.faces.dao.Person;


public class QuizActivity extends Activity implements OnClickListener {

    Map<Button, Person> buttonPersonMap = new HashMap<Button, Person>();
    private Person rightPerson;
    private People people;

    public void onClick(View v) {

        Person choosenPerson = buttonPersonMap.get(v);

        if (choosenPerson.equals(rightPerson)) {
            layoutRightResult();
        } else {
            layoutWrongResult(choosenPerson);
        }
    }

    private void layoutRightResult() {
        setContentView(R.layout.result);

        TextView result = (TextView) findViewById(R.id.result);
        result.setText(R.string.right);

        ImageView picture = (ImageView) findViewById(R.id.picture);
        picture.setImageBitmap(getBitmapFromAsset(rightPerson.getPictureAssetsName()));

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(rightPerson.getName());

        Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
        nextStepButton.setText(R.string.next);
        nextStepButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutQuestion();
            }
        });
    }

    private void layoutWrongResult(final Person wrongPerson) {
        setContentView(R.layout.result);

        TextView result = (TextView) findViewById(R.id.result);
        result.setText(R.string.wrong);

        ImageView picture = (ImageView) findViewById(R.id.picture);
        picture.setImageBitmap(getBitmapFromAsset(rightPerson.getPictureAssetsName()));

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(rightPerson.getName());

        Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
        nextStepButton.setText("But then who is: " + wrongPerson.getName() + "?");
        nextStepButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rightPerson = wrongPerson;
                layoutRightResult();
            }
        });
    }

    private Bitmap getBitmapFromAsset(String strName) {
        AssetManager assetManager = getAssets();
        Bitmap bitmap = null;
        try {
            InputStream istr;
            istr = assetManager.open(strName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.icon)).getBitmap();
        }
        return bitmap;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        people = new People();

        try {
            String[] list = getResources().getAssets().list("people");
            for (String pictureAssetName:list) {
                String name = baseNameWithoutExtension(pictureAssetName);
                people.add(new Person(name, "people/" + pictureAssetName));
            }
        } catch (IOException e) {
        }

        layoutQuestion();
    }

    public static String baseNameWithoutExtension(String pictureAssetName) {
        Uri uri = Uri.parse(pictureAssetName);
        List<String> segments = uri.getPathSegments();
        String basename = segments.get(segments.size() -1);
        int lastDot = basename.lastIndexOf('.');
        if (lastDot < 0) {
            return pictureAssetName;
        }
        String name = basename.substring(0, lastDot);
        return name;
    }

    private void layoutQuestion() {
        setContentView(R.layout.main);

        List<Person> buttonPeople = people.randomlySelectSomePeople();
        rightPerson = buttonPeople.get(new Random().nextInt(3));

        Button answer1Button = (Button) findViewById(R.id.answer1);
        Button answer2Button = (Button) findViewById(R.id.answer2);
        Button answer3Button = (Button) findViewById(R.id.answer3);
        ImageView picture = (ImageView) findViewById(R.id.picture);

        answer1Button.setText(buttonPeople.get(0).getName());
        answer2Button.setText(buttonPeople.get(1).getName());
        answer3Button.setText(buttonPeople.get(2).getName());

        buttonPersonMap.put(answer1Button, buttonPeople.get(0));
        buttonPersonMap.put(answer2Button, buttonPeople.get(1));
        buttonPersonMap.put(answer3Button, buttonPeople.get(2));

        picture.setImageBitmap(getBitmapFromAsset(rightPerson.getPictureAssetsName()));

        answer1Button.setOnClickListener(this);
        answer2Button.setOnClickListener(this);
        answer3Button.setOnClickListener(this);
    }
}
