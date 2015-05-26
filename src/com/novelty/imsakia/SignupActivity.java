package com.novelty.imsakia;

import java.util.HashMap;

import com.novelty.imsakia.R;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.tasks.SaveUser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class SignupActivity  extends Activity implements DataRequestor, OnClickListener{

	EditText usernameEdt;
	EditText passwEdt, emailEdt;
	private ProgressDialog mSpinnerProgress;
	ImageView cancel_btn,done_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);

		passwEdt = (EditText) findViewById(R.id.passwEdt);
		emailEdt = (EditText) findViewById(R.id.emailEdt);
		usernameEdt=(EditText)findViewById(R.id.usernameEdt);
		cancel_btn=(ImageView)findViewById(R.id.cancel_btn);
		cancel_btn.setOnClickListener(this);
		done_btn=(ImageView)findViewById(R.id.done_btn);
		done_btn.setOnClickListener(this);

		
	}
	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(Task task) {
		if (task.getId() == TaskID.SaveUserTask) {
			mSpinnerProgress.cancel();
			Intent home = new Intent(SignupActivity.this, MainActivity.class);
			startActivity(home);
			SharedPreferences appSettin = getSharedPreferences("Amskia",
					Context.MODE_PRIVATE);
			Editor editor = appSettin.edit();
			editor.putString("Name", emailEdt.getText().toString());
			editor.putString("Password", passwEdt.getText().toString());
			UserData user = (UserData) task.getResult();
			if(user!=null)
			{
			String userid = user.getUserId();
			editor.putString("Auth", user.getAuth());
			editor.putString("UserId", userid);

			editor.commit();
			finish();
			}	
		}
	}
	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {

		if(v==cancel_btn)
		{
			finish();
		}
		else  if(v==done_btn)
		{
			singup();
			}
	}
	private void singup() {
		String mail = emailEdt.getText().toString();
		String password = passwEdt.getText().toString();
		if (mail != null && mail.length() > 0 && password != null
				&& password.length() > 0) {

			mSpinnerProgress = new ProgressDialog(SignupActivity.this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();

			Task task = new SaveUser(this, this.getApplicationContext(), new HashMap<String, String>());
			AsyncTaskInvoker.RunTaskInvoker(task);
		}

	}

}
