package com.cfsp.activity;

import com.cfsp.QRCode.CaptureActivity;
import com.cfsp_client.R;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class TwoDimensionalCodeActivity extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.two_dimensional_code, container,false);
		Button btn_scan = (Button) view.findViewById(R.id.btn_scan); 
		ImageButton btn_return=(ImageButton)view.findViewById(R.id.btn_return);
		
		btn_scan.setOnClickListener(new OnClickListener() {
			// 跳转到二维码界面
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						CaptureActivity.class);
				startActivity(intent);
			}
		});
		btn_return.setOnClickListener(new OnClickListener() {
			// 跳转到二维码界面
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						MainPageActivity.class);
				startActivity(intent);
			}
		});
		return view;
	}

	
}

