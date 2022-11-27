package com.mingrisoft.whj.db.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class},version = 2,exportSchema = false)    //Database:用于告诉系统这是Room数据库对象   entities属性用于指定该数据库有哪些表，若需建立多张表，以逗号相隔开。version属性用于指定数据库版本号，后续数据库的升级正是依据版本号来判断的
public abstract class StudentDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="my_student.db";     //数据库名字
    private static StudentDatabase instance;

    public static StudentDatabase getInstance(Context context){
        if (instance==null){
            synchronized (StudentDatabase.class){
                if (instance==null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,DATABASE_NAME)
                            .allowMainThreadQueries()     //允许主线程操作数据库
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return instance;
    }

    //数据库升级，添加一个字段sex 类型：TEXT
    private static final Migration MIGRATION_1_2 = new Migration(1, 2){
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student " + " ADD COLUMN sex TEXT");
        }
    };

    public abstract StudentDao studentDao();

}
