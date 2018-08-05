package com.geno1024.cleaner;

import android.content.*;
import android.util.*;
import android.widget.*;
import android.view.*;
import java.io.*;

public class TrashFile extends RelativeLayout
{
	private String path = "";
	
	public TrashFile(Context context)
	{
		super(context);
		init(context);
	}
	
	public TrashFile(Context context, AttributeSet attrs) 
	{
		super(context);
		init(context);
	}

    public TrashFile(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context);
		init(context);
	}
	
	public TrashFile(Context context, String path)
	{
		super(context);
		this.path = path;
		init(context);
	}
	
	private void init(final Context context)
	{
		Integer dist = Utils.dp2px(16);
		this.setPadding(dist, dist, dist, dist);
		
		TextView pathT = new TextView(context);
		pathT.setText(path);
		pathT.setTextSize(16.0f);
		RelativeLayout.LayoutParams pathLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		pathLP.addRule(RelativeLayout.CENTER_VERTICAL);
		pathLP.addRule(RelativeLayout.ALIGN_PARENT_START);
		this.addView(pathT, pathLP);
		
		final TextView lockT = new TextView(context);
		lockT.setText(isLocked() ? "🔒" : "🔓");
		lockT.setTextSize(16.0f);
		lockT.setId(0x50000001);
		lockT.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View p1)
				{
					try
					{
						new File(path).createNewFile();
					}
					catch (IOException e)
					{
						Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
					}
					lockT.setText(isLocked() ? "🔒" : "🔓");
				}
			});
		RelativeLayout.LayoutParams lockLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lockLP.addRule(RelativeLayout.CENTER_VERTICAL);
		lockLP.addRule(RelativeLayout.ALIGN_PARENT_END);
		this.addView(lockT, lockLP);
		
		final TextView deleteT = new TextView(context);
		deleteT.setText("🗑");
		deleteT.setTextSize(16.0f);
		deleteT.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View p1)
				{
					new File(path).delete();
				}
			});
		RelativeLayout.LayoutParams deleteLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		deleteLP.addRule(RelativeLayout.CENTER_VERTICAL);
		deleteLP.addRule(RelativeLayout.START_OF, lockT.getId());
		deleteLP.setMargins(dist, 0, dist, 0);
		this.addView(deleteT, deleteLP);
	}
	
	private boolean isLocked()
	{
		return new File(path).isFile();
	}
}
