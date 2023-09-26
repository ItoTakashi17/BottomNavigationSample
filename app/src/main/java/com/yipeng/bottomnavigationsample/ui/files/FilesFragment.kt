package com.yipeng.bottomnavigationsample.ui.files

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.yipeng.bottomnavigationsample.databinding.FragmentFilesBinding
import java.io.File
import java.io.FileOutputStream

class FilesFragment : Fragment() {

    private var _binding: FragmentFilesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilesBinding.inflate(inflater, container, false)
        val editText = binding.editTextTextMultiLine
        val buttonForSave = binding.buttonForSave
        buttonForSave.setOnClickListener {
            val newText = editText.text.toString()
            val downloadsDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val folderPath = File(downloadsDir, "AirCast")
            if (!folderPath.exists()) {
                folderPath.mkdirs()
            }
            when{
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(context, "已授权", Toast.LENGTH_SHORT).show()
                    val file = File(folderPath, "$newText.mp3")
                    if (file.createNewFile()) {
                        FileOutputStream(file).use {
                            it.write(newText.toByteArray())
                        }
                        MediaScannerConnection.scanFile(context, arrayOf(file.path), null, null)
                    }
                }
                shouldShowRequestPermissionRationale(Manifest.permission.MANAGE_EXTERNAL_STORAGE) -> {
                    Toast.makeText(context, "我们需要您授予储存权限才能保存文件到设备", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "我们需要您授予储存权限才能保存文件到设备", Toast.LENGTH_SHORT).show()
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 222)
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_CODE) {
//            // 检查权限是否已经获取
//            if (ContextCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.MANAGE_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//
//            }
//
//        }
//
//    }
}