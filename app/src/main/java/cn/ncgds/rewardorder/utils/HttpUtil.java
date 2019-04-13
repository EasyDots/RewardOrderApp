/**
 *
 */
package cn.ncgds.rewardorder.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 封装的http工具类
 */
public class HttpUtil {
	public static HttpClient httpClient = new DefaultHttpClient();
	public static final String BASE_URL = "http://212.64.12.155/payback_war/";

	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String getRequest(final String url)throws Exception {
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>()
				{
					@Override
					public String call() throws Exception
					{
						HttpGet get = new HttpGet(url);
						HttpResponse httpResponse = httpClient.execute(get);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpResponse.getEntity());
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}

	/**
	 * post请求
	 * @param url
	 * @param rawParams
	 * @return
	 * @throws Exception
	 */
	public static String postRequest(final String url, final Map<String ,String> rawParams)throws Exception {
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						HttpPost post = new HttpPost(url);

						List<NameValuePair> params =
								new ArrayList<NameValuePair>();
						for(String key : rawParams.keySet()) {
							params.add(new BasicNameValuePair(key
									, rawParams.get(key)));
						}
						post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

						HttpResponse httpResponse = httpClient.execute(post);

						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpResponse.getEntity());
							return result;
						}

						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}

	public static JSONObject query(String username, String password)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		String url = HttpUtil.BASE_URL + "/user/login";
		JSONObject jso= new JSONObject(HttpUtil.postRequest(url, map));

		Log.e("HttpUtil", "Query user/login" + jso);
		return jso;
	}

	public static JSONObject queryInfo(String username)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		String url = HttpUtil.BASE_URL + "/user/get";
		String res = HttpUtil.postRequest(url, map);
		Log.e("HttpUtil", "res = " + res);
		JSONObject jso= new JSONObject(res);


		Log.e("HttpUtil", "Query userinfo.php" + jso);
		return jso;
	}
	public static JSONObject update(JsonObject jsonObject)
			throws Exception {
		Gson gson = new Gson();
		java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
		}.getType();
		Map<String, String> map = gson.fromJson(jsonObject, type);
		String url = HttpUtil.BASE_URL + "/user/update";
		String res = HttpUtil.postRequest(url, map);
		Log.e("HttpUtil", "res = " + res);
		JSONObject jso= new JSONObject(res);
		Log.e("HttpUtil", "Query userinfo.php" + jso);
		return jso;
	}
	//注册
	public static JSONObject regAction(String username, String password)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		String url = HttpUtil.BASE_URL + "user/reg";
		JSONObject jso= new JSONObject(HttpUtil.postRequest(url, map));

		Log.e("HttpUtil", "Query user/reg" + jso);
		return jso;
	}

}
