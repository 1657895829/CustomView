package com.dhw.propertyanimation;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

//web页面加载
public class DeatilsActivity extends AppCompatActivity {
    private WebView webView;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);

        Intent intent = getIntent();
        title = intent.getStringExtra("name");

        Log.e("---",title);
        webView = (WebView)findViewById(R.id.my_web);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(DeatilsActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/js.html");

    }

    public void dianJi(View view){
        //按钮点击事件
        // 必须另开线程进行JS方法调用(否则无法调用)
        webView.post(new Runnable() {
            @Override
            public void run() {

                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                webView.loadUrl("javascript:actionFromJs()");
                Toast.makeText(DeatilsActivity.this,"js交互",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

