package course.examples.Fragments.DynamicLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class QuotesFragment2 extends Fragment {

	private static final String TAG = "QuotesFragment2";

	private WebView mQuoteView = null;
	private int mCurrIdx = -1;
	private int mQuoteArrayLen;
	private ListViewModel model;

	public QuotesFragment2() {
		super() ;
		Log.i("QuotesFragment2", "I got created!") ;
	}

	int getShownIndex() {
		return mCurrIdx;
	}

	void showQuoteAtIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrayLen)
			return;
		mCurrIdx = newIndex;
		mQuoteView.loadUrl(QuoteViewerActivity2.mQuoteArray[mCurrIdx]);
	}
	
	@Override
	public void onAttach(@NonNull Context activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

		return inflater.inflate(R.layout.quote_fragment,
				container, false);
	}

	@SuppressLint("WrongViewCast")
	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){

		Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
		super.onViewCreated(view,savedInstanceState);
		setHasOptionsMenu(true);

		model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

		model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {

			Log.i("Ugo says", "Entered QuoteFragment observe()") ;
			if (item == mCurrIdx || item < 0 || item >= mQuoteArrayLen)
				return;
			mQuoteView.setWebViewClient(new WebViewClient());
			mQuoteView.getSettings().setJavaScriptEnabled(true);

			mCurrIdx = item;
			mQuoteView.loadUrl(QuoteViewerActivity2.mQuoteArray[mCurrIdx]);
		});

		mQuoteView = (WebView) getActivity().findViewById(R.id.quoteView);
		mQuoteArrayLen = QuoteViewerActivity2.mQuoteArray.length;
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		mQuoteView.saveState(outState);
	}

	@Override
	public void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	
	@Override
	public void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}
	
	@Override
	public void onDetach() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
		super.onDetach();
	}

	
	@Override
	public void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
		super.onDestroyView();
	}


}
