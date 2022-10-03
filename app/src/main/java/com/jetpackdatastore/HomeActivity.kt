/*
* Copyright 2022
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

package com.jetpackdatastore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jetpackdatastore.databinding.ActivityHomeBinding
import com.jetpackdatastore.prefrence_datastore.PreferencesDataStoreActivity
import com.jetpackdatastore.proto_datastore.ProtoDataStoreActivity

/**
 * Activity represents launcher screen
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setListeners()
    }

    /**
     * Set Listeners
     */
    private fun setListeners() {
        mBinding.buttonPreferencesDatastore.setOnClickListener {
            startActivity(Intent(this, PreferencesDataStoreActivity::class.java))
        }
        mBinding.buttonProtoDatastore.setOnClickListener {
            startActivity(Intent(this, ProtoDataStoreActivity::class.java))
        }
    }
}
