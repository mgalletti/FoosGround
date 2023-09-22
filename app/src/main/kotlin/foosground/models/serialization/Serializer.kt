package foosground.models.serialization

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.Duration
import java.time.LocalDateTime

class Serializer {
    companion object {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .registerTypeAdapter(Duration::class.java, DurationAdapter())
            .create()
    }

    fun <T>serialize(value: T): String {
        return gson.toJson(value)
    }

    inline fun <reified T>deserialize(value: String): T {
        return gson.fromJson<T>(value, object: TypeToken<T>() {}.type)
    }
}