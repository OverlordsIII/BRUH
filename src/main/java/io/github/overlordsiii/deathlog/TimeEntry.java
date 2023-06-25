package io.github.overlordsiii.deathlog;

public class TimeEntry {

	private int days = 0;

	private int hours;

	private int minutes;

	private int totalTicks;

	private String suffix;

	public TimeEntry(int totalTicks) {
		this.totalTicks = totalTicks;

		int copy = totalTicks;

		while (copy >= 24000) {
			copy -= 24000;
			this.days += 1;
		}

		if (copy > 12000) {
			this.suffix = " PM";
			copy -= 12000;
		} else {
			this.suffix = " AM";
		}

		this.hours = Math.abs(copy / 1000);

		int leftOverTicks = copy % 1000;

		this.minutes = Math.abs((int) Math.round(leftOverTicks / 16.6));
	}

	public int getDays() {
		return days;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getTotalTicks() {
		return totalTicks;
	}

	public String getString() {
		return "Day " + days + ", " + hours + ":" + minutes + suffix;
	}

	// finds distance between current time and time of this entry, returns as a seperate entry
	@Override
	public String toString() {
		return getString();
	}
}
