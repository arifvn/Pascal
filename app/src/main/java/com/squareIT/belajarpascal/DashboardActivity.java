package com.squareIT.belajarpascal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.squareIT.belajarpascal.adapter.DashboardAdapter;
import com.squareIT.belajarpascal.model.DashboardItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private Button btnCloseConfirm, btnCancel, btnOk, btnRpp;

    private RecyclerView recyclerView;
    private ArrayList<DashboardItem> arrayList;
    private GridLayoutManager gridLayoutManager;
    private DashboardAdapter dashboardAdapter;
    private Intent intent;

    private LinearLayout linearLayoutConfirm;

    private Animation confirmVisible, confirmInvisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        loadAnim();

        btnCloseConfirm = (Button) findViewById(R.id.btn_close_confirm);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnRpp = (Button) findViewById(R.id.btn_rpp);
        btnOk = (Button) findViewById(R.id.btn_ok);
        linearLayoutConfirm = (LinearLayout) findViewById(R.id.linear_confirm);

        btnRpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DashboardActivity.this, RPPActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutConfirm.setVisibility(View.GONE);

        btnCloseConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnim();
                linearLayoutConfirm.setVisibility(View.VISIBLE);
                linearLayoutConfirm.setAnimation(confirmVisible);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnim();
                linearLayoutConfirm.setAnimation(confirmInvisible);
                linearLayoutConfirm.setVisibility(View.GONE);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setDashboardItemList();
        setRecyclerView();


    }

    private void loadAnim() {
        confirmVisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashboard_close_visible);
        confirmInvisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashboard_close_invisible);
    }

    private void setDashboardItemList() {
        arrayList = new ArrayList<DashboardItem>();

        arrayList.add(new DashboardItem(R.drawable.ic_question, "Petunjuk"));
        arrayList.add(new DashboardItem(R.drawable.ic_kompetensi, "Kompetensi\nDasar"));
        arrayList.add(new DashboardItem(R.drawable.ic_tujuan, "Tujuan"));
        arrayList.add(new DashboardItem(R.drawable.ic_open_book, "Materi"));
        arrayList.add(new DashboardItem(R.drawable.ic_video, "Video"));
        arrayList.add(new DashboardItem(R.drawable.ic_laptop, "Contoh\nProgram"));
        arrayList.add(new DashboardItem(R.drawable.ic_exam, "Latihan"));
        arrayList.add(new DashboardItem(R.drawable.ic_library, "Glosarium"));
        arrayList.add(new DashboardItem(R.drawable.ic_student_1, "Profil\nPengembang"));
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_dashboard);

        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,
                false);

        dashboardAdapter = new DashboardAdapter(arrayList, this);
        dashboardAdapter.setOnItemClickListener(new DashboardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(DashboardActivity.this, PetunjukActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(DashboardActivity.this, KDActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(DashboardActivity.this, TujuanActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(DashboardActivity.this, MateriActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(DashboardActivity.this, VideoActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(DashboardActivity.this, ContohProgramActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(DashboardActivity.this, LatihanActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(DashboardActivity.this, GlosariumActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(DashboardActivity.this, ProfilActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(dashboardAdapter);
    }

    class CompilePascalOnline extends AsyncTask<String, Void, String> {

        private static final String TAG = "CompilePascalOnline";

        private String output;

        protected String doInBackground(String... urls) {
            String clientId = "18ce459142065aa42d58a8c7f6fbfa07"; //Replace with your client ID
            String clientSecret = "4c45f7b1351fb761000ca1c3fe2105b65e1ab14de5e619bff28b3eaf43324b3c"; //Replace with your client Secret
            String script = "program HelloWorld;\\n" +
                    "\\n" +
                    "begin\\n" +
                    "    writeln('Hello, worlddsaf');\\n" +
                    "    writeln('Hello, worlddsaf');\\n" +
                    "    writeln('Hello, daf');\\n" +
                    "end.";
            String language = "pascal";
            String versionIndex = "0";

            try {
                URL url = new URL("https://api.jdoodle.com/v1/execute");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");

                String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + script +
                        "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(input.getBytes());
                outputStream.flush();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("Please check your inputs : HTTP error code : " + connection.getResponseCode());
                }

                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new InputStreamReader(
                        (connection.getInputStream())));

                while ((output = bufferedReader.readLine()) != null) {
                    Log.e(TAG, "main: " + "Output from JDoodle .... \n" + output);
                }

                connection.disconnect();
                return output;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return output;
        }

        protected void onPostExecute(String output) {

        }
    }
}
