package com.zlt.test;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

	private String bookName;
	private String bookAuthor;
	private int publishTime;

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setPublishTime(int publishTime) {
		this.publishTime = publishTime;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public int getPublishTime() {
		return publishTime;
	}

	public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {

		@Override
		public Book createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			Book mBook = new Book();
			mBook.bookName = source.readString();
			mBook.bookAuthor = source.readString();
			mBook.publishTime = source.readInt();
			return mBook;
		}

		@Override
		public Book[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Book[size];
		}

	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(bookName);
		dest.writeString(bookAuthor);
		dest.writeInt(publishTime);
	}

}
