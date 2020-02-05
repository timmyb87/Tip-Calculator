package com.twentyint.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
	
	@BindView(R.id.etBillAmount)
	EditText etBillAmount;
	@BindView(R.id.tvTipPercent)
	TextView tvTipPercent;
	@BindView(R.id.tvTipTotal)
	TextView tvTipTotal;
	@BindView(R.id.tvBillTotalAmount)
	TextView tvBillTotalAmount;
	
	float percentage = 0f, tipTotal = 0f, finalAmount = 0f, totalBillAmount = 0f;
	final float DEFAULT_TIP_PERCENT = 15f, REGULAR_TIP_PERCENT = 10f, EXCELLENT_TIP_PERCENT = 20f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		
		setTipValues();
	}
	
	private void setTipValues() {
		tvTipPercent.setText(getString(R.string.main_msg_tipPercent, percentage));
		tvTipTotal.setText(getString(R.string.main_msg_tipTotal, tipTotal));
		tvBillTotalAmount.setText(getString(R.string.main_msg_billTotalResult, finalAmount));
	}
	
	@OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.ibRegularService:
				percentage = REGULAR_TIP_PERCENT;
				break;
			case R.id.ibGoodService:
				percentage = DEFAULT_TIP_PERCENT;
				break;
			case R.id.ibExcellentService:
				percentage = EXCELLENT_TIP_PERCENT;
				break;
		}
		calculateFinalBill();
		setTipValues();
	}
	
	@OnTextChanged(R.id.etBillAmount)
	public void onTextChanged() {
		calculateFinalBill();
		setTipValues();
	}
	
	private void calculateFinalBill() {
		if(percentage == 0){
			percentage = DEFAULT_TIP_PERCENT;
		}
		String temp = etBillAmount.getText().toString();
		if(!temp.equals("") && !temp.equals(".")){
			totalBillAmount = Float.valueOf(temp);
		}
		else{
			totalBillAmount = 0f;
		}
		
		tipTotal = (totalBillAmount * percentage) / 100;
		finalAmount = totalBillAmount + tipTotal;
		
		temp = null;
	}
}
