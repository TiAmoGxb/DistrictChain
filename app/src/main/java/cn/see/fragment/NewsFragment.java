package cn.see.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.see.R;
import cn.see.adapter.CustPagerFragmentAdapter;
import cn.see.base.BaseFragement;
import cn.see.fragment.fragmentview.homeview.AttentionFragment;
import cn.see.fragment.fragmentview.homeview.QualityLifeFragment;
import cn.see.fragment.fragmentview.newsview.MsgFragment;
import cn.see.fragment.fragmentview.newsview.PrivateLetterFragment;

/**
 * @日期：2018/6/5
 * @作者： GuoXinBo
 * @邮箱： guoxinbo@banling.com
 * @说明： 消息通知Frg
 */

public class NewsFragment extends BaseFragement {

    private List<Fragment> fragmentList;
    private TextView[] tabs;
    private View[] views;
    private int lastP = 0;

    @BindView(R.id.nes_vp)
    ViewPager pager;
    @BindView(R.id.msg_tv)
    TextView msgTv;
    @BindView(R.id.send_tv)
    TextView sendTv;
    @BindView(R.id.msg_v)
    View msgv;
    @BindView(R.id.send_v)
    View snedv;

    @OnClick(R.id.msg_tv)
    void msg_tv(){
        pager.setCurrentItem(0);
    }

    @OnClick(R.id.send_tv)
    void send_tv(){
        pager.setCurrentItem(1);
    }


    @Override
    public void initView() {
        tabs = new TextView[]{msgTv,sendTv};
        views = new View[]{msgv,snedv};
    }

    @Override
    public void initAfter() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MsgFragment());
        fragmentList.add(new PrivateLetterFragment());
        pager.setOffscreenPageLimit(fragmentList.size());
        pager.setAdapter(new CustPagerFragmentAdapter(getChildFragmentManager(),fragmentList));
        pager.setCurrentItem(lastP);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_news_fragment;
    }

    @Override
    public XPresent bindPresent() {
        return null;
    }

    @Override
    public void setListener() {

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabs[position].setTextColor(getResources().getColor(R.color.text_3d));
                tabs[lastP].setTextColor(getResources().getColor(R.color.text_101010));
                views[position].setVisibility(View.VISIBLE);
                views[lastP].setVisibility(View.GONE);
                lastP  = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }
}
