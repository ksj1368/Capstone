package com.example.myapplication111;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;
private FragmentManager fragmentManager;
private FragmentTransaction transaction;
private NavigationFragment nfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillExampleList();
        setUpRecyclerView();
        FragmentManager fragmentManager=getSupportFragmentManager();
        nfragment = new NavigationFragment();

        Button Mapbtn = (Button) findViewById(R.id.MapNavi);
        Mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Map.class);
                startActivity(intent);
            }
        });

    }
    private void fillExampleList() {

        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("4-320(공간정보공학과 강의실)", "4호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("4-335(공간정보공학과 강의실)", "4호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("7-343(학생지원팀)", "7호관","37.4495373", "126.6562075"));
        exampleList.add(new ExampleItem("7-505(국제지원팀)", "7호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("2-212(공과대학행정실)", "2호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("2-291(기계공학과 과사무실)", "2호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("2-235(항공우주공학과 과사무실)", "2호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("2-491A(조선해양공학과 과사무실)", "2호관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("60-801(화학공학과 과사무실)", "60주년기념관","37.4509377", "126.6560217"));
        exampleList.add(new ExampleItem("2-477A(산업경영공학과 과사무실)", "2호관","37.4509377", "126.6560217"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList,this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("검색하시오");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}