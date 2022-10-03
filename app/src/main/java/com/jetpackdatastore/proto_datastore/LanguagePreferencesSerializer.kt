package com.jetpackdatastore.proto_datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.jetpackdatastore.LanguagePreferences
import java.io.InputStream
import java.io.OutputStream

object LanguagePreferencesSerializer : Serializer<LanguagePreferences> {

    override val defaultValue: LanguagePreferences = LanguagePreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LanguagePreferences {
        try {
            return LanguagePreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: LanguagePreferences, output: OutputStream) = t.writeTo(output)
}