package com.fin.fininfocom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fin.fininfocom.adapter.EmployeeAdapter;
import com.fin.fininfocom.model.EmployeeModel;
import com.fin.fininfocom.utils.InternetConnection;
import com.fin.fininfocom.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    ImageView menu_options, logout;
    TextView user_name;
    RecyclerView recyclerview_data;
    ProgressBar progress_bar;
    MySharedPreferences mySharedPreferences ;
    ArrayList<EmployeeModel> list;
    EmployeeAdapter adapter;
    Realm realm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        if (!InternetConnection.isNetworkAvailable(this)){
            InternetConnection.networkDialog(this);
            return;
        }
        realm = Realm.getDefaultInstance();
        menu_options = findViewById(R.id.menu_options);
        user_name = findViewById(R.id.user_name);
        logout = findViewById(R.id.logout);
        recyclerview_data = findViewById(R.id.recyclerview_data);
        progress_bar = findViewById(R.id.progress_bar);
        list = new ArrayList<>();
        EmployeeModel employeeModel1 = new EmployeeModel();
        employeeModel1.setId(1);
        employeeModel1.setEmployee_name("Suresh");
        employeeModel1.setEmployee_age(25);
        employeeModel1.setEmployee_city("Hyderabad");
        EmployeeModel employeeModel2 = new EmployeeModel();
        employeeModel2.setId(2);
        employeeModel2.setEmployee_name("Ramesh");
        employeeModel2.setEmployee_age(35);
        employeeModel2.setEmployee_city("Vijayawada");
        EmployeeModel employeeModel3 = new EmployeeModel();
        employeeModel3.setId(3);
        employeeModel3.setEmployee_name("Akash");
        employeeModel3.setEmployee_age(29);
        employeeModel3.setEmployee_city("Bengaluru");
        EmployeeModel employeeModel4 = new EmployeeModel();
        employeeModel4.setId(4);
        employeeModel4.setEmployee_name("Brajesh");
        employeeModel4.setEmployee_age(24);
        employeeModel4.setEmployee_city("Mumbai");
        EmployeeModel employeeModel5 = new EmployeeModel();
        employeeModel5.setId(5);
        employeeModel5.setEmployee_name("Kumar");
        employeeModel5.setEmployee_age(26);
        employeeModel5.setEmployee_city("Kolkata");
        EmployeeModel employeeModel6 = new EmployeeModel();
        employeeModel6.setId(6);
        employeeModel6.setEmployee_name("Lalit");
        employeeModel6.setEmployee_age(32);
        employeeModel6.setEmployee_city("Chennai");
        list.add(employeeModel1);
        list.add(employeeModel2);
        list.add(employeeModel3);
        list.add(employeeModel4);
        list.add(employeeModel5);
        list.add(employeeModel6);
        addDataToDataBase();
        mySharedPreferences = new MySharedPreferences(MainActivity.this);
        user_name.setText(mySharedPreferences.getUserName());

        LinearLayoutManager mLayoutManager12 = new LinearLayoutManager(MainActivity.this);
        recyclerview_data.setLayoutManager(mLayoutManager12);
        recyclerview_data.setItemAnimator(new DefaultItemAnimator());
        recyclerview_data.setNestedScrollingEnabled(false);
        adapter = new EmployeeAdapter(list, MainActivity.this);
        recyclerview_data.setAdapter(adapter);
        menu_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.AlertDialogCustom1))
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to Logout ?")
                        .setPositiveButton(Html.fromHtml("<font color='#000000'>Yes</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mySharedPreferences.setLoginStatus(false);
                                mySharedPreferences.logoutUser();
                                Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                                startActivity(intent);
                                finish();

                            }
                        }).setNegativeButton(Html.fromHtml("<font color='#000000'>No</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        }).show();
            }
        });
    }

    private void addDataToDataBase() {
        for (int i=0; i<list.size();i++){
            int finalI = i;
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(list.get(finalI));
                    System.out.println("Success :");
                }
            });
        }

    }

    private void showPopup(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.option_menu,popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.sort_by_name:
                        openSortByName();
                        return true;
                    case R.id.sort_by_age:
                        openSortByAge();
                        return true;
                    case R.id.sort_by_city:
                        openSortByCity();
                        return true;
                }
                return false;
            }
        });
    }

    private void openSortByCity() {
        Collections.sort(list, new Comparator<EmployeeModel>() {
            @Override
            public int compare(EmployeeModel employeeModel, EmployeeModel t1) {
                return employeeModel.getEmployee_city().compareToIgnoreCase(t1.getEmployee_city());
            }
        });
        adapter = new EmployeeAdapter(list, MainActivity.this);
        recyclerview_data.setAdapter(adapter);
    }

    private void openSortByAge() {
        Collections.sort(list, new Comparator<EmployeeModel>() {
            @Override
            public int compare(EmployeeModel employeeModel, EmployeeModel t1) {
                return employeeModel.getEmployee_age()- t1.getEmployee_age();
            }
        });
        adapter = new EmployeeAdapter(list, MainActivity.this);
        recyclerview_data.setAdapter(adapter);
    }

    private void openSortByName() {
        Collections.sort(list, new Comparator<EmployeeModel>() {
            @Override
            public int compare(EmployeeModel employeeModel, EmployeeModel t1) {
                return employeeModel.getEmployee_name().compareToIgnoreCase(t1.getEmployee_name());
            }
        });
        adapter = new EmployeeAdapter(list, MainActivity.this);
        recyclerview_data.setAdapter(adapter);
    }
}