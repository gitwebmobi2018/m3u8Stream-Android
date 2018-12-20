package com.solodroid.thestreamapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.solodroid.thestreamapp.Config;
import com.solodroid.thestreamapp.R;
import com.solodroid.thestreamapp.databases.DatabaseHandlerFavorite;
import com.solodroid.thestreamapp.models.Channel;
import com.solodroid.thestreamapp.utils.Constant;
import com.solodroid.thestreamapp.utils.NetworkCheck;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActivityDetailChannel extends AppCompatActivity {

    String str_category, str_id, str_image, str_name, str_url, str_description;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    ImageView channel_image;
    TextView channel_name, channel_category;
    WebView channel_description;
    Snackbar snackbar;
    private AdView adView;
    private NativeExpressAdView nativeExpressAdView;
    View view;

    DatabaseHandlerFavorite databaseHandler;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        view = findViewById(android.R.id.content);

        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        } else {
            Log.d("Log", "Working in Normal Mode, RTL Mode is Disabled");
        }

        loadBannerAd();
        loadNativeAd();

        databaseHandler = new DatabaseHandlerFavorite(getApplicationContext());
        floatingActionButton = (FloatingActionButton) findViewById(R.id.img_fav);

        Intent intent = getIntent();
        if (null != intent) {
            str_category = intent.getStringExtra(Constant.KEY_CHANNEL_CATEGORY);
            str_id = intent.getStringExtra(Constant.KEY_CHANNEL_ID);
            str_name = intent.getStringExtra(Constant.KEY_CHANNEL_NAME);
            str_image = intent.getStringExtra(Constant.KEY_CHANNEL_IMAGE);
            str_url = intent.getStringExtra(Constant.KEY_CHANNEL_URL);
            str_description = intent.getStringExtra(Constant.KEY_CHANNEL_DESCRIPTION);
        }

        setupToolbar();

        channel_image = (ImageView) findViewById(R.id.channel_image);
        channel_name = (TextView) findViewById(R.id.channel_name);
        channel_category = (TextView) findViewById(R.id.channel_category);
        channel_description = (WebView) findViewById(R.id.channel_description);

        if (Config.ENABLE_RTL_MODE) {
            rtlLayout();
        } else {
            normalLayout();
        }

        addFavorite();

    }

    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");
        }

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(str_category);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

    }

    public void normalLayout() {

        channel_name.setText(str_name);
        channel_category.setText(str_category);

        Picasso.with(this)
                .load(Config.ADMIN_PANEL_URL + "/upload/" + str_image)
                .placeholder(R.drawable.ic_thumbnail)
                .into(channel_image);

        channel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetworkCheck.isNetworkAvailable(ActivityDetailChannel.this)) {
                    Intent intent = new Intent(ActivityDetailChannel.this, ActivityStreaming.class);
                    intent.putExtra("url", str_url);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_required), Toast.LENGTH_SHORT).show();
                }

            }
        });

        channel_description.setBackgroundColor(Color.parseColor("#ffffff"));
        channel_description.setFocusableInTouchMode(false);
        channel_description.setFocusable(false);
        channel_description.getSettings().setDefaultTextEncodingName("UTF-8");

        WebSettings webSettings = channel_description.getSettings();
        Resources res = getResources();
        int fontSize = res.getInteger(R.integer.font_size);
        webSettings.setDefaultFontSize(fontSize);

        String mimeType = "text/html; charset=UTF-8";
        String encoding = "utf-8";
        String htmlText = str_description;

        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: #525252;}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";

        channel_description.loadData(text, mimeType, encoding);
    }

    public void rtlLayout() {

        channel_name.setText(str_name);
        channel_category.setText(str_category);

        Picasso.with(this)
                .load(Config.ADMIN_PANEL_URL + "/upload/" + str_image)
                .placeholder(R.drawable.ic_thumbnail)
                .into(channel_image);

        channel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NetworkCheck.isNetworkAvailable(ActivityDetailChannel.this)) {
                    Intent intent = new Intent(ActivityDetailChannel.this, ActivityStreaming.class);
                    intent.putExtra("url", str_url);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_required), Toast.LENGTH_SHORT).show();
                }

            }
        });

        channel_description.setBackgroundColor(Color.parseColor("#ffffff"));
        channel_description.setFocusableInTouchMode(false);
        channel_description.setFocusable(false);
        channel_description.getSettings().setDefaultTextEncodingName("UTF-8");

        WebSettings webSettings = channel_description.getSettings();
        Resources res = getResources();
        int fontSize = res.getInteger(R.integer.font_size);
        webSettings.setDefaultFontSize(fontSize);

        String mimeType = "text/html; charset=UTF-8";
        String encoding = "utf-8";
        String htmlText = str_description;

        String text = "<html dir='rtl'><head>"
                + "<style type=\"text/css\">body{color: #525252;}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";

        channel_description.loadData(text, mimeType, encoding);
    }

    public void addFavorite() {

        List<Channel> data = databaseHandler.getFavRow(str_id);
        if (data.size() == 0) {
            floatingActionButton.setImageResource(R.drawable.ic_favorite_outline_white);
        } else {
            if (data.get(0).getChannel_id().equals(str_id)) {
                floatingActionButton.setImageResource(R.drawable.ic_favorite_white);
            }
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                List<Channel> data = databaseHandler.getFavRow(str_id);
                if (data.size() == 0) {
                    databaseHandler.AddtoFavorite(new Channel(
                            str_category,
                            str_id,
                            str_name,
                            str_image,
                            str_url,
                            str_description
                    ));
                    snackbar = Snackbar.make(view, getResources().getString(R.string.favorite_added), Snackbar.LENGTH_SHORT);
                    snackbar.show();

                    floatingActionButton.setImageResource(R.drawable.ic_favorite_white);

                } else {
                    if (data.get(0).getChannel_id().equals(str_id)) {
                        databaseHandler.RemoveFav(new Channel(str_id));
                        snackbar = Snackbar.make(view, getResources().getString(R.string.favorite_removed), Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        floatingActionButton.setImageResource(R.drawable.ic_favorite_outline_white);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.share:

                String news_heading = android.text.Html.fromHtml(getResources().getString(R.string.share_title) + " " + str_name).toString();
                String share_text = android.text.Html.fromHtml(getResources().getString(R.string.share_content)).toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, news_heading + "\n\n" + share_text + "\n\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void loadBannerAd() {
        if (Config.ENABLE_ADMOB_BANNER_ADS) {
            MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.admob_app_id));
            adView = (AdView) findViewById(R.id.adView);
            adView.loadAd(new AdRequest.Builder().build());
            adView.setAdListener(new AdListener() {

                @Override
                public void onAdClosed() {
                }

                @Override
                public void onAdFailedToLoad(int error) {
                    adView.setVisibility(View.GONE);
                }

                @Override
                public void onAdLeftApplication() {
                }

                @Override
                public void onAdOpened() {
                }

                @Override
                public void onAdLoaded() {
                    adView.setVisibility(View.VISIBLE);
                }
            });

        } else {
            Log.d("AdMob", "AdMob Banner is Disabled");
        }
    }

    public void loadNativeAd() {

        if (Config.ENABLE_ADMOB_NATIVE_ADS_CHANNEL_DETAIL) {

            nativeExpressAdView = (NativeExpressAdView) findViewById(R.id.nativeAd);
            nativeExpressAdView.loadAd(new AdRequest.Builder().build());

            nativeExpressAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    nativeExpressAdView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();

                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    super.onAdFailedToLoad(errorCode);
                    nativeExpressAdView.setVisibility(View.GONE);
                }

                @Override
                public void onAdLeftApplication() {
                    super.onAdLeftApplication();

                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();

                }
            });

        } else {
            Log.d("Native Ad", "AdMob Native Ad is Disabled");
        }
    }

}
