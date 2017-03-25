package agata91bcomgithub.cardatabase.Add;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import agata91bcomgithub.cardatabase.Car;
import agata91bcomgithub.cardatabase.CarBuilder;
import agata91bcomgithub.cardatabase.MotoDatabaseOpenHelper;
import agata91bcomgithub.cardatabase.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCarActivity extends AppCompatActivity {
    private MotoDatabaseOpenHelper databaseOpenHelper;

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.year)
    EditText year;
    @BindView(R.id.make)
    EditText make;
    @BindView(R.id.model)
    EditText model;

    String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        ButterKnife.bind(this);
        databaseOpenHelper = new MotoDatabaseOpenHelper(this);
    }

    @OnClick(R.id.add_car)
    void onAddCarButtonClick() {
        Car car = new CarBuilder()
                .setMake(make.getText().toString())
                .setModel(model.getText().toString())
                .setYear(Integer.parseInt(year.getText().toString()))
                .setImage("")
                .createCar();
        boolean added = databaseOpenHelper.insertCar(car);

        if(added){
            Toast.makeText(this, "Dodano nowy samochod", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.add_image)
    void onAddImageButtonClick() {
        View promptView = LayoutInflater.from(this).inflate(R.layout.dialog_prompt,null);
        EditText urlEditText = (EditText) findViewById(R.id.url_edit_text) ;

        new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imageUrl = urlEditText.getText().toString();
                        Glide.with(AddNewCarActivity.this)
                                .load(imageUrl)
                                .into(imageView);
                    }
                }).show();

    }
}
