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

import com.xebia.faces.dao.QuizItem;
import com.xebia.faces.dao.QuizList;


public class QuizActivity extends Activity implements OnClickListener {

    Map<Button, QuizItem> buttonQuizItemMap = new HashMap<Button, QuizItem>();
    private QuizItem rightItem;
    private QuizList quizSet;

    public void onClick(View v) {

        QuizItem choosenItem = buttonQuizItemMap.get(v);

        if (choosenItem.equals(rightItem)) {
            layoutRightResult();
        } else {
            layoutWrongResult(choosenItem);
        }
    }

    private void layoutRightResult() {
        setContentView(R.layout.result);

        TextView result = (TextView) findViewById(R.id.result);
        result.setText(R.string.right);

        ImageView picture = (ImageView) findViewById(R.id.picture);
        picture.setImageBitmap(getBitmapFromAsset(rightItem.getPictureAssetsName()));

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(rightItem.getName());

        Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
        nextStepButton.setText(R.string.next);
        nextStepButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutQuestion();
            }
        });
    }

    private void layoutWrongResult(final QuizItem wrongItem) {
        setContentView(R.layout.result);

        TextView result = (TextView) findViewById(R.id.result);
        result.setText(R.string.wrong);

        ImageView picture = (ImageView) findViewById(R.id.picture);
        picture.setImageBitmap(getBitmapFromAsset(rightItem.getPictureAssetsName()));

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(rightItem.getName());

        Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
        nextStepButton.setText("But then who is: " + wrongItem.getName() + "?");
        nextStepButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rightItem = wrongItem;
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
        quizSet = new QuizList();

        try {
            String[] list = getResources().getAssets().list("sets/Birds of India");
            for (String pictureAssetName:list) {
                String name = baseNameWithoutExtension(pictureAssetName);
                quizSet.add(new QuizItem(name, "sets/Birds of India/" + pictureAssetName));
            }
        } catch (IOException e) {
            //Empty quizset
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

        QuizList buttonQuizItems = quizSet.shuffleAndSelect(3);
        rightItem = buttonQuizItems.get(new Random().nextInt(buttonQuizItems.size()));

        Button answer1Button = (Button) findViewById(R.id.answer1);
        Button answer2Button = (Button) findViewById(R.id.answer2);
        Button answer3Button = (Button) findViewById(R.id.answer3);
        ImageView picture = (ImageView) findViewById(R.id.picture);

        answer1Button.setText(buttonQuizItems.get(0).getName());
        answer2Button.setText(buttonQuizItems.get(1).getName());
        answer3Button.setText(buttonQuizItems.get(2).getName());

        buttonQuizItemMap.put(answer1Button, buttonQuizItems.get(0));
        buttonQuizItemMap.put(answer2Button, buttonQuizItems.get(1));
        buttonQuizItemMap.put(answer3Button, buttonQuizItems.get(2));

        picture.setImageBitmap(getBitmapFromAsset(rightItem.getPictureAssetsName()));

        answer1Button.setOnClickListener(this);
        answer2Button.setOnClickListener(this);
        answer3Button.setOnClickListener(this);
    }
}
