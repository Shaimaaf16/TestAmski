package com.novelty.imsakia.controller.communication;

import java.util.List;


import android.content.Context;

public abstract class Task {

	public enum TaskID {
		GetCountriesTask,GetPrayerTime, LoginTask, SaveUserTask, 
		GetAllZones, GetZoneByCountryId, 
		GetDistrictByZoneId, Weather, GetDuaaListTask, GetVendors, 
		GetAdvertisments, GetBranchesTask, GetTVGuidListTask, GetTVGuidProgramsTask, GetProgramFollowersTask;

	}
	

	public enum ConnectionError {
		ERROR_NONE, 
		ERROR_SUCCESS, 
		ERROR_NOT_ACCEPTABLE,
		ERROR_FAIL, 
		ERROR_NO_CONNECTION,
		ERROR_NET_CONNECTION_NOT_EXIST, 
		ERROR_USER_NOT_FOUND, 
		ERROR_SERVER_ERROR, 
		ERROR_UNAUTHORIZED_ERROR, 
		ERROR_BAD_REQUEST_ERROR, 
		ERROR_NO_CONTENT_ERROR, 
		ERROR_METHOD_NOT_ALLOWED_ERROR,
		ERROR_SERVER_NOT_READY, 
		ERROR_INVALID_USERNAME_PASSWORD,
		ERROR_Internal_Server_Error,
		ERRORR_EMPTY
	}

	public enum FacilityType
	{
		DAILYREPORT(0),
		DAILYPERFORMANCE(1),
		MARKETSHARE(2);

		private final int index;
		
		private FacilityType(int index) {
			this.index = index;
		}

		public int index() { 
			return index; 
		}
	}
	
	private TaskID id;
	private DataRequestor requestor;
	private Context context;
	private ConnectionError errorCode = ConnectionError.ERROR_SUCCESS;

	public TaskID getId() {
		return id;
	}

	public void setId(TaskID id) {
		this.id = id;
	}

	public DataRequestor getRequestor() {
		return requestor;
	}

	public void setRequestor(DataRequestor requestor) {
		this.requestor = requestor;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public boolean isStaticVersion() {
	
		return false;
	}

	public ConnectionError getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ConnectionError errorCode) {
		this.errorCode = errorCode;
	}

	public void mapServerError(int serverError) {
		switch (serverError) {
		
		case StatusCodeConstants.STATUS_CODE_OK:
			setErrorCode(ConnectionError.ERROR_SUCCESS);
			break;
			
		case StatusCodeConstants.STATUS_CODE_NO_CONTENT:
			setErrorCode(ConnectionError.ERROR_NO_CONTENT_ERROR);
			break;
			
		case StatusCodeConstants.STATUS_CODE_BAD_REQUEST:
			setErrorCode(ConnectionError.ERROR_BAD_REQUEST_ERROR);
			break;
			
		case StatusCodeConstants.STATUS_CODE_UNAUTHORIZED:
			setErrorCode(ConnectionError.ERROR_UNAUTHORIZED_ERROR);
			break;
			
		case StatusCodeConstants.STATUS_CODE_NOT_ACCEPTABLE:
			setErrorCode(ConnectionError.ERROR_NOT_ACCEPTABLE);
			break;
			
		case StatusCodeConstants.STATUS_CODE_METHOD_NOT_ALLOWED:
			setErrorCode(ConnectionError.ERROR_METHOD_NOT_ALLOWED_ERROR);
			break;
			
		case StatusCodeConstants.STATUS_CODE_INTERNAL_SERVER_ERROR:
			setErrorCode(ConnectionError.ERROR_SERVER_ERROR);
			break;
			
		case StatusCodeConstants.STATUS_CODE_UNKNOWN_HOST_EXCEPTION:
			setErrorCode(ConnectionError.ERROR_SERVER_NOT_READY);
			break;
			
		case StatusCodeConstants.STATUS_CODE_HTTP_HOST_CONNECTION_EXCEPTION:
			setErrorCode(ConnectionError.ERROR_NET_CONNECTION_NOT_EXIST);
			break;
			
		case StatusCodeConstants.STATUS_CODE_IO_EXCEPTION:
		case StatusCodeConstants.STATUS_CODE_EXCEPTION:
			setErrorCode(ConnectionError.ERROR_FAIL);
			break;
		case StatusCodeConstants.STATUS_CODE_NO_CONNECTION:
			setErrorCode(ConnectionError.ERROR_NO_CONNECTION);
			break;
			
		default:
			break;
		}
	}
	
	
	public String getErrorMessage(ConnectionError connectionError) {
		String message = "";
		switch (connectionError) {
			
		case ERROR_NO_CONTENT_ERROR:
			message = "context.getString(R.string.no_content_error)";
			break;
			
		case ERROR_BAD_REQUEST_ERROR:
			message = "context.getString(R.string.bad_request_error)";
			break;
			
		case ERROR_METHOD_NOT_ALLOWED_ERROR:
			message = "context.getString(R.string.method_not_allowed_error)";
			break;
			
		case ERROR_UNAUTHORIZED_ERROR:
			message = "context.getString(R.string.un_authorized_error)";
			break;
			
		case ERROR_SERVER_ERROR:
			message = "context.getString(R.string.server_error)";
			break;
			
		case ERROR_SERVER_NOT_READY:
			message = "context.getString(R.string.server_not_ready_error)";
			break;
			
		case ERROR_NET_CONNECTION_NOT_EXIST:
			message = "context.getString(R.string.net_connection_not_exist_error)";
			break;
			
		case ERROR_FAIL:
			message = "context.getString(R.string.fail_error)";
			break;
		case ERROR_NO_CONNECTION:
			message = "context.getString(R.string.net_connection_not_exist_error)";
			break;
		default:
			break;
		}
		return message;
	}
	
	private String mapNotAcceptableError(String errorCode) {
		String message = "";
		if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_101))
			message = "context.getString(R.string.not_acceptable_error_101)";
		
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_102))
			message = "context.getString(R.string.not_acceptable_error_102)";
		
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_103))
			message = "context.getString(R.string.not_acceptable_error_103)";
		
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_104))
			message = "context.getString(R.string.not_acceptable_error_104)";
		
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_105))
			message = "context.getString(R.string.not_acceptable_error_105)";
		
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_106))
			message = "context.getString(R.string.not_acceptable_error_106)";
		
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.ERROR_CODE_107))
			message = "context.getString(R.string.not_acceptable_error_107)";
		else if(errorCode.equals(NotAcceptableErrorCodesConstants.Internal_Server_Error))
			message = "Internal_Server_Error";

		return message;
	}
	
	
	public String getNotAcceptableErrorMessages(List<NotAcceptableError> errorlist)
	{
		String message="";
		for (NotAcceptableError error : errorlist) {
			message = mapNotAcceptableError(error.getErrorCode()) + "-";
		}
		if(message.length()>1)
			message = message.substring(0, message.length()-1);
		return message;
	}
	
	
	public abstract void execute();
	public abstract Object getResult();
}
