package com.yipeng.bottomnavigationsample.ui.files

import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//        val view = inflater.inflate(R.layout.fragment_files, container, false)
//        val editText = view.findViewById<EditText>(R.id.editTextTextMultiLine)
//        val buttonForSave = view.findViewById<Button>(R.id.buttonForSave)


        _binding = FragmentFilesBinding.inflate(inflater, container, false)
        val editText = binding.editTextTextMultiLine
        val buttonForSave = binding.buttonForSave
        buttonForSave.setOnClickListener {
            val newText = editText.text.toString()
            val folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
            val file = File(folderPath, ".txt")
            val fos = FileOutputStream(file)
            fos.write(newText.toByteArray())
            fos.close()
            MediaScannerConnection.scanFile(context, arrayOf(file.path), null, null)
        }

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}