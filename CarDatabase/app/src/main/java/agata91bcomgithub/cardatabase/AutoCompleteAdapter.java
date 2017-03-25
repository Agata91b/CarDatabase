package agata91bcomgithub.cardatabase;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

/**
 * Created by RENT on 2017-03-25.
 */

public class AutoCompleteAdapter  extends CursorAdapter implements Filterable{


    public AutoCompleteAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.single_auto_complete_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_MAKE)));
    }


}
