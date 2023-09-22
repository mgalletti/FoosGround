package foosground.models.serialization

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.Duration

class DurationAdapter() : TypeAdapter<Duration>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter?, value: Duration?) {
        if (value == null) {
            out?.nullValue()
        } else {
            out?.value(value.toString())
        }
    }

    @Throws(IOException::class)
    override fun read(out: JsonReader?): Duration? {
        out ?: return null

        return when (out.peek()) {
            JsonToken.NULL -> {
                out.nextNull()
                null
            }
            else -> {
                Duration.parse(out.nextString())
            }
        }
    }
}