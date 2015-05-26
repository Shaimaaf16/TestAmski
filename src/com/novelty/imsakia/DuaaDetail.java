package com.novelty.imsakia;

import com.novelty.imsakia.R;
import com.novelty.imsakia.dataobjects.DuaaData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DuaaDetail extends Activity {

	private ImageView share_img;
	private DuaaData object;
	private TextView title;
	private TextView description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duaa_detail);
		Bundle b = getIntent().getExtras();
		if (b != null) {
			object = b.getParcelable(DuaaData.class.getName());
		}
		share_img = (ImageView) findViewById(R.id.share);
		share_img.setOnClickListener(listener);
		title = (TextView) findViewById(R.id.duaaTitle);
		description = (TextView) findViewById(R.id.duaadesc);
		if (object != null) {
			title.setText(object.getName());
			description.setText(Html.fromHtml(object.getDescription()));
		}

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent share = new Intent(android.content.Intent.ACTION_SEND);
			share.setType("text/plain");
			share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

			// share.putExtra(Intent.EXTRA_SUBJECT, object.getTitle());
			share.putExtra(Intent.EXTRA_TEXT, object.getDescription());

			startActivity(Intent.createChooser(share, "Share " + object.getName()
					+ " to..."));
		}
	};

}
