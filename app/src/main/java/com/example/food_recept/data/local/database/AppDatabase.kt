package com.example.food_recept.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.food_recept.data.local.dao.UserDao
import com.example.food_recept.data.local.entity.Food
import com.example.food_recept.data.local.entity.User

@Database(
    entities = [User::class, Food::class],
    version = 6,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(4, 5, AppDatabase.AutoMigration::class),
        AutoMigration(5, 6)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    @DeleteColumn.Entries(
        DeleteColumn(
            tableName = "Food",
            columnName = "userCreatorId"
        )
    )
    class AutoMigration : AutoMigrationSpec

    companion object {
        val migration3To4 = object : Migration(2, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE Food ADD COLUMN newColumnName TEXT NOT NULL DEFAULT 'defaultValue'")
            }
        }
    }
}
