package com.starcodex.olimpia.ui.stepform.fragment.stepsave

import androidx.lifecycle.ViewModel
import com.starcodex.domain.auth.model.UserInfo
import com.starcodex.domain.stepform.model.UserInfoResponse
import com.starcodex.domain.stepform.usecase.SaveUserInfoUseCase
import com.starcodex.olimpia.R
import com.starcodex.olimpia.util.arch.MutableProcessEventLiveData
import com.starcodex.olimpia.util.arch.ProcessEventLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import javax.inject.Inject

class StepSaveViewModel @Inject constructor(private val saveUserInfoUseCase: SaveUserInfoUseCase) : ViewModel() {

    private val compositeDisposable =  CompositeDisposable()

    private val saveInfoProcessStatusLiveData =
        MutableProcessEventLiveData<Any>()

    fun saveUserInfo(userInfo: UserInfo){
        compositeDisposable.add(
            saveUserInfoUseCase.saveUserInfo(userInfo)
                .doOnSubscribe {
                    saveInfoProcessStatusLiveData.setLoading(R.string.sending_user_info)
                }.subscribeWith(object : DisposableMaybeObserver<UserInfoResponse>(){
                    override fun onSuccess(t: UserInfoResponse) {
                        saveInfoProcessStatusLiveData.setSuccess(t)
                    }
                    override fun onError(e: Throwable) {
                        saveInfoProcessStatusLiveData.setError()
                    }

                    override fun onComplete() {
                        saveInfoProcessStatusLiveData.setSuccess(null)
                    }
                })
        )
    }

    fun getSaveInfoProcessStatusLiveData(): ProcessEventLiveData<Any> =
        saveInfoProcessStatusLiveData
}