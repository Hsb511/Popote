package com.team23.data.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.team23.data.daos.FavoriteDao
import com.team23.data.daos.IngredientDao
import com.team23.data.daos.InstructionDao
import com.team23.data.daos.PreferenceDao
import com.team23.data.daos.RecipeDao
import com.team23.data.daos.SummarizedRecipeDao
import com.team23.data.daos.TagDao
import com.team23.data.daos.UserDao
import com.team23.data.models.BaseRecipeDataModel
import com.team23.data.models.FavoriteDataModel
import com.team23.data.models.IngredientDataModel
import com.team23.data.models.InstructionDataModel
import com.team23.data.models.PreferenceDataModel
import com.team23.data.models.SummarizedRecipeDataModel
import com.team23.data.models.TagDataModel
import com.team23.data.models.UserDataModel

@Database(
	version = AppDatabase.LATEST_VERSION,
	entities = [
		SummarizedRecipeDataModel::class,
		BaseRecipeDataModel::class,
		TagDataModel::class,
		IngredientDataModel::class,
		InstructionDataModel::class,
		FavoriteDataModel::class,
		PreferenceDataModel::class,
		UserDataModel::class,
	],
	exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
	companion object {
		const val LATEST_VERSION = 4
	}

	abstract fun summarizedRecipeDao(): SummarizedRecipeDao
	abstract fun fullRecipeDao(): RecipeDao
	abstract fun tagDao(): TagDao
	abstract fun ingredientDao(): IngredientDao
	abstract fun instructionDao(): InstructionDao
	abstract fun favoriteDao(): FavoriteDao
	abstract fun preferenceDao(): PreferenceDao
	abstract fun userDao(): UserDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
	override fun migrate(database: SupportSQLiteDatabase) {
		database.execSQL("CREATE TABLE IF NOT EXISTS `FavoriteDataModel` (`id` INTEGER NOT NULL, `recipeId` TEXT NOT NULL, PRIMARY KEY(`id`))")
		database.execSQL("CREATE TABLE IF NOT EXISTS `PreferenceDataModel` (`id` INTEGER NOT NULL, `label` TEXT NOT NULL, `value` INTEGER NOT NULL, PRIMARY KEY(`id`))")
	}
}

val MIGRATION_2_3 = object : Migration(2, 3) {
	override fun migrate(database: SupportSQLiteDatabase) {
		database.execSQL("ALTER TABLE `BaseRecipeDataModel` ADD COLUMN `isSourceLocal` INTEGER DEFAULT 0 NOT NULL")
		database.execSQL("ALTER TABLE `BaseRecipeDataModel` ADD COLUMN `isTemporary` INTEGER DEFAULT 0 NOT NULL")
	}
}

val MIGRATION_3_4 = object : Migration(3, 4) {
	override fun migrate(database: SupportSQLiteDatabase) {
		database.execSQL("CREATE TABLE IF NOT EXISTS `UserDataModel` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))")
	}
}
