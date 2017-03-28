package agata91bcomgithub.cardatabase.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import agata91bcomgithub.cardatabase.Car;
import agata91bcomgithub.cardatabase.MotoDatabaseOpenHelper;
import agata91bcomgithub.cardatabase.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by RENT on 2017-03-28.
 */

public class DetailsFragmnet extends Fragment {

    private static final String CAR_ID_KEY = "car_id_key";

    @BindView(R.id.make_and_model)
    TextView makeAndModel;

    @BindView(R.id.year)
    TextView year;

    @BindView(R.id.detail_image)
    ImageView detailImage;


    private MotoDatabaseOpenHelper openHelper;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHelper = new MotoDatabaseOpenHelper(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmnet_details, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String carID = getArguments().getString(CAR_ID_KEY);
        unbinder = ButterKnife.bind(this, view);
        Car car = openHelper.getCarWithID(carID);
        makeAndModel.setText(car.getMake() + " " + car.getModel());
        year.setText("Rocznik: " + car.getYear());
        Glide.with(getActivity()).load(car.getImage()).into(detailImage);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static Fragment getInstance(String carId){
        DetailsFragmnet detailsFragmnet = new DetailsFragmnet();
        Bundle arguments = new Bundle();
        arguments.putString(CAR_ID_KEY, carId);
        detailsFragmnet.setArguments(arguments);
        return detailsFragmnet;
    }
}
