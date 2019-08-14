package space.mrandika.myapplestore.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;

import space.mrandika.myapplestore.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PRICE = "extra_price";
    public static final String EXTRA_STORAGE = "extra_storage";
    public static final String EXTRA_COLOR = "extra_color";
    public static final String EXTRA_DESCRIPTION = "extra_description";

    Toolbar toolbar;
    ImageView phone_photo;
    TextView phone_name, phone_price, phone_storage, phone_color, phone_description;
    Button btn_buy;

    DecimalFormat kursIndonesia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final String photo = getString(EXTRA_PHOTO);
        final String name = getString(EXTRA_NAME);
        final String price = getString(EXTRA_PRICE);
        final String storage = getString(EXTRA_STORAGE);
        final String color = getString(EXTRA_COLOR);
        String description = getString(EXTRA_DESCRIPTION);

        toolbar = findViewById(R.id.toolbar);
        phone_photo = findViewById(R.id.phone_image);
        phone_name = findViewById(R.id.phone_name);
        phone_price = findViewById(R.id.phone_price);
        phone_storage = findViewById(R.id.phone_storage);
        phone_color = findViewById(R.id.phone_color);
        phone_description = findViewById(R.id.phone_description);
        btn_buy = findViewById(R.id.btn_buy);

        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Glide.with(this).load(photo)
                .apply(new RequestOptions().override(550, 550))
                .into(phone_photo);
        phone_name.setText(name);
        phone_price.setText(kursIndonesia.format(Double.parseDouble(price)));
        phone_storage.setText("Storage: "+storage);
        phone_color.setText("Color: "+color);
        phone_description.setText(description);

        setSupportActionBar(toolbar);
        toolbar.setTitle(name);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_white_24dp));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, CheckoutActivity.class);
                i.putExtra(CheckoutActivity.EXTRA_PHOTO, photo);
                i.putExtra(CheckoutActivity.EXTRA_NAME, name);
                i.putExtra(CheckoutActivity.EXTRA_STORAGE, storage);
                i.putExtra(CheckoutActivity.EXTRA_COLOR, color);
                i.putExtra(CheckoutActivity.EXTRA_PRICE, price);
                startActivityIfNeeded(i, 0);
            }
        });
    }

    private String getString(String s) {
        return getIntent().getStringExtra(s);
    }
}
