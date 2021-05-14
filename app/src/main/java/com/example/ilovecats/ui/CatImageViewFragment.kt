package com.example.ilovecats.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.ilovecats.Helpers.Helpers
import com.example.ilovecats.R
import com.example.ilovecats.databinding.CatImageViewFragmentBinding
import com.example.ilovecats.models.Cat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatImageViewFragment : Fragment() {

    companion object {
        fun newInstance() = CatImageViewFragment()
    }

     val viewModel: CatImageViewViewModel by viewModels()
    private lateinit var binding: CatImageViewFragmentBinding
    private lateinit var cat: Cat
    private val REQUEST_CODE = 101
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cat_image_view_fragment, container, false)
        binding.lifecycleOwner = this


        cat = arguments?.getParcelable<Cat>("cat")!!
        binding.cat = cat
        binding.share.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, cat.url)
                type = "image/jpeg"
            }

//            startActivity(Intent.createChooser(shareIntent, "Share cat"))
        }
        binding.save.setOnClickListener {
            checkPermission()
        }
        return binding.root
    }

    // Function to check and request permission.
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
        } else {
            Helpers.saveImage(cat.url,requireContext())
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Helpers.saveImage(cat.url,requireContext())
            } else {
                Toast.makeText(requireContext(), "Storage Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }



}