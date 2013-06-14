package com.itcast.db;

import com.itcast.service.DBOpenHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
	private DBOpenHelper dbOpenHelper;
	private static final UriMatcher MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
	private static final int PERSONS=1;
	private static final int PERSONSID=2;
	static{
		MATCHER.addURI("com.itcast.provides.personprovider","person", PERSONS);
		MATCHER.addURI("com.itcast.provides.personprovider","person/#", PERSONSID);
	}

	@Override
	public boolean onCreate() {
		dbOpenHelper=new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
		 return  db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			
	    case 2:
			long rowid=ContentUris.parseId(uri);
			String where="personid"+rowid;
			if (selection!=null && "".equals(selection.trim())) {
				where+="and"+selection;
			}
			return db.query("person", projection, where, selectionArgs, null, null, sortOrder);
		default:
			throw new IllegalArgumentException("this is Unknown Uri:"+uri);
		}
	}

	@Override
	public String getType(Uri uri) {
		switch (MATCHER.match(uri)) {
		case 1:
			return "vnd.android.cursor.dir/person";
		case 2:
			return "vnd.android.cursor.item/person";
		default:
			throw new IllegalArgumentException("this is UnKnown Uri:"+uri);
		}

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			long rowid=db.insert("person","name",values);//主建值
//			Uri insertUri=Uri.parse("content://com.itcast.provides.personprovider/person"+rowid);
			Uri insertUri=ContentUris.withAppendedId(uri, rowid);
			//发出数据变化通知
			this.getContext().getContentResolver().notifyChange(uri, null);
			 return insertUri;
		default:
			throw new IllegalArgumentException("this is UnKnown Uri:"+uri);
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		int num=0;
		switch (MATCHER.match(uri)) {
		case 1:
			num=db.delete("person", selection, selectionArgs);
			break;
		case 2:
			long rowid=ContentUris.parseId(uri);
			String where="personid="+rowid;
			if (selection!=null && !"".equals(selection.trim())) {
				where+="and"+selection;
			}
			num=db.delete("person", where, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("this is Unknown Uri:"+uri);
		}
		return num;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		int num=0;
		switch (MATCHER.match(uri)) {
		case 1:
			num=db.update("person", values, selection, selectionArgs);
			break;
		case 2:
			long rowid=ContentUris.parseId(uri);
			String where="personid="+rowid;
			if (selection!=null && !"".equals(selection.trim())) {
				where+="and"+selection;
			}
			num=db.update("person", values, where, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("this is Unknown Uri:"+uri);
		}
		return num;
	}

}
