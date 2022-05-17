package course.examples.UI.GridLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
	private static final int PADDING = 0;
	private static final int WIDTH = 520;
	private static final int HEIGHT = 520;
	private Context mContext;          // This will have to be passed to the ImageView
	private List<Integer> mThumbIds;
	private final List<String> mnames;// Adapter must store AdapterView's items

	// Save the list of image IDs and the context
	public ImageAdapter(Context c, List<Integer> ids, List<String> names) {
		mContext = c;
		this.mThumbIds = ids;
		this.mnames=names;
	}

	// Now the methods inherited from abstract superclass BaseAdapter

	// Return the number of items in the Adapter
	@Override
	public int getCount() {
		return mThumbIds.size();
	}

	// Return the data item at position
	@Override
	public Object getItem(int position) {
		return mThumbIds.get(position);
	}

	// Will get called to provide the ID that
	// is passed to OnItemClickListener.onItemClick()
	@Override
	public long getItemId(int position) {
		return mThumbIds.get(position);
	}

	// Return an ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View grid;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


		// if convertView's not recycled, initialize some attributes
		if (convertView == null) {
			grid = inflater.inflate(R.layout.imgtxtview, null);

			ImageView imageView = (ImageView)grid.findViewById(R.id.Image);
			TextView textView = (TextView) grid.findViewById(R.id.Text);
			imageView.setImageResource(mThumbIds.get(position));
			textView.setText(mnames.get(position));
		}

		else {
			grid = (View) convertView;
		}

		return grid;
	}
}
