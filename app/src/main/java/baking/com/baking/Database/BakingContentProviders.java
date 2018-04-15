package baking.com.baking.Database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BakingContentProviders extends ContentProvider {

    private static final String PROVIDER_NAME = "baking.com.baking.BakingContentProviders";
    private static final String URL_STEPS = "content://" + PROVIDER_NAME + "/"+ BakingContractClass.RecipeStepsWatched.TABLE_NAME+ "";
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final Uri CONTENT_URL = Uri.parse(URL_STEPS);
    private SQLiteDatabase sqLiteDatabase;

    static {
        uriMatcher.addURI(PROVIDER_NAME, BakingContractClass.RecipeStepsWatched.TABLE_NAME,1);
    }

    @Override
    public boolean onCreate() {

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        BakingDatabaseHelper bakingDatabaseHelper = new BakingDatabaseHelper(getContext());
        sqLiteDatabase = bakingDatabaseHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                cursor = sqLiteDatabase.query(BakingContractClass.RecipeStepsWatched.TABLE_NAME,strings,s,strings1,null,null,null);
                break;


        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        sqLiteDatabase = new BakingDatabaseHelper(getContext()).getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                sqLiteDatabase.insert(BakingContractClass.RecipeStepsWatched.TABLE_NAME, null, contentValues);
                break;

        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        sqLiteDatabase = new BakingDatabaseHelper(getContext()).getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                sqLiteDatabase.delete(BakingContractClass.RecipeStepsWatched.TABLE_NAME,s,strings);
                break;

        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        sqLiteDatabase = new BakingDatabaseHelper(getContext()).getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                return sqLiteDatabase.update(BakingContractClass.RecipeStepsWatched.TABLE_NAME,contentValues,s,strings);


        }
        return 0;

    }

    public  class BakingDatabaseHelper extends SQLiteOpenHelper{

        private final static String DATABASE_NAME = "Baking8.db";
        private static final int DATABASE_VERSION = 1;

        public BakingDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            final String SQL_CREATE_RECIPE_STEPS_DB = "CREATE TABLE IF NOT EXISTS "
                    + BakingContractClass.RecipeStepsWatched.TABLE_NAME + "("
                    + BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID + " INTEGER NOT NULL , "
                    + BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID + " INTEGER NOT NULL ,"
                    + BakingContractClass.RecipeStepsWatched.COLUMN_WATCHED + " INTEGER DEFAULT 0 , " +
                    "PRIMARY KEY("+ BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID+ "," +
                      BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID +  "));";
            try{
                sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_STEPS_DB);
            }
            catch (SQLiteException se){
                se.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
