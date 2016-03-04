
package com.mwp.movies;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class NowPlayingActivity extends Activity {
	List<TopRatedDAta> topRatedList = new ArrayList<TopRatedDAta>();
	private ListView mainList;
	private Handler mHandler = null;
	private LayoutInflater inflater;
	boolean loadingMore;
	private NewRelAdapter mainListAdapter;
	View footer;
	private ProgressDialog dialog = null;
	int pageNo = 1;
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_list);
		inflater = getLayoutInflater();
		mainList = (ListView) findViewById(R.id.newReleaseList);
		footer = inflater.inflate(R.layout.footer, null);
		mHandler = new Handler();
		//get TopRated Movie and set on listview....
		setTopRatedList();
		
	}
	private void setTopRatedList() {
		// TODO Auto-generated method stub
		try {

			dialog = ProgressDialog.show(NowPlayingActivity.this, "",
					"Loading content please wait...");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					JSONData h = new JSONData(NowPlayingActivity.this);
					try {
						if (Utlity.isOnline(NowPlayingActivity.this)) {
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									int fvcount = mainList.getFooterViewsCount();
									for (int f = 0; f < fvcount; f++) {
										mainList.removeFooterView(footer);
									}
								}
							});
							
							topRatedList = h.getAlbumList(pageNo,"now_playing");
							
							if (topRatedList == null) {
								Log.d("albumlist is null", "-111");
								mHandler.post(new Runnable() {
									@Override
									public void run() {
										Toast.makeText(NowPlayingActivity.this,	"No Data Found",Toast.LENGTH_LONG).show();
										if (dialog != null && dialog.isShowing())
											dialog.dismiss();
										loadingMore = false;
										mainListAdapter = new NewRelAdapter(NowPlayingActivity.this,topRatedList);
										int fvcount = mainList.getFooterViewsCount();
										for (int f = 0; f < fvcount; f++) {
											mainList.removeFooterView(footer);
										}
										mainList.setAdapter(mainListAdapter);
										mainListAdapter.notifyDataSetChanged();

									}
								});
							} else {
								mHandler.post(new Runnable() {
									

									@Override
									public void run() {
										if (dialog != null && dialog.isShowing())
											dialog.dismiss();
										loadingMore = false;
										mainListAdapter = new NewRelAdapter(NowPlayingActivity.this,topRatedList);
										int fvcount = mainList.getFooterViewsCount();
										for (int f = 0; f < fvcount; f++) {
											mainList.removeFooterView(footer);
										}
										
										if (Utlity.isOnline(NowPlayingActivity.this)) {
											mainList.addFooterView(footer);
											}

										mainList.setAdapter(mainListAdapter);
										
										// pbar.setVisibility(View.GONE);
										mainList.setOnScrollListener(new OnScrollListener() {
											@Override
											public void onScrollStateChanged(
													AbsListView view,
													int scrollState) {
											}
											@Override
											public void onScroll(AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount) {
												int lastInScreen = firstVisibleItem+ visibleItemCount;
												if ((lastInScreen == totalItemCount)&& !(loadingMore)) {
													if (Utlity.isOnline(NowPlayingActivity.this)) {
														pageNo++;
														
														if (Utlity.isOnline(NowPlayingActivity.this)) {
															Thread thread = new Thread(getDataOnListEnd);
															thread.start();
														}
													}
												}
												if (!Utlity.isOnline(NowPlayingActivity.this)) {
													int fvcount = mainList.getFooterViewsCount();
													for (int f = 0; f < fvcount; f++) {
														mainList.removeFooterView(footer);
													}
												}
											}
										});

										mainList.setOnItemClickListener(new OnItemClickListener() {



											@Override
											public void onItemClick(AdapterView<?> parent, View view, int position,
																	long id) {

												if(position < topRatedList.size())
												{
													Intent i = new Intent(NowPlayingActivity.this,SecondActivity.class);
													i.putExtra("id",topRatedList.get(position).getId().toString());
													i.putExtra("Backgoundpath",topRatedList.get(position).getBackgraoudPath().toString());
													i.putExtra("title", topRatedList.get(position).getTitle().toString());
													i.putExtra("adult", topRatedList.get(position).getAdult().toString());
													i.putExtra("overView", topRatedList.get(position).getOverView().toString());
													i.putExtra("popularity", topRatedList.get(position).getPopularity().toString());
													i.putExtra("releaseDate", topRatedList.get(position).getRealeseDate().toString());
													i.putExtra("posterPath", topRatedList.get(position).getPosterPAth().toString());
													i.putExtra("vedio",topRatedList.get(position).getVideo().toString());
													i.putExtra("average_vote",topRatedList.get(position).getVote_average().toString());
													i.putExtra("vote_count",topRatedList.get(position).getVote_count().toString());
													startActivity(i);
												}


											}
										});
										
									}
								});
							}
						}else{
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(NowPlayingActivity.this,	"No Network Detected",Toast.LENGTH_LONG).show();
								}
							});
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	Handler lhandler = new Handler();
	Runnable getDataOnListEnd = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			loadingMore = true;
			JSONData data = new JSONData(NowPlayingActivity.this);
			try {
				
					final List<TopRatedDAta> alist = data.getAlbumList(pageNo,"now_playing");
					lhandler.post(new Runnable() {

						@Override
						public void run() {
							
								loadingMore = false;
								// TODO Auto-generated method stub
								if (alist != null && alist.size() > 0) {
									for (int i = 0; i < alist.size(); i++) {
										
										topRatedList.add(alist.get(i));
									}
								}
								mainListAdapter.notifyDataSetChanged();
							

							
						}
					});
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				topRatedList = new ArrayList<TopRatedDAta>();
				runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(NowPlayingActivity.this,
							"No Network Detected",Toast.LENGTH_LONG).show();
					
					int fvcount = mainList.getFooterViewsCount();
					for (int f = 0; f < fvcount; f++) {
						mainList.removeFooterView(footer);
					}
				}
			});
			}
		}
	};
}
