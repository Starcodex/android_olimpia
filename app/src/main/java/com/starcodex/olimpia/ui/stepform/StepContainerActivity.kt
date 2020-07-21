package com.starcodex.olimpia.ui.stepform

import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.libraries.maps.model.LatLng
import com.starcodex.commons.app.DaggerViewModelFactory
import com.starcodex.domain.auth.model.UserInfo
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.adapter.ScreenPageChangeCallback
import com.starcodex.olimpia.ui.stepform.adapter.ScreenPageSelected
import com.starcodex.olimpia.ui.stepform.adapter.ScreenSlidePagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_stepcontainer.*
import javax.inject.Inject


class StepContainerActivity : DaggerAppCompatActivity(), ScreenPageSelected, ActivityPagerControl{

    private lateinit var stepPagesAdapter : ScreenSlidePagerAdapter
    private var userInfo = UserInfo()

    @Inject
    lateinit var factory: DaggerViewModelFactory

    lateinit var stepContainerViewModel : StepContainerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepcontainer)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)

        stepPagesAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = stepPagesAdapter
        viewPager.registerOnPageChangeCallback(ScreenPageChangeCallback(this))
        viewPager.isUserInputEnabled = false

        stepContainerViewModel = ViewModelProviders.of(this, factory).get(StepContainerViewModel::class.java)
        stepContainerViewModel.getApiAccess()
    }


    override fun pageSelected(position: Int) {
        supportActionBar!!.title = resources.getString(stepPagesAdapter.getTitle(position))
    }

    override fun nextPage() {
        viewPager.setCurrentItem(viewPager.currentItem+1, true)
    }

    override fun previouspage() {
        viewPager.setCurrentItem(viewPager.currentItem-1, true)
    }

    override fun updatePersonalData(
        name: String,
        idNumber: String,
        address: String,
        city: String,
        country: String,
        phoneNumber: String
    ) {
        userInfo.name = name
        userInfo.id_number = idNumber
        userInfo.address = address
        userInfo.city = city
        userInfo.country = country
        userInfo.phone_number = phoneNumber
    }

    override fun updatePicturePath(currentPhotoPath: String) {
        userInfo.picture = currentPhotoPath
    }

    override fun updateCurrentLocation(location: LatLng) {
        userInfo.latitude = location.latitude
        userInfo.longitude = location.longitude
    }

    override fun updateWBStatus(wifi: String, bluetooth: String) {
        userInfo.wifi_status = wifi
        userInfo.bluetooth_status = bluetooth
    }

    override fun getUserInfo(): UserInfo = userInfo


}