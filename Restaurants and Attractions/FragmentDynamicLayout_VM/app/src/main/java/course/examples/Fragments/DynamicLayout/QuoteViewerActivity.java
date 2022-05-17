package course.examples.Fragments.DynamicLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


public class QuoteViewerActivity
        extends AppCompatActivity {

	public static String[] mTitleArray;
	public static String[] mQuoteArray;
	private QuotesFragment mQuotesFragment;
	private TitlesFragment mTitlesFragment;


	FragmentManager mFragmentManager;

	private FrameLayout mTitleFrameLayout, mQuotesFrameLayout;
	private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
	private static final String TAG = "QuoteViewerActivity";

	private ListViewModel mModel ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Log.i(TAG, getClass().getSimpleName() + ": entered onCreate()");

		super.onCreate(savedInstanceState);

		mTitleArray = getResources().getStringArray(R.array.Titles);
		mQuoteArray = getResources().getStringArray(R.array.Restaurants);

		setContentView(R.layout.main);

		mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
		mQuotesFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);


		mFragmentManager = getSupportFragmentManager();
		mQuotesFragment=(QuotesFragment) mFragmentManager.findFragmentByTag("quote");
		if (mQuotesFragment == null) {
			mQuotesFragment = new QuotesFragment();
		}
		mTitlesFragment=(TitlesFragment) mFragmentManager.findFragmentByTag("title");
		if (mTitlesFragment == null) {
			mTitlesFragment = new TitlesFragment();
		}

		final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

		fragmentTransaction.replace(
				R.id.title_fragment_container,
				mTitlesFragment,"title");

		fragmentTransaction.commit();

		mFragmentManager.addOnBackStackChangedListener(
				new FragmentManager.OnBackStackChangedListener() {
					public void onBackStackChanged() {
						setLayout();
					}
				});

		mModel = new ViewModelProvider(this).get(ListViewModel.class) ;
		mModel.getSelectedItem().observe(this, item -> {
			if (!mQuotesFragment.isAdded()) {
				FragmentTransaction fragmentTransaction2 = mFragmentManager.beginTransaction() ;

				fragmentTransaction2.replace(R.id.quote_fragment_container,
					mQuotesFragment,"quote");

				fragmentTransaction2.addToBackStack(null);

				fragmentTransaction2.commit();

				mFragmentManager.executePendingTransactions();
			}
		});
		setLayout() ;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.restaurant:
				Intent intentr = new Intent(QuoteViewerActivity.this, QuoteViewerActivity.class);
				startActivity(intentr);
				break;
			case R.id.tourism:
				Intent intentt = new Intent(QuoteViewerActivity.this, QuoteViewerActivity2.class);
				startActivity(intentt);
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void setLayout() {

		if (!mQuotesFragment.isAdded()) {

			mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
					MATCH_PARENT, MATCH_PARENT));
			mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT));
		} else {

			mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 1f));

			mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 2f));
		}
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}

}