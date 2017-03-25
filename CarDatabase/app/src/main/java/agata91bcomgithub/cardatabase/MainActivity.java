package agata91bcomgithub.cardatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.FilterQueryProvider;
import android.widget.TextView;

import agata91bcomgithub.cardatabase.Add.AddNewCarActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.autocomplete_textview)
    AutoCompleteTextView autoCompleteTextView;

    private MotoDatabaseOpenHelper databaseOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        databaseOpenHelper = new MotoDatabaseOpenHelper(this);

        AutoCompleteAdapter adapter = new AutoCompleteAdapter(this,databaseOpenHelper.getAllItems());

        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return databaseOpenHelper.searchQuery(constraint);
            }
        });
        autoCompleteTextView.setAdapter(adapter);

    }

    @OnClick(R.id.add_new_car)
    void onAddNewCarButtonClick(){
        Intent intent = new Intent(this, AddNewCarActivity.class);
        startActivity(intent);
    }
}
