package tv;

public class TV {
	private int volume;      // 0 ~ 100
	private int channel;     // 1 ~ 255
	private boolean power;   // getter, setter 대신에 사용
	
	public TV(int volume, int channel, boolean power) {
		this.volume = volume;
		this.channel = channel;
		this.power = power;
	}
	
	// volume, channel은 1보다 낮아지면 255로 라운딩
	
	public void channel() {
		if(this.channel > 255) {
			this.channel = 1;
		}else if (this.channel < 1) {
			this.channel = 255;
		}
	}
	public void channel(int channel) {
		this.channel = channel;
		this.channel();
	}
	
	public void channel(boolean channel) {
		if(channel) {
			this.channel++;
		}else {
			this.channel--;
		}
		this.channel();
	}
	
	public void status() {
		String onoff = power? "on" : "off";
		System.out.println("TV[power=" + onoff + ", channel=" + channel + ", volume=" + volume + "]");

	}
	
	public void power(boolean power) {
		this.power = power;
	}

	public void volume() {
		if(this.volume > 100) {
			this.volume = 0;
		}else if (this.volume < 0) {
			this.volume = 100;
		}
	}
	
	public void volume(int volume) {
		this.volume = volume;
		this.volume();
	}
	
	public void volume(boolean volume) {
		if(volume) {
			this.volume++;
		}else {
			this.volume--;
		}
		this.volume();	
	}
	
}
