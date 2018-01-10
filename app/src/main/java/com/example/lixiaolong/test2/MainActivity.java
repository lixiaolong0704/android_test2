package com.example.lixiaolong.test2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.*;
import java.nio.charset.*;
import android.os.*;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private WebView wv;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        wv = (WebView)findViewById(R.id.wv);

        wv.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.google.com");

        //Find the directory for the SD Card using the API
//*Don't* hardcode "/sdcard"
        File sdcard = Environment.getExternalStorageDirectory();

//Get the text file
        File file = new File(sdcard,"/Download/bbb.txt");
        File file1 = new File(sdcard,"");
        File[] fss= file1.listFiles();
//Read text from file
        StringBuilder text = new StringBuilder();

        try {

            InputStreamReader ir= new InputStreamReader(new FileInputStream(file.getAbsolutePath()), StandardCharsets.UTF_8);

//            BufferedReader in
//                    = new BufferedReader(new FileInputStream(new InputStream()));
            BufferedReader br = new BufferedReader(ir);
            String line;

            while ((line = br.readLine()) != null) {
                for(int i=0;i<1;i++){
                    text.append("<span style='backgroud:red;margin:2px 2px;color:yellow;'>"+line+"</span>");
                    text.append('\n');
                }

            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here

            Log.i("fuck",e.toString());
        }

        WebSettings settings = wv.getSettings();

        settings.setDefaultTextEncodingName("utf-8");

        String customHtml = "<html><body><h1>ffff, WebView"+text.toString()+"</h1></body></html>";
        wv.loadData(customHtml, "text/html; charset=utf-8", "utf-8");



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
