package com.example.redstar.ctourbeta;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.redstar.ctourbeta.adapter.TabsFragmentAdapter;
import com.example.redstar.ctourbeta.dto.CtourResponse;
import com.example.redstar.ctourbeta.dto.JSONParser;
import com.example.redstar.ctourbeta.dto.TourDTO;
import com.example.redstar.ctourbeta.dto.ToursDTO;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private static final  int LAYOUT = R.layout.activity_main;
    private static final String TAG = "MyCtourApp";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    TabsFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTab();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);
    }

    private void initToolbar() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initTab() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabsFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        new GetTourTask().execute();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_open, R.string.view_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.menu_item_tour:
                        showAllToursTab();
                        break;
                }
                return true;
            }
        });
    }

    private void showAllToursTab() {
        viewPager.setCurrentItem(Constant.TAB_ONE);
    }


    public class TourCtour {
        List<ToursDTO> items;
    }

    private class GetTourTask extends AsyncTask<Void, Void, ToursDTO>{
        private static final String TAG = "CTour";

        @Override
        protected ToursDTO doInBackground(Void... params) {
            try {
                JSONParser jp = new JSONParser();
                String result = new CtourResponse()
                        .getUrlString(Constant.URL.HOST + Constant.URL.GET_TOUR);

                ToursDTO tds = jp.fromJSON(result, ToursDTO.class);

                Log.i(TAG, "Fetched contents of URL: " + result);
                return tds;

            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ToursDTO tourDTO) {

            if (tourDTO != null) {
                List<TourDTO> data = new ArrayList<>();
                for (int i = 0; i < tourDTO.param.size(); i++) {
                    data.add(tourDTO.param.get(i));
                }

                adapter.setData(data);
            }else{
                TourDTO err = new TourDTO("Error 404");
                err.setInfo("Ошибка при подключении к серверу, сервер временно недоступен или что-то пошло не так. Попробуйте в следующий раз... ");
                err.setId(-1);
                err.setImage("none");
                List<TourDTO> data = new ArrayList<>();
                data.add(err);
                adapter.setData(data);
            }

        }
    }


    public interface APIService {
        @POST(Constant.URL.GET_TOUR)
        Call<ToursDTO> getTours();
    }
}
