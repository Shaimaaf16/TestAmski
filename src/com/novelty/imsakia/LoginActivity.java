package com.novelty.imsakia;

import java.util.HashMap;

import com.novelty.imsakia.R;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.tasks.LoginTask;
import com.novelty.imsakia.tasks.PrayerTimes;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements DataRequestor,
		OnClickListener {

	Button loginBtn, newAccount;
	TextView forgetPassword;
	EditText passwEdt, emailEdt;
	private ProgressDialog mSpinnerProgress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initUI();
	}

	private void initUI() {
		loginBtn = (Button) findViewById(R.id.loginBtn);
		newAccount = (Button) findViewById(R.id.newAccount);
		forgetPassword = (TextView) findViewById(R.id.forgetPassword);
		passwEdt = (EditText) findViewById(R.id.passwEdt);
		emailEdt = (EditText) findViewById(R.id.emailEdt);
		loginBtn.setOnClickListener(this);
		newAccount.setOnClickListener(this);
	}

	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(Task task) {
		// TODO Auto-generated method stub
		if (task.getId() == TaskID.LoginTask) {
			mSpinnerProgress.cancel();
			Intent home = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(home);
			SharedPreferences appSettin = getSharedPreferences("CeekJobs",
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
			else
			{
				Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {

		if (v == loginBtn) {
			login();
		} else if (v == newAccount) {
			singup();
		}
	}

	
	private void singup() {

		Intent singu=new Intent(LoginActivity.this,SignupActivity.class);
		startActivity(singu);
	}

	private void login() {
		String mail = emailEdt.getText().toString();
		String password = passwEdt.getText().toString();
		if (mail != null && mail.length() > 0 && password != null
				&& password.length() > 0) {

			mSpinnerProgress = new ProgressDialog(LoginActivity.this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();

			Task task = new LoginTask(this, this.getApplicationContext(), mail,
					password);
			AsyncTaskInvoker.RunTaskInvoker(task);
		}
	}

}
