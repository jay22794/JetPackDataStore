/*
* Copyright 2021 Test
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.jetpackdatastore.proto_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.jetpackdatastore.LanguagePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 *  Manages the proto data storage
 *
 *  @param context  The instance of [Context]
 */
class ProtoDataStoreManager(private val context: Context) {

    //Create a instance of Proto DataStore
    private val Context.userPreferredLanguageDataStore: DataStore<LanguagePreferences> by dataStore(
        fileName = FILE_NAME,
        serializer = LanguagePreferencesSerializer
    )

    /**
     *  Get the app language from Proto DataStore
     */
    val appLanguage: Flow<String> = context.userPreferredLanguageDataStore.data
        .map {
            it.language
        }

    /**
     *  Save the app language into Proto DataStore
     */
    suspend fun saveAppLanguage(language: String) {
        context.userPreferredLanguageDataStore.updateData {
            it.toBuilder()
                .setLanguage(language)
                .build()
        }
    }

    companion object {
        //Proto DataStore file name
        const val FILE_NAME = "language.pb"
    }
}
