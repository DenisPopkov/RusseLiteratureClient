package ru.popkov.datastore.settings

import androidx.datastore.core.Serializer
import ru.popkov.android.core.feature.datastore.ProtoSettings
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream
import java.util.InvalidPropertiesFormatException

object SettingsSerializer : Serializer<ProtoSettings> {
    override val defaultValue: ProtoSettings = ProtoSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoSettings {
        return try {
            ProtoSettings.parseFrom(input)
        } catch (e: InvalidPropertiesFormatException) {
            Timber.tag("Settings proto:").e("Cannot read proto. Create default.")
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProtoSettings, output: OutputStream) = t.writeTo(output)
}