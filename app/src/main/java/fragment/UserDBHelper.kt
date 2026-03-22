package fragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// SQLiteOpenHelper(context, 数据库名, CursorFactory, 数据库版本)
class UserDBHelper(context: Context) : SQLiteOpenHelper(context, "test.db", null, 1) {
    // 用来创建数据库
    override fun onCreate(db: SQLiteDatabase?) {
        // 数据库创建的语句
        val sql = """
            CREATE TABLE user_info (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                age INTEGER,
                height REAL,
                weight REAL
            )
        """.trimIndent()

        db?.execSQL(sql)
    }

    // 数据库版本更新
    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

}