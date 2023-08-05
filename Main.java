import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    String mGeoLocationRequestOrigin = null;
    GeolocationPermissions.Callback  mGeoLocationCallback = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        mWebView.setWebViewClient(new WebViewClient()); // to handle URL redirects in the app
        webSettings.setJavaScriptEnabled(true); // to enable JavaScript on web pages
        webSettings.setGeolocationEnabled(true); // to enable GPS location on web pages
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.loadUrl("https://john-christ.github.io/mbmw");


        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin,
                                                           final GeolocationPermissions.Callback callback) {

                int permissionCheckFineLocation = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheckFineLocation!= PackageManager.PERMISSION_GRANTED) {
                    mGeoLocationCallback=callback;
                    mGeoLocationRequestOrigin=origin;
                    //requesting permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

                }

                else {// permission and the user has therefore already granted it
                    callback.invoke(origin, true, false);


                }

            }
        });




    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //you have the permission now.
            if (requestCode == 123) {
                mGeoLocationCallback.invoke(mGeoLocationRequestOrigin, true, false);
            }
        }

    }


    @Override
    public void onBackPressed(){
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        }

        else{
            super.onBackPressed();
        }
    }
}
