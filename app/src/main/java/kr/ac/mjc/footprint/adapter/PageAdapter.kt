package kr.ac.mjc.footprint.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kr.ac.mjc.footprint.Fragment.CommunityFragment
import kr.ac.mjc.footprint.Fragment.HomeFragment
import kr.ac.mjc.footprint.Fragment.NoteFragment
import kr.ac.mjc.footprint.Fragment.ProfileFragment

class PageAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    //이 클래스를 상속 받으면 오버라이딩을 해야하는 함수들을 해줘야한다
    override fun getItem(position: Int): Fragment { //각각의 텝이 눌러질 떄마다 그 포지션이 호출이 된다.
        if(position==0){
            return HomeFragment()
        } else if(position==1) {
            return CommunityFragment()
        } else if(position==2){
            return NoteFragment()
        } else{
            return ProfileFragment()

        }



    }

    override fun getCount(): Int {
       return 4
    }

}