package com.novelty.imsakia.fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	
	/** General Loading bar **/
	
	private ProgressDialog progressDialog;

	
	public boolean showLoadingBar() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setCancelable(false);
		}
		
		if (!progressDialog.isShowing()) {
			try {
				progressDialog.show();
			} catch (Exception e) {
			}
			return true;
		}
		return false;
	}
	
	public boolean hideLoadingBar() {
		if (getActivity() != null && progressDialog != null && progressDialog.isShowing()) {
			try {
				progressDialog.dismiss();
			} catch (Exception e) {
			}
			return true;
		}
		return false;
	}
	
	/** End General Loading bar **/
	
}
