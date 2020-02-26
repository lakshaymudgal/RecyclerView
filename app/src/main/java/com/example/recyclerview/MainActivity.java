package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.onItemClickListener {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_ID = "id";

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<ViewModel> modelArrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.r_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        fetchData();
    }

    private void fetchData() {

        String url = "https://pixabay.com/api/?key=15346009-9e56bc36c5ae6b466a5d1d7c3&q=yellow+flowers&image_type=photo&pretty=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for(int i = 0; i< jsonArray.length(); i++){

                                JSONObject user = jsonArray.getJSONObject(i);

                                String userName = user.getString("user");
                                int userId = user.getInt("user_id");

                                modelArrayList.add(new ViewModel(userName, userId));
                            }

                            adapter = new Adapter(MainActivity.this, modelArrayList);
                            recyclerView.setAdapter(adapter);

                            adapter.setOnItemClickListener(MainActivity.this);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onItenClick(int position) {
        Intent intent = new Intent(MainActivity.this, Details.class);

        ViewModel clicked = modelArrayList.get(position);

        intent.putExtra(EXTRA_NAME, clicked.getName());
        intent.putExtra(EXTRA_ID, clicked.getId());

        startActivity(intent);
    }
}
