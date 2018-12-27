package com.example.harshil.hosteldata;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private List<Details> mRecyclerViewItems = new ArrayList<>();
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(mRecyclerViewItems, this);
        mRecyclerView.setAdapter(adapter);

        try {
            addMenuItemsFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addMenuItemsFromJson() throws IOException {
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);
            for (int i = 0 ;i<menuItemsJsonArray.length();++i){
                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);
                String menuItemRoomNo = menuItemObject.getString("room_no.");
                String menuItemEnrollNo = menuItemObject.getString("enroll_no");
                String menuItemFullName = menuItemObject.getString("full_name");
                String menuItemBranch = menuItemObject.getString("branch");
                String menuItemYear = menuItemObject.getString("year");
                String menuItemEmail = menuItemObject.getString("email");
                String menuItemContactNo = menuItemObject.getString("contact_no.");
                String menuItemEConatactNo = menuItemObject.getString("e_contact_no");
                String menuItemAddress = menuItemObject.getString("address");

                Details details = new Details(menuItemRoomNo,menuItemEnrollNo,menuItemFullName,menuItemBranch,menuItemYear,menuItemEmail,menuItemContactNo,menuItemEConatactNo,menuItemAddress);
                mRecyclerViewItems.add(details);
            }
        } catch (JSONException e) {
            Log.d(MainActivity.class.getName(),"unable to parse JSON file",e);
        }
    }

    private String readJsonDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try{
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.final_hostel);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(inputStream,"UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null){
                builder.append(jsonDataString);
            }
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
        }
        return new String(builder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Details> filterModeList = filter(mRecyclerViewItems,newText);
                adapter.setFilter(filterModeList);
                return true;
            }
        });
        return true;
    }
    private List<Details> filter(List<Details> p1,String query){
        query = query.toLowerCase();
        final List<Details> filterList = new ArrayList<>();
        for(Details model : p1){

            final String rommNO = model.getRoomNo().toLowerCase();
            final String stdName = model.getFullName().toLowerCase();
            if(rommNO.startsWith(query) ){
                filterList.add(model);
            }else if(stdName.contains(query)){
                filterList.add(model);
            }
        }
        return filterList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            startActivity(new Intent(MainActivity.this,AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
