package space.mrandika.myapplestore.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import space.mrandika.myapplestore.R;

public class CheckoutActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PRICE = "extra_price";
    public static final String EXTRA_STORAGE = "extra_storage";
    public static final String EXTRA_COLOR = "extra_color";

    Toolbar toolbar;
    ImageView phone_photo;
    TextView phone_name, phone_storage, phone_color, text_total;
    CheckBox asurance_check;
    RadioGroup group_shipment, group_payment;
    Button btn_buy;

    DecimalFormat kursIndonesia;

    int total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        final String photo = getString(EXTRA_PHOTO);
        final String name = getString(EXTRA_NAME);
        final String price = getString(EXTRA_PRICE);
        final String storage = getString(EXTRA_STORAGE);
        final String color = getString(EXTRA_COLOR);

        toolbar = findViewById(R.id.toolbar);
        phone_photo = findViewById(R.id.phone_image);
        phone_name = findViewById(R.id.phone_name);
        phone_storage = findViewById(R.id.phone_storage);
        phone_color = findViewById(R.id.phone_color);
        text_total = findViewById(R.id.text_total);
        asurance_check = findViewById(R.id.asurance_check);
        group_shipment = findViewById(R.id.group_shipment);
        group_payment = findViewById(R.id.group_payment);
        btn_buy = findViewById(R.id.button_buy);

        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        total_price = Integer.parseInt(price);

        Glide.with(this).load(photo)
                .apply(new RequestOptions().override(550, 550))
                .into(phone_photo);
        phone_name.setText(name);
        text_total.setText(kursIndonesia.format(Double.parseDouble(price)));
        phone_storage.setText("Storage: "+storage);
        phone_color.setText("Color: "+color);

        setSupportActionBar(toolbar);
        toolbar.setSubtitle(name);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        asurance_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updatePrice(true, 5000);
                } else {
                    updatePrice(false, 5000);
                }
            }
        });

        group_shipment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.shipment_normal) {
                    updatePrice(true, 8000);
                } else if (checkedId == R.id.shipment_fast) {
                    updatePrice(true, 16000);
                }
            }
        });

        group_payment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.payment_merchant) {
                    updatePrice(true, 2500);
                } else if (checkedId == R.id.payment_credit) {
                    updatePrice(true, 25000);
                }
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(CheckoutActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Pembelian Berhasil!");
                pDialog.setContentText("Pembelian sebesar "+kursIndonesia.format(total_price)+" berhasil dilakukan!");
                pDialog.show();
            }
        });
    }

    private void updatePrice(boolean isIncrement, int value) {
        if (isIncrement) {
            total_price = total_price + value;
            text_total.setText(kursIndonesia.format(total_price));
        } else {
            total_price = total_price - value;
            text_total.setText(kursIndonesia.format(total_price));
        }
    }

    private String getString(String s) {
        return getIntent().getStringExtra(s);
    }
}
