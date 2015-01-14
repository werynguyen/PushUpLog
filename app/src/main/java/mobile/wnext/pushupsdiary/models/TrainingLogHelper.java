package mobile.wnext.pushupsdiary.models;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Nnguyen on 12/01/2015.
 */
public class TrainingLogHelper extends TableHelper {

    // the DAO object we use to access the SimpleData table
    private Dao<TrainingLog, Integer> simpleDao = null;
    private RuntimeExceptionDao<TrainingLog, Integer> simpleRuntimeDao = null;

    private OrmLiteSqliteOpenHelper dbHelper;

    public static TrainingLogHelper getInstance(OrmLiteSqliteOpenHelper dbHelper) {
        if(singleton == null) singleton = new TrainingLogHelper(dbHelper);
        return singleton;
    }
    private static TrainingLogHelper singleton;
    private TrainingLogHelper(OrmLiteSqliteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(this.getClass().getName(), "onCreate");
            TableUtils.createTable(connectionSource, TrainingLog.class);
        } catch (SQLException e) {
            Log.e(this.getClass().getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(TrainingLogHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, TrainingLog.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(this.getClass().getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }
}
