package com.lxc.file_editor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FileEditActivity extends AppCompatActivity {

	private EditText etFileName;
	private EditText etFileContent;
	FileEditor fileEditor = new FileEditor(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_edit);
		etFileName = findViewById(R.id.et_filename);
		etFileContent = findViewById(R.id.et_content);
		Button btSave = findViewById(R.id.bt_save);
		Button btLoad = findViewById(R.id.bt_load);
		Button btClear = findViewById(R.id.bt_clear);
		Button btDelete = findViewById(R.id.bt_delete);
		btSave.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				fileEditor.saveContent(etFileName.getText().toString(), etFileContent.getText().toString());
			}
		});
		btLoad.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				String content = fileEditor.getContent(etFileName.getText().toString());
				etFileContent.setText(content);
			}
		});
		btClear.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				etFileContent.setText("");
			}
		});
		btDelete.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				fileEditor.deleteFile(etFileName.getText().toString());
			}
		});

	}

}
