package com.yipeng.bottomnavigationsample.ui.files

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yipeng.bottomnavigationsample.databinding.FragmentFilesBinding
import java.io.File
import java.io.FileOutputStream

class FilesFragment : Fragment() {

    private var _binding: FragmentFilesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val filesViewModel =
            ViewModelProvider(this).get(FilesViewModel::class.java)

        _binding = FragmentFilesBinding.inflate(inflater, container, false)
        val editText = binding.editTextTextMultiLine
        val buttonForSave = binding.buttonForSave
        buttonForSave.setOnClickListener {
            val newText = editText.text.toString()
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val folderPath = File(downloadsDir, "AirCast")
            if (!folderPath.exists()) {
                folderPath.mkdirs()
            }
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)  {
                val file = File(folderPath, "hello.txt")
                if (file.createNewFile()) {
                    FileOutputStream(file).use {
                        it.write(newText.toByteArray())
                    }
                    MediaScannerConnection.scanFile(context, arrayOf(file.path), null, null)
                }
            }
        }

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}