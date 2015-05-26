package com.novelty.imsakia.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.BranchesListAdapter;
import com.novelty.imsakia.model.BranchesModel;

public class BranchesListFragment extends Fragment{
	private ListView branchesListView;
	private BranchesListAdapter adapter = null;
	private ArrayList<BranchesModel> branchesList = new ArrayList<BranchesModel>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_branches, container, false);
		
		/** Initialization of views **/
		init(layout);
		
		return layout;
	}
	
	public void init(View view){
		/** Only for testing branges listView **/
		testData();
		
		branchesListView = (ListView) view.findViewById(R.id.branchesListV);
		branchesListView.setAdapter(adapter);
	}
	
	public void testData(){
		BranchesModel model = new BranchesModel("Branch Name..", "Branch Address..", 
				"http://www.google.com.eg/imgres?imgurl=http://freehighresolutionimages.org/natural-images-22-hd-wallpaper.html&imgrefurl=http://freehighresolutionimages.org/natural-images-22-hd-wallpaper.html&h=1440&w=2560&tbnid=lm7OtUy-Bj4TWM:&zoom=1&docid=F3Gc7wJNiPQAJM&ei=As9cVcauI8HMyAP05oC4Bw&tbm=isch", 
				100, "+201063878525");
		branchesList.add(model);
		model = new BranchesModel("Branch Name..", "Branch Address..", 
				"http://www.google.com.eg/imgres?imgurl=http://www.hdwallpapersos.com/wp-content/uploads/2015/02/nature-wallpapers-natural-green-wallpaper-wallpaper.jpg&imgrefurl=https://blogs.lt.vt.edu/cads19912/2015/02/27/is-agriculture-natural-what-is/&h=1200&w=1920&tbnid=1mLMZb2YSGnu9M:&zoom=1&docid=m_Yq2OWxIhd3iM&ei=As9cVcauI8HMyAP05oC4Bw&tbm=isch", 
				100, "+201063878525");
		branchesList.add(model);
		model = new BranchesModel("Branch Name..", "Branch Address..", 
				"http://www.google.com.eg/imgres?imgurl=http://freehighresolutionimages.org/images/img9/natural-images-35.jpg&imgrefurl=http://freehighresolutionimages.org/natural-images-23-hd-wallpaper.html&h=1200&w=1600&tbnid=lRuo6a9IMTeijM:&zoom=1&docid=4tNR5xkJNuCd3M&ei=As9cVcauI8HMyAP05oC4Bw&tbm=isch&ved=0CDIQMygCMAI&biw=1366&bih=653", 
				100, "+201063878525");
		branchesList.add(model);
	}
}