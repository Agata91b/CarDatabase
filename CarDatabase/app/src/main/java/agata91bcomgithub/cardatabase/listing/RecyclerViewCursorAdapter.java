package agata91bcomgithub.cardatabase.listing;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import agata91bcomgithub.cardatabase.CarsTableContracts;
import agata91bcomgithub.cardatabase.R;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.*;

/**
 * Created by RENT on 2017-03-27.
 */

public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<RecyclerViewCursorAdapter.ViewHolder> {
    private Cursor cursor;
    private OnCarItemClickListener onCarItemClickListener;




    @Override
    public RecyclerViewCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCursorAdapter.ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String imageUrl = cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_IMAGE));
        String make = cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_MAKE));
        String model = cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_MODEL));
        int year  = cursor.getInt(cursor.getColumnIndex(CarsTableContracts.COLUMN_YEAR));

        holder.year.setText("Rocznik: " + year);
        holder.makeAndModel.setText(make + " " + model);
        Glide.with(holder.imageView.getContext()).load(imageUrl).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            if(onCarItemClickListener !=null) {
                cursor.moveToPosition(position);
                onCarItemClickListener.onCarItemClick(cursor.getString(0));
            }
        });
    }

    @Override
    public int getItemCount() {
        return  cursor != null ? cursor.getCount() : 0;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        TextView makeAndModel;

        TextView year;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = findById(itemView, R.id.image);
            makeAndModel = findById(itemView, R.id.make_and_model);
            year = findById(itemView, R.id.year);
        }
    }
        public void setOnCarItemClickListener(OnCarItemClickListener onCarItemClickListener) {
            this.onCarItemClickListener = onCarItemClickListener;
        }
    }
