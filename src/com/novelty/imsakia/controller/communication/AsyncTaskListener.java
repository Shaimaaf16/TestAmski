package com.novelty.imsakia.controller.communication;

public interface AsyncTaskListener {
	void onStart(Task task);

	void onFinish(Task task);

}
