package agata91bcomgithub.cardatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by RENT on 2017-03-25.
 */

public class MotoDatabaseOpenHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "car.db";

    private static String SQL_CREATE_TABLE = "CREATE TABLE " + CarsTableContracts.TABLE_NAME + " ("
            + CarsTableContracts._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CarsTableContracts.COLUMN_MAKE + " TEXT, "
            + CarsTableContracts.COLUMN_MODEL + " TEXT, "
            + CarsTableContracts.COLUMN_IMAGE + " TEXT, "
            + CarsTableContracts.COLUMN_YEAR + " INT)";


    private static String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + CarsTableContracts.TABLE_NAME;
    public MotoDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    public boolean insertCar(Car car ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarsTableContracts.COLUMN_MAKE, car.getMake());
        contentValues.put(CarsTableContracts.COLUMN_MODEL, car.getModel());
        contentValues.put(CarsTableContracts.COLUMN_IMAGE, car.getImage());
        contentValues.put(CarsTableContracts.COLUMN_YEAR, car.getYear());

        long value = getWritableDatabase().insert(CarsTableContracts.TABLE_NAME, null, contentValues);

        return value != -1;
    }

        public Cursor getAllItems() {
         Cursor cursor = getReadableDatabase().query(CarsTableContracts.TABLE_NAME, new String[]{
                    CarsTableContracts._ID,
                    CarsTableContracts.COLUMN_MAKE,
                       CarsTableContracts.COLUMN_MODEL,
                       CarsTableContracts.COLUMN_IMAGE,
                       CarsTableContracts.COLUMN_YEAR
               }, null, null, null, null, null);
            Log.d("result", "colorSize" + cursor.getCount());
            return cursor;
        }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(SQL_DROP_TABLE);
            onCreate(db);
        }

    }
    public Car getCarWithID (String id){
        Cursor cursor = getReadableDatabase().query(CarsTableContracts.TABLE_NAME, null,
                CarsTableContracts._ID + " == ?", new String[]{id}, null, null, null);


        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Car car = new CarBuilder()
                    .setMake(cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_MAKE)))
                    .setImage(cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_IMAGE)))
                    .setYear(cursor.getInt(cursor.getColumnIndex(CarsTableContracts.COLUMN_YEAR)))
                    .setModel(cursor.getString(cursor.getColumnIndex(CarsTableContracts.COLUMN_MODEL)))
                    .createCar();
            return car;
        }
        cursor.close();
        return null;
    }


    public Cursor searchQuery(CharSequence constraint) {
        Cursor cursor;
        cursor = getReadableDatabase().query(CarsTableContracts.TABLE_NAME, new String[]{
                CarsTableContracts._ID,
                CarsTableContracts.COLUMN_MAKE,
                CarsTableContracts.COLUMN_MODEL,
                CarsTableContracts.COLUMN_IMAGE,
                CarsTableContracts.COLUMN_YEAR
        }, CarsTableContracts.COLUMN_MAKE + " =?", new String[] {constraint.toString()}, null, null, null);
        Log.d("result", "colorSize" + cursor.getCount());
        return cursor;
    }
}
