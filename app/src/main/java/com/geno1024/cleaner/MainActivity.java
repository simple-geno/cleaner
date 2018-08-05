package com.geno1024.cleaner;

import android.app.*;
import android.content.*;
import android.os.*;
import android.preference.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.util.*;
import android.graphics.*;
import android.content.pm.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor e = sp.edit();
        setContentView(R.layout.main);
		
		LinearLayout filesL = (LinearLayout) findViewById(R.id.llFiles);
		LinearLayout packagesL = (LinearLayout) findViewById(R.id.llPackages);
		
		Button filesB = (Button) findViewById(R.id.filesAdd);
		filesB.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View p1)
			{
				AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
				LinearLayout l = new LinearLayout(MainActivity.this);
				l.setOrientation(LinearLayout.VERTICAL);
				b.setView(l);
				Dialog d = b.create();
				
				TextView title = new TextView(MainActivity.this);
				title.setText("Select file");
				int dist = Utils.dp2px(16);
				title.setPadding(dist, dist, dist, dist);
				title.setTextSize(16f);
				title.setTypeface(Typeface.create("", Typeface.BOLD));
				l.addView(title);
				
				ScrollView s = new ScrollView(MainActivity.this);
				LinearLayout files = new LinearLayout(MainActivity.this);
				files.setOrientation(LinearLayout.VERTICAL);
				files.addView(new Button(MainActivity.this));
				s.addView(files);
				l.addView(s);
				
				d.show();
			}
		});
		Button packagesB = (Button) findViewById(R.id.packagesAdd);
		packagesB.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View p1)
			{
				AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
				LinearLayout l = new LinearLayout(MainActivity.this);
				l.setOrientation(LinearLayout.VERTICAL);
				b.setView(l);
				Dialog d = b.create();
				
				PackageManager pm = MainActivity.this.getPackageManager();
				
				ScrollView s = new ScrollView(MainActivity.this);
				LinearLayout packages = new LinearLayout(MainActivity.this);
				packages.setOrientation(LinearLayout.VERTICAL);
				List<PackageInfo> infos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
				for (PackageInfo info : infos)
				{
					TextView t = new TextView(MainActivity.this);
					t.setText(info.packageName);
					packages.addView(t);
				}
				s.addView(packages, ScrollView.LayoutParams.MATCH_PARENT, Utils.dp2px(480));
				l.addView(s, LinearLayout.LayoutParams.MATCH_PARENT, Utils.dp2px(480));
				
				d.show();
			}
		});
		
		Set<String> trashFiles = sp.getStringSet("files", new HashSet<String>());
		Set<String> trashPackages = sp.getStringSet("packages", new HashSet<String>());
		
		//filesL.addView(new TrashFile(this, "aaa"), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
