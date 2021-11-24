package com.callor.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.callor.hello.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    /**
     * Fragment Binding 하기
     * 1. null 값으로 초기화를 한다.
     */
    private var _firstFragment:FragmentFirstBinding? = null
    /**
     * _firstFragment 와 fragment 의 모든 속성을 연결하여
     * _firstFragment 현재 클래스에서 safe 데이터 형식으로
     * 값을 설정하고
     * 외부로 전송할때는 get() 가 설정된 firsFragment 를 사용하는
     * 다소 독특한 방식
     *
     * 내부 클래스에서 변경되는 변수가 외부로 전달될때
     * 문제를 일으킬수 있기 때문에
     * 외부에서는 변수값을 변경하지 못하도록
     * Immutable(불변)객체로
     * 변환하는 작업
     */
    private val firFragment get() = _firstFragment!!

    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _firstFragment = FragmentFirstBinding.inflate(
                inflater,
                container,
                false)
        val root : View = firFragment.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _firstFragment = null
    }
}