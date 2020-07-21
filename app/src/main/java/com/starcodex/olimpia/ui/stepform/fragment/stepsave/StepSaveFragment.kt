package com.starcodex.olimpia.ui.stepform.fragment.stepsave

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.starcodex.commons.app.DaggerViewModelFactory
import com.starcodex.domain.stepform.model.UserInfoResponse
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.home.HomeActivity
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import com.starcodex.olimpia.util.arch.ProcessEventObserver
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stepsave.*
import java.io.File
import javax.inject.Inject

class StepSaveFragment() : Fragment() {

    @Inject
    lateinit var pagerControl: ActivityPagerControl

    @Inject
    lateinit var factory: DaggerViewModelFactory

    lateinit var viewModel: StepSaveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_stepsave, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(StepSaveViewModel::class.java)

        buttonNext.setOnClickListener {
            loader.visibility = View.VISIBLE
            viewModel.saveUserInfo(pagerControl.getUserInfo())
        }

        buttonPrevious.setOnClickListener { pagerControl.previouspage() }

        viewModel.getSaveInfoProcessStatusLiveData()
            .observe(viewLifecycleOwner, ProcessEventObserver(
                onLoading = {
                    loader.visibility = View.VISIBLE
                    buttonNext.isEnabled = false
                    buttonPrevious.isEnabled = false
                },
                onSuccess = {
                    loader.visibility = View.GONE
                    it as UserInfoResponse
                    Toast.makeText(requireContext(), it.success, Toast.LENGTH_SHORT).show()
                    activity?.startActivity(Intent(activity, HomeActivity::class.java))
                    activity?.finish()
                },
                onError = {
                    loader.visibility = View.GONE
                    Toast.makeText(requireContext(), "Ha ocurrido un error inesperado", Toast.LENGTH_SHORT).show()
                    buttonNext.isEnabled = true
                    buttonPrevious.isEnabled = true
                }
            ))

    }

    fun populatedataView(){
        val userInfo = pagerControl.getUserInfo()
        Glide.with(requireActivity())
            .load(File(userInfo.picture!!)) // Uri of the picture
            .into(imageView)
        textName.text = userInfo.name
        textIdNumber.text = userInfo.id_number
        textAddress.text = userInfo.address
        textCity.text = userInfo.city
        textCountry.text = userInfo.country
        textPhoneNumber.text = userInfo.phone_number
        textLatitude.text = userInfo.latitude.toString()
        textLongitude.text = userInfo.longitude.toString()
        textWifiStatus.text = userInfo.wifi_status
        textBluetoothStatus.text = userInfo.bluetooth_status
    }

    override fun onResume() {
        super.onResume()
        populatedataView()
    }
}