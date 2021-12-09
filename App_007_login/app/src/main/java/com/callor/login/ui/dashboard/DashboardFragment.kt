package com.callor.login.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.callor.login.R
import com.callor.login.databinding.FragmentDashboardBinding
import com.callor.login.ui.AuthFragmentParent

/**
 * 만약 대쉬보드 메뉴가 선택되면
 * 대쉬보드 Fragment 를 열릴텐데
 * 만약 login 이 되어 있지 않으면 login 화면으로 redirect 를 수행할 것이다
 */
class DashboardFragment : AuthFragmentParent() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        // dashboardViewModel 에 담겨있는 text 변수의 값이 변경이 되면
        // (.observe) : 감시를 하고 있는 실행 코드
        dashboardViewModel.text.observe(viewLifecycleOwner,
            Observer {
                textView.text = it
            }
        )
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}