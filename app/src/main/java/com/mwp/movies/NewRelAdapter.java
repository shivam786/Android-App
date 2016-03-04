package com.mwp.movies;

import java.util.List;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NewRelAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	Activity _context;
	List<TopRatedDAta>topRatedList ;
	ImageLoader imageLoader = ImageLoader.getInstance();

	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.logo)
			.showImageForEmptyUri(R.drawable.logo)
			.showImageOnFail(R.drawable.logo).cacheInMemory(true)
			.cacheOnDisc(true).considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565).build();
	public NewRelAdapter(Activity topRatedActivity,
			List<TopRatedDAta> topRatedList) {
		// TODO Auto-generated constructor stub
		this._context=topRatedActivity;
		this.topRatedList=topRatedList;
		inflater = _context.getLayoutInflater();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (topRatedList!= null && topRatedList.size()!=0) {
			return topRatedList.size();
		}else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	int LastPosition = -1;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder _holder = null;
		if (convertView == null) {
			_holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row_album, null);
			_holder._albumImg = (ImageView) convertView
					.findViewById(R.id.albumImage);
			_holder._albumText = (TextView) convertView
					.findViewById(R.id.album_txt_01);
			_holder.releaseDate = (TextView) convertView
					.findViewById(R.id.album_txt_02);
            _holder.vote = (TextView)convertView.findViewById(R.id.album_txt_03);
            _holder.vote_count= (TextView)convertView.findViewById(R.id.album_txt_05);
			convertView.setTag(_holder);
		} else {
			_holder = (ViewHolder) convertView.getTag();
		}
		if (topRatedList!=null&&topRatedList.size()!=0) {
			_holder._albumText.setText(topRatedList.get(position).getTitle());
			_holder.releaseDate.setText(topRatedList.get(position).getRealeseDate());
            _holder.vote.setText(topRatedList.get(position).getVote_average());
            _holder.vote_count.setText(topRatedList.get(position).getVote_count());
			imageLoader.displayImage("http://image.tmdb.org/t/p/w300"+topRatedList.get(position).getPosterPAth(),
					_holder._albumImg, options);
		}

		View view = convertView;
		Animation anim  = AnimationUtils.loadAnimation(_context,(position > LastPosition) ? R.anim.abc_fade_in : R.anim.abc_fade_out);
		view.startAnimation(anim);

		return convertView;

	}
	
	private static class ViewHolder {
		private ImageView _albumImg;
		private TextView _albumText, releaseDate,vote,vote_count;

		
	}
}
