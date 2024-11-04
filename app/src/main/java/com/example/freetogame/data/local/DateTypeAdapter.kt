package com.example.freetogame.data.local

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Rigoberto Torres on 02/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
class DateTypeAdapter : JsonDeserializer<Date> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
        return try {
            dateFormat.parse(json.asString) ?: Date()
        } catch (e: Exception) {
            Date()
        }
    }
}
