package com.lxc.file_editor;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LaiXiancheng on 2017/12/11.
 * Email: lxc.sysu@qq.com
 */

public class FileEditor {
	Context mContext;

	public FileEditor(Context mContext) {
		this.mContext = mContext;
	}

	public void deleteFile(String name)
	{
		mContext.deleteFile(name);
		showToast("Delete successfully");
	}

	public String getContent(String name)
	{
		String content = "";
		try
		{
			FileInputStream inputStream = mContext.openFileInput(name);
			byte[] arrayOfByte = new byte[inputStream.available()];
			inputStream.read(arrayOfByte);
			inputStream.close();
			showToast("Load successfully");
			content = new String(arrayOfByte);
			return content;
		}
		catch (IOException e)
		{
			showToast("Fail to load file");
			e.printStackTrace();
		}
		return content;
	}


	public void saveContent(String name, String content)
	{
		try
		{
			FileOutputStream outputStream = mContext.openFileOutput(name, 0);
			outputStream.write(content.getBytes());
			outputStream.close();
			showToast("Save successfully");
		}
		catch (IOException e)
		{
			showToast("Fail to save file");
			e.printStackTrace();
		}
	}
	private void showToast(String msg){
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}
}
