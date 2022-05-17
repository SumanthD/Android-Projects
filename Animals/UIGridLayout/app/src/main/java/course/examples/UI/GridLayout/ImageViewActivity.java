package course.examples.UI.GridLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.AdapterView;

public class ImageViewActivity extends Activity {

	String[] gridViewString = {"Lion", "Tiger", "Cat", "Dog", "Hippo", "Elephant",
			"Giraffe", "Panda"} ;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	
		// Get the Intent used to start this Activity
		Intent intent = getIntent();
		// Make a new ImageView
		// Example of programmatic layout definition
		ImageView imageView = new ImageView(getApplicationContext());
		int position = getIntent().getExtras().getInt("imgPos");
		Log.i("sss", String.valueOf(position));
		// Get the ID of the image to display and set it as the image for this ImageView
		imageView.setImageResource(GridLayoutActivity.mThumbIdsAnimals.get(position));

		setContentView(imageView);
		imageView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String temp=GridLayoutActivity.facts1.get(gridViewString[position]);
				Intent intent3 = new Intent(ImageViewActivity.this, FactsViewActivity.class);
				intent3.putExtra("f", temp);
				startActivity(intent3);
			}
		});
	}
}