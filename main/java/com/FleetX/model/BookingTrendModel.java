package com.FleetX.model;

import java.sql.Date;

public class BookingTrendModel {
	private Date bookingDate;
	private int totalBookings;

	public BookingTrendModel(Date bookingDate, int totalBookings) {
		this.bookingDate = bookingDate;
		this.totalBookings = totalBookings;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getTotalBookings() {
		return totalBookings;
	}

	public void setTotalBookings(int totalBookings) {
		this.totalBookings = totalBookings;
	}
}
