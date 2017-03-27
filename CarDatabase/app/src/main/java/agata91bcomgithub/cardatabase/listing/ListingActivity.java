package agata91bcomgithub.cardatabase.listing;

/**
 * Created by RENT on 2017-03-27.
 */


    import android.content.Context;
    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;

    import agata91bcomgithub.cardatabase.R;


public class ListingActivity extends AppCompatActivity {
        private static final String QUERY = "query";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listing);
        }

        public static Intent createIntent(Context context, String query) {
            Intent intent = new Intent(context, ListingActivity.class);
            intent.putExtra(QUERY, query);
            return intent;
        }
    }

