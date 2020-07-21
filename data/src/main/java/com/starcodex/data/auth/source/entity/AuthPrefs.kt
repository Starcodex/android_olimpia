package com.starcodex.data.auth.source.entity

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.starcodex.domain.auth.model.ApiDataAccess

class AuthPrefs (var context: Context) {

    private val API_TOKEN = "api_token"
    private val ACCESS_TOKEN = "access_token"
    private val TOKEN_TYPE = "token_type"
    private val TOKEN_EXPIRATION = "token_expiration"

    fun saveApiTokenData(apiAccess : ApiDataAccess){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit {
            putString(API_TOKEN, apiAccess.token_type+""+apiAccess.access_token)
            putString(ACCESS_TOKEN, apiAccess.access_token)
            putString(TOKEN_TYPE, apiAccess.token_type)
            putString(TOKEN_EXPIRATION, apiAccess.expires_at)
        }
    }

    fun getApiTokenData() : ApiAccess{
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return ApiAccess(
            sharedPreferences.getString(ACCESS_TOKEN,""),
            sharedPreferences.getString(TOKEN_TYPE,""),
            sharedPreferences.getString(TOKEN_EXPIRATION,"")
        )
    }

    fun getApiToken() : String? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(API_TOKEN,"")
    }
}