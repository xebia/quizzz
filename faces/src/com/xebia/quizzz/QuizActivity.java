package com.xebia.quizzz;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xebia.quizzz.R;
import com.xebia.quizzz.dao.AssetQuizItem;
import com.xebia.quizzz.dao.QuizItem;
import com.xebia.quizzz.dao.QuizList;

/**
 * Set up Quiz, show views and handle user input
 */
public class QuizActivity extends Activity implements OnClickListener {

	Map<Button, AssetQuizItem> buttonQuizItemMap = new HashMap<Button, AssetQuizItem>();
	private QuizItem correctItem;
	private QuizList quizSet;

	public void onClick(View v) {

		QuizItem choosenItem = buttonQuizItemMap.get(v);

		if (choosenItem.equals(correctItem)) {
			layoutIfAnswerIsCorrect();
		} else {
			layoutIfAnswerIsWrong(choosenItem);
		}
	}

	private void layoutIfAnswerIsCorrect() {
		setContentView(R.layout.result);

		TextView result = (TextView) findViewById(R.id.result);
		result.setText(R.string.right);

		ImageView picture = (ImageView) findViewById(R.id.picture);
		correctItem.setImageBitmapOn(this, picture);

		TextView name = (TextView) findViewById(R.id.name);
		name.setText(correctItem.getName());

		Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
		nextStepButton.setText(R.string.next);
		nextStepButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				layoutQuestion();
			}
		});
	}

	private void layoutIfAnswerIsWrong(final QuizItem wrongItem) {
		setContentView(R.layout.result);

		TextView result = (TextView) findViewById(R.id.result);
		result.setText(R.string.wrong);

		ImageView picture = (ImageView) findViewById(R.id.picture);
		correctItem.setImageBitmapOn(this, picture);

		TextView name = (TextView) findViewById(R.id.name);
		name.setText(correctItem.getName());

		Button nextStepButton = (Button) findViewById(R.id.nextStepButton);
		nextStepButton.setText("But then who is: " + wrongItem.getName() + "?");
		nextStepButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				correctItem = wrongItem;
				layoutIfAnswerIsCorrect();
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		quizSet = QuizList.fromAssetsDirectory(this, "sets/Birds of India");
		layoutQuestion();
	}

	private void layoutQuestion() {
		setContentView(R.layout.main);

		QuizList buttonQuizItems = quizSet.shuffleAndSelect(3);
		correctItem = buttonQuizItems.get(new Random().nextInt(buttonQuizItems
				.size()));

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
		correctItem.setImageBitmapOn(this, picture);

		answer1Button.setOnClickListener(this);
		answer2Button.setOnClickListener(this);
		answer3Button.setOnClickListener(this);
	}
}
