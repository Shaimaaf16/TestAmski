package com.novelty.imsakia.controller.communication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Log;

public class Communication {

	private static int TIMEOUT_MILLISEC = 5000;
	public static String Common_Base_URL="http://www.noveltycreators.com/imskya/api/localisation/";
	public static String CommonUser_Base_URL="http://www.noveltycreators.com/imskya/api/user/";
	public static String CommonUser_API_URL="http://www.noveltycreators.com/imskya/api/";


	private static DefaultHttpClient h;

	/**
	 * Execute web service of type Get
	 * 
	 * @param url
	 *            :The URL of web service
	 * @param headers
	 *            :Headers of HTTP connection
	 * @return response object contains status code and response string
	 */
	public static ResponseObject getMethod(String url,
			ArrayList<RequestHeader> headers, Context context) {
		ResponseObject responseObject = new ResponseObject();
		try {
			if (!ConnectionDetector.getInstance(context)
					.isConnectingToInternet()) {
				responseObject
						.setStatusCode(StatusCodeConstants.STATUS_CODE_NO_CONNECTION);
				return responseObject;
			}
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					TIMEOUT_MILLISEC);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
			DefaultHttpClient client = getHTTPSClient();
			HttpGet request = new HttpGet(url);
			
			if (headers != null) {
				for (RequestHeader header : headers)
					request.addHeader(header.getKey(), header.getValue());
			}
			HttpResponse response = client.execute(request);

			responseObject.setStatusCode(response.getStatusLine()
					.getStatusCode());

			if (responseObject.getStatusCode() == StatusCodeConstants.STATUS_CODE_OK
					|| responseObject.getStatusCode() == StatusCodeConstants.STATUS_CODE_NOT_ACCEPTABLE) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					responseObject.setResponseString(EntityUtils.toString(
							entity, "UTF-8"));
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseObject.setResponseString(" ");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_UNKNOWN_HOST_EXCEPTION);
			responseObject.setResponseString(" ");
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_HTTP_HOST_CONNECTION_EXCEPTION);
			responseObject.setResponseString(" ");
		} catch (IOException e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_IO_EXCEPTION);
			responseObject.setResponseString(" ");
		} catch (Exception e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_EXCEPTION);
			responseObject.setResponseString(" ");
		}
		// /log response
		Log.d("Shaimaa", responseObject.getResponseString());

		return responseObject;
	}

	/**
	 * Execute web service of type Post
	 * 
	 * @param url
	 *            :The URL of web service
	 * @param headers
	 *            :Headers of HTTP connection
	 * @param body
	 *            :the request of web service
	 */
	public static ResponseObject postMethod(String url, ArrayList<RequestHeader> headers,
			Context context) {
		ResponseObject responseObject = new ResponseObject();

		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					TIMEOUT_MILLISEC);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

			DefaultHttpClient client = getHTTPSClient();
			HttpPost request = new HttpPost(url);

			if (headers != null) {
				for (RequestHeader header : headers)
					request.addHeader(header.getKey(), header.getValue());
			}
			HttpResponse response = client.execute(request);

			 responseObject.setStatusCode(response.getStatusLine().getStatusCode());
			
			 if(responseObject.getStatusCode() ==
			 StatusCodeConstants.STATUS_CODE_OK ||
			 responseObject.getStatusCode() ==
			 StatusCodeConstants.STATUS_CODE_NOT_ACCEPTABLE)
			 {
			 HttpEntity entity = response.getEntity();
			 if(entity!=null)
			 responseObject.setResponseString(EntityUtils.toString(entity,"UTF-8"));
			 }

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			 responseObject.setResponseString(" ");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			 responseObject.setStatusCode(StatusCodeConstants.STATUS_CODE_UNKNOWN_HOST_EXCEPTION);
			 responseObject.setResponseString(" ");
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
			 responseObject.setStatusCode(StatusCodeConstants.STATUS_CODE_HTTP_HOST_CONNECTION_EXCEPTION);
			 responseObject.setResponseString(" ");
		} catch (IOException e) {
			e.printStackTrace();
			 responseObject.setStatusCode(StatusCodeConstants.STATUS_CODE_IO_EXCEPTION);
			 responseObject.setResponseString(" ");
		} catch (Exception e) {
			e.printStackTrace();
			 responseObject.setStatusCode(StatusCodeConstants.STATUS_CODE_EXCEPTION);
			 responseObject.setResponseString(" ");
		}
		 Log.i("Response", responseObject.getResponseString());
		// LogUtility.i("Response", responseObject.getResponseString());
		 return responseObject;
	}

	/**
	 * Establish HTTPS connection
	 * 
	 * @return HTTP client
	 */
	public static DefaultHttpClient getHTTPSClient() 
	{
		try 
		{
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) 
		{
			return new DefaultHttpClient();
		}
	}

//	
	// /post method with body
	public static ResponseObject postMethodWithoutBody(String url,
			ArrayList<RequestHeader> headers, Context context) {

		ResponseObject responseObject = new ResponseObject();
		try {
			if (!ConnectionDetector.getInstance(context)
					.isConnectingToInternet()) {
				responseObject
						.setStatusCode(StatusCodeConstants.STATUS_CODE_NO_CONNECTION);
				return responseObject;
			}
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					TIMEOUT_MILLISEC);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

			DefaultHttpClient client = getHTTPSClient();
			HttpPost request = new HttpPost(url);

			
			if (headers != null) {
				for (RequestHeader header : headers)
					request.addHeader(header.getKey(), header.getValue());
			}
			HttpResponse response = client.execute(request);

			responseObject.setStatusCode(response.getStatusLine()
					.getStatusCode());

			if (responseObject.getStatusCode() == StatusCodeConstants.STATUS_CODE_OK
					|| responseObject.getStatusCode() == StatusCodeConstants.STATUS_CODE_NOT_ACCEPTABLE) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					responseObject.setResponseString(EntityUtils.toString(
							entity, "UTF-8"));
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseObject.setResponseString(" ");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_UNKNOWN_HOST_EXCEPTION);
			responseObject.setResponseString(" ");
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_HTTP_HOST_CONNECTION_EXCEPTION);
			responseObject.setResponseString(" ");
		} catch (IOException e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_IO_EXCEPTION);
			responseObject.setResponseString(" ");
		} catch (Exception e) {
			e.printStackTrace();
			responseObject
					.setStatusCode(StatusCodeConstants.STATUS_CODE_EXCEPTION);
			responseObject.setResponseString(e.getMessage());
		}
		// /log response
		Log.i("Response", responseObject.getResponseString());

		return responseObject;
	}
	
	
	
	
	// /post method with body
		public static ResponseObject postMethodWithBody(String url,
				ArrayList<RequestHeader> headers, String body, Context context) {

			ResponseObject responseObject = new ResponseObject();
			try {
				if (!ConnectionDetector.getInstance(context)
						.isConnectingToInternet()) {
					responseObject
							.setStatusCode(StatusCodeConstants.STATUS_CODE_NO_CONNECTION);
					return responseObject;
				}
				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams,
						TIMEOUT_MILLISEC);
				HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

				DefaultHttpClient client = getHTTPSClient();
				HttpPost request = new HttpPost(url);
			

				BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
				byte bytes[] = body.getBytes();

				basicHttpEntity.setContent(new ByteArrayInputStream(bytes));
				request.setEntity(basicHttpEntity);
				if (headers != null) {
					for (RequestHeader header : headers)
						request.addHeader(header.getKey(), header.getValue());
				}
				HttpResponse response = client.execute(request);

				responseObject.setStatusCode(response.getStatusLine()
						.getStatusCode());

				if (responseObject.getStatusCode() == StatusCodeConstants.STATUS_CODE_OK
						|| responseObject.getStatusCode() == StatusCodeConstants.STATUS_CODE_NOT_ACCEPTABLE) {
					HttpEntity entity = response.getEntity();
					if (entity != null)
						responseObject.setResponseString(EntityUtils.toString(
								entity, "UTF-8"));
				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
				responseObject.setResponseString(" ");
			} catch (UnknownHostException e) {
				e.printStackTrace();
				responseObject
						.setStatusCode(StatusCodeConstants.STATUS_CODE_UNKNOWN_HOST_EXCEPTION);
				responseObject.setResponseString(" ");
			} catch (HttpHostConnectException e) {
				e.printStackTrace();
				responseObject
						.setStatusCode(StatusCodeConstants.STATUS_CODE_HTTP_HOST_CONNECTION_EXCEPTION);
				responseObject.setResponseString(" ");
			} catch (IOException e) {
				e.printStackTrace();
				responseObject
						.setStatusCode(StatusCodeConstants.STATUS_CODE_IO_EXCEPTION);
				responseObject.setResponseString(" ");
			} catch (Exception e) {
				e.printStackTrace();
				responseObject
						.setStatusCode(StatusCodeConstants.STATUS_CODE_EXCEPTION);
				responseObject.setResponseString(e.getMessage());
			}
			// /log response
			Log.i("Response", responseObject.getResponseString());

			return responseObject;
		}

//		public  static StringBuilder getPostResult(String url,MultipartEntity entity)
//		{
//			HttpResponse response ;
//			StringBuilder sb = null;
//			HttpClient httpClient = new DefaultHttpClient();
//	        HttpPost httpPost = new HttpPost(url);    
//			try 
//	        {
//	    		httpPost.setEntity(entity);			
//				response = httpClient.execute(httpPost);
//	            try 
//	            {
//	                InputStream inputStream = response.getEntity().getContent();
//	                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//	                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//	                StringBuilder stringBuilder = new StringBuilder();
//	                String bufferedStrChunk = "";
//	                while((bufferedStrChunk = bufferedReader.readLine()) != null)
//	                {
//	                    stringBuilder.append(bufferedStrChunk);
//	                }
//	                inputStream.close();
//	                inputStreamReader.close();
//	                bufferedReader.close();
//	                return stringBuilder;
//	            } 
//	            catch (ClientProtocolException cpe) 
//	            {
//	               cpe.printStackTrace();
//	            } 
//	            catch (IOException ioe) 
//	            {
//	               ioe.printStackTrace();
//	            }
//	            return null;	
//			}
//	        catch (ClientProtocolException e) 
//	        {
//				e.printStackTrace();
//			} 
//	        catch (IOException e) 
//	        {
//				e.printStackTrace();
//			}
//	        httpPost.abort();
//			return sb;
//		}

}



