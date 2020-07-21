package com.starcodex.domain.auth.usecase

import android.util.Log
import com.starcodex.commons.app.ExecutionThreads
import com.starcodex.domain.auth.repository.AuthRepository
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetApiAccessUsecaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    var executionThreads: ExecutionThreads
): GetApiAccessUsecase {

    val compositeDisposable =  CompositeDisposable()

    override fun getApiAccess() {
        val apiAccess = authRepository.getLocalApiDataAccess()
        if(apiAccess.expires_at!=""){
            val expirationFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault())
            val expirationDate: Date = expirationFormat.parse(apiAccess.expires_at!!)!!
            if (System.currentTimeMillis() > expirationDate.getTime()) {
                getRemoteApiAccess()
            }
        }else{
            getRemoteApiAccess()
        }
    }

    override fun getLocalApiToken() {
        authRepository.getLocalApiToken()
    }

    override fun getRemoteApiAccess() {
        compositeDisposable.addAll(authRepository.retrieveRemoteApiAccess()
            .observeOn(executionThreads.io())
            .subscribe {
                authRepository.saveApiDataAccess(it)
            Log.e("REMOTE ACESS KEY",it.access_token!!)
        })
    }
}