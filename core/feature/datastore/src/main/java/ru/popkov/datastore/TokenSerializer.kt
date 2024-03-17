package ru.popkov.datastore

import androidx.datastore.core.Serializer
import ru.popkov.android.core.feature.datastore.ProtoToken
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream
import java.util.InvalidPropertiesFormatException

object TokenSerializer : Serializer<ProtoToken> {
    override val defaultValue: ProtoToken = ProtoToken.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoToken {
        return try {
            ProtoToken.parseFrom(input)
        } catch (e: InvalidPropertiesFormatException) {
            Timber.tag("SETTINGS").e("Cannot read proto. Create default.")
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProtoToken, output: OutputStream) = t.writeTo(output)
}