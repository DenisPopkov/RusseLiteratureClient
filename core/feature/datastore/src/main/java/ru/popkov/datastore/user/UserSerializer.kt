package ru.popkov.datastore.user

import androidx.datastore.core.Serializer
import ru.popkov.android.core.feature.datastore.ProtoUser
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream
import java.util.InvalidPropertiesFormatException

object UserSerializer : Serializer<ProtoUser> {
    override val defaultValue: ProtoUser = ProtoUser.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoUser {
        return try {
            ProtoUser.parseFrom(input)
        } catch (e: InvalidPropertiesFormatException) {
            Timber.tag("SETTINGS").e("Cannot read proto. Create default.")
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProtoUser, output: OutputStream) = t.writeTo(output)
}