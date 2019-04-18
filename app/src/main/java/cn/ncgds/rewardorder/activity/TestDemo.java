package cn.ncgds.rewardorder.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.payelves.sdk.EPay;
import com.payelves.sdk.bean.QueryOrderModel;
import com.payelves.sdk.enums.EPayResult;
import com.payelves.sdk.listener.PayResultListener;
import com.payelves.sdk.listener.QueryOrderListener;

import java.util.UUID;

import cn.ncgds.rewardorder.R;

public class TestDemo extends Activity {
    String openId = "woeQxl2zn";
    String token = "b602b8c5969d4455b8c3e09b7997c7c7";
    String appId = "8087312729047045";
    String channel = "baidu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_demo);
//init Epay
        EPay.getInstance(TestDemo.this).init(openId, token, appId, channel);
    }

    public void pay(View view) {

        String subject = "悬赏令";
        String body = "悬赏令赏金充值";
        String orderId = UUID.randomUUID().toString().replace("-", "");
        String payUserId = orderId;
        String backPara = "";

        EPay.getInstance(TestDemo.this).pay(subject, body, 50, orderId, payUserId, backPara, new PayResultListener() {
            @Override
            public void onFinish(Context context, Long payId, String orderId, String payUserId, EPayResult payResult, int payType, Integer amount) {
                EPay.getInstance(context).closePayView();//关闭快捷支付页面
                if (payResult.getCode() == EPayResult.SUCCESS_CODE.getCode()) {
                    Toast.makeText(TestDemo.this, payResult.getMsg(), Toast.LENGTH_LONG).show();
                    //再次检查订单
                    EPay.getInstance(context).queryOrder(payId, new QueryOrderListener() {
                        @Override
                        public void onFinish(boolean isSuccess, String msg, QueryOrderModel model) {
                            if (isSuccess) {
                                //成功
                                Toast.makeText(TestDemo.this, model.getPayStatusMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(TestDemo.this, msg, Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                    //支付成功逻辑处理
                    Toast.makeText(TestDemo.this, payResult.getMsg(), Toast.LENGTH_LONG).show();
                } else if (payResult.getCode() == EPayResult.FAIL_CODE.getCode()) {
                    //支付失败逻辑处理
                    Toast.makeText(TestDemo.this, payResult.getMsg(), Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
