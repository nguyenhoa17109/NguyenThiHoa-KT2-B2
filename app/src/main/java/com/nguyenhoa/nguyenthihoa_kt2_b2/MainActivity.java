package com.nguyenhoa.nguyenthihoa_kt2_b2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btDate, btAdd, btGet, btUpdate, btGetAll, btDelete;
    private TextView tvDate, tvTong;
    private EditText etid, etName;
    private Spinner spinner;
    private CheckBox checkBox;
    private int mYear, mMonth, mDay;

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteVeMayBay sqLiteVeMayBay;

    String[] location = {"Ha Noi", "Da Nang", "TP HCM", "Nha Trang"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
//        etid.setEnabled(false);
        creatSpinner();
        setButton();
    }

    private void setButton() {
        btDelete.setEnabled(false);
//        btUpdate.setClickable(false);
        btUpdate.setEnabled(false);
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tvDate.setText(dayOfMonth+"/"+month+"/"+year);
                            }
                        }, mYear, mMonth, mDay);
                dialog.show();
            }
        });
        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<VeMayBay> list = sqLiteVeMayBay.getAll();

                adapter.setVMB(list);
                recyclerView.setAdapter(adapter);
                float tong = 0;
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).isHanhli())
                    tong++;
                }
                tvTong.setText("Tong so ve co HL: "+String.valueOf(tong));
            }
        });
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                etid.setEnabled(false);
                btDelete.setEnabled(true);
                btUpdate.setEnabled(true);
                int id = Integer.parseInt(etid.getText().toString());
                VeMayBay s = sqLiteVeMayBay.getVMB(id);
                etName.setText(s.getName());
                checkBox.setChecked(s.isHanhli());
                tvDate.setText(s.getDateStart());
                for(int i=0; i<location.length; i++){
                    if(location[i].equals(s.getLocation())){
                        spinner.setSelection(i);
                        return;
                    }
                }
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int id = Integer.parseInt(etid.getText().toString());

                String name = etName.getText().toString();
                String location = spinner.getSelectedItem().toString();
                String date = tvDate.getText().toString();
                boolean checkbox = checkBox.isChecked();
                VeMayBay s = new VeMayBay(name, location, date, checkbox);
//                Log.d("OK", s.toString());
                sqLiteVeMayBay.addVMB(s);
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= Integer.parseInt(etid.getText().toString());
                if(checkBox.isChecked())
                    Toast.makeText(getApplication().getBaseContext(), "Ve co hanh li", Toast.LENGTH_LONG).show();
                else sqLiteVeMayBay.deleteVMB(id);
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etid.getText().toString());
//                etid.setEnabled(false);
                String name = etName.getText().toString();
                String location = spinner.getSelectedItem().toString();
                String date = tvDate.getText().toString();
                boolean checkbox = checkBox.isChecked();
                VeMayBay s = new VeMayBay(id, name, location, date, checkbox);
                sqLiteVeMayBay.updateVMB(s);
            }
        });
    }

    private void init() {
        btDate = findViewById(R.id.btdate);
        btDelete = findViewById(R.id.btDelete);
        btGet = findViewById(R.id.btGet);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btGetAll = findViewById(R.id.btAll);

        tvDate = findViewById(R.id.tvdate);
        tvTong = findViewById(R.id.tvtong);

        etid = findViewById(R.id.txtid);
        checkBox = findViewById(R.id.checkbox);
        etName = findViewById(R.id.txtName);
        spinner = findViewById(R.id.spiner);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        sqLiteVeMayBay = new SQLiteVeMayBay(this);
    }

    private void creatSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, location);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search,menu);
//        MenuItem item=menu.findItem(R.id.mSearch);
//        SearchView searchView= (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                List<KhoaHoc> list=sqLiteKhoaHoc.getStudentByName(newText);
//                adapter.setKhoaHoc(list);
//                recyclerView.setAdapter(adapter);
//                return true;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
}