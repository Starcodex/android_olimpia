package com.starcodex.olimpia.ui.stepform.fragment

import android.Manifest
import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_steppicture.*
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class StepPictureFragment() : Fragment() {

    private val PERMISSION_REQUEST_CODE = 1234
    private val INTENT_IMAGE_CODE = 4321

    val PERMISSIONS = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    lateinit var currentPhotoPath: String

    @Inject
    lateinit var pagerControl: ActivityPagerControl

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_steppicture, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonNext.setOnClickListener {
            pagerControl.updatePicturePath(currentPhotoPath)
            pagerControl.nextPage()
        }
        buttonPrevious.setOnClickListener { pagerControl.previouspage() }
        buttonLoadPicture.setOnClickListener { requestImage() }
    }

    fun requestImage(){

        if (!hasPermissions(requireActivity().applicationContext, PERMISSIONS)) {
            ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE)
        }else{
            launchIntent()
        }
    }

    fun hasPermissions(context: Context,  permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    fun launchIntent(){
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val pickPhoto = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickPhoto.type = "image/*"
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            null
        }
        // Continue only if the File was successfully created
        photoFile?.also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.example.android.fileprovider",
                it
            )
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            val chooser = Intent.createChooser(pickPhoto, "Load Image")
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(takePicture))
            startActivityForResult(chooser, INTENT_IMAGE_CODE)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                   launchIntent()
                } else {
                    Toast.makeText(activity,"Permissions needed in order to upload picture", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == INTENT_IMAGE_CODE && resultCode == RESULT_OK) {
            if(data!=null){
                if(data.data!=null){
                    val selectedImage: Uri = data.data!!
                    currentPhotoPath = getPathFromUri(selectedImage)
                }
            }
            buttonNext.isEnabled = true

            Glide.with(requireActivity())
                .load(File(currentPhotoPath).absolutePath) // Uri of the picture
            .into(imagePreview)
        }
    }

    fun getPathFromUri(contentUri: Uri): String{
        val proj = arrayOf(MediaStore.Audio.Media.DATA)
        val cursor : Cursor = requireActivity().contentResolver.query(contentUri,proj,null,null,null)!!
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }



    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    override fun onResume() {
        super.onResume()
        if (!hasPermissions(requireActivity().applicationContext, PERMISSIONS)) {
            ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE)
        }
    }
}