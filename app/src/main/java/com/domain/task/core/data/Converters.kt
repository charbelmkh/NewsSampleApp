package com.domain.task.core.data

import androidx.room.TypeConverter
import com.domain.task.news.data.models.Multimedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    companion object {
        val gson = Gson()
        val listOfMultiMediaType: Type =object: TypeToken<List<Multimedia>>() {}.type
        val listOfString: Type =object : TypeToken<List<String>>() {}.type
    }
    @TypeConverter
    fun fromListOfMultimediaToJson(media: List<Multimedia>): String = gson.toJson(media)

    @TypeConverter
    fun fromJsonToListOfMultimedia(json: String): List<Multimedia> =gson.fromJson(json, listOfMultiMediaType)

    @TypeConverter
    fun fromListOfStringToJson(listOfString: List<String>): String = gson.toJson(listOfString)

    @TypeConverter
    fun fromJsonToListOfString(json: String): List<String> =gson.fromJson(json,listOfString)

}
