package foosground.models.serialization

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.google.gson.stream.JsonToken.NULL

class LocalDateTimeAdapter(private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME) : TypeAdapter<LocalDateTime>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter?, value: LocalDateTime?) {
        if (value == null) {
            out?.nullValue()
        } else {
            out?.value(formatter.format(value))
        }
    }

    @Throws(IOException::class)
    override fun read(out: JsonReader?): LocalDateTime? {
        out ?: return null

        return when (out.peek()) {
            NULL -> {
                out.nextNull()
                null
            }
            else -> {
                LocalDateTime.parse(out.nextString(), formatter)
            }
        }
    }
}