package com.cfsp.activity;


import com.cfsp_client.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ViewFlipper;


public class HomePageActivity extends Fragment   {

	ViewFlipper flipper;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_page, container,false);
		flipper = (ViewFlipper)view.findViewById(R.id.flipper);
		flipper.startFlipping();
		ImageButton btn_economy = (ImageButton) view.findViewById(R.id.ibtn_economy); 
		ImageButton btn_health = (ImageButton) view.findViewById(R.id.ibtn_health);
		ImageButton btn_people = (ImageButton) view.findViewById(R.id.ibtn_people);
		ImageButton btn_other = (ImageButton) view.findViewById(R.id.ibtn_other);
		btn_economy.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),NewsEconomyActivity.class);
				 startActivity(intent);}

		});
		btn_health.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),NewsHealthActivity.class);
				 startActivity(intent);}

		});
		btn_people.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),NewsPeopleActivity.class);
				 startActivity(intent);}

		});
		btn_other.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),NewsOtherActivity.class);
				 startActivity(intent);}

		});
		return view;
	}

}





