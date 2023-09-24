package com.yipeng.bottomnavigationsample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yipeng.bottomnavigationsample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // 准备Binding对象
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 在Fragment中获取VideoView对象,保存为videoView成员变量。
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // 加载Fragment布局，通过 Binding 的 inflate() 来加载 fragment_dashboard.xml 布局文件。
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 设置根视图
        // Fragment的视图通过Binding的root元素设置
        val root: View = binding.root

//        val videoView: VideoView = binding.videoView
//        homeViewModel.currentVideo.observe(viewLifecycleOwner) { uri ->
//            videoView.setVideoURI(uri)
//            videoView.start()
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}