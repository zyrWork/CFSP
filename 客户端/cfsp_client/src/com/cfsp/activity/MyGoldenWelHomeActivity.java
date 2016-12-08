package com.cfsp.activity;

import com.cfsp_client.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MyGoldenWelHomeActivity extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.my_golden_welhome, container, false);

		Button btnaccount = (Button) view.findViewById(R.id.bt_account);

		btnaccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), MyAccountActivity.class);
				startActivity(intent);
			}

		});
		Button btnscore = (Button) view.findViewById(R.id.bt_score);

		btnscore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), MyScoreActivity.class);
				startActivity(intent);
			}

		});
		Button btnmanage = (Button) view.findViewById(R.id.bt_manage);

		btnmanage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), MyFinancingActivity.class);
				startActivity(intent);
			}

		});

		Button btnorder = (Button) view.findViewById(R.id.bt_order);

		btnorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), MyOrderActivity.class);
				startActivity(intent);
				
			}

		});

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

}
