package com.lxc.file_editor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

	EditText etNewPassword;
	EditText etConfirmPassword;
	Button btOK;
	Button btClear;
	private SharedPreferences.Editor editor;
	private boolean isRegistered = false;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		etNewPassword = findViewById(R.id.edit_new_password);
		etConfirmPassword = findViewById(R.id.edit_confirm);
		btOK = findViewById(R.id.bt_ok);
		btClear = findViewById(R.id.bt_clear);

		/* 检查是否已经有密码了*/
		sp = this.getPreferences(MODE_PRIVATE);//以activity名作为文件名
		editor = sp.edit();
		if (!TextUtils.isEmpty(sp.getString("Password", null)))
		{
			isRegistered = true;
			etNewPassword.setVisibility(View.GONE);
			etConfirmPassword.setHint("Password");
		}
		/* 结束检查是否已经有密码*/

		btClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				etNewPassword.setText("");
				etConfirmPassword.setText("");
			}
		});
		btOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String strNew = etNewPassword.getText().toString();
				String strConfirm = etConfirmPassword.getText().toString();
				if (PasswordActivity.this.isRegistered)
				{
					if (strConfirm.equals(sp.getString("Password", null)))
					{
						Intent i = new Intent();
						i.setClass(PasswordActivity.this, FileEditActivity.class);
						startActivity(i);
						return;
					}
					if (TextUtils.isEmpty(strConfirm))
					{
						showToast("Password cannot be empty");
						return;
					}
					showToast("Password Mismatch");
					return;
				}
				if ((TextUtils.isEmpty(strNew)) || (TextUtils.isEmpty(strConfirm)))
				{
					showToast("Password cannot be empty");
					return;
				}
				if (!strNew.equals(strConfirm))
				{
					showToast("Password Mismatch");
				}
				else{
					editor.putString("Password", strNew);
					editor.apply();
					Intent i = new Intent();
					i.setClass(PasswordActivity.this, FileEditActivity.class);
					startActivity(i);
				}

			}
		});

	}

	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
