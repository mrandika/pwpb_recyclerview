package space.mrandika.myapplestore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import space.mrandika.myapplestore.model.Phone;
import space.mrandika.myapplestore.adapter.PhoneAdapter;
import space.mrandika.myapplestore.model.PhoneData;
import space.mrandika.myapplestore.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_phone;
    private ArrayList<Phone> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_phone = findViewById(R.id.rv_phone);
        rv_phone.setHasFixedSize(true);

        list.addAll(PhoneData.getListData());
        showListRv();
    }

    private void showListRv() {
        rv_phone.setLayoutManager(new LinearLayoutManager(this));
        PhoneAdapter phoneAdapter = new PhoneAdapter(list, this);
        rv_phone.setAdapter(phoneAdapter);
    }
}
