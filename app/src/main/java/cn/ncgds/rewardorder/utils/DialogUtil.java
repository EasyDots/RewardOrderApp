/**
 *
 */
package cn.ncgds.rewardorder.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public class DialogUtil
{
	
	public static void showDialog(final Context ctx
		, String msg , boolean goHome)
	{
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
			.setMessage(msg).setCancelable(false);
		
			builder.setPositiveButton("确定", null);
		
		builder.create().show();
	}
	
	public static void showDialog(Context ctx , View view)
	{
		new AlertDialog.Builder(ctx)
			.setView(view).setCancelable(false)
			.setPositiveButton("返回", null)
			.create()
			.show();
	}
}
