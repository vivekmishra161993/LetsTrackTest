package com.app.lets_track_test.news.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_table")
data class ArticlesItem(

	@ColumnInfo
	@field:SerializedName("publishedAt")
	var publishedAt: String? = "",

	@ColumnInfo
	@field:SerializedName("author")
	var author: String? = "",

	@ColumnInfo
	@field:SerializedName("urlToImage")
	var urlToImage: String? = "",

	@ColumnInfo
	@field:SerializedName("description")
	var description: String? = "",

	@ColumnInfo
	@field:SerializedName("title")
	var title: String? = "",

	@ColumnInfo
	@field:SerializedName("url")
	var url: String? = "",

	@ColumnInfo
	@field:SerializedName("content")
	var content: String? = "",

	@PrimaryKey(autoGenerate = true)
	var id:Int=0,

	@ColumnInfo
	var img_path:String?=""
)