package course.examples.UI.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

//This application uses some deprecated methods. 
//See UIViewPager for a more modern version of this application

public class GridLayoutActivity extends Activity {

	protected static final String EXTRA_RES_ID = "POS";
	
	public static ArrayList<Integer> mThumbIdsAnimals = new ArrayList<Integer>(
			Arrays.asList(R.drawable.lion, R.drawable.tiger,
					R.drawable.cat, R.drawable.dog, R.drawable.hippo,
					R.drawable.elephant, R.drawable.giraffe, R.drawable.panda));

	String[] gridViewString = {"Lion", "Tiger", "Cat", "Dog", "Hippo", "Elephant",
			"Giraffe", "Panda"} ;
	private ArrayList<String> mnames=new ArrayList<>(Arrays.asList("Lion", "Tiger", "Cat", "Dog", "Hippo", "Elephant",
			"Giraffe", "Panda")) ;
	public static HashMap<String, String> facts1 = new HashMap<String, String>();
	public GridLayoutActivity() {
		facts1.put("Lion", "Name: Lion \n\nLifespan: 10-15 years\n\nWeight: 200-420lbs\n\nHabitat: Grassland, Open Woodland  \n\nFood habits: Carnivorous\n\nEndangered: Vulnerable");
		facts1.put("Tiger", "Name: Tiger \n\nLifespan: 8-10 years\n\nWeight: 200-680lbs\n\nHabitat: Grassland, Rain forests  \n\nFood habits: Carnivorous \n\nEndangered: Endangered");
		facts1.put("Cat", "Name: Cat \n\nLifespan: 12-18 years\n\nWeight: 8-10lbs\n\nHabitat: Savannas, Tropical Rain forests  \n\nFood habits: Obligate Carnivores\n\nEndangered: Not extinct");
		facts1.put("Dog", "Name: Dog \n\nLifespan: 10-13 years\n\nWeight: 6-150lbs\n\nHabitat: Grassland, Forests, Prairies  \n\nFood habits: Omnivorous\n\nEndangered: Not Extinct");
		facts1.put("Hippo", "Name: Hippo \n\nLifespan: 40-50 years\n\nWeight: 3000-4000lbs\n\nHabitat: Slow moving rivers and lakes \n\nFood habits: Mostly Herbivorous\n\nEndangered: Vulnerable");
		facts1.put("Elephant", "Name: Elephant \n\nLifespan: 50-70 years\n\nWeight: 6000-13000lbs\n\nHabitat: Grassland, Savanna, Forest  \n\nFood habits: Herbivorous\n\nEndangered: Not Extinct");
		facts1.put("Giraffe", "Name: Giraffe \n\nLifespan: 20-30 years\n\nWeight: 2500-4000lbs\n\nHabitat: Semi-arid savannah, Woodlands  \n\nFood habits: Herbivorous\n\nEndangered: Not Extinct");
		facts1.put("Panda", "Name: Panda \n\nLifespan: 20 years\n\nWeight: 150-300lbs\n\nHabitat: Temperate forests in China  \n\nFood habits: Mainly Herbivorous\n\nEndangered: Not Extinct");
	};

	String[] Wikiarr = {"https://en.wikipedia.org/wiki/Lion","https://en.wikipedia.org/wiki/Tiger","https://en.wikipedia.org/wiki/Cat","https://en.wikipedia.org/wiki/Dog","https://en.wikipedia.org/wiki/Hippopotamus","https://en.wikipedia.org/wiki/Elephant","https://en.wikipedia.org/wiki/Giraffe","https://en.wikipedia.org/wiki/Giant_panda"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		GridView gridview = (GridView) findViewById(R.id.gridview);

		// Create a new ImageAdapter and set it as the Adapter for this GridView
		gridview.setAdapter(new ImageAdapter(this, mThumbIdsAnimals,mnames));

		// Set an setOnItemClickListener on the GridView
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				//Create an Intent to start the ImageViewActivity
				String temp=facts1.get(gridViewString[position]);
				Intent intent = new Intent(GridLayoutActivity.this,
						ImageViewActivity.class);

				// Add the ID of the thumbnail to display as an Intent Extra
				intent.putExtra("imgPos",position);

				// Start the ImageViewActivity
				startActivity(intent);
			}

		});
		registerForContextMenu(gridview);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()) {
			case R.id.view:
				Intent intent = new Intent(GridLayoutActivity.this, ImageViewActivity.class);
				intent.putExtra("imgPos", (int) info.position);
				startActivity(intent);
				return true;

			case R.id.facts:

				String temp=facts1.get(gridViewString[(int)info.position]);
				Intent intent3 = new Intent(GridLayoutActivity.this, FactsViewActivity.class);
				intent3.putExtra("f", temp);
				startActivity(intent3);
				return true;

			case R.id.wiki:
				String url = Wikiarr[info.position];
				Intent intent2 = new Intent(Intent.ACTION_VIEW);
				intent2.setData(Uri.parse(url));
				startActivity(intent2);
				return true;

			default:
				return super.onContextItemSelected(item);
		}
	}
}