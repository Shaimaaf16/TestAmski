 package com.novelty.imsakia.controller.communication;

import java.util.Hashtable;

import com.novelty.imsakia.controller.communication.Task.TaskID;

import android.os.AsyncTask;

public class AsyncTaskInvoker extends AsyncTask<Void, Void, Task> implements
		AsyncTaskListener {

	private AsyncTaskListener taskListener;
	private final static Hashtable<TaskID, Task> runningTasks = new Hashtable<TaskID, Task>();

	private Task task;

	public AsyncTaskInvoker(Task task) {
		super();
		this.taskListener = this;
		this.task = task;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		if (taskListener != null)
			taskListener.onStart(task);

	}

	@Override
	protected Task doInBackground(Void... params) {

		if (this.task != null) {
			this.task.execute();
		}
		return this.task;
	}


	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public static void RunTaskInvoker(Task task) {

		if (!isTaskRunning(task)) {
			addRunningTask(task);
			AsyncTaskInvoker asyncTask = new AsyncTaskInvoker(task);
			asyncTask.execute();
		}
	}

	private static void addRunningTask(Task task) {
		runningTasks.put(task.getId(), task);
	}

	public static void removeRunningTask(Task task) {
		runningTasks.remove(task.getId());
	}

	private static boolean isTaskRunning(Task task) {
		return runningTasks.containsKey(task.getId());
	}

	public void onStart(Task task) {
		task.getRequestor().onStart(task);
		
	}

	public void onFinish(Task task) {
		removeRunningTask(task);
		task.getRequestor().onFinish(task);		
	}

	@Override
	protected void onPostExecute(Task result) {
		super.onPostExecute(result);

		// Fire listener that task was finished, and send error code and result.
		if (taskListener != null)
			taskListener.onFinish(result);
	}


}
