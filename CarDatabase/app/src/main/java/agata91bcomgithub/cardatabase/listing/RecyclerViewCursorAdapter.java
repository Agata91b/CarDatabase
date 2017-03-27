package agata91bcomgithub.cardatabase.listing;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import agata91bcomgithub.cardatabase.CarsTableContracts;
import agata91bcomgithub.cardatabase.R;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.*;

/**
 * Created by RENT on 2017-03-27.
 */

public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<RecyclerViewCursorAdapter.ViewHolder> {
    private Cursor cursor;

    @Override
    public RecyclerViewCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewCursorAdapter.ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String imageUrl = cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_IMAGE));
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
    }
