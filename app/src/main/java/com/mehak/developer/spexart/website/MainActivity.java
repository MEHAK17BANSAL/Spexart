package com.mehak.developer.spexart.website;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mehak.developer.spexart.R;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //initializing WebView
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
            AppRate.with(this)
                    .setInstallDays(0)
                    .setLaunchTimes(3)
                    .setRemindInterval(2)
                    .monitor();
            AppRate.showRateDialogIfMeetsConditions(this);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setBackgroundColor(Color.parseColor("#FF9E3D94"));



            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            //WebView
            webView = (WebView) findViewById(R.id.myWebView);

            webView.setWebViewClient(new  myWebclient());
            webView.getSettings().setJavaScriptEnabled(true);

            webView.loadUrl("http://newspexart.onlinedemos.xyz");
        }



    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }


    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

    public  class  myWebclient extends WebViewClient{
        @Override
        public  void onPageFinished(WebView view,String url) {
            super.onPageFinished(view, url);
            pd.dismiss();

        }
        ProgressDialog pd = null;
        @Override
        public void onPageStarted(WebView view,String url,Bitmap favfcon){
            pd=new ProgressDialog(MainActivity.this);
            pd.setTitle("Please Wait..");
            pd.setMessage("Loading..");
            pd.show();
            super.onPageStarted(view, url, favfcon);

        }
        @Override public  boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);

            return super.shouldOverrideUrlLoading(view,url);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode==KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return  true;
        }
        return  super.onKeyDown(keyCode,event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details?id=com.mehak.goprokart_app");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.me) {
            // Handle the camera action
            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/category/21/21");

        } else if (id == R.id.we) {
            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/category/21/20");
        } else if (id == R.id.ke) {
            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/category/21/22");
        } else if (id == R.id.sw) {
            webView.loadUrl("hhttp://newspexart.onlinedemos.xyz/index.php/home/category/22/23");
        }
        else if (id == R.id.sm) {

            webView.loadUrl(" http://newspexart.onlinedemos.xyz/index.php/home/category/22/24");
        }
        else if (id == R.id.sk) {
            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/category/22/27");
        }
        else if (id == R.id.ci) {
            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/contact");
        }
        else if (id == R.id.au) {

            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/legal/privacy_policy");
        }
        else if (id == R.id.t) {

            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/shippingreturn");
        }
        else if (id == R.id.f) {

            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/framematerials");
        }
        else if (id == R.id.l) {

            webView.loadUrl("http://newspexart.onlinedemos.xyz/index.php/home/ourlenses");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
