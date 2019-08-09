package space.mrandika.myapplestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import space.mrandika.myapplestore.R;
import space.mrandika.myapplestore.activity.DetailActivity;
import space.mrandika.myapplestore.model.Phone;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private ArrayList<Phone> listPhone;

    private Context context;

    public PhoneAdapter(ArrayList<Phone> list, Context context) {
        this.context = context;
        this.listPhone = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_phone, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Phone phone = listPhone.get(i);
        Glide.with(viewHolder.itemView.getContext()).load(phone.getPhoto())
                .apply(new RequestOptions().override(550, 550))
                .into(viewHolder.phone_image);
        viewHolder.phone_name.setText(phone.getName());
        viewHolder.phone_description.setText(phone.getDescription());
        viewHolder.phone_storagecolor.setText(String.format("%s, %s", phone.getStorage(), phone.getColor()));

        viewHolder.item_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(DetailActivity.EXTRA_PHOTO, phone.getPhoto());
                i.putExtra(DetailActivity.EXTRA_NAME, phone.getName());
                i.putExtra(DetailActivity.EXTRA_PRICE, String.valueOf(phone.getPrice()));
                i.putExtra(DetailActivity.EXTRA_STORAGE, phone.getStorage());
                i.putExtra(DetailActivity.EXTRA_COLOR, phone.getColor());
                i.putExtra(DetailActivity.EXTRA_DESCRIPTION, phone.getDescription());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhone.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout item_phone;
        ImageView phone_image;
        TextView phone_name, phone_description, phone_storagecolor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_phone = itemView.findViewById(R.id.item_phone);
            phone_image = itemView.findViewById(R.id.phone_image);
            phone_name = itemView.findViewById(R.id.phone_name);
            phone_description = itemView.findViewById(R.id.phone_description);
            phone_storagecolor = itemView.findViewById(R.id.phone_storagecolor);
        }
    }

}
