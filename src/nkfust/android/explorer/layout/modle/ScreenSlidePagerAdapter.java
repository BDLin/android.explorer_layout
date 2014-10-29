package nkfust.android.explorer.layout.modle;

import java.util.List;

import com.viewpagerindicator.IconPagerAdapter;

import nkfust.android.explorer.layout.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter{

	private List<Fragment> fragmentList;
	private List<String>   titleList;
	
	protected static final int[] ICONS = new int[] {
        R.drawable.box_small_icon,
        R.drawable.download_folder_small_icon,
        R.drawable.setting_small_icon
	};
	
	public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String>   titleList) {
		super(fm);
		this.fragmentList = fragmentList;
		this.titleList = titleList;
	}

	@Override
	public Fragment getItem(int position) {
		return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(position);
	}
	
	public CharSequence getPageTitle(int position) {
        return (titleList.size() > position) ? titleList.get(position) : "";
    }

	@Override
	public int getCount() {
		return fragmentList == null ? 0 : fragmentList.size();
	}

	@Override
	public int getIconResId(int index) {
		return ICONS[index % ICONS.length];
	}

}
