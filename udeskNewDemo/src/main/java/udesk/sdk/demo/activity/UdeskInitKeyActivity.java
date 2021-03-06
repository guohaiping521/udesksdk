package udesk.sdk.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import cn.udesk.PreferenceHelper;
import cn.udesk.UdeskSDKManager;
import udesk.core.UdeskConst;
import udesk.sdk.demo.R;


public class UdeskInitKeyActivity extends Activity {

//    //替换成你们注册生成的域名
//    private String UDESK_DOMAIN = "linapp.udeskt2.com";
//    //替换成你们生成应用产生的appid
//    private String AppId = "075c55fdc38508ce";
//    // 替换成你们在后台生成的密钥
//    private String UDESK_SECRETKEY = "f71b983d30bb41a23cba209a5558cf8f";

    //替换成你们注册生成的域名
    private String UDESK_DOMAIN = "udesksdk.udesk.cn";
    //替换成你们生成应用产生的appid
    private String AppId = "cdc6da4fa97efc2c";
    // 替换成你们在后台生成的密钥
    private String UDESK_SECRETKEY = "6c37f775019907785d85c027e29dae4e";


    private EditText mDomainEdit;

    private EditText mAppidEdit;

    private EditText mKeyEdit;
    private EditText stoken;

    private Button startBtn;

    String domain = "";
    String appkey = "";
    String appid = "";
    String sdkToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udesk_init_key_view);
        //传入注册的域名和密钥
        redDoaminAndKey();
        mDomainEdit = (EditText) findViewById(R.id.udesk_domain);
        mKeyEdit = (EditText) findViewById(R.id.udesk_appkey);
        mAppidEdit = (EditText) findViewById(R.id.appid);
        stoken = (EditText) findViewById(R.id.stoken);
        startBtn = (Button) findViewById(R.id.udesk_start);
        sdkToken = PreferenceHelper.readString(getApplicationContext(), "init_base_name", "sdktoken");
        if (TextUtils.isEmpty(sdkToken)) {
            sdkToken = UUID.randomUUID().toString();
        }
        stoken.setText(sdkToken);
        if (TextUtils.isEmpty(domain) || TextUtils.isEmpty(appkey) || TextUtils.isEmpty(appid)) {
            mDomainEdit.setText(UDESK_DOMAIN);
            mKeyEdit.setText(UDESK_SECRETKEY);
            mAppidEdit.setText(AppId);
        } else {
            mDomainEdit.setText(domain);
            mKeyEdit.setText(appkey);
            mAppidEdit.setText(appid);
        }

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mDomainEdit.getText().toString()) && !TextUtils.isEmpty(mKeyEdit.getText().toString())) {
                    //  使用前需要设置的信息:

                    UdeskSDKManager.getInstance().initApiKey(getApplicationContext(), mDomainEdit.getText().toString(),
                            mKeyEdit.getText().toString(), mAppidEdit.getText().toString());
                    UdeskConst.HTTP = "http://";
                    sdkToken = stoken.getText().toString();
                    PreferenceHelper.write(getApplicationContext(), "init_base_name", "sdktoken", sdkToken);
                    PreferenceHelper.write(getApplicationContext(), "init_base_name", "domain", mDomainEdit.getText().toString());
                    PreferenceHelper.write(getApplicationContext(), "init_base_name", "appkey", mKeyEdit.getText().toString());
                    PreferenceHelper.write(getApplicationContext(), "init_base_name", "appid", mAppidEdit.getText().toString());

                    Intent intent = new Intent();
                    intent.setClass(UdeskInitKeyActivity.this, UdeskUseGuideActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UdeskInitKeyActivity.this, "Please enter domian and key values", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void redDoaminAndKey() {
        domain = PreferenceHelper.readString(this, "init_base_name", "domain");
        appkey = PreferenceHelper.readString(this, "init_base_name", "appkey");
        appid = PreferenceHelper.readString(this, "init_base_name", "appid");
    }


}
